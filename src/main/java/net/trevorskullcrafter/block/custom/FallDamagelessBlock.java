package net.trevorskullcrafter.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FallDamagelessBlock extends Block {
    float damageMultiplier;
    public FallDamagelessBlock(float damageMultiplier, Settings settings) {
        super(settings);
        this.damageMultiplier = damageMultiplier;
    }

    @Override public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        entity.handleFallDamage(fallDistance, damageMultiplier, entity.getDamageSources().fall());
    }
}