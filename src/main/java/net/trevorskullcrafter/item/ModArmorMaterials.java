package net.trevorskullcrafter.item;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static net.trevorskullcrafter.TrevorsSentinels.MOD_ID;

public class ModArmorMaterials extends ArmorMaterials {

	public static final RegistryEntry<ArmorMaterial> SCRAP_METAL = registerMaterial("scrap_metal", 3, 8, 6, 3, 5,
		SoundEvents.ITEM_ARMOR_EQUIP_IRON, () -> Ingredient.ofItems(TSItems.Tech.SCRAP_METAL_SHARD), 0.0F, 0.0F, false);
	public static final int SCRAP_METAL_DURABILITY = 3;

	public static final RegistryEntry<ArmorMaterial> DRYADIC = registerMaterial("dryadic", 2, 6, 4, 2, 12,
		SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, () -> Ingredient.ofItems(TSItems.Magic.ENCHANTED_LEAF), 0.0F, 0.0F, false);
	public static final int DRYADIC_DURABILITY = 7;

	public static final RegistryEntry<ArmorMaterial> ROSE_GOLD = registerMaterial("rose_gold", 2, 6, 4, 2, 9,
		SoundEvents.ITEM_ARMOR_EQUIP_IRON, () -> Ingredient.ofItems(TSItems.Magic.ROSE_GOLD_INGOT), 0.0F, 0.0F, false);
	public static final int ROSE_GOLD_DURABILITY = 13;

	public static final RegistryEntry<ArmorMaterial> DRUIDIC = registerMaterial("druidic", 2, 6, 4, 2, 12,
		SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, () -> Ingredient.ofItems(TSItems.Magic.DRUIDIC_GEM), 0.0F, 0.0F, false);
	public static final int DRUIDIC_DURABILITY = 13;

	public static final RegistryEntry<ArmorMaterial> STARSTEEL = registerMaterial("starsteel", 3, 7, 4, 3, 6,
		SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, () -> Ingredient.ofItems(TSItems.Tech.STARSTEEL_INGOT), 2.0F, 0.1F, false);
	public static final int STARSTEEL_DURABILITY = 27;

	public static final RegistryEntry<ArmorMaterial> IMPERIAL = registerMaterial("imperial", 3, 8, 6, 3, 12,
		SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, () -> Ingredient.ofItems(TSItems.Magic.IMPERIAL_ALLOY_INGOT), 2.0F, 0.1F, false);
	public static final int IMPERIAL_DURABILITY = 24;

	public static final RegistryEntry<ArmorMaterial> UNHOLY = registerMaterial("unholy", 4, 10, 8, 4, 17,
		SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, () -> Ingredient.ofItems(TSItems.Magic.UNHOLY_INGOT), 3.0F, 0.4F, false);
	public static final int UNHOLY_DURABILITY = 39;

	public static final RegistryEntry<ArmorMaterial> NUCLEAR = registerMaterial("nuclear", 4, 10, 8, 4, 6,
		SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, () -> Ingredient.ofItems(TSItems.Tech.NUCLEAR_INGOT), 3.0F, 0.4F, false);
	public static final int NUCLEAR_DURABILITY = 39;

	public static final RegistryEntry<ArmorMaterial> ARMA_DEI = registerMaterial("arma_dei", 6, 12, 8, 5, 20,
		SoundEvents.ITEM_ARMOR_EQUIP_GOLD, () -> Ingredient.ofItems(Items.GOLD_INGOT), 4.0F, 0.5F, false);
	public static final int ARMA_DEI_DURABILITY = 42;

	public static final RegistryEntry<ArmorMaterial> NANOTECH = registerMaterial("nanotech", 6, 12, 8, 5, 6,
		SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, () -> Ingredient.ofItems(TSItems.Tech.NANOTECH_CAPSULE), 4.0F, 0.5F, false);
	public static final int NANOTECH_DURABILITY = 42;

	public static final RegistryEntry<ArmorMaterial> ZENITHIUM = registerMaterial("zenithium", 8, 14, 10, 7, 24,
		SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, () -> Ingredient.ofItems(TSItems.Tech.ZENITHIUM_CLUSTER), 5.0F, 0.7F, false);
	public static final int ZENITHIUM_DURABILITY = 50;

	public static RegistryEntry<ArmorMaterial> registerMaterial(String id, int helmet, int chestplate, int leggings, int boots, int enchantability, RegistryEntry<SoundEvent> equipSound, Supplier<Ingredient> repairIngredientSupplier, float toughness, float knockbackResistance, boolean dyeable) {
		List<ArmorMaterial.Layer> layers = List.of(new ArmorMaterial.Layer(Identifier.of(MOD_ID, id), "", dyeable));
		return Registry.registerReference(Registries.ARMOR_MATERIAL, Identifier.of(MOD_ID, id), new ArmorMaterial(
			Map.of(ArmorItem.Type.HELMET, helmet, ArmorItem.Type.CHESTPLATE, chestplate, ArmorItem.Type.LEGGINGS, leggings, ArmorItem.Type.BOOTS, boots),
			enchantability, equipSound, repairIngredientSupplier, layers, toughness, knockbackResistance));
	}

	public static void registerModArmorMaterials() {}
}
