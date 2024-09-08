package net.trevorskullcrafter.item.custom.unique;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.item.FireworkRocketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.trevorskullcrafter.item.ModDataComponentTypes;
import net.trevorskullcrafter.item.custom.FuelableItem;
import net.trevorskullcrafter.util.TextUtil;

import java.util.List;

public class NuclearRocketItem extends FireworkRocketItem implements FuelableItem {
    public NuclearRocketItem(Item.Settings settings) { super(settings); }

    @Override public ActionResult useOnBlock(ItemUsageContext context) {
		ItemStack stack = context.getStack();
		PlayerEntity user = context.getPlayer();
		if(getFuel(stack) > 0){
			if (user != null) {
				if (!user.getAbilities().creativeMode) { stack.set(ModDataComponentTypes.FUEL, getFuel(stack)-1); }
				user.sendMessage(fuelText(stack), true);
				user.incrementStat(Stats.USED.getOrCreateStat(this));
				user.addVelocity(0, 1, 0);
				return ActionResult.success(true);
			}
		} return ActionResult.FAIL;
	}

    @Override public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		ItemStack stack = user.getStackInHand(hand);
		if(getFuel(stack) > 0){
			if (!user.getAbilities().creativeMode) { stack.set(ModDataComponentTypes.FUEL, getFuel(stack)-1); }
			user.sendMessage(fuelText(stack), true);
			user.incrementStat(Stats.USED.getOrCreateStat(this));
			if (user.isFallFlying()) {
				FireworkRocketEntity fireworkRocketEntity = new FireworkRocketEntity(world, stack, user);
				world.spawnEntity(fireworkRocketEntity);
			} else { user.addVelocity(0, 3, 0); }
			user.getItemCooldownManager().set(this, 20);
			return TypedActionResult.success(user.getStackInHand(hand), true);
		}
		return TypedActionResult.pass(user.getStackInHand(hand));
	}

	@Override public int getItemBarStep(ItemStack stack) { return Math.round(getFuel(stack) * 13.0F / getMaxFuel(stack)); }
	@Override public int getItemBarColor(ItemStack stack) { return TextUtil.NUCLEAR3.getRGB(); }
	@Override public boolean isItemBarVisible(ItemStack stack) { return getFuel(stack) != getMaxFuel(stack); }
}
