package net.trevorskullcrafter.block;

import com.terraformersmc.terraform.sign.api.block.TerraformHangingSignBlock;
import com.terraformersmc.terraform.sign.api.block.TerraformSignBlock;
import com.terraformersmc.terraform.sign.api.block.TerraformWallHangingSignBlock;
import com.terraformersmc.terraform.sign.api.block.TerraformWallSignBlock;
import io.wispforest.owo.registration.annotations.RegistryNamespace;
import io.wispforest.owo.registration.reflect.BlockRegistryContainer;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.component.type.SuspiciousStewEffectsComponent;
import net.minecraft.data.family.BlockFamilies;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.trevorskullcrafter.TrevorsSentinels;
import net.trevorskullcrafter.block.custom.*;
import net.trevorskullcrafter.sound.ModSounds;

import java.lang.reflect.Field;
import java.util.List;

import static net.trevorskullcrafter.TrevorsSentinels.MOD_ID;

public class TSBlocks implements BlockRegistryContainer {
	@Override public void postProcessField(String namespace, Block value, String identifier, Field field) {
		if(field.isAnnotationPresent(NoBlockItem.class)) { return; }
		BlockItem.Settings settings = new BlockItem.Settings();
		if(field.isAnnotationPresent(Fireproof.class)) { settings.fireproof(); }
		Registry.register(Registries.ITEM, Identifier.of(namespace, identifier), new BlockItem(value, settings));
	}

	@RegistryNamespace("trevorssentinels") public static class Tech implements BlockRegistryContainer {
		public static final Block SCRAP_METAL_BLOCK = new Block(Block.Settings.create().pistonBehavior(PistonBehavior.NORMAL).sounds(BlockSoundGroup.CALCITE)
			.mapColor(MapColor.GRAY).strength(3.5f,3f).requiresTool());
		public static final Block CHISELED_SCRAP_METAL_BLOCK = new Block(Block.Settings.copy(Tech.SCRAP_METAL_BLOCK));
		public static final Block SCRAP_METAL_PILLAR = new PillarBlock(Block.Settings.copy(Tech.SCRAP_METAL_BLOCK));
		public static final Block SCRAP_BARREL = new BarrelBlock(Block.Settings.copy(SCRAP_METAL_BLOCK));

		public static final Block STARSTEEL_BLOCK = new Block(Block.Settings.create().strength(6f,8f)
			.mapColor(MapColor.STONE_GRAY).sounds(ModSounds.STEEL_SOUNDS).requiresTool().instrument(NoteBlockInstrument.SNARE));
		public static final Block CHISELED_STARSTEEL_BLOCK = new PillarBlock(Block.Settings.copy(STARSTEEL_BLOCK));
		public static final Block STAINLESS_STARSTEEL_BLOCK = new Block(Block.Settings.copy(STARSTEEL_BLOCK));
		public static final Block STARSTEEL_LAMP = new ToggleableLampBlock(Block.Settings.copy(STARSTEEL_BLOCK)
			.mapColor(MapColor.TERRACOTTA_YELLOW).luminance((state) -> state.get(ToggleableLampBlock.CLICKED) ? 15 : 0));
		public static final Block STARSTEEL_FAN = new Block(Block.Settings.copy(STARSTEEL_BLOCK));
		public static final Block RUSTED_STEEL_BLOCK = new Block(Block.Settings.copy(STARSTEEL_BLOCK));
		public static final Block BATTERY = new DirectionalBlock(Block.Settings.copy(STARSTEEL_BLOCK));
		public static final Block FUSEBOX = new DirectionalBlock(Block.Settings.copy(STARSTEEL_BLOCK));
		public static final Block STARSTEEL_REPULSOR = new Block(Block.Settings.copy(STARSTEEL_BLOCK).slipperiness(/*1.098*/1.2f).mapColor(MapColor.LIGHT_BLUE));
		public static final Block STARSTEEL_LADDER = new LadderBlock(Block.Settings.copy(Blocks.LADDER).nonOpaque()
			.sounds(ModSounds.STEEL_SOUNDS).strength(6f, 8f).requiresTool());

		public static final Identifier STARSTEEL_SIGN_TEXTURE = Identifier.of(MOD_ID, "entity/signs/starsteel");
		public static final Identifier STARSTEEL_HANGING_SIGN_TEXTURE = Identifier.of(MOD_ID, "entity/signs/hanging/starsteel");
		public static final Identifier STARSTEEL_HANGING_GUI_SIGN_TEXTURE = Identifier.of(MOD_ID, "textures/gui/hanging_signs/starsteel");

		@NoBlockItem public static final Block STARSTEEL_SIGN = new TerraformSignBlock(STARSTEEL_SIGN_TEXTURE, Block.Settings.copy(STARSTEEL_BLOCK).noCollision());
		@NoBlockItem public static final Block STARSTEEL_WALL_SIGN = new TerraformWallSignBlock(STARSTEEL_SIGN_TEXTURE, Block.Settings.copy(STARSTEEL_SIGN));
		@NoBlockItem public static final Block STARSTEEL_HANGING_SIGN = new TerraformHangingSignBlock(STARSTEEL_HANGING_SIGN_TEXTURE, STARSTEEL_HANGING_GUI_SIGN_TEXTURE,
			Block.Settings.copy(STARSTEEL_SIGN));
		@NoBlockItem public static final Block STARSTEEL_WALL_HANGING_SIGN = new TerraformWallHangingSignBlock(STARSTEEL_HANGING_SIGN_TEXTURE, STARSTEEL_HANGING_GUI_SIGN_TEXTURE,
			Block.Settings.copy(STARSTEEL_SIGN));

		public static final Block BIG_RED_BUTTON = new ButtonBlock(BlockSetType.IRON, 5, Block.Settings.copy(STARSTEEL_BLOCK).noCollision().mapColor(MapColor.RED));
		public static final Block BIG_RED_PLATE = new PressurePlateBlock(BlockSetType.IRON, Block.Settings.copy(STARSTEEL_BLOCK).mapColor(MapColor.RED));

		public static final BlockFamily STARSTEEL_FAMILY = BlockFamilies.register(STARSTEEL_BLOCK).chiseled(CHISELED_STARSTEEL_BLOCK)
			.polished(STAINLESS_STARSTEEL_BLOCK).cracked(RUSTED_STEEL_BLOCK).pressurePlate(BIG_RED_PLATE).button(BIG_RED_BUTTON)
			.sign(STARSTEEL_SIGN, STARSTEEL_WALL_SIGN).unlockCriterionName("has_steel").build();

