package net.trevorskullcrafter.util;

import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.trevorskullcrafter.item.ModDataComponentTypes;
import net.trevorskullcrafter.item.TSItems;

import static net.trevorskullcrafter.util.TextUtil.*;

public class StyleUtil {
    public static final Text style = TextUtil.coloredText("tooltip.trevorssentinels.style", GOLD);
    public static final Text mode = TextUtil.coloredText("tooltip.trevorssentinels.mode", SENTINEL_AQUA1);

    public interface StyleSwitchable {
        default SoundEvent getSwitchSoundEvent(ItemStack stack){ return SoundEvents.BLOCK_END_PORTAL_FRAME_FILL; }
        default float getSwitchSoundVolume(ItemStack stack){ return 1.0f; }
        default float getSwitchSoundPitch(ItemStack stack){ return 1.2F; }

		static int getStyle(ItemStack stack) { return TSItems.getComponentValue(stack, ModDataComponentTypes.STYLE, 0); }
		default int getStyles(ItemStack stack) { return TSItems.getComponentValue(stack, ModDataComponentTypes.MAX_STYLE, getStyle(stack)); }

        default Formatting getStyleSwitchFormatting(ItemStack stack){ return Formatting.GRAY; }
        default Text getSwitchMessagePrefix(ItemStack stack) { return StyleUtil.style; }
        default Text getCurrentStyleTranslation(ItemStack stack) { return Text.translatable("style."+stack.getTranslationKey()+"."+stack.get(ModDataComponentTypes.STYLE)); }

        default void sendSwitchSound(ServerPlayerEntity player, ItemStack stack){
            player.getServerWorld().playSoundFromEntity(null, player, getSwitchSoundEvent(stack), SoundCategory.BLOCKS, getSwitchSoundVolume(stack), getSwitchSoundPitch(stack));
        }

        default void sendSwitchMessage(ServerPlayerEntity player, ItemStack stack){
            player.sendMessage(Text.empty().append(getSwitchMessagePrefix(stack)).append(getCurrentStyleTranslation(stack)).formatted(getStyleSwitchFormatting(stack)), true);
        }

        default void onStyleSwitch(ServerPlayerEntity player){ ItemStack stack = player.getMainHandStack(); sendSwitchSound(player, stack); sendSwitchMessage(player, stack); }

        default boolean showStyleSwitchTooltip(ItemStack stack) { return true; }
    }
}
