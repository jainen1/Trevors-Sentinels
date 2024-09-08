package net.trevorskullcrafter.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.registry.entry.RegistryEntry;

import java.util.Objects;

public class CureStatusEffect extends StatusEffect {
	RegistryEntry<StatusEffect> cures;
	RegistryEntry<StatusEffect> effect = ModEffects.FORTIFIED;
    public CureStatusEffect(StatusEffectCategory type, int color, RegistryEntry<StatusEffect> cures) { super(type, color); this.cures = cures; }

	@Override public boolean canApplyUpdateEffect(int tick, int amplifier) { return tick % 10 == 0; }

	@Override public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
		StatusEffectInstance wither = entity.getStatusEffect(cures);
		if(wither != null){
			int thisDuration = Objects.requireNonNull(entity.getStatusEffect(effect)).getDuration();
			int witherDuration = wither.getDuration();

			int result = amplifier - wither.getAmplifier();
			int result2 = thisDuration - witherDuration;
			entity.removeStatusEffect(cures);
			entity.removeStatusEffect(effect);

			if(result > 0){ entity.addStatusEffect(new StatusEffectInstance(effect, result2, Math.abs(result)-1)); }
			else if(result < 0) { entity.addStatusEffect(new StatusEffectInstance(cures, -result2, Math.abs(-result)-1)); }
			else if(result2 > 0){ entity.addStatusEffect(new StatusEffectInstance(effect, result2, 0)); }
			else if(result2 < 0){ entity.addStatusEffect(new StatusEffectInstance(cures, -result2, 0)); }
			return true;
		}
		return false;
	}
}