		public static final Block VENDOR = new VendorBlock(Block.Settings.copy(STARSTEEL_BLOCK).mapColor(MapColor.RED).luminance((state) -> 5));
		public static final Block FANCY_COMPUTER = new DirectionalBlock(Block.Settings.copy(STARSTEEL_BLOCK).nonOpaque());

		public static final Identifier HOLOGRAPHIC_SIGN_TEXTURE = Identifier.of(MOD_ID, "entity/signs/holographic");
		public static final Identifier HOLOGRAPHIC_HANGING_SIGN_TEXTURE = Identifier.of(MOD_ID, "entity/signs/hanging/holographic");
		public static final Identifier HOLOGRAPHIC_HANGING_GUI_SIGN_TEXTURE = Identifier.of(MOD_ID, "textures/gui/hanging_signs/holographic");

		@NoBlockItem public static final Block HOLOGRAPHIC_SIGN = new TerraformSignBlock(HOLOGRAPHIC_SIGN_TEXTURE, Block.Settings.copy(STARSTEEL_BLOCK));
		@NoBlockItem public static final Block HOLOGRAPHIC_WALL_SIGN = new TerraformWallSignBlock(HOLOGRAPHIC_SIGN_TEXTURE, Block.Settings.copy(HOLOGRAPHIC_SIGN));
		@NoBlockItem public static final Block HOLOGRAPHIC_HANGING_SIGN = new TerraformHangingSignBlock(HOLOGRAPHIC_HANGING_SIGN_TEXTURE,
			HOLOGRAPHIC_HANGING_GUI_SIGN_TEXTURE, Block.Settings.copy(HOLOGRAPHIC_SIGN));
		@NoBlockItem public static final Block HOLOGRAPHIC_WALL_HANGING_SIGN = new TerraformWallHangingSignBlock(HOLOGRAPHIC_HANGING_SIGN_TEXTURE,
			HOLOGRAPHIC_HANGING_GUI_SIGN_TEXTURE, Block.Settings.copy(HOLOGRAPHIC_SIGN));

		public static final Identifier CAUTION_HOLOGRAPHIC_SIGN_TEXTURE = Identifier.of(MOD_ID, "entity/signs/caution_holographic");
		public static final Identifier CAUTION_HOLOGRAPHIC_HANGING_SIGN_TEXTURE = Identifier.of(MOD_ID, "entity/signs/hanging/caution_holographic");
		public static final Identifier CAUTION_HOLOGRAPHIC_HANGING_GUI_SIGN_TEXTURE = Identifier.of(MOD_ID, "textures/gui/hanging_signs/caution_holographic");

		@NoBlockItem public static final Block CAUTION_HOLOGRAPHIC_SIGN = new TerraformSignBlock(CAUTION_HOLOGRAPHIC_SIGN_TEXTURE, Block.Settings.copy(STARSTEEL_BLOCK));
		@NoBlockItem public static final Block CAUTION_HOLOGRAPHIC_WALL_SIGN = new TerraformWallSignBlock(CAUTION_HOLOGRAPHIC_SIGN_TEXTURE, Block.Settings.copy(CAUTION_HOLOGRAPHIC_SIGN));
		@NoBlockItem public static final Block CAUTION_HOLOGRAPHIC_HANGING_SIGN = new TerraformHangingSignBlock(CAUTION_HOLOGRAPHIC_HANGING_SIGN_TEXTURE,
			CAUTION_HOLOGRAPHIC_HANGING_GUI_SIGN_TEXTURE, Block.Settings.copy(CAUTION_HOLOGRAPHIC_SIGN));
		@NoBlockItem public static final Block CAUTION_HOLOGRAPHIC_WALL_HANGING_SIGN = new TerraformWallHangingSignBlock(CAUTION_HOLOGRAPHIC_HANGING_SIGN_TEXTURE,
			CAUTION_HOLOGRAPHIC_HANGING_GUI_SIGN_TEXTURE, Block.Settings.copy(CAUTION_HOLOGRAPHIC_SIGN));

		public static final Identifier SENTINEL_HOLOGRAPHIC_SIGN_TEXTURE = Identifier.of(MOD_ID, "entity/signs/sentinel_holographic");
		public static final Identifier SENTINEL_HOLOGRAPHIC_HANGING_SIGN_TEXTURE = Identifier.of(MOD_ID, "entity/signs/hanging/sentinel_holographic");
		public static final Identifier SENTINEL_HOLOGRAPHIC_HANGING_GUI_SIGN_TEXTURE = Identifier.of(MOD_ID, "textures/gui/hanging_signs/sentinel_holographic");

		@NoBlockItem public static final Block SENTINEL_HOLOGRAPHIC_SIGN = new TerraformSignBlock(SENTINEL_HOLOGRAPHIC_SIGN_TEXTURE, Block.Settings.copy(STARSTEEL_BLOCK));
		@NoBlockItem public static final Block SENTINEL_HOLOGRAPHIC_WALL_SIGN = new TerraformWallSignBlock(SENTINEL_HOLOGRAPHIC_SIGN_TEXTURE, Block.Settings.copy(SENTINEL_HOLOGRAPHIC_SIGN));
		@NoBlockItem public static final Block SENTINEL_HOLOGRAPHIC_HANGING_SIGN = new TerraformHangingSignBlock(SENTINEL_HOLOGRAPHIC_HANGING_SIGN_TEXTURE,
			SENTINEL_HOLOGRAPHIC_HANGING_GUI_SIGN_TEXTURE, Block.Settings.copy(SENTINEL_HOLOGRAPHIC_SIGN));
		@NoBlockItem public static final Block SENTINEL_HOLOGRAPHIC_WALL_HANGING_SIGN = new TerraformWallHangingSignBlock(SENTINEL_HOLOGRAPHIC_HANGING_SIGN_TEXTURE,
			SENTINEL_HOLOGRAPHIC_HANGING_GUI_SIGN_TEXTURE, Block.Settings.copy(SENTINEL_HOLOGRAPHIC_SIGN));

		public static final Block WAX_INFUSED_COPPER_BLOCK = new Block(Block.Settings.copy(Blocks.COPPER_BLOCK).mapColor(MapColor.ORANGE));

