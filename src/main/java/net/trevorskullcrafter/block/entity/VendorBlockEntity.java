package net.trevorskullcrafter.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class VendorBlockEntity extends BlockEntity {
    public VendorBlockEntity(BlockPos pos, BlockState state) { super(ModBlockEntities.VENDOR, pos, state); }
}