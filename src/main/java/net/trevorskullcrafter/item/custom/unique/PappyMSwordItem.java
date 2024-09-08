package net.trevorskullcrafter.item.custom.unique;

import net.minecraft.component.ComponentMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.trevorskullcrafter.item.ModDataComponentTypes;
import net.trevorskullcrafter.item.TSItems;
import net.trevorskullcrafter.item.custom.PillarAligned;
import net.trevorskullcrafter.util.StyleUtil;

import java.util.List;
import java.util.random.RandomGenerator;

public class PappyMSwordItem extends SwordItem implements StyleUtil.StyleSwitchable, PillarAligned {
	public PappyMSwordItem(ToolMaterial toolMaterial, Settings settings) { super(toolMaterial, settings.component(ModDataComponentTypes.MAX_STYLE, 4)); }

    @Override public Formatting getStyleSwitchFormatting(ItemStack stack){ return TSItems.getOrSetComponent(stack, ModDataComponentTypes.STYLE, 1) % 2 == 0?
			Formatting.GREEN : Formatting.RED; }
    @Override public boolean hasGlint(ItemStack stack){ return TSItems.getOrSetComponent(stack, ModDataComponentTypes.PALADIN_CHARGE, 0) == 6; }
    Text[] paladinText = new Text[]{
            Text.literal("◇ ◇ ◇ ◇ ◇ ◇").formatted(Formatting.GRAY),
            Text.literal("◆ ◇ ◇ ◇ ◇ ◇").formatted(Formatting.DARK_GREEN),
            Text.literal("◆ ◆ ◇ ◇ ◇ ◇").formatted(Formatting.DARK_GREEN),
            Text.literal("◆ ◆ ◆ ◇ ◇ ◇").formatted(Formatting.DARK_GREEN),
            Text.literal("◆ ◆ ◆ ◆ ◇ ◇").formatted(Formatting.GREEN),
            Text.literal("◆ ◆ ◆ ◆ ◆ ◇").formatted(Formatting.GREEN),
            Text.literal("◆ ◆ ◆ ◆ ◆ ◆").formatted(Formatting.AQUA), };

    @Override public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand){
        if(world instanceof ServerWorld serverWorld) {
			ItemStack stack = user.getStackInHand(hand);
			switch (TSItems.getOrSetComponent(stack, ModDataComponentTypes.STYLE, 1)) {
				case 1 -> {
					user.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 200, 1, false, false));
					user.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 200, 2, false, false));
					user.addStatusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 200, 2, false, false));
					serverWorld.playSoundFromEntity(null, user, SoundEvents.ENTITY_WARDEN_SONIC_CHARGE, SoundCategory.BLOCKS, 2.0F, 5.0F);
					user.sendMessage(Text.literal("Energized!").formatted(Formatting.GREEN), true);
					user.getItemCooldownManager().set(this, 200);
					return TypedActionResult.success(stack);
				}
				case 4 -> {
					user.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 5, 255, false, false));
					user.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 5, 3, false, false));
					serverWorld.playSoundFromEntity(null, user, SoundEvents.BLOCK_ANVIL_LAND, SoundCategory.BLOCKS, 2.0F, RandomGenerator.getDefault().nextFloat());
					user.sendMessage(Text.literal("Parry!").formatted(Formatting.GRAY), true);
					user.getItemCooldownManager().set(this, 100);
					return TypedActionResult.success(stack);
				}
				default -> { return TypedActionResult.pass(stack); }
			}
        } return super.use(world, user, hand);
    }

    @Override public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if(world instanceof ServerWorld serverWorld && selected && entity instanceof PlayerEntity player) {
			switch (TSItems.getOrSetComponent(stack, ModDataComponentTypes.STYLE, 1)){
				case 1 -> { player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 1, 0, false, false)); }
				case 2 -> {
					player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 10, 1, false, false));
					if (TSItems.getOrSetComponent(stack, ModDataComponentTypes.PALADIN_CHARGE, 0) == 6) {
						player.sendMessage(Text.literal("◆ ◆ ◆ ◆ ◆ ◆").formatted(serverWorld.getTime() % 2 == 0 ? Formatting.AQUA : Formatting.DARK_AQUA), true);
					}
				}
				case 3 -> { player.addStatusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 100, 2, false, false)); }
				case 4 -> { player.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 1, 0, false, false)); }
			}
        } super.inventoryTick(stack, world, entity, slot, selected);
    }

    @Override public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		switch (TSItems.getOrSetComponent(stack, ModDataComponentTypes.STYLE, 1)){
			case 2 -> {
				int charge = TSItems.getOrSetComponent(stack, ModDataComponentTypes.PALADIN_CHARGE, 0);
				if(charge == 6){
					target.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 77, 2, false, false));
					target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 77, 1, false, false));
				}
				stack.applyComponentsFrom(ComponentMap.builder().add(ModDataComponentTypes.PALADIN_CHARGE, charge == 6? 0 : charge+1).build());
				if(attacker instanceof PlayerEntity player) { player.sendMessage(paladinText[charge], true); }
			}
			case 3 -> { attacker.addStatusEffect(new StatusEffectInstance(StatusEffects.SATURATION, 1, 1, false, false)); }
		} return true;
    }

	@Override public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
		switch (TSItems.getOrSetComponent(stack, ModDataComponentTypes.STYLE, 1)){
			case 1 -> tooltip.add(Text.literal("Right click to become energized!").formatted(Formatting.GRAY));
			case 2 -> { tooltip.add(Text.literal("Every seventh strike hits harder!").formatted(Formatting.GRAY));
				tooltip.add(paladinText[TSItems.getOrSetComponent(stack, ModDataComponentTypes.PALADIN_CHARGE, 0)]);
			}
			case 3 -> tooltip.add(Text.literal("Striking enemies satiates you!").formatted(Formatting.GRAY));
			case 4 -> tooltip.add(Text.literal("Right click to parry!").formatted(Formatting.GRAY));
		}
		super.appendTooltip(stack, context, tooltip, type);
	}
}