		public static final Block HARD_LIGHT = new HardLightBlock(Block.Settings.create().mapColor(MapColor.CYAN)
			.sounds(ModSounds.HARD_LIGHT_SOUNDS).luminance((state) -> 5).emissiveLighting(Blocks::always).noBlockBreakParticles().nonOpaque().dynamicBounds().allowsSpawning(Blocks::never)
				.blockVision(Blocks::never).pistonBehavior(PistonBehavior.IGNORE).strength(-1f, 3600000f));
		public static final Block CAUTION_HARD_LIGHT = new HardLightBlock(Block.Settings.copy(HARD_LIGHT).mapColor(MapColor.GOLD));
		public static final Block SENTINEL_HARD_LIGHT = new HardLightBlock(Block.Settings.copy(HARD_LIGHT).mapColor(MapColor.RED));
		public static final Block HARD_LIGHT_BARRIER = new TranslucentBlock(Block.Settings.copy(HARD_LIGHT));
		public static final Block CAUTION_HARD_LIGHT_BARRIER = new TranslucentBlock(Block.Settings.copy(CAUTION_HARD_LIGHT));
		public static final Block SENTINEL_HARD_LIGHT_BARRIER = new TranslucentBlock(Block.Settings.copy(SENTINEL_HARD_LIGHT)); //might need TransparentBlock instead

		public static final Block CHAMBER_BLOCK = new FallDamagelessBlock(0.0f, Block.Settings.create().mapColor(MapColor.TERRACOTTA_ORANGE)
			.sounds(BlockSoundGroup.NETHER_STEM).strength(2f, 4f));
		public static final Block DARK_CHAMBER_BLOCK = new FallDamagelessBlock(0.0f, Block.Settings.copy(CHAMBER_BLOCK).mapColor(MapColor.BLACK));
		public static final Block LIGHT_CHAMBER_BLOCK = new FallDamagelessBlock(0.0f, Block.Settings.copy(CHAMBER_BLOCK).mapColor(MapColor.WHITE));
		public static final Block BLUE_AGILITY_BLOCK = new FallDamagelessBlock(0.0f, Block.Settings.copy(CHAMBER_BLOCK).mapColor(MapColor.CYAN)
			.sounds(BlockSoundGroup.SLIME).jumpVelocityMultiplier(2.7f).slipperiness(0.989f));
		public static final Block ORANGE_AGILITY_BLOCK = new FallDamagelessBlock(0.0f, Block.Settings.copy(CHAMBER_BLOCK).mapColor(MapColor.ORANGE)
			.sounds(BlockSoundGroup.SLIME).velocityMultiplier(1.4f).slipperiness(0.75f));
		@Fireproof public static final Block RECONSTRUCTION_TABLE = new ReconstructionTableBlock(Block.Settings.create()
				.sounds(ModSounds.STEEL_SOUNDS).mapColor(MapColor.DARK_AQUA).strength(35f,1200f).nonOpaque().requiresTool());
		public static final Block MODIFICATION_TABLE = new ModificationTableBlock(Block.Settings.create()
			.sounds(ModSounds.STEEL_SOUNDS).mapColor(MapColor.DARK_AQUA).strength(35f,1200f).nonOpaque().requiresTool());

		public static final Block PHASEPLATE = new PhaseplateBlock(Block.Settings.copy(CHAMBER_BLOCK));

		@Fireproof public static final Block NUCLEAR_BLOCK = new Block(Block.Settings.create().sounds(BlockSoundGroup.AMETHYST_BLOCK).strength(20f,1200f)
			.mapColor(MapColor.EMERALD_GREEN).requiresTool());
		@Fireproof public static final Block NUCLEAR_ORE = new ExperienceDroppingBlock(UniformIntProvider.create(5, 9), Block.Settings.create().sounds(BlockSoundGroup.STONE)
			.strength(20f,1200f).mapColor(MapColor.EMERALD_GREEN).requiresTool());

		@Fireproof public static final Block NANOTECH_BLOCK = new Block(Block.Settings.create().sounds(BlockSoundGroup.NETHERITE).strength(35f, 2000f)
			.mapColor(MapColor.BLACK).requiresTool());
	}

	@RegistryNamespace("trevorssentinels") public static class Magic implements BlockRegistryContainer {
		public static final Block CHISELED_END_STONE_BRICKS = new Block(Block.Settings.copy(Blocks.END_STONE_BRICKS));
		public static final Block CRACKED_END_STONE_BRICKS = new Block(Block.Settings.copy(Blocks.END_STONE_BRICKS));
		public static final Block END_STONE_BRICK_COLUMN = new PillarBlock(Block.Settings.copy(Blocks.END_STONE_BRICKS));

		public static final Block IMPIOUS_END_STONE = new Block(Block.Settings.copy(Blocks.END_STONE).mapColor(MapColor.WHITE_GRAY));
		public static final Block IMPIOUS_END_STONE_BRICKS = new Block(Block.Settings.copy(Blocks.END_STONE_BRICKS).mapColor(MapColor.WHITE_GRAY));
		public static final Block CRACKED_IMPIOUS_END_STONE_BRICKS = new Block(Block.Settings.copy(Blocks.END_STONE_BRICKS).mapColor(MapColor.WHITE_GRAY));
		public static final Block CHISELED_IMPIOUS_END_STONE_BRICKS = new Block(Block.Settings.copy(Blocks.END_STONE_BRICKS).mapColor(MapColor.WHITE_GRAY));
		public static final Block IMPIOUS_END_STONE_BRICK_COLUMN = new Block(Block.Settings.copy(Blocks.END_STONE_BRICKS).mapColor(MapColor.WHITE_GRAY));

		public static final Block DEIFIC_END_STONE = new Block(Block.Settings.copy(Blocks.END_STONE).mapColor(MapColor.WHITE_GRAY));
		public static final Block DEIFIC_END_STONE_BRICKS = new Block(Block.Settings.copy(Blocks.END_STONE_BRICKS).mapColor(MapColor.WHITE_GRAY));
		public static final Block CRACKED_DEIFIC_END_STONE_BRICKS = new Block(Block.Settings.copy(Blocks.END_STONE_BRICKS).mapColor(MapColor.WHITE_GRAY));
		public static final Block CHISELED_DEIFIC_END_STONE_BRICKS = new Block(Block.Settings.copy(Blocks.END_STONE_BRICKS).mapColor(MapColor.WHITE_GRAY));
		public static final Block DEIFIC_END_STONE_BRICK_COLUMN = new Block(Block.Settings.copy(Blocks.END_STONE_BRICKS).mapColor(MapColor.WHITE_GRAY));

