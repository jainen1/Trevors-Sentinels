package net.trevorskullcrafter.networking.packet;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.trevorskullcrafter.TrevorsSentinels;

public record StyleSwitchPayload(boolean placeholder) implements CustomPayload {
	public static final CustomPayload.Id<StyleSwitchPayload> ID = new CustomPayload.Id<>(Identifier.of(TrevorsSentinels.MOD_ID, "style_switch"));
	public static final PacketCodec<RegistryByteBuf, StyleSwitchPayload> CODEC = PacketCodec.tuple(PacketCodecs.BOOL, StyleSwitchPayload::placeholder, StyleSwitchPayload::new);

	@Override public Id<? extends CustomPayload> getId() { return ID; }
}
