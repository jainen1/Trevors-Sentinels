package net.trevorskullcrafter.mixin;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.text.Text;
import net.trevorskullcrafter.util.TextUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.awt.*;

@Mixin(StatusEffect.class)
public abstract class StatusEffectMixin {
    @Shadow public abstract String getTranslationKey();
    @Shadow public abstract int getColor();

    @Inject(at = @At("HEAD"), method = "getName", cancellable = true)
    private void getName(CallbackInfoReturnable<Text> cir) {
        cir.setReturnValue(TextUtil.coloredText(getTranslationKey(), Color.decode(String.valueOf(getColor()))));
    }
}
