package net.trevorskullcrafter.item.custom;

import net.minecraft.component.ComponentMap;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ContainerComponent;
import net.minecraft.component.type.DyedColorComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.trevorskullcrafter.TrevorsSentinels;
import net.trevorskullcrafter.entity.ModEntities;
import net.trevorskullcrafter.entity.custom.PhaserProjectileEntity;
import net.trevorskullcrafter.item.CooldownComponent;
import net.trevorskullcrafter.item.ModDataComponentTypes;
import net.trevorskullcrafter.item.PhaserModifiersComponent;
import net.trevorskullcrafter.item.TSItems;
import net.trevorskullcrafter.sound.ModSounds;
import net.trevorskullcrafter.util.StyleUtil;
import net.trevorskullcrafter.util.TextUtil;

import java.awt.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.List;

import static net.trevorskullcrafter.item.PhaserModifiersComponent.*;

public class PhaserItem extends Item implements StyleUtil.StyleSwitchable, Reloadable, FuelableItem, ExtendedTooltipItem {
	public PhaserItem(Settings settings){ super(settings.maxCount(1).component(ModDataComponentTypes.MAX_STYLE, 2)); }

	public static ItemStack getCustomPhaser(ItemStack phaser, Text name, Color skinColor, Color lensColor, ItemStack... attachments){
		if(name != null) { phaser.applyComponentsFrom(ComponentMap.builder().add(DataComponentTypes.CUSTOM_NAME, name).build()); }
		List<ItemStack> stacks = new ArrayList<>(Arrays.stream(attachments).toList());

		ItemStack skin = ItemStack.EMPTY;
		if (skinColor != null) { skin = new ItemStack(TSItems.Tech.PAINT_PACK);
			skin.applyComponentsFrom(ComponentMap.builder().add(DataComponentTypes.DYED_COLOR, new DyedColorComponent(skinColor.getRGB(), true)).build());
		} stacks.add(0, skin);

		ItemStack lens = ItemStack.EMPTY;
		if (lensColor != null) { lens = new ItemStack(TSItems.Tech.CHROMATIC_LENS);
			lens.applyComponentsFrom(ComponentMap.builder().add(DataComponentTypes.DYED_COLOR, new DyedColorComponent(lensColor.getRGB(), true)).build());
		} stacks.add(1, lens);

		phaser.applyComponentsFrom(ComponentMap.builder().add(DataComponentTypes.CONTAINER, ContainerComponent.fromStacks(stacks)).build());
		return initializePhaser(phaser);
	}

	public static ItemStack initializePhaser(ItemStack phaser){
		PhaserModifiersComponent phaserModifiers = phaser.get(ModDataComponentTypes.PHASER_MODIFIERS);
		if(phaserModifiers == null) { phaserModifiers = TSItems.Tech.SCRAP_METAL_PHASER.getDefaultStack().get(ModDataComponentTypes.PHASER_MODIFIERS); } assert phaserModifiers != null;

		ContainerComponent container = phaser.get(DataComponentTypes.CONTAINER);
		if(container == null){ container = ContainerComponent.fromStacks(new ArrayList<>()); }
		ItemStack[] attachments = container.stream().toList().toArray(new ItemStack[0]);

		int remainingSlots = phaserModifiers.attachment_slots();
		for(int i = 2; i < attachments.length; i++){
			PhaserModifiersComponent attachmentModifiers = attachments[i].get(ModDataComponentTypes.PHASER_MODIFIERS);
			if(attachmentModifiers != null && attachmentModifiers.isAttachment() && attachmentModifiers.attachment_slots() <= remainingSlots){
				remainingSlots -= attachmentModifiers.attachment_slots();
				List<StatusEffectInstance> projectile_effects = new ArrayList<>(phaserModifiers.projectile_effects());
				projectile_effects.addAll(attachmentModifiers.projectile_effects());

				PhaserModifiersComponent tempModifiers = new Builder()
						.projectile_damage(phaserModifiers.projectile_damage() + attachmentModifiers.projectile_damage())
						.projectile_lifetime(phaserModifiers.projectile_lifetime() + attachmentModifiers.projectile_lifetime())
						.projectile_inaccuracy(phaserModifiers.projectile_inaccuracy() + attachmentModifiers.projectile_inaccuracy())
						.projectile_recoil(phaserModifiers.projectile_recoil() + attachmentModifiers.projectile_recoil())
						.burst_projectiles(phaserModifiers.burst_projectiles() + attachmentModifiers.burst_projectiles())
						.burst_cooldown(phaserModifiers.burst_cooldown() + attachmentModifiers.burst_cooldown())
						.reload_cooldown(phaserModifiers.reload_cooldown() + attachmentModifiers.reload_cooldown())
						.magazine_size(phaserModifiers.magazine_size() + attachmentModifiers.magazine_size())
						.automatic_reloading(attachmentModifiers.automatic_reloading() || phaserModifiers.automatic_reloading())
						.lingering_effects(attachmentModifiers.lingering_effects() || phaserModifiers.lingering_effects())
						.projectile_effects(projectile_effects).build();
				phaser.applyComponentsFrom(ComponentMap.builder().add(ModDataComponentTypes.PHASER_MODIFIERS, tempModifiers).build());
			}
		} setToNeedReload(phaser, true);
		return phaser;
	}

