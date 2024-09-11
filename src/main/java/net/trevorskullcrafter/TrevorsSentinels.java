package net.trevorskullcrafter;

import io.wispforest.owo.itemgroup.Icon;
import io.wispforest.owo.itemgroup.OwoItemGroup;
import io.wispforest.owo.itemgroup.gui.ItemGroupButton;
import io.wispforest.owo.registration.reflect.FieldRegistrationHandler;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.type.BlockSetTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.FabricLoader;
import net.minecraft.block.BlockSetType;
import net.minecraft.block.SaplingGenerator;
import net.minecraft.block.WoodType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.mob.WitherSkeletonEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.trevorskullcrafter.block.TSBlocks;
import net.trevorskullcrafter.block.entity.ModBlockEntities;
import net.trevorskullcrafter.command.WorldLevelCommand;
import net.trevorskullcrafter.datagen.ConfiguredFeatureGenerator;
import net.trevorskullcrafter.effect.ModEffects;
import net.trevorskullcrafter.entity.ModEntities;
import net.trevorskullcrafter.entity.custom.FlorbusEntity;
import net.trevorskullcrafter.entity.custom.RoombaEntity;
import net.trevorskullcrafter.entity.custom.SentinelEntity;
import net.trevorskullcrafter.item.ModArmorMaterials;
import net.trevorskullcrafter.item.ModDataComponentTypes;
import net.trevorskullcrafter.item.TSItems;
import net.trevorskullcrafter.item.custom.PhaserItem;
import net.trevorskullcrafter.networking.ModMessages;
import net.trevorskullcrafter.networking.packet.PortkeyPayload;
import net.trevorskullcrafter.networking.packet.ReloadPayload;
import net.trevorskullcrafter.networking.packet.StyleSwitchPayload;
import net.trevorskullcrafter.potion.ModPotions;
import net.trevorskullcrafter.recipe.ModRecipes;
import net.trevorskullcrafter.util.ModLootTableModifiers;
import net.trevorskullcrafter.util.ServerState;
import net.trevorskullcrafter.util.TextUtil;
import net.trevorskullcrafter.villager.ModVillagers;
import net.trevorskullcrafter.world.gen.BiomeModifier;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.util.Objects;
import java.util.Optional;

public class TrevorsSentinels implements ModInitializer {
	public static final String MOD_ID = "trevorssentinels";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final OwoItemGroup SENTINELS = OwoItemGroup.builder(Identifier.of(MOD_ID, "sentinels"), () -> Icon.of(Items.IRON_INGOT)).build();

	public static final GameRules.Key<GameRules.BooleanRule> USE_VELOCITY_FALL_DAMAGE =
			GameRuleRegistry.register("trevorssentinels:useVelocityFallDamage", GameRules.Category.MOBS, GameRuleFactory.createBooleanRule(false));
	public static final GameRules.Key<GameRules.BooleanRule> MILK_CURES_POTION_EFFECTS =
			GameRuleRegistry.register("trevorssentinels:milkCuresPotionEffects", GameRules.Category.PLAYER, GameRuleFactory.createBooleanRule(false));

