package net.trevorskullcrafter.trevorssentinels;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.GameRules;
import net.trevorskullcrafter.trevorssentinels.block.ModBlocks;
import net.trevorskullcrafter.trevorssentinels.block.entity.ModBlockEntities;
import net.trevorskullcrafter.trevorssentinels.datagen.BlockTagGenerator;
import net.trevorskullcrafter.trevorssentinels.effect.ModEffects;
import net.trevorskullcrafter.trevorssentinels.entity.ModEntities;
import net.trevorskullcrafter.trevorssentinels.entity.custom.FlorbusEntity;
import net.trevorskullcrafter.trevorssentinels.entity.custom.RoombaEntity;
import net.trevorskullcrafter.trevorssentinels.entity.custom.SentinelEntity;
import net.trevorskullcrafter.trevorssentinels.fluid.ModFluids;
import net.trevorskullcrafter.trevorssentinels.item.*;
import net.trevorskullcrafter.trevorssentinels.networking.ModMessages;
import net.trevorskullcrafter.trevorssentinels.potion.ModPotions;
import net.trevorskullcrafter.trevorssentinels.recipe.ModRecipes;
import net.trevorskullcrafter.trevorssentinels.util.ModLootTableModifiers;
import net.trevorskullcrafter.trevorssentinels.util.ModRegistries;
import net.trevorskullcrafter.trevorssentinels.util.ServerState;
import net.trevorskullcrafter.trevorssentinels.util.TextUtil;
import net.trevorskullcrafter.trevorssentinels.villager.ModVillagers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.bernie.geckolib.GeckoLib;

import java.util.Objects;

import static net.trevorskullcrafter.trevorssentinels.util.TextUtil.*;

