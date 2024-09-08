package net.trevorskullcrafter.datagen.lang;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.OrePlacedFeatures;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.placementmodifier.*;
import net.trevorskullcrafter.TrevorsSentinels;
import net.trevorskullcrafter.datagen.ConfiguredFeatureGenerator;

import java.util.List;

public class PlacedFeatureGenerator {
    public static final RegistryKey<PlacedFeature> TRANQUIL_ROSE_KEY = registerKey("tranquil_rose");
    public static final RegistryKey<PlacedFeature> TRANQUIL_ROSE_PATCH_KEY = registerKey("tranquil_rose_patch");

    public static final RegistryKey<PlacedFeature> END_NUCLEAR_ORE_KEY = registerKey("end_nuclear_ore");

    public static void bootstrap(Registerable<PlacedFeature> context){
        RegistryEntryLookup<ConfiguredFeature<?, ?>> registryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        register(context, END_NUCLEAR_ORE_KEY, registryEntryLookup.getOrThrow(ConfiguredFeatureGenerator.END_NUCLEAR_ORE_KEY),
                OrePlacedFeatures.modifiersWithCount(3, HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.getTop())));

        register(context, TRANQUIL_ROSE_KEY, registryEntryLookup.getOrThrow(ConfiguredFeatureGenerator.TRANQUIL_ROSE_KEY),
                List.of(BlockFilterPlacementModifier.of(BlockPredicate.IS_AIR)));

        register(context, TRANQUIL_ROSE_PATCH_KEY, registryEntryLookup.getOrThrow(ConfiguredFeatureGenerator.TRANQUIL_ROSE_PATCH),
                List.of(RarityFilterPlacementModifier.of(8),
                        SquarePlacementModifier.of(),
                        PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
                        BiomePlacementModifier.of()));
    }

    private static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(TrevorsSentinels.MOD_ID, name));
    }

    private static void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> config, List<PlacementModifier> modifiers){
        context.register(key, new PlacedFeature(config, List.copyOf(modifiers)));
    }
}
