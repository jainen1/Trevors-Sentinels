package net.trevorskullcrafter.networking;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.component.ComponentMap;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.GlobalPos;
import net.minecraft.world.World;
import net.trevorskullcrafter.effect.ModEffects;
import net.trevorskullcrafter.item.ModDataComponentTypes;
import net.trevorskullcrafter.item.PortkeyComponent;
import net.trevorskullcrafter.item.custom.Reloadable;
import net.trevorskullcrafter.networking.packet.PortkeyPayload;
import net.trevorskullcrafter.networking.packet.ReloadPayload;
import net.trevorskullcrafter.networking.packet.StyleSwitchPayload;
import net.trevorskullcrafter.util.StyleUtil;
import net.trevorskullcrafter.util.TextUtil;

import java.util.Set;

public class ModMessages {
	public static void registerC2SPackets(){
		ServerPlayNetworking.registerGlobalReceiver(StyleSwitchPayload.ID, (payload, context) -> {
			context.player().sendMessage(Text.literal("style switched"));

			ItemStack stack = context.player().getMainHandStack();
			Integer style = stack.get(ModDataComponentTypes.STYLE);
			Integer max_style = stack.get(ModDataComponentTypes.STYLE);
			if (style != null && max_style != null && max_style > 1){
				stack.set(ModDataComponentTypes.STYLE, style+1);
				if(stack.getItem() instanceof StyleUtil.StyleSwitchable styledItem) { styledItem.onStyleSwitch(context.player()); }
			}
		});

		ServerPlayNetworking.registerGlobalReceiver(ReloadPayload.ID, (payload, context) -> {
			ServerPlayerEntity player = context.player();
			for(ItemStack stack : player.getHandItems()) { if(stack.getItem() instanceof Reloadable reloadable) reloadable.reload(stack, player.getWorld(), player); }


			//if(player.getMainHandStack().getItem() instanceof Reloadable reloadable){ reloadable.reload(player.getMainHandStack(), player.getWorld(), player); }
			//if(player.getOffHandStack().getItem() instanceof Reloadable reloadable){ reloadable.reload(player.getOffHandStack(), player.getWorld(), player); }
		});

		ServerPlayNetworking.registerGlobalReceiver(PortkeyPayload.ID, (payload, context) -> {
			ServerPlayerEntity player = context.player();
			for(ItemStack stack : player.getHandItems()){
				PortkeyComponent portkey = stack.get(ModDataComponentTypes.PORTKEY);
				if(portkey != null){ portkey.react(stack, player); }
			}
		});
	}

    public static void registerS2CPackets(){

    }
}
