package net.trevorskullcrafter.mixin.client;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.AbstractInventoryScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.*;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;


@Mixin(AbstractInventoryScreen.class)
public class AbstractInventoryScreenMixin<T extends ScreenHandler> extends HandledScreen<T> {

    public AbstractInventoryScreenMixin(T handler, PlayerInventory inventory, Text title) { super(handler, inventory, title); }
    @Override protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {}

    //@ModifyExpressionValue(method = "drawStatusEffects", at = @At(value = "INVOKE", target = "Ljava/util/List;of(Ljava/lang/Object;Ljava/lang/Object;)Ljava/util/List;"))
    //private List<Text> addExtraText(List<Text> original) {
    //    ArrayList<Text> arrayList = new ArrayList<>(original);
    //    arrayList.add(Text.literal("test").formatted(Formatting.GRAY, Formatting.ITALIC));
    //    return arrayList;
    //}

    //@Inject(method = "drawStatusEffectDescriptions", at = @At(value = "TAIL"))
    //private void addExtraText(DrawContext context, int x, int height, Iterable<StatusEffectInstance> statusEffects, CallbackInfo ci) {
    //    int i = y;
    //    for (Iterator<StatusEffectInstance> var6 = statusEffects.iterator(); var6.hasNext(); i += height) {
    //        StatusEffectInstance statusEffectInstance = var6.next();
    //        Text text = Text.translatable("tooltip." + statusEffectInstance.getTranslationKey().formatted(Formatting.GRAY, Formatting.ITALIC)).fillStyle(Style.of(Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
    //                Optional.empty(), Optional.empty(),
    //                Optional.of(new HoverEvent(HoverEvent.Action.SHOW_TEXT, Text.literal("test"))), Optional.empty(), Optional.empty()));
    //        context.drawTextWithShadow(this.textRenderer, text, x + 10 + 18, i + 6 + 20, 16777215);
    //    }
    //}

    //@ModifyExpressionValue(method = "getStatusEffectDescription", at = @At(value = "INVOKE", target = "Lnet/minecraft/text/Text;copy()Lnet/minecraft/text/MutableText;"))
    //private MutableText withHoverEvent(MutableText original){
    //    return original.fillStyle(Style.of(Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
    //            Optional.empty(), Optional.empty(),
    //            Optional.of(new HoverEvent(HoverEvent.Action.SHOW_TEXT, Text.literal("test"))), Optional.empty(), Optional.empty()));
    //}

    @WrapOperation(method = "drawStatusEffectBackgrounds", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Lnet/minecraft/util/Identifier;IIII)V"))
    private void extendBackgroundLarge(DrawContext instance, Identifier texture, int x, int y, int width, int height, Operation<Void> original){
        original.call(instance, texture, x, y, width, height);
    }
}
