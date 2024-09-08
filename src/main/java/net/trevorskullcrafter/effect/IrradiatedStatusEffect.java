package net.trevorskullcrafter.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class IrradiatedStatusEffect extends StatusEffect {
    public IrradiatedStatusEffect(StatusEffectCategory type, int color) { super(type, color); }

	@Override public boolean canApplyUpdateEffect(int tick, int amplifier) {
		int i = 40 >> amplifier;
		if (i > 0) return tick % i == 0;
		else return true;
	}

	@Override public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        entity.damage(entity.getDamageSources().generic(), (amplifier / 2f)+0.5f);
		return true;
    }
}
