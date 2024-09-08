package net.trevorskullcrafter.item.custom;

import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.ArrayList;
import java.util.List;

public interface ExtendedTooltipItem {
    default List<Text> shortText(ItemStack stack){ return List.of(Text.empty().append(Text.literal("SHIFT").formatted(Formatting.GOLD))
            .append(Text.translatable("tooltip.trevorssentinels.show_advanced_tooltip").formatted(Formatting.DARK_GRAY))); }
    default List<Text> longText(ItemStack stack){ return new ArrayList<>(); }
}