		public static final Block SNOWSTONE = new Block(Block.Settings.copy(Blocks.SANDSTONE).mapColor(MapColor.TERRACOTTA_WHITE));
		public static final Block SNOWSTONE_STAIRS = new StairsBlock(SNOWSTONE.getDefaultState(), Block.Settings.copy(Blocks.SANDSTONE_STAIRS).mapColor(MapColor.TERRACOTTA_WHITE));
		public static final Block SNOWSTONE_SLAB = new SlabBlock(Block.Settings.copy(Blocks.SANDSTONE_SLAB).mapColor(MapColor.TERRACOTTA_WHITE));
		public static final Block SNOWSTONE_WALL = new WallBlock(Block.Settings.copy(Blocks.SANDSTONE_WALL).mapColor(MapColor.TERRACOTTA_WHITE));
		public static final Block SMOOTH_SNOWSTONE = new Block(Block.Settings.copy(Blocks.SMOOTH_SANDSTONE).mapColor(MapColor.TERRACOTTA_WHITE));
		public static final Block SMOOTH_SNOWSTONE_STAIRS = new StairsBlock(SMOOTH_SNOWSTONE.getDefaultState(), Block.Settings.copy(Blocks.SMOOTH_SANDSTONE_STAIRS)
			.mapColor(MapColor.TERRACOTTA_WHITE));
		public static final Block SMOOTH_SNOWSTONE_SLAB = new SlabBlock(Block.Settings.copy(Blocks.SMOOTH_SANDSTONE_SLAB).mapColor(MapColor.TERRACOTTA_WHITE));
		public static final Block SMOOTH_SNOWSTONE_WALL = new WallBlock(Block.Settings.copy(Blocks.SANDSTONE_WALL).mapColor(MapColor.TERRACOTTA_WHITE));
		public static final Block CUT_SNOWSTONE = new Block(Block.Settings.copy(Blocks.CUT_SANDSTONE).mapColor(MapColor.TERRACOTTA_WHITE));
		public static final Block CUT_SNOWSTONE_SLAB = new SlabBlock(Block.Settings.copy(Blocks.CUT_SANDSTONE_SLAB).mapColor(MapColor.TERRACOTTA_WHITE));
		public static final Block CHISELED_SNOWSTONE = new Block(Block.Settings.copy(Blocks.CHISELED_SANDSTONE).mapColor(MapColor.TERRACOTTA_WHITE));

		public static final Block DIRT_STAIRS = new StairsBlock(Blocks.DIRT.getDefaultState(), Block.Settings.copy(Blocks.DIRT));
		public static final Block DIRT_SLAB = new SlabBlock(Block.Settings.copy(Blocks.DIRT));
		public static final Block COARSE_DIRT_STAIRS = new StairsBlock(Blocks.COARSE_DIRT.getDefaultState(), Block.Settings.copy(Blocks.COARSE_DIRT));
		public static final Block COARSE_DIRT_SLAB = new SlabBlock(Block.Settings.copy(Blocks.COARSE_DIRT));
		public static final Block MOSS_STAIRS = new StairsBlock(Blocks.MOSS_BLOCK.getDefaultState(), Block.Settings.copy(Blocks.MOSS_BLOCK));
		public static final Block MOSS_SLAB = new SlabBlock(Block.Settings.copy(Blocks.MOSS_BLOCK));
		public static final Block FLESH_BLOCK = new FleshBlock(0.5f, ModSounds.FLESH_AMBIENT,
			Block.Settings.create().slipperiness(0.7f).strength(1.5F, 6.0F).mapColor(MapColor.RED).sounds(ModSounds.FLESH_SOUNDS));
		public static final Block FLESH_VEINS = new FleshVeinBlock(Block.Settings.copy(Blocks.GLOW_LICHEN).strength(0.7F, 3.0F).luminance((state) -> 0)
			.mapColor(MapColor.RED).sounds(ModSounds.FLESH_SOUNDS));
		public static final Block FLESHY_EYE = new FleshyEyeBlock(Block.Settings.copy(FLESH_BLOCK).mapColor(MapColor.YELLOW)
			.luminance((state) -> state.get(FleshyEyeBlock.CLICKED) ? 8 : 0));
		@NoBlockItem public static final Block FLESHY_PUSTULE = new FleshyPustuleBlock(
				new SuspiciousStewEffectsComponent(List.of(new SuspiciousStewEffectsComponent.StewEffect(StatusEffects.DARKNESS, 160))),
				Block.Settings.copy(Blocks.ALLIUM).mapColor(MapColor.YELLOW).luminance((state) -> 6).sounds(ModSounds.FLESH_SOUNDS));
		@NoBlockItem public static final Block POTTED_FLESHY_PUSTULE = new FlowerPotBlock(FLESHY_PUSTULE, Block.Settings.copy(Blocks.POTTED_ALLIUM)
				.mapColor(MapColor.YELLOW).luminance((state) -> 6));

		public static final Block UNHOLY_BLOCK = new Block(Block.Settings.create().sounds(BlockSoundGroup.NETHERITE).mapColor(MapColor.TERRACOTTA_PURPLE)
			.strength(8f,12f).requiresTool());
		public static final Block TELEPORTER = new TeleporterBlock(Block.Settings.copy(UNHOLY_BLOCK));
		public static final Block IMPERIAL_ALLOY_BLOCK = new Block(Block.Settings.create().strength(6f,8f).mapColor(MapColor.YELLOW).requiresTool());
		public static final Block COPPER_IRON_BLOCK = new Block(Block.Settings.create().strength(6f,8f).mapColor(MapColor.RAW_IRON_PINK).requiresTool());
		public static final Block ROSE_GOLD_BLOCK = new Block(Block.Settings.create().strength(5f,7f).mapColor(MapColor.RAW_IRON_PINK).requiresTool());
	}

	@RegistryNamespace("trevorssentinels") public static class Plants implements BlockRegistryContainer {
		@NoBlockItem public static final Block RICE_PLANT = new RiceCropBlock(Block.Settings.copy(Blocks.WHEAT).nonOpaque());
		public static final Block RICE_BLOCK = new PillarBlock(Block.Settings.copy(Blocks.HAY_BLOCK));
		@NoBlockItem public static final Block TRANQUIL_ROSE = new FlowerBlock(StatusEffects.DARKNESS, 8, Block.Settings.copy(Blocks.ALLIUM).luminance((state) -> 4));
		@NoBlockItem public static final Block POTTED_TRANQUIL_ROSE = new FlowerPotBlock(TRANQUIL_ROSE, Block.Settings.copy(Blocks.POTTED_ALLIUM).luminance((state) -> 4));
		@NoBlockItem public static final Block SKULLWEED = new FlowerBlock(StatusEffects.HASTE, 8, Block.Settings.copy(Blocks.ALLIUM).sounds(BlockSoundGroup.NETHER_SPROUTS));
		@NoBlockItem public static final Block POTTED_SKULLWEED = new FlowerPotBlock(SKULLWEED, Block.Settings.copy(Blocks.POTTED_ALLIUM));
		@NoBlockItem public static final Block FEATHERCORN = new TallFlowerBlock(Block.Settings.copy(Blocks.ROSE_BUSH));
	}

