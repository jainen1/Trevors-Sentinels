package net.trevorskullcrafter.entity.client;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.ColorHelper;

public class PhaserProjectileModel extends Model {
    private final ModelPart exterior;
    private final ModelPart interior;
    public PhaserProjectileModel(ModelPart root) {
        super(ModRenderLayers::getEntityTranslucentCullEmissive);
        this.exterior = root.getChild("exterior");
        this.interior = root.getChild("interior");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
		ModelPartData interior = modelPartData.addChild("interior", ModelPartBuilder.create()
				.uv(8, 8).cuboid(-1.0F, 1.0F, -1.0F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F)),
			ModelTransform.pivot(0.0F, 0.0F, 0.0F));
		ModelPartData exterior = modelPartData.addChild("exterior", ModelPartBuilder.create()
				.uv(-4, 0).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 0.0F, 4.0F, new Dilation(0.0F))
				.uv(4, 0).cuboid(-2.0F, 8.0F, -2.0F, 4.0F, 0.0F, 4.0F, new Dilation(0.0F))
				.uv(0, 8).cuboid(-2.0F, 0.0F, -2.0F, 0.0F, 8.0F, 4.0F, new Dilation(0.0F))
				.uv(0, 0).cuboid(2.0F, 0.0F, -2.0F, 0.0F, 8.0F, 4.0F, new Dilation(0.0F))
				.uv(24, 0).cuboid(-2.0F, 0.0F, 2.0F, 4.0F, 8.0F, 0.0F, new Dilation(0.0F))
				.uv(16, 0).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 8.0F, 0.0F, new Dilation(0.0F)),
			ModelTransform.pivot(0.0F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 32, 32);
    }

	@Override public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, int color) {
		exterior.render(matrices, vertices, 15, overlay, color);
		interior.render(matrices, vertices, 15, overlay, ColorHelper.Argb.getArgb(ColorHelper.Argb.getAlpha(color), 255, 255, 255));
	}
}