	@Override public void onInitialize() {
		GLFW.glfwInit();
		String[] flavors = {"ay yo, the pizza here", "Back again?", "*mic drop*", "I'll load the game when you fix this damn door!",
				"Kristoffer was here", "Aw man, here we go again...", "Don't you have work to be doing?", "Nooo don't crash your so sexy aha",
				"Hey, at least this isn't Unity!", "version 1.0 coming never", "Water check!"};
		LOGGER.info(flavors[(int) (Math.random() * flavors.length)]);

		FabricLoader.INSTANCE.getModContainer(MOD_ID).ifPresent(modContainer -> {
			ResourceManagerHelper.registerBuiltinResourcePack(Identifier.of(MOD_ID, "hologui"), modContainer,
					Text.translatable(Identifier.of(MOD_ID, "hologui").toTranslationKey()), ResourcePackActivationType.NORMAL);
			ResourceManagerHelper.registerBuiltinResourcePack(Identifier.of(MOD_ID, "vanilla_extensions"), modContainer,
					Text.translatable(Identifier.of(MOD_ID, "vanilla_extensions").toTranslationKey()), ResourcePackActivationType.DEFAULT_ENABLED);
			ResourceManagerHelper.registerBuiltinResourcePack(Identifier.of(MOD_ID, "legacy"), modContainer,
					Text.translatable(Identifier.of(MOD_ID, "legacy").toTranslationKey()), ResourcePackActivationType.NORMAL);
		});

		ModDataComponentTypes.registerDataComponentTypes();
		ModEffects.registerStatusEffects();

		FieldRegistrationHandler.register(TSBlocks.class, MOD_ID, true);
		FieldRegistrationHandler.register(TSItems.class, MOD_ID, true);

		PayloadTypeRegistry.playC2S().register(StyleSwitchPayload.ID, StyleSwitchPayload.CODEC);
		PayloadTypeRegistry.playC2S().register(ReloadPayload.ID, ReloadPayload.CODEC);
		PayloadTypeRegistry.playC2S().register(PortkeyPayload.ID, PortkeyPayload.CODEC);

		ModMessages.registerC2SPackets();
		ModArmorMaterials.registerModArmorMaterials();
		ModPotions.registerPotions();
		ModEntities.registerModEntities();

		ModBlockEntities.registerBlockEntities();
		ModRecipes.registerRecipes();
		ModVillagers.registerVillagers();
		ModVillagers.registerTrades();
		BiomeModifier.registerModifications();

		//TrevorsSentinels.Registries.registerModelPredicates();
		TrevorsSentinels.Registries.registerWorldLevelState();
		TrevorsSentinels.Registries.registerFlammableBlocks();
		TrevorsSentinels.Registries.registerStrippables();
		TrevorsSentinels.Registries.registerFuels();
		TrevorsSentinels.Registries.registerCommands();
		TrevorsSentinels.Registries.registerParticles();
		ModLootTableModifiers.modifyLootTables();

		FabricDefaultAttributeRegistry.register(ModEntities.SENTINEL, SentinelEntity.setAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.JANITOR_DROID, RoombaEntity.setAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.FLORBUS, FlorbusEntity.setAttributes());

		SENTINELS.addCustomTab(Icon.of(TSItems.Magic.EYE_OF_RUIN),"magic", (context, entries) -> {
			entries.add(TSBlocks.Magic.FLESH_BLOCK);
			entries.add(TSBlocks.Magic.FLESH_VEINS);
			entries.add(TSBlocks.Magic.FLESHY_EYE);
			entries.add(TSBlocks.Magic.FLESHY_PUSTULE);

			entries.add(TSItems.Magic.ROSE_GOLD_HELMET);
			entries.add(TSItems.Magic.ROSE_GOLD_CHESTPLATE);
			entries.add(TSItems.Magic.ROSE_GOLD_LEGGINGS);
			entries.add(TSItems.Magic.ROSE_GOLD_BOOTS);

			entries.add(TSItems.Magic.UNHOLY_CORE);

			entries.add(TSItems.Magic.ARMA_DEI_SWORD);
			entries.add(TSItems.Magic.ARMA_DEI_DAGGER);
			entries.add(TSItems.Magic.ARMA_DEI_PICKAXE);
			entries.add(TSItems.Magic.ARMA_DEI_BATTLEAXE);
			entries.add(TSItems.Magic.ARMA_DEI_SHOVEL);
			entries.add(TSItems.Magic.ARMA_DEI_HOE);
			entries.add(TSItems.Magic.ARMA_DEI_HELMET);
			entries.add(TSItems.Magic.ARMA_DEI_CHESTPLATE);
			entries.add(TSItems.Magic.ARMA_DEI_LEGGINGS);
			entries.add(TSItems.Magic.ARMA_DEI_BOOTS);
		}, false);
		SENTINELS.addCustomTab(Icon.of(TSItems.Tech.SENTINEL_HARD_LIGHT_PROJECTOR),"tech", (context, entries) -> {
			entries.add(TSItems.Tech.HARD_LIGHT_PROJECTOR.getDefaultStack());
			entries.add(TSItems.Tech.CAUTION_HARD_LIGHT_PROJECTOR.getDefaultStack());
			entries.add(TSItems.Tech.SENTINEL_HARD_LIGHT_PROJECTOR.getDefaultStack());
			entries.add(TSItems.Tech.PLASMA_CELL);
			entries.add(TSItems.Tech.PAINT_PACK);
			entries.add(TSItems.Tech.CHROMATIC_LENS);

			entries.add(TSItems.Tech.SCRAP_METAL_SWORD);
			entries.add(TSItems.Tech.SCRAP_METAL_KNIFE);
			entries.add(TSItems.Tech.SCRAP_METAL_PHASER);
			entries.add(PhaserItem.getCustomPhaser(new ItemStack(TSItems.Tech.SCRAP_METAL_PHASER), Text.translatable("item.trevorssentinels.custom_phaser.comet"), TextUtil.TRANQUIL, TextUtil.SENTINEL_CRIMSON2));
			entries.add(PhaserItem.getCustomPhaser(new ItemStack(TSItems.Tech.SCRAP_METAL_PHASER), Text.translatable("item.trevorssentinels.custom_phaser.comet"), TextUtil.TRANQUIL, TextUtil.SENTINEL_CRIMSON2,
					new ItemStack(TSItems.Tech.COUNTERFORCE_DIFFUSER)));
			entries.add(PhaserItem.getCustomPhaser(new ItemStack(TSItems.Tech.SCRAP_METAL_PHASER), Text.translatable("item.trevorssentinels.custom_phaser.deathgun"), TextUtil.DEATHGUN, TextUtil.PURE2,
					new ItemStack(TSItems.Tech.PHASE_ASSIMILATOR)));
			entries.add(TSItems.Tech.SCRAP_METAL_DRILL);
			entries.add(TSItems.Tech.SCRAP_METAL_CHAINSAW);
			entries.add(TSItems.Tech.SCRAP_METAL_SHOVEL);
			entries.add(TSItems.Tech.SCRAP_METAL_HOE);
			entries.add(TSItems.Tech.SCRAP_METAL_HELMET);
			entries.add(TSItems.Tech.SCRAP_METAL_CHESTPLATE);
			entries.add(TSItems.Tech.SCRAP_METAL_LEGGINGS);
			entries.add(TSItems.Tech.SCRAP_METAL_BOOTS);

			entries.add(TSItems.Tech.STARSTEEL_SWORD);
			entries.add(TSItems.Tech.STARSTEEL_KNIFE);
			entries.add(TSItems.Tech.STARSTEEL_PHASER);
			entries.add(TSItems.Tech.STARSTEEL_DRILL);
			entries.add(TSItems.Tech.STARSTEEL_AXE);
			entries.add(TSItems.Tech.STARSTEEL_SHOVEL);
			entries.add(TSItems.Tech.STARSTEEL_HOE);
			entries.add(TSItems.Tech.STARSTEEL_HELMET);
			entries.add(TSItems.Tech.STARSTEEL_CHESTPLATE);
			entries.add(TSItems.Tech.STARSTEEL_LEGGINGS);
			entries.add(TSItems.Tech.STARSTEEL_BOOTS);

			entries.add(TSItems.Tech.INDUSTRIAL_PHASER);
			entries.add(PhaserItem.getCustomPhaser(new ItemStack(TSItems.Tech.STARSTEEL_PHASER), Text.translatable("item.trevorssentinels.custom_phaser.lunar"), TextUtil.SENTINEL_AQUA1, TextUtil.SENTINEL_GOLD1,
					new ItemStack(TSItems.Tech.PHASE_ASSIMILATOR), new ItemStack(TSItems.Tech.AUXILIARY_PLASMA_CHAMBER), new ItemStack(TSItems.Tech.AUXILIARY_PLASMA_CHAMBER)));
			entries.add(PhaserItem.getCustomPhaser(new ItemStack(TSItems.Tech.STARSTEEL_PHASER), Text.translatable("item.trevorssentinels.custom_phaser.lunar"), TextUtil.SENTINEL_GOLD1, TextUtil.NUCLEAR1,
					new ItemStack(TSItems.Tech.PHASE_ASSIMILATOR), new ItemStack(TSItems.Tech.AUXILIARY_PLASMA_CHAMBER), new ItemStack(TSItems.Tech.AUXILIARY_PLASMA_CHAMBER)));
			entries.add(TSItems.Tech.NUCLEAR_PHASER);
			entries.add(PhaserItem.getCustomPhaser(new ItemStack(TSItems.Tech.NUCLEAR_PHASER), Text.translatable("item.trevorssentinels.custom_phaser.pandemic"), TextUtil.SKULLWEED, TextUtil.NUCLEAR1,
					new ItemStack(TSItems.Tech.POISON_CAPSULE), new ItemStack(TSItems.Tech.AUXILIARY_PLASMA_CHAMBER), new ItemStack(TSItems.Tech.AUXILIARY_PLASMA_CHAMBER),
					new ItemStack(TSItems.Tech.WITHER_CAPSULE), new ItemStack(TSItems.Tech.ADVANCED_BREECH_MECHANISM)));
			entries.add(TSItems.Tech.NANOTECH_PHASER);
			entries.add(PhaserItem.getCustomPhaser(new ItemStack(TSItems.Tech.NANOTECH_PHASER), Text.translatable("item.trevorssentinels.custom_phaser.serenity"), null, TextUtil.SENTINEL_AQUA1,
					new ItemStack(TSItems.Tech.PHASE_ASSIMILATOR), new ItemStack(TSItems.Tech.ADVANCED_BREECH_MECHANISM), new ItemStack(TSItems.Tech.ADVANCED_BREECH_MECHANISM),
					new ItemStack(TSItems.Tech.COUNTERFORCE_DIFFUSER), new ItemStack(TSItems.Tech.ADVANCED_BREECH_MECHANISM), new ItemStack(TSItems.Tech.ADVANCED_BREECH_MECHANISM), new ItemStack(TSItems.Tech.ADVANCED_BREECH_MECHANISM)));

			entries.add(TSItems.Tech.ZENITHIUM_SWORD);
			entries.add(TSItems.Tech.ZENITHIUM_DAGGER);
			entries.add(TSItems.Tech.ZENITHIUM_PHASER);
			entries.add(PhaserItem.getCustomPhaser(new ItemStack(TSItems.Tech.ZENITHIUM_PHASER), Text.translatable("item.trevorssentinels.custom_phaser.bfg"), new Color(62, 62, 62, 255), TextUtil.SENTINEL_CRIMSON1));
			entries.add(TSItems.Tech.ZENITHIUM_PICKAXE);
			entries.add(TSItems.Tech.ZENITHIUM_AXE);
			entries.add(TSItems.Tech.ZENITHIUM_SHOVEL);
			entries.add(TSItems.Tech.ZENITHIUM_HOE);
			entries.add(TSItems.Tech.ZENITHIUM_HELMET);
			entries.add(TSItems.Tech.ZENITHIUM_CHESTPLATE);
			entries.add(TSItems.Tech.ZENITHIUM_LEGGINGS);
			entries.add(TSItems.Tech.ZENITHIUM_BOOTS);
		}, false);
		SENTINELS.addCustomTab(Icon.of(TSItems.Tech.ZENITHIUM_CLUSTER),"beta", (context, entries) -> {
			entries.add(TSItems.Magic.RESISTANCE_ITEM);
			entries.add(TSItems.Magic.FIRE_RESISTANCE_ITEM);
		}, false);
		SENTINELS.addButton(ItemGroupButton.link(SENTINELS, Icon.of(TSItems.Tech.MUSIC_DISC_LAPSE_IN_JUDGEMENT), "wiki", "https://github.com/jainen1/Trevors-Sentinels-Quilt"));
		SENTINELS.initialize();
	}

