package net.trevorskullcrafter;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.client.util.InputUtil;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.DyedColorComponent;
import net.minecraft.item.ItemConvertible;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ColorHelper;
import net.trevorskullcrafter.block.TSBlocks;
import net.trevorskullcrafter.entity.ModEntities;
import net.trevorskullcrafter.entity.client.*;
import net.trevorskullcrafter.item.CooldownComponent;
import net.trevorskullcrafter.item.ModDataComponentTypes;
import net.trevorskullcrafter.item.PortkeyComponent;
import net.trevorskullcrafter.item.TSItems;
import net.trevorskullcrafter.item.custom.*;
import net.trevorskullcrafter.networking.ModMessages;
import net.trevorskullcrafter.networking.packet.PortkeyPayload;
import net.trevorskullcrafter.networking.packet.ReloadPayload;
import net.trevorskullcrafter.networking.packet.StyleSwitchPayload;
import net.trevorskullcrafter.particle.ModSuspendParticle;
import net.trevorskullcrafter.particle.MuzzleFlashParticle;
import net.trevorskullcrafter.util.StyleUtil;
import net.trevorskullcrafter.util.TextUtil;
import org.lwjgl.glfw.GLFW;

import java.util.Objects;

import static com.mojang.text2speech.Narrator.LOGGER;
import static net.trevorskullcrafter.TrevorsSentinels.MOD_ID;

public class TrevorsSentinelsClient implements ClientModInitializer {
	public static final String KEY_CATEGORY_TREVORSSENTINELS = "key.category.trevorssentinels.trevorssentinels";
	public static final String KEY_STYLE_SWITCH = "key.trevorssentinels.style_switch";
	public static final String KEY_RELOAD = "key.trevorssentinels.reload";
	public static final String KEY_PORTKEY = "key.trevorssentinels.portkey";

	public static KeyBinding style_switch = KeyBindingHelper.registerKeyBinding(new KeyBinding(KEY_STYLE_SWITCH, InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_Z, KEY_CATEGORY_TREVORSSENTINELS));
	public static KeyBinding reload = KeyBindingHelper.registerKeyBinding(new KeyBinding(KEY_RELOAD, InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_R, KEY_CATEGORY_TREVORSSENTINELS));
	public static KeyBinding portkey = KeyBindingHelper.registerKeyBinding(new KeyBinding(KEY_PORTKEY, InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_V, KEY_CATEGORY_TREVORSSENTINELS));

