package net.trevorskullcrafter.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.trevorskullcrafter.block.TSBlocks;

import java.util.concurrent.CompletableFuture;

import static net.trevorskullcrafter.TrevorsSentinels.MOD_ID;

public class BlockTagGenerator extends FabricTagProvider.BlockTagProvider {
	public BlockTagGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) { super(output, registriesFuture); }

    public static final TagKey<Block> LASER_REFLECTIVE = TagKey.of(RegistryKeys.BLOCK, Identifier.of(MOD_ID, "laser_reflective"));
    public static final TagKey<Block> LASER_PROJECTILE_PASS = TagKey.of(RegistryKeys.BLOCK, Identifier.of(MOD_ID, "laser_projectile_pass"));
    public static final TagKey<Block> SOLID_PROJECTILE_PASS = TagKey.of(RegistryKeys.BLOCK, Identifier.of(MOD_ID, "solid_projectile_pass"));

    public static final TagKey<Block> FLESHY_BLOCKS = TagKey.of(RegistryKeys.BLOCK, Identifier.of(MOD_ID, "fleshy_blocks"));

    public static final TagKey<Block> CERULII_LOGS = TagKey.of(RegistryKeys.BLOCK, Identifier.of(MOD_ID, "cerulii_logs"));
    public static final TagKey<Block> CHARRED_LOGS = TagKey.of(RegistryKeys.BLOCK, Identifier.of(MOD_ID, "charred_logs"));
    public static final TagKey<Block> MIDAS_LOGS = TagKey.of(RegistryKeys.BLOCK, Identifier.of(MOD_ID, "midas_logs"));
    public static final TagKey<Block> VIRIDESCENT_LOGS = TagKey.of(RegistryKeys.BLOCK, Identifier.of(MOD_ID, "viridescent_logs"));
    public static final TagKey<Block> YGGDRASIL_LOGS = TagKey.of(RegistryKeys.BLOCK, Identifier.of(MOD_ID, "yggdrasil_logs"));

    public static final TagKey<Block> REQUIRES_LEVEL_2 = TagKey.of(RegistryKeys.BLOCK, Identifier.of(MOD_ID, "requires_level_2"));
    public static final TagKey<Block> REQUIRES_LEVEL_3 = TagKey.of(RegistryKeys.BLOCK, Identifier.of(MOD_ID, "requires_level_3"));

	@Override protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
		getOrCreateTagBuilder(LASER_REFLECTIVE)
				.add(Blocks.IRON_BLOCK, Blocks.ICE, Blocks.PACKED_ICE, Blocks.BLUE_ICE, Blocks.BELL)
				.add(TSBlocks.Tech.STARSTEEL_BLOCK, TSBlocks.Tech.CHISELED_STARSTEEL_BLOCK, TSBlocks.Tech.STAINLESS_STARSTEEL_BLOCK, TSBlocks.Tech.STARSTEEL_LAMP, TSBlocks.Tech.STARSTEEL_FAN,
						TSBlocks.Tech.BATTERY, TSBlocks.Tech.FUSEBOX, TSBlocks.Tech.STARSTEEL_LADDER, TSBlocks.Tech.BIG_RED_BUTTON, TSBlocks.Tech.BIG_RED_PLATE, TSBlocks.Tech.RECONSTRUCTION_TABLE)
				.add(TSBlocks.Magic.UNHOLY_BLOCK, TSBlocks.Magic.IMPERIAL_ALLOY_BLOCK, TSBlocks.Magic.ROSE_GOLD_BLOCK, TSBlocks.Magic.COPPER_IRON_BLOCK);
		getOrCreateTagBuilder(SOLID_PROJECTILE_PASS)
				.addOptionalTag(BlockTags.LEAVES)
				.add(Blocks.IRON_BARS)
				.add(Blocks.CHAIN);
		getOrCreateTagBuilder(LASER_PROJECTILE_PASS)
				.addOptionalTag(Identifier.of("c", "glass_blocks"))
				.addTag(SOLID_PROJECTILE_PASS);

		getOrCreateTagBuilder(FLESHY_BLOCKS).add(TSBlocks.Magic.FLESH_BLOCK, TSBlocks.Magic.FLESH_VEINS, TSBlocks.Magic.FLESHY_EYE);

		getOrCreateTagBuilder(CERULII_LOGS).add(TSBlocks.Trees.CERULII_LOG, TSBlocks.Trees.CERULII_WOOD, TSBlocks.Trees.STRIPPED_CERULII_LOG, TSBlocks.Trees.STRIPPED_CERULII_WOOD);
		getOrCreateTagBuilder(CHARRED_LOGS).add(TSBlocks.Trees.CHARRED_LOG, TSBlocks.Trees.CHARRED_WOOD, TSBlocks.Trees.STRIPPED_CHARRED_LOG, TSBlocks.Trees.STRIPPED_CHARRED_WOOD);
		getOrCreateTagBuilder(MIDAS_LOGS).add(TSBlocks.Trees.MIDAS_LOG, TSBlocks.Trees.MIDAS_WOOD, TSBlocks.Trees.STRIPPED_MIDAS_LOG, TSBlocks.Trees.STRIPPED_MIDAS_WOOD);
		getOrCreateTagBuilder(VIRIDESCENT_LOGS).add(TSBlocks.Trees.VIRIDIAN_LOG, TSBlocks.Trees.VIRIDIAN_WOOD, TSBlocks.Trees.STRIPPED_VIRIDIAN_LOG, TSBlocks.Trees.STRIPPED_VIRIDIAN_WOOD);
		getOrCreateTagBuilder(YGGDRASIL_LOGS).add(TSBlocks.Trees.PALE_LOG, TSBlocks.Trees.PALE_WOOD, TSBlocks.Trees.STRIPPED_PALE_LOG, TSBlocks.Trees.STRIPPED_PALE_WOOD);

		getOrCreateTagBuilder(BlockTags.WOODEN_FENCES).add(TSBlocks.Trees.PALE_FENCE, TSBlocks.Trees.CHARRED_FENCE, TSBlocks.Trees.MIDAS_FENCE, TSBlocks.Trees.VIRIDIAN_FENCE, TSBlocks.Trees.CERULII_FENCE);
		getOrCreateTagBuilder(BlockTags.FENCE_GATES).add(TSBlocks.Trees.PALE_FENCE_GATE, TSBlocks.Trees.CHARRED_FENCE_GATE, TSBlocks.Trees.MIDAS_FENCE_GATE, TSBlocks.Trees.VIRIDIAN_FENCE_GATE, TSBlocks.Trees.CERULII_FENCE_GATE);
		getOrCreateTagBuilder(BlockTags.LEAVES).add(TSBlocks.Trees.PALE_LEAVES, TSBlocks.Trees.MIDAS_LEAVES, TSBlocks.Trees.VIRIDIAN_LEAVES, TSBlocks.Trees.CERULII_LEAVES);

		getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN);

		getOrCreateTagBuilder(BlockTags.FLOWERS).add(TSBlocks.Plants.SKULLWEED, TSBlocks.Plants.TRANQUIL_ROSE, TSBlocks.Magic.FLESHY_PUSTULE);
		getOrCreateTagBuilder(BlockTags.TALL_FLOWERS);
		getOrCreateTagBuilder(BlockTags.CLIMBABLE).add(TSBlocks.Magic.FLESH_VEINS);

		getOrCreateTagBuilder(REQUIRES_LEVEL_2).add(TSBlocks.Tech.NUCLEAR_ORE, TSBlocks.Tech.NUCLEAR_BLOCK);
		getOrCreateTagBuilder(REQUIRES_LEVEL_3).add(TSBlocks.Tech.CAUTION_HARD_LIGHT_BARRIER);

	}
}
