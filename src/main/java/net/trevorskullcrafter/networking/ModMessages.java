package net.trevorskullcrafter.networking;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.trevorskullcrafter.item.ModDataComponentTypes;
import net.trevorskullcrafter.item.PortkeyComponent;
import net.trevorskullcrafter.item.custom.Reloadable;
import net.trevorskullcrafter.networking.packet.PortkeyPayload;
import net.trevorskullcrafter.networking.packet.ReloadPayload;
import net.trevorskullcrafter.networking.packet.StyleSwitchPayload;
import net.trevorskullcrafter.util.StyleUtil;

public class ModMessages {
	public static void registerC2SPackets(){
		ServerPlayNetworking.registerGlobalReceiver(StyleSwitchPayload.ID, (payload, context) -> {
			ItemStack stack = context.player().getMainHandStack();
			Integer style = stack.get(ModDataComponentTypes.STYLE);
			Integer max_style = stack.get(ModDataComponentTypes.MAX_STYLE);
			if (style != null && max_style != null && max_style > 1){
				stack.set(ModDataComponentTypes.STYLE, (style < max_style)? style+1 : 1);
				if(stack.getItem() instanceof StyleUtil.StyleSwitchable styledItem) { styledItem.onStyleSwitch(context.player()); }
			}
		});

		ServerPlayNetworking.registerGlobalReceiver(ReloadPayload.ID, (payload, context) -> {
			ServerPlayerEntity player = context.player();
			for(ItemStack stack : player.getHandItems()) { if(stack.getItem() instanceof Reloadable reloadable) reloadable.reload(stack, player.getWorld(), player); }
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
