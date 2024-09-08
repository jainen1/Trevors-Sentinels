package net.trevorskullcrafter.item.custom.unique;

import net.minecraft.component.ComponentMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.GlobalPos;
import net.minecraft.world.World;
import net.trevorskullcrafter.item.ModDataComponentTypes;
import net.trevorskullcrafter.item.custom.PillarAligned;
import net.trevorskullcrafter.util.Pillars;
import net.trevorskullcrafter.util.StyleUtil;

import java.util.List;

public class ThanatuBladeItem extends SwordItem implements StyleUtil.StyleSwitchable, PillarAligned {
    public ThanatuBladeItem(ToolMaterial toolMaterial, Settings settings) { super(toolMaterial, settings); }

    @Override public Formatting getStyleSwitchFormatting(ItemStack stack) {
        return (StyleUtil.StyleSwitchable.getStyle(stack) & 2) == 0? Formatting.DARK_PURPLE : Formatting.LIGHT_PURPLE;
    }

    @Override public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (world instanceof ServerWorld serverWorld && hand == Hand.MAIN_HAND) {
            if (StyleUtil.StyleSwitchable.getStyle(stack) == 1) {
                if (user.isSneaking()) {
                    stack.applyComponentsFrom(ComponentMap.builder().add(ModDataComponentTypes.STORED_POSITION, GlobalPos.create(world.getRegistryKey(), user.getBlockPos()))
						.add(ModDataComponentTypes.TIMER, (int) serverWorld.getTime() + 200).build());
					BlockPos pos = stack.get(ModDataComponentTypes.STORED_POSITION).pos();
                    user.sendMessage(Text.translatable("trevorssentinels:bound_position", pos.getX(), pos.getY(), pos.getZ())
						.formatted(Formatting.LIGHT_PURPLE), true);
                    serverWorld.playSound(null, user.getBlockPos(), SoundEvents.ENTITY_WARDEN_SONIC_CHARGE, SoundCategory.BLOCKS, 2.0F, 2.0F);
                    user.getItemCooldownManager().set(this, 10);
                    return TypedActionResult.success(stack);
                } else {
					GlobalPos pos = stack.get(ModDataComponentTypes.STORED_POSITION);
                    if (pos != null) {
						user.teleport(pos.pos().getX(), pos.pos().getY(), pos.pos().getZ(), true);
                        serverWorld.playSound(null, user.getBlockPos(), SoundEvents.ENTITY_WARDEN_SONIC_BOOM, SoundCategory.BLOCKS, 2.0f, 2.0f);
                        user.getItemCooldownManager().set(this, 20);
                        return TypedActionResult.success(stack);
                    } else user.sendMessage(Text.literal("This item is not bound to a location!").formatted(Formatting.GRAY), true);
                }
            } else {
                LightningEntity lightningEntity = new LightningEntity(EntityType.LIGHTNING_BOLT, serverWorld);
                lightningEntity.setCosmetic(true);
                lightningEntity.setPos(user.getX(), user.getY()+50, user.getZ());
                serverWorld.spawnEntity(lightningEntity);

                user.sendMessage(Text.literal("Yeah this isn't working yet, sorry.").formatted(Formatting.DARK_PURPLE), true);
                serverWorld.playSound(null, user.getBlockPos(), SoundEvents.BLOCK_END_PORTAL_SPAWN, SoundCategory.BLOCKS, 0.5f, 1.0f);
                user.getItemCooldownManager().set(this, 200);
            }
        } return super.use(world, user, hand);
    }

    @Override public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if(StyleUtil.StyleSwitchable.getStyle(stack) == 1){
			GlobalPos pos = stack.get(ModDataComponentTypes.STORED_POSITION);
            if(pos == null) { target.requestTeleportAndDismount(target.getX(), target.getY() + 1, target.getZ()); }
			else {
				target.getWorld().playSound(null, target.getBlockPos(), SoundEvents.ENTITY_WARDEN_SONIC_CHARGE, SoundCategory.BLOCKS, 1.0F, 2.0F);
				target.requestTeleportAndDismount(pos.pos().getX(), pos.pos().getY(), pos.pos().getZ());
                target.getWorld().playSound(null, pos.pos(), SoundEvents.ENTITY_WARDEN_SONIC_BOOM, SoundCategory.BLOCKS, 1.0F, 2.0F);
            }
        } else { target.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER,60,0,true,true,false)); }
        return true;
    }

    @Override public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if(world instanceof ServerWorld serverWorld && stack.get(ModDataComponentTypes.STORED_POSITION) != null && stack.get(ModDataComponentTypes.TIMER) <= serverWorld.getTime()) {
			stack.remove(ModDataComponentTypes.STORED_POSITION); stack.remove(ModDataComponentTypes.TIMER);
		} super.inventoryTick(stack, world, entity, slot, selected);
    }

    @Override public Pillars.Pillar getPillar(ItemStack stack) { return Pillars.TSANE; }
    @Override public boolean isItemBarVisible(ItemStack stack) { return stack.get(ModDataComponentTypes.TIMER) != null; }
	@Override public boolean hasGlint(ItemStack stack){ return stack.get(ModDataComponentTypes.TIMER) != null; }
	@Override public int getItemBarStep(ItemStack stack) {
        if (ModDataComponentTypes.TIMER != null) { return Math.round(13.0F - ((float)
			(stack.get(ModDataComponentTypes.TIMER) - (int) stack.getHolder().getWorld().getTime()) / 2) * 13.0F / 100); }
		return super.getItemBarStep(stack);
    }

	@Override public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
		World world = stack.getHolder().getWorld();
		if (StyleUtil.StyleSwitchable.getStyle(stack) == 1) {
			GlobalPos pos = stack.get(ModDataComponentTypes.STORED_POSITION);
			if(pos != null) {
				tooltip.add(Text.translatable("trevorssentinels.tooltip.bound_position", pos.pos().getX(), pos.pos().getY(), pos.pos().getZ())
					.formatted(Formatting.LIGHT_PURPLE));
				tooltip.add(Text.literal("Attacks will also teleport").formatted(Formatting.ITALIC, Formatting.GRAY));
				tooltip.add(Text.literal("the target to this location.").formatted(Formatting.ITALIC, Formatting.GRAY));
				if (stack.get(ModDataComponentTypes.TIMER) != null){ tooltip.add(Text.translatable("trevorssentinels.tooltip.rift_timer",
					(stack.get(ModDataComponentTypes.TIMER) - world.getTime()) / 2));}
			} else {
				tooltip.add(Text.literal("Hold shift and right click").formatted(Formatting.GRAY));
				tooltip.add(Text.literal("to bind to a location!").formatted(Formatting.GRAY));
			}
		} else {
			tooltip.add(Text.literal("Right click to summon allies! (WIP)").formatted(Formatting.LIGHT_PURPLE));
			tooltip.add(Text.empty().append(Text.literal("Attacks will apply ").formatted(Formatting.ITALIC, Formatting.GRAY))
				.append(Text.literal("Crystallized").formatted(Formatting.LIGHT_PURPLE))
				.append(Text.literal(".")).formatted(Formatting.ITALIC, Formatting.GRAY));
		}
		super.appendTooltip(stack, context, tooltip, type);
	}
}