	public static class Registries {
		public static final Identifier pale = Identifier.of(MOD_ID, "pale");
		public static final Identifier charred = Identifier.of(MOD_ID, "charred");
		public static final Identifier midas = Identifier.of(MOD_ID, "midas");
		public static final Identifier viridian = Identifier.of(MOD_ID, "viridian");
		public static final Identifier cerulii = Identifier.of(MOD_ID, "cerulii");

		public static final BlockSetType PALE = BlockSetTypeBuilder.copyOf(BlockSetType.CRIMSON).build(pale);
		public static final BlockSetType CHARRED = BlockSetTypeBuilder.copyOf(BlockSetType.OAK).build(charred);
		public static final BlockSetType MIDAS = BlockSetTypeBuilder.copyOf(BlockSetType.OAK).build(midas);
		public static final BlockSetType VIRIDIAN = BlockSetTypeBuilder.copyOf(BlockSetType.CHERRY).build(viridian);
		public static final BlockSetType CERULII  = BlockSetTypeBuilder.copyOf(BlockSetType.OAK).build(cerulii);

		public static final WoodType PALE_WOOD = new WoodType("pale", PALE);
		public static final WoodType CHARRED_WOOD = new WoodType("pale", CHARRED);
		public static final WoodType MIDAS_WOOD = new WoodType("pale", MIDAS);
		public static final WoodType VIRIDIAN_WOOD = new WoodType("pale", VIRIDIAN);
		public static final WoodType CERULII_WOOD = new WoodType("pale", CERULII);

