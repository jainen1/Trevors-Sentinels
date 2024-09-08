package net.trevorskullcrafter.util;

public interface ICustomRarity {
	default CustomRarities.CustomRarity getCustomRarity(){
		return CustomRarities.COMMON;
	}
}
