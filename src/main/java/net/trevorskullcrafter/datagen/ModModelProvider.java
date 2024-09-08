package net.trevorskullcrafter.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.data.client.*;
import net.minecraft.item.Item;
import net.trevorskullcrafter.block.TSBlocks;
import net.trevorskullcrafter.item.TSItems;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) { super(output); }

    @Override public void generateBlockStateModels(BlockStateModelGenerator blockStateGenerator) {
        blockStateGenerator.registerLog(TSBlocks.Tech.CHISELED_STARSTEEL_BLOCK);

        blockStateGenerator.registerSimpleCubeAll(TSBlocks.Tech.NUCLEAR_BLOCK);
        blockStateGenerator.registerSimpleCubeAll(TSBlocks.Tech.NUCLEAR_ORE);
        blockStateGenerator.registerSimpleCubeAll(TSBlocks.Tech.STARSTEEL_REPULSOR);
        blockStateGenerator.registerSimpleCubeAll(TSBlocks.Tech.BLUE_AGILITY_BLOCK);
        blockStateGenerator.registerSimpleCubeAll(TSBlocks.Tech.ORANGE_AGILITY_BLOCK);
        blockStateGenerator.registerSimpleCubeAll(TSBlocks.Tech.STAINLESS_STARSTEEL_BLOCK);
        //blockStateGenerator.registerSimpleCubeAll(TSBlocks.Tech.RUSTED_STEEL_BLOCK);
        blockStateGenerator.registerSimpleCubeAll(TSBlocks.Tech.STARSTEEL_FAN);
        blockStateGenerator.registerSimpleCubeAll(TSBlocks.Tech.LIGHT_CHAMBER_BLOCK);
        blockStateGenerator.registerSimpleCubeAll(TSBlocks.Tech.DARK_CHAMBER_BLOCK);

        blockStateGenerator.registerSimpleCubeAll(TSBlocks.Magic.IMPERIAL_ALLOY_BLOCK);
        blockStateGenerator.registerSimpleCubeAll(TSBlocks.Magic.COPPER_IRON_BLOCK);
        blockStateGenerator.registerSimpleCubeAll(TSBlocks.Magic.ROSE_GOLD_BLOCK);
        blockStateGenerator.registerSimpleCubeAll(TSBlocks.Tech.WAX_INFUSED_COPPER_BLOCK);
        blockStateGenerator.registerSimpleCubeAll(TSBlocks.Magic.UNHOLY_BLOCK);

        blockStateGenerator.registerSimpleCubeAll(TSBlocks.Magic.CHISELED_END_STONE_BRICKS);
        blockStateGenerator.registerSimpleCubeAll(TSBlocks.Tech.CHISELED_SCRAP_METAL_BLOCK);
        blockStateGenerator.registerSimpleCubeAll(TSBlocks.Magic.CHISELED_SNOWSTONE);
        blockStateGenerator.registerSimpleCubeAll(TSBlocks.Tech.SCRAP_METAL_BLOCK);

        BlockStateModelGenerator.BlockTexturePool steelPool = blockStateGenerator.registerCubeAllModelTexturePool(TSBlocks.Tech.STARSTEEL_BLOCK);
        steelPool.family(TSBlocks.Tech.STARSTEEL_FAMILY);

        BlockStateModelGenerator.BlockTexturePool smooth_snowstonePool = blockStateGenerator.registerCubeAllModelTexturePool(TSBlocks.Magic.SMOOTH_SNOWSTONE);
        smooth_snowstonePool.stairs(TSBlocks.Magic.SMOOTH_SNOWSTONE_STAIRS);
        smooth_snowstonePool.slab(TSBlocks.Magic.SMOOTH_SNOWSTONE_SLAB);
        smooth_snowstonePool.wall(TSBlocks.Magic.SMOOTH_SNOWSTONE_WALL);

        registerWoodType(blockStateGenerator, TSBlocks.Trees.VIRIDIAN_LOG, TSBlocks.Trees.VIRIDIAN_WOOD, TSBlocks.Trees.STRIPPED_VIRIDIAN_LOG, TSBlocks.Trees.STRIPPED_VIRIDIAN_WOOD,
                TSBlocks.Trees.VIRIDIAN_LEAVES, TSBlocks.Trees.VIRIDIAN_SAPLING, TSBlocks.Trees.POTTED_VIRIDIAN_SAPLING);
        BlockStateModelGenerator.BlockTexturePool viridescentPool = blockStateGenerator.registerCubeAllModelTexturePool(TSBlocks.Trees.VIRIDIAN_PLANKS);
        viridescentPool.family(TSBlocks.Trees.VIRIDIAN_FAMILY);

        registerWoodType(blockStateGenerator, TSBlocks.Trees.CERULII_LOG, TSBlocks.Trees.CERULII_WOOD, TSBlocks.Trees.STRIPPED_CERULII_LOG, TSBlocks.Trees.STRIPPED_CERULII_WOOD,
                TSBlocks.Trees.CERULII_LEAVES, TSBlocks.Trees.CERULII_SAPLING, TSBlocks.Trees.POTTED_CERULII_SAPLING);
        BlockStateModelGenerator.BlockTexturePool ceruliiPool = blockStateGenerator.registerCubeAllModelTexturePool(TSBlocks.Trees.CERULII_PLANKS);
        ceruliiPool.family(TSBlocks.Trees.CERULII_FAMILY);

        registerWoodType(blockStateGenerator, TSBlocks.Trees.CHARRED_LOG, TSBlocks.Trees.CHARRED_WOOD, TSBlocks.Trees.STRIPPED_CHARRED_LOG, TSBlocks.Trees.STRIPPED_CHARRED_WOOD,
                null, null, null);
        BlockStateModelGenerator.BlockTexturePool charredPool = blockStateGenerator.registerCubeAllModelTexturePool(TSBlocks.Trees.CHARRED_PLANKS);
        charredPool.family(TSBlocks.Trees.CHARRED_FAMILY);

        registerWoodType(blockStateGenerator, TSBlocks.Trees.MIDAS_LOG, TSBlocks.Trees.MIDAS_WOOD, TSBlocks.Trees.STRIPPED_MIDAS_LOG, TSBlocks.Trees.STRIPPED_MIDAS_WOOD,
                TSBlocks.Trees.MIDAS_LEAVES, TSBlocks.Trees.MIDAS_SAPLING, TSBlocks.Trees.POTTED_MIDAS_SAPLING);
        BlockStateModelGenerator.BlockTexturePool midasPool = blockStateGenerator.registerCubeAllModelTexturePool(TSBlocks.Trees.MIDAS_PLANKS);
        midasPool.family(TSBlocks.Trees.MIDAS_FAMILY);

        registerWoodType(blockStateGenerator, TSBlocks.Trees.PALE_LOG, TSBlocks.Trees.PALE_WOOD, TSBlocks.Trees.STRIPPED_PALE_LOG, TSBlocks.Trees.STRIPPED_PALE_WOOD,
                null, TSBlocks.Trees.PALE_SAPLING, TSBlocks.Trees.POTTED_PALE_SAPLING);
        BlockStateModelGenerator.BlockTexturePool yggdrasilPool = blockStateGenerator.registerCubeAllModelTexturePool(TSBlocks.Trees.PALE_PLANKS);
        yggdrasilPool.family(TSBlocks.Trees.PALE_FAMILY);

        blockStateGenerator.registerFlowerPotPlant(TSBlocks.Magic.FLESHY_PUSTULE, TSBlocks.Magic.POTTED_FLESHY_PUSTULE, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateGenerator.registerFlowerPotPlant(TSBlocks.Plants.SKULLWEED, TSBlocks.Plants.POTTED_SKULLWEED, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateGenerator.registerFlowerPotPlant(TSBlocks.Plants.TRANQUIL_ROSE, TSBlocks.Plants.POTTED_TRANQUIL_ROSE, BlockStateModelGenerator.TintType.NOT_TINTED);

        blockStateGenerator.registerSingleton(TSBlocks.Tech.PHASEPLATE, TexturedModel.LEAVES);
    }

    @Override public void generateItemModels(ItemModelGenerator itemModelGenerator) {
		//itemModelGenerator.registerArmor()

		registerItems(itemModelGenerator, Models.GENERATED, TSItems.Tech.SCRAP_METAL_HELMET, TSItems.Tech.SCRAP_METAL_CHESTPLATE, TSItems.Tech.SCRAP_METAL_LEGGINGS, TSItems.Tech.SCRAP_METAL_BOOTS);
        registerItems(itemModelGenerator, Models.HANDHELD, TSItems.Tech.SCRAP_METAL_SWORD, TSItems.Tech.SCRAP_METAL_KNIFE, TSItems.Tech.SCRAP_METAL_DRILL,
			TSItems.Tech.SCRAP_METAL_CHAINSAW, TSItems.Tech.SCRAP_METAL_SHOVEL, TSItems.Tech.SCRAP_METAL_HOE);

        registerItems(itemModelGenerator, Models.GENERATED, TSItems.Tech.STARSTEEL_HELMET, TSItems.Tech.STARSTEEL_CHESTPLATE, TSItems.Tech.STARSTEEL_LEGGINGS, TSItems.Tech.STARSTEEL_BOOTS);
        registerItems(itemModelGenerator, Models.HANDHELD, TSItems.Tech.STARSTEEL_SWORD, TSItems.Tech.STARSTEEL_KNIFE, TSItems.Tech.STARSTEEL_DRILL,
			TSItems.Tech.STARSTEEL_AXE, TSItems.Tech.STARSTEEL_SHOVEL, TSItems.Tech.STARSTEEL_HOE);

        registerItems(itemModelGenerator, Models.GENERATED, TSItems.Magic.ROSE_GOLD_HELMET, TSItems.Magic.ROSE_GOLD_CHESTPLATE, TSItems.Magic.ROSE_GOLD_LEGGINGS, TSItems.Magic.ROSE_GOLD_BOOTS);
        registerItems(itemModelGenerator, Models.HANDHELD, TSItems.Magic.ROSE_GOLD_SWORD, TSItems.Magic.ROSE_GOLD_DAGGER, TSItems.Magic.ROSE_GOLD_PICKAXE,
			TSItems.Magic.ROSE_GOLD_BATTLEAXE, TSItems.Magic.ROSE_GOLD_SHOVEL, TSItems.Magic.ROSE_GOLD_HOE);

        registerItems(itemModelGenerator, Models.GENERATED, TSItems.Tech.INDUSTRIAL_HELMET, TSItems.Tech.INDUSTRIAL_HARNESS, TSItems.Tech.INDUSTRIAL_PANTS, TSItems.Tech.INDUSTRIAL_BOOTS);
        registerItems(itemModelGenerator, Models.HANDHELD, TSItems.Tech.INDUSTRIAL_CROWBAR, TSItems.Tech.INDUSTRIAL_KNIFE, TSItems.Tech.INDUSTRIAL_DRILL,
			TSItems.Tech.INDUSTRIAL_AXE, TSItems.Tech.INDUSTRIAL_SHOVEL, TSItems.Tech.INDUSTRIAL_HOE);

        registerItems(itemModelGenerator, Models.GENERATED, TSItems.Magic.IMPERIAL_HELMET, TSItems.Magic.IMPERIAL_CHESTPLATE, TSItems.Magic.IMPERIAL_LEGGINGS, TSItems.Magic.IMPERIAL_BOOTS);
        registerItems(itemModelGenerator, Models.HANDHELD, TSItems.Magic.IMPERIAL_SWORD, TSItems.Magic.IMPERIAL_GLADIUS, TSItems.Magic.IMPERIAL_PICKAXE, TSItems.Magic.IMPERIAL_BATTLEAXE,
			TSItems.Magic.IMPERIAL_SHOVEL, TSItems.Magic.IMPERIAL_HOE);

        registerItems(itemModelGenerator, Models.GENERATED, TSItems.Magic.UNHOLY_HELMET, TSItems.Magic.UNHOLY_CHESTPLATE, TSItems.Magic.UNHOLY_LEGGINGS,
			TSItems.Magic.UNHOLY_BOOTS);
        registerItems(itemModelGenerator, Models.HANDHELD, TSItems.Magic.UNHOLY_SWORD, TSItems.Magic.UNHOLY_DAGGER, TSItems.Magic.UNHOLY_PICKAXE,
			TSItems.Magic.UNHOLY_BATTLEAXE, TSItems.Magic.UNHOLY_SHOVEL, TSItems.Magic.UNHOLY_HOE);

        registerItems(itemModelGenerator, Models.GENERATED, TSItems.Tech.NUCLEAR_HELMET, TSItems.Tech.NUCLEAR_CHESTPLATE, TSItems.Tech.NUCLEAR_LEGGINGS, TSItems.Tech.NUCLEAR_BOOTS);
        registerItems(itemModelGenerator, Models.HANDHELD, TSItems.Tech.NUCLEAR_SWORD, TSItems.Tech.NUCLEAR_VIBROKNIFE, TSItems.Tech.NUCLEAR_DRILL, TSItems.Tech.NUCLEAR_AXE,
			TSItems.Tech.NUCLEAR_SHOVEL, TSItems.Tech.NUCLEAR_HOE);

        registerItems(itemModelGenerator, Models.GENERATED, TSItems.Tech.NANOTECH_HELMET, TSItems.Tech.NANOTECH_CHESTPLATE, TSItems.Tech.NANOTECH_LEGGINGS, TSItems.Tech.NANOTECH_BOOTS);
        registerItems(itemModelGenerator, Models.HANDHELD, TSItems.Tech.NANOTECH_SWORD, TSItems.Tech.NANOTECH_VIBROKNIFE, TSItems.Tech.NANOTECH_DRILL, TSItems.Tech.NANOTECH_AXE,
			TSItems.Tech.NANOTECH_SHOVEL, TSItems.Tech.NANOTECH_HOE);

        registerItems(itemModelGenerator, Models.GENERATED, TSItems.Magic.MACABRE_HELMET, TSItems.Magic.MACABRE_HARNESS, TSItems.Magic.MACABRE_LOINCLOTH, TSItems.Magic.MACABRE_SANDALS);

        registerItems(itemModelGenerator, Models.GENERATED, TSItems.Beta.BLACKSMITHS_WELDING_MASK, TSItems.Beta.MAD_SCIENTISTS_LAB_COAT, TSItems.Tech.ZENITHIUM_CHESTPLATE,
			TSItems.Tech.ZENITHIUM_LEGGINGS, TSItems.Tech.ZENITHIUM_BOOTS);
        registerItems(itemModelGenerator, Models.HANDHELD, TSItems.Tech.ZENITHIUM_SWORD, TSItems.Tech.ZENITHIUM_DAGGER, TSItems.Tech.ZENITHIUM_PICKAXE,
			TSItems.Tech.ZENITHIUM_AXE, TSItems.Tech.ZENITHIUM_SHOVEL, TSItems.Tech.ZENITHIUM_HOE);
		//TSItems.Magic.MASTER_SWORD

        //food
        registerItems(itemModelGenerator, Models.GENERATED, TSItems.Beta.ASH, TSItems.Beta.PEARFRUIT, TSItems.Beta.MIDAS_FRUIT, TSItems.Beta.BANANA, TSItems.Beta.BANANA_BREAD,
			TSItems.Beta.BROWNIE, TSItems.Beta.RICE_SEEDS, TSItems.Beta.RICE_CAKE, TSItems.Beta.TORTILLA, TSItems.Beta.BURRITO, TSItems.Beta.CHORUS_COBBLER, TSItems.Tech.COLA_CYAN, TSItems.Tech.COLA_GREEN,
			TSItems.Tech.COLA_ORANGE, TSItems.Tech.MILK_CAN, TSItems.Tech.BEETROOT_SOUP_CAN, TSItems.Tech.MUSHROOM_STEW_CAN, TSItems.Tech.RABBIT_STEW_CAN, TSItems.Beta.SANDFISH, TSItems.Beta.COOKED_SANDFISH,
			TSItems.Beta.SIDEWINDER, TSItems.Beta.SMOKED_FISH, TSItems.Magic.DUBIOUS_BACON, TSItems.Beta.FRIED_EGG, TSItems.Beta.SUSHI_ROLL);

        //progression materials
        registerItems(itemModelGenerator, Models.GENERATED, TSItems.Tech.SCRAP_METAL_SHARD, TSItems.Magic.ROSE_GOLD_INGOT, TSItems.Magic.IMPERIAL_ALLOY_INGOT,
			TSItems.Tech.NANOTECH_CAPSULE, TSItems.Tech.STARSTEEL_INGOT, TSItems.Beta.REDSTONE_CRYSTAL, TSItems.Magic.UNHOLY_INGOT, TSItems.Tech.NUCLEAR_INGOT, TSItems.Tech.ZENITHIUM_CLUSTER);

		registerItems(itemModelGenerator, Models.GENERATED, TSItems.Magic.ENCHANTED_LEAF);

        //misc
        registerItems(itemModelGenerator, Models.GENERATED, TSItems.Tech.EMPTY_CAN, TSItems.Magic.EYE_OF_RUIN, TSItems.Tech.PLASMA_CELL,
                TSItems.Tech.NUCLEAR_ROCKET, TSItems.Tech.MUSIC_DISC_ASSASSINATION_UPLOAD, TSItems.Tech.MUSIC_DISC_LAPSE_IN_JUDGEMENT,
                TSItems.Tech.MUSIC_DISC_ODE_TO_TRANQUILITY, TSItems.Tech.MUSIC_DISC_RECITAL);

        registerItems(itemModelGenerator, Models.HANDHELD, TSItems.Tech.HARD_LIGHT_PROJECTOR, TSItems.Tech.CAUTION_HARD_LIGHT_PROJECTOR, TSItems.Tech.SENTINEL_HARD_LIGHT_PROJECTOR);

        itemModelGenerator.register(TSItems.Beta.VENDOR_TOKEN, TSItems.Beta.LEGENDARY_TOKEN, Models.GENERATED);
        itemModelGenerator.register(TSItems.Magic.UNHOLY_CORE, Models.GENERATED);

        //effect items
        registerItems(itemModelGenerator, Models.GENERATED, TSItems.Magic.CYBERNETIC_STOMACH, TSItems.Magic.ONE_PENCE);

        itemModelGenerator.register(TSItems.Magic.PALE_HANGING_SIGN, Models.GENERATED);
    }

    public void registerWoodType(BlockStateModelGenerator generator, Block LOG, Block WOOD, Block STRIPPED_LOG, Block STRIPPED_WOOD, Block LEAVES, Block SAPLING, Block POTTED_SAPLING){
        if (LOG != null && WOOD != null) generator.registerLog(LOG).log(LOG).wood(WOOD);
        if (STRIPPED_LOG != null && STRIPPED_WOOD != null) generator.registerLog(STRIPPED_LOG).log(STRIPPED_LOG).wood(STRIPPED_WOOD);
        if (SAPLING != null && POTTED_SAPLING != null) generator.registerFlowerPotPlant(SAPLING, POTTED_SAPLING, BlockStateModelGenerator.TintType.NOT_TINTED);
        if(LEAVES != null) { generator.registerSimpleCubeAll(LEAVES); }
    }

    public void registerItems(ItemModelGenerator itemModelGenerator, Model model, Item... items){
        for (Item item:items) { itemModelGenerator.register(item, model); }
    }
}
