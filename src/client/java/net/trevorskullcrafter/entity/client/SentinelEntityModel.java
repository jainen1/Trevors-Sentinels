package net.trevorskullcrafter.entity.client;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.ModelWithHead;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.util.math.MathHelper;
import net.trevorskullcrafter.entity.animation.ModAnimations;
import net.trevorskullcrafter.entity.custom.SentinelEntity;

public class SentinelEntityModel<T extends SentinelEntity> extends SinglePartEntityModel<T> implements ModelWithHead {
	private final ModelPart head;

	public SentinelEntityModel(ModelPart root) { this.head = root.getChild("head"); }

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData head = modelPartData.addChild("head", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 19.0F, 0.0F));

		ModelPartData mainbody = head.addChild("mainbody", ModelPartBuilder.create()
				.uv(0, 15).cuboid(-4.25F, -3.0F, -6.0F, 8.0F, 7.0F, 12.0F, new Dilation(0.0F))
			.uv(7, 0).cuboid(1.75F, -3.0F, -6.5F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)),
		ModelTransform.pivot(0.25F, -3.0F, 0.0F));

			ModelPartData visor = head.addChild("visor", ModelPartBuilder.create()
			.uv(0, 0).cuboid(-5.0F, -1.1095F, -7.0F, 10.0F, 1.0F, 14.0F, new Dilation(0.0F)),
			ModelTransform.pivot(0.0F, -5.8905F, 0.0F));

		ModelPartData left = visor.addChild("left", ModelPartBuilder.create(),
			ModelTransform.pivot(5.0F, -1.1095F, 0.0F));

		ModelPartData Visor_r1 = left.addChild("Visor_r1", ModelPartBuilder.create()
			.uv(26, 20).cuboid(-5.0F, -12.0F, -7.9F, 3.0F, 1.0F, 14.0F, new Dilation(0.0F)),
			ModelTransform.of(-4.9497F, 12.0208F, 0.9F, 0.0F, 0.0F, 0.7854F));

		ModelPartData right = visor.addChild("right", ModelPartBuilder.create(),
			ModelTransform.pivot(-5.0F, -1.1095F, 0.0F));

		ModelPartData Visor_r2 = right.addChild("Visor_r2", ModelPartBuilder.create().uv(0, 34)
			.cuboid(2.0F, -12.0F, -7.9F, 3.0F, 1.0F, 14.0F, new Dilation(0.0F)),
			ModelTransform.of(4.9497F, 12.0208F, 0.9F, 0.0F, 0.0F, -0.7854F));

		ModelPartData lens = head.addChild("lens", ModelPartBuilder.create()
			.uv(0, 0).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 3.0F, new Dilation(0.0F))
			.uv(0, 15).cuboid(1.0F, -1.0F, -2.0F, 1.0F, 2.0F, 3.0F, new Dilation(0.0F))
			.uv(0, 9).cuboid(-1.0F, -2.0F, -2.0F, 2.0F, 1.0F, 3.0F, new Dilation(0.0F))
			.uv(0, 5).cuboid(-1.0F, 1.0F, -2.0F, 2.0F, 1.0F, 3.0F, new Dilation(0.0F))
			.uv(0, 20).cuboid(-2.0F, -1.0F, -2.0F, 1.0F, 2.0F, 3.0F, new Dilation(0.0F)),
			ModelTransform.pivot(0.0F, -3.0F, -5.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}

	@Override public ModelPart getPart() { return head; }
	@Override public ModelPart getHead() { return head; }

	@Override public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
		this.getPart().traverse().forEach(ModelPart::resetTransform);
		this.setHeadAngles(headPitch);

		this.animateMovement(ModAnimations.FLY, limbAngle, limbDistance, 2f, 2.5f);
		this.updateAnimation(entity.IDLE_ANIMATION_STATE, ModAnimations.IDLE, animationProgress, 1f);
	}

	private void setHeadAngles(float headPitch){
		headPitch = MathHelper.clamp(headPitch, -90F, 90F);
		this.head.pitch = headPitch * 0.017453292F;
	}
}
