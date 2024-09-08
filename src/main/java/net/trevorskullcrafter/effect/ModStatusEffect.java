package net.trevorskullcrafter.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.particle.ParticleEffect;

public class ModStatusEffect extends StatusEffect {
    public ModStatusEffect(StatusEffectCategory type, int color) { super(type, color); }
	public ModStatusEffect(StatusEffectCategory type, int color, ParticleEffect particle) { super(type, color, particle); }
}
