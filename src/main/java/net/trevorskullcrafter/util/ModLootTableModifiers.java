package net.trevorskullcrafter.util;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;
import net.trevorskullcrafter.TrevorsSentinels;
import net.trevorskullcrafter.item.TSItems;

public class ModLootTableModifiers {
    private static final Identifier GRASS_BLOCK_ID = Identifier.of("minecraft", "blocks/grass");
    private static final Identifier IGLOO_STRUCTURE_CHEST_ID = Identifier.of("minecraft", "chests/igloo/chest");
    private static final Identifier WITHER_SKELETON_ID = Identifier.of("minecraft", "entities/wither_skeleton");
    private static final Identifier ENDERMAN_ID = Identifier.of("minecraft", "entities/enderman");

    public static void modifyLootTables(){
        TrevorsSentinels.LOGGER.info("Placing shards in grass... (" + TrevorsSentinels.MOD_ID + ")");
        LootTableEvents.MODIFY.register((key, tableBuilder, source) -> {

            if(source.isBuiltin() && key.equals(GRASS_BLOCK_ID)){
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.05f).build())
                        .with(ItemEntry.builder(TSItems.Beta.RICE_SEEDS))
                        .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0f)).build());
                tableBuilder.pool(poolBuilder.build());
            }

            if(source.isBuiltin() && key.equals(WITHER_SKELETON_ID)){
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.25f).build())
                        .with(ItemEntry.builder(TSItems.Beta.ASH))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0f, 3.0f)).build());
                tableBuilder.pool(poolBuilder.build());
            }

            if(source.isBuiltin() && key.equals(ENDERMAN_ID)){
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(3))
                        .conditionally(RandomChanceLootCondition.builder(0.25f).build())
                        .with(ItemEntry.builder(TSItems.Magic.UNHOLY_CORE))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0f, 3.0f)).build());
                tableBuilder.pool(poolBuilder.build());
            }
        });

        /* .conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.KILLER,
        new EntityPredicate.Builder().equipment(EntityEquipmentPredicate.Builder.create()
        .mainhand(ItemPredicate.Builder.create().items(Items.GOLDEN_SWORD).build()).build()))) */
    }
}
