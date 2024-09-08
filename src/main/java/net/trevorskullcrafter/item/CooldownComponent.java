package net.trevorskullcrafter.item;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.component.ComponentMap;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipAppender;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtOps;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.GlobalPos;
import net.minecraft.world.World;
import net.trevorskullcrafter.effect.ModEffects;
import net.trevorskullcrafter.util.TextUtil;
import org.jetbrains.annotations.Nullable;

import java.util.Set;
import java.util.function.Consumer;

public record CooldownComponent(int length, int step) {
	public CooldownComponent(int length){ this(length, length); }

	public static final Codec<CooldownComponent> CODEC = RecordCodecBuilder.create(instance -> instance.group(
		Codec.INT.fieldOf("length").forGetter(CooldownComponent::length),
		Codec.INT.fieldOf("step").forGetter(CooldownComponent::step)).apply(instance, CooldownComponent::new));
	public static final PacketCodec<RegistryByteBuf, CooldownComponent> PACKET_CODEC = PacketCodec.tuple(
		PacketCodecs.VAR_INT, CooldownComponent::length,
		PacketCodecs.VAR_INT, CooldownComponent::step,
		CooldownComponent::new);

	public NbtCompound toNbt(NbtCompound nbt) { nbt.put("cooldown", CODEC.encodeStart(NbtOps.INSTANCE, this).getOrThrow()); return nbt; }
	public static CooldownComponent fromNbt(@Nullable NbtCompound nbt) {
		return nbt != null && nbt.contains("cooldown") ? CODEC.parse(NbtOps.INSTANCE, nbt.get("cooldown")).result().orElse(null) : null;
	}
}
