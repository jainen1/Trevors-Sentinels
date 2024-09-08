package net.trevorskullcrafter.item.custom.unique;

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

import java.util.List;
import java.util.random.RandomGenerator;

public class SkylarBladeItem extends SwordItem {
    public SkylarBladeItem(ToolMaterial toolMaterial, Settings settings) { super(toolMaterial, settings); }

    @Override public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand){
        ItemStack itemStack = user.getStackInHand(hand);
        if(world instanceof ServerWorld serverWorld && hand == Hand.MAIN_HAND){
            if(!user.isSneaking()){
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 5, 255,false,false,false));
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS,5,3,false,false,false));
                serverWorld.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.BLOCK_ANVIL_LAND, SoundCategory.BLOCKS, 2.0F, RandomGenerator.getDefault().nextFloat());
                user.sendMessage(Text.literal("Parry!").formatted(Formatting.GRAY),true);
                user.getItemCooldownManager().set(this, 100);
            }else {
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH,200,1,false,false,false));
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED,200,2,false,false,false));
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.HUNGER,200,2,false,false,false));
                serverWorld.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_WARDEN_SONIC_CHARGE, SoundCategory.BLOCKS, 2.0F, 5.0F);
                user.sendMessage(Text.literal("Energized!").formatted(Formatting.GREEN),true);
                user.getItemCooldownManager().set(this,200);
            } return TypedActionResult.success(itemStack);
        } return super.use(world, user, hand);
    }

	@Override public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
		tooltip.add(Text.literal("Right click to parry!").formatted(Formatting.GRAY));
		tooltip.add(Text.literal("Shift + right click to become energized!").formatted(Formatting.GREEN));
		tooltip.add(Text.literal("Justice").formatted(Formatting.ITALIC, Formatting.YELLOW));
		super.appendTooltip(stack, context, tooltip, type);
	}
}
