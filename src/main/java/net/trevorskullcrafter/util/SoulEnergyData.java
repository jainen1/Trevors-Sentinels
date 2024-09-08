package net.trevorskullcrafter.util;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.MathHelper;

public class SoulEnergyData {
	public static int addSoulEnergy(IEntityDataSaver player, int amount){
		NbtCompound	nbt = player.getPersistentData();
		int soul_energy = MathHelper.clamp(nbt.getInt("soul_energy") + amount, 0, 10);
		nbt.putInt("soul_energy", soul_energy);
		//sync data
		return soul_energy;
	}
}
