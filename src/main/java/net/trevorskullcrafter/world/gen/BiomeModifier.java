package net.trevorskullcrafter.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.gen.GenerationStep;
import net.trevorskullcrafter.datagen.lang.PlacedFeatureGenerator;

public class BiomeModifier {
    public static void registerModifications(){
        BiomeModifications.addFeature(BiomeSelectors.foundInTheEnd(), GenerationStep.Feature.UNDERGROUND_ORES, PlacedFeatureGenerator.END_NUCLEAR_ORE_KEY);

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.VEGETAL_DECORATION, PlacedFeatureGenerator.TRANQUIL_ROSE_PATCH_KEY);
    }
}
