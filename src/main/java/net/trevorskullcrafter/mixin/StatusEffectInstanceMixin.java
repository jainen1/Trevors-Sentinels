package net.trevorskullcrafter.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.trevorskullcrafter.util.TextUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

@Mixin(StatusEffectInstance.class)
public class StatusEffectInstanceMixin {
    @Inject(method = "onApplied", at = @At("TAIL"))
    private void sendAppliedFlavorText(LivingEntity entity, CallbackInfo ci){
        StatusEffectInstance instance = (StatusEffectInstance) (Object) this;
        entity.sendMessage(TextUtil.statusEffectTooltip(instance, "tooltip." + instance.getTranslationKey() + ".applied"));
    }

    //@Inject(method = "onEntityRemoval", at = @At("TAIL"))
    //private void sendRemovedFlavorText(LivingEntity entity, Entity.RemovalReason reason, CallbackInfo ci){
    //    StatusEffectInstance instance = (StatusEffectInstance) (Object) this;
    //    entity.sendMessage(TextUtil.statusEffectTooltip(instance, "tooltip." + instance.getTranslationKey() + ".removed"));
    //}
}
