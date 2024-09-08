package net.trevorskullcrafter.entity.client;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.trevorskullcrafter.TrevorsSentinels;
import net.trevorskullcrafter.entity.custom.SentinelEntity;

public class SentinelEntityRenderer extends MobEntityRenderer<SentinelEntity, SentinelEntityModel<SentinelEntity>> {
	private static final Identifier TEXTURE = Identifier.of(TrevorsSentinels.MOD_ID, "textures/entity/sentinel.png");

	public SentinelEntityRenderer(EntityRendererFactory.Context context) {
		super(context, new SentinelEntityModel<>(context.getPart(ModEntityModelLayers.SENTINEL)), 0.6f);
		addFeature(new SentinelEyeFeatureRenderer(this));
	}

	@Override public Identifier getTexture(SentinelEntity entity) { return TEXTURE; }

	@Override public void render(SentinelEntity mobEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
		//if(mobEntity.isBaby()) { matrixStack.scale(0.5f, 0.5f, 0.5f); }
		super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
	}
}
