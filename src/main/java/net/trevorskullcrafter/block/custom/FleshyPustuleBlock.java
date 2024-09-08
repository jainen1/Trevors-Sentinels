package net.trevorskullcrafter.block.custom;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowerBlock;
import net.minecraft.component.type.SuspiciousStewEffectsComponent;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.trevorskullcrafter.datagen.BlockTagGenerator;

public class FleshyPustuleBlock extends FlowerBlock {
    public FleshyPustuleBlock(SuspiciousStewEffectsComponent stewEffects, Settings settings) { super(stewEffects, settings); }
    public FleshyPustuleBlock(RegistryEntry<StatusEffect> stewEffect, float effectLengthInSeconds, AbstractBlock.Settings settings) { super(stewEffect, effectLengthInSeconds, settings); }

    @Override protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) { return floor.isIn(BlockTagGenerator.FLESHY_BLOCKS) && floor.isSolidBlock(world, pos); }
}
