package net.trevorskullcrafter.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;

public class WellFedEffect extends StatusEffect {
    public WellFedEffect(StatusEffectCategory type, int color) { super(type, color); }

    @Override public boolean canApplyUpdateEffect(int tick, int amplifier) {
		int i; i = 25 >> amplifier;
		if (i > 0) return tick % i == 0;
		else return true;
	}

	@Override public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity instanceof PlayerEntity player && player.getHungerManager().isNotFull()) { player.getHungerManager().add(1, 1.0f); }
		return true;
    }
}
