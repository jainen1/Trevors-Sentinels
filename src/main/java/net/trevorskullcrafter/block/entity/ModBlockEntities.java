package net.trevorskullcrafter.block.entity;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.trevorskullcrafter.TrevorsSentinels;
import net.trevorskullcrafter.block.TSBlocks;

public class ModBlockEntities {
    public static BlockEntityType<VendorBlockEntity> VENDOR = register("vendor", BlockEntityType.Builder.create(VendorBlockEntity::new, TSBlocks.Tech.VENDOR).build());
    public static BlockEntityType<ReconstructionTableBlockEntity> RECONSTRUCTION_TABLE = register("superforge", BlockEntityType.Builder.create(ReconstructionTableBlockEntity::new, TSBlocks.Tech.RECONSTRUCTION_TABLE).build());
    public static BlockEntityType<ModificationTableBlockEntity> MODIFICATION_STATION = register("modification_station", BlockEntityType.Builder.create(ModificationTableBlockEntity::new, TSBlocks.Tech.MODIFICATION_TABLE).build());
    public static BlockEntityType<HardLightBlockEntity> HARD_LIGHT = register("hard_light", BlockEntityType.Builder.create(HardLightBlockEntity::new, TSBlocks.Tech.HARD_LIGHT, TSBlocks.Tech.CAUTION_HARD_LIGHT, TSBlocks.Tech.SENTINEL_HARD_LIGHT).build());
    public static final BlockEntityType<TeleporterBlockEntity> TELEPORTER = register("teleporter", BlockEntityType.Builder.create(TeleporterBlockEntity::new, TSBlocks.Magic.TELEPORTER).build());
    public static final BlockEntityType<PhaseplateBlockEntity> PHASEPLATE = register("phaseplate", BlockEntityType.Builder.create(PhaseplateBlockEntity::new, TSBlocks.Tech.PHASEPLATE).build());

    public static <T extends BlockEntity> BlockEntityType<T> register(String name, BlockEntityType<T> type){
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(TrevorsSentinels.MOD_ID, name), type);
    }

    public static void registerBlockEntities(){ TrevorsSentinels.LOGGER.info("Registering block entities... (" + TrevorsSentinels.MOD_ID + ")"); }
}
