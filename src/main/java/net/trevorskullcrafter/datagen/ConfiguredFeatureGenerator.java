package net.trevorskullcrafter.datagen;

import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.noise.DoublePerlinNoiseSampler;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.foliage.LargeOakFoliagePlacer;
import net.minecraft.world.gen.foliage.PineFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.NoiseBlockStateProvider;
import net.minecraft.world.gen.trunk.BendingTrunkPlacer;
import net.minecraft.world.gen.trunk.LargeOakTrunkPlacer;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;
import net.trevorskullcrafter.TrevorsSentinels;
import net.trevorskullcrafter.block.TSBlocks;
import net.trevorskullcrafter.datagen.lang.PlacedFeatureGenerator;

import java.util.List;
import java.util.OptionalInt;

public class ConfiguredFeatureGenerator {
    public static final RegistryKey<ConfiguredFeature<?, ?>> TRANQUIL_ROSE_KEY = registerKey("tranquil_rose");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SKULLWEED_KEY = registerKey("skullweed");
    public static final RegistryKey<ConfiguredFeature<?, ?>> TRANQUIL_ROSE_PATCH = registerKey("tranquil_rose_patch");

    public static final RegistryKey<ConfiguredFeature<?, ?>> FLESHY_PUSTULE_KEY = registerKey("fleshy_pustule");

    public static final RegistryKey<ConfiguredFeature<?, ?>> END_NUCLEAR_ORE_KEY = registerKey("end_nuclear_ore");

    public static final RegistryKey<ConfiguredFeature<?, ?>> CERULII_TREE = registerKey("cerulii");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FANCY_CERULII_TREE = registerKey("fancy_cerulii");
    public static final RegistryKey<ConfiguredFeature<?, ?>> MIDAS_TREE = registerKey("midas");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FANCY_MIDAS_TREE = registerKey("fancy_midas");
    public static final RegistryKey<ConfiguredFeature<?, ?>> VIRIDIAN_TREE = registerKey("viridian");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FANCY_VIRIDIAN_TREE = registerKey("fancy_viridian");
    public static final RegistryKey<ConfiguredFeature<?, ?>> PALE_TREE = registerKey("pale");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FANCY_PALE_TREE = registerKey("fancy_pale");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context){
        RuleTest endOreReplaceables = new BlockMatchRuleTest(Blocks.END_STONE);
        List<OreFeatureConfig.Target> endTargets = List.of(
                OreFeatureConfig.createTarget(endOreReplaceables, TSBlocks.Tech.NUCLEAR_ORE.getDefaultState())
        );

        register(context, END_NUCLEAR_ORE_KEY, Feature.ORE, new OreFeatureConfig(endTargets, 3, 0.38f));

        register(context, CERULII_TREE, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(TSBlocks.Trees.CERULII_LOG),
                new LargeOakTrunkPlacer(2, 4, 0),
                BlockStateProvider.of(TSBlocks.Trees.CERULII_LEAVES),
                new LargeOakFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(2), 2),
                new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4))).ignoreVines().build());
        register(context, FANCY_CERULII_TREE, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(TSBlocks.Trees.CERULII_LOG),
                new LargeOakTrunkPlacer(4, 6, 0),
                BlockStateProvider.of(TSBlocks.Trees.CERULII_LEAVES),
                new LargeOakFoliagePlacer(ConstantIntProvider.create(3), ConstantIntProvider.create(4), 4),
                new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(6))).ignoreVines().build());
        register(context, MIDAS_TREE, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(TSBlocks.Trees.MIDAS_LOG),
                new StraightTrunkPlacer(4, 3, 3),
                BlockStateProvider.of(TSBlocks.Trees.MIDAS_LEAVES),
                new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 4),
                new TwoLayersFeatureSize(1, 0, 1)).build());
        register(context, FANCY_MIDAS_TREE, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(TSBlocks.Trees.MIDAS_LOG),
                new BendingTrunkPlacer(4, 3, 3, 5, ConstantIntProvider.create(3)),
                BlockStateProvider.of(TSBlocks.Trees.MIDAS_LEAVES),
                new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(1), 3),
                new TwoLayersFeatureSize(1, 0, 1)).build());
        register(context, VIRIDIAN_TREE, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(TSBlocks.Trees.VIRIDIAN_LOG),
                new StraightTrunkPlacer(3, 1, 1),
                BlockStateProvider.of(TSBlocks.Trees.VIRIDIAN_LEAVES),
                new PineFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(1), ConstantIntProvider.create(2)),
                new TwoLayersFeatureSize(1, 0, 1)).dirtProvider(BlockStateProvider.of(Blocks.COARSE_DIRT)).forceDirt().build());
        register(context, FANCY_VIRIDIAN_TREE, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(TSBlocks.Trees.VIRIDIAN_LOG),
                new StraightTrunkPlacer(4, 2, 1),
                BlockStateProvider.of(TSBlocks.Trees.VIRIDIAN_LEAVES),
                new PineFoliagePlacer(ConstantIntProvider.create(3), ConstantIntProvider.create(2), ConstantIntProvider.create(4)),
                new TwoLayersFeatureSize(1, 0, 1)).dirtProvider(BlockStateProvider.of(Blocks.COARSE_DIRT)).forceDirt().build());
        register(context, PALE_TREE, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(TSBlocks.Trees.PALE_LOG),
                new StraightTrunkPlacer(4, 3, 3),
                BlockStateProvider.of(TSBlocks.Trees.PALE_LEAVES),
                new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 4),
                new TwoLayersFeatureSize(1, 0, 1)).build());
        register(context, FANCY_PALE_TREE, Feature.TREE, new TreeFeatureConfig.Builder(
                BlockStateProvider.of(TSBlocks.Trees.PALE_LOG),
                new BendingTrunkPlacer(4, 3, 3, 5, ConstantIntProvider.create(3)),
                BlockStateProvider.of(TSBlocks.Trees.PALE_LEAVES),
                new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(1), 3),
                new TwoLayersFeatureSize(1, 0, 1)).build());

        RegistryEntryLookup<PlacedFeature> registryEntryLookup = context.getRegistryLookup(RegistryKeys.PLACED_FEATURE);

        register(context, TRANQUIL_ROSE_KEY, Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(
                new NoiseBlockStateProvider(2145, new DoublePerlinNoiseSampler.NoiseParameters(0, 1), 0.020833334F,
                        List.of(TSBlocks.Plants.TRANQUIL_ROSE.getDefaultState(), TSBlocks.Plants.SKULLWEED.getDefaultState()))));

        register(context, SKULLWEED_KEY, Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(TSBlocks.Plants.SKULLWEED)));
        register(context, FLESHY_PUSTULE_KEY, Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(TSBlocks.Magic.FLESHY_PUSTULE))); //dirtProvider

        register(context, TRANQUIL_ROSE_PATCH, Feature.FLOWER, new RandomPatchFeatureConfig(14, 10, 4, registryEntryLookup.getOrThrow(PlacedFeatureGenerator.TRANQUIL_ROSE_KEY)));
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name){
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(TrevorsSentinels.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>>
    void register(Registerable<ConfiguredFeature<?, ?>> context, RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC featureconfig){
        context.register(key, new ConfiguredFeature<>(feature, featureconfig));
    }
}
