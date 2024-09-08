package net.trevorskullcrafter.entity.client;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.trevorskullcrafter.TrevorsSentinels;
import net.trevorskullcrafter.entity.custom.FlorbusEntity;

public class FlorbusEntityRenderer extends MobEntityRenderer<FlorbusEntity, FlorbusEntityModel<FlorbusEntity>> {
	private static final Identifier TEXTURE = Identifier.of(TrevorsSentinels.MOD_ID, "textures/entity/florbus.png");

	public FlorbusEntityRenderer(EntityRendererFactory.Context context) {
		super(context, new FlorbusEntityModel<>(context.getPart(ModEntityModelLayers.FLORBUS)), 0.6f);
	}

	@Override public Identifier getTexture(FlorbusEntity entity) { return TEXTURE; }


	@Override public void render(FlorbusEntity mobEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
		if(mobEntity.isBaby()) { matrixStack.scale(0.5f, 0.5f, 0.5f); }
		super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
	}
}
