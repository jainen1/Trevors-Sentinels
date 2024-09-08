package net.trevorskullcrafter.entity.client;

import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.trevorskullcrafter.entity.custom.RoombaEntity;

public class RoombaEntityModel<T extends RoombaEntity> extends SinglePartEntityModel<T> {
	private final ModelPart roomba;

	public RoombaEntityModel(ModelPart root){ this.roomba = root.getChild("Body"); }


	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData Body = modelPartData.addChild("Body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData UpperBody = Body.addChild("UpperBody", ModelPartBuilder.create().uv(0, 0).cuboid(-6.0F, -3.0F, -6.0F, 12.0F, 2.0F, 12.0F, new Dilation(0.0F))
			.uv(0, 0).cuboid(-1.0F, -4.0F, -4.0F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData LowerBody = Body.addChild("LowerBody", ModelPartBuilder.create().uv(0, 14).cuboid(-5.0F, -1.0F, -5.0F, 10.0F, 1.0F, 10.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}

	@Override public ModelPart getPart() { return roomba; }

	@Override public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
		this.getPart().traverse().forEach(ModelPart::resetTransform);
	}
}
