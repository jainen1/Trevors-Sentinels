package net.trevorskullcrafter.item.custom.unique;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import net.trevorskullcrafter.item.ModDataComponentTypes;
import net.trevorskullcrafter.item.custom.FuelableItem;
import net.trevorskullcrafter.util.TextUtil;

import java.util.List;

public class PhaseCoreItem extends Item implements FuelableItem {
	int progressTicks = 0;
    public PhaseCoreItem(Item.Settings settings) { super(settings); }

	@Override public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
		if(getFuel(stack) < getMaxFuel(stack)){ progressTicks++; }
		if(progressTicks >= 200) { progressTicks -= 200; stack.set(ModDataComponentTypes.FUEL, getFuel(stack)+1); }
		super.inventoryTick(stack, world, entity, slot, selected);
	}

	@Override public int getItemBarStep(ItemStack stack) { return Math.round(getFuel(stack) * 13.0F / getMaxFuel(stack)); }
	@Override public int getItemBarColor(ItemStack stack) { return TextUtil.formattingFromQuotient(getFuel(stack), getMaxFuel(stack)).getColorValue(); }
	@Override public boolean isItemBarVisible(ItemStack stack) { return getFuel(stack) != getMaxFuel(stack); }

	@Override public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
		super.appendTooltip(stack, context, tooltip, type);
		tooltip.add(Text.literal("Fabrication in " + ((200 - progressTicks) / 20) + "s"));
	}
}
