package net.trevorskullcrafter.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;

public class FlightStatusEffect extends StatusEffect {
    public FlightStatusEffect(StatusEffectCategory type, int color) { super(type, color); }

	@Override public boolean canApplyUpdateEffect(int tick, int amplifier) { return true; }

    //@Override public void applyInstantEffect(@Nullable Entity source, @Nullable Entity attacker, LivingEntity target, int amplifier, double proximity) {
    //    if(target instanceof PlayerEntity player) {
    //        PlayerAbilities abilities = player.getAbilities();
    //        abilities.allowFlying = true;
    //        player.sendAbilitiesUpdate();
    //    }
    //}

    @Override public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        float fallVelocity = (float) Math.abs(entity.getVelocity().y * 5);
        if (entity instanceof PlayerEntity playerEntity) {
            if(playerEntity.isSprinting()){
                if(entity.getVelocity().y < 0.6) entity.addVelocity(0, 0.15, 0);
                else entity.setVelocity(entity.getVelocity().x, 0.6, entity.getVelocity().z); entity.fallDistance = fallVelocity;
            }else if(playerEntity.isSneaking()) { entity.setVelocity(entity.getVelocity().x, 0, entity.getVelocity().z); entity.fallDistance = 0.0f; }
            entity.getMovementDirection();
        } super.applyUpdateEffect(entity, amplifier);
		return true;
	}
}
