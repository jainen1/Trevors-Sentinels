package net.trevorskullcrafter.trevorssentinels.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.trevorskullcrafter.trevorssentinels.block.ModBlocks;
import net.trevorskullcrafter.trevorssentinels.trevorssentinels;

import static net.trevorskullcrafter.trevorssentinels.trevorssentinels.MOD_ID;

public class ModBlockEntities {
    public static BlockEntityType<VendorBlockEntity> VENDOR;
    public static BlockEntityType<SuperforgeBlockEntity> SUPERFORGE;
    public static BlockEntityType<ModificationTableBlockEntity> MODIFICATION_TABLE;
    public static BlockEntityType<HardLightBlockEntity> HARD_LIGHT;

    public static void registerBlockEntities(){
        trevorssentinels.LOGGER.info("Registering block entities... (" + MOD_ID + ")");
        VENDOR = Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(MOD_ID, "vendor"),
                FabricBlockEntityTypeBuilder.create(VendorBlockEntity::new, ModBlocks.VENDOR).build(null));

        SUPERFORGE = Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(MOD_ID, "superforge"),
                FabricBlockEntityTypeBuilder.create(SuperforgeBlockEntity::new, ModBlocks.SUPERFORGE).build(null));

        MODIFICATION_TABLE = Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(MOD_ID, "modification_table"),
                FabricBlockEntityTypeBuilder.create(ModificationTableBlockEntity::new, ModBlocks.MODIFICATION_TABLE).build(null));

        HARD_LIGHT = Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(MOD_ID, "hard_light"),
                FabricBlockEntityTypeBuilder.create(HardLightBlockEntity::new, ModBlocks.HARD_LIGHT, ModBlocks.CAUTION_HARD_LIGHT, ModBlocks.SENTINEL_HARD_LIGHT).build(null));
    }
}