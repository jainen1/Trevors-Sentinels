package net.trevorskullcrafter.recipe;

import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialRecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.trevorskullcrafter.TrevorsSentinels;

import static net.trevorskullcrafter.TrevorsSentinels.MOD_ID;

public class ModRecipes {
    public static final RecipeSerializer<PortkeyRecipe> PORTKEY_SERIALIZER =
            RecipeSerializer.register("trevorssentinels:crafting_special_portkey", new SpecialRecipeSerializer<>(PortkeyRecipe::new));
    public static final RecipeSerializer<PurifyPortkeyRecipe> PURIFY_PORTKEY_SERIALIZER =
            RecipeSerializer.register("trevorssentinels:crafting_special_purify_portkey", new SpecialRecipeSerializer<>(PurifyPortkeyRecipe::new));


    public static void registerRecipes(){
        TrevorsSentinels.LOGGER.info("Cooking up some fun... ("+ MOD_ID + ")");

        //Registry.registerModifications(Registries.RECIPE_SERIALIZER, new Identifier(trevorssentinels.MOD_ID, ForgeRecipe.Serializer.ID),
        //        ForgeRecipe.Serializer.INSTANCE);
        //Registry.registerModifications(Registries.RECIPE_TYPE, new Identifier(trevorssentinels.MOD_ID, ForgeRecipe.Type.ID),
        //        ForgeRecipe.Type.INSTANCE);
        Registry.register(Registries.RECIPE_SERIALIZER, Identifier.of(MOD_ID, "portkey"), PORTKEY_SERIALIZER);
        Registry.register(Registries.RECIPE_SERIALIZER, Identifier.of(MOD_ID, "purify_portkey"), PURIFY_PORTKEY_SERIALIZER);
    }
}