public class trevorssentinels implements ModInitializer {
	public static final String MOD_ID = "trevorssentinels";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final RegistryKey<ItemGroup> SENTINELS = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(trevorssentinels.MOD_ID, "sentinels"));
	public static final RegistryKey<ItemGroup> EFFECTS = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(trevorssentinels.MOD_ID, "effects"));
	public static final RegistryKey<ItemGroup> BETA = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(trevorssentinels.MOD_ID, "beta"));

	public static final GameRules.Key<GameRules.BooleanRule> USE_VELOCITY_FALL_DAMAGE =
			GameRuleRegistry.register("trevorssentinels:useVelocityFallDamage", GameRules.Category.MOBS, GameRuleFactory.createBooleanRule(false));
	public static final GameRules.Key<GameRules.BooleanRule> MILK_CURES_POTION_EFFECTS =
			GameRuleRegistry.register("trevorssentinels:milkCuresPotionEffects", GameRules.Category.PLAYER, GameRuleFactory.createBooleanRule(false));

	public static int getBlockWorldLevelRequirement(BlockState state){
		if(state.isIn(BlockTagGenerator.REQUIRES_LEVEL_3)) { return 3; }
		else if(state.isIn(BlockTagGenerator.REQUIRES_LEVEL_2)) { return 2; }
		else { return 1; }
	}

	@Override public void onInitialize() {
        /*EntityElytraEvents.CUSTOM.register((entity, tickElytra) -> {
			if (entity instanceof PlayerEntity player && !player.isOnGround() &&
					player.getInventory().getArmorStack(0).getItem() instanceof ArmorItem boots && boots.getMaterial() == ModArmorMaterials.NUCLEAR &&
					player.getInventory().getArmorStack(1).getItem() instanceof ArmorItem leggings && leggings.getMaterial() == ModArmorMaterials.NUCLEAR &&
					player.getInventory().getArmorStack(2).getItem() instanceof ArmorItem chestplate && chestplate.getMaterial() == ModArmorMaterials.NUCLEAR &&
					player.getInventory().getArmorStack(3).getItem() instanceof ArmorItem helmet && helmet.getMaterial() == ModArmorMaterials.NUCLEAR){
				if (tickElytra) { player.addExhaustion(0.1f); //placeholder effect for soul
				if(!player.getWorld().isClient() && player.getWorld().getTime() % 5 == 0) player.getWorld().playSoundFromEntity(null, player,
						SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.MASTER, Math.min(Math.abs((float) player.getVelocity().y), 2.0f), 0f);
				else player.getWorld().addParticle(ParticleTypes.LARGE_SMOKE, player.getX(), player.getY(), player.getZ(),
						-player.getVelocity().x, -player.getVelocity().y, -player.getVelocity().z);
			} return true; } return false;
		});*/

		PlayerBlockBreakEvents.BEFORE.register((world, player, pos, state, blockEntity) -> {
			if(player.isCreative()) { return true; }
            return trevorssentinels.getBlockWorldLevelRequirement(state) <= ServerState.getServerState(Objects.requireNonNull(world.getServer())).worldLevel;
        });

		FabricLoader.getInstance().getModContainer(trevorssentinels.MOD_ID).ifPresent(modContainer -> {
			ResourceManagerHelper.registerBuiltinResourcePack(new Identifier(trevorssentinels.MOD_ID, "hologui"), modContainer,
					Text.translatable(new Identifier(trevorssentinels.MOD_ID, "hologui").toTranslationKey()), ResourcePackActivationType.NORMAL);
			ResourceManagerHelper.registerBuiltinResourcePack(new Identifier(trevorssentinels.MOD_ID, "vanilla_extensions"), modContainer,
					Text.translatable(new Identifier(trevorssentinels.MOD_ID, "vanilla_extensions").toTranslationKey()), ResourcePackActivationType.DEFAULT_ENABLED);
			ResourceManagerHelper.registerBuiltinResourcePack(new Identifier(trevorssentinels.MOD_ID, "legacy"), modContainer,
					Text.translatable(new Identifier(trevorssentinels.MOD_ID, "legacy").toTranslationKey()), ResourcePackActivationType.NORMAL);
		});

		ModEffects.registerStatusEffects();
		ModItems.registerModItems();
		ModMessages.registerC2SPackets();
		ModArmory.registerArmory();
		ModEffectItems.registerModItems();
		BetaItems.registerModItems();
		ModPotions.registerPotions();
		ModBlocks.registerModBlocks();

		ModBlockEntities.registerBlockEntities();
		ModRecipes.registerRecipes();
		ModVillagers.registerVillagers();
		ModVillagers.registerTrades();

		ModFluids.registerFluids();
		ModRegistries.registerFlammableBlocks();
		ModRegistries.registerStrippables();
		ModRegistries.registerFuels();
		ModRegistries.registerCommands();
		ModRegistries.registerParticles();
		ModLootTableModifiers.modifyLootTables();

		GeckoLib.initialize();
		FabricDefaultAttributeRegistry.register(ModEntities.SENTINEL, SentinelEntity.setAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.GALINITE_ROOMBA, RoombaEntity.setAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.FLORBUS, FlorbusEntity.setAttributes());

		Registry.register(Registries.ITEM_GROUP, SENTINELS, FabricItemGroup.builder()
				.displayName(TextUtil.coloredText("itemGroup.trevorssentinels.sentinels", SENTINEL_AQUA))
				.icon(() -> new ItemStack(ModItems.HARD_LIGHT_PROJECTOR)).build());
		Registry.register(Registries.ITEM_GROUP, EFFECTS, FabricItemGroup.builder()
				.displayName(TextUtil.coloredText("itemGroup.trevorssentinels.effects", COPPER))
				.icon(() -> new ItemStack(ModEffectItems.ONE_PENCE)).build());
		Registry.register(Registries.ITEM_GROUP, BETA, FabricItemGroup.builder()
				.displayName(TextUtil.coloredText("itemGroup.trevorssentinels.beta", DARK_GREEN))
				.icon(() -> new ItemStack(ModItems.RUINOUS_GAZE)).build());
		ModItemGroupEvents.registerAllGroupEvents();
		ModRegistries.registerWorldLevelState();
	}
}