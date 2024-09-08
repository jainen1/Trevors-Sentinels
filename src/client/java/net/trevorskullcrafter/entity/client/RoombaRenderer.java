package net.trevorskullcrafter.entity.client;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.trevorskullcrafter.TrevorsSentinels;
import net.trevorskullcrafter.entity.custom.RoombaEntity;

public class RoombaRenderer extends MobEntityRenderer<RoombaEntity, RoombaEntityModel<RoombaEntity>> {
	private static final Identifier TEXTURE = Identifier.of(TrevorsSentinels.MOD_ID, "textures/entity/roomba.png");

	public RoombaRenderer(EntityRendererFactory.Context context) {
		super(context, new RoombaEntityModel<>(context.getPart(ModEntityModelLayers.ROOMBA)), 0.6f);
	}

	@Override public Identifier getTexture(RoombaEntity entity) { return TEXTURE; }

	@Override public void render(RoombaEntity mobEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
		//if(mobEntity.isBaby()) { matrixStack.scale(0.5f, 0.5f, 0.5f); }
		super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
	}
}
