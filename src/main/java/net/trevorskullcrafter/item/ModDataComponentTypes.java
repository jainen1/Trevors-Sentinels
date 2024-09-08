package net.trevorskullcrafter.item;

import com.mojang.serialization.Codec;
import net.minecraft.component.ComponentType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.util.math.GlobalPos;
import net.trevorskullcrafter.TrevorsSentinels;

import java.util.List;
import java.util.function.UnaryOperator;

public class ModDataComponentTypes extends DataComponentTypes {
	public static final ComponentType<Integer> MODEL_OVERRIDE = register("trevorssentinels:model_override",
			(builder) -> builder.codec(Codecs.NONNEGATIVE_INT).packetCodec(PacketCodecs.VAR_INT));

	public static final ComponentType<PhaserModifiersComponent> PHASER_MODIFIERS = register("trevorssentinels:phaser_modifiers",
			(builder) -> builder.codec(PhaserModifiersComponent.CODEC).packetCodec(PhaserModifiersComponent.PACKET_CODEC));

	public static final ComponentType<Boolean> PHASER_LOCK = register("trevorssentinels:phaser_lock",
			(builder) -> builder.codec(Codec.BOOL).packetCodec(PacketCodecs.BOOL));

	public static final ComponentType<Integer> FUEL = register("trevorssentinels:fuel",
			(builder) -> builder.codec(Codecs.NONNEGATIVE_INT).packetCodec(PacketCodecs.VAR_INT));

	public static final ComponentType<Integer> MAX_FUEL = register("trevorssentinels:max_fuel",
			(builder) -> builder.codec(Codecs.NONNEGATIVE_INT).packetCodec(PacketCodecs.VAR_INT));

	public static final ComponentType<CooldownComponent> COOLDOWN = register("trevorssentinels:cooldown",
			(builder) -> builder.codec(CooldownComponent.CODEC).packetCodec(CooldownComponent.PACKET_CODEC));

	public static final ComponentType<Integer> STYLE = register("trevorssentinels:style",
			(builder) -> builder.codec(Codecs.NONNEGATIVE_INT).packetCodec(PacketCodecs.VAR_INT));

	public static final ComponentType<Integer> MAX_STYLE = register("trevorssentinels:max_style",
			(builder) -> builder.codec(Codecs.NONNEGATIVE_INT).packetCodec(PacketCodecs.VAR_INT));

	public static final ComponentType<List<Integer>> KINGS_RUNES = register("trevorssentinels:kings_runes",
			(builder) -> builder.codec(Codecs.NONNEGATIVE_INT.listOf(3,3)).packetCodec(PacketCodecs.VAR_INT.collect(PacketCodecs.toList())));

	public static final ComponentType<GlobalPos> STORED_POSITION = register("trevorssentinels:stored_position",
			(builder) -> builder.codec(GlobalPos.CODEC).packetCodec(GlobalPos.PACKET_CODEC));

	public static final ComponentType<PortkeyComponent> PORTKEY = register("trevorssentinels:portkey",
			(builder) -> builder.codec(PortkeyComponent.CODEC).packetCodec(PortkeyComponent.PACKET_CODEC));

	public static final ComponentType<Integer> TIMER = register("trevorssentinels:timer",
			(builder -> builder.codec(Codecs.NONNEGATIVE_INT).packetCodec(PacketCodecs.VAR_INT)));

	public static final ComponentType<Integer> PALADIN_CHARGE = register("trevorssentinels:paladin_charge",
			(builder -> builder.codec(Codecs.NONNEGATIVE_INT).packetCodec(PacketCodecs.VAR_INT)));

	private static <T> ComponentType<T> register(String id, UnaryOperator<ComponentType.Builder<T>> operator) {
		return Registry.register(Registries.DATA_COMPONENT_TYPE, id, operator.apply(ComponentType.builder()).build());
	}
	public static void registerDataComponentTypes(){ TrevorsSentinels.LOGGER.info("Registering data component types..."); }
}
