package net.trevorskullcrafter.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FacingBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class FleshyEyeBlock extends FacingBlock {
    public static final BooleanProperty CLICKED = BooleanProperty.of("clicked");
    public FleshyEyeBlock(Settings settings) { super(settings); this.setDefaultState(this.stateManager.getDefaultState().with(CLICKED, true)); }

	@Override protected MapCodec<? extends FacingBlock> getCodec() { return null; }

	@Override protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity entity, BlockHitResult hitResult) {
		if(world instanceof ServerWorld serverWorld){
			boolean currentState = state.get(CLICKED); serverWorld.setBlockState(pos, state.with(CLICKED, !currentState), Block.NOTIFY_ALL);
			serverWorld.updateNeighborsAlways(pos, this);
		} return ActionResult.SUCCESS;
	}

	@Override public void onBlockBreakStart(BlockState state, World world, BlockPos pos, PlayerEntity player) {
		if (state.get(CLICKED)) world.setBlockState(pos, state.with(CLICKED, false));
	}

	@Override protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
		super.randomTick(state, world, pos, random);
		if (state.get(CLICKED)) world.setBlockState(pos, state.with(CLICKED, false));
		else world.setBlockState(pos, state.with(CLICKED, true));
	}

	public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        if(!entity.bypassesSteppingEffects() && state.get(CLICKED) && state.get(FACING) == Direction.UP){ world.setBlockState(pos, state.with(CLICKED, false)); }
    }

    public BlockState rotate(BlockState state, BlockRotation rotation) { return state.with(FACING, rotation.rotate(state.get(FACING))); }
    public BlockState mirror(BlockState state, BlockMirror mirror) { return state.rotate(mirror.getRotation(state.get(FACING))); }
    public BlockState getPlacementState(ItemPlacementContext ctx) { return getDefaultState().with(FACING, ctx.getPlayerLookDirection().getOpposite()); }
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) { builder.add(CLICKED, FACING); }

	@Override protected boolean emitsRedstonePower(BlockState state) { return true; }

	public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) { return state.get(CLICKED) ? 8 : 0; }
    public int getStrongRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) { return state.get(CLICKED) ? 12 : 0; }
}
