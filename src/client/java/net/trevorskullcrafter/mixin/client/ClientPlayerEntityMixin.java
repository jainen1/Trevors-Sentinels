package net.trevorskullcrafter.mixin.client;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin{
    @Inject(method = "sendMessage(Lnet/minecraft/text/Text;)V", at = @At(value = "HEAD"))
    public void eh(Text message, CallbackInfo ci){ ((ClientPlayerEntity) (Object) this).playSound(SoundEvents.UI_BUTTON_CLICK.value(), 0.4f, 1.5f); }

    @Inject(method = "sendMessage(Lnet/minecraft/text/Text;Z)V", at = @At(value = "HEAD"))
    public void chatSound(Text message, boolean overlay, CallbackInfo ci) { ((ClientPlayerEntity) (Object) this).playSound(SoundEvents.UI_BUTTON_CLICK.value(), 0.4f, 1.5f); }
}