	@RegistryNamespace("trevorssentinels") public static class Trees implements BlockRegistryContainer {
		public static final Block PALE_PLANKS = new Block(Block.Settings.copy(Blocks.WARPED_PLANKS).mapColor(MapColor.TERRACOTTA_WHITE));
		public static final Block PALE_SAPLING =new SaplingBlock(TrevorsSentinels.Registries.PALE_TREE, Block.Settings.copy(Blocks.OAK_SAPLING).mapColor(MapColor.TERRACOTTA_WHITE));
		@NoBlockItem public static final Block POTTED_PALE_SAPLING = new FlowerPotBlock(PALE_SAPLING, Block.Settings.copy(Blocks.POTTED_OAK_SAPLING).mapColor(MapColor.TERRACOTTA_WHITE));
		public static final Block PALE_LOG = new PillarBlock(Block.Settings.copy(Blocks.OAK_LOG).mapColor(MapColor.TERRACOTTA_WHITE));
		public static final Block STRIPPED_PALE_LOG = new PillarBlock(Block.Settings.copy(Blocks.STRIPPED_OAK_LOG).mapColor(MapColor.TERRACOTTA_WHITE));
		public static final Block PALE_WOOD = new PillarBlock(Block.Settings.copy(Blocks.OAK_WOOD).mapColor(MapColor.TERRACOTTA_WHITE));
		public static final Block STRIPPED_PALE_WOOD = new PillarBlock(Block.Settings.copy(Blocks.STRIPPED_OAK_WOOD).mapColor(MapColor.TERRACOTTA_WHITE));
		public static final Block PALE_LEAVES = new LeavesBlock(Block.Settings.copy(Blocks.OAK_LEAVES).mapColor(MapColor.MAGENTA));
		public static final Block PALE_SLAB = new SlabBlock(Block.Settings.copy(Blocks.WARPED_SLAB).mapColor(MapColor.TERRACOTTA_WHITE));
		public static final Block PALE_FENCE = new FenceBlock(Block.Settings.copy(Blocks.WARPED_FENCE).mapColor(MapColor.TERRACOTTA_WHITE));
		public static final Block PALE_STAIRS = new StairsBlock(PALE_PLANKS.getDefaultState(), Block.Settings.copy(Blocks.WARPED_STAIRS).mapColor(MapColor.TERRACOTTA_WHITE));
		public static final Block PALE_BUTTON = new ButtonBlock(TrevorsSentinels.Registries.PALE, 30, Block.Settings.copy(Blocks.WARPED_BUTTON).mapColor(MapColor.TERRACOTTA_WHITE));
		public static final Block PALE_PRESSURE_PLATE = new PressurePlateBlock(TrevorsSentinels.Registries.PALE, Block.Settings.copy(Blocks.WARPED_PRESSURE_PLATE).mapColor(MapColor.TERRACOTTA_WHITE));
		public static final Block PALE_DOOR = new DoorBlock(TrevorsSentinels.Registries.PALE, Block.Settings.copy(Blocks.WARPED_DOOR).mapColor(MapColor.TERRACOTTA_WHITE));
		public static final Block PALE_TRAPDOOR = new TrapdoorBlock(TrevorsSentinels.Registries.PALE, Block.Settings.copy(Blocks.WARPED_TRAPDOOR).mapColor(MapColor.TERRACOTTA_WHITE));
		public static final Block PALE_FENCE_GATE = new FenceGateBlock(TrevorsSentinels.Registries.PALE_WOOD, Block.Settings.copy(Blocks.WARPED_FENCE_GATE).mapColor(MapColor.TERRACOTTA_WHITE));

		public static final Identifier PALE_SIGN_TEXTURE = Identifier.of(MOD_ID, "entity/signs/pale");
		public static final Identifier PALE_HANGING_SIGN_TEXTURE = Identifier.of(MOD_ID, "entity/signs/hanging/pale");
		public static final Identifier PALE_HANGING_GUI_SIGN_TEXTURE = Identifier.of(MOD_ID, "textures/gui/hanging_signs/pale");

		@NoBlockItem public static final Block PALE_SIGN = new TerraformSignBlock(PALE_SIGN_TEXTURE, Block.Settings.copy(PALE_PLANKS).noCollision());
		public static final Block PALE_WALL_SIGN = new TerraformWallSignBlock(PALE_SIGN_TEXTURE, Block.Settings.copy(PALE_SIGN));
		public static final Block PALE_HANGING_SIGN = new TerraformHangingSignBlock(PALE_HANGING_SIGN_TEXTURE, PALE_HANGING_GUI_SIGN_TEXTURE, Block.Settings.copy(PALE_SIGN));
		public static final Block PALE_WALL_HANGING_SIGN = new TerraformWallHangingSignBlock(PALE_HANGING_SIGN_TEXTURE, PALE_HANGING_GUI_SIGN_TEXTURE, Block.Settings.copy(PALE_SIGN));

		public static final BlockFamily PALE_FAMILY = BlockFamilies.register(PALE_PLANKS).stairs(PALE_STAIRS).slab(PALE_SLAB).button(PALE_BUTTON)
			.pressurePlate(PALE_PRESSURE_PLATE).fence(PALE_FENCE).fenceGate(PALE_FENCE_GATE).door(PALE_DOOR).trapdoor(PALE_TRAPDOOR)
			.sign(PALE_SIGN, PALE_WALL_SIGN).unlockCriterionName("has_planks").group("wooden").build();

