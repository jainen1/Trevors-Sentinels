package net.trevorskullcrafter.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.trevorskullcrafter.block.entity.HardLightBlockEntity;
import net.trevorskullcrafter.block.entity.ModBlockEntities;
import org.jetbrains.annotations.Nullable;

public class HardLightBlock extends BlockWithEntity {
    public HardLightBlock(Settings settings) { super(settings); }

	@Override protected MapCodec<? extends BlockWithEntity> getCodec() { return null; }

	@Override public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        world.playSound(null, pos, SoundEvents.BLOCK_AMETHYST_BLOCK_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);
        super.onPlaced(world, pos, state, placer, itemStack);
    }

    @Override public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return world instanceof ServerWorld ? null : validateTicker(type, ModBlockEntities.HARD_LIGHT, HardLightBlockEntity::tick);
    }

    @Nullable @Override public BlockEntity createBlockEntity(BlockPos pos, BlockState state) { return ModBlockEntities.HARD_LIGHT.instantiate(pos, state); }
    @Override public BlockRenderType getRenderType(BlockState state) { return BlockRenderType.MODEL; }
    public VoxelShape getCameraCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) { return VoxelShapes.empty(); }
    public float getAmbientOcclusionLightLevel(BlockState state, BlockView world, BlockPos pos) { return 1.0F; }
	@Override protected boolean isTransparent(BlockState state, BlockView world, BlockPos pos) { return true; }

	public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
        return stateFrom.isOf(this) || super.isSideInvisible(state, stateFrom, direction);
    }
}
