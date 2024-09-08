package net.trevorskullcrafter.mixin;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MilkBucketItem;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import net.trevorskullcrafter.TrevorsSentinels;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

import static net.trevorskullcrafter.TrevorsSentinels.MILK_CURES_POTION_EFFECTS;

@Mixin(MilkBucketItem.class)
public abstract class MilkBucketMixin extends ItemMixin {
    @WrapWithCondition(method = "finishUsing", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;clearStatusEffects()Z"))
    private boolean milkCuresPotionEffects(LivingEntity instance) { return instance.getWorld().getGameRules().getBoolean(MILK_CURES_POTION_EFFECTS); }

	//@Inject(method = "finishUsing", at = @At(value = "RETURN", target = "Lnet/minecraft/item/ItemStack;decrement(I)V"))
	//private void finishUsing(ItemStack stack, World world, LivingEntity user, CallbackInfoReturnable<ItemStack> cir){
	//	if(stack.getComponents().get(DataComponentTypes.FOOD) != null) { user.eat; }
	//}

	//@Override public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type, CallbackInfo ci) {
	//	World world = stack.getHolder().getWorld();
	//	boolean cures = world != null && world.getGameRules().getBoolean(MILK_CURES_POTION_EFFECTS);
	//	if(cures){ tooltip.add(Text.translatable("tooltip.trevorssentinels:milkCuresPotionEffects").formatted(Formatting.RED)); }
	//	TrevorsSentinels.LOGGER.info("milk cures potion effects: {}", cures);
	//	super.appendTooltip(stack, context, tooltip, type, ci);
	//}
}
