package net.trevorskullcrafter.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.*;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import net.trevorskullcrafter.block.TSBlocks;
import net.trevorskullcrafter.item.TSItems;
import net.trevorskullcrafter.recipe.PortkeyRecipe;
import net.trevorskullcrafter.recipe.PurifyPortkeyRecipe;

import java.util.concurrent.CompletableFuture;

import static net.trevorskullcrafter.TrevorsSentinels.MOD_ID;

public class ModRecipeGenerator extends FabricRecipeProvider {
	public ModRecipeGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) { super(output, registriesFuture); }

	@Override public void generate(RecipeExporter exporter) {
		offerReversibleCompactingRecipes(exporter, RecipeCategory.MISC, TSItems.Tech.NUCLEAR_INGOT, RecipeCategory.BUILDING_BLOCKS, TSBlocks.Tech.NUCLEAR_BLOCK);
		ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, TSItems.Tech.PLASMA_CELL)
				.pattern("#  ").pattern(" $ ").pattern("  #")
				.input('#', TSItems.Tech.STARSTEEL_INGOT).input('$', TSItems.Tech.NUCLEAR_INGOT)
				.criterion(FabricRecipeProvider.hasItem(TSItems.Tech.NUCLEAR_INGOT), FabricRecipeProvider.conditionsFromItem(TSItems.Tech.NUCLEAR_INGOT))
				.criterion(FabricRecipeProvider.hasItem(TSItems.Tech.STARSTEEL_INGOT), FabricRecipeProvider.conditionsFromItem(TSItems.Tech.STARSTEEL_INGOT))
				.offerTo(exporter, Identifier.of(MOD_ID, "energy_cell_from_torbernite"));
		ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, TSBlocks.Tech.WAX_INFUSED_COPPER_BLOCK, 4)
				.pattern(" # ").pattern("#$#").pattern(" # ")
				.input('#', Items.HONEYCOMB).input('$', Blocks.COPPER_BLOCK)
				.criterion(FabricRecipeProvider.hasItem(Items.HONEYCOMB), FabricRecipeProvider.conditionsFromItem(Items.HONEYCOMB))
				.criterion(FabricRecipeProvider.hasItem(Blocks.COPPER_BLOCK), FabricRecipeProvider.conditionsFromItem(Blocks.COPPER_BLOCK))
				.offerTo(exporter, Identifier.of(MOD_ID, "wax_infused_copper_block"));

		ComplexRecipeJsonBuilder.create(PortkeyRecipe::new).offerTo(exporter, "trevorssentinels:portkey");
		ComplexRecipeJsonBuilder.create(PurifyPortkeyRecipe::new).offerTo(exporter, "trevorssentinels:purify_portkey");

		ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, TSItems.Magic.UNHOLY_INGOT)
				.input(TSItems.Beta.TRANQUIL_DUST, 4).input(TSItems.Beta.ASH, 4).input(Items.IRON_INGOT)
				.criterion(FabricRecipeProvider.hasItem(TSItems.Beta.TRANQUIL_DUST), FabricRecipeProvider.conditionsFromItem(TSItems.Beta.TRANQUIL_DUST))
				.criterion(FabricRecipeProvider.hasItem(TSItems.Beta.ASH), FabricRecipeProvider.conditionsFromItem(TSItems.Beta.ASH))
				.criterion(FabricRecipeProvider.hasItem(Items.IRON_INGOT), FabricRecipeProvider.conditionsFromItem(Items.IRON_INGOT))
				.offerTo(exporter, Identifier.of(MOD_ID, "darksteel_ingot"));
		SmithingTransformRecipeJsonBuilder.create(Ingredient.empty(), Ingredient.ofItems(Items.COPPER_INGOT), Ingredient.ofItems(Items.GOLD_INGOT), RecipeCategory.MISC, TSItems.Magic.ROSE_GOLD_INGOT)
				.criterion(FabricRecipeProvider.hasItem(Items.COPPER_INGOT), FabricRecipeProvider.conditionsFromItem(Items.COPPER_INGOT))
				.criterion(FabricRecipeProvider.hasItem(Items.GOLD_INGOT), FabricRecipeProvider.conditionsFromItem(Items.GOLD_INGOT))
				.offerTo(exporter, Identifier.of(MOD_ID, "copper_gold"));
		SmithingTransformRecipeJsonBuilder.create(Ingredient.empty(), Ingredient.ofItems(Items.GOLD_INGOT), Ingredient.ofItems(Items.COPPER_INGOT), RecipeCategory.MISC, TSItems.Magic.ROSE_GOLD_INGOT)
				.criterion(FabricRecipeProvider.hasItem(Items.GOLD_INGOT), FabricRecipeProvider.conditionsFromItem(Items.GOLD_INGOT))
				.criterion(FabricRecipeProvider.hasItem(Items.COPPER_INGOT), FabricRecipeProvider.conditionsFromItem(Items.COPPER_INGOT))
				.offerTo(exporter, Identifier.of(MOD_ID, "gold_copper"));

		ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, TSItems.Magic.UNHOLY_CORE)
				.pattern("#").pattern("$").pattern("#")
				.input('#', Items.AMETHYST_SHARD).input('$', Items.ENDER_EYE)
				.criterion(FabricRecipeProvider.hasItem(Items.AMETHYST_SHARD), FabricRecipeProvider.conditionsFromItem(Items.AMETHYST_SHARD))
				.criterion(FabricRecipeProvider.hasItem(Items.ENDER_EYE), FabricRecipeProvider.conditionsFromItem(Items.ENDER_EYE))
				.offerTo(exporter, Identifier.of(MOD_ID, "amethyst_core"));
	}
}