	@Override public void onInitializeClient() {
		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> 0xCFF8FF, TSItems.Beta.VENDOR_TOKEN);
		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> TextUtil.DARK_PURPLE.getRGB(), TSItems.Beta.LEGENDARY_TOKEN);

		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> TextUtil.tintByIndex(tintIndex, TextUtil.WHITE.getRGB(),
                        Objects.requireNonNullElse(stack.getComponents().get(DataComponentTypes.DYED_COLOR),
								new DyedColorComponent(TextUtil.WHITE.getRGB(), false)).rgb()), TSItems.Tech.PAINT_PACK);
		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> TextUtil.tintByIndex(tintIndex, TextUtil.WHITE.getRGB(),
						Objects.requireNonNullElse(stack.getComponents().get(DataComponentTypes.DYED_COLOR),
								new DyedColorComponent(TextUtil.WHITE.getRGB(), false)).rgb()), TSItems.Tech.CHROMATIC_LENS);

		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
			int projectile_color = ((PhaserItem) stack.getItem()).getProjectileColor(stack).getRGB();
			CooldownComponent cooldown = stack.get(ModDataComponentTypes.COOLDOWN);
			if(Objects.requireNonNullElse(stack.get(ModDataComponentTypes.PHASER_LOCK), false)){ projectile_color = TextUtil.DARK_DARK_GRAY.getRGB(); }
			else if(cooldown != null){ projectile_color = ColorHelper.Argb.lerp((float) cooldown.step() / cooldown.length(), projectile_color, TextUtil.DARK_DARK_GRAY.getRGB()); }
			return TextUtil.tintByIndex(tintIndex, TextUtil.WHITE.getRGB(), ((PhaserItem) stack.getItem()).getDecalColor(stack).getRGB(), projectile_color); },
				TSItems.Tech.SCRAP_METAL_PHASER, TSItems.Tech.INDUSTRIAL_PHASER, TSItems.Tech.STARSTEEL_PHASER, TSItems.Tech.NUCLEAR_PHASER, TSItems.Tech.NANOTECH_PHASER,
				TSItems.Tech.ZENITHIUM_PHASER, TSItems.Tech.VILE_SPITTER);

		ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> TextUtil.tintByIndex(tintIndex, TextUtil.SENTINEL_AQUA1, TextUtil.SENTINEL_AQUA3),
				TSBlocks.Tech.HARD_LIGHT, TSBlocks.Tech.HARD_LIGHT_BARRIER);
		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> TextUtil.tintByIndex(tintIndex, TextUtil.SENTINEL_AQUA1, TextUtil.SENTINEL_AQUA3),
				TSBlocks.Tech.HARD_LIGHT_BARRIER);
		ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> TextUtil.tintByIndex(tintIndex, TextUtil.SENTINEL_GOLD1, TextUtil.SENTINEL_GOLD3),
				TSBlocks.Tech.CAUTION_HARD_LIGHT, TSBlocks.Tech.CAUTION_HARD_LIGHT_BARRIER);
		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> TextUtil.tintByIndex(tintIndex, TextUtil.SENTINEL_GOLD1, TextUtil.SENTINEL_GOLD3),
				TSBlocks.Tech.CAUTION_HARD_LIGHT_BARRIER);
		ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> TextUtil.tintByIndex(tintIndex, TextUtil.SENTINEL_CRIMSON1, TextUtil.SENTINEL_CRIMSON3),
				TSBlocks.Tech.SENTINEL_HARD_LIGHT, TSBlocks.Tech.SENTINEL_HARD_LIGHT_BARRIER);
		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> TextUtil.tintByIndex(tintIndex, TextUtil.SENTINEL_CRIMSON1, TextUtil.SENTINEL_CRIMSON3),
				TSBlocks.Tech.SENTINEL_HARD_LIGHT_BARRIER);

		ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> TextUtil.tintByIndex(tintIndex, TextUtil.SENTINEL_CRIMSON1, TextUtil.SENTINEL_CRIMSON3),
				TSBlocks.Tech.SENTINEL_HARD_LIGHT, TSBlocks.Tech.SENTINEL_HARD_LIGHT_BARRIER);

		ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
			if(world != null && world.getBlockEntityRenderData(pos) instanceof Integer integer) { return integer; }
			return TextUtil.WHITE.getRGB();
		}, TSBlocks.Tech.PHASEPLATE);

		BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), TSBlocks.Trees.PALE_LEAVES, TSBlocks.Trees.PALE_SAPLING, TSBlocks.Trees.POTTED_PALE_SAPLING,
				TSBlocks.Trees.PALE_DOOR, TSBlocks.Trees.PALE_TRAPDOOR);
		BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), TSBlocks.Trees.CHARRED_DOOR, TSBlocks.Trees.CHARRED_TRAPDOOR);
		BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), TSBlocks.Trees.MIDAS_LEAVES, TSBlocks.Trees.MIDAS_SAPLING, TSBlocks.Trees.POTTED_MIDAS_SAPLING,
				TSBlocks.Trees.MIDAS_DOOR, TSBlocks.Trees.MIDAS_TRAPDOOR);
		BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), TSBlocks.Trees.VIRIDIAN_LEAVES, TSBlocks.Trees.VIRIDIAN_SAPLING, TSBlocks.Trees.POTTED_VIRIDIAN_SAPLING,
				TSBlocks.Trees.VIRIDIAN_DOOR, TSBlocks.Trees.VIRIDIAN_TRAPDOOR);
		BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), TSBlocks.Trees.CERULII_LEAVES, TSBlocks.Trees.CERULII_SAPLING, TSBlocks.Trees.POTTED_CERULII_SAPLING,
				TSBlocks.Trees.CERULII_DOOR, TSBlocks.Trees.CERULII_TRAPDOOR);
		BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), TSBlocks.Tech.FANCY_COMPUTER, TSBlocks.Magic.TELEPORTER, TSBlocks.Magic.UNHOLY_BLOCK, TSBlocks.Tech.RECONSTRUCTION_TABLE,
				TSBlocks.Tech.MODIFICATION_TABLE, TSBlocks.Plants.TRANQUIL_ROSE, TSBlocks.Plants.POTTED_TRANQUIL_ROSE, TSBlocks.Plants.FEATHERCORN, TSBlocks.Plants.SKULLWEED,
				TSBlocks.Plants.POTTED_SKULLWEED, TSBlocks.Magic.FLESHY_PUSTULE, TSBlocks.Magic.POTTED_FLESHY_PUSTULE, TSBlocks.Plants.RICE_PLANT, TSBlocks.Magic.FLESH_VEINS);
		BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getTranslucent(), TSBlocks.Tech.HARD_LIGHT, TSBlocks.Tech.CAUTION_HARD_LIGHT, TSBlocks.Tech.SENTINEL_HARD_LIGHT,
				TSBlocks.Tech.HARD_LIGHT_BARRIER, TSBlocks.Tech.CAUTION_HARD_LIGHT_BARRIER, TSBlocks.Tech.SENTINEL_HARD_LIGHT_BARRIER);

		ItemTooltipCallback.EVENT.register((stack, tooltipContext, tooltipType, lines) -> {
			ItemConvertible item = stack.getItem();
			if(item instanceof FuelableItem fuelableItem){
				if(Screen.hasShiftDown()){ lines.add(1, fuelableItem.longFuelText(stack)); }
				else { lines.add(1, fuelableItem.shortFuelText(stack)); }
			}
			if(item instanceof StyleUtil.StyleSwitchable switchable && TSItems.getOrSetComponent(stack, ModDataComponentTypes.MAX_STYLE, 1) > 1) {
				if(switchable.showStyleSwitchTooltip(stack)) {
					lines.add(Text.empty().append(StyleUtil.style).append(switchable.getCurrentStyleTranslation(stack)).formatted(switchable.getStyleSwitchFormatting(stack)));
				}
				lines.add(Text.empty().append(Text.literal(style_switch.getBoundKeyLocalizedText().getString().toUpperCase()).formatted(Formatting.YELLOW))
						.append(Text.translatable("tooltip.trevorssentinels.style_switch").formatted(Formatting.DARK_GRAY)));
			}
			PortkeyComponent portkey = stack.get(ModDataComponentTypes.PORTKEY);
			if(portkey != null) {
				if(portkey.active()){
					BlockPos pos = portkey.globalPos().pos();
					lines.add(TextUtil.coloredText(Text.translatable("tooltip.trevorssentinels.bound_position", pos.getX(), pos.getY(), pos.getZ())
							.append(Text.literal(" (")).append(String.valueOf(portkey.globalPos().dimension().getValue())).append(Text.literal(")")), TextUtil.TETHERED));
				} else { lines.add(TextUtil.coloredText(Text.translatable("tooltip.trevorssentinels.position_not_bound"), TextUtil.TETHERED)); }
				lines.add(TextUtil.coloredText(Text.translatable("tooltip.trevorssentinels.portkey_hotkey_1", Text.keybind("trevorssentinels:portkey")), TextUtil.TRANQUIL));
				lines.add(TextUtil.coloredText("tooltip.trevorssentinels.portkey_hotkey_2", TextUtil.TRANQUIL));
			}

			if(item instanceof ExtendedTooltipItem extendedTooltipItem) {
				if(Screen.hasShiftDown()){ lines.addAll(extendedTooltipItem.longText(stack)); }
				else { lines.addAll(extendedTooltipItem.shortText(stack)); }
			}
			if(item instanceof PillarAligned pillarAligned && stack.getHolder() != null) {
				lines.add(pillarAligned.getPillar(stack).tooltipText(stack.getHolder().getWorld()));
			}
		});

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while(style_switch.wasPressed()) { ClientPlayNetworking.send(new StyleSwitchPayload(true)); }
			while(reload.wasPressed()) { ClientPlayNetworking.send(new ReloadPayload(true)); }
			while(portkey.wasPressed()) { ClientPlayNetworking.send(new PortkeyPayload(client.player.getBlockPos())); }
		});
		ModMessages.registerS2CPackets();

		EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.PLASMA_PROJECTILE, PhaserProjectileModel::getTexturedModelData);
		EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.SENTINEL, SentinelEntityModel::getTexturedModelData);
		EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.ROOMBA, RoombaEntityModel::getTexturedModelData);
		EntityModelLayerRegistry.registerModelLayer(ModEntityModelLayers.FLORBUS, FlorbusEntityModel::getTexturedModelData);

		LOGGER.info("Registering model predicates... ("+ MOD_ID + ")");
		ModelPredicateProviderRegistry.register(TSItems.Beta.PAPPYM_BLADE, Identifier.of(MOD_ID, "model"), (stack, world, entity, seed) -> {
			int style = TSItems.getOrSetComponent(stack, ModDataComponentTypes.STYLE, 1);
			if ((style % 2) == 0) {  return 0f; } return 0.2f;
		});

		ModelPredicateProviderRegistry.register(TSItems.Tech.LIFEFORM_TRACER, Identifier.of(MOD_ID, "model"), (stack, world, entity, seed) -> {
			switch (stack.getComponents().getOrDefault(ModDataComponentTypes.MODEL_OVERRIDE, 0)){
				case 1 -> { return 0.5f; }
				case 2 -> { return 0.7f; }
				case 3 -> { return 1.0f; }
				default -> { return 0.0f; }}
		});

		EntityRendererRegistry.register(ModEntities.SENTINEL, SentinelEntityRenderer::new);
		EntityRendererRegistry.register(ModEntities.JANITOR_DROID, RoombaRenderer::new);
		EntityRendererRegistry.register(ModEntities.FLORBUS, FlorbusEntityRenderer::new);
		EntityRendererRegistry.register(ModEntities.SHARD_PROJECTILE, FlyingItemEntityRenderer::new);
		EntityRendererRegistry.register(ModEntities.DAGGER_PROJECTILE, DaggerEntityRenderer::new);
		EntityRendererRegistry.register(ModEntities.DELAYED_EXPLOSIVE, FlyingItemEntityRenderer::new);
		EntityRendererRegistry.register(ModEntities.PHASER_PROJECTILE, PhaserProjectileRenderer::new);

		ParticleFactoryRegistry.getInstance().register(TrevorsSentinels.Registries.FLESH_PUS, ModSuspendParticle.FleshPusFactory::new);
		ParticleFactoryRegistry.getInstance().register(TrevorsSentinels.Registries.MUZZLE_FLASH, MuzzleFlashParticle.Factory::new);

		//SpriteIdentifierRegistry.INSTANCE.addIdentifier(new Material(TexturedRenderLayers.SIGNS_ATLAS_TEXTURE, TSBlocks.Trees.PALE_SIGN_TEXTURE));
		//SpriteIdentifierRegistry.INSTANCE.addIdentifier(new Material(TexturedRenderLayers.SIGNS_ATLAS_TEXTURE, TSBlocks.Trees.PALE_HANGING_SIGN_TEXTURE));
		//SpriteIdentifierRegistry.INSTANCE.addIdentifier(new Material(TexturedRenderLayers.SIGNS_ATLAS_TEXTURE, TSBlocks.Trees.CHARRED_SIGN_TEXTURE));
		//SpriteIdentifierRegistry.INSTANCE.addIdentifier(new Material(TexturedRenderLayers.SIGNS_ATLAS_TEXTURE, TSBlocks.Trees.CHARRED_HANGING_SIGN_TEXTURE));
		//SpriteIdentifierRegistry.INSTANCE.addIdentifier(new Material(TexturedRenderLayers.SIGNS_ATLAS_TEXTURE, TSBlocks.Trees.MIDAS_SIGN_TEXTURE));
		//SpriteIdentifierRegistry.INSTANCE.addIdentifier(new Material(TexturedRenderLayers.SIGNS_ATLAS_TEXTURE, TSBlocks.Trees.MIDAS_HANGING_SIGN_TEXTURE));
		//SpriteIdentifierRegistry.INSTANCE.addIdentifier(new Material(TexturedRenderLayers.SIGNS_ATLAS_TEXTURE, TSBlocks.Trees.VIRIDIAN_SIGN_TEXTURE));
		//SpriteIdentifierRegistry.INSTANCE.addIdentifier(new Material(TexturedRenderLayers.SIGNS_ATLAS_TEXTURE, TSBlocks.Trees.VIRIDIAN_HANGING_SIGN_TEXTURE));
		//SpriteIdentifierRegistry.INSTANCE.addIdentifier(new Material(TexturedRenderLayers.SIGNS_ATLAS_TEXTURE, TSBlocks.Trees.CERULII_SIGN_TEXTURE));
		//SpriteIdentifierRegistry.INSTANCE.addIdentifier(new Material(TexturedRenderLayers.SIGNS_ATLAS_TEXTURE, TSBlocks.Trees.CERULII_HANGING_SIGN_TEXTURE));
	}
}