package net.trevorskullcrafter.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.trevorskullcrafter.util.TextUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    //@Inject(method = "onStatusEffectApplied", at = @At("TAIL")) private void sendAppliedFlavorText(StatusEffectInstance effect, Entity source, CallbackInfo ci){
    //    ((LivingEntity) (Object) this).sendMessage(statusEffectTooltip(effect, "tooltip." + effect.getTranslationKey() + ".applied"));
    //}

    @Inject(method = "onStatusEffectRemoved", at = @At("TAIL")) private void sendRemovedFlavorText(StatusEffectInstance effect, CallbackInfo ci){
        ((LivingEntity) (Object) this).sendMessage(TextUtil.statusEffectTooltip(effect, "tooltip." + effect.getTranslationKey() + ".removed"));
    }
}
