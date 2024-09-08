package net.trevorskullcrafter.networking.packet;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.trevorskullcrafter.TrevorsSentinels;

public record ReloadPayload(boolean placeholder) implements CustomPayload {
	public static final Id<ReloadPayload> ID = new Id<>(Identifier.of(TrevorsSentinels.MOD_ID, "reload"));
	public static final PacketCodec<RegistryByteBuf, ReloadPayload> CODEC = PacketCodec.tuple(PacketCodecs.BOOL, ReloadPayload::placeholder, ReloadPayload::new);


	@Override public Id<? extends CustomPayload> getId() { return ID; }
}
