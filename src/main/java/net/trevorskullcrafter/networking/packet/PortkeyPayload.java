package net.trevorskullcrafter.networking.packet;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.trevorskullcrafter.TrevorsSentinels;

public record PortkeyPayload(BlockPos blockPos) implements CustomPayload {
	public static final Id<PortkeyPayload> ID = new Id<>(Identifier.of(TrevorsSentinels.MOD_ID, "portkey"));
	public static final PacketCodec<RegistryByteBuf, PortkeyPayload> CODEC = PacketCodec.tuple(BlockPos.PACKET_CODEC, PortkeyPayload::blockPos, PortkeyPayload::new);

	@Override public Id<? extends CustomPayload> getId() { return ID; }
}
