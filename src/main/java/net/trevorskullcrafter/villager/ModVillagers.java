package net.trevorskullcrafter.villager;

import com.google.common.collect.ImmutableSet;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.object.builder.v1.villager.VillagerProfessionBuilder;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradedItem;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;
import net.trevorskullcrafter.TrevorsSentinels;

public class ModVillagers {

    public static final PointOfInterestType DEMOLITIONIST_POI = registerPOI("demolitionist_poi", Blocks.TNT);
    public static final VillagerProfession DEMOLITIONIST = registerProfession("demolitionist",
            RegistryKey.of(Registries.POINT_OF_INTEREST_TYPE.getKey(), Identifier.of(TrevorsSentinels.MOD_ID,"demolitionist_poi")));

    public static final PointOfInterestType WITHERSEER_POI = registerPOI("witherseer_poi", Blocks.RESPAWN_ANCHOR);
    public static final VillagerProfession WITHERSEER = registerProfession("witherseer",
            RegistryKey.of(Registries.POINT_OF_INTEREST_TYPE.getKey(), Identifier.of(TrevorsSentinels.MOD_ID,"witherseer_poi")));

    public static final PointOfInterestType ACOLYTE_POI = registerPOI("acolyte_poi", Blocks.DRAGON_EGG);
    public static final VillagerProfession ACOLYTE = registerProfession("acolyte",
            RegistryKey.of(Registries.POINT_OF_INTEREST_TYPE.getKey(), Identifier.of(TrevorsSentinels.MOD_ID,"acolyte_poi")));


    public static VillagerProfession registerProfession(String name, RegistryKey<PointOfInterestType> type) {
        return Registry.register(Registries.VILLAGER_PROFESSION, Identifier.of(TrevorsSentinels.MOD_ID, name),
			VillagerProfessionBuilder.create().id(Identifier.of(TrevorsSentinels.MOD_ID, name)).workstation(type).workSound(SoundEvents.ENTITY_VILLAGER_WORK_ARMORER).build());
    }

    public static PointOfInterestType registerPOI(String name, Block block){
		Identifier id = Identifier.of(TrevorsSentinels.MOD_ID, name);
		PointOfInterestHelper.register(id, 1, 1, ImmutableSet.copyOf(block.getStateManager().getStates()));
        return Registries.POINT_OF_INTEREST_TYPE.get(RegistryKey.of(RegistryKeys.POINT_OF_INTEREST_TYPE, id));
    }

    public static void registerVillagers(){ TrevorsSentinels.LOGGER.debug("Fixing the unemployment rate... ("+ TrevorsSentinels.MOD_ID+")"); }

    public static void registerTrades(){
        TradeOfferHelper.registerVillagerOffers(DEMOLITIONIST,1, factories -> {
            factories.add((entity, random) -> new TradeOffer(
				new TradedItem(Items.EMERALD, 16),
				new ItemStack(Items.GUNPOWDER,64),
				4,2,0.02f));
            factories.add((entity, random) -> new TradeOffer(
				new TradedItem(Items.EMERALD, 6),
				new ItemStack(Items.FIREWORK_STAR,2),
				6,2,0.02f));
            factories.add((entity, random) -> new TradeOffer(
				new TradedItem(Items.EMERALD, 16),
				new ItemStack(Items.FIREWORK_ROCKET,16),
				4,2,0.02f));
            factories.add((entity, random) -> new TradeOffer(
				new TradedItem(Items.EMERALD, 3),
				new ItemStack(Items.TNT,1),
				8,2,0.02f));
        });

        //TradeOfferHelper.registerVillagerOffers(WITHERSEER,1, factories -> {
		//	factories.add((entity, random) -> new TradeOffer(
		//		new ItemStack(Magic.SOUL, 2),
		//		new ItemStack(Magic.TRANQUIL_ESSENCE,8),
		//		new ItemStack(Magic.DEMON_SOUL, 1),
		//		16,5,0.02f));
        //});
        TradeOfferHelper.registerVillagerOffers(WITHERSEER,2, factories -> {
            factories.add((entity, random) -> new TradeOffer(
				new TradedItem(Items.EMERALD, 3),
				new ItemStack(Items.TNT,1),
				8,2,0.02f));
        });
    }
}
