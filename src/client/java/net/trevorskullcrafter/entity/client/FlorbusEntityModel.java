package net.trevorskullcrafter.entity.client;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.trevorskullcrafter.entity.custom.FlorbusEntity;

public class FlorbusEntityModel<T extends FlorbusEntity> extends SinglePartEntityModel<T> {
	private final ModelPart florbus;

	public FlorbusEntityModel(ModelPart root){ this.florbus = root.getChild("Body"); }

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData Body = modelPartData.addChild("Body", ModelPartBuilder.create().uv(0, 24).cuboid(-6.0F, -20.0F, -9.0F, 12.0F, 8.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData Main = Body.addChild("Main", ModelPartBuilder.create().uv(0, 0).cuboid(-6.0F, -22.0F, -3.0F, 12.0F, 16.0F, 8.0F, new Dilation(0.0F))
			.uv(34, 24).cuboid(-5.5F, -21.0F, -4.0F, 11.0F, 8.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData Eyes = Main.addChild("Eyes", ModelPartBuilder.create().uv(24, 40).cuboid(-7.0F, -19.0F, -2.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F))
			.uv(40, 20).cuboid(6.0F, -19.0F, -2.0F, 1.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData LeftLeg = Body.addChild("LeftLeg", ModelPartBuilder.create().uv(12, 37).cuboid(-1.5F, 0.0F, -1.75F, 3.0F, 3.0F, 3.0F, new Dilation(0.0F))
			.uv(0, 37).cuboid(-1.5F, 3.0F, -4.75F, 3.0F, 3.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(3.5F, -6.0F, 1.75F));

		ModelPartData RightLeg = Body.addChild("RightLeg", ModelPartBuilder.create().uv(28, 33).cuboid(-1.5F, 3.0F, -4.75F, 3.0F, 3.0F, 6.0F, new Dilation(0.0F))
			.uv(32, 0).cuboid(-1.5F, 0.0F, -1.75F, 3.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(-3.5F, -6.0F, 1.75F));

		ModelPartData LeftArm = Body.addChild("LeftArm", ModelPartBuilder.create().uv(40, 6).cuboid(-1.0F, 0.0F, -1.75F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F))
			.uv(40, 14).cuboid(-1.0F, 0.0F, 0.25F, 2.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(7.0F, -15.0F, 1.75F));

		ModelPartData RightArm = Body.addChild("RightArm", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, 0.0F, -1.75F, 2.0F, 6.0F, 2.0F, new Dilation(0.0F))
			.uv(0, 37).cuboid(-1.0F, 0.0F, 0.25F, 2.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(-7.0F, -15.0F, 1.75F));
		return TexturedModelData.of(modelData, 64, 64);
	}

	@Override public ModelPart getPart() { return florbus; }
	@Override public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
		this.getPart().traverse().forEach(ModelPart::resetTransform);
	}
}
