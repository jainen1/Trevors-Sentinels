package net.trevorskullcrafter.mixin;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.ClickType;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import net.trevorskullcrafter.datagen.ItemTagGenerator;
import net.trevorskullcrafter.item.CooldownComponent;
import net.trevorskullcrafter.item.ModDataComponentTypes;
import net.trevorskullcrafter.item.custom.FuelableItem;
import net.trevorskullcrafter.util.TextUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(Item.class)
public abstract class ItemMixin {
    @Shadow public abstract String getTranslationKey(ItemStack stack);

	@Inject(at = @At("HEAD"), method = "appendTooltip")
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type, CallbackInfo ci) {
		FoodComponent foodComponent = stack.get(DataComponentTypes.FOOD);
        if(foodComponent != null && foodComponent.effects() != null){ for(FoodComponent.StatusEffectEntry effect : foodComponent.effects()){
            tooltip.add(Text.empty().append(TextUtil.potionText(effect.effect(), false)).append(Text.literal(" ["+String.format("%.0f",effect.probability()*100)+"%]")
                    .formatted(Formatting.YELLOW)));
        }}

        String tooltipText = TextUtil.translationExistsFor("tooltip." + getTranslationKey(stack));
        if(tooltipText != null) {
            Formatting[] formattings = new Formatting[]{Formatting.ITALIC, Formatting.GRAY};
            if (tooltipText.contains(" ") && tooltipText.length() >= 24) { //if tooltip is very long, split it in half
                int halfPoint = tooltipText.indexOf(" ", (int) (tooltipText.length() / 2.0)) + 1;
                tooltip.add(Text.literal(tooltipText.substring(0, halfPoint)).formatted(formattings));
                tooltip.add(Text.literal(tooltipText.substring(halfPoint)).formatted(formattings));
            } else { tooltip.add(Text.literal(tooltipText).formatted(formattings)); }
        }
    }

	@Inject(at = @At("RETURN"), method = "onClicked", cancellable = true)
	public void onClicked(ItemStack thisStack, ItemStack otherStack, Slot thisSlot, ClickType clickType, PlayerEntity player, StackReference cursorStackReference, CallbackInfoReturnable<Boolean> cir) {
		if (thisStack.getItem() instanceof FuelableItem fuelableItem && clickType == ClickType.RIGHT) {
			cir.setReturnValue(fuelableItem.onFuelableClicked(thisStack, otherStack, thisSlot, clickType, player, cursorStackReference));
		}
	}

	@Inject(at = @At("RETURN"), method = "onStackClicked", cancellable = true)
	public void onStackClicked(ItemStack thisStack, Slot otherSlot, ClickType clickType, PlayerEntity player, CallbackInfoReturnable<Boolean> cir) {
		if (thisStack.getItem() instanceof FuelableItem fuelableItem && clickType == ClickType.RIGHT) {
			cir.setReturnValue(fuelableItem.onFuelableStackClicked(thisStack, otherSlot, clickType, player));
		}
	}

    @Inject(at = @At("HEAD"), method = "getItemBarColor", cancellable = true)
    public void getItemBarColor(ItemStack stack, CallbackInfoReturnable<Integer> cir) {
        TextColor styleColor = stack.getName().getStyle().getColor();
        if(stack.isIn(ItemTagGenerator.ITEM_BAR_COLOR_OVERRIDE) && styleColor != null) { cir.setReturnValue(styleColor.getRgb()); }
    }

    @Inject(method = "inventoryTick", at = @At("HEAD"))
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected, CallbackInfo ci) {
        CooldownComponent cooldown = stack.get(ModDataComponentTypes.COOLDOWN);
        if(cooldown != null){
            if(cooldown.step() > 0){ stack.set(ModDataComponentTypes.COOLDOWN, new CooldownComponent(cooldown.length(), cooldown.step()-1)); }
            else { stack.remove(ModDataComponentTypes.COOLDOWN); }
        }
    }
}
