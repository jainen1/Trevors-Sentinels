package net.trevorskullcrafter.mixin.client;

import net.minecraft.client.gui.screen.ingame.HandledScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(HandledScreen.class)
public class HandledScreenMixin extends ScreenMixin {
    @Shadow protected int x;
    @Shadow protected int y;
}
