package net.trevorskullcrafter.item.custom;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.trevorskullcrafter.util.CustomRarities;
import net.trevorskullcrafter.util.ICustomRarity;

import java.util.List;

public class GachaScrapItem extends Item implements ICustomRarity {
	CustomRarities.CustomRarity customRarity;
	public GachaScrapItem(CustomRarities.CustomRarity customRarity, Settings settings) { super(settings); this.customRarity = customRarity; }

	@Override public CustomRarities.CustomRarity getCustomRarity() { return customRarity; }

	@Override public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
		tooltip.add(Text.empty().append(Text.literal("-- ").formatted(Formatting.GRAY)).append(getCustomRarity().getName())
				.append(Text.literal(" Scrap --").formatted(Formatting.GRAY)));
		super.appendTooltip(stack, context, tooltip, type);
	}
}
