package net.trevorskullcrafter;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;
import net.trevorskullcrafter.datagen.*;
import net.trevorskullcrafter.datagen.lang.EN_US_Generator;
import net.trevorskullcrafter.datagen.lang.PlacedFeatureGenerator;

public class TrevorsSentinelsDataGenerator implements DataGeneratorEntrypoint {
	@Override public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(ModLootTableGenerator::new);
		pack.addProvider(ModModelProvider::new);
		pack.addProvider(ModRecipeGenerator::new);
		pack.addProvider(ModWorldGenerator::new);
		pack.addProvider(AdvancementGenerator::new);
		pack.addProvider(ItemTagGenerator::new);
		pack.addProvider(BlockTagGenerator::new);

		pack.addProvider(EN_US_Generator::new);
	}

	@Override public void buildRegistry(RegistryBuilder registryBuilder) {
		registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, ConfiguredFeatureGenerator::bootstrap);
		registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, PlacedFeatureGenerator::bootstrap);
	}
}
