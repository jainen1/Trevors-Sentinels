package net.trevorskullcrafter.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.trevorskullcrafter.block.entity.ModificationTableBlockEntity;
import org.jetbrains.annotations.Nullable;

public class ModificationTableBlock extends FacingBlock implements BlockEntityProvider {
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    public ModificationTableBlock(Settings settings){ super(settings); }

	@Override protected MapCodec<? extends FacingBlock> getCodec() { return null; }

	@Nullable @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) { return this.getDefaultState().with(FACING, ctx.getPlayerLookDirection().getOpposite()); }

    @Override protected void appendProperties(StateManager.Builder<Block, BlockState> builder) { builder.add(FACING); }

    @Nullable @Override public BlockEntity createBlockEntity(BlockPos pos, BlockState state) { return new ModificationTableBlockEntity(pos, state); }

    @Override public BlockRenderType getRenderType(BlockState state){ return BlockRenderType.ENTITYBLOCK_ANIMATED; }
}
