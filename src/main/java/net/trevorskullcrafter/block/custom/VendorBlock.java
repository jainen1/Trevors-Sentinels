package net.trevorskullcrafter.block.custom;

import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.trevorskullcrafter.item.TSItems;

public class VendorBlock extends DirectionalBlock {
    long timeLeft; boolean available;
    public VendorBlock(Settings settings){ super(settings); }

    @Override public BlockRenderType getRenderType(BlockState state) { return BlockRenderType.MODEL; }

	@Override protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity entity, BlockHitResult hitResult) {
		if (world instanceof ServerWorld serverWorld) {
			if(timeLeft <= 0){
				if(entity.getActiveItem().isOf(TSItems.Beta.VENDOR_TOKEN)) {
					serverWorld.playSound((double)pos.getX() + 0.5,pos.getY(), (double)pos.getZ() + 0.5, SoundEvents.BLOCK_METAL_PRESSURE_PLATE_CLICK_ON,
						SoundCategory.BLOCKS, 1.0F, 1.0F, false); timeLeft = 20; return ActionResult.SUCCESS;
				}
			}
		} return ActionResult.PASS;
	}

	@Override protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
		if (timeLeft > 0) { available = false; timeLeft--; }
		else if (timeLeft < 0) timeLeft = 0;
		else{ if(!available){
			Item[] flavors = {TSItems.Tech.COLA_CYAN, TSItems.Tech.MUSHROOM_STEW_CAN, TSItems.Tech.RABBIT_STEW_CAN, TSItems.Tech.BEETROOT_SOUP_CAN,
					TSItems.Tech.COLA_ORANGE, TSItems.Tech.COLA_GREEN, TSItems.Tech.MILK_CAN};
			ItemStack drop = flavors[(int) (Math.random() * flavors.length)].getDefaultStack();

			switch (state.get(FACING)) {
				case Direction.NORTH -> ItemScatterer.spawn(world, pos.getX(), pos.getY(), pos.getZ() - 1, drop);
				case Direction.SOUTH -> ItemScatterer.spawn(world, pos.getX(), pos.getY(), pos.getZ() + 1, drop);
				case Direction.EAST -> ItemScatterer.spawn(world, pos.getX() + 1, pos.getY(), pos.getZ(), drop);
				case Direction.WEST -> ItemScatterer.spawn(world, pos.getX() - 1, pos.getY(), pos.getZ(), drop);
			}
			available = true;
		}}
	}

	@Override public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
		if (random.nextInt(100) == 0) { world.playSound((double) pos.getX() + 0.5, (double) pos.getY() + 0.5,
				(double) pos.getZ() + 0.5, SoundEvents.BLOCK_PORTAL_AMBIENT, SoundCategory.BLOCKS, 0.5f, 0.0f, false); }
		if (timeLeft > 0) { world.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_BELL_USE, SoundCategory.BLOCKS, 1.0f, timeLeft * 0.1f, false); }
	}
}
