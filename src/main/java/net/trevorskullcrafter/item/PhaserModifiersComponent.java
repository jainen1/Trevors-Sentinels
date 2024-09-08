package net.trevorskullcrafter.item;

import com.mojang.datafixers.util.Function13;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Item;
import net.minecraft.item.tooltip.TooltipAppender;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtOps;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.MathHelper;
import net.trevorskullcrafter.TrevorsSentinels;
import net.trevorskullcrafter.util.TextUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public record PhaserModifiersComponent(int attachment_slots, int projectile_damage, int projectile_lifetime, int projectile_inaccuracy, int projectile_recoil, int burst_projectiles,
									   int burst_cooldown, int reload_cooldown, int magazine_size, boolean automatic_reloading, boolean lingering_effects, List<StatusEffectInstance> projectile_effects, boolean isAttachment) implements TooltipAppender {

	public static final Codec<PhaserModifiersComponent> CODEC = RecordCodecBuilder.create(instance -> instance.group(
			Codec.INT.fieldOf("attachment_slots").forGetter(PhaserModifiersComponent::attachment_slots),
			Codec.INT.fieldOf("projectile_damage").forGetter(PhaserModifiersComponent::projectile_damage),
			Codec.INT.fieldOf("projectile_lifetime").forGetter(PhaserModifiersComponent::projectile_lifetime),
			Codec.INT.fieldOf("projectile_inaccuracy").forGetter(PhaserModifiersComponent::projectile_inaccuracy),
			Codec.INT.fieldOf("projectile_recoil").forGetter(PhaserModifiersComponent::projectile_recoil),
			Codec.INT.fieldOf("burst_projectiles").forGetter(PhaserModifiersComponent::burst_projectiles),
			Codec.INT.fieldOf("burst_cooldown").forGetter(PhaserModifiersComponent::burst_cooldown),
			Codec.INT.fieldOf("reload_cooldown").forGetter(PhaserModifiersComponent::reload_cooldown),
			Codec.INT.fieldOf("magazine_size").forGetter(PhaserModifiersComponent::magazine_size),
			Codec.BOOL.fieldOf("automatic_reloading").forGetter(PhaserModifiersComponent::automatic_reloading),
			Codec.BOOL.fieldOf("lingering_effects").forGetter(PhaserModifiersComponent::lingering_effects),
			StatusEffectInstance.CODEC.listOf().optionalFieldOf("projectile_effects", List.of()).forGetter(PhaserModifiersComponent::projectile_effects),
			Codec.BOOL.fieldOf("getAttachment").forGetter(PhaserModifiersComponent::isAttachment)).apply(instance, PhaserModifiersComponent::new));

	public static final PacketCodec<RegistryByteBuf, PhaserModifiersComponent> PACKET_CODEC = tuple(
			PacketCodecs.VAR_INT, PhaserModifiersComponent::attachment_slots,
			PacketCodecs.VAR_INT, PhaserModifiersComponent::projectile_damage,
			PacketCodecs.VAR_INT, PhaserModifiersComponent::projectile_lifetime,
			PacketCodecs.VAR_INT, PhaserModifiersComponent::projectile_inaccuracy,
			PacketCodecs.VAR_INT, PhaserModifiersComponent::projectile_recoil,
			PacketCodecs.VAR_INT, PhaserModifiersComponent::burst_projectiles,
			PacketCodecs.VAR_INT, PhaserModifiersComponent::burst_cooldown,
			PacketCodecs.VAR_INT, PhaserModifiersComponent::reload_cooldown,
			PacketCodecs.VAR_INT, PhaserModifiersComponent::magazine_size,
			PacketCodecs.BOOL, PhaserModifiersComponent::automatic_reloading,
			PacketCodecs.BOOL, PhaserModifiersComponent::lingering_effects,
			StatusEffectInstance.PACKET_CODEC.collect(PacketCodecs.toList()), PhaserModifiersComponent::projectile_effects,
			PacketCodecs.BOOL, PhaserModifiersComponent::isAttachment, PhaserModifiersComponent::new);


	static <B, C, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13> PacketCodec<B, C> tuple(final PacketCodec<? super B, T1> codec1, final Function<C, T1> from1,
			final PacketCodec<? super B, T2> codec2, final Function<C, T2> from2, final PacketCodec<? super B, T3> codec3, final Function<C, T3> from3,
			final PacketCodec<? super B, T4> codec4, final Function<C, T4> from4, final PacketCodec<? super B, T5> codec5, final Function<C, T5> from5,
			final PacketCodec<? super B, T6> codec6, final Function<C, T6> from6, final PacketCodec<? super B, T7> codec7, final Function<C, T7> from7,
			final PacketCodec<? super B, T8> codec8, final Function<C, T8> from8, final PacketCodec<? super B, T9> codec9, final Function<C, T9> from9,
			final PacketCodec<? super B, T10> codec10, final Function<C, T10> from10, final PacketCodec<? super B, T11> codec11, final Function<C, T11> from11,
			final PacketCodec<? super B, T12> codec12, final Function<C, T12> from12, final PacketCodec<? super B, T13> codec13, final Function<C, T13> from13,
			final Function13<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, C> to) {

		return new PacketCodec<>() {
			public C decode(B object) {
				T1 object2 = codec1.decode(object); T2 object3 = codec2.decode(object); T3 object4 = codec3.decode(object); T4 object5 = codec4.decode(object);
				T5 object6 = codec5.decode(object); T6 object7 = codec6.decode(object); T7 object8 = codec7.decode(object); T8 object9 = codec8.decode(object);
				T9 object10 = codec9.decode(object); T10 object11 = codec10.decode(object); T11 object12 = codec11.decode(object); T12 object13 = codec12.decode(object);
				T13 object14 = codec13.decode(object); return to.apply(object2, object3, object4, object5, object6, object7, object8, object9, object10, object11, object12, object13, object14);
			}

			public void encode(B object, C object2) {
				codec1.encode(object, from1.apply(object2)); codec2.encode(object, from2.apply(object2)); codec3.encode(object, from3.apply(object2));
				codec4.encode(object, from4.apply(object2)); codec5.encode(object, from5.apply(object2)); codec6.encode(object, from6.apply(object2));
				codec7.encode(object, from7.apply(object2)); codec8.encode(object, from8.apply(object2)); codec9.encode(object, from9.apply(object2));
				codec10.encode(object, from10.apply(object2)); codec11.encode(object, from11.apply(object2)); codec12.encode(object, from12.apply(object2));
				codec13.encode(object, from13.apply(object2));
			}
		};
	}

	public NbtCompound toNbt(NbtCompound nbt) { nbt.put("phaser_modifiers", CODEC.encodeStart(NbtOps.INSTANCE, this).getOrThrow()); return nbt; }
	public static PhaserModifiersComponent fromNbt(@Nullable NbtCompound nbt) {
		return nbt != null && nbt.contains("phaser_modifiers") ? CODEC.parse(NbtOps.INSTANCE, nbt.get("phaser_modifiers")).result().orElse(null) : null;
	}

	@Override public void appendTooltip(Item.TooltipContext context, Consumer<Text> tooltip, TooltipType type) {
		/*tooltip.add(Text.literal(" ").append(PROJECTILE_DAMAGE).append(Text.literal(String.valueOf(getProjectileLifetime(stack))).formatted(TextUtil.formattingFromQuotient(getProjectileLifetime(stack), 4))));
		tooltip.add(Text.literal(" ").append(PROJECTILE_LIFETIME).append(Text.literal(String.valueOf(getProjectileLifetime(stack))).formatted(TextUtil.formattingFromQuotient(getProjectileLifetime(stack), 4))));
		tooltip.add(Text.literal(" ").append(PROJECTILE_INACCURACY).append(Text.literal(String.valueOf(getProjectileInaccuracy(stack))).formatted(TextUtil.formattingFromQuotient(getProjectileInaccuracy(stack), 60, TextUtil.reverseFormattings))));
		tooltip.add(Text.literal(" ").append(PROJECTILE_RECOIL).append(Text.literal(String.valueOf(getProjectileRecoil(stack))).formatted(TextUtil.formattingFromQuotient(getProjectileRecoil(stack), 4, TextUtil.reverseFormattings))));
		tooltip.add(Text.literal(" ").append(BURST_COOLDOWN).append(Text.literal(String.valueOf(getBurstCooldown(stack))).formatted(TextUtil.formattingFromQuotient(getBurstCooldown(stack), 4, TextUtil.reverseFormattings))));
		tooltip.add(Text.literal(" ").append(RELOAD_COOLDOWN).append(Text.literal(String.valueOf(getReloadCooldown(stack))).formatted(TextUtil.formattingFromQuotient(getReloadCooldown(stack), 4, TextUtil.reverseFormattings))));
		if(stats.automatic_reloading) { tooltip.add(Text.literal(" ").append(AUTOMATIC_RELOADING)); }
		if(stats.lingering_effects) { tooltip.add(Text.literal(" ").append(LINGERING_EFFECTS)); }
		if(stats.projectile_effects != null) {
			tooltip.add(PROJECTILE_EFFECTS);
			for(StatusEffectInstance effect : stats.projectile_effects){ tooltip.add(Text.literal(" ").append(APPLIES).append(TextUtil.potionText(effect, false))); }
		}*/

		tooltip.accept(Text.empty());
		tooltip.accept(PHASER_MODIFIERS);
		modifierTooltip(tooltip, projectile_damage, PROJECTILE_DAMAGE, true);
		modifierTooltip(tooltip, projectile_lifetime, PROJECTILE_LIFETIME, true);
		modifierTooltip(tooltip, projectile_inaccuracy, PROJECTILE_INACCURACY, false);
		modifierTooltip(tooltip, projectile_recoil, PROJECTILE_RECOIL, false);
		modifierTooltip(tooltip, burst_projectiles, BURST_PROJECTILES, true);
		modifierTooltip(tooltip, magazine_size, MAGAZINE_SIZE, true);
		modifierTooltip(tooltip, burst_cooldown, BURST_COOLDOWN, false);
		modifierTooltip(tooltip, reload_cooldown, RELOAD_COOLDOWN, false);
		if(automatic_reloading) { tooltip.accept(ScreenTexts.space().append(AUTOMATIC_RELOADING)); }
		if(lingering_effects) { tooltip.accept(ScreenTexts.space().append(LINGERING_EFFECTS)); }
		if(projectile_effects != null) {
			tooltip.accept(PROJECTILE_EFFECTS);
			for(StatusEffectInstance effect : projectile_effects){ tooltip.accept(ScreenTexts.space().append(APPLIES).append(TextUtil.potionText(effect, false))); }
		}
	}

	private void modifierTooltip(Consumer<Text> tooltip, int modifier, Text modifierText, boolean beneficial){
		if (modifier != 0) { tooltip.accept(Text.literal(" ").append(modifierText)
				.append(Text.literal((modifier > 0 ? "+" : "-") + modifier)
						.formatted(modifier > 0 ? (beneficial? Formatting.GREEN : Formatting.RED) : (beneficial? Formatting.RED : Formatting.GREEN)))); }
	}

	public enum Type { PISTOL, SNIPER, SHOTGUN, RIFLE, ATTACHMENT; Type(){} }
	public Type getType(){
		if(isAttachment) { return Type.ATTACHMENT; }
		else if(projectile_damage > 4 && magazine_size < 6 && burst_projectiles == 1) { return Type.SNIPER; }
		else if(burst_projectiles > 2) { return Type.SHOTGUN; }
		else if(burst_cooldown < 6) { return Type.RIFLE; }
		else { return Type.PISTOL; }
	}

	public static final Text PHASER_DETAILS = Text.translatable("tooltip.item." + TrevorsSentinels.MOD_ID + ".phaser.details").formatted(Formatting.GRAY);
	public static final Text PHASER_MODIFIERS = Text.translatable("tooltip.item." + TrevorsSentinels.MOD_ID + ".phaser.modifiers").formatted(Formatting.GRAY);
	public static final Text DECAL_COLOR = Text.translatable("tooltip.item." + TrevorsSentinels.MOD_ID + ".phaser.decal_color").formatted(Formatting.BLUE);
	public static final Text PROJECTILE_COLOR = Text.translatable("tooltip.item." + TrevorsSentinels.MOD_ID + ".phaser.projectile_color").formatted(Formatting.BLUE);
	public static final Text PROJECTILE_DAMAGE = Text.translatable("tooltip.item." + TrevorsSentinels.MOD_ID + ".phaser.projectile_damage").formatted(Formatting.BLUE);
	public static final Text PROJECTILE_LIFETIME = Text.translatable("tooltip.item." + TrevorsSentinels.MOD_ID + ".phaser.projectile_lifetime").formatted(Formatting.BLUE);
	public static final Text PROJECTILE_INACCURACY = Text.translatable("tooltip.item." + TrevorsSentinels.MOD_ID + ".phaser.projectile_inaccuracy").formatted(Formatting.BLUE);
	public static final Text PROJECTILE_RECOIL = Text.translatable("tooltip.item." + TrevorsSentinels.MOD_ID + ".phaser.projectile_recoil").formatted(Formatting.BLUE);
	public static final Text BURST_PROJECTILES = Text.translatable("tooltip.item." + TrevorsSentinels.MOD_ID + ".phaser.burst_projectiles").formatted(Formatting.BLUE);
	public static final Text BURST_COOLDOWN = Text.translatable("tooltip.item." + TrevorsSentinels.MOD_ID + ".phaser.burst_cooldown").formatted(Formatting.BLUE);
	public static final Text RELOAD_COOLDOWN = Text.translatable("tooltip.item." + TrevorsSentinels.MOD_ID + ".phaser.reload_cooldown").formatted(Formatting.BLUE);
	public static final Text MAGAZINE_SIZE = Text.translatable("tooltip.item." + TrevorsSentinels.MOD_ID + ".phaser.magazine_size").formatted(Formatting.GOLD);
	public static final Text AUTOMATIC_RELOADING = Text.translatable("tooltip.item." + TrevorsSentinels.MOD_ID + ".phaser.automatic_reloading").formatted(Formatting.GOLD);
	public static final Text LINGERING_EFFECTS = Text.translatable("tooltip.item." + TrevorsSentinels.MOD_ID + ".phaser.lingering_effects").formatted(Formatting.GOLD);
	public static final Text PROJECTILE_EFFECTS = Text.translatable("tooltip.item." + TrevorsSentinels.MOD_ID + ".phaser.projectile_effects").formatted(Formatting.GRAY);
	public static final Text APPLIES = Text.translatable("tooltip.item." + TrevorsSentinels.MOD_ID + ".phaser.applies").formatted(Formatting.BLUE);

	public static class Builder{
		private int attachment_slots = 0; private int projectile_damage = 0; private int projectile_lifetime = 0; private int projectile_inaccuracy = 0; private int projectile_recoil = 0;
		private int burst_projectiles = 0; private int burst_cooldown = 0; private int reload_cooldown = 0; private int magazine_size = 0;
		private boolean automatic_reloading = false; private boolean lingering_effects = false; private List<StatusEffectInstance> projectile_effects = List.of();
		private boolean isAttachment = false;

		public Builder(){}
		public Builder attachment_slots(int attachment_slots){ this.attachment_slots = MathHelper.clamp(attachment_slots, 0, 18); return this; }
		public Builder projectile_damage(int projectile_damage){ this.projectile_damage = MathHelper.clamp(projectile_damage, -40, 40); return this; }
		public Builder projectile_lifetime(int projectile_lifetime){ this.projectile_lifetime = MathHelper.clamp(projectile_lifetime, 10, 100); return this; }
		public Builder projectile_inaccuracy(int projectile_inaccuracy){ this.projectile_inaccuracy = MathHelper.clamp(projectile_inaccuracy, 0, 150); return this; }
		public Builder projectile_recoil(int projectile_recoil){ this.projectile_recoil = MathHelper.clamp(projectile_recoil, 0, 5); return this; }
		public Builder burst_projectiles(int burst_projectiles){ this.burst_projectiles = MathHelper.clamp(burst_projectiles, 1, 8); return this; }
		public Builder burst_cooldown(int burst_cooldown){ this.burst_cooldown = MathHelper.clamp(burst_cooldown, 0, 100); return this; }
		public Builder reload_cooldown(int reload_cooldown){ this.reload_cooldown = MathHelper.clamp(reload_cooldown, 0, 200); return this; }
		public Builder magazine_size(int magazine_size){ this.magazine_size = MathHelper.clamp(magazine_size, 1, 64); return this; }
		public Builder automatic_reloading(boolean automatic_reloading){ this.automatic_reloading = automatic_reloading; return this; }
		public Builder lingering_effects(boolean lingering_effects){ this.lingering_effects = lingering_effects; return this; }
		public Builder projectile_effects(List<StatusEffectInstance> projectile_effects){ this.projectile_effects = projectile_effects; return this; }
		public Builder isAttachment(boolean isAttachment){ this.isAttachment = isAttachment; return this; }
		public Builder isAttachment(){ this.isAttachment = true; return this; }

		public PhaserModifiersComponent build(){
			return new PhaserModifiersComponent(attachment_slots, projectile_damage, projectile_lifetime, projectile_inaccuracy, projectile_recoil,
					burst_projectiles, burst_cooldown, reload_cooldown, magazine_size, automatic_reloading, lingering_effects, projectile_effects, isAttachment);
		}
	}
}
