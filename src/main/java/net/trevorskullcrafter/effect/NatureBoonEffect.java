package net.trevorskullcrafter.effect;

import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.trevorskullcrafter.item.TSItems;

import java.util.stream.Stream;

public class NatureBoonEffect extends StatusEffect {
	protected NatureBoonEffect(StatusEffectCategory type, int color) { super(type, color); }

	@Override public boolean canApplyUpdateEffect(int tick, int amplifier) { return tick % 20 == 0; }

	@Override public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
		Stream<BlockPos> stream = BlockPos.stream(entity.getBoundingBox(entity.getPose()).expand(6 + Math.min(4, amplifier)))
			.filter(pos -> entity.getWorld().getBlockState(pos).isIn(BlockTags.LEAVES));
		stream.forEach((pos) -> {
			if(entity.getWorld().random.nextInt(1600 - (Math.min(4, amplifier) * 200)) == 100){
				entity.getWorld().playSound(null, pos, SoundEvents.BLOCK_NOTE_BLOCK_CHIME.value(), SoundCategory.BLOCKS, 3.0f, 3.0f);
				entity.getWorld().spawnEntity(new ItemEntity(entity.getWorld(), pos.getX(), pos.getY()-1, pos.getZ(), new ItemStack(TSItems.Magic.ENCHANTED_LEAF)));
			}
		});
		return true;
	}
}
