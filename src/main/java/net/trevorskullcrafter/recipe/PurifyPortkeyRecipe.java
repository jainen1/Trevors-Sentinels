package net.trevorskullcrafter.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import net.trevorskullcrafter.item.ModDataComponentTypes;
import net.trevorskullcrafter.item.PortkeyComponent;
import net.trevorskullcrafter.item.TSItems;

public class PurifyPortkeyRecipe extends SpecialCraftingRecipe {
    private static final Ingredient BERRIES = Ingredient.ofItems(TSItems.Magic.PALE_BERRIES);

    public PurifyPortkeyRecipe(CraftingRecipeCategory craftingRecipeCategory) { super(craftingRecipeCategory); }

    public boolean matches(CraftingRecipeInput craftingRecipeInput, World world) {
        boolean portkeyPresent = false;
        boolean berriesPresent = false;
        int filledSlots = 0;

        for(int slot = 0; slot < craftingRecipeInput.getSize(); ++slot) {
            ItemStack itemStack = craftingRecipeInput.getStackInSlot(slot);
            if (!itemStack.isEmpty()) {
                filledSlots++;
                if (BERRIES.test(itemStack)) { berriesPresent = true; }
                else if(itemStack.get(ModDataComponentTypes.PORTKEY) != null){ portkeyPresent = true; }
            }
        }
        return portkeyPresent && berriesPresent && filledSlots == 2;
    }

    public ItemStack craft(CraftingRecipeInput craftingRecipeInput, RegistryWrapper.WrapperLookup wrapperLookup) {
        ItemStack portkey = ItemStack.EMPTY;

        for(int slot = 0; slot < craftingRecipeInput.getSize(); ++slot) {
            ItemStack itemStack = craftingRecipeInput.getStackInSlot(slot);
            if (!itemStack.isEmpty() && itemStack.get(ModDataComponentTypes.PORTKEY) != null) { portkey = itemStack.copy(); }
        }

        if(portkey.getItem() == TSItems.Magic.UNHOLY_CORE){ portkey.set(ModDataComponentTypes.PORTKEY, PortkeyComponent.DEFAULT); }
        else { portkey.remove(ModDataComponentTypes.PORTKEY); }
        return portkey;
    }

    @Override public DefaultedList<ItemStack> getRemainder(CraftingRecipeInput input) {
        var result = DefaultedList.ofSize(input.getSize(), ItemStack.EMPTY);

        for(int i = 0; i < result.size(); ++i) {
            var stack = input.getStackInSlot(i);
            var item = stack.getItem();
            if(item.getRecipeRemainder() != null) { result.set(i, stack.getRecipeRemainder()); }
            if(stack.get(ModDataComponentTypes.PORTKEY) != null){ result.add(new ItemStack(TSItems.Magic.UNHOLY_CORE, 1)); }
        }
        return result;
    }

    public boolean fits(int width, int height) { return width * height >= 2; }
    public RecipeSerializer<?> getSerializer() { return ModRecipes.PURIFY_PORTKEY_SERIALIZER; }
}