	@Override public void onCraft(ItemStack stack, World world) { super.onCraft(initializePhaser(stack), world); }

	@Override public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand){
		ItemStack stack = user.getStackInHand(hand);
		if(stack.get(ModDataComponentTypes.COOLDOWN) == null) { shoot(world, stack, user); }
		if(stack.getItem() instanceof PhaserItem phaser && phaser.getFuel(stack) == 0){ setToNeedReload(stack, true); }
		return super.use(world, user, hand);
    }

	public void shoot(World world, ItemStack stack, PlayerEntity user) {
		PhaserModifiersComponent modifiers = stack.get(ModDataComponentTypes.PHASER_MODIFIERS); if(modifiers != null){
			if (!needsReload(stack)) {
				world.playSoundFromEntity(null, user, getShootSound(modifiers.getType()), SoundCategory.PLAYERS, 1, 1);
				for (int i = 1; i <= modifiers.burst_projectiles(); i++) {
					PhaserProjectileEntity projectile = new PhaserProjectileEntity(ModEntities.PHASER_PROJECTILE, world, user, modifiers.projectile_lifetime(), modifiers.projectile_damage(),
							getProjectileColor(stack).getRGB(), modifiers.projectile_effects());
					if(world instanceof ServerWorld){
						projectile.setVelocity(user.getPitch(), user.getYaw(), 0.0F, 1.5f, modifiers.projectile_inaccuracy() / 10f);
						world.spawnEntity(projectile);
					}
					user.setPitch(user.getPitch()+user.getRandom().nextBetween(-modifiers.projectile_recoil(), modifiers.projectile_recoil()));
					user.setYaw(user.getYaw()+user.getRandom().nextBetween(-modifiers.projectile_recoil(), modifiers.projectile_recoil()));
				}
				if (user instanceof ServerPlayerEntity serverPlayerEntity) { serverPlayerEntity.incrementStat(Stats.USED.getOrCreateStat(stack.getItem())); }
				user.fallDistance = (float) Math.abs(user.getVelocity().y) * 4;
				if (!user.getAbilities().creativeMode) { stack.set(ModDataComponentTypes.FUEL, getFuel(stack)-1); }
			} else { world.playSoundFromEntity(null, user, getShootFailSound(modifiers.getType()), SoundCategory.PLAYERS, 1, 1); }
			sendFuelMessage(user, stack); //play shoot animation
			stack.set(ModDataComponentTypes.COOLDOWN, new CooldownComponent(modifiers.burst_cooldown()));
		}
    }

    @Override public void reload(ItemStack stack, World world, Entity user) {
        if(world instanceof ServerWorld) {
			PhaserModifiersComponent modifiers = stack.get(ModDataComponentTypes.PHASER_MODIFIERS); if(modifiers != null && getFuel(stack) > 0 && needsReload(stack)){
				setToNeedReload(stack, false);
				//triggerAnim(user, GeoItem.getOrAssignId(stack, serverWorld), "reloadController", "reload");
				world.playSoundFromEntity(null, user, getReloadSound(modifiers.getType()), SoundCategory.PLAYERS, 3, 0);
				stack.set(ModDataComponentTypes.COOLDOWN, new CooldownComponent(modifiers.reload_cooldown()));
			}
		}
    }

	@Override public boolean addFuel(ItemStack stack, ItemStack otherStack, Entity entity) {
		boolean result = FuelableItem.super.addFuel(stack, otherStack, entity); if(result) { setToNeedReload(stack, true); } return result;
	}

	@Override public Optional<ItemStack> removeFuel(ItemStack stack, Entity entity) {
		Optional<ItemStack> result = FuelableItem.super.removeFuel(stack, entity); if(result.isPresent()) { setToNeedReload(stack, true); } return result;
	}

	@Override public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
		PhaserModifiersComponent modifiers = stack.get(ModDataComponentTypes.PHASER_MODIFIERS);
		if(modifiers != null){
			double firingRate = (20.0 / (modifiers.burst_cooldown() +1)) * modifiers.burst_projectiles();
			String firingRateString = new DecimalFormat("#.#").format(firingRate);
			Text column = Text.literal(" | ").formatted(Formatting.DARK_GRAY);
			tooltip.add(Text.empty().append(Text.literal("☄ "+ modifiers.burst_projectiles()).formatted(TextUtil.formattingFromQuotient(modifiers.burst_projectiles(), 5)))
					.append(column).append(Text.literal(modifiers.projectile_damage() < 1? "❤ " : "☠ " + Math.abs(modifiers.projectile_damage()))
							.formatted(TextUtil.formattingFromQuotient(Math.abs(modifiers.projectile_damage()), 20))).append(column)
					.append(Text.literal("\uD83D\uDD25 "+ firingRateString).formatted(TextUtil.formattingFromQuotient(firingRate, 5))));

			MutableText text = Text.empty();
			int availableSlots = modifiers.attachment_slots();

			ContainerComponent container = stack.get(DataComponentTypes.CONTAINER);
			if(container == null){ container = ContainerComponent.fromStacks(new ArrayList<>()); }
			ItemStack[] attachments = container.stream().toList().toArray(new ItemStack[0]);

			Color color = TextUtil.RED;
			for (int i = 2; i < attachments.length; i++) {
				PhaserModifiersComponent attachmentModifiers = attachments[i].get(ModDataComponentTypes.PHASER_MODIFIERS);
				if (attachmentModifiers != null && attachmentModifiers.isAttachment() && attachmentModifiers.attachment_slots() <= availableSlots) {
					for (int j = attachmentModifiers.attachment_slots(); j >= 0; j--) { text.append(TextUtil.coloredText(Text.literal("◆ "), color)); }
					if (color == TextUtil.RED) { color = TextUtil.GOLD; }
					else if (color == TextUtil.GOLD) { color = TextUtil.YELLOW; }
					else if (color == TextUtil.YELLOW) { color = TextUtil.GREEN; }
					else if (color == TextUtil.GREEN) { color = TextUtil.AQUA; }
					else if (color == TextUtil.AQUA) { color = TextUtil.BLUE; }
					else if (color == TextUtil.BLUE) { color = TextUtil.LIGHT_PURPLE; }
					else { color = TextUtil.RED; }
					availableSlots -= attachmentModifiers.attachment_slots();
				}
			}
			for (int i = availableSlots; i >= 0; i--) { text.append(Text.literal("◇ ").formatted(Formatting.GRAY)); }
			tooltip.add(text);
		} super.appendTooltip(stack, context, tooltip, type);
	}

	@Override public Text fuelText(ItemStack stack) {
		MutableText fuel = Text.empty().append(FuelableItem.super.fuelText(stack));
		if(getStyles(stack) > 1) { fuel.append(Text.literal("△").formatted(TSItems.getComponentValue(stack, ModDataComponentTypes.STYLE, 1) == 1? Formatting.RED : Formatting.GREEN)); }
		return fuel;
	}

	@Override public List<Text> longText(ItemStack stack) {
		List<Text> tooltip = ExtendedTooltipItem.super.longText(stack);
		PhaserModifiersComponent modifiers = stack.get(ModDataComponentTypes.PHASER_MODIFIERS);
		if(modifiers != null){
			tooltip.add(Text.empty());
			tooltip.add(PHASER_DETAILS);
			tooltip.add(Text.literal(" ").append(DECAL_COLOR)
					.append(TextUtil.coloredText("#" + Integer.toHexString(getDecalColor(stack).getRGB()).toUpperCase().substring(2), getDecalColor(stack))));
			tooltip.add(Text.literal(" ").append(PROJECTILE_COLOR)
					.append(TextUtil.coloredText("#" + Integer.toHexString(getProjectileColor(stack).getRGB()).toUpperCase().substring(2), getProjectileColor(stack))));
			tooltip.add(Text.literal(" ").append(PROJECTILE_LIFETIME).append(Text.literal(String.valueOf(modifiers.projectile_lifetime()))
					.formatted(TextUtil.formattingFromQuotient(modifiers.projectile_lifetime(), 4))));
			tooltip.add(Text.literal(" ").append(PROJECTILE_INACCURACY).append(Text.literal(String.valueOf(modifiers.projectile_inaccuracy()))
					.formatted(TextUtil.formattingFromQuotient(modifiers.projectile_inaccuracy(), 60, TextUtil.reverseFormattings))));
			tooltip.add(Text.literal(" ").append(PROJECTILE_RECOIL).append(Text.literal(String.valueOf(modifiers.projectile_recoil()))
					.formatted(TextUtil.formattingFromQuotient(modifiers.projectile_recoil(), 4, TextUtil.reverseFormattings))));
			tooltip.add(Text.literal(" ").append(BURST_COOLDOWN).append(Text.literal(String.valueOf(modifiers.burst_cooldown()))
					.formatted(TextUtil.formattingFromQuotient(modifiers.burst_cooldown(), 4, TextUtil.reverseFormattings))));
			tooltip.add(Text.literal(" ").append(RELOAD_COOLDOWN).append(Text.literal(String.valueOf(modifiers.reload_cooldown()))
					.formatted(TextUtil.formattingFromQuotient(modifiers.reload_cooldown(), 4, TextUtil.reverseFormattings))));
			if(modifiers.automatic_reloading()) { tooltip.add(Text.literal(" ").append(AUTOMATIC_RELOADING)); }
			if(modifiers.lingering_effects()) { tooltip.add(Text.literal(" ").append(LINGERING_EFFECTS)); }
			if(modifiers.projectile_effects() != null) {
				tooltip.add(PROJECTILE_EFFECTS);
				for(StatusEffectInstance effect : modifiers.projectile_effects()){ tooltip.add(Text.literal(" ").append(APPLIES).append(TextUtil.potionText(effect, false))); }
			}
		} return tooltip;
	}

	public SoundEvent getShootSound(Type type){ return switch (type){
		case Type.PISTOL -> ModSounds.BLASTER_SHOOT;
		case Type.SNIPER -> ModSounds.BLASTER_SHOOT;
		case Type.SHOTGUN -> ModSounds.BLASTER_SHOOT;
		case Type.RIFLE -> ModSounds.BLASTER_SHOOT;
		default -> ModSounds.BLASTER_SHOOT; };
	}
	public SoundEvent getShootFailSound(Type type){ return switch (type){
		case Type.PISTOL -> SoundEvents.BLOCK_LEVER_CLICK;
		case Type.SNIPER -> SoundEvents.BLOCK_LEVER_CLICK;
		case Type.SHOTGUN -> SoundEvents.BLOCK_LEVER_CLICK;
		case Type.RIFLE -> SoundEvents.BLOCK_LEVER_CLICK;
		default -> SoundEvents.BLOCK_LEVER_CLICK; };
	}
	public SoundEvent getReloadSound(Type type){ return switch (type){
		case Type.PISTOL -> ModSounds.PISTOL_RELOAD;
		case Type.SNIPER -> ModSounds.SNIPER_RELOAD;
		case Type.SHOTGUN -> ModSounds.BIO_RELOAD;
		case Type.RIFLE -> ModSounds.SHOTGUN_RELOAD;
		default -> ModSounds.PISTOL_RELOAD; };
	}

	public static void setToNeedReload(ItemStack stack, boolean value) { stack.set(ModDataComponentTypes.PHASER_LOCK, value); }
	public boolean needsReload(ItemStack stack) { return Objects.requireNonNullElse(stack.get(ModDataComponentTypes.PHASER_LOCK), false); }

	public Color getComponentColor(ItemStack stack, int i, Color fallback){
		ContainerComponent container = stack.get(DataComponentTypes.CONTAINER);
		if(container == null){ return fallback; }
		return new Color(DyedColorComponent.getColor(container.stream().toList().get(i), fallback.getRGB()));
	}
	public Color getDecalColor(ItemStack stack) { return getComponentColor(stack, 0, TextUtil.WHITE); }
	public Color getProjectileColor(ItemStack stack) { return getComponentColor(stack, 1, TextUtil.PURE); }
	@Override public int getMaxFuel(ItemStack stack) {
		PhaserModifiersComponent modifiers = stack.get(ModDataComponentTypes.PHASER_MODIFIERS);
		if(modifiers == null) { return 6; }
		return modifiers.magazine_size();
	}
	@Override public int getItemBarColor(ItemStack stack) { return needsReload(stack)? Color.GRAY.getRGB() : getProjectileColor(stack).getRGB(); }
	@Override public boolean isItemBarVisible(ItemStack stack) { return true; }
    @Override public int getItemBarStep(ItemStack stack) { return Math.round(getFuel(stack) * 13.0F / getMaxFuel(stack)); }
    @Override public UseAction getUseAction(ItemStack stack) { return UseAction.CROSSBOW; }
	@Override public Text getSwitchMessagePrefix(ItemStack stack) { return StyleUtil.mode; }
	@Override public Text getCurrentStyleTranslation(ItemStack stack) { return Text.translatable("style.item."+ TrevorsSentinels.MOD_ID + ".phaser." + stack.get(ModDataComponentTypes.STYLE)); }
	@Override public boolean showStyleSwitchTooltip(ItemStack stack) { return false; }
	@Override public Formatting getStyleSwitchFormatting(ItemStack stack){
		return TSItems.getComponentValue(stack, ModDataComponentTypes.STYLE, 1) == 1? Formatting.RED : Formatting.GREEN;
	}
    @Override public SoundEvent getSwitchSoundEvent(ItemStack stack) { return SoundEvents.UI_BUTTON_CLICK.value(); }
    @Override public float getSwitchSoundPitch(ItemStack stack) { return 1.5f; }
	@Override public boolean allowComponentsUpdateAnimation(PlayerEntity player, Hand hand, ItemStack oldStack, ItemStack newStack) { return false; }
}