		public static RegistryKey<DamageType> INFESTED = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of(MOD_ID, "infested"));
		public static RegistryKey<DamageType> IRRADIATED = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of(MOD_ID, "irradiated"));
		public static RegistryKey<DamageType> REDSTONED = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of(MOD_ID, "redstoned"));
		public static RegistryKey<DamageType> SHARD_PROJECTILE = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of(MOD_ID, "shard_projectile"));
		public static RegistryKey<DamageType> PHASER_PROJECTILE = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of(MOD_ID, "phaser_projectile"));
		public static RegistryKey<DamageType> DAGGER_PROJECTILE = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of(MOD_ID, "dagger_projectile"));

		public static SaplingGenerator PALE_TREE = new SaplingGenerator("pale", 0.1f, Optional.empty(), Optional.empty(),
				Optional.of(ConfiguredFeatureGenerator.PALE_TREE), Optional.of(ConfiguredFeatureGenerator.FANCY_PALE_TREE), Optional.empty(), Optional.empty());
		public static SaplingGenerator MIDAS_TREE = new SaplingGenerator("midas", 0.1f, Optional.empty(), Optional.empty(),
				Optional.of(ConfiguredFeatureGenerator.MIDAS_TREE), Optional.of(ConfiguredFeatureGenerator.FANCY_MIDAS_TREE), Optional.empty(), Optional.empty());
		public static SaplingGenerator VIRIDIAN_TREE = new SaplingGenerator("viridian", 0.1f, Optional.empty(), Optional.empty(),
				Optional.of(ConfiguredFeatureGenerator.VIRIDIAN_TREE), Optional.of(ConfiguredFeatureGenerator.FANCY_VIRIDIAN_TREE), Optional.empty(), Optional.empty());
		public static SaplingGenerator CERULII_TREE = new SaplingGenerator("cerulii", 0.1f, Optional.empty(), Optional.empty(),
				Optional.of(ConfiguredFeatureGenerator.CERULII_TREE), Optional.of(ConfiguredFeatureGenerator.FANCY_CERULII_TREE), Optional.empty(), Optional.empty());

		public static final SimpleParticleType FLESH_PUS = FabricParticleTypes.simple();
		public static final SimpleParticleType MUZZLE_FLASH = FabricParticleTypes.simple();

		public static void registerFlammableBlocks(){
			LOGGER.info("Committing arson... ("+ MOD_ID + ")");
			FlammableBlockRegistry registry = FlammableBlockRegistry.getDefaultInstance();

			registry.add(TSBlocks.Trees.PALE_LOG, 5, 5);
			registry.add(TSBlocks.Trees.PALE_WOOD, 5, 5);
			registry.add(TSBlocks.Trees.STRIPPED_PALE_LOG, 5, 5);
			registry.add(TSBlocks.Trees.STRIPPED_PALE_WOOD, 5, 5);
			registry.add(TSBlocks.Trees.PALE_PLANKS, 5, 20);
			registry.add(TSBlocks.Trees.PALE_LEAVES, 30, 60);

			registry.add(TSBlocks.Trees.CHARRED_LOG, 2, 2);
			registry.add(TSBlocks.Trees.CHARRED_WOOD, 2, 2);
			registry.add(TSBlocks.Trees.STRIPPED_CHARRED_LOG, 2, 2);
			registry.add(TSBlocks.Trees.STRIPPED_CHARRED_WOOD, 2, 2);
			registry.add(TSBlocks.Trees.CHARRED_PLANKS, 2, 8);

			registry.add(TSBlocks.Trees.MIDAS_LOG, 3, 3);
			registry.add(TSBlocks.Trees.MIDAS_WOOD, 3, 3);
			registry.add(TSBlocks.Trees.STRIPPED_MIDAS_LOG, 3, 3);
			registry.add(TSBlocks.Trees.STRIPPED_MIDAS_WOOD, 3, 3);
			registry.add(TSBlocks.Trees.MIDAS_PLANKS, 3, 12);
			registry.add(TSBlocks.Trees.MIDAS_LEAVES, 18, 40);

			registry.add(TSBlocks.Trees.VIRIDIAN_LOG, 5, 5);
			registry.add(TSBlocks.Trees.VIRIDIAN_WOOD, 5, 5);
			registry.add(TSBlocks.Trees.STRIPPED_VIRIDIAN_LOG, 5, 5);
			registry.add(TSBlocks.Trees.STRIPPED_VIRIDIAN_WOOD, 5, 5);
			registry.add(TSBlocks.Trees.VIRIDIAN_PLANKS, 5, 20);
			registry.add(TSBlocks.Trees.VIRIDIAN_LEAVES, 30, 60);

			registry.add(TSBlocks.Trees.CERULII_LOG, 5, 5);
			registry.add(TSBlocks.Trees.CERULII_WOOD, 5, 5);
			registry.add(TSBlocks.Trees.STRIPPED_CERULII_LOG, 5, 5);
			registry.add(TSBlocks.Trees.STRIPPED_CERULII_WOOD, 5, 5);
			registry.add(TSBlocks.Trees.CERULII_PLANKS, 5, 20);
			registry.add(TSBlocks.Trees.CERULII_LEAVES, 30, 60);
		}

		public static void registerStrippables(){
			LOGGER.info("Stripping logs... ("+ MOD_ID + ")");
			StrippableBlockRegistry.register(TSBlocks.Trees.PALE_LOG, TSBlocks.Trees.STRIPPED_PALE_LOG);
			StrippableBlockRegistry.register(TSBlocks.Trees.PALE_WOOD, TSBlocks.Trees.STRIPPED_PALE_WOOD);

			StrippableBlockRegistry.register(TSBlocks.Trees.CHARRED_LOG, TSBlocks.Trees.STRIPPED_CHARRED_LOG);
			StrippableBlockRegistry.register(TSBlocks.Trees.CHARRED_WOOD, TSBlocks.Trees.STRIPPED_CHARRED_WOOD);

			StrippableBlockRegistry.register(TSBlocks.Trees.MIDAS_LOG, TSBlocks.Trees.STRIPPED_MIDAS_LOG);
			StrippableBlockRegistry.register(TSBlocks.Trees.MIDAS_WOOD, TSBlocks.Trees.STRIPPED_MIDAS_WOOD);

			StrippableBlockRegistry.register(TSBlocks.Trees.VIRIDIAN_LOG, TSBlocks.Trees.STRIPPED_VIRIDIAN_LOG);
			StrippableBlockRegistry.register(TSBlocks.Trees.VIRIDIAN_WOOD, TSBlocks.Trees.STRIPPED_VIRIDIAN_WOOD);

			StrippableBlockRegistry.register(TSBlocks.Trees.CERULII_LOG, TSBlocks.Trees.STRIPPED_CERULII_LOG);
			StrippableBlockRegistry.register(TSBlocks.Trees.CERULII_WOOD, TSBlocks.Trees.STRIPPED_CERULII_WOOD);
		}

		public static void registerFuels(){
			LOGGER.info("Registering fuels... ("+ MOD_ID + ")");
			FuelRegistry.INSTANCE.add(TSItems.Beta.FEATHERCORN, 200);
		}

		public static void registerWorldLevelState(){
			ServerEntityCombatEvents.AFTER_KILLED_OTHER_ENTITY.register((world, entity, killedEntity) -> {
				ServerState serverState = ServerState.getServerState(Objects.requireNonNull(entity.getServer(), "Server is null"));
				int originalLevel = serverState.worldLevel;

				if(entity instanceof WitherEntity witherEntity){
					world.createExplosion(entity, entity.getX(), entity.getY(), entity.getZ(), 8f, false, World.ExplosionSourceType.MOB);
					WitherSkeletonEntity leftovers = new WitherSkeletonEntity(EntityType.WITHER_SKELETON, world);
					leftovers.setPosition(witherEntity.getPos());
					leftovers.setCustomName(TextUtil.coloredText("Cerberus, Darkness Incarnate", TextUtil.DARK_PURPLE));
					witherEntity.getWorld().spawnEntity(leftovers);
					for (PlayerEntity player:world.getPlayers()) {
						player.sendMessage(Text.literal(witherEntity.getName().getString() + " grows restless!").formatted(Formatting.RED), false);
					}
					if(serverState.worldLevel < 2) {
						serverState.worldLevel = 2;
						serverState.markDirty();
					}
				}

				if(serverState.worldLevel != originalLevel){ for (PlayerEntity player:world.getPlayers()) {
					player.sendMessage(Text.empty().append(Text.literal((serverState.worldLevel > originalLevel)? "World level upgraded to " : "World level reverted to "))
							.append(Text.literal(String.valueOf(serverState.worldLevel)).formatted(Formatting.GOLD)).append(Text.literal(" from "))
							.append(Text.literal(String.valueOf(originalLevel)).formatted(Formatting.RED)).append(Text.literal("!")), false);
				}}
			});

			ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
				server.getCommandManager();
				//handler.player.getAdvancementTracker().grantCriterion(server.getAdvancementLoader().get(Identifier.of(MOD_ID, "trevorssentinels")), "on_join");
				handler.player.sendMessage(Text.literal("Welcome back, " + handler.player.getName().getString()+"!").formatted(Formatting.GREEN), false);
			});
		}

		public static void registerCommands(){
			CommandRegistrationCallback.EVENT.register(WorldLevelCommand::register);
		}

		public static void registerParticles(){
			Registry.register(net.minecraft.registry.Registries.PARTICLE_TYPE, Identifier.of(MOD_ID, "flesh_pus"), FLESH_PUS);
			Registry.register(net.minecraft.registry.Registries.PARTICLE_TYPE, Identifier.of(MOD_ID, "muzzle_flash"), MUZZLE_FLASH);
		}
	}
}