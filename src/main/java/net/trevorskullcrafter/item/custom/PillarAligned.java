package net.trevorskullcrafter.item.custom;

import net.minecraft.item.ItemStack;
import net.trevorskullcrafter.util.Pillars;

public interface PillarAligned {
	default Pillars.Pillar getPillar(ItemStack stack){ return Pillars.BYNDI; }
}
