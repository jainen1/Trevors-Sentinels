package net.trevorskullcrafter.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.item.ItemConvertible;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.trevorskullcrafter.item.TSItems;

public class RiceCropBlock extends CropBlock {
    static final int maxAge = 7;
    public static final IntProperty AGE = IntProperty.of("age",0,maxAge);
    public RiceCropBlock(Settings settings) { super(settings); }

    @Override protected ItemConvertible getSeedsItem(){ return TSItems.Beta.RICE_SEEDS; }
    @Override public int getMaxAge(){ return maxAge; }
    @Override public IntProperty getAgeProperty(){ return AGE; }
    @Override protected void appendProperties(StateManager.Builder<Block, BlockState> builder){ builder.add(AGE); }
}
