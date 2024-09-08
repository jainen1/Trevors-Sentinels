package net.trevorskullcrafter.block.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.GlowLichenBlock;
import net.minecraft.item.ItemPlacementContext;

public class FleshVeinBlock extends GlowLichenBlock {
    public FleshVeinBlock(Settings settings) { super(settings); }

    @Override public boolean canReplace(BlockState state, ItemPlacementContext context) {
        return !context.getStack().isOf(this.asItem()) || super.canReplace(state, context);
    }
}
