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

public record PortkeyComponent(boolean active, GlobalPos globalPos) implements TooltipAppender {
	public static final PortkeyComponent DEFAULT = new PortkeyComponent(false, new GlobalPos(RegistryKey.of(RegistryKeys.WORLD, Identifier.ofVanilla("overworld")), BlockPos.ORIGIN));

	public static final Codec<PortkeyComponent> CODEC = RecordCodecBuilder.create(instance -> instance.group(
		Codec.BOOL.fieldOf("active").forGetter(PortkeyComponent::active),
		GlobalPos.CODEC.fieldOf("bound_position").forGetter(PortkeyComponent::globalPos)).apply(instance, PortkeyComponent::new));
	public static final PacketCodec<RegistryByteBuf, PortkeyComponent> PACKET_CODEC = PacketCodec.tuple(
		PacketCodecs.BOOL, PortkeyComponent::active,
		GlobalPos.PACKET_CODEC, PortkeyComponent::globalPos,
		PortkeyComponent::new);

	public void react(ItemStack stack, ServerPlayerEntity player){
		if(player.isSneaking()){
			BlockPos pos = player.getBlockPos();
			RegistryKey<World> world = player.getWorld().getRegistryKey();
			stack.applyComponentsFrom(ComponentMap.builder().add(ModDataComponentTypes.PORTKEY, new PortkeyComponent(true, new GlobalPos(world, pos))).build());
			player.sendMessage(TextUtil.coloredText(Text.translatable("tooltip.trevorssentinels.bound_position", pos.getX(), pos.getY(), pos.getZ()), TextUtil.TETHERED), true);
			player.getServerWorld().playSoundFromEntity(null, player, SoundEvents.ENTITY_WARDEN_SONIC_CHARGE, SoundCategory.PLAYERS, 1.0F, 1.5F);
			player.addStatusEffect(new StatusEffectInstance(ModEffects.TETHERED, 30));
		} else if(active()){
			if(!player.hasStatusEffect(ModEffects.TETHERED) || player.getAbilities().creativeMode){
				BlockPos targetPos = globalPos().pos();
				if(player.getWorld().getRegistryKey() == globalPos().dimension()){
					player.getServerWorld().playSound(null, player.getBlockPos(), SoundEvents.ENTITY_WARDEN_SONIC_BOOM, SoundCategory.PLAYERS, 1.0f, 1.5f);
					player.teleport(player.getServerWorld(), targetPos.getX()+0.5, targetPos.getY(), targetPos.getZ()+0.5, Set.of(), player.getYaw(), player.getPitch());
					player.sendMessage(TextUtil.coloredText(Text.translatable("tooltip.trevorssentinels.teleported", targetPos.getX(), targetPos.getY(), targetPos.getZ()), TextUtil.TETHERED), true);
					player.getServerWorld().playSound(null, targetPos, SoundEvents.ENTITY_WARDEN_SONIC_BOOM, SoundCategory.PLAYERS, 1.0f, 1.5f);
					player.addStatusEffect(new StatusEffectInstance(ModEffects.TETHERED, (int) (80 + Math.pow(player.getBlockPos().getSquaredDistance(targetPos) / 10, 0.33))));
				} else {
					player.sendMessage(Text.translatable("tooltip.trevorssentinels.impossible_teleport").formatted(Formatting.RED), true);
					player.getServerWorld().playSoundFromEntity(null, player, SoundEvents.ENTITY_WARDEN_SONIC_BOOM, SoundCategory.PLAYERS, 0.6f, 1.1f);
					player.getServerWorld().playSoundFromEntity(null, player, SoundEvents.BLOCK_AMETHYST_CLUSTER_BREAK, SoundCategory.PLAYERS, 1.2f, 0.8f);
					player.addStatusEffect(new StatusEffectInstance(ModEffects.TETHERED, 60));
				}
			}
		} else { player.sendMessage(TextUtil.coloredText(Text.translatable("tooltip.trevorssentinels.position_not_bound"), TextUtil.TETHERED), true); }

	}

	public NbtCompound toNbt(NbtCompound nbt) { nbt.put("portkey", CODEC.encodeStart(NbtOps.INSTANCE, this).getOrThrow()); return nbt; }
	public static PortkeyComponent fromNbt(@Nullable NbtCompound nbt) {
		return nbt != null && nbt.contains("portkey") ? CODEC.parse(NbtOps.INSTANCE, nbt.get("portkey")).result().orElse(null) : null;
	}

	@Override public void appendTooltip(Item.TooltipContext context, Consumer<Text> tooltip, TooltipType type) {
		if(active()){ BlockPos pos = globalPos().pos();
			tooltip.accept(TextUtil.coloredText(Text.translatable("tooltip.trevorssentinels.bound_position", pos.getX(), pos.getY(), pos.getZ())
					.append(Text.literal(" (")).append(String.valueOf(globalPos().dimension().getValue())).append(Text.literal(")")), TextUtil.TETHERED));
		} else { tooltip.accept(TextUtil.coloredText(Text.translatable("tooltip.trevorssentinels.position_not_bound"), TextUtil.TETHERED)); }
		tooltip.accept(TextUtil.coloredText("Press V to teleport to bound location,", TextUtil.TRANQUIL));
		tooltip.accept(TextUtil.coloredText("or while crouching to bind your location.", TextUtil.TRANQUIL));
	}
}
