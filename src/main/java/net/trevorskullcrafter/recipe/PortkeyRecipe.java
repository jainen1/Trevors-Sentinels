package net.trevorskullcrafter.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.*;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.World;
import net.trevorskullcrafter.item.ModDataComponentTypes;
import net.trevorskullcrafter.item.TSItems;

public class PortkeyRecipe extends SpecialCraftingRecipe {
    private static final Ingredient PORTKEY = Ingredient.ofItems(TSItems.Magic.UNHOLY_CORE);

    public PortkeyRecipe(CraftingRecipeCategory craftingRecipeCategory) { super(craftingRecipeCategory); }

    public boolean matches(CraftingRecipeInput craftingRecipeInput, World world) {
        boolean portkeyPresent = false;
        int filledSlots = 0;

        for(int slot = 0; slot < craftingRecipeInput.getSize(); ++slot) {
            ItemStack itemStack = craftingRecipeInput.getStackInSlot(slot);
            if (!itemStack.isEmpty()) { filledSlots++; if (PORTKEY.test(itemStack)) { portkeyPresent = true; } }
        }
        return portkeyPresent && filledSlots == 2;
    }

    public ItemStack craft(CraftingRecipeInput craftingRecipeInput, RegistryWrapper.WrapperLookup wrapperLookup) {
        ItemStack portkey = ItemStack.EMPTY;
        ItemStack other = ItemStack.EMPTY;

        for(int slot = 0; slot < craftingRecipeInput.getSize(); ++slot) {
            ItemStack itemStack = craftingRecipeInput.getStackInSlot(slot);
            if (!itemStack.isEmpty()) {
                if (PORTKEY.test(itemStack)) { portkey = itemStack; }
                else if(itemStack.getItem() != TSItems.Magic.PALE_BERRIES) { other = itemStack; }
            }
        }

        ItemStack itemStack2 = other.copyWithCount(1);
        itemStack2.set(ModDataComponentTypes.PORTKEY, portkey.get(ModDataComponentTypes.PORTKEY));
        return itemStack2;
    }

    public boolean fits(int width, int height) { return width * height >= 2; }

    //public ItemStack getResult(RegistryWrapper.WrapperLookup registriesLookup) { return new ItemStack(Items.FIREWORK_ROCKET); }

    public RecipeSerializer<?> getSerializer() { return ModRecipes.PORTKEY_SERIALIZER; }
}