package net.trevorskullcrafter.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ToggleableLampBlock extends Block {
    public static final BooleanProperty CLICKED = BooleanProperty.of("clicked");
    public ToggleableLampBlock(Settings settings) { super(settings); }

	@Override protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity entity, BlockHitResult hitResult) {
		if(world instanceof ServerWorld serverWorld){
			boolean currentState = state.get(CLICKED); serverWorld.setBlockState(pos, state.with(CLICKED, !currentState), Block.NOTIFY_ALL);
		} return ActionResult.SUCCESS;
	}

	@Override protected void appendProperties(StateManager.Builder<Block, BlockState> builder) { builder.add(CLICKED); }
}
