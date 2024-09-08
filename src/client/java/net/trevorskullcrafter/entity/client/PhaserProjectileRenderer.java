package net.trevorskullcrafter.entity.client;

import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.trevorskullcrafter.TrevorsSentinels;
import net.trevorskullcrafter.entity.custom.PhaserProjectileEntity;

public class PhaserProjectileRenderer extends EntityRenderer<PhaserProjectileEntity> {
    private final PhaserProjectileModel model;

    public PhaserProjectileRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.model = new PhaserProjectileModel(context.getPart(ModEntityModelLayers.PLASMA_PROJECTILE));
    }

    public void render(PhaserProjectileEntity projectile, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        if(projectile.getAge() > 1) {
            matrixStack.push();
            matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(MathHelper.lerp(g, projectile.prevYaw, projectile.getYaw()) - 90.0F));
			matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(MathHelper.lerp(g, projectile.prevPitch, projectile.getPitch()) + 90.0F));
            VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(this.model.getLayer(this.getTexture(projectile)));
			int ticksLeft = projectile.getLifetime() - projectile.getAge();
			this.model.render(matrixStack, vertexConsumer, 15, OverlayTexture.DEFAULT_UV, ColorHelper.Argb.withAlpha((ticksLeft <= 10)? (int) Math.max(0, ticksLeft * 25.5) : 255, projectile.getColor()));

            matrixStack.pop();
            super.render(projectile, f, g, matrixStack, vertexConsumerProvider, 15);
        }
    }

    @Override public Identifier getTexture(PhaserProjectileEntity entity) { return Identifier.of(TrevorsSentinels.MOD_ID, "textures/entity/phaser_projectile.png"); }
}
