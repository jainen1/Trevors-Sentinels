package net.trevorskullcrafter.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class ReconstructionTableBlockEntity extends BlockEntity {
    public ReconstructionTableBlockEntity(BlockPos pos, BlockState state) { super(ModBlockEntities.RECONSTRUCTION_TABLE, pos, state); }
}
