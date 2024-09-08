package net.trevorskullcrafter.block.custom;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BoneMealItem;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.trevorskullcrafter.TrevorsSentinels;
import net.trevorskullcrafter.block.TSBlocks;

public class FleshBlock extends FallDamagelessBlock {
    SoundEvent randomSound;
    public FleshBlock(float damageMultiplier, SoundEvent randomSound, Settings settings) {
        super(damageMultiplier, settings);
        this.randomSound = randomSound;
    }

	@Override public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
		if (random.nextInt(200) == 0) world.playSound((double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5,
				randomSound, SoundCategory.BLOCKS, random.nextFloat() * 0.5F + 0.1F, random.nextFloat() * 0.4F + 0.8F, false);
		BlockPos.Mutable mutable = new BlockPos.Mutable();
		for(int l = 0; l < 3; ++l) {
			mutable.set(pos.getX() + MathHelper.nextInt(random, -1, 1), pos.getY() + MathHelper.nextInt(random, -1, 1), pos.getZ() + MathHelper.nextInt(random, -1, 1));
			BlockState blockState = world.getBlockState(mutable);
			if (!blockState.isFullCube(world, mutable)) world.addParticle(TrevorsSentinels.Registries.FLESH_PUS, (double)mutable.getX() + random.nextDouble(),
					(double)mutable.getY() + random.nextDouble(), (double)mutable.getZ() + random.nextDouble(), 0.0, 0.0, 0.0);
		}
	}

	@Override protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity entity, BlockHitResult hitResult) {
		ItemStack stack = entity.getActiveItem();
		if(stack.getItem() instanceof BoneMealItem && world.getBlockState(pos.offset(hitResult.getSide())).isReplaceable()){
			if (world instanceof ServerWorld serverWorld) { serverWorld.setBlockState(pos.offset(hitResult.getSide()), TSBlocks.Magic.FLESH_VEINS.getDefaultState()
				.with(Properties.EAST, hitResult.getSide() == Direction.WEST).with(Properties.WEST, hitResult.getSide() == Direction.EAST)
				.with(Properties.NORTH, hitResult.getSide() == Direction.SOUTH).with(Properties.SOUTH, hitResult.getSide() == Direction.NORTH)
				.with(Properties.UP, hitResult.getSide() == Direction.DOWN).with(Properties.DOWN, hitResult.getSide() == Direction.UP));
				serverWorld.syncWorldEvent(1505, pos.offset(hitResult.getSide()), 0); }
			if(!entity.getAbilities().creativeMode) stack.decrement(1);
			return ActionResult.CONSUME;
		} return ActionResult.PASS;
	}
}
