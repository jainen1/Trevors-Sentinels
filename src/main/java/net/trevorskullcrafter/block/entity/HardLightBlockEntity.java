package net.trevorskullcrafter.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class HardLightBlockEntity extends BlockEntity {
    int lifetime;
    public HardLightBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.HARD_LIGHT, pos, state);
        lifetime = 100;
    }

    @Override protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        nbt.putInt("lifetime", lifetime);
        super.writeNbt(nbt, registryLookup);
    }

    @Override protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        lifetime = nbt.getInt("lifetime");
    }

    public static void tick(World world, BlockPos pos, BlockState state, HardLightBlockEntity blockEntity) {
        --blockEntity.lifetime;
        if(blockEntity.lifetime < 1){
            world.breakBlock(pos, false);
            world.removeBlockEntity(pos);
			blockEntity.markRemoved();
        }
    }
}
