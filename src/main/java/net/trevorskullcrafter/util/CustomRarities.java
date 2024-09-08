package net.trevorskullcrafter.util;

import net.minecraft.text.Text;

import static net.trevorskullcrafter.TrevorsSentinels.MOD_ID;

public class CustomRarities {
	public record CustomRarity(String name) {
		public Text getName(){ return Text.translatable("tooltip.rarity." + MOD_ID + "." + name); }
	}

	public static final CustomRarity COMMON = new CustomRarity("common");
	public static final CustomRarity UNCOMMON = new CustomRarity("uncommon");
	public static final CustomRarity RARE = new CustomRarity("rare");
	public static final CustomRarity EPIC = new CustomRarity("epic");
	public static final CustomRarity LEGENDARY = new CustomRarity("legendary");
}
