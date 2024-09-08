package net.trevorskullcrafter.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class RedstonedStatusEffect extends StatusEffect {
    float storedHealth; float changeInHealth; LivingEntity bearer;
    public RedstonedStatusEffect(StatusEffectCategory type, int color) { super(type, color); }

	@Override public boolean canApplyUpdateEffect(int tick, int amplifier) { return true; }

    @Override public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        super.applyUpdateEffect(entity, amplifier);
        if(!entity.getWorld().isClient()){
            if(storedHealth != entity.getHealth()) changeInHealth += (entity.getHealth() - storedHealth);
            if(entity.getHealth() > 0) entity.setHealth(storedHealth);
        }
		return true;
    }

	@Override public void onApplied(AttributeContainer attributes, int amplifier) {
		super.onApplied(attributes, amplifier); storedHealth = bearer.getHealth();
	}

    @Override public void onRemoved(AttributeContainer attributes) {
        super.onRemoved(attributes);
        if (changeInHealth < 0) bearer.damage(bearer.getDamageSources().generic(), Math.abs(changeInHealth));
        else if(changeInHealth > 0) bearer.heal(changeInHealth);
        storedHealth = 0; changeInHealth = 0;
    }
}
