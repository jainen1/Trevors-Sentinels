package net.trevorskullcrafter.item;

import io.wispforest.owo.registration.annotations.RegistryNamespace;
import io.wispforest.owo.registration.reflect.ItemRegistryContainer;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.jukebox.JukeboxSong;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.ComponentType;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.*;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.trevorskullcrafter.block.TSBlocks;
import net.trevorskullcrafter.effect.ModEffects;
import net.trevorskullcrafter.entity.ModEntities;
import net.trevorskullcrafter.item.custom.*;
import net.trevorskullcrafter.item.custom.unique.*;
import net.trevorskullcrafter.util.CustomRarities;
import net.trevorskullcrafter.util.TextUtil;
import org.spongepowered.include.com.google.common.collect.ImmutableMap;

import java.util.List;

import static net.trevorskullcrafter.item.ModArmorMaterials.*;

public class TSItems implements ItemRegistryContainer {
	public static <T> T getOrSetComponent(ItemStack stack, ComponentType<T> type, T fallback) {
		if(stack.get(type) == null) { stack.applyComponentsFrom(ComponentMap.builder().add(type, fallback).build()); } return stack.get(type);
	}

	@RegistryNamespace("trevorssentinels") public static class Tech implements ItemRegistryContainer {
		public static final Item SCRAP_METAL_SHARD = new Item(new Item.Settings());
		public static final Item SCRAP_METAL_HELMET = new ArmorItem(ModArmorMaterials.SCRAP_METAL, ArmorItem.Type.HELMET,
			new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(SCRAP_METAL_DURABILITY)));
		public static final Item SCRAP_METAL_CHESTPLATE = new ArmorItem(ModArmorMaterials.SCRAP_METAL, ArmorItem.Type.CHESTPLATE,
			new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(SCRAP_METAL_DURABILITY)));
		public static final Item SCRAP_METAL_LEGGINGS = new ArmorItem(ModArmorMaterials.SCRAP_METAL, ArmorItem.Type.LEGGINGS,
			new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(SCRAP_METAL_DURABILITY)));
		public static final Item SCRAP_METAL_BOOTS = new ArmorItem(ModArmorMaterials.SCRAP_METAL, ArmorItem.Type.BOOTS,
			new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(SCRAP_METAL_DURABILITY)));
		public static final Item SCRAP_METAL_SWORD = new SwordItem(ModToolMaterials.SCRAP_METAL,
			new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.SCRAP_METAL, 1, -2.4f)));
		public static final Item SCRAP_METAL_PHASER = new PhaserItem(TextUtil.PURE2, new Item.Settings().component(ModDataComponentTypes.PHASER_MODIFIERS, new PhaserModifiersComponent.Builder()
				.attachment_slots(3).projectile_damage(5).projectile_lifetime(60).projectile_inaccuracy(40).projectile_recoil(5).burst_projectiles(1).burst_cooldown(10)
				.reload_cooldown(50).magazine_size(4).build()));
		public static final Item SCRAP_METAL_KNIFE = new DaggerItem(ModToolMaterials.SCRAP_METAL, 1, 0, 0.9f, new Item.Settings());
		public static final Item SCRAP_METAL_DRILL = new PickaxeItem(ModToolMaterials.SCRAP_METAL, new Item.Settings()
			.attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.SCRAP_METAL, 1, -2.8f)));
		public static final Item SCRAP_METAL_CHAINSAW = new ChainsawItem(ModToolMaterials.SCRAP_METAL, new Item.Settings()
			.attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterials.SCRAP_METAL, 3, -3f)));
		public static final Item SCRAP_METAL_SHOVEL = new ShovelItem(ModToolMaterials.SCRAP_METAL, new Item.Settings()
			.attributeModifiers(ShovelItem.createAttributeModifiers(ModToolMaterials.SCRAP_METAL, 1.5f, -3f)));
		public static final Item SCRAP_METAL_HOE = new HoeItem(ModToolMaterials.SCRAP_METAL, new Item.Settings()
			.attributeModifiers(HoeItem.createAttributeModifiers(ModToolMaterials.SCRAP_METAL, 0, -3)));

		public static final Item INDUSTRIAL_HELMET = new ArmorItem(ModArmorMaterials.STARSTEEL, ArmorItem.Type.HELMET,
			new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(STARSTEEL_DURABILITY)));
		public static final Item INDUSTRIAL_HARNESS = new ArmorItem( ModArmorMaterials.STARSTEEL, ArmorItem.Type.CHESTPLATE,
			new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(STARSTEEL_DURABILITY)));
		public static final Item INDUSTRIAL_PANTS = new ArmorItem(ModArmorMaterials.STARSTEEL, ArmorItem.Type.LEGGINGS,
			new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(STARSTEEL_DURABILITY)));
		public static final Item INDUSTRIAL_BOOTS = new ArmorItem(ModArmorMaterials.STARSTEEL, ArmorItem.Type.BOOTS,
			new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(STARSTEEL_DURABILITY)));
		public static final Item INDUSTRIAL_CROWBAR = new SwordItem(ModToolMaterials.STARSTEEL, new Item.Settings()
			.attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterials.STARSTEEL, 3, -2.4f)));
		public static final Item INDUSTRIAL_PHASER = new PhaserItem(TextUtil.PURE2, new Item.Settings().component(ModDataComponentTypes.PHASER_MODIFIERS, new PhaserModifiersComponent.Builder()
				.attachment_slots(4).projectile_damage(6).projectile_lifetime(7).projectile_inaccuracy(120).projectile_recoil(5).burst_projectiles(3).burst_cooldown(20)
				.reload_cooldown(100).magazine_size(4).build()));
		public static final Item INDUSTRIAL_KNIFE = new DaggerItem(ModToolMaterials.STARSTEEL, 3, 1.5f, 0.5f, new Item.Settings());
		public static final Item INDUSTRIAL_DRILL = new PickaxeItem(ModToolMaterials.STARSTEEL, new Item.Settings()
			.attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.STARSTEEL, 1, 0)));
		public static final Item INDUSTRIAL_AXE = new AxeItem(ModToolMaterials.STARSTEEL, new Item.Settings()
			.attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterials.STARSTEEL, 3, -2.8f)));
		public static final Item INDUSTRIAL_SHOVEL = new ShovelItem(ModToolMaterials.STARSTEEL, new Item.Settings()
			.attributeModifiers(ShovelItem.createAttributeModifiers(ModToolMaterials.STARSTEEL, 1.5f, -3f)));
		public static final Item INDUSTRIAL_HOE = new HoeItem(ModToolMaterials.STARSTEEL, new Item.Settings()
			.attributeModifiers(HoeItem.createAttributeModifiers(ModToolMaterials.STARSTEEL, 0, -3f)));

		public static final Item STARSTEEL_SIGN = new SignItem(new Item.Settings().maxCount(16), TSBlocks.Tech.STARSTEEL_SIGN, TSBlocks.Tech.STARSTEEL_WALL_SIGN);
		public static final Item STARSTEEL_HANGING_SIGN = new HangingSignItem(TSBlocks.Tech.STARSTEEL_HANGING_SIGN, TSBlocks.Tech.STARSTEEL_WALL_HANGING_SIGN, new Item.Settings().maxCount(16));
		public static final Item HOLOGRAPHIC_SIGN = new SignItem(new Item.Settings().maxCount(16), TSBlocks.Tech.HOLOGRAPHIC_SIGN, TSBlocks.Tech.HOLOGRAPHIC_WALL_SIGN);
		public static final Item HOLOGRAPHIC_HANGING_SIGN = new HangingSignItem(TSBlocks.Tech.HOLOGRAPHIC_HANGING_SIGN, TSBlocks.Tech.HOLOGRAPHIC_WALL_HANGING_SIGN, new Item.Settings().maxCount(16));
		public static final Item CAUTION_HOLOGRAPHIC_SIGN = new SignItem(new Item.Settings().maxCount(16), TSBlocks.Tech.CAUTION_HOLOGRAPHIC_SIGN, TSBlocks.Tech.CAUTION_HOLOGRAPHIC_WALL_SIGN);
		public static final Item CAUTION_HOLOGRAPHIC_HANGING_SIGN = new HangingSignItem(TSBlocks.Tech.CAUTION_HOLOGRAPHIC_HANGING_SIGN, TSBlocks.Tech.CAUTION_HOLOGRAPHIC_WALL_HANGING_SIGN, new Item.Settings().maxCount(16));
		public static final Item SENTINEL_HOLOGRAPHIC_SIGN = new SignItem(new Item.Settings().maxCount(16), TSBlocks.Tech.SENTINEL_HOLOGRAPHIC_SIGN, TSBlocks.Tech.SENTINEL_HOLOGRAPHIC_WALL_SIGN);
		public static final Item SENTINEL_HOLOGRAPHIC_HANGING_SIGN = new HangingSignItem(TSBlocks.Tech.SENTINEL_HOLOGRAPHIC_HANGING_SIGN, TSBlocks.Tech.SENTINEL_HOLOGRAPHIC_WALL_HANGING_SIGN, new Item.Settings().maxCount(16));

		public static final Item STARSTEEL_INGOT = new Item(new Item.Settings());
		public static final Item STARSTEEL_HELMET = new ArmorItem(ModArmorMaterials.STARSTEEL, ArmorItem.Type.HELMET,
			new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(SCRAP_METAL_DURABILITY)));
		public static final Item STARSTEEL_CHESTPLATE = new ArmorItem(ModArmorMaterials.STARSTEEL, ArmorItem.Type.CHESTPLATE,
			new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(SCRAP_METAL_DURABILITY)));
		public static final Item STARSTEEL_LEGGINGS = new ArmorItem(ModArmorMaterials.STARSTEEL, ArmorItem.Type.LEGGINGS,
			new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(SCRAP_METAL_DURABILITY)));
		public static final Item STARSTEEL_BOOTS = new ArmorItem(ModArmorMaterials.STARSTEEL, ArmorItem.Type.BOOTS,
			new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(SCRAP_METAL_DURABILITY)));
		public static final Item STARSTEEL_SWORD = new SwordItem(ModToolMaterials.STARSTEEL, new Item.Settings()
			.attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.STARSTEEL, 0, -2.4f)));
		public static final Item STARSTEEL_PHASER = new PhaserItem(TextUtil.PURE, new Item.Settings().component(ModDataComponentTypes.PHASER_MODIFIERS, new PhaserModifiersComponent.Builder()
				.attachment_slots(5).projectile_damage(7).projectile_lifetime(60).projectile_inaccuracy(20).projectile_recoil(3).burst_projectiles(1).burst_cooldown(10)
				.reload_cooldown(50).magazine_size(8).build()));
		public static final Item STARSTEEL_KNIFE = new DaggerItem(ModToolMaterials.STARSTEEL, 2, 0.5f, 0.3f, new Item.Settings());
		public static final Item STARSTEEL_DRILL = new PickaxeItem(ModToolMaterials.STARSTEEL, new Item.Settings()
			.attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.STARSTEEL, 0, -2.8f)));
		public static final Item STARSTEEL_AXE = new AxeItem(ModToolMaterials.STARSTEEL, new Item.Settings()
			.attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterials.STARSTEEL, 3, -3f)));
		public static final Item STARSTEEL_SHOVEL = new ShovelItem(ModToolMaterials.STARSTEEL, new Item.Settings()
			.attributeModifiers(ShovelItem.createAttributeModifiers(ModToolMaterials.STARSTEEL, 1.5f, -3f)));
		public static final Item STARSTEEL_HOE = new HoeItem(ModToolMaterials.STARSTEEL, new Item.Settings()
			.attributeModifiers(HoeItem.createAttributeModifiers(ModToolMaterials.STARSTEEL, 0f, -3f)));

		public static final Item NUCLEAR_INGOT = new Item(new Item.Settings().fireproof());
		public static final Item NUCLEAR_HELMET = new ArmorItem(ModArmorMaterials.NUCLEAR, ArmorItem.Type.HELMET,
			new Item.Settings().fireproof().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(NUCLEAR_DURABILITY)));
		public static final Item NUCLEAR_CHESTPLATE = new ArmorItem(ModArmorMaterials.NUCLEAR, ArmorItem.Type.CHESTPLATE,
			new Item.Settings().fireproof().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(NUCLEAR_DURABILITY)));
		public static final Item NUCLEAR_LEGGINGS = new ArmorItem(ModArmorMaterials.NUCLEAR, ArmorItem.Type.LEGGINGS,
			new Item.Settings().fireproof().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(NUCLEAR_DURABILITY)));
		public static final Item NUCLEAR_BOOTS = new ArmorItem(ModArmorMaterials.NUCLEAR, ArmorItem.Type.BOOTS,
			new Item.Settings().fireproof().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(NUCLEAR_DURABILITY)));
		public static final Item NUCLEAR_SWORD = new SwordItem(ModToolMaterials.NUCLEAR, new Item.Settings().fireproof()
			.attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.NUCLEAR, 3, -2.4f)));
		public static final Item NUCLEAR_PHASER = new PhaserItem(TextUtil.NUCLEAR1, new Item.Settings().component(ModDataComponentTypes.PHASER_MODIFIERS, new PhaserModifiersComponent.Builder()
				.attachment_slots(7).projectile_damage(9).projectile_lifetime(60).projectile_inaccuracy(20).projectile_recoil(4).burst_projectiles(1).burst_cooldown(10)
				.reload_cooldown(50).magazine_size(8).projectile_effects(List.of(new StatusEffectInstance(ModEffects.IRRADIATED, 60))).build()));
		public static final Item NUCLEAR_VIBROKNIFE = new DaggerItem(ModToolMaterials.NUCLEAR, 1, 2.5f, 0.15f,
			new Item.Settings().maxCount(16).fireproof(), new StatusEffectInstance(ModEffects.IRRADIATED, 60, 0));
		public static final Item NUCLEAR_DRILL = new PickaxeItem(ModToolMaterials.NUCLEAR, new Item.Settings().fireproof()
			.attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.NUCLEAR, 0, 0)));
		public static final Item NUCLEAR_AXE = new AxeItem(ModToolMaterials.NUCLEAR, new Item.Settings().fireproof()
			.attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterials.NUCLEAR, 3, -3.1f)));
		public static final Item NUCLEAR_SHOVEL = new ShovelItem(ModToolMaterials.NUCLEAR, new Item.Settings().fireproof()
			.attributeModifiers(ShovelItem.createAttributeModifiers(ModToolMaterials.NUCLEAR, 1.5f, -3f)));
		public static final Item NUCLEAR_HOE = new HoeItem(ModToolMaterials.NUCLEAR, new Item.Settings().fireproof()
			.attributeModifiers(HoeItem.createAttributeModifiers(ModToolMaterials.NUCLEAR, 0f, -3f)));

		public static final Item NANOTECH_CAPSULE = new Item(new Item.Settings().fireproof().food(new FoodComponent.Builder()
			.statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 100, 3), 1.0f).alwaysEdible().build()));
		public static final Item NANOTECH_HELMET = new ArmorItem(ModArmorMaterials.NANOTECH, ArmorItem.Type.HELMET,
			new Item.Settings().fireproof().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(NANOTECH_DURABILITY)));
		public static final Item NANOTECH_CHESTPLATE = new ArmorItem(ModArmorMaterials.NANOTECH, ArmorItem.Type.CHESTPLATE,
			new Item.Settings().fireproof().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(NANOTECH_DURABILITY)));
		public static final Item NANOTECH_LEGGINGS = new ArmorItem(ModArmorMaterials.NANOTECH, ArmorItem.Type.LEGGINGS,
			new Item.Settings().fireproof().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(NANOTECH_DURABILITY)));
		public static final Item NANOTECH_BOOTS = new ArmorItem(ModArmorMaterials.NANOTECH, ArmorItem.Type.BOOTS,
			new Item.Settings().fireproof().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(NANOTECH_DURABILITY)));
		public static final Item NANOTECH_SWORD = new SwordItem(ModToolMaterials.NANOTECH, new Item.Settings().fireproof()
			.attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.NANOTECH, 3, -2.4f)));
		public static final Item NANOTECH_PHASER = new PhaserItem(TextUtil.BLOOD_RED, new Item.Settings().component(ModDataComponentTypes.PHASER_MODIFIERS, new PhaserModifiersComponent.Builder()
				.attachment_slots(9).projectile_damage(12).projectile_lifetime(60).projectile_inaccuracy(10).projectile_recoil(3).burst_projectiles(1).burst_cooldown(10)
				.reload_cooldown(50).magazine_size(12).automatic_reloading(true).build()));
		public static final Item NANOTECH_VIBROKNIFE = new DaggerItem(ModToolMaterials.NANOTECH, 1, 3f, 0.15f, new Item.Settings().fireproof());
		public static final Item NANOTECH_DRILL = new PickaxeItem(ModToolMaterials.NANOTECH, new Item.Settings().fireproof()
			.attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.NANOTECH, 0, 0)));
		public static final Item NANOTECH_AXE = new AxeItem(ModToolMaterials.NANOTECH, new Item.Settings().fireproof()
			.attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterials.NANOTECH, 3, -3.1f)));
		public static final Item NANOTECH_SHOVEL = new ShovelItem(ModToolMaterials.NANOTECH, new Item.Settings().fireproof()
			.attributeModifiers(ShovelItem.createAttributeModifiers(ModToolMaterials.NANOTECH, 1.5f, -3f)));
		public static final Item NANOTECH_HOE = new HoeItem(ModToolMaterials.NANOTECH, new Item.Settings().fireproof()
			.attributeModifiers(HoeItem.createAttributeModifiers(ModToolMaterials.NANOTECH, 0f, -3f)));

		public static final Item PHASE_CORE = new PhaseCoreItem(new Item.Settings().maxCount(1).fireproof()
				.component(ModDataComponentTypes.FUEL, 0).component(ModDataComponentTypes.MAX_FUEL, 128));

		public static final Item ZENITHIUM_CLUSTER = new Item(new Item.Settings().fireproof());
		public static final Item ZENITHIUM_HELMET = new ArmorItem(ModArmorMaterials.ZENITHIUM, ArmorItem.Type.HELMET,
			new Item.Settings().fireproof().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(ZENITHIUM_DURABILITY)));
		public static final Item ZENITHIUM_CHESTPLATE = new ArmorItem(ModArmorMaterials.ZENITHIUM, ArmorItem.Type.CHESTPLATE,
			new Item.Settings().fireproof().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(ZENITHIUM_DURABILITY)));
		public static final Item ZENITHIUM_LEGGINGS = new ArmorItem(ModArmorMaterials.ZENITHIUM, ArmorItem.Type.LEGGINGS,
			new Item.Settings().fireproof().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(ZENITHIUM_DURABILITY)));
		public static final Item ZENITHIUM_BOOTS = new ArmorItem(ModArmorMaterials.ZENITHIUM, ArmorItem.Type.BOOTS,
			new Item.Settings().fireproof().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(ZENITHIUM_DURABILITY)));
		public static final Item ZENITHIUM_SWORD = new SwordItem(ModToolMaterials.ZENITHIUM, new Item.Settings().fireproof()
			.attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.ZENITHIUM, 3, -2.4f)));
		public static final Item ZENITHIUM_PHASER = new PhaserItem(TextUtil.SENTINEL_GOLD1, new Item.Settings().component(ModDataComponentTypes.PHASER_MODIFIERS, new PhaserModifiersComponent.Builder()
				.attachment_slots(9).projectile_damage(16).projectile_lifetime(60).projectile_inaccuracy(2).projectile_recoil(2).burst_projectiles(1).burst_cooldown(10)
				.reload_cooldown(50).magazine_size(16).automatic_reloading(true).build()));
		public static final Item ZENITHIUM_DAGGER = new DaggerItem(ModToolMaterials.ZENITHIUM, 1, 4f, 0, new Item.Settings().fireproof());
		public static final Item ZENITHIUM_PICKAXE = new PickaxeItem(ModToolMaterials.ZENITHIUM, new Item.Settings().fireproof()
			.attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.ZENITHIUM, 1, -2.8f)));
		public static final Item ZENITHIUM_AXE = new AxeItem(ModToolMaterials.ZENITHIUM, new Item.Settings().fireproof()
			.attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterials.ZENITHIUM, 6, -3f)));
		public static final Item ZENITHIUM_SHOVEL = new ShovelItem(ModToolMaterials.ZENITHIUM, new Item.Settings().fireproof()
			.attributeModifiers(ShovelItem.createAttributeModifiers(ModToolMaterials.ZENITHIUM, 1.5f, -3f)));
		public static final Item ZENITHIUM_HOE = new HoeItem(ModToolMaterials.ZENITHIUM, new Item.Settings().fireproof()
			.attributeModifiers(HoeItem.createAttributeModifiers(ModToolMaterials.ZENITHIUM, 0f, -3f)));

		public static final Item LIFEFORM_TRACER = new DistanceTrackerItem(new Item.Settings().maxCount(1));

		public static final Item PLASMA_CELL = new Item(new Item.Settings());
		public static final Item MEAT_PELLET = new Item(new Item.Settings().food(new FoodComponent.Builder().nutrition(1).snack()
			.statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 40), 0.3f).build()));

		public static final Item PALETTE_CLEANSER = new Item(new Item.Settings());

		public static final Item PAINT_PACK = new GachaScrapItem(CustomRarities.RARE, new Item.Settings());
		public static final Item CHROMATIC_LENS = new GachaScrapItem(CustomRarities.RARE, new Item.Settings().maxCount(1));
		public static final Item COUNTERFORCE_DIFFUSER = new GachaScrapItem(CustomRarities.UNCOMMON, new Item.Settings().maxCount(1)
			.component(ModDataComponentTypes.PHASER_MODIFIERS, new PhaserModifiersComponent.Builder().burst_projectiles(1).projectile_damage(-2)
				.projectile_recoil(5).projectile_inaccuracy(20).isAttachment().build()));
		public static final Item PHASE_ASSIMILATOR = new GachaScrapItem(CustomRarities.UNCOMMON, new Item.Settings().maxCount(1)
			.component(ModDataComponentTypes.PHASER_MODIFIERS, new PhaserModifiersComponent.Builder().burst_projectiles(-2).projectile_damage(3)
				.projectile_recoil(2).projectile_inaccuracy(-25).isAttachment().build()));
		public static final Item AUXILIARY_PLASMA_CHAMBER = new GachaScrapItem(CustomRarities.UNCOMMON, new Item.Settings().maxCount(1)
			.component(ModDataComponentTypes.PHASER_MODIFIERS, new PhaserModifiersComponent.Builder().burst_cooldown(-1).reload_cooldown(-2)
				.magazine_size(2).isAttachment().build()));
		public static final Item ADVANCED_BREECH_MECHANISM = new GachaScrapItem(CustomRarities.UNCOMMON, new Item.Settings().maxCount(1)
			.component(ModDataComponentTypes.PHASER_MODIFIERS, new PhaserModifiersComponent.Builder().burst_cooldown(-4)
				.reload_cooldown(-10).projectile_lifetime(10).isAttachment().build()));
		public static final Item SMOKE_CAPSULE = new GachaScrapItem(CustomRarities.UNCOMMON, new Item.Settings().maxCount(1)
			.component(ModDataComponentTypes.PHASER_MODIFIERS, new PhaserModifiersComponent.Builder()
				.projectile_effects(List.of(new StatusEffectInstance(StatusEffects.BLINDNESS, 40))).isAttachment().build()));
		public static final Item POISON_CAPSULE = new GachaScrapItem(CustomRarities.RARE, new Item.Settings().maxCount(1)
			.component(ModDataComponentTypes.PHASER_MODIFIERS, new PhaserModifiersComponent.Builder()
				.projectile_effects(List.of(new StatusEffectInstance(StatusEffects.POISON, 40))).isAttachment().build()));
		public static final Item WITHER_CAPSULE = new GachaScrapItem(CustomRarities.RARE, new Item.Settings().maxCount(1)
			.component(ModDataComponentTypes.PHASER_MODIFIERS, new PhaserModifiersComponent.Builder()
				.projectile_effects(List.of(new StatusEffectInstance(StatusEffects.WITHER, 40, 1))).isAttachment().build()));
		public static final Item REGENERATION_CAPSULE = new GachaScrapItem(CustomRarities.RARE, new Item.Settings().maxCount(1)
			.component(ModDataComponentTypes.PHASER_MODIFIERS, new PhaserModifiersComponent.Builder()
				.projectile_effects(List.of(new StatusEffectInstance(StatusEffects.REGENERATION, 40, 1))).isAttachment().build()));

		public static final Item VILE_SPITTER = new LivingPhaserItem(TextUtil.FLESH_PUS, new Item.Settings().component(ModDataComponentTypes.PHASER_MODIFIERS, new PhaserModifiersComponent.Builder()
				.attachment_slots(5).magazine_size(7).projectile_damage(8).projectile_lifetime(48).projectile_inaccuracy(40).projectile_recoil(3).burst_projectiles(1).burst_cooldown(10)
				.reload_cooldown(60).projectile_effects(List.of(new StatusEffectInstance(ModEffects.INFESTED, 60))).build()));

		public static final Item HARD_LIGHT_PROJECTOR = new HardLightProjectorItem(TSBlocks.Tech.HARD_LIGHT, TextUtil.SENTINEL_AQUA1,
				new Item.Settings().maxCount(1).component(ModDataComponentTypes.FUEL, 0).component(ModDataComponentTypes.MAX_FUEL, 128));
		public static final Item CAUTION_HARD_LIGHT_PROJECTOR = new HardLightProjectorItem(TSBlocks.Tech.CAUTION_HARD_LIGHT, TextUtil.SENTINEL_GOLD1,
				new Item.Settings().maxCount(1).component(ModDataComponentTypes.FUEL, 0).component(ModDataComponentTypes.MAX_FUEL, 128));
		public static final Item SENTINEL_HARD_LIGHT_PROJECTOR = new HardLightProjectorItem(TSBlocks.Tech.SENTINEL_HARD_LIGHT, TextUtil.SENTINEL_CRIMSON1,
				new Item.Settings().maxCount(1).component(ModDataComponentTypes.FUEL, 0).component(ModDataComponentTypes.MAX_FUEL, 128));
		public static final Item NUCLEAR_ROCKET = new NuclearRocketItem(new Item.Settings().maxCount(1).fireproof()
				.component(ModDataComponentTypes.FUEL, 0).component(ModDataComponentTypes.MAX_FUEL, 128));

		public static final Item EMPTY_CAN = new Item(new Item.Settings());
		public static final Item BEETROOT_SOUP_CAN = new CannedItem(new Item.Settings().maxCount(16).food(new FoodComponent.Builder().snack().nutrition(3).saturationModifier(0.6f).build()));
		public static final Item MUSHROOM_STEW_CAN = new CannedItem(new Item.Settings().maxCount(16).food(new FoodComponent.Builder().snack().nutrition(3).saturationModifier(0.6f).build()));
		public static final Item RABBIT_STEW_CAN = new CannedItem(new Item.Settings().maxCount(16).food(new FoodComponent.Builder().snack().nutrition(5).saturationModifier(0.6f).build()));
		public static final Item MILK_CAN = new CannedMilkItem(new Item.Settings().maxCount(16).food(new FoodComponent.Builder()
			.statusEffect(new StatusEffectInstance(ModEffects.FORTIFIED, 360), 0.2f).alwaysEdible().build()));
		public static final Item COLA_ORANGE = new CannedItem(new Item.Settings().maxCount(16).food(new FoodComponent.Builder().snack().nutrition(2).saturationModifier(0.3f).build()));
		public static final Item COLA_GREEN = new CannedItem(new Item.Settings().maxCount(16).food(new FoodComponent.Builder().snack().nutrition(2).saturationModifier(0.3f).build()));
		public static final Item COLA_CYAN = new CannedItem(new Item.Settings().maxCount(16).food(new FoodComponent.Builder().snack().nutrition(2).saturationModifier(0.3f).build()));

		static RegistryKey<JukeboxSong> ASSASSINATION_UPLOAD = RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Identifier.of("trevorssentinels", "assassination_upload")); //ModSounds.ASSASSINATION_UPLOAD, 190 seconds, 7 output
		static RegistryKey<JukeboxSong> ODE_TO_TRANQUILITY = RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Identifier.of("trevorssentinels", "ode_to_tranquility")); //ModSounds.ODE_TO_TRANQUILITY, 80 seconds, 7 output
		static RegistryKey<JukeboxSong> LAPSE_IN_JUDGEMENT = RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Identifier.of("trevorssentinels", "lapse_in_judgement")); //ModSounds.LAPSE_IN_JUDGEMENT, 142 seconds, 7 output
		static RegistryKey<JukeboxSong> RECITAL = RegistryKey.of(RegistryKeys.JUKEBOX_SONG, Identifier.of("trevorssentinels", "recital")); //ModSounds.RECITAL, 114 seconds, 7 output

		public static final Item MUSIC_DISC_ASSASSINATION_UPLOAD = new Item(new Item.Settings().maxCount(1).rarity(Rarity.RARE).jukeboxPlayable(ASSASSINATION_UPLOAD));
		public static final Item MUSIC_DISC_ODE_TO_TRANQUILITY = new Item(new Item.Settings().maxCount(1).rarity(Rarity.RARE).jukeboxPlayable(ODE_TO_TRANQUILITY));
		public static final Item MUSIC_DISC_LAPSE_IN_JUDGEMENT = new Item(new Item.Settings().maxCount(1).rarity(Rarity.RARE).jukeboxPlayable(LAPSE_IN_JUDGEMENT));
		public static final Item MUSIC_DISC_RECITAL = new Item(new Item.Settings().maxCount(1).rarity(Rarity.RARE).jukeboxPlayable(RECITAL));
	}

	@RegistryNamespace("trevorssentinels") public static class Magic implements ItemRegistryContainer {
		public static final Item ENCHANTED_LEAF = new Item(new Item.Settings());
		public static final Item DRYADIC_HELMET = new ArmorItem(ModArmorMaterials.DRYADIC, ArmorItem.Type.HELMET,
			new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(DRYADIC_DURABILITY)));
		public static final Item DRYADIC_CHESTPLATE = new ArmorItem(ModArmorMaterials.DRYADIC, ArmorItem.Type.CHESTPLATE,
			new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(DRYADIC_DURABILITY)));
		public static final Item DRYADIC_LEGGINGS = new ArmorItem(ModArmorMaterials.DRYADIC, ArmorItem.Type.LEGGINGS,
			new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(DRYADIC_DURABILITY)));
		public static final Item DRYADIC_BOOTS = new ArmorItem(ModArmorMaterials.DRYADIC, ArmorItem.Type.BOOTS,
			new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(DRYADIC_DURABILITY)));
		public static final Item DRYADIC_SWORD = new SwordItem(ModToolMaterials.DRYADIC, new Item.Settings()
			.attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.DRYADIC, 3, -2.4f)));
		public static final Item DRYADIC_DAGGER = new DaggerItem(ModToolMaterials.DRYADIC, 1, 0, 0.85f, new Item.Settings());

		public static final Item ROSE_GOLD_INGOT = new Item(new Item.Settings());
		public static final Item ROSE_GOLD_HELMET = new ArmorItem(ModArmorMaterials.ROSE_GOLD, ArmorItem.Type.HELMET,
			new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(ROSE_GOLD_DURABILITY)));
		public static final Item ROSE_GOLD_CHESTPLATE = new ArmorItem(ModArmorMaterials.ROSE_GOLD, ArmorItem.Type.CHESTPLATE,
			new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(ROSE_GOLD_DURABILITY)));
		public static final Item ROSE_GOLD_LEGGINGS = new ArmorItem(ModArmorMaterials.ROSE_GOLD, ArmorItem.Type.LEGGINGS,
			new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(ROSE_GOLD_DURABILITY)));
		public static final Item ROSE_GOLD_BOOTS = new ArmorItem(ModArmorMaterials.ROSE_GOLD, ArmorItem.Type.BOOTS,
			new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(ROSE_GOLD_DURABILITY)));
		public static final Item ROSE_GOLD_SWORD = new SwordItem(ModToolMaterials.ROSE_GOLD, new Item.Settings()
			.attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.ROSE_GOLD, 3, -2.4f)));
		public static final Item ROSE_GOLD_DAGGER = new DaggerItem(ModToolMaterials.ROSE_GOLD, 1, 0, 0.85f, new Item.Settings());
		public static final Item ROSE_GOLD_PICKAXE = new PickaxeItem(ModToolMaterials.ROSE_GOLD, new Item.Settings()
			.attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.ROSE_GOLD, 1, -2.8f)));
		public static final Item ROSE_GOLD_BATTLEAXE = new AxeItem(ModToolMaterials.ROSE_GOLD, new Item.Settings()
			.attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterials.ROSE_GOLD, 3, -3f)));
		public static final Item ROSE_GOLD_SHOVEL = new ShovelItem(ModToolMaterials.ROSE_GOLD, new Item.Settings()
			.attributeModifiers(ShovelItem.createAttributeModifiers(ModToolMaterials.ROSE_GOLD, 1.5f, -3f)));
		public static final Item ROSE_GOLD_HOE = new HoeItem(ModToolMaterials.ROSE_GOLD, new Item.Settings()
			.attributeModifiers(HoeItem.createAttributeModifiers(ModToolMaterials.ROSE_GOLD, 0f, -3f)));

		public static final Item DRUIDIC_GEM = new Item(new Item.Settings());
		public static final Item DRUIDIC_HELMET = new ArmorItem(ModArmorMaterials.DRUIDIC, ArmorItem.Type.HELMET,
			new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(DRUIDIC_DURABILITY)));
		public static final Item DRUIDIC_CHESTPLATE = new ArmorItem(ModArmorMaterials.DRUIDIC, ArmorItem.Type.CHESTPLATE,
			new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(DRUIDIC_DURABILITY)));
		public static final Item DRUIDIC_LEGGINGS = new ArmorItem(ModArmorMaterials.DRUIDIC, ArmorItem.Type.LEGGINGS,
			new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(DRUIDIC_DURABILITY)));
		public static final Item DRUIDIC_BOOTS = new ArmorItem(ModArmorMaterials.DRUIDIC, ArmorItem.Type.BOOTS,
			new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(DRUIDIC_DURABILITY)));
		public static final Item DRUIDIC_SWORD = new SwordItem(ModToolMaterials.DRUIDIC, new Item.Settings()
			.attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.DRUIDIC, 3, -2.4f)));
		public static final Item DRUIDIC_DAGGER = new DaggerItem(ModToolMaterials.DRUIDIC, 1, 0, 0.85f, new Item.Settings());
		public static final Item DRUIDIC_BOW = new BowItem(new Item.Settings());

		public static final Item IMPERIAL_ALLOY_INGOT = new Item(new Item.Settings());
		public static final Item IMPERIAL_HELMET = new ArmorItem(ModArmorMaterials.IMPERIAL, ArmorItem.Type.HELMET,
			new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(IMPERIAL_DURABILITY)));
		public static final Item IMPERIAL_CHESTPLATE = new ArmorItem(ModArmorMaterials.IMPERIAL, ArmorItem.Type.CHESTPLATE,
			new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(IMPERIAL_DURABILITY)));
		public static final Item IMPERIAL_LEGGINGS = new ArmorItem(ModArmorMaterials.IMPERIAL, ArmorItem.Type.LEGGINGS,
			new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(IMPERIAL_DURABILITY)));
		public static final Item IMPERIAL_BOOTS = new ArmorItem(ModArmorMaterials.IMPERIAL, ArmorItem.Type.BOOTS,
			new Item.Settings().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(IMPERIAL_DURABILITY)));
		public static final Item IMPERIAL_SWORD = new SwordItem(ModToolMaterials.IMPERIAL_ALLOY, new Item.Settings()
			.attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.IMPERIAL_ALLOY, 3, -2.4f)));
		public static final Item IMPERIAL_GLADIUS = new DaggerItem(ModToolMaterials.IMPERIAL_ALLOY, 4, 2f, 0.35f, new Item.Settings());
		public static final Item IMPERIAL_PICKAXE = new PickaxeItem(ModToolMaterials.IMPERIAL_ALLOY, new Item.Settings()
			.attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.IMPERIAL_ALLOY, 0, 0)));
		public static final Item IMPERIAL_BATTLEAXE = new AxeItem(ModToolMaterials.IMPERIAL_ALLOY, new Item.Settings()
			.attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterials.IMPERIAL_ALLOY, 3, -2.8f)));
		public static final Item IMPERIAL_SHOVEL = new ShovelItem(ModToolMaterials.IMPERIAL_ALLOY, new Item.Settings()
			.attributeModifiers(ShovelItem.createAttributeModifiers(ModToolMaterials.IMPERIAL_ALLOY, 1.5f, -3f)));
		public static final Item IMPERIAL_HOE = new HoeItem(ModToolMaterials.IMPERIAL_ALLOY, new Item.Settings()
			.attributeModifiers(HoeItem.createAttributeModifiers(ModToolMaterials.IMPERIAL_ALLOY, 0f, -3f)));

		public static final Item UNHOLY_CORE = new Item(new Item.Settings().fireproof()
				.component(ModDataComponentTypes.PORTKEY, PortkeyComponent.DEFAULT));
		public static final Item UNHOLY_INGOT = new Item(new Item.Settings().fireproof());
		public static final Item UNHOLY_HELMET = new ArmorItem(ModArmorMaterials.UNHOLY, ArmorItem.Type.HELMET,
			new Item.Settings().fireproof().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(UNHOLY_DURABILITY)));
		public static final Item UNHOLY_CHESTPLATE = new ArmorItem(ModArmorMaterials.UNHOLY, ArmorItem.Type.CHESTPLATE,
			new Item.Settings().fireproof().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(UNHOLY_DURABILITY)));
		public static final Item UNHOLY_LEGGINGS = new ArmorItem(ModArmorMaterials.UNHOLY, ArmorItem.Type.LEGGINGS,
			new Item.Settings().fireproof().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(UNHOLY_DURABILITY)));
		public static final Item UNHOLY_BOOTS = new ArmorItem(ModArmorMaterials.UNHOLY, ArmorItem.Type.BOOTS,
			new Item.Settings().fireproof().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(UNHOLY_DURABILITY)));
		public static final Item UNHOLY_SWORD = new SwordItem(ModToolMaterials.UNHOLY, new Item.Settings().fireproof()
			.attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.UNHOLY, 3, -2.4f)));
		public static final Item UNHOLY_DAGGER = new DaggerItem(ModToolMaterials.UNHOLY, 6, 2.5f, 0.15f, new Item.Settings().fireproof());
		public static final Item UNHOLY_PICKAXE = new PickaxeItem(ModToolMaterials.UNHOLY, new Item.Settings().fireproof()
			.attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterials.UNHOLY, 0, 0)));
		public static final Item UNHOLY_BATTLEAXE = new AxeItem(ModToolMaterials.UNHOLY, new Item.Settings().fireproof()
			.attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterials.UNHOLY, 3, -2.8f)));
		public static final Item UNHOLY_SHOVEL = new ShovelItem(ModToolMaterials.UNHOLY, new Item.Settings().fireproof()
			.attributeModifiers(ShovelItem.createAttributeModifiers(ModToolMaterials.UNHOLY, 1.5f, -3f)));
		public static final Item UNHOLY_HOE = new HoeItem(ModToolMaterials.UNHOLY, new Item.Settings().fireproof()
			.attributeModifiers(HoeItem.createAttributeModifiers(ModToolMaterials.UNHOLY, 0f, -3f)));

		public static final Item ARMA_DEI_HELMET = new ArmorItem(ModArmorMaterials.ARMA_DEI, ArmorItem.Type.HELMET,
			new Item.Settings().fireproof().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(ARMA_DEI_DURABILITY)));
		public static final Item ARMA_DEI_CHESTPLATE = new ArmorItem(ModArmorMaterials.ARMA_DEI, ArmorItem.Type.CHESTPLATE,
			new Item.Settings().fireproof().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(ARMA_DEI_DURABILITY)));
		public static final Item ARMA_DEI_LEGGINGS = new ArmorItem(ModArmorMaterials.ARMA_DEI, ArmorItem.Type.LEGGINGS,
			new Item.Settings().fireproof().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(ARMA_DEI_DURABILITY)));
		public static final Item ARMA_DEI_BOOTS = new ArmorItem(ModArmorMaterials.ARMA_DEI, ArmorItem.Type.BOOTS,
			new Item.Settings().fireproof().maxDamage(ArmorItem.Type.HELMET.getMaxDamage(ARMA_DEI_DURABILITY)));
		public static final Item ARMA_DEI_SWORD = new SwordItem(ModToolMaterials.ARMA_DEI, new Item.Settings().fireproof()
			.attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.ARMA_DEI, 3, -2.4f)));
		public static final Item ARMA_DEI_DAGGER = new DaggerItem(ModToolMaterials.ARMA_DEI, 6, 2.5f, 0.15f, new Item.Settings().fireproof());
		public static final Item ARMA_DEI_PICKAXE = new PickaxeItem(ModToolMaterials.ARMA_DEI, new Item.Settings().fireproof()
			.attributeModifiers(PickaxeItem.createAttributeModifiers(ModToolMaterials.ARMA_DEI, 0, 0)));
		public static final Item ARMA_DEI_BATTLEAXE = new AxeItem(ModToolMaterials.ARMA_DEI, new Item.Settings().fireproof()
			.attributeModifiers(AxeItem.createAttributeModifiers(ModToolMaterials.ARMA_DEI, 3, -2.8f)));
		public static final Item ARMA_DEI_SHOVEL = new ShovelItem(ModToolMaterials.ARMA_DEI, new Item.Settings().fireproof()
			.attributeModifiers(ShovelItem.createAttributeModifiers(ModToolMaterials.ARMA_DEI, 1.5f, -3f)));
		public static final Item ARMA_DEI_HOE = new HoeItem(ModToolMaterials.ARMA_DEI, new Item.Settings().fireproof()
			.attributeModifiers(HoeItem.createAttributeModifiers(ModToolMaterials.ARMA_DEI, 0, -3f)));

		public static final Item MACABRE_HELMET = new ArmorItem(ModArmorMaterials.ZENITHIUM, ArmorItem.Type.HELMET, new Item.Settings());
		public static final Item MACABRE_HARNESS = new ArmorItem(ModArmorMaterials.ZENITHIUM, ArmorItem.Type.CHESTPLATE, new Item.Settings());
		public static final Item MACABRE_LOINCLOTH = new ArmorItem(ModArmorMaterials.ZENITHIUM, ArmorItem.Type.CHESTPLATE, new Item.Settings());
		public static final Item MACABRE_SANDALS = new ArmorItem(ModArmorMaterials.ZENITHIUM, ArmorItem.Type.CHESTPLATE, new Item.Settings());

		public static final Item COPPER_GLADIUS = new NewDaggerItem(ModToolMaterials.COPPER, new Item.Settings()
			.attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.COPPER, 2, 2f)));
		public static final Item ICICLE_DAGGER = new NewDaggerItem(ModToolMaterials.COPPER, new Item.Settings()
			.attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.COPPER, 1, 2f)));
			//.component(DataComponentTypes.POTION_CONTENTS, new PotionContentsComponent(new StatusEffectInstance(StatusEffects.SLOWNESS, 20, 1)));

		public static final Item PALE_BERRIES = new BlockConverterItem(new Item.Settings(), SoundEvents.ITEM_HONEYCOMB_WAX_ON, 1.0f, 1.0f,
			new ImmutableMap.Builder<Block, Block>()
			.put(Blocks.END_STONE, TSBlocks.Magic.IMPIOUS_END_STONE)
			.put(Blocks.END_STONE_BRICKS, TSBlocks.Magic.IMPIOUS_END_STONE_BRICKS)
			.put(Blocks.END_STONE_BRICK_SLAB, Blocks.STONE_BRICK_SLAB)
			.put(Blocks.END_STONE_BRICK_STAIRS, Blocks.STONE_BRICK_STAIRS)
			.put(Blocks.END_STONE_BRICK_WALL, Blocks.STONE_BRICK_WALL)
			.put(TSBlocks.Magic.CRACKED_END_STONE_BRICKS, TSBlocks.Magic.CRACKED_IMPIOUS_END_STONE_BRICKS)
			.put(TSBlocks.Magic.CHISELED_END_STONE_BRICKS, TSBlocks.Magic.CHISELED_IMPIOUS_END_STONE_BRICKS)
			.put(TSBlocks.Magic.END_STONE_BRICK_COLUMN, TSBlocks.Magic.IMPIOUS_END_STONE_BRICK_COLUMN)
			.build());

		public static final Item RESISTANCE_ITEM = new InfiniteEffectItem(new Item.Settings().maxCount(1),
			new StatusEffectInstance(StatusEffects.RESISTANCE, 200, 0, false, false));

		public static final Item FIRE_RESISTANCE_ITEM = new InfiniteEffectItem(new Item.Settings().maxCount(1).fireproof(),
			new StatusEffectInstance(StatusEffects.RESISTANCE, 200, 1, false, false),
			new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 200, 0, false, false));

		public static final Item JUMP_BOOST_ITEM = new InfiniteEffectItem(new Item.Settings().maxCount(1),
			new StatusEffectInstance(StatusEffects.JUMP_BOOST, 200, 0, false, false));

		public static final Item STRENGTH_ITEM = new InfiniteEffectItem(new Item.Settings().maxCount(1),
			new StatusEffectInstance(StatusEffects.STRENGTH, 200, 0, false, false));

		public static final Item WEAKNESS_ITEM = new InfiniteEffectItem(new Item.Settings().maxCount(1),
			new StatusEffectInstance(StatusEffects.WEAKNESS, 200, 0, false, false));

		public static final Item REGENERATION_ITEM = new InfiniteEffectItem(new Item.Settings().maxCount(1),
			new StatusEffectInstance(StatusEffects.REGENERATION, 200, 0, false, false));

		public static final Item WATER_BREATHING_ITEM = new InfiniteEffectItem(new Item.Settings().maxCount(1),
			new StatusEffectInstance(StatusEffects.WATER_BREATHING, 200, 0, false, false));

		public static final Item INVISIBILITY_ITEM = new InfiniteEffectItem(new Item.Settings().maxCount(1),
			new StatusEffectInstance(StatusEffects.INVISIBILITY, 200, 0, false, false));

		public static final Item DOLPHINS_GRACE_ITEM = new InfiniteEffectItem(new Item.Settings().maxCount(1),
			new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE, 200, 0, false, false));

		public static final Item CONDUIT_POWER_ITEM = new InfiniteEffectItem(new Item.Settings().maxCount(1),
			new StatusEffectInstance(StatusEffects.CONDUIT_POWER, 200, 0, false, false));

		public static final Item NIGHT_VISION_ITEM = new InfiniteEffectItem(new Item.Settings().maxCount(1),
			new StatusEffectInstance(StatusEffects.WEAKNESS, 200, 0, false, false));

		public static final Item HERO_OF_THE_VILLAGE_ITEM = new InfiniteEffectItem(new Item.Settings().maxCount(1),
			new StatusEffectInstance(StatusEffects.HERO_OF_THE_VILLAGE, 200, 0, false, false));

		public static final Item PALADINS_BADGE = new InfiniteEffectItem(new Item.Settings().maxCount(1),
			new StatusEffectInstance(StatusEffects.RESISTANCE, 200, 0, false, false),
			new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 200, 0, false, false),
			new StatusEffectInstance(StatusEffects.STRENGTH, 200, 0, false, false));

		public static final Item CYBERNETIC_STOMACH = new InfiniteEffectItem(new Item.Settings().maxCount(1),
			new StatusEffectInstance(ModEffects.WELL_FED, 200, 0, false, false));

		public static final Item ONE_PENCE = new InfiniteEffectItem(new Item.Settings().maxCount(1),
			new StatusEffectInstance(StatusEffects.LUCK, 200, 9, false, false));

		public static final Item EYE_OF_RUIN = new Item(new Item.Settings());
		public static final Item DUBIOUS_BACON = new Item(new Item.Settings().food(new FoodComponent.Builder().nutrition(3).saturationModifier(0.4f)
			.statusEffect(new StatusEffectInstance(ModEffects.INFESTED, 200, 0), 0.1f).build()));
		public static final Item FLESHY_PUSTULE = new BlockItem(TSBlocks.Magic.FLESHY_PUSTULE, new Item.Settings().food(new FoodComponent.Builder().nutrition(1).saturationModifier(0.4f)
				.statusEffect(new StatusEffectInstance(StatusEffects.INFESTED, 44, 0), 0.3f).build()));

		public static final Item PALE_SIGN = new SignItem(new Item.Settings().maxCount(16), TSBlocks.Trees.PALE_SIGN, TSBlocks.Trees.PALE_WALL_SIGN);
		public static final Item PALE_HANGING_SIGN = new HangingSignItem(TSBlocks.Trees.PALE_HANGING_SIGN, TSBlocks.Trees.PALE_WALL_HANGING_SIGN, new Item.Settings().maxCount(16));
		public static final Item PALE_BOAT = new BoatItem(false, BoatEntity.Type.BIRCH, new Item.Settings().maxCount(1));
		public static final Item PALE_CHEST_BOAT = new BoatItem(true, BoatEntity.Type.BIRCH, new Item.Settings().maxCount(1));

		public static final Item CHARRED_SIGN = new SignItem(new Item.Settings().maxCount(16), TSBlocks.Trees.CHARRED_SIGN, TSBlocks.Trees.CHARRED_WALL_SIGN);
		public static final Item CHARRED_HANGING_SIGN = new HangingSignItem(TSBlocks.Trees.CHARRED_HANGING_SIGN, TSBlocks.Trees.CHARRED_WALL_HANGING_SIGN, new Item.Settings().maxCount(16));
		public static final Item CHARRED_BOAT = new BoatItem(false, BoatEntity.Type.OAK, new Item.Settings().maxCount(1));
		public static final Item CHARRED_CHEST_BOAT = new BoatItem(true, BoatEntity.Type.OAK, new Item.Settings().maxCount(1));

		public static final Item MIDAS_SIGN = new SignItem(new Item.Settings().maxCount(16), TSBlocks.Trees.MIDAS_SIGN, TSBlocks.Trees.MIDAS_WALL_SIGN);
		public static final Item MIDAS_HANGING_SIGN = new HangingSignItem(TSBlocks.Trees.MIDAS_HANGING_SIGN, TSBlocks.Trees.MIDAS_WALL_HANGING_SIGN, new Item.Settings().maxCount(16));
		public static final Item MIDAS_BOAT =  new BoatItem(false, BoatEntity.Type.DARK_OAK, new Item.Settings().maxCount(1));
		public static final Item MIDAS_CHEST_BOAT = new BoatItem(true, BoatEntity.Type.DARK_OAK, new Item.Settings().maxCount(1));

		public static final Item VIRIDIAN_SIGN = new SignItem(new Item.Settings().maxCount(16), TSBlocks.Trees.VIRIDIAN_SIGN, TSBlocks.Trees.VIRIDIAN_WALL_SIGN);
		public static final Item VIRIDIAN_HANGING_SIGN = new HangingSignItem(TSBlocks.Trees.VIRIDIAN_HANGING_SIGN, TSBlocks.Trees.VIRIDIAN_WALL_HANGING_SIGN, new Item.Settings().maxCount(16));
		public static final Item VIRIDIAN_BOAT = new BoatItem(false, BoatEntity.Type.JUNGLE, new Item.Settings().maxCount(1));
		public static final Item VIRIDIAN_CHEST_BOAT = new BoatItem(true, BoatEntity.Type.JUNGLE, new Item.Settings().maxCount(1));

		public static final Item CERULII_SIGN = new SignItem(new Item.Settings().maxCount(16), TSBlocks.Trees.CERULII_SIGN, TSBlocks.Trees.CERULII_WALL_SIGN);
		public static final Item CERULII_HANGING_SIGN = new HangingSignItem(TSBlocks.Trees.CERULII_HANGING_SIGN, TSBlocks.Trees.CERULII_WALL_HANGING_SIGN, new Item.Settings().maxCount(16));
		public static final Item CERULII_BOAT = new BoatItem(false, BoatEntity.Type.JUNGLE, new Item.Settings().maxCount(1));
		public static final Item CERULII_CHEST_BOAT = new BoatItem(true, BoatEntity.Type.JUNGLE, new Item.Settings().maxCount(1));
	}

	@RegistryNamespace("trevorssentinels") public static class Beta implements ItemRegistryContainer {
		public static final Item PAPPYM_BLADE = new PappyMSwordItem(ModToolMaterials.ZENITHIUM, new Item.Settings().fireproof().maxDamage(1200)
			.attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.ZENITHIUM, 0, -2.5f)));
		public static final Item PAPPYD_BLADE = new PappyDSwordItem(ModToolMaterials.ZENITHIUM, new Item.Settings().fireproof()
			.attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.ZENITHIUM, 0, 02.5f)));
		public static final Item THANATU_BLADE = new ThanatuBladeItem(ModToolMaterials.ZENITHIUM, new Item.Settings().fireproof()
			.attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.ZENITHIUM, 3, -2.8f)));
		public static final Item SKYLAR_BLADE = new SkylarBladeItem(ModToolMaterials.ZENITHIUM,new Item.Settings().fireproof()
			.attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.ZENITHIUM, 0, -2.4f)));
		public static final Item LILITH_BLADE = new LilithBladeItem(ModToolMaterials.ZENITHIUM, new Item.Settings().fireproof()
			.attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.ZENITHIUM, -5, -2.4f)));
		public static final Item KINGS_BLADE = new KingsBladeItem(ModToolMaterials.ZENITHIUM, new Item.Settings().fireproof()
			.attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.ZENITHIUM, 3, -2.4f)));

		public static final Item SCARA_SHEARS = new SwordItem(ModToolMaterials.ZENITHIUM, new Item.Settings().fireproof()
			.attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.ZENITHIUM, 3, -2.4f)));
		public static final Item MASTER_SWORD = new SwordItem(ModToolMaterials.ZENITHIUM, new Item.Settings().fireproof()
			.attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.ZENITHIUM, 3, -2.4f)));
		public static final Item DREAMBORNE_SLASHER = new SwordItem(ModToolMaterials.ZENITHIUM, new Item.Settings().fireproof()
			.attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.ZENITHIUM, 3, -2.4f)));

		public static final Item BLACKSMITHS_WELDING_MASK = new ArmorItem(ModArmorMaterials.ZENITHIUM, ArmorItem.Type.HELMET, new Item.Settings());
		public static final Item MAD_SCIENTISTS_LAB_COAT = new ArmorItem(ModArmorMaterials.ZENITHIUM, ArmorItem.Type.CHESTPLATE, new Item.Settings());

		public static final Item ASH = new Item(new Item.Settings().food(new FoodComponent.Builder().nutrition(-1).saturationModifier(0.2f).build()));
		public static final Item TRANQUIL_DUST = new Item(new Item.Settings().food(new FoodComponent.Builder().nutrition(1).saturationModifier(0.2f)
			.statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 200, 0), 0.6f).build()));
		public static final Item SALT = new Item(new Item.Settings().food(new FoodComponent.Builder().nutrition(1).saturationModifier(-0.5f).build()));
		public static final Item REDSTONE_CRYSTAL = new Item(new Item.Settings());
		public static final Item BLOOD_DIAMOND = new Item(new Item.Settings().fireproof());

		public static final Item TRANQUIL_ROSE = new BlockItem(TSBlocks.Plants.TRANQUIL_ROSE, new Item.Settings().food(new FoodComponent.Builder().nutrition(1).saturationModifier(0.4f)
			.statusEffect(new StatusEffectInstance(StatusEffects.DARKNESS, 30, 0), 0.3f).build()));
		public static final Item SKULLWEED = new BlockItem(TSBlocks.Plants.SKULLWEED, new Item.Settings().food(new FoodComponent.Builder().nutrition(1).saturationModifier(0.4f)
			.statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 30, 0), 0.3f).build()));

		public static final Item FRIED_EGG = new Item(new Item.Settings().food(new FoodComponent.Builder().nutrition(2).saturationModifier(0.3f).build()));
		public static final Item BANANA = new Item(new Item.Settings().food(new FoodComponent.Builder().nutrition(4).saturationModifier(0.5f)
			.statusEffect(new StatusEffectInstance(ModEffects.IRRADIATED, 40, 1), 0.4f).build()));
		public static final Item BANANA_BREAD = new Item(new Item.Settings().food(new FoodComponent.Builder().nutrition(4).saturationModifier(0.5f)
			.statusEffect(new StatusEffectInstance(ModEffects.WELL_FED, 200, 1), 0.4f).build()));
		public static final Item RICE_SEEDS = new AliasedBlockItem(TSBlocks.Plants.RICE_PLANT, new Item.Settings()
			.food(new FoodComponent.Builder().nutrition(1).saturationModifier(0.2f).build()));
		public static final Item RICE_CAKE = new Item(new Item.Settings().food(new FoodComponent.Builder().nutrition(3).saturationModifier(0.6f).build()));
		public static final Item SUSHI_ROLL = new Item(new Item.Settings().food(new FoodComponent.Builder().nutrition(8).saturationModifier(0.8f).build()));
		public static final Item TORTILLA = new Item(new Item.Settings().food(new FoodComponent.Builder().nutrition(6).saturationModifier(0.8f).build()));
		public static final Item BURRITO = new Item(new Item.Settings().food(new FoodComponent.Builder().nutrition(12).saturationModifier(2.4f).build()));
		public static final Item PEARFRUIT = new Item(new Item.Settings().food(new FoodComponent.Builder().nutrition(4).saturationModifier(1.0f)
			.statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 2400, 1), 1.0f)
			.statusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 2400, 1), 1.0f).alwaysEdible().build()));
		public static final Item MIDAS_FRUIT = new Item(new Item.Settings().food(new FoodComponent.Builder().nutrition(2).saturationModifier(1.0f)
			.statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 120, 1), 1.0f).alwaysEdible().build()));
		public static final Item APPLE_JUICE = new Item(new Item.Settings().food(new FoodComponent.Builder().nutrition(1).saturationModifier(0.1f).build()));
		public static final Item GOLDEN_APPLE_JUICE = new Item(new Item.Settings()
			.food(new FoodComponent.Builder().nutrition(1).saturationModifier(0.1f).build()));
		public static final Item PEARFRUIT_JUICE = new Item(new Item.Settings()
			.food(new FoodComponent.Builder().nutrition(1).saturationModifier(0.1f).build()));
		public static final Item SWEET_BERRY_JUICE = new Item(new Item.Settings()
			.food(new FoodComponent.Builder().nutrition(3).saturationModifier(1.0f).build()));
		public static final Item BROWNIE = new Item(new Item.Settings().food(new FoodComponent.Builder().nutrition(6).saturationModifier(0.5f).build()));
		public static final Item CHORUS_COBBLER = new Item(new Item.Settings().food(new FoodComponent.Builder().nutrition(4).saturationModifier(1.0f)
			.statusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 120, 0), 1.0f).build()));

		public static final Item CURED_BEEF = new Item(new Item.Settings().food(new FoodComponent.Builder().nutrition(2).saturationModifier(0.6f).build()));
		public static final Item CURED_CHICKEN = new Item(new Item.Settings().food(new FoodComponent.Builder().nutrition(4).saturationModifier(0.8f).build()));

		public static final Item SANDFISH = new Item(new Item.Settings().food(new FoodComponent.Builder().nutrition(2).saturationModifier(0.6f).build()));
		public static final Item COOKED_SANDFISH = new Item(new Item.Settings()
			.food(new FoodComponent.Builder().nutrition(5).saturationModifier(0.6f).build()));
		public static final Item SIDEWINDER = new Item(new Item.Settings().food(new FoodComponent.Builder().nutrition(6).saturationModifier(0.6f).build()));
		public static final Item SMOKED_FISH = new Item(new Item.Settings().food(new FoodComponent.Builder().nutrition(3).saturationModifier(0f).build()));


		public static final Item SENTINEL_SPAWN_EGG = new SpawnEggItem(ModEntities.SENTINEL, 0xffffff, 0xD31400, new Item.Settings());
		public static final Item JANITOR_DROID_SPAWN_EGG = new SpawnEggItem(ModEntities.JANITOR_DROID, 0xffffff, 0xffffff, new Item.Settings());
		public static final Item FLORBUS_SPAWN_EGG = new SpawnEggItem(ModEntities.FLORBUS, 0xd9c996, 0xb6a269, new Item.Settings());

		public static final Item FEATHERCORN = new TallBlockItem(TSBlocks.Plants.FEATHERCORN, new Item.Settings());

		public static final Item VENDOR_TOKEN = new Item(new Item.Settings());
		public static final Item LEGENDARY_TOKEN = new Item(new Item.Settings().rarity(Rarity.EPIC));
	}
}