		public static final Block CHARRED_PLANKS = new Block(Block.Settings.copy(Blocks.OAK_PLANKS).mapColor(MapColor.GRAY));
		public static final Block CHARRED_LOG = new PillarBlock(Block.Settings.copy(Blocks.OAK_LOG).mapColor(MapColor.GRAY));
		public static final Block STRIPPED_CHARRED_LOG = new PillarBlock(Block.Settings.copy(Blocks.STRIPPED_OAK_LOG).mapColor(MapColor.GRAY));
		public static final Block CHARRED_WOOD = new PillarBlock(Block.Settings.copy(Blocks.OAK_WOOD).mapColor(MapColor.GRAY));
		public static final Block STRIPPED_CHARRED_WOOD = new PillarBlock(Block.Settings.copy(Blocks.STRIPPED_OAK_WOOD).mapColor(MapColor.GRAY));
		public static final Block CHARRED_SLAB = new SlabBlock(Block.Settings.copy(Blocks.OAK_SLAB).mapColor(MapColor.GRAY));
		public static final Block CHARRED_FENCE = new FenceBlock(Block.Settings.copy(Blocks.OAK_FENCE).mapColor(MapColor.GRAY));
		public static final Block CHARRED_STAIRS = new StairsBlock(CHARRED_PLANKS.getDefaultState(), Block.Settings.copy(Blocks.OAK_STAIRS).mapColor(MapColor.GRAY));
		public static final Block CHARRED_BUTTON = new ButtonBlock(TrevorsSentinels.Registries.CHARRED, 30, Block.Settings.copy(Blocks.OAK_BUTTON).mapColor(MapColor.GRAY));
		public static final Block CHARRED_PRESSURE_PLATE = new PressurePlateBlock(TrevorsSentinels.Registries.CHARRED, Block.Settings.copy(Blocks.OAK_PRESSURE_PLATE).mapColor(MapColor.GRAY));
		public static final Block CHARRED_DOOR = new DoorBlock(TrevorsSentinels.Registries.CHARRED, Block.Settings.copy(Blocks.OAK_DOOR).mapColor(MapColor.GRAY));
		public static final Block CHARRED_TRAPDOOR = new TrapdoorBlock(TrevorsSentinels.Registries.CHARRED, Block.Settings.copy(Blocks.OAK_TRAPDOOR).mapColor(MapColor.GRAY));
		public static final Block CHARRED_FENCE_GATE = new FenceGateBlock(TrevorsSentinels.Registries.CHARRED_WOOD, Block.Settings.copy(Blocks.OAK_FENCE_GATE).mapColor(MapColor.GRAY));

		public static final Identifier CHARRED_SIGN_TEXTURE = Identifier.of(MOD_ID, "entity/signs/charred");
		public static final Identifier CHARRED_HANGING_SIGN_TEXTURE = Identifier.of(MOD_ID, "entity/signs/hanging/charred");
		public static final Identifier CHARRED_HANGING_GUI_SIGN_TEXTURE = Identifier.of(MOD_ID, "textures/gui/hanging_signs/charred");

		@NoBlockItem public static final Block CHARRED_SIGN = new TerraformSignBlock(CHARRED_SIGN_TEXTURE, Block.Settings.copy(CHARRED_PLANKS).noCollision());
		@NoBlockItem public static final Block CHARRED_WALL_SIGN = new TerraformWallSignBlock(CHARRED_SIGN_TEXTURE, Block.Settings.copy(CHARRED_SIGN));
		@NoBlockItem public static final Block CHARRED_HANGING_SIGN = new TerraformHangingSignBlock(CHARRED_HANGING_SIGN_TEXTURE,
			CHARRED_HANGING_GUI_SIGN_TEXTURE, Block.Settings.copy(CHARRED_SIGN));
		@NoBlockItem public static final Block CHARRED_WALL_HANGING_SIGN = new TerraformWallHangingSignBlock(CHARRED_HANGING_SIGN_TEXTURE,
			CHARRED_HANGING_GUI_SIGN_TEXTURE, Block.Settings.copy(CHARRED_SIGN));

		public static final BlockFamily CHARRED_FAMILY = BlockFamilies.register(CHARRED_PLANKS).stairs(CHARRED_STAIRS).slab(CHARRED_SLAB).button(CHARRED_BUTTON)
			.pressurePlate(CHARRED_PRESSURE_PLATE).fence(CHARRED_FENCE).fenceGate(CHARRED_FENCE_GATE).door(CHARRED_DOOR).trapdoor(CHARRED_TRAPDOOR)
			.sign(CHARRED_SIGN, CHARRED_WALL_SIGN).unlockCriterionName("has_planks").group("wooden").build();

		public static final Block MIDAS_PLANKS = new Block(Block.Settings.copy(Blocks.OAK_PLANKS).mapColor(MapColor.GOLD));
		public static final Block MIDAS_SAPLING = new SaplingBlock(TrevorsSentinels.Registries.MIDAS_TREE, Block.Settings.copy(Blocks.OAK_SAPLING));
		@NoBlockItem public static final Block POTTED_MIDAS_SAPLING = new FlowerPotBlock(MIDAS_SAPLING, Block.Settings.copy(Blocks.POTTED_OAK_SAPLING));
		public static final Block MIDAS_LOG = new PillarBlock(Block.Settings.copy(Blocks.OAK_LOG).mapColor(MapColor.GOLD));
		public static final Block STRIPPED_MIDAS_LOG = new PillarBlock(Block.Settings.copy(Blocks.STRIPPED_OAK_LOG).mapColor(MapColor.GOLD));
		public static final Block MIDAS_WOOD = new PillarBlock(Block.Settings.copy(Blocks.OAK_WOOD).mapColor(MapColor.GOLD));
		public static final Block STRIPPED_MIDAS_WOOD = new PillarBlock(Block.Settings.copy(Blocks.STRIPPED_OAK_WOOD).mapColor(MapColor.GOLD));
		public static final Block MIDAS_LEAVES = new LeavesBlock(Block.Settings.copy(Blocks.OAK_LEAVES).mapColor(MapColor.EMERALD_GREEN));
		public static final Block MIDAS_SLAB = new SlabBlock(Block.Settings.copy(Blocks.OAK_SLAB).mapColor(MapColor.GOLD));
		public static final Block MIDAS_FENCE = new FenceBlock(Block.Settings.copy(Blocks.OAK_FENCE).mapColor(MapColor.GOLD));
		public static final Block MIDAS_STAIRS = new StairsBlock(MIDAS_PLANKS.getDefaultState(), Block.Settings.copy(Blocks.OAK_STAIRS).mapColor(MapColor.GOLD));
		public static final Block MIDAS_BUTTON = new ButtonBlock(TrevorsSentinels.Registries.MIDAS, 30, Block.Settings.copy(Blocks.OAK_BUTTON).mapColor(MapColor.GOLD));
		public static final Block MIDAS_PRESSURE_PLATE = new PressurePlateBlock(TrevorsSentinels.Registries.MIDAS, Block.Settings.copy(Blocks.OAK_PRESSURE_PLATE).mapColor(MapColor.GOLD));
		public static final Block MIDAS_DOOR = new DoorBlock(TrevorsSentinels.Registries.MIDAS, Block.Settings.copy(Blocks.OAK_DOOR).mapColor(MapColor.GOLD));
		public static final Block MIDAS_TRAPDOOR = new TrapdoorBlock(TrevorsSentinels.Registries.MIDAS, Block.Settings.copy(Blocks.OAK_TRAPDOOR).mapColor(MapColor.GOLD));
		public static final Block MIDAS_FENCE_GATE = new FenceGateBlock(TrevorsSentinels.Registries.MIDAS_WOOD, Block.Settings.copy(Blocks.OAK_FENCE_GATE).mapColor(MapColor.GOLD));

