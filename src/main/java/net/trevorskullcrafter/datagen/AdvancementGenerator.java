package net.trevorskullcrafter.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.*;
import net.minecraft.advancement.criterion.ConsumeItemCriterion;
import net.minecraft.advancement.criterion.ImpossibleCriterion;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.advancement.criterion.OnKilledCriterion;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.trevorskullcrafter.block.TSBlocks;
import net.trevorskullcrafter.item.TSItems;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import static net.trevorskullcrafter.TrevorsSentinels.MOD_ID;

public class AdvancementGenerator extends FabricAdvancementProvider {
	public AdvancementGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) { super(output, registryLookup); }

	@Override public void generateAdvancement(RegistryWrapper.WrapperLookup registryLookup, Consumer<AdvancementEntry> consumer) {
		AdvancementEntry trevorssentinels = Advancement.Builder.create()
				.display(new AdvancementDisplay(new ItemStack(TSItems.Tech.SCRAP_METAL_SHARD), Text.translatable("advancements.trevorssentinels.trevorssentinels.title"),
						Text.translatable("advancements.trevorssentinels.trevorssentinels.desc"), Optional.of(Identifier.of(MOD_ID, "textures/block/steel_block.png")),
						AdvancementFrame.TASK, true, true, false))
				.criterion("got_scrap_metal_shard", InventoryChangedCriterion.Conditions.items(TSItems.Tech.SCRAP_METAL_SHARD))
				.rewards(AdvancementRewards.Builder.loot(RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.of(MOD_ID, "grant_database"))))
				.build(consumer, MOD_ID + ":trevorssentinels");

		AdvancementEntry spf = Advancement.Builder.create().parent(trevorssentinels)
				.display(new AdvancementDisplay(new ItemStack(TSItems.Magic.VIRIDIAN_BOAT), Text.translatable("advancements.trevorssentinels.spf.title"),
						Text.translatable("advancements.trevorssentinels.spf.desc"), Optional.of(Identifier.of(MOD_ID, "textures/block/steel_block.png")),
						AdvancementFrame.TASK, true, true, false))
				.criterion("got_banana_boat", InventoryChangedCriterion.Conditions.items(TSItems.Magic.VIRIDIAN_BOAT, TSItems.Magic.VIRIDIAN_CHEST_BOAT))
				.build(consumer, MOD_ID + ":spf");

		AdvancementEntry bacon_and_eggs = Advancement.Builder.create().parent(trevorssentinels)
				.display(new AdvancementDisplay(new ItemStack(TSItems.Beta.FRIED_EGG), Text.translatable("advancements.trevorssentinels.bacon_and_eggs.title"),
						Text.translatable("advancements.trevorssentinels.bacon_and_eggs.desc"), Optional.of(Identifier.of(MOD_ID, "textures/block/steel_block.png")),
						AdvancementFrame.GOAL, true, true, false))
				.criterion("eaten_egg", ConsumeItemCriterion.Conditions.item(TSItems.Beta.FRIED_EGG))
				.criterion("eaten_bacon", ConsumeItemCriterion.Conditions.item(TSItems.Magic.DUBIOUS_BACON))
				.build(consumer, MOD_ID + ":bacon_and_eggs");

		AdvancementEntry brownie = Advancement.Builder.create().parent(trevorssentinels)
				.display(new AdvancementDisplay(new ItemStack(TSItems.Beta.BROWNIE), Text.translatable("advancements.trevorssentinels.brownie.title"),
						Text.translatable("advancements.trevorssentinels.brownie.desc"), Optional.of(Identifier.of(MOD_ID, "textures/block/steel_block.png")),
						AdvancementFrame.GOAL, true, true, false))
				.criterion("consumed_brownie", ConsumeItemCriterion.Conditions.item(TSItems.Beta.BROWNIE))
				.rewards(AdvancementRewards.Builder.experience(420))
				.build(consumer, MOD_ID + ":brownie");

		AdvancementEntry chainsaw_man = Advancement.Builder.create().parent(trevorssentinels)
				.display(new AdvancementDisplay(new ItemStack(TSItems.Tech.SCRAP_METAL_CHAINSAW), Text.translatable("advancements.trevorssentinels.chainsaw_man.title"),
						Text.translatable("advancements.trevorssentinels.chainsaw_man.desc"), Optional.of(Identifier.of(MOD_ID, "textures/block/steel_block.png")),
						AdvancementFrame.GOAL, true, true, false))
				.criterion("got_chainsaw", InventoryChangedCriterion.Conditions.items(TSItems.Tech.SCRAP_METAL_CHAINSAW))
				.build(consumer, MOD_ID + ":chainsaw_man");

		AdvancementEntry ancient_metal = Advancement.Builder.create().parent(trevorssentinels)
				.display(new AdvancementDisplay(new ItemStack(TSItems.Magic.IMPERIAL_ALLOY_INGOT), Text.translatable("advancements.trevorssentinels.ancient_metal.title"),
						Text.translatable("advancements.trevorssentinels.ancient_metal.desc"), Optional.of(Identifier.of(MOD_ID, "textures/block/steel_block.png")),
						AdvancementFrame.TASK, true, true, false))
				.criterion("got_something", InventoryChangedCriterion.Conditions.items(TSItems.Magic.IMPERIAL_SWORD, TSItems.Magic.IMPERIAL_GLADIUS,
						TSItems.Magic.IMPERIAL_PICKAXE, TSItems.Magic.IMPERIAL_BATTLEAXE, TSItems.Magic.IMPERIAL_SHOVEL, TSItems.Magic.IMPERIAL_HOE,
						TSItems.Magic.IMPERIAL_HELMET,TSItems.Magic.IMPERIAL_CHESTPLATE, TSItems.Magic.IMPERIAL_LEGGINGS, TSItems.Magic.IMPERIAL_BOOTS))
				.build(consumer, MOD_ID + ":ancient_metal");

		AdvancementEntry rose_gold = Advancement.Builder.create().parent(trevorssentinels)
				.display(new AdvancementDisplay(new ItemStack(TSItems.Magic.ROSE_GOLD_INGOT), Text.translatable("advancements.trevorssentinels.rose_gold.title"),
						Text.translatable("advancements.trevorssentinels.rose_gold.desc"), Optional.of(Identifier.of(MOD_ID, "textures/block/steel_block.png")),
						AdvancementFrame.TASK, true, true, false))
				.criterion("got_ingot", InventoryChangedCriterion.Conditions.items(TSItems.Magic.ROSE_GOLD_INGOT))
				.build(consumer, MOD_ID + ":rose_gold");

		AdvancementEntry rose_gold_2 = Advancement.Builder.create().parent(rose_gold)
				.display(new AdvancementDisplay(new ItemStack(TSItems.Magic.ROSE_GOLD_INGOT), Text.translatable("advancements.trevorssentinels.rose_gold_2.title"),
						Text.translatable("advancements.trevorssentinels.rose_gold_2.desc"), Optional.of(Identifier.of(MOD_ID, "textures/block/steel_block.png")),
						AdvancementFrame.GOAL, true, true, false))
				.criterion("got_something", InventoryChangedCriterion.Conditions.items(TSItems.Magic.ROSE_GOLD_SWORD, TSItems.Magic.ROSE_GOLD_DAGGER,
						TSItems.Magic.ROSE_GOLD_PICKAXE, TSItems.Magic.ROSE_GOLD_BATTLEAXE, TSItems.Magic.ROSE_GOLD_SHOVEL, TSItems.Magic.ROSE_GOLD_HOE,
						TSItems.Magic.ROSE_GOLD_HELMET, TSItems.Magic.ROSE_GOLD_CHESTPLATE, TSItems.Magic.ROSE_GOLD_LEGGINGS, TSItems.Magic.ROSE_GOLD_BOOTS))
				.build(consumer, MOD_ID + ":rose_gold_2");

		AdvancementEntry thanatu = Advancement.Builder.create().parent(rose_gold_2)
				.display(new AdvancementDisplay(new ItemStack(TSItems.Magic.MACABRE_HELMET), Text.translatable("advancements.trevorssentinels.thanatu.title"),
						Text.translatable("advancements.trevorssentinels.thanatu.desc"), Optional.of(Identifier.of(MOD_ID, "textures/block/steel_block.png")),
						AdvancementFrame.CHALLENGE, true, true, false))
				.criterion("got_sword", InventoryChangedCriterion.Conditions.items(TSItems.Beta.THANATU_BLADE))
				.criterion("got_helmet", InventoryChangedCriterion.Conditions.items(TSItems.Magic.MACABRE_HELMET))
				.criterion("got_chestplate", InventoryChangedCriterion.Conditions.items(TSItems.Magic.MACABRE_HARNESS))
				.criterion("got_leggings", InventoryChangedCriterion.Conditions.items(TSItems.Magic.MACABRE_LOINCLOTH))
				.criterion("got_boots", InventoryChangedCriterion.Conditions.items(TSItems.Magic.MACABRE_SANDALS))
				.build(consumer, MOD_ID + ":thanatu");

		AdvancementEntry cerberus = Advancement.Builder.create().parent(ancient_metal)
				.display(new AdvancementDisplay(new ItemStack(Items.WITHER_SKELETON_SKULL), Text.translatable("advancements.trevorssentinels.cerberus.title"),
						Text.translatable("advancements.trevorssentinels.cerberus.desc"), Optional.of(Identifier.of(MOD_ID, "textures/block/steel_block.png")),
						AdvancementFrame.CHALLENGE, true, true, true))
				.criterion("killed", OnKilledCriterion.Conditions.createPlayerKilledEntity(EntityPredicate.Builder.create().type(EntityType.WITHER)))
				.build(consumer, MOD_ID + ":cerberus");

		AdvancementEntry uranium = Advancement.Builder.create().parent(cerberus)
				.display(new AdvancementDisplay(new ItemStack(TSItems.Tech.NUCLEAR_INGOT), Text.translatable("advancements.trevorssentinels.uranium.title"),
						Text.translatable("advancements.trevorssentinels.uranium.desc"), Optional.of(Identifier.of(MOD_ID, "textures/block/steel_block.png")),
						AdvancementFrame.GOAL, true, true, false))
				.criterion("got_cube", InventoryChangedCriterion.Conditions.items(TSItems.Tech.NUCLEAR_INGOT))
				.build(consumer, MOD_ID + ":uranium");

		AdvancementEntry superforge = Advancement.Builder.create().parent(uranium)
				.display(new AdvancementDisplay(new ItemStack(TSBlocks.Tech.RECONSTRUCTION_TABLE), Text.translatable("advancements.trevorssentinels.superforge.title"),
						Text.translatable("advancements.trevorssentinels.superforge.desc"), Optional.of(Identifier.of(MOD_ID, "textures/block/steel_block.png")),
						AdvancementFrame.GOAL, true, true, false))
				.criterion("got_superforge", InventoryChangedCriterion.Conditions.items(TSBlocks.Tech.RECONSTRUCTION_TABLE))
				.build(consumer, MOD_ID + ":superforge");

		AdvancementEntry zenithium = Advancement.Builder.create().parent(superforge)
				.display(new AdvancementDisplay(new ItemStack(TSItems.Tech.ZENITHIUM_CLUSTER), Text.translatable("advancements.trevorssentinels.zenithium.title"),
						Text.translatable("advancements.trevorssentinels.zenithium.desc"), Optional.of(Identifier.of(MOD_ID, "textures/block/steel_block.png")),
						AdvancementFrame.CHALLENGE, true, true, false))
				.criterion("got_cluster", InventoryChangedCriterion.Conditions.items(TSItems.Tech.ZENITHIUM_CLUSTER))
				.build(consumer, MOD_ID + ":zenithium");
	}
}
