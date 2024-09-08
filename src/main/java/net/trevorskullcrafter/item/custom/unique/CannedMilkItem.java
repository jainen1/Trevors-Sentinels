package net.trevorskullcrafter.item.custom.unique;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.trevorskullcrafter.item.custom.CannedItem;

import static net.trevorskullcrafter.TrevorsSentinels.MILK_CURES_POTION_EFFECTS;

public class CannedMilkItem extends CannedItem {
	public CannedMilkItem(Item.Settings settings) { super(settings); }

	@Override public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
		if(world.getGameRules().getBoolean(MILK_CURES_POTION_EFFECTS)){ user.clearStatusEffects(); }
		return super.finishUsing(stack, world, user);
	}
}