		public static final Identifier MIDAS_SIGN_TEXTURE = Identifier.of(MOD_ID, "entity/signs/midas");
		public static final Identifier MIDAS_HANGING_SIGN_TEXTURE = Identifier.of(MOD_ID, "entity/signs/hanging/midas");
		public static final Identifier MIDAS_HANGING_GUI_SIGN_TEXTURE = Identifier.of(MOD_ID, "textures/gui/hanging_signs/midas");

		@NoBlockItem public static final Block MIDAS_SIGN = new TerraformSignBlock(MIDAS_SIGN_TEXTURE, Block.Settings.copy(MIDAS_PLANKS).noCollision());
		@NoBlockItem public static final Block MIDAS_WALL_SIGN = new TerraformWallSignBlock(MIDAS_SIGN_TEXTURE, Block.Settings.copy(MIDAS_SIGN));
		@NoBlockItem public static final Block MIDAS_HANGING_SIGN = new TerraformHangingSignBlock(MIDAS_HANGING_SIGN_TEXTURE,
			MIDAS_HANGING_GUI_SIGN_TEXTURE, Block.Settings.copy(MIDAS_SIGN));
		@NoBlockItem public static final Block MIDAS_WALL_HANGING_SIGN = new TerraformWallHangingSignBlock(MIDAS_HANGING_SIGN_TEXTURE,
			MIDAS_HANGING_GUI_SIGN_TEXTURE, Block.Settings.copy(MIDAS_SIGN));

		public static final BlockFamily MIDAS_FAMILY = BlockFamilies.register(MIDAS_PLANKS).stairs(MIDAS_STAIRS).slab(MIDAS_SLAB).button(MIDAS_BUTTON)
			.pressurePlate(MIDAS_PRESSURE_PLATE).fence(MIDAS_FENCE).fenceGate(MIDAS_FENCE_GATE).door(MIDAS_DOOR).trapdoor(MIDAS_TRAPDOOR)
			.sign(MIDAS_SIGN, MIDAS_WALL_SIGN).unlockCriterionName("has_planks").group("wooden").build();

		public static final Block VIRIDIAN_PLANKS = new Block(Block.Settings.copy(Blocks.OAK_PLANKS).mapColor(MapColor.GREEN));
		public static final Block VIRIDIAN_SAPLING = new SaplingBlock(TrevorsSentinels.Registries.VIRIDIAN_TREE, Block.Settings.copy(Blocks.OAK_SAPLING));
		@NoBlockItem public static final Block POTTED_VIRIDIAN_SAPLING = new FlowerPotBlock(VIRIDIAN_SAPLING, Block.Settings.copy(Blocks.POTTED_OAK_SAPLING));
		public static final Block VIRIDIAN_LOG = new PillarBlock(Block.Settings.copy(Blocks.OAK_LOG).mapColor(MapColor.GREEN));
		public static final Block STRIPPED_VIRIDIAN_LOG = new PillarBlock(Block.Settings.copy(Blocks.STRIPPED_OAK_LOG).mapColor(MapColor.GREEN));
		public static final Block VIRIDIAN_WOOD = new PillarBlock(Block.Settings.copy(Blocks.OAK_WOOD).mapColor(MapColor.GREEN));
		public static final Block STRIPPED_VIRIDIAN_WOOD = new PillarBlock(Block.Settings.copy(Blocks.STRIPPED_OAK_WOOD).mapColor(MapColor.GREEN));
		public static final Block VIRIDIAN_LEAVES = new LeavesBlock(Block.Settings.copy(Blocks.OAK_LEAVES).mapColor(MapColor.GREEN));
		public static final Block VIRIDIAN_SLAB = new SlabBlock(Block.Settings.copy(Blocks.OAK_SLAB).mapColor(MapColor.GREEN));
		public static final Block VIRIDIAN_FENCE = new FenceBlock(Block.Settings.copy(Blocks.OAK_FENCE).mapColor(MapColor.GREEN));
		public static final Block VIRIDIAN_STAIRS = new StairsBlock(VIRIDIAN_PLANKS.getDefaultState(), Block.Settings.copy(Blocks.OAK_STAIRS).mapColor(MapColor.GREEN));
		public static final Block VIRIDIAN_BUTTON = new ButtonBlock(TrevorsSentinels.Registries.VIRIDIAN, 30, Block.Settings.copy(Blocks.OAK_BUTTON).mapColor(MapColor.GREEN));
		public static final Block VIRIDIAN_PRESSURE_PLATE = new PressurePlateBlock(TrevorsSentinels.Registries.VIRIDIAN, Block.Settings.copy(Blocks.JUNGLE_PRESSURE_PLATE).mapColor(MapColor.GREEN));
		public static final Block VIRIDIAN_DOOR = new DoorBlock(TrevorsSentinels.Registries.VIRIDIAN, Block.Settings.copy(Blocks.JUNGLE_DOOR).mapColor(MapColor.GREEN));
		public static final Block VIRIDIAN_TRAPDOOR = new TrapdoorBlock(TrevorsSentinels.Registries.VIRIDIAN, Block.Settings.copy(Blocks.JUNGLE_TRAPDOOR).mapColor(MapColor.GREEN));
		public static final Block VIRIDIAN_FENCE_GATE = new FenceGateBlock(TrevorsSentinels.Registries.VIRIDIAN_WOOD, Block.Settings.copy(Blocks.JUNGLE_FENCE_GATE).mapColor(MapColor.GREEN));

		public static final Identifier VIRIDIAN_SIGN_TEXTURE = Identifier.of(MOD_ID, "entity/signs/viridian");
		public static final Identifier VIRIDIAN_HANGING_SIGN_TEXTURE = Identifier.of(MOD_ID, "entity/signs/hanging/viridian");
		public static final Identifier VIRIDIAN_HANGING_GUI_SIGN_TEXTURE = Identifier.of(MOD_ID, "textures/gui/hanging_signs/viridian");

