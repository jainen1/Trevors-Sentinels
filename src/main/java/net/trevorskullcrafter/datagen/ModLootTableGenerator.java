package net.trevorskullcrafter.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.TableBonusLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.trevorskullcrafter.block.TSBlocks;
import net.trevorskullcrafter.item.TSItems;

import java.util.concurrent.CompletableFuture;

public class ModLootTableGenerator extends FabricBlockLootTableProvider {
        public ModLootTableGenerator(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) { super(dataOutput, registryLookup); }

        @Override public void generate() {
        addDrop(TSBlocks.Magic.DIRT_SLAB);
        addDrop(TSBlocks.Magic.DIRT_STAIRS);
        addDrop(TSBlocks.Magic.COARSE_DIRT_SLAB);
        addDrop(TSBlocks.Magic.COARSE_DIRT_STAIRS);
        addDrop(TSBlocks.Magic.MOSS_SLAB);
        addDrop(TSBlocks.Magic.MOSS_STAIRS);
        addDrop(TSBlocks.Magic.COPPER_IRON_BLOCK);
        addDrop(TSBlocks.Magic.ROSE_GOLD_BLOCK);
        addDrop(TSBlocks.Magic.IMPERIAL_ALLOY_BLOCK);
        addDrop(TSBlocks.Tech.CHAMBER_BLOCK);
        addDrop(TSBlocks.Tech.LIGHT_CHAMBER_BLOCK);
        addDrop(TSBlocks.Tech.DARK_CHAMBER_BLOCK);
        addDrop(TSBlocks.Tech.PHASEPLATE);

        addDrop(TSBlocks.Tech.BATTERY);
        addDrop(TSBlocks.Tech.FUSEBOX);
        addDrop(TSBlocks.Tech.NUCLEAR_BLOCK);
        addDrop(TSBlocks.Tech.BIG_RED_BUTTON);
        addDrop(TSBlocks.Tech.BIG_RED_PLATE);
        addDrop(TSBlocks.Tech.STARSTEEL_REPULSOR);
        addDrop(TSBlocks.Tech.BLUE_AGILITY_BLOCK);
        addDrop(TSBlocks.Tech.CHISELED_STARSTEEL_BLOCK);
        addDrop(TSBlocks.Magic.CHISELED_END_STONE_BRICKS);
        addDrop(TSBlocks.Magic.CRACKED_END_STONE_BRICKS);
        addDrop(TSBlocks.Magic.END_STONE_BRICK_COLUMN);
        addDrop(TSBlocks.Tech.SCRAP_METAL_BLOCK);
        addDrop(TSBlocks.Tech.SCRAP_METAL_PILLAR);
        addDrop(TSBlocks.Tech.CHISELED_SCRAP_METAL_BLOCK);
        addDrop(TSBlocks.Magic.SNOWSTONE);
        addDrop(TSBlocks.Magic.SNOWSTONE_SLAB);
        addDrop(TSBlocks.Magic.SNOWSTONE_STAIRS);
        addDrop(TSBlocks.Magic.SMOOTH_SNOWSTONE);
        addDrop(TSBlocks.Magic.SMOOTH_SNOWSTONE_SLAB);
        addDrop(TSBlocks.Magic.SMOOTH_SNOWSTONE_STAIRS);
        addDrop(TSBlocks.Magic.CHISELED_SNOWSTONE);
        addDrop(TSBlocks.Magic.SNOWSTONE_WALL);
        addDrop(TSBlocks.Magic.CUT_SNOWSTONE);
        addDrop(TSBlocks.Magic.CUT_SNOWSTONE_SLAB);
        addDrop(TSBlocks.Tech.ORANGE_AGILITY_BLOCK);
        addDrop(TSBlocks.Magic.UNHOLY_BLOCK);
        addDrop(TSBlocks.Tech.STARSTEEL_BLOCK);
        addDrop(TSBlocks.Tech.STAINLESS_STARSTEEL_BLOCK);
        addDrop(TSBlocks.Tech.RUSTED_STEEL_BLOCK);
        addDrop(TSBlocks.Tech.WAX_INFUSED_COPPER_BLOCK);
        addDrop(TSBlocks.Tech.STARSTEEL_FAN);
        addDrop(TSBlocks.Tech.STARSTEEL_LADDER);
        addDrop(TSBlocks.Tech.STARSTEEL_LAMP);
        addDrop(TSBlocks.Tech.RECONSTRUCTION_TABLE);
        addDrop(TSBlocks.Plants.TRANQUIL_ROSE);
        addPottedPlantDrops(TSBlocks.Plants.POTTED_TRANQUIL_ROSE);
        addDrop(TSBlocks.Plants.SKULLWEED);
        addPottedPlantDrops(TSBlocks.Plants.POTTED_SKULLWEED);

        addDrop(TSBlocks.Tech.STARSTEEL_SIGN, TSItems.Tech.STARSTEEL_SIGN);
        addDrop(TSBlocks.Tech.STARSTEEL_WALL_SIGN, TSItems.Tech.STARSTEEL_SIGN);
        addDrop(TSBlocks.Tech.STARSTEEL_HANGING_SIGN, TSItems.Tech.STARSTEEL_HANGING_SIGN);
        addDrop(TSBlocks.Tech.STARSTEEL_WALL_HANGING_SIGN, TSItems.Tech.STARSTEEL_HANGING_SIGN);

        addDrop(TSBlocks.Tech.HOLOGRAPHIC_SIGN, TSItems.Tech.HOLOGRAPHIC_SIGN);
        addDrop(TSBlocks.Tech.HOLOGRAPHIC_WALL_SIGN, TSItems.Tech.HOLOGRAPHIC_SIGN);
		addDrop(TSBlocks.Tech.HOLOGRAPHIC_HANGING_SIGN, TSItems.Tech.HOLOGRAPHIC_HANGING_SIGN);
		addDrop(TSBlocks.Tech.HOLOGRAPHIC_WALL_HANGING_SIGN, TSItems.Tech.HOLOGRAPHIC_HANGING_SIGN);

		addDrop(TSBlocks.Tech.CAUTION_HOLOGRAPHIC_SIGN, TSItems.Tech.CAUTION_HOLOGRAPHIC_SIGN);
		addDrop(TSBlocks.Tech.CAUTION_HOLOGRAPHIC_WALL_SIGN, TSItems.Tech.CAUTION_HOLOGRAPHIC_SIGN);
		addDrop(TSBlocks.Tech.CAUTION_HOLOGRAPHIC_HANGING_SIGN, TSItems.Tech.CAUTION_HOLOGRAPHIC_SIGN);
		addDrop(TSBlocks.Tech.CAUTION_HOLOGRAPHIC_WALL_HANGING_SIGN, TSItems.Tech.CAUTION_HOLOGRAPHIC_SIGN);

		addDrop(TSBlocks.Tech.SENTINEL_HOLOGRAPHIC_SIGN, TSItems.Tech.SENTINEL_HOLOGRAPHIC_SIGN);
		addDrop(TSBlocks.Tech.SENTINEL_HOLOGRAPHIC_WALL_SIGN, TSItems.Tech.SENTINEL_HOLOGRAPHIC_SIGN);
		addDrop(TSBlocks.Tech.SENTINEL_HOLOGRAPHIC_HANGING_SIGN, TSItems.Tech.SENTINEL_HOLOGRAPHIC_SIGN);
		addDrop(TSBlocks.Tech.SENTINEL_HOLOGRAPHIC_WALL_HANGING_SIGN, TSItems.Tech.SENTINEL_HOLOGRAPHIC_SIGN);

        addDrop(TSBlocks.Magic.FLESH_BLOCK);
        addDrop(TSBlocks.Magic.FLESHY_EYE, silkDrop(TSBlocks.Magic.FLESHY_EYE, TSItems.Magic.EYE_OF_RUIN));

        addDrop(TSBlocks.Trees.VIRIDIAN_BUTTON);
        addDrop(TSBlocks.Trees.VIRIDIAN_DOOR);
        addDrop(TSBlocks.Trees.VIRIDIAN_FENCE);
        addDrop(TSBlocks.Trees.VIRIDIAN_FENCE_GATE);
        addDrop(TSBlocks.Trees.VIRIDIAN_LEAVES, leavesWithFruitDrop(TSBlocks.Trees.VIRIDIAN_LEAVES, TSItems.Beta.BANANA, TSBlocks.Trees.VIRIDIAN_SAPLING, 0.25f));
        addDrop(TSBlocks.Trees.VIRIDIAN_LOG);
        addDrop(TSBlocks.Trees.VIRIDIAN_PLANKS);
        addDrop(TSBlocks.Trees.VIRIDIAN_PRESSURE_PLATE);
        addDrop(TSBlocks.Trees.VIRIDIAN_SAPLING);
        addPottedPlantDrops(TSBlocks.Trees.POTTED_VIRIDIAN_SAPLING);
        addDrop(TSBlocks.Trees.VIRIDIAN_SIGN, TSItems.Magic.VIRIDIAN_SIGN);
        addDrop(TSBlocks.Trees.VIRIDIAN_SLAB);
        addDrop(TSBlocks.Trees.VIRIDIAN_STAIRS);
        addDrop(TSBlocks.Trees.VIRIDIAN_TRAPDOOR);
        addDrop(TSBlocks.Trees.VIRIDIAN_WALL_SIGN, TSItems.Magic.VIRIDIAN_SIGN);
        addDrop(TSBlocks.Trees.VIRIDIAN_HANGING_SIGN, TSItems.Magic.VIRIDIAN_HANGING_SIGN);
        addDrop(TSBlocks.Trees.VIRIDIAN_WALL_HANGING_SIGN, TSItems.Magic.VIRIDIAN_HANGING_SIGN);
        addDrop(TSBlocks.Trees.VIRIDIAN_WOOD);
        addDrop(TSBlocks.Trees.STRIPPED_VIRIDIAN_LOG);
        addDrop(TSBlocks.Trees.STRIPPED_VIRIDIAN_WOOD);

        addDrop(TSBlocks.Trees.CERULII_BUTTON);
        addDrop(TSBlocks.Trees.CERULII_DOOR);
        addDrop(TSBlocks.Trees.CERULII_FENCE);
        addDrop(TSBlocks.Trees.CERULII_FENCE_GATE);
        addDrop(TSBlocks.Trees.CERULII_LEAVES, leavesDrops(TSBlocks.Trees.CERULII_LEAVES, TSBlocks.Trees.CERULII_SAPLING, 0.25f));
        addDrop(TSBlocks.Trees.CERULII_LOG);
        addDrop(TSBlocks.Trees.CERULII_PLANKS);
        addDrop(TSBlocks.Trees.CERULII_PRESSURE_PLATE);
        addDrop(TSBlocks.Trees.CERULII_SAPLING);
        addPottedPlantDrops(TSBlocks.Trees.POTTED_CERULII_SAPLING);
        addDrop(TSBlocks.Trees.CERULII_SIGN, TSItems.Magic.CERULII_SIGN);
        addDrop(TSBlocks.Trees.CERULII_SLAB);
        addDrop(TSBlocks.Trees.CERULII_STAIRS);
        addDrop(TSBlocks.Trees.CERULII_TRAPDOOR);
        addDrop(TSBlocks.Trees.CERULII_WALL_SIGN, TSItems.Magic.CERULII_SIGN);
        addDrop(TSBlocks.Trees.CERULII_HANGING_SIGN, TSItems.Magic.CERULII_HANGING_SIGN);
        addDrop(TSBlocks.Trees.CERULII_WALL_HANGING_SIGN, TSItems.Magic.CERULII_HANGING_SIGN);
        addDrop(TSBlocks.Trees.CERULII_WOOD);
        addDrop(TSBlocks.Trees.STRIPPED_CERULII_LOG);
        addDrop(TSBlocks.Trees.STRIPPED_CERULII_WOOD);

        addDrop(TSBlocks.Trees.CHARRED_BUTTON);
        addDrop(TSBlocks.Trees.CHARRED_DOOR);
        addDrop(TSBlocks.Trees.CHARRED_FENCE);
        addDrop(TSBlocks.Trees.CHARRED_FENCE_GATE);
        addDrop(TSBlocks.Trees.CHARRED_LOG);
        addDrop(TSBlocks.Trees.CHARRED_PLANKS);
        addDrop(TSBlocks.Trees.CHARRED_PRESSURE_PLATE);
        addDrop(TSBlocks.Trees.CHARRED_SIGN, TSItems.Magic.CHARRED_SIGN);
        addDrop(TSBlocks.Trees.CHARRED_SLAB);
        addDrop(TSBlocks.Trees.CHARRED_STAIRS);
        addDrop(TSBlocks.Trees.CHARRED_TRAPDOOR);
        addDrop(TSBlocks.Trees.CHARRED_WALL_SIGN, TSItems.Magic.CHARRED_SIGN);
        addDrop(TSBlocks.Trees.CHARRED_HANGING_SIGN, TSItems.Magic.CHARRED_HANGING_SIGN);
        addDrop(TSBlocks.Trees.CHARRED_WALL_HANGING_SIGN, TSItems.Magic.CHARRED_HANGING_SIGN);
        addDrop(TSBlocks.Trees.CHARRED_WOOD);
        addDrop(TSBlocks.Trees.STRIPPED_CHARRED_LOG);
        addDrop(TSBlocks.Trees.STRIPPED_CHARRED_WOOD);

        addDrop(TSBlocks.Trees.MIDAS_BUTTON);
        addDrop(TSBlocks.Trees.MIDAS_DOOR);
        addDrop(TSBlocks.Trees.MIDAS_FENCE);
        addDrop(TSBlocks.Trees.MIDAS_FENCE_GATE);
        addDrop(TSBlocks.Trees.MIDAS_LEAVES, leavesWithFruitDrop(TSBlocks.Trees.MIDAS_LEAVES, TSItems.Beta.MIDAS_FRUIT, TSBlocks.Trees.MIDAS_SAPLING, 0.25f));
        addDrop(TSBlocks.Trees.MIDAS_LOG);
        addDrop(TSBlocks.Trees.MIDAS_PLANKS);
        addDrop(TSBlocks.Trees.MIDAS_PRESSURE_PLATE);
        addDrop(TSBlocks.Trees.MIDAS_SAPLING);
        addPottedPlantDrops(TSBlocks.Trees.POTTED_MIDAS_SAPLING);
        addDrop(TSBlocks.Trees.MIDAS_SIGN, TSItems.Magic.MIDAS_SIGN);
        addDrop(TSBlocks.Trees.MIDAS_SLAB);
        addDrop(TSBlocks.Trees.MIDAS_STAIRS);
        addDrop(TSBlocks.Trees.MIDAS_TRAPDOOR);
        addDrop(TSBlocks.Trees.MIDAS_WALL_SIGN, TSItems.Magic.MIDAS_SIGN);
        addDrop(TSBlocks.Trees.MIDAS_HANGING_SIGN, TSItems.Magic.MIDAS_HANGING_SIGN);
        addDrop(TSBlocks.Trees.MIDAS_WALL_HANGING_SIGN, TSItems.Magic.MIDAS_HANGING_SIGN);
        addDrop(TSBlocks.Trees.MIDAS_WOOD);
        addDrop(TSBlocks.Trees.STRIPPED_MIDAS_LOG);
        addDrop(TSBlocks.Trees.STRIPPED_MIDAS_WOOD);

        addDrop(TSBlocks.Trees.PALE_BUTTON);
        addDrop(TSBlocks.Trees.PALE_DOOR);
        addDrop(TSBlocks.Trees.PALE_FENCE);
        addDrop(TSBlocks.Trees.PALE_FENCE_GATE);
        addDrop(TSBlocks.Trees.PALE_SAPLING, leavesWithFruitDrop(TSBlocks.Trees.PALE_LEAVES, TSItems.Beta.PEARFRUIT, TSBlocks.Trees.PALE_SAPLING, 0.25f));
        addDrop(TSBlocks.Trees.PALE_LOG);
        addDrop(TSBlocks.Trees.PALE_PLANKS);
        addDrop(TSBlocks.Trees.PALE_PRESSURE_PLATE);
        addDrop(TSBlocks.Trees.PALE_SAPLING);
        addPottedPlantDrops(TSBlocks.Trees.POTTED_PALE_SAPLING);
        addDrop(TSBlocks.Trees.PALE_SIGN, TSItems.Magic.PALE_SIGN);
        addDrop(TSBlocks.Trees.PALE_SLAB);
        addDrop(TSBlocks.Trees.PALE_STAIRS);
        addDrop(TSBlocks.Trees.PALE_TRAPDOOR);
        addDrop(TSBlocks.Trees.PALE_WALL_SIGN, TSItems.Magic.PALE_SIGN);
        addDrop(TSBlocks.Trees.PALE_HANGING_SIGN, TSItems.Magic.PALE_HANGING_SIGN);
        addDrop(TSBlocks.Trees.PALE_WALL_HANGING_SIGN, TSItems.Magic.PALE_HANGING_SIGN);
        addDrop(TSBlocks.Trees.PALE_WOOD);
        addDrop(TSBlocks.Trees.STRIPPED_PALE_LOG);
        addDrop(TSBlocks.Trees.STRIPPED_PALE_WOOD);

        addDrop(TSBlocks.Tech.NUCLEAR_ORE, oreDrops(TSBlocks.Tech.NUCLEAR_ORE, TSItems.Tech.NUCLEAR_INGOT));
    }

    public LootTable.Builder silkDrop(Block silk, Item noSilk){
        return dropsWithSilkTouch(silk, applyExplosionDecay(silk, ItemEntry.builder(noSilk)));
    }

    public LootTable.Builder leavesWithFruitDrop(Block leaves, Item fruit, Block sapling, float... saplingChance) {
		RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
        return this.leavesDrops(leaves, sapling, saplingChance).pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0F))
                .conditionally(this.createWithoutShearsOrSilkTouchCondition()).with(((LeafEntry.Builder<?>)this.addSurvivesExplosionCondition(leaves, ItemEntry.builder(fruit)))
                        .conditionally(TableBonusLootCondition.builder(impl.getOrThrow(Enchantments.FORTUNE), 0.005F, 0.0055555557F, 0.00625F, 0.008333334F, 0.025F))));
    }
}
