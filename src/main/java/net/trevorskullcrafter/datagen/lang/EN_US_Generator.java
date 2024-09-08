package net.trevorskullcrafter.datagen.lang;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.trevorskullcrafter.block.TSBlocks;
import net.trevorskullcrafter.effect.ModEffects;
import net.trevorskullcrafter.entity.ModEntities;
import net.trevorskullcrafter.item.TSItems;
import net.trevorskullcrafter.sound.ModSounds;
import net.trevorskullcrafter.util.JsonTextObject;

import java.awt.*;
import java.util.concurrent.CompletableFuture;

import static net.trevorskullcrafter.TrevorsSentinels.MOD_ID;
import static net.trevorskullcrafter.util.TextUtil.*;
import static net.trevorskullcrafter.util.TextUtil.Translation.*;

public class EN_US_Generator extends OwoLanguageProvider {
	public EN_US_Generator(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) { super(dataOutput, "en_us", registryLookup); }

	@Override public void generateRichTranslations(OwoTranslationBuilder translationBuilder) {
		//Magic Equipment
		translationBuilder.addWithParent(new Translation().textColor(VIRIDIAN),
			new ItemTranslation(TSItems.Magic.DRYADIC_HELMET),
			new ItemTranslation(TSItems.Magic.DRYADIC_CHESTPLATE),
			new ItemTranslation(TSItems.Magic.DRYADIC_LEGGINGS),
			new ItemTranslation(TSItems.Magic.DRYADIC_BOOTS),
			new ItemTranslation(TSItems.Magic.DRYADIC_SWORD),
			new ItemTranslation(TSItems.Magic.DRYADIC_DAGGER),
			new ItemTranslation(TSItems.Magic.ENCHANTED_LEAF).tooltip("A gift from nature."));
		translationBuilder.addWithParent(new Translation().textColor(ROSE_GOLD),
			new ItemTranslation(TSItems.Magic.ROSE_GOLD_INGOT),
			new ItemTranslation(TSBlocks.Magic.ROSE_GOLD_BLOCK).text("Block of Rose Gold"),
			new ItemTranslation(TSItems.Magic.ROSE_GOLD_HELMET),
			new ItemTranslation(TSItems.Magic.ROSE_GOLD_CHESTPLATE).text("Rose Gold Cuirass"),
			new ItemTranslation(TSItems.Magic.ROSE_GOLD_LEGGINGS),
			new ItemTranslation(TSItems.Magic.ROSE_GOLD_BOOTS),
			new ItemTranslation(TSItems.Magic.ROSE_GOLD_SWORD),
			new ItemTranslation(TSItems.Magic.ROSE_GOLD_DAGGER),
			new ItemTranslation(TSItems.Magic.ROSE_GOLD_PICKAXE),
			new ItemTranslation(TSItems.Magic.ROSE_GOLD_BATTLEAXE),
			new ItemTranslation(TSItems.Magic.ROSE_GOLD_SHOVEL),
			new ItemTranslation(TSItems.Magic.ROSE_GOLD_HOE).text("Rose Gold Sickle"));
		translationBuilder.addWithParent(new Translation().textColor(VIRIDIAN),
			new ItemTranslation(TSItems.Magic.DRUIDIC_HELMET),
			new ItemTranslation(TSItems.Magic.DRUIDIC_CHESTPLATE),
			new ItemTranslation(TSItems.Magic.DRUIDIC_LEGGINGS),
			new ItemTranslation(TSItems.Magic.DRUIDIC_BOOTS),
			new ItemTranslation(TSItems.Magic.DRUIDIC_SWORD),
			new ItemTranslation(TSItems.Magic.DRUIDIC_DAGGER),
			new ItemTranslation(TSItems.Magic.DRUIDIC_BOW),
			new ItemTranslation(TSItems.Magic.DRUIDIC_GEM));
		translationBuilder.addWithParent(new Translation().textColor(IMPERIAL_ALLOY, IMPERIAL_ALLOY2),
			new ItemTranslation(TSItems.Magic.IMPERIAL_ALLOY_INGOT),
			new ItemTranslation(TSBlocks.Magic.IMPERIAL_ALLOY_BLOCK).text("Block of Imperial Alloy"),
			new ItemTranslation(TSItems.Magic.IMPERIAL_HELMET),
			new ItemTranslation(TSItems.Magic.IMPERIAL_CHESTPLATE),
			new ItemTranslation(TSItems.Magic.IMPERIAL_LEGGINGS),
			new ItemTranslation(TSItems.Magic.IMPERIAL_BOOTS),
			new ItemTranslation(TSItems.Magic.IMPERIAL_SWORD),
			new ItemTranslation(TSItems.Magic.IMPERIAL_GLADIUS),
			new ItemTranslation(TSItems.Magic.IMPERIAL_PICKAXE),
			new ItemTranslation(TSItems.Magic.IMPERIAL_BATTLEAXE),
			new ItemTranslation(TSItems.Magic.IMPERIAL_SHOVEL),
			new ItemTranslation(TSItems.Magic.IMPERIAL_HOE).text("Imperial Sickle"));
		translationBuilder.addWithParent(new Translation().textColor(LIGHT_PURPLE),
			new ItemTranslation(TSItems.Magic.MACABRE_HELMET).tooltip(new JsonTextObject().text("\"A... melancholic facade.\"").color(TRANQUIL)),
			new ItemTranslation(TSItems.Magic.MACABRE_HARNESS).tooltip(new JsonTextObject().text("\"A convenient accessory.\"").color(TRANQUIL)),
			new ItemTranslation(TSItems.Magic.MACABRE_LOINCLOTH).tooltip(new JsonTextObject().text("\"A liberating adornment.\"").color(TRANQUIL)),
			new ItemTranslation(TSItems.Magic.MACABRE_SANDALS).tooltip(new JsonTextObject().text("\"A comfortable fit.\"").color(TRANQUIL)));
		translationBuilder.addWithParent(new Translation().textColor(LIGHT_PURPLE),
			new ItemTranslation(TSItems.Magic.UNHOLY_CORE).tooltip(new JsonTextObject().text("\"Refreshing... yet melancholic.\"").color(TRANQUIL)),
			new ItemTranslation(TSItems.Magic.UNHOLY_INGOT).tooltip("Reflects only the light in darkness."),
			new ItemTranslation(TSBlocks.Magic.UNHOLY_BLOCK).text("Block of Unholy Alloy"),
			new ItemTranslation(TSBlocks.Magic.TELEPORTER),
			new ItemTranslation(TSItems.Magic.UNHOLY_HELMET),
			new ItemTranslation(TSItems.Magic.UNHOLY_CHESTPLATE).text("Unholy Cuirass"),
			new ItemTranslation(TSItems.Magic.UNHOLY_LEGGINGS),
			new ItemTranslation(TSItems.Magic.UNHOLY_BOOTS),
			new ItemTranslation(TSItems.Magic.UNHOLY_SWORD),
			new ItemTranslation(TSItems.Magic.UNHOLY_DAGGER),
			new ItemTranslation(TSItems.Magic.UNHOLY_PICKAXE),
			new ItemTranslation(TSItems.Magic.UNHOLY_BATTLEAXE),
			new ItemTranslation(TSItems.Magic.UNHOLY_SHOVEL),
			new ItemTranslation(TSItems.Magic.UNHOLY_HOE).text("Unholy Sickle"));
		translationBuilder.addWithParent(new Translation().textColor(RAINBOW),
			new ItemTranslation(TSItems.Magic.ARMA_DEI_HELMET),
			new ItemTranslation(TSItems.Magic.ARMA_DEI_CHESTPLATE),
			new ItemTranslation(TSItems.Magic.ARMA_DEI_LEGGINGS),
			new ItemTranslation(TSItems.Magic.ARMA_DEI_BOOTS),
			new ItemTranslation(TSItems.Magic.ARMA_DEI_SWORD),
			new ItemTranslation(TSItems.Magic.ARMA_DEI_DAGGER),
			new ItemTranslation(TSItems.Magic.ARMA_DEI_PICKAXE),
			new ItemTranslation(TSItems.Magic.ARMA_DEI_BATTLEAXE),
			new ItemTranslation(TSItems.Magic.ARMA_DEI_SHOVEL),
			new ItemTranslation(TSItems.Magic.ARMA_DEI_HOE).text("Arma Dei Sickle"));

		translationBuilder.add(new ItemTranslation(TSBlocks.Tech.WAX_INFUSED_COPPER_BLOCK).text("Wax-Infused Block of Copper"));
		translationBuilder.add(new ItemTranslation(TSBlocks.Tech.STARSTEEL_LAMP));
		translationBuilder.add(new ItemTranslation(TSBlocks.Tech.STARSTEEL_FAN));
		translationBuilder.add(new ItemTranslation(TSBlocks.Tech.BATTERY));
		translationBuilder.add(new ItemTranslation(TSBlocks.Tech.FUSEBOX));
		translationBuilder.add(new ItemTranslation(TSBlocks.Tech.FANCY_COMPUTER));
		translationBuilder.add(new ItemTranslation(TSBlocks.Tech.STARSTEEL_REPULSOR).tooltip("Watch your step."));
		translationBuilder.addWithParent(new Translation().textColor(SENTINEL_CRIMSON1, SENTINEL_CRIMSON3),
			new ItemTranslation(TSBlocks.Tech.BIG_RED_BUTTON),
			new ItemTranslation(TSBlocks.Tech.BIG_RED_PLATE).tooltip("1500 Megawatt Super Colliding Super Plate"));

		//Tech Equipment
		translationBuilder.addWithParent(new Translation().textColor(SCRAP_METAL),
			new ItemTranslation(TSItems.Tech.SCRAP_METAL_SHARD),
			new ItemTranslation(TSBlocks.Tech.SCRAP_METAL_BLOCK),
			new ItemTranslation(TSBlocks.Tech.CHISELED_SCRAP_METAL_BLOCK),
			new ItemTranslation(TSBlocks.Tech.SCRAP_METAL_PILLAR),
			new ItemTranslation(TSItems.Tech.SCRAP_METAL_HELMET),
			new ItemTranslation(TSItems.Tech.SCRAP_METAL_CHESTPLATE),
			new ItemTranslation(TSItems.Tech.SCRAP_METAL_LEGGINGS),
			new ItemTranslation(TSItems.Tech.SCRAP_METAL_BOOTS),
			new ItemTranslation(TSItems.Tech.SCRAP_METAL_SWORD),
			new ItemTranslation(TSItems.Tech.SCRAP_METAL_KNIFE),
			new ItemTranslation(TSItems.Tech.SCRAP_METAL_DRILL),
			new ItemTranslation(TSItems.Tech.SCRAP_METAL_CHAINSAW).tooltip("Right click to launch metal shards!"),
			new ItemTranslation(TSItems.Tech.SCRAP_METAL_SHOVEL),
			new ItemTranslation(TSItems.Tech.SCRAP_METAL_HOE),
			new ItemTranslation(TSItems.Tech.SCRAP_METAL_PHASER),
			new ItemTranslation(TSItems.Tech.LIFEFORM_TRACER).tooltip("Use on a mob to track it."));
		translationBuilder.addWithParent(new Translation().textColor(STARSTEEL),
			new ItemTranslation(TSItems.Tech.INDUSTRIAL_HELMET),
			new ItemTranslation(TSItems.Tech.INDUSTRIAL_HARNESS),
			new ItemTranslation(TSItems.Tech.INDUSTRIAL_PANTS),
			new ItemTranslation(TSItems.Tech.INDUSTRIAL_BOOTS),
			new ItemTranslation(TSItems.Tech.INDUSTRIAL_CROWBAR).tooltip("\"Rise, and... shine.\""),
			new ItemTranslation(TSItems.Tech.INDUSTRIAL_PHASER));
		translationBuilder.addWithParent(new Translation().textColor(STARSTEEL_BASE),
			new ItemTranslation(TSItems.Tech.STARSTEEL_INGOT),
			new ItemTranslation(TSBlocks.Tech.STARSTEEL_BLOCK).text("Block of Starsteel"),
			new ItemTranslation(TSBlocks.Tech.CHISELED_STARSTEEL_BLOCK).text("Chiseled Block of Starsteel"),
			new ItemTranslation(TSBlocks.Tech.STAINLESS_STARSTEEL_BLOCK),
			new ItemTranslation(TSBlocks.Tech.STARSTEEL_LADDER),
			new ItemTranslation(TSItems.Tech.STARSTEEL_SIGN),
			new ItemTranslation(TSItems.Tech.STARSTEEL_HANGING_SIGN),
			new ItemTranslation(TSItems.Tech.STARSTEEL_HELMET),
			new ItemTranslation(TSItems.Tech.STARSTEEL_CHESTPLATE),
			new ItemTranslation(TSItems.Tech.STARSTEEL_LEGGINGS),
			new ItemTranslation(TSItems.Tech.STARSTEEL_BOOTS),
			new ItemTranslation(TSItems.Tech.STARSTEEL_SWORD),
			new ItemTranslation(TSItems.Tech.STARSTEEL_KNIFE),
			new ItemTranslation(TSItems.Tech.STARSTEEL_DRILL),
			new ItemTranslation(TSItems.Tech.STARSTEEL_AXE),
			new ItemTranslation(TSItems.Tech.STARSTEEL_SHOVEL),
			new ItemTranslation(TSItems.Tech.STARSTEEL_HOE),
			new ItemTranslation(TSItems.Tech.STARSTEEL_PHASER));
		translationBuilder.addWithParent(new Translation().textColor(NUCLEAR),
			new ItemTranslation(TSItems.Tech.NUCLEAR_INGOT),
			new ItemTranslation(TSBlocks.Tech.NUCLEAR_ORE),
			new ItemTranslation(TSBlocks.Tech.NUCLEAR_BLOCK).text("Nuclear Storage Block"),
			new ItemTranslation(TSItems.Tech.NUCLEAR_ROCKET),
			new ItemTranslation(TSItems.Tech.NUCLEAR_HELMET),
			new ItemTranslation(TSItems.Tech.NUCLEAR_CHESTPLATE),
			new ItemTranslation(TSItems.Tech.NUCLEAR_LEGGINGS),
			new ItemTranslation(TSItems.Tech.NUCLEAR_BOOTS),
			new ItemTranslation(TSItems.Tech.NUCLEAR_SWORD).tooltip("Irradiates enemies."),
			new ItemTranslation(TSItems.Tech.NUCLEAR_VIBROKNIFE).tooltip("Irradiates enemies."),
			new ItemTranslation(TSItems.Tech.NUCLEAR_DRILL),
			new ItemTranslation(TSItems.Tech.NUCLEAR_AXE),
			new ItemTranslation(TSItems.Tech.NUCLEAR_SHOVEL),
			new ItemTranslation(TSItems.Tech.NUCLEAR_HOE),
			new ItemTranslation(TSItems.Tech.NUCLEAR_PHASER));
		translationBuilder.addWithParent(new Translation().textColor(BLOOD_RED),
			new ItemTranslation(TSItems.Tech.NANOTECH_CAPSULE),
			new ItemTranslation(TSBlocks.Tech.NANOTECH_BLOCK).text("Nanotech Storage Block"),
			new ItemTranslation(TSItems.Tech.NANOTECH_HELMET),
			new ItemTranslation(TSItems.Tech.NANOTECH_CHESTPLATE),
			new ItemTranslation(TSItems.Tech.NANOTECH_LEGGINGS),
			new ItemTranslation(TSItems.Tech.NANOTECH_BOOTS),
			new ItemTranslation(TSItems.Tech.NANOTECH_SWORD).tooltip(new JsonTextObject().text("\"I've even forgotten my name...\"").color(DARK_RED)),
			new ItemTranslation(TSItems.Tech.NANOTECH_VIBROKNIFE),
			new ItemTranslation(TSItems.Tech.NANOTECH_DRILL),
			new ItemTranslation(TSItems.Tech.NANOTECH_AXE),
			new ItemTranslation(TSItems.Tech.NANOTECH_SHOVEL),
			new ItemTranslation(TSItems.Tech.NANOTECH_HOE),
			new ItemTranslation(TSItems.Tech.NANOTECH_PHASER));
		translationBuilder.addWithParent(new Translation().textColor(ZENITHIUM),
			new ItemTranslation(TSItems.Tech.ZENITHIUM_CLUSTER).tooltip("The concentrated essence of a galaxy."),
			new ItemTranslation(TSItems.Tech.ZENITHIUM_HELMET),
			new ItemTranslation(TSItems.Tech.ZENITHIUM_CHESTPLATE),
			new ItemTranslation(TSItems.Tech.ZENITHIUM_LEGGINGS),
			new ItemTranslation(TSItems.Tech.ZENITHIUM_BOOTS),
			new ItemTranslation(TSItems.Tech.ZENITHIUM_SWORD),
			new ItemTranslation(TSItems.Tech.ZENITHIUM_DAGGER),
			new ItemTranslation(TSItems.Tech.ZENITHIUM_PICKAXE),
			new ItemTranslation(TSItems.Tech.ZENITHIUM_AXE),
			new ItemTranslation(TSItems.Tech.ZENITHIUM_SHOVEL),
			new ItemTranslation(TSItems.Tech.ZENITHIUM_HOE),
			new ItemTranslation(TSItems.Tech.ZENITHIUM_PHASER));

		translationBuilder.add(new ItemTranslation(TSItems.Magic.COPPER_GLADIUS).tooltip("An ornamental blade of goblin tradition.").textColor(COPPER));
		translationBuilder.add(new ItemTranslation(TSItems.Beta.BLACKSMITHS_WELDING_MASK).tooltip("\"+3 Fire Resistance\"").textColor(STARSTEEL));
		translationBuilder.add(new ItemTranslation(TSItems.Beta.MAD_SCIENTISTS_LAB_COAT).tooltip("\"Behold The C.O.A.T!\"").textColor(STARSTEEL));

        translationBuilder.add(
			new ItemTranslation(TSItems.Beta.PAPPYM_BLADE).text("Jade Seraphim")
				.tooltip(new JsonTextObject().text("\"An ancient relic... and now it has found its way to you.\"").color(TRANQUIL)).textColor(GREEN),
			new ItemTranslation(TSItems.Beta.PAPPYD_BLADE).text("Hell's Flaming Fury").textColor(HELLFIRE),
			new ItemTranslation(TSItems.Beta.THANATU_BLADE).text("Harmonious Rapture").textColor(LIGHT_PURPLE).tooltip(new JsonTextObject().text("\"An old friend.\"").color(TRANQUIL)),
			new ItemTranslation(TSItems.Beta.SKYLAR_BLADE).text("Equitous Retribution").textColor(SHINY_GOLD),
			new ItemTranslation(TSItems.Beta.LILITH_BLADE).text("Unwavering Judgement").textColor(DUNE_TAN),
			new ItemTranslation(TSItems.Beta.KINGS_BLADE).text("Stainless Resolve").textColor(WHITE),
			new ItemTranslation(TSItems.Beta.SCARA_SHEARS).text("SCARA Shears").tooltip("A military-grade threat.").textColor(DARK_PURPLE),
			new ItemTranslation(TSItems.Beta.MASTER_SWORD).text("The Master's Sword").tooltip("From a distant universe...").textColor(AQUA),
			new ItemTranslation(TSItems.Beta.DREAMBORNE_SLASHER).tooltip("\"I will be /you/ will be me.\"").textColor(AQUA)); //tu fui, ego eris

		//new Translation(TSItems.Tech.LASER_PISTOL, "NUMA-28 Comet", "\"Fast and portable!\"", STARSTEEL),
		//new Translation(TSItems.Tech.LASER_TASER, "Enforcer-Standard Taser", "\"Non-lethal.\"", AQUA),
		//new Translation(TSItems.Tech.LASER_SNIPER_ECHO, "NUMA-52 Echo", "\"They'll never see it coming!\"", DUNE_TAN),
		//new Translation(TSItems.Tech.LASER_MINIGUN, "UTARI-91 Serenity", "\"Keep the peace!\"", BLUE),
		//new Translation(TSItems.Tech.LASER_RIFLE, "NUMA-63 Agnostyk", "\"Packs a punch!\"", MOSS),
		//new Translation(TSItems.Tech.LASER_SHOTGUN, "KHAOS-66 Tremor", "\"Rip and tear!\"", HELLFIRE),
		//new Translation(TSItems.Tech.LASER_HEALER, "PW49 Firebird", "\"Those who can't do, heal!\"", SHINY_GOLD),
		//new Translation(TSItems.Tech.LASER_SNIPER, "PW88 Shadow", "\"The assassin's weapon of choice!\"", DARK_PURPLE),
		//new Translation(TSItems.Tech.LASER_SPREADER, "PW76 Pandemic", "\"Filled with a toxic bioweapon.\"", GREEN),
		//new Translation(TSItems.Tech.LASER_REVOLVER, "Drunkard's Handcannon", "\"But you have heard of me!\"", null),

		translationBuilder.addWithParent(new Translation().textColor(STARSTEEL),
			new ItemTranslation(TSItems.Tech.PLASMA_CELL),
			new ItemTranslation(TSItems.Tech.PAINT_PACK).textColor(RAINBOW),
			new ItemTranslation(TSItems.Tech.CHROMATIC_LENS),
			new ItemTranslation(TSItems.Tech.COUNTERFORCE_DIFFUSER),
			new ItemTranslation(TSItems.Tech.PHASE_ASSIMILATOR),
			new ItemTranslation(TSItems.Tech.AUXILIARY_PLASMA_CHAMBER),
			new ItemTranslation(TSItems.Tech.ADVANCED_BREECH_MECHANISM),
			new ItemTranslation(TSItems.Tech.SMOKE_CAPSULE),
			new ItemTranslation(TSItems.Tech.POISON_CAPSULE),
			new ItemTranslation(TSItems.Tech.WITHER_CAPSULE),
			new ItemTranslation(TSItems.Tech.REGENERATION_CAPSULE));
		translationBuilder.add(
			new ItemTranslation(TSItems.Magic.RESISTANCE_ITEM).text("Shield Badge").textColor(GRAY),
			new ItemTranslation(TSItems.Magic.FIRE_RESISTANCE_ITEM).text("Flaming Badge").textColor(HELLFIRE), //shield badge, fire stuff
			new ItemTranslation(TSItems.Magic.JUMP_BOOST_ITEM).text("Icarus' Gust").textColor(NUCLEAR),
			new ItemTranslation(TSItems.Magic.STRENGTH_ITEM).text("Sword Badge").textColor(GRAY), //shattered sword, iron ingots?
			new ItemTranslation(TSItems.Magic.WEAKNESS_ITEM).text("Shattered Sword").tooltip("A reminder of past failures."),
			new ItemTranslation(TSItems.Magic.REGENERATION_ITEM).text("Ever-Beating Heart").tooltip(new JsonTextObject().text("\"The price of immortality...\"").color(TRANQUIL)),
			new ItemTranslation(TSItems.Magic.WATER_BREATHING_ITEM).text("Abyssal Bubbles"),
			new ItemTranslation(TSItems.Magic.INVISIBILITY_ITEM).text("Vampire's Mirror").tooltip("\"Aah! A vampire!\""),
			new ItemTranslation(TSItems.Magic.DOLPHINS_GRACE_ITEM).text("Foam Dolphin Plush").tooltip("Its silky skin makes one feel hydrodynamic.").textColor(DOLPHIN),
			new ItemTranslation(TSItems.Magic.CONDUIT_POWER_ITEM).text("Eye of the Sea").tooltip(""),
			new ItemTranslation(TSItems.Magic.NIGHT_VISION_ITEM).text("Feline Eye"),
			new ItemTranslation(TSItems.Magic.HERO_OF_THE_VILLAGE_ITEM).text("Hero's Emerald").tooltip("Gifted to heroes of legend.").textColor(NUCLEAR),
			new ItemTranslation(TSItems.Magic.PALADINS_BADGE).tooltip("The mark of a true warrior of Light.").textColor(SHINY_GOLD), //flaming badge, sword badge
			new ItemTranslation(TSItems.Magic.CYBERNETIC_STOMACH).textColor(SCRAP_METAL_BASE),
			new ItemTranslation(TSItems.Magic.ONE_PENCE).text("The One Pence").tooltip("\"Wealth, Fame, Power.\"").textColor(COPPER));

		translationBuilder.add(
			new ItemTranslation(TSItems.Beta.SENTINEL_SPAWN_EGG).text("Sentinel Spawn Beacon")
				.tooltip(new JsonTextObject().text("\"A mindless drone for the hive.\"").color(TRANQUIL)).textColor(SCRAP_METAL_BASE),
			new ItemTranslation(TSItems.Beta.JANITOR_DROID_SPAWN_EGG).text("Janitor Droid")
				.tooltip(new JsonTextObject().text("\"What a waste of a soul.\"").color(TRANQUIL)).textColor(SCRAP_METAL_BASE),
			new ItemTranslation(TSItems.Beta.FLORBUS_SPAWN_EGG).text("Florbus Spawn Sac").tooltip("\"Oh my clod!\""));
		translationBuilder.add(
			new ItemTranslation(TSItems.Beta.VENDOR_TOKEN).textColor(VENDOR_TOKEN),
			new ItemTranslation(TSItems.Beta.LEGENDARY_TOKEN));

		translationBuilder.addMusicDisc(new ItemTranslation(TSItems.Tech.MUSIC_DISC_ASSASSINATION_UPLOAD).text("Scrapped Cassette").textColor(SCRAP_METAL),
			jsonArrayOf(new JsonTextObject().text("Kristoffer Kaufmann - Assassination Upload")), ModSounds.ASSASSINATION_UPLOAD);
		translationBuilder.addMusicDisc(new ItemTranslation(TSItems.Tech.MUSIC_DISC_ODE_TO_TRANQUILITY).text("Music Disc").tooltip("\"A classic.\""),
			jsonArrayOf(new JsonTextObject().text("Kristoffer Kaufmann - Ode to Tranquility")), ModSounds.ODE_TO_TRANQUILITY);
		translationBuilder.addMusicDisc(new ItemTranslation(TSItems.Tech.MUSIC_DISC_LAPSE_IN_JUDGEMENT).text("War-Torn Music Disc").tooltip("\"It certainly was.\""),
			jsonArrayOf(new JsonTextObject().text("Kristoffer Kaufmann - Lapse in Judgement")), ModSounds.LAPSE_IN_JUDGEMENT);
		translationBuilder.addMusicDisc(new ItemTranslation(TSItems.Tech.MUSIC_DISC_RECITAL).text("Aged Cassette")
				.tooltip(new JsonTextObject().text("\"To think it's survived all this time...\"").color(TRANQUIL)),
			jsonArrayOf(new JsonTextObject().text("Trevor Skullcrafter - recital")), ModSounds.RECITAL);

		//Wood
		translationBuilder.addWithParent(new Translation().textColor(YGGDRASIL),
			new ItemTranslation(TSItems.Magic.PALE_BERRIES),
			new ItemTranslation(TSBlocks.Trees.PALE_LOG),
			new ItemTranslation(TSBlocks.Trees.PALE_WOOD),
			new ItemTranslation(TSBlocks.Trees.STRIPPED_PALE_LOG),
			new ItemTranslation(TSBlocks.Trees.STRIPPED_PALE_WOOD),
			new ItemTranslation(TSBlocks.Trees.PALE_PLANKS),
			new ItemTranslation(TSBlocks.Trees.PALE_LEAVES),
			new ItemTranslation(TSBlocks.Trees.PALE_SAPLING),
			new ItemTranslation(TSBlocks.Trees.POTTED_PALE_SAPLING),
			new ItemTranslation(TSBlocks.Trees.PALE_SLAB),
			new ItemTranslation(TSBlocks.Trees.PALE_STAIRS),
			new ItemTranslation(TSBlocks.Trees.PALE_FENCE),
			new ItemTranslation(TSBlocks.Trees.PALE_FENCE_GATE),
			new ItemTranslation(TSBlocks.Trees.PALE_BUTTON),
			new ItemTranslation(TSBlocks.Trees.PALE_PRESSURE_PLATE),
			new ItemTranslation(TSBlocks.Trees.PALE_DOOR),
			new ItemTranslation(TSBlocks.Trees.PALE_TRAPDOOR),
			new ItemTranslation(TSBlocks.Trees.PALE_SIGN),
			new ItemTranslation(TSBlocks.Trees.PALE_HANGING_SIGN),
			new ItemTranslation(TSItems.Magic.PALE_BOAT),
			new ItemTranslation(TSItems.Magic.PALE_CHEST_BOAT));
		translationBuilder.addWithParent(new Translation().textColor(CHARRED),
			new ItemTranslation(TSBlocks.Trees.CHARRED_LOG),
			new ItemTranslation(TSBlocks.Trees.CHARRED_WOOD),
			new ItemTranslation(TSBlocks.Trees.STRIPPED_CHARRED_LOG), new ItemTranslation(TSBlocks.Trees.STRIPPED_CHARRED_WOOD),
			new ItemTranslation(TSBlocks.Trees.CHARRED_PLANKS),
			null,
			null,
			null,
			new ItemTranslation(TSBlocks.Trees.CHARRED_SLAB),
			new ItemTranslation(TSBlocks.Trees.CHARRED_STAIRS),
			new ItemTranslation(TSBlocks.Trees.CHARRED_FENCE),
			new ItemTranslation(TSBlocks.Trees.CHARRED_FENCE_GATE),
			new ItemTranslation(TSBlocks.Trees.CHARRED_BUTTON),
			new ItemTranslation(TSBlocks.Trees.CHARRED_PRESSURE_PLATE),
			new ItemTranslation(TSBlocks.Trees.CHARRED_DOOR),
			new ItemTranslation(TSBlocks.Trees.CHARRED_TRAPDOOR),
			new ItemTranslation(TSBlocks.Trees.CHARRED_SIGN),
			new ItemTranslation(TSBlocks.Trees.CHARRED_HANGING_SIGN),
			new ItemTranslation(TSItems.Magic.CHARRED_BOAT),
			new ItemTranslation(TSItems.Magic.CHARRED_CHEST_BOAT));
		translationBuilder.addWithParent(new Translation().textColor(MIDAS),
			new ItemTranslation(TSBlocks.Trees.MIDAS_LOG),
			new ItemTranslation(TSBlocks.Trees.MIDAS_WOOD),
			new ItemTranslation(TSBlocks.Trees.STRIPPED_MIDAS_LOG),
			new ItemTranslation(TSBlocks.Trees.STRIPPED_MIDAS_WOOD),
			new ItemTranslation(TSBlocks.Trees.MIDAS_PLANKS),
			new ItemTranslation(TSBlocks.Trees.MIDAS_LEAVES),
			new ItemTranslation(TSBlocks.Trees.MIDAS_SAPLING),
			new ItemTranslation(TSBlocks.Trees.POTTED_MIDAS_SAPLING),
			new ItemTranslation(TSBlocks.Trees.MIDAS_SLAB),
			new ItemTranslation(TSBlocks.Trees.MIDAS_STAIRS),
			new ItemTranslation(TSBlocks.Trees.MIDAS_FENCE),
			new ItemTranslation(TSBlocks.Trees.MIDAS_FENCE_GATE),
			new ItemTranslation(TSBlocks.Trees.MIDAS_BUTTON),
			new ItemTranslation(TSBlocks.Trees.MIDAS_PRESSURE_PLATE),
			new ItemTranslation(TSBlocks.Trees.MIDAS_DOOR),
			new ItemTranslation(TSBlocks.Trees.MIDAS_TRAPDOOR),
			new ItemTranslation(TSBlocks.Trees.MIDAS_SIGN),
			new ItemTranslation(TSBlocks.Trees.MIDAS_HANGING_SIGN),
			new ItemTranslation(TSItems.Magic.MIDAS_BOAT),
			new ItemTranslation(TSItems.Magic.MIDAS_CHEST_BOAT));
		translationBuilder.addWithParent(new Translation().textColor(VIRIDIAN),
			new ItemTranslation(TSBlocks.Trees.VIRIDIAN_LOG),
			new ItemTranslation(TSBlocks.Trees.VIRIDIAN_WOOD),
			new ItemTranslation(TSBlocks.Trees.STRIPPED_VIRIDIAN_LOG),
			new ItemTranslation(TSBlocks.Trees.STRIPPED_VIRIDIAN_WOOD),
			new ItemTranslation(TSBlocks.Trees.VIRIDIAN_PLANKS),
			new ItemTranslation(TSBlocks.Trees.VIRIDIAN_LEAVES),
			new ItemTranslation(TSBlocks.Trees.VIRIDIAN_SAPLING),
			new ItemTranslation(TSBlocks.Trees.POTTED_VIRIDIAN_SAPLING),
			new ItemTranslation(TSBlocks.Trees.VIRIDIAN_SLAB),
			new ItemTranslation(TSBlocks.Trees.VIRIDIAN_STAIRS),
			new ItemTranslation(TSBlocks.Trees.VIRIDIAN_FENCE),
			new ItemTranslation(TSBlocks.Trees.VIRIDIAN_FENCE_GATE),
			new ItemTranslation(TSBlocks.Trees.VIRIDIAN_BUTTON),
			new ItemTranslation(TSBlocks.Trees.VIRIDIAN_PRESSURE_PLATE),
			new ItemTranslation(TSBlocks.Trees.VIRIDIAN_DOOR),
			new ItemTranslation(TSBlocks.Trees.VIRIDIAN_TRAPDOOR),
			new ItemTranslation(TSBlocks.Trees.VIRIDIAN_SIGN),
			new ItemTranslation(TSBlocks.Trees.VIRIDIAN_HANGING_SIGN),
			new ItemTranslation(TSItems.Magic.VIRIDIAN_BOAT),
			new ItemTranslation(TSItems.Magic.VIRIDIAN_CHEST_BOAT));
		translationBuilder.addWithParent(new Translation().textColor(CERULII),
			new ItemTranslation(TSBlocks.Trees.CERULII_LOG),
			new ItemTranslation(TSBlocks.Trees.CERULII_WOOD),
			new ItemTranslation(TSBlocks.Trees.STRIPPED_CERULII_LOG),
			new ItemTranslation(TSBlocks.Trees.STRIPPED_CERULII_WOOD),
			new ItemTranslation(TSBlocks.Trees.CERULII_PLANKS),
			new ItemTranslation(TSBlocks.Trees.CERULII_LEAVES),
			new ItemTranslation(TSBlocks.Trees.CERULII_SAPLING),
			new ItemTranslation(TSBlocks.Trees.POTTED_CERULII_SAPLING),
			new ItemTranslation(TSBlocks.Trees.CERULII_SLAB),
			new ItemTranslation(TSBlocks.Trees.CERULII_STAIRS),
			new ItemTranslation(TSBlocks.Trees.CERULII_FENCE),
			new ItemTranslation(TSBlocks.Trees.CERULII_FENCE_GATE),
			new ItemTranslation(TSBlocks.Trees.CERULII_BUTTON),
			new ItemTranslation(TSBlocks.Trees.CERULII_PRESSURE_PLATE),
			new ItemTranslation(TSBlocks.Trees.CERULII_DOOR),
			new ItemTranslation(TSBlocks.Trees.CERULII_TRAPDOOR),
			new ItemTranslation(TSBlocks.Trees.CERULII_SIGN),
			new ItemTranslation(TSBlocks.Trees.CERULII_HANGING_SIGN),
			new ItemTranslation(TSItems.Magic.CERULII_BOAT),
			new ItemTranslation(TSItems.Magic.CERULII_CHEST_BOAT));

		translationBuilder.add(new Translation("tooltip.effect." + MOD_ID + ".well_fed.applied").text("Your hunger ."));

		translationBuilder.add(
				new EffectTranslation(ModEffects.REDSTONED).flavorText(
						"Your body freezes in time...", "Your body has regained equilibrium.", "Stores all change in health until effect is removed"),
				new EffectTranslation(ModEffects.TETHERED).flavorText(
						"Your inner harmony has been disrupted...", "Your inner harmony has been restored!", "Inhibits teleportation using harmonic means"),
				new EffectTranslation(ModEffects.FLIGHT),
				new EffectTranslation(ModEffects.WELL_FED),
				new EffectTranslation(ModEffects.FORTIFIED).addPotion(),
			//new EffectTranslation(ModEffects.COSMIC_FIRE, false).text("Cosmic Flame"),
				new EffectTranslation(ModEffects.INFESTED).flavorText(
						"Parasites writhe beneath your skin...", "The parasites have left your body.", "Deals an exponential amount of damage from x every second"),
				new EffectTranslation(ModEffects.IRRADIATED).flavorText(
						"Your body is tearing apart...", "The tearing has subsided.", "Deals an exponential amount of damage from x every second"),
			new EffectTranslation(ModEffects.NATURES_BOON).text("Nature's Boon"),
			new EffectTranslation(ModEffects.SET_BONUS_SCRAP_METAL).text("Set Bonus: Scrap Metal"),
			new EffectTranslation(ModEffects.SET_BONUS_DRYADIC).text("Set Bonus: Dryadic"),
			new EffectTranslation(ModEffects.SET_BONUS_ROSE_GOLD).text("Set Bonus: Rose Gold"),
			new EffectTranslation(ModEffects.SET_BONUS_DRUIDIC).text("Set Bonus: Druidic"),
			new EffectTranslation(ModEffects.SET_BONUS_STARSTEEL)
				.text(new JsonTextObject().text("Set Bonus: ").color(GOLD), new JsonTextObject().text("Starsteel").color(STARSTEEL_BASE)),
			new EffectTranslation(ModEffects.SET_BONUS_IMPERIAL).text("Set Bonus: Imperial"),
			new EffectTranslation(ModEffects.SET_BONUS_UNHOLY).text("Set Bonus: Unholy"),
			new EffectTranslation(ModEffects.SET_BONUS_NUCLEAR).text("Set Bonus: Nuclear"),
			new EffectTranslation(ModEffects.SET_BONUS_ARMA_DEI).text("Set Bonus: Arma Dei"),
			new EffectTranslation(ModEffects.SET_BONUS_NANOTECH).text("Set Bonus: Nanotech"),
			new EffectTranslation(ModEffects.SET_BONUS_ZENITHIUM).text("Set Bonus: Zenithium"));

		translationBuilder.addWithParent(new Translation().noName(),
				new EffectTranslation(StatusEffects.POISON).flavorText(
						"Your body is fading away...", "The pain has faded.", "Deals x points of non-lethal damage every second"),
				new EffectTranslation(StatusEffects.WITHER).flavorText(
						"Death eats away at your being...", "Death has granted you freedom.", "Deals x points of damage every second"),
				new EffectTranslation(StatusEffects.DARKNESS).flavorText(
						"The shadows dance around you...", "The light has returned to your surroundings.", "Lowers maximum view distance"),
				new EffectTranslation(StatusEffects.BLINDNESS).flavorText(
						"Your vision darkens...", "Your vision returns.", "Massively lowers maximum view distance"),
				new EffectTranslation(StatusEffects.OOZING).flavorText(
						"Slime leaks from your pores...", "The ooze subsides.", "Upon death, spawns x small slimes"),
				new EffectTranslation(StatusEffects.WEAKNESS).flavorText(
						"Your arms grow heavy...", "The ooze subsides.", "Upon death, spawns x small slimes"),
				new EffectTranslation(StatusEffects.SLOWNESS).flavorText(
						"Your knees grow weak...", "The ooze subsides.", "Upon death, spawns x small slimes"),
				new EffectTranslation(StatusEffects.SATURATION).flavorText(
						"Your stomach is filled with nutrients...", "The ooze subsides.", "Upon death, spawns x small slimes"),
				new EffectTranslation(StatusEffects.HUNGER).flavorText(
						"Your stomach grumbles for living flesh...", "The ooze subsides.", "Upon death, spawns x small slimes"),
				new EffectTranslation(StatusEffects.REGENERATION).flavorText(
						"Your body is rejuvenating...", "The ooze subsides.", "Upon death, spawns x small slimes"),
				new EffectTranslation(StatusEffects.NAUSEA).flavorText(
						"Your head and stomach groan in harmony...", "The ooze subsides.", "Upon death, spawns x small slimes"));

		translationBuilder.add(new ItemTranslation(TSItems.Beta.SALT).tooltip("Used for keeping meat fresh.").textColor(SALT));

		translationBuilder.addPottedPlant(TSBlocks.Plants.TRANQUIL_ROSE, TSBlocks.Plants.POTTED_TRANQUIL_ROSE, "Tranquil Rose", "Smells delightful.", TRANQUIL);
		translationBuilder.addPottedPlant(TSBlocks.Plants.SKULLWEED, TSBlocks.Plants.POTTED_SKULLWEED, "Skullweed", "It's so cold...", SKULLWEED);

		translationBuilder.addWithParent(new Translation().textColor(SHINY_GOLD),
			new ItemTranslation(TSItems.Beta.MIDAS_FRUIT).tooltip("\"Shiny shiny!\"").textColor(SHINY_GOLD),
			new ItemTranslation(TSItems.Beta.GOLDEN_APPLE_JUICE),
			new ItemTranslation(TSItems.Beta.BANANA),
			new ItemTranslation(TSItems.Beta.BANANA_BREAD),
			new ItemTranslation(TSBlocks.Plants.FEATHERCORN).tooltip("Soft and warm."));
		translationBuilder.addWithParent(new Translation().textColor(PEARFRUIT),
			new ItemTranslation(TSItems.Beta.PEARFRUIT).tooltip("As good as gold."),
			new ItemTranslation(TSItems.Beta.PEARFRUIT_JUICE));
		translationBuilder.add(
			new ItemTranslation(TSItems.Beta.RICE_SEEDS),
			new ItemTranslation(TSItems.Beta.RICE_CAKE),
			new ItemTranslation(TSBlocks.Plants.RICE_PLANT),
			new ItemTranslation(TSBlocks.Plants.RICE_BLOCK),
			new ItemTranslation(TSItems.Beta.SUSHI_ROLL),
			new ItemTranslation(TSItems.Beta.TORTILLA),
			new ItemTranslation(TSItems.Beta.BURRITO),
			new ItemTranslation(TSItems.Beta.FRIED_EGG),
			new ItemTranslation(TSItems.Beta.APPLE_JUICE),
			new ItemTranslation(TSItems.Beta.SWEET_BERRY_JUICE),
			new ItemTranslation(TSItems.Beta.SMOKED_FISH).textColor(SMOKED_FISH));
		translationBuilder.addWithParent(new Translation().textColor(STARSTEEL_BASE),
			new ItemTranslation(TSItems.Tech.EMPTY_CAN),
			new ItemTranslation(TSItems.Tech.BEETROOT_SOUP_CAN).text("Can of Beetroot Soup"),
			new ItemTranslation(TSItems.Tech.MUSHROOM_STEW_CAN).text("Can of Mushroom Stew"),
			new ItemTranslation(TSItems.Tech.RABBIT_STEW_CAN).text("Can of Rabbit Stew"),
			new ItemTranslation(TSItems.Tech.MILK_CAN).text("Can of Evaporated Milk"),
			new ItemTranslation(TSItems.Tech.COLA_ORANGE).text("Can of A&L"),
			new ItemTranslation(TSItems.Tech.COLA_GREEN).text("Can of Trola-Cola"),
			new ItemTranslation(TSItems.Tech.COLA_CYAN).text("Can of AquaDei Lite"));

		translationBuilder.add(new ItemTranslation(Items.CAKE).tooltip("It was never a lie. Exile Vilify").noName());

        translationBuilder.add(new ItemTranslation(TSItems.Beta.BROWNIE).tooltip("Comes from outer space?"));
        translationBuilder.add(new ItemTranslation(TSItems.Beta.CHORUS_COBBLER).tooltip("Memah's favorite recipe!").textColor(CHORUS));
        translationBuilder.addWithParent(new Translation().textColor(DUNE_TAN),
			new ItemTranslation(TSItems.Beta.SANDFISH).tooltip("This is not a fish..."),
			new ItemTranslation(TSItems.Beta.COOKED_SANDFISH),
			new ItemTranslation(TSItems.Beta.SIDEWINDER).tooltip("Isn't this a snake?"));

		translationBuilder.addWithParent(new Translation().textColor(FLESH_PUS),
			new ItemTranslation(TSBlocks.Magic.FLESH_BLOCK).tooltip("It's strangely warm..."),
			new ItemTranslation(TSBlocks.Magic.FLESH_VEINS).tooltip("They're spreading..."),
			new ItemTranslation(TSBlocks.Magic.FLESHY_EYE).tooltip("It's staring..."),
			new ItemTranslation(TSBlocks.Magic.FLESHY_PUSTULE).tooltip("It's still beating..."),
			new ItemTranslation(TSItems.Magic.EYE_OF_RUIN).tooltip("What a troubling gaze..."),
			new ItemTranslation(TSItems.Magic.DUBIOUS_BACON).tooltip("Don't ask where it came from..."),
			new ItemTranslation(TSItems.Tech.VILE_SPITTER).tooltip("\"It's alive!\""),
			new ItemTranslation(TSItems.Tech.MEAT_PELLET).tooltip("Only a vile creature would crave this..."));

		translationBuilder.add(new ItemTranslation(TSItems.Beta.REDSTONE_CRYSTAL).tooltip("Sparkles with potential.").text("Crystallized Redstone")
			.textColor(BLOOD_RED));
		translationBuilder.add(new ItemTranslation(TSItems.Beta.BLOOD_DIAMOND).tooltip("Chaos itself shines through.").textColor(BLOOD_RED));
		translationBuilder.add(new ItemTranslation(TSItems.Beta.ASH).tooltip("Contains traces of dark magic.").text("Wither Ash").textColor(TRANQUIL));
		translationBuilder.add(new ItemTranslation(TSItems.Beta.TRANQUIL_DUST).tooltip("Useful in dark alchemy.").text("Tranquil Pollen").textColor(TRANQUIL));

		translationBuilder.addWithParent(new Translation().textColor(SENTINEL_AQUA),
			new ItemTranslation(TSItems.Tech.HARD_LIGHT_PROJECTOR).text("Hard Light Projector").tooltip("Deploys temporary blocks."),
			new ItemTranslation(TSBlocks.Tech.HARD_LIGHT).text("Hard Light"),
			new ItemTranslation(TSBlocks.Tech.HARD_LIGHT_BARRIER).text("Hard Light Barrier").tooltip("Creative only!"),
			new ItemTranslation(TSItems.Tech.HOLOGRAPHIC_SIGN).text("Holographic Sign"),
			new ItemTranslation(TSItems.Tech.HOLOGRAPHIC_HANGING_SIGN).text("Holographic Hanging Sign"));
		translationBuilder.addWithParent(new Translation().textColor(SENTINEL_GOLD),
			new ItemTranslation(TSItems.Tech.CAUTION_HARD_LIGHT_PROJECTOR).text("Hard Light Projector").tooltip("Deploys temporary blocks."),
			new ItemTranslation(TSBlocks.Tech.CAUTION_HARD_LIGHT).text("Hard Light"),
			new ItemTranslation(TSBlocks.Tech.CAUTION_HARD_LIGHT_BARRIER).text("Hard Light Barrier").tooltip("Creative only!"),
			new ItemTranslation(TSItems.Tech.CAUTION_HOLOGRAPHIC_SIGN).text("Holographic Sign"),
			new ItemTranslation(TSItems.Tech.CAUTION_HOLOGRAPHIC_HANGING_SIGN).text("Holographic Hanging Sign"));
		translationBuilder.addWithParent(new Translation().textColor(SENTINEL_CRIMSON),
			new ItemTranslation(TSItems.Tech.SENTINEL_HARD_LIGHT_PROJECTOR).text("Hard Light Projector").tooltip("Deploys temporary blocks."),
			new ItemTranslation(TSBlocks.Tech.SENTINEL_HARD_LIGHT).text("Hard Light"),
			new ItemTranslation(TSBlocks.Tech.SENTINEL_HARD_LIGHT_BARRIER).text("Hard Light Barrier").tooltip("Creative only!"),
			new ItemTranslation(TSItems.Tech.SENTINEL_HOLOGRAPHIC_SIGN).text("Holographic Sign"),
			new ItemTranslation(TSItems.Tech.SENTINEL_HOLOGRAPHIC_HANGING_SIGN).text("Holographic Hanging Sign"));
		translationBuilder.add(
				new ItemTranslation(TSBlocks.Tech.CHAMBER_BLOCK),
				new ItemTranslation(TSBlocks.Tech.LIGHT_CHAMBER_BLOCK),
				new ItemTranslation(TSBlocks.Tech.DARK_CHAMBER_BLOCK),
				new ItemTranslation(TSBlocks.Tech.BLUE_AGILITY_BLOCK).textColor(SENTINEL_AQUA2),
				new ItemTranslation(TSBlocks.Tech.ORANGE_AGILITY_BLOCK).textColor(SENTINEL_GOLD2),
				new ItemTranslation(TSBlocks.Tech.PHASEPLATE));

		//Foundation Blocks
		translationBuilder.add(
			new ItemTranslation(TSBlocks.Magic.CRACKED_END_STONE_BRICKS),
			new ItemTranslation(TSBlocks.Magic.CHISELED_END_STONE_BRICKS),
			new ItemTranslation(TSBlocks.Magic.END_STONE_BRICK_COLUMN));
		translationBuilder.addWithParent(new Translation().textColor(LIGHT_PURPLE),
			new ItemTranslation(TSBlocks.Magic.IMPIOUS_END_STONE),
			new ItemTranslation(TSBlocks.Magic.IMPIOUS_END_STONE_BRICKS),
			new ItemTranslation(TSBlocks.Magic.CRACKED_IMPIOUS_END_STONE_BRICKS),
			new ItemTranslation(TSBlocks.Magic.CHISELED_IMPIOUS_END_STONE_BRICKS),
			new ItemTranslation(TSBlocks.Magic.IMPIOUS_END_STONE_BRICK_COLUMN));
		translationBuilder.addWithParent(new Translation().textColor(LIGHT_PURPLE),
			new ItemTranslation(TSBlocks.Magic.DEIFIC_END_STONE),
			new ItemTranslation(TSBlocks.Magic.DEIFIC_END_STONE_BRICKS),
			new ItemTranslation(TSBlocks.Magic.CRACKED_DEIFIC_END_STONE_BRICKS),
			new ItemTranslation(TSBlocks.Magic.CHISELED_DEIFIC_END_STONE_BRICKS),
			new ItemTranslation(TSBlocks.Magic.DEIFIC_END_STONE_BRICK_COLUMN));
		translationBuilder.addWithParent(new Translation().textColor(SNOWSTONE, SNOWSTONE.darker()),
			new ItemTranslation(TSBlocks.Magic.SNOWSTONE),
			new ItemTranslation(TSBlocks.Magic.SNOWSTONE_STAIRS),
			new ItemTranslation(TSBlocks.Magic.SNOWSTONE_SLAB),
			new ItemTranslation(TSBlocks.Magic.SNOWSTONE_WALL),
			new ItemTranslation(TSBlocks.Magic.SMOOTH_SNOWSTONE),
			new ItemTranslation(TSBlocks.Magic.SMOOTH_SNOWSTONE_STAIRS),
			new ItemTranslation(TSBlocks.Magic.SMOOTH_SNOWSTONE_SLAB),
			new ItemTranslation(TSBlocks.Magic.SMOOTH_SNOWSTONE_WALL),
			new ItemTranslation(TSBlocks.Magic.CUT_SNOWSTONE),
			new ItemTranslation(TSBlocks.Magic.CUT_SNOWSTONE_SLAB),
			new ItemTranslation(TSBlocks.Magic.CHISELED_SNOWSTONE));
		translationBuilder.add(
			new ItemTranslation(TSBlocks.Magic.MOSS_STAIRS),
			new ItemTranslation(TSBlocks.Magic.MOSS_SLAB),
			new ItemTranslation(TSBlocks.Magic.DIRT_STAIRS),
			new ItemTranslation(TSBlocks.Magic.DIRT_SLAB),
			new ItemTranslation(TSBlocks.Magic.COARSE_DIRT_STAIRS),
			new ItemTranslation(TSBlocks.Magic.COARSE_DIRT_SLAB));

		translationBuilder.add(new ItemTranslation(TSBlocks.Tech.VENDOR));
		translationBuilder.add(new ItemTranslation(TSBlocks.Tech.RECONSTRUCTION_TABLE).textColor(SENTINEL_AQUA1));
		translationBuilder.add(new ItemTranslation(TSBlocks.Tech.MODIFICATION_TABLE));

		translationBuilder.add(
			new Translation(ModSounds.FLESH_AMBIENT).text("Flesh wriggles"),
			new Translation(ModSounds.BLASTER_SHOOT).text("Projectile fired"),
			new Translation(ModSounds.DATABASE_OPEN).text("Codex opened"),
			new Translation(ModSounds.DATABASE_FLIP).text("Codex flipped"),
			new Translation(ModSounds.NOTCH_UP).text("Powering up"));
		translationBuilder.addWithParent(new Translation().text("Phaser reloads"),
			new Translation(ModSounds.PISTOL_RELOAD),
			new Translation(ModSounds.SHOTGUN_RELOAD),
			new Translation(ModSounds.SNIPER_RELOAD),
			new Translation(ModSounds.BIO_RELOAD));

        translationBuilder.add(new Translation("trevorssentinels.holocodex.name").text("HoloCodex").textColor(SENTINEL_AQUA));

		translationBuilder.addAdvancement("trevorssentinels",new Translation().text("Trevor's Sentinels").underlined()
			.textColor(SENTINEL_AQUA3, SENTINEL_AQUA2, SENTINEL_AQUA1, SENTINEL_GOLD2, SENTINEL_CRIMSON1, SENTINEL_CRIMSON2, SENTINEL_CRIMSON3),
				new Translation().text("Obtain a shard of long-abandoned metal"));
		translationBuilder.addAdvancement("industrial", new Translation().text("Industrial"), new Translation().text("Obtain any industrial-tier item"));
		translationBuilder.addAdvancement("ancient_metal", new Translation().text("Ancient Metal"), new Translation().text("Obtain an ingot of Imperial Alloy"));
		translationBuilder.addAdvancement("ancient_metal2", new Translation().text("Good Soldiers Follow Orders"),
			new Translation().text("Obtain equipment made from Imperial Alloy"));
		translationBuilder.addAdvancement("rose_gold", new Translation().text("Every Rose..."), new Translation().text("Obtain equipment made from Rose Gold"));
		translationBuilder.addAdvancement("thanatu", new Translation().text("...Has its Thorn"), new Translation().text("Acquire the tools of the demon lord"));
		translationBuilder.addAdvancement("cerberus", new Translation().text("False Idol"), new Translation().text("Defeat Cerberus, and release the seal on ancient forces"));
		translationBuilder.addAdvancement("uranium", new Translation().text("Nuclear Age"), new Translation().text("Obtain equipment made from Nuclear Ingots"));
		translationBuilder.addAdvancement("zenithium", new Translation().text("Infinity Squared"), new Translation().text("Acquire a cluster of Zenithium"));
		translationBuilder.addAdvancement("zenithium2", new Translation().text("Infinity +1 Everything"), new Translation().text("\"Honey, where's my super suit?\""));

		translationBuilder.addAdvancement("brownie", new Translation().text("This Edible Ain't-"), new Translation().text("Consume a Brownie"));
		translationBuilder.addAdvancement("chainsaw", new Translation().text("Chainsaws, Man"), new Translation().text("Craft a Scrap Metal Chainsaw"));
		translationBuilder.addAdvancement("bacon_and_eggs", new Translation().text("Bacon and Eggs"), new Translation().text("Part of a complete breakfast!"));

		translationBuilder.add(new Translation("pillar.trevorssentinels.chaos").text("Chaos").textColor(Color.RED));
		translationBuilder.add(new Translation("pillar.trevorssentinels.truth").text("Truth").textColor(Color.ORANGE));
		translationBuilder.add(new Translation("pillar.trevorssentinels.order").text("Order").textColor(Color.YELLOW));
        translationBuilder.add(new Translation("pillar.trevorssentinels.balance").text("Balance").textColor(Color.GREEN));
        translationBuilder.add(new Translation("pillar.trevorssentinels.wisdom").text("Wisdom").textColor(Color.CYAN));
        translationBuilder.add(new Translation("pillar.trevorssentinels.tranquility").text("Tranquility").textColor(TRANQUIL));
        translationBuilder.add(new Translation("pillar.trevorssentinels.purity").text("Purity").textColor(Color.WHITE));

        translationBuilder.add("death.attack.redstoned",
			new JsonTextObject().index(0),
			new JsonTextObject().text(" gave into the pain"));
        translationBuilder.add("death.attack.infested",
			new JsonTextObject().index(0),
			new JsonTextObject().text(" didn't make it"));
        translationBuilder.add("death.attack.irradiated",
			new JsonTextObject().index(0),
			new JsonTextObject().text(" ate one too many bananas"));
        translationBuilder.add("death.attack.phaser_projectile",
			new JsonTextObject().index(0),
			new JsonTextObject().text(" phased away thanks to "),
			new JsonTextObject().index(1));
        translationBuilder.add("death.attack.phaser_projectile.item",
			new JsonTextObject().index(0),
			new JsonTextObject().text(" phased away thanks to "),
			new JsonTextObject().index(1),
			new JsonTextObject().text(" and their "),
			new JsonTextObject().index(2));
        translationBuilder.add("death.attack.shard_projectile",
			new JsonTextObject().index(1),
			new JsonTextObject().text(" turned "),
			new JsonTextObject().index(0),
			new JsonTextObject().text(" into a pincushion"));
        translationBuilder.add("death.attack.shard_projectile.item",
			new JsonTextObject().index(1),
			new JsonTextObject().text(" turned "),
			new JsonTextObject().index(0),
			new JsonTextObject().text(" into a pincushion using "),
			new JsonTextObject().index(2));
        translationBuilder.add("death.attack.dagger_projectile",
			new JsonTextObject().index(0), //%1$s
			new JsonTextObject().text(" was shot through the heart, and "),
			new JsonTextObject().index(1), //%2$s
			new JsonTextObject().text(" is to blame"));
        translationBuilder.add("death.attack.dagger_projectile.item",
			new JsonTextObject().index(0), //%1$s
			new JsonTextObject().text(" was shot through the heart, and "),
			new JsonTextObject().index(1), //%2$s
			new JsonTextObject().text(" is to blame. They give "),
			new JsonTextObject().index(2), //%3$s
			new JsonTextObject().text(" a bad name"));

        translationBuilder.add(new Translation("key.category.trevorssentinels.trevorssentinels").text("Trevor's Sentinels").underlined()
			.textColor(SENTINEL_AQUA3, SENTINEL_AQUA2, SENTINEL_AQUA1, SENTINEL_GOLD2, SENTINEL_CRIMSON1, SENTINEL_CRIMSON2, SENTINEL_CRIMSON3));
        translationBuilder.add(new Translation("key.trevorssentinels.style_switch").text("Style Switch"));
        translationBuilder.add(new Translation("key.trevorssentinels.reload").text("Reload"));

        translationBuilder.add(new Translation(Identifier.of(MOD_ID, "hologui")).text("Sentinel HoloGUI").textColor(SENTINEL_AQUA));
        translationBuilder.add(new Translation(Identifier.of(MOD_ID, "vanilla_extensions")).text("Vanilla Extensions"));
        translationBuilder.add(new Translation(Identifier.of(MOD_ID, "legacy")).text("Legacy Resources"));

		translationBuilder.add(
			new Translation(ModEntities.SHARD_PROJECTILE).text(Text.translatable(TSItems.Tech.SCRAP_METAL_SHARD.getTranslationKey()).getString()),
			new Translation(ModEntities.PHASER_PROJECTILE),
			new Translation(ModEntities.DELAYED_EXPLOSIVE),
			new Translation(ModEntities.SENTINEL),
			new Translation(ModEntities.JANITOR_DROID),
			new Translation(ModEntities.FLORBUS),
			new Translation("entity.minecraft.villager.engineer").text("Engineer").textColor(AQUA),
			new Translation("entity.minecraft.villager.demolitionist").text("Demolitionist").textColor(RED),
			new Translation("entity.minecraft.villager.acolyte").text("Acolyte").textColor(LIGHT_PURPLE),
			new Translation("entity.minecraft.villager.witherseer").text("Witherseer").textColor(TRANQUIL));

        translationBuilder.add(new Translation("gamerule.trevorssentinels:useVelocityFallDamage").text("Use velocity-based fall damage"));
        translationBuilder.add(new Translation("gamerule.trevorssentinels:useVelocityFallDamage.description")
			.text("If enabled, fall damage will be calculated based on velocity."));
        translationBuilder.add(new Translation("gamerule.trevorssentinels:milkCuresPotionEffects").text("Drinking milk cures potion effects"));
        translationBuilder.add(new Translation("tooltip.trevorssentinels:milkCuresPotionEffects").text("Effects will not be cured!"));
        translationBuilder.add(new Translation("gamerule.trevorssentinels:milkCuresPotionEffects.description")
			.text("If enabled, fall damage will be calculated based on velocity."));

		translationBuilder.add(new Translation("itemGroup." + MOD_ID + ".sentinels").text("Trevor's Sentinels").underlined()
			.textColor(SENTINEL_AQUA3, SENTINEL_AQUA2, SENTINEL_AQUA1, SENTINEL_GOLD2, SENTINEL_CRIMSON1, SENTINEL_CRIMSON2, SENTINEL_CRIMSON3));
		translationBuilder.add(new Translation("itemGroup." + MOD_ID + ".sentinels.tab.magic").text("Magic").textColor(SHINY_GOLD));
		translationBuilder.add(new Translation("itemGroup." + MOD_ID + ".sentinels.tab.tech").text("Tech").textColor(STARSTEEL));
		translationBuilder.add(new Translation("itemGroup." + MOD_ID + ".sentinels.tab.beta").text("Beta").textColor(CERULII).obfuscated(true));
		translationBuilder.add(new Translation("itemGroup." + MOD_ID + ".sentinels.button.wiki").text("Wiki").textColor(VIRIDIAN));

		translationBuilder.add(new Translation("item." + MOD_ID + ".custom_phaser.comet").text("Comet Phase Pistol").textColor(SCRAP_METAL));
		translationBuilder.add(new Translation("item." + MOD_ID + ".custom_phaser.lunar").text("Lunar Phase Rifle").textColor(SENTINEL_GOLD));
		translationBuilder.add(new Translation("item." + MOD_ID + ".custom_phaser.pandemic").text("Pandemic Phase Annihilator").textColor(NUCLEAR));
		translationBuilder.add(new Translation("item." + MOD_ID + ".custom_phaser.serenity").text("Serenity Phase Rifle").textColor(SENTINEL_AQUA));
		translationBuilder.add(new Translation("item." + MOD_ID + ".custom_phaser.deathgun").text("Jordan's Deathgun MK II").textColor(DEATHGUN_NAME));
		translationBuilder.add(new Translation("item." + MOD_ID + ".custom_phaser.bfg").text("The Dark Angels' Symphony").textColor(BLOOD_RED));

		translationBuilder.add(new Translation("tooltip.rarity." + MOD_ID + ".common").text("Common").textColor(WHITE, PURE));
		translationBuilder.add(new Translation("tooltip.rarity." + MOD_ID + ".uncommon").text("Uncommon").textColor(GREEN, VIRIDIAN));
		translationBuilder.add(new Translation("tooltip.rarity." + MOD_ID + ".rare").text("Rare").textColor(AQUA, SENTINEL_AQUA2));
		translationBuilder.add(new Translation("tooltip.rarity." + MOD_ID + ".epic").text("Epic").textColor(LIGHT_PURPLE, BLUE));
		translationBuilder.add(new Translation("tooltip.rarity." + MOD_ID + ".legendary").text("Legendary").textColor(SHINY_GOLD, HELLFIRE));

		translationBuilder.add(new Translation("tooltip.item." + MOD_ID + ".phaser.details").text("Phaser Details:"));
		translationBuilder.add(new Translation("tooltip.item." + MOD_ID + ".phaser.modifiers").text("Phaser Modifiers:"));
		translationBuilder.add(new Translation("tooltip.item." + MOD_ID + ".phaser.decal_color").text("Decal Color: "));
		translationBuilder.add(new Translation("tooltip.item." + MOD_ID + ".phaser.projectile_color").text("Projectile Color: "));
		translationBuilder.add(new Translation("tooltip.item." + MOD_ID + ".phaser.projectile_damage").text("Projectile Damage: "));
		translationBuilder.add(new Translation("tooltip.item." + MOD_ID + ".phaser.projectile_lifetime").text("Projectile Lifetime: "));
		translationBuilder.add(new Translation("tooltip.item." + MOD_ID + ".phaser.projectile_inaccuracy").text("Projectile Inaccuracy: "));
		translationBuilder.add(new Translation("tooltip.item." + MOD_ID + ".phaser.projectile_recoil").text("Projectile Recoil: "));
		translationBuilder.add(new Translation("tooltip.item." + MOD_ID + ".phaser.burst_projectiles").text("Burst Projectiles: "));
		translationBuilder.add(new Translation("tooltip.item." + MOD_ID + ".phaser.burst_cooldown").text("Burst Cooldown: "));
		translationBuilder.add(new Translation("tooltip.item." + MOD_ID + ".phaser.reload_cooldown").text("Reload Cooldown: "));
		translationBuilder.add(new Translation("tooltip.item." + MOD_ID + ".phaser.automatic_reloading").text("Automatic Reloading"));
		translationBuilder.add(new Translation("tooltip.item." + MOD_ID + ".phaser.lingering_effects").text("Lingering Effects"));
		translationBuilder.add(new Translation("tooltip.item." + MOD_ID + ".phaser.projectile_effects").text("Projectile Effects:"));
		translationBuilder.add(new Translation("tooltip.item." + MOD_ID + ".phaser.applies").text("Applies "));

		translationBuilder.add(new ItemTranslation(Items.APPLE).tooltip("The fundamental fruit.").noName());

		translationBuilder.add(new Translation("tooltip." + MOD_ID + ".set_bonus_active").text("Set bonus active!"));
		translationBuilder.add(new Translation("tooltip." + MOD_ID + ".set_bonus_inactive").text("Set bonus inactive!"));
        translationBuilder.add(new Translation("tooltip." + MOD_ID + ".style").text("Style: "));
        translationBuilder.add(new Translation("tooltip." + MOD_ID + ".mode").text("Mode: "));
        translationBuilder.add(new Translation("tooltip." + MOD_ID + ".style_switch").text(" to switch style"));
        translationBuilder.addNumbered(new Translation("style.item." + MOD_ID + ".pappym_blade."),
			new Translation().text("Trickster"), new Translation().text("Paladin"), new Translation().text("Predator"), new Translation().text("Guardian"));
        translationBuilder.addNumbered(new Translation("style.item." + MOD_ID + ".thanatu_blade."),
			new Translation().text("Riftwalker"), new Translation().text("Riftcaller"));
        translationBuilder.addNumbered(new Translation("style.item." + MOD_ID + ".lilith_blade."),
			new Translation().text("Holy Retribution"), new Translation().text("ENDLESS LIGHT"));
        translationBuilder.addNumbered(new Translation("style.item." + MOD_ID + ".phaser."),
			new Translation().text("Manual"), new Translation().text("Automatic"));
		translationBuilder.addNumbered(new Translation(MOD_ID +".worldLevelTooLow."),
			new Translation().text("Something is wrong..."),
			new Translation().text("Demonic power has too much of a hold over the world!").textColor(LIGHT_PURPLE),
			new Translation().text("The power is too strong!").textColor(BLUE));
		translationBuilder.add(new Translation(MOD_ID +".worldLevelTooLow.other").text("A strange force pushes back!"));
		translationBuilder.add(new Translation("tooltip." + MOD_ID + ".rift_timer").text("Rift %s % stable"));
		translationBuilder.add(new Translation("tooltip." + MOD_ID + ".bound_position").text(
				new JsonTextObject().text("Bound to "), new JsonTextObject().index(0), new JsonTextObject().text(", "),
				new JsonTextObject().index(1), new JsonTextObject().text(", "), new JsonTextObject().index(2)));
		translationBuilder.add(new Translation("tooltip." + MOD_ID + ".position_not_bound").text("Item not bound to a position!"));
		translationBuilder.add(new Translation("tooltip." + MOD_ID + ".teleported").text(
				new JsonTextObject().text("Teleported to "), new JsonTextObject().index(0), new JsonTextObject().text(", "),
				new JsonTextObject().index(1), new JsonTextObject().text(", "), new JsonTextObject().index(2)));
		translationBuilder.add(new Translation("tooltip." + MOD_ID + ".impossible_teleport").text("Unable to teleport between dimensions!"));
		translationBuilder.add(new Translation("tooltip." + MOD_ID + ".show_advanced_tooltip").text(" for extended tooltip"));

		translationBuilder.add(new Translation("tooltip." + MOD_ID + ".portkey_hotkey_1").text(new JsonTextObject().text("Press "),
				new JsonTextObject().index(0), new JsonTextObject().text(" to teleport to bound location,")));
		translationBuilder.add(new Translation("tooltip." + MOD_ID + ".portkey_hotkey_2").text("or while crouching to bind your location."));

        if (dataOutput.getModContainer().findPath("assets/trevorssentinels/lang/en_us.existing.json").isPresent()) try {
            translationBuilder.add(dataOutput.getModContainer().findPath("assets/trevorssentinels/lang/en_us.existing.json").get());
        } catch (Exception e) { throw new RuntimeException("Failed to add existing language file!", e); }
    }
}
