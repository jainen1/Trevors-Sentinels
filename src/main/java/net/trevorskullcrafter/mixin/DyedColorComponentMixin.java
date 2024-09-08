package net.trevorskullcrafter.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.component.type.DyedColorComponent;
import net.minecraft.text.MutableText;
import net.trevorskullcrafter.util.TextUtil;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import java.awt.*;

@Mixin(DyedColorComponent.class)
public class DyedColorComponentMixin {
	@Final @Shadow private int rgb;

	//@ModifyExpressionValue(method = "appendTooltip", at = @At(value = "INVOKE_ASSIGN", target = "Ljava/util/function/Consumer;accept(Ljava/lang/Object;)V", ordinal = 0))
	//public MutableText lol(MutableText original){
	//	return TextUtil.coloredText(original, new Color(rgb));
	//}
}
