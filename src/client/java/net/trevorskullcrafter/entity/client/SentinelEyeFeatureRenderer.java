package net.trevorskullcrafter.entity.client;

import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.EyesFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;
import net.trevorskullcrafter.TrevorsSentinels;
import net.trevorskullcrafter.entity.custom.SentinelEntity;

public class SentinelEyeFeatureRenderer<T extends SentinelEntity> extends EyesFeatureRenderer<T, SentinelEntityModel<T>> {
	private static final RenderLayer SKIN = RenderLayer.getEyes(Identifier.of(TrevorsSentinels.MOD_ID, "textures/entity/sentinel_eye.png"));

	public SentinelEyeFeatureRenderer(FeatureRendererContext<T, SentinelEntityModel<T>> featureRendererContext) { super(featureRendererContext); }

	public RenderLayer getEyesTexture() { return SKIN; }

	@Override public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, T entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
		VertexConsumer vertexConsumer = vertexConsumers.getBuffer(this.getEyesTexture());
		this.getContextModel().render(matrices, vertexConsumer, 15728640, OverlayTexture.DEFAULT_UV, ColorHelper.Argb.withAlpha(255, entity.getColor()));
	}
}