		@NoBlockItem public static final Block VIRIDIAN_SIGN = new TerraformSignBlock(VIRIDIAN_SIGN_TEXTURE, Block.Settings.copy(VIRIDIAN_PLANKS)
			.noCollision().instrument(NoteBlockInstrument.BASS));
		@NoBlockItem public static final Block VIRIDIAN_WALL_SIGN = new TerraformWallSignBlock(VIRIDIAN_SIGN_TEXTURE, Block.Settings.copy(VIRIDIAN_SIGN));
		@NoBlockItem public static final Block VIRIDIAN_HANGING_SIGN = new TerraformHangingSignBlock(VIRIDIAN_HANGING_SIGN_TEXTURE,
			VIRIDIAN_HANGING_GUI_SIGN_TEXTURE, Block.Settings.copy(VIRIDIAN_SIGN));
		@NoBlockItem public static final Block VIRIDIAN_WALL_HANGING_SIGN = new TerraformWallHangingSignBlock(VIRIDIAN_HANGING_SIGN_TEXTURE,
			VIRIDIAN_HANGING_GUI_SIGN_TEXTURE, Block.Settings.copy(VIRIDIAN_SIGN));

		public static final BlockFamily VIRIDIAN_FAMILY = BlockFamilies.register(VIRIDIAN_PLANKS).stairs(VIRIDIAN_STAIRS).slab(VIRIDIAN_SLAB).button(VIRIDIAN_BUTTON)
			.pressurePlate(VIRIDIAN_PRESSURE_PLATE).fence(VIRIDIAN_FENCE).fenceGate(VIRIDIAN_FENCE_GATE).door(VIRIDIAN_DOOR).trapdoor(VIRIDIAN_TRAPDOOR)
			.sign(VIRIDIAN_SIGN, VIRIDIAN_WALL_SIGN).unlockCriterionName("has_planks").group("wooden").build();

		public static final Block CERULII_PLANKS = new Block(Block.Settings.copy(Blocks.OAK_PLANKS).mapColor(MapColor.BLUE));
		public static final Block CERULII_SAPLING = new SaplingBlock(TrevorsSentinels.Registries.CERULII_TREE, Block.Settings.copy(Blocks.OAK_SAPLING));
		@NoBlockItem public static final Block POTTED_CERULII_SAPLING = new FlowerPotBlock(CERULII_SAPLING, Block.Settings.copy(Blocks.POTTED_OAK_SAPLING));
		public static final Block CERULII_LOG = new PillarBlock(Block.Settings.copy(Blocks.OAK_LOG).mapColor(MapColor.BLUE));
		public static final Block STRIPPED_CERULII_LOG = new PillarBlock(Block.Settings.copy(Blocks.STRIPPED_OAK_LOG).mapColor(MapColor.BLUE));
		public static final Block CERULII_WOOD = new PillarBlock(Block.Settings.copy(Blocks.OAK_WOOD).mapColor(MapColor.BLUE));
		public static final Block STRIPPED_CERULII_WOOD = new PillarBlock(Block.Settings.copy(Blocks.STRIPPED_OAK_WOOD).mapColor(MapColor.BLUE));
		public static final Block CERULII_LEAVES = new LeavesBlock(Block.Settings.copy(Blocks.OAK_LEAVES).mapColor(MapColor.BLUE));
		public static final Block CERULII_SLAB = new SlabBlock(Block.Settings.copy(Blocks.OAK_SLAB).mapColor(MapColor.BLUE));
		public static final Block CERULII_FENCE = new FenceBlock(Block.Settings.copy(Blocks.OAK_FENCE).mapColor(MapColor.BLUE));
		public static final Block CERULII_STAIRS = new StairsBlock(CERULII_PLANKS.getDefaultState(), Block.Settings.copy(Blocks.OAK_STAIRS).mapColor(MapColor.BLUE));
		public static final Block CERULII_BUTTON = new ButtonBlock(TrevorsSentinels.Registries.CERULII, 30, Block.Settings.copy(Blocks.OAK_BUTTON).mapColor(MapColor.BLUE));
		public static final Block CERULII_PRESSURE_PLATE = new PressurePlateBlock(TrevorsSentinels.Registries.CERULII, Block.Settings.copy(Blocks.OAK_PRESSURE_PLATE).mapColor(MapColor.BLUE));
		public static final Block CERULII_DOOR = new DoorBlock(TrevorsSentinels.Registries.CERULII, Block.Settings.copy(Blocks.OAK_DOOR).mapColor(MapColor.BLUE));
		public static final Block CERULII_TRAPDOOR = new TrapdoorBlock(TrevorsSentinels.Registries.CERULII, Block.Settings.copy(Blocks.OAK_TRAPDOOR).mapColor(MapColor.BLUE));
		public static final Block CERULII_FENCE_GATE = new FenceGateBlock(TrevorsSentinels.Registries.CERULII_WOOD, Block.Settings.copy(Blocks.OAK_FENCE_GATE).mapColor(MapColor.BLUE));

		public static final Identifier CERULII_SIGN_TEXTURE = Identifier.of(MOD_ID, "entity/signs/cerulii");
		public static final Identifier CERULII_HANGING_SIGN_TEXTURE = Identifier.of(MOD_ID, "entity/signs/hanging/cerulii");
		public static final Identifier CERULII_HANGING_GUI_SIGN_TEXTURE = Identifier.of(MOD_ID, "textures/gui/hanging_signs/cerulii");

		@NoBlockItem public static final Block CERULII_SIGN = new TerraformSignBlock(CERULII_SIGN_TEXTURE, Block.Settings.copy(CERULII_PLANKS).noCollision());
		@NoBlockItem public static final Block CERULII_WALL_SIGN = new TerraformWallSignBlock(CERULII_SIGN_TEXTURE, Block.Settings.copy(CERULII_SIGN));
		@NoBlockItem public static final Block CERULII_HANGING_SIGN = new TerraformHangingSignBlock(CERULII_HANGING_SIGN_TEXTURE,
			CERULII_HANGING_GUI_SIGN_TEXTURE, Block.Settings.copy(CERULII_SIGN));
		@NoBlockItem public static final Block CERULII_WALL_HANGING_SIGN = new TerraformWallHangingSignBlock(CERULII_HANGING_SIGN_TEXTURE,
			CERULII_HANGING_GUI_SIGN_TEXTURE, Block.Settings.copy(CERULII_SIGN));

		public static final BlockFamily CERULII_FAMILY = BlockFamilies.register(CERULII_PLANKS).stairs(CERULII_STAIRS).slab(CERULII_SLAB).button(CERULII_BUTTON)
			.pressurePlate(CERULII_PRESSURE_PLATE).fence(CERULII_FENCE).fenceGate(CERULII_FENCE_GATE).door(CERULII_DOOR).trapdoor(CERULII_TRAPDOOR)
			.sign(CERULII_SIGN, CERULII_WALL_SIGN).unlockCriterionName("has_planks").group("wooden").build();
	}
}
