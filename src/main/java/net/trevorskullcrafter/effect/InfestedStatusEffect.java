package net.trevorskullcrafter.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class InfestedStatusEffect extends StatusEffect {
    int strength;
    public InfestedStatusEffect(StatusEffectCategory type, int color) { super(type, color); }

	@Override public boolean canApplyUpdateEffect(int tick, int amplifier) {
		int i = 25 >> amplifier;
		if (i > 0) return tick % i == 0;
		else return true;
	}

	@Override public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        entity.damage(entity.getDamageSources().generic(), (float) strength / 2);
        strength++;
        super.applyUpdateEffect(entity, amplifier);
		return true;
    }

	@Override public void onApplied(AttributeContainer attributes, int amplifier) {
		strength = amplifier;
		super.onApplied(attributes, amplifier);
	}

	@Override public void onRemoved(AttributeContainer attributes) {
        strength = 0;
        super.onRemoved(attributes);
    }
}
