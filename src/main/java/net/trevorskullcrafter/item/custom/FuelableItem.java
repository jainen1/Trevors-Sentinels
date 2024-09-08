package net.trevorskullcrafter.item.custom;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Arm;
import net.minecraft.util.ClickType;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.trevorskullcrafter.item.ModDataComponentTypes;
import net.trevorskullcrafter.item.TSItems;
import net.trevorskullcrafter.util.TextUtil;

import java.util.Objects;
import java.util.Optional;

public interface FuelableItem {
	default boolean addFuel(ItemStack stack, ItemStack otherStack, Entity entity) {
		int space = getMaxFuel(stack) - getFuel(stack);
		boolean isFuelItem = otherStack.isOf(getFuelItem());
		if (space > 0 && isFuelItem) {
			int amount = Math.min(space, otherStack.getCount());
			if (amount > 0) {
				stack.set(ModDataComponentTypes.FUEL, getFuel(stack)+amount);
				playFuelSound(entity);
				otherStack.decrement(amount);
				return true;
			}
		} return false;
	}

	default Optional<ItemStack> removeFuel(ItemStack stack, Entity entity) {
		if (getFuel(stack) == 0) { return Optional.empty(); }
		else {
			int amount = Math.min(getFuel(stack), Objects.requireNonNullElse(getFuelItem().getComponents().get(DataComponentTypes.MAX_STACK_SIZE), 64));
			if(amount > 0) {
				stack.set(ModDataComponentTypes.FUEL, getFuel(stack)-amount);
				playFuelSound(entity);
			} return Optional.of(new ItemStack(getFuelItem(), amount));
		}
	}

	default boolean onFuelableStackClicked(ItemStack stack, Slot slot, ClickType clickType, PlayerEntity player) {
		ItemStack otherStack = slot.getStack();
		if (otherStack.isEmpty()) { removeFuel(stack, player).ifPresent((removedStack) -> addFuel(stack, slot.insertStack(removedStack), player)); }
		else { addFuel(stack, otherStack, player); }
		return true;
	}

	default boolean onFuelableClicked(ItemStack stack, ItemStack otherStack, Slot slot, ClickType clickType, PlayerEntity player, StackReference cursorStackReference) {
		if (slot.canTakePartial(player)) {
			if (otherStack.isEmpty()) { cursorStackReference.set(removeFuel(stack, player).orElse(ItemStack.EMPTY)); }
			else { addFuel(stack, otherStack, player); }
			return true;
		} return false;
	}

	default Text fuelText(ItemStack stack){
		int ammo = getFuel(stack); int maxAmmo = getMaxFuel(stack);
		return Text.empty().append(Text.literal(String.valueOf(ammo)).formatted(TextUtil.formattingFromQuotient(ammo, maxAmmo))).append(Text.literal(" / " + maxAmmo +" ⚡ ").formatted(Formatting.GRAY));
	}

	default void sendFuelMessage(PlayerEntity user, ItemStack stack){
		ItemStack leftHandedStack = user.getStackInHand(user.getMainArm() == Arm.RIGHT? Hand.OFF_HAND : Hand.MAIN_HAND);
		ItemStack rightHandedStack = user.getStackInHand(user.getMainArm() == Arm.RIGHT? Hand.MAIN_HAND : Hand.OFF_HAND);
		if(leftHandedStack.getItem() instanceof FuelableItem leftFuelable && rightHandedStack.getItem() instanceof FuelableItem rightFuelable){
			user.sendMessage(Text.empty().append(leftFuelable.fuelText(leftHandedStack)).append("   |   ").append(rightFuelable.fuelText(rightHandedStack)), true);
		}
		else { user.sendMessage(fuelText(stack), true); }
	}

	default Text shortFuelText(ItemStack stack){ return Text.empty()
			.append(((FuelableItem) stack.getItem()).fuelText(stack)).append(Text.literal(" (...)").formatted(Formatting.ITALIC, Formatting.GOLD)); }
	default Text longFuelText(ItemStack stack){ return Text.empty()
			.append(((FuelableItem) stack.getItem()).fuelText(stack)).append((Text.literal(" (Charge with ")).append(getFuelItem().getName()).append(Text.literal(")")).formatted(Formatting.ITALIC, Formatting.GOLD)); }

	default Item getFuelItem(){ return TSItems.Tech.PLASMA_CELL; }
	default int getFuel(ItemStack stack) { return TSItems.getComponentValue(stack, ModDataComponentTypes.FUEL, 0); }
	default int getMaxFuel(ItemStack stack) { return TSItems.getComponentValue(stack, ModDataComponentTypes.MAX_FUEL, 6); }
	default void playFuelSound(Entity entity) { entity.playSound(SoundEvents.BLOCK_CHAIN_PLACE, 1f, 2f + entity.getWorld().getRandom().nextFloat() * 0.4F); }
}
