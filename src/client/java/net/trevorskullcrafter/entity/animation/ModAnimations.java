package net.trevorskullcrafter.entity.animation;

import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.animation.Keyframe;
import net.minecraft.client.render.entity.animation.Transformation;
import net.minecraft.client.render.entity.animation.AnimationHelper;

public class ModAnimations {
	public static final Animation SPAWN = Animation.Builder.create(1f)
		.addBoneAnimation("mainbody",
			new Transformation(Transformation.Targets.SCALE,
				new Keyframe(0f, AnimationHelper.createScalingVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC),
				new Keyframe(0.15f, AnimationHelper.createScalingVector(1f, 1f, 0f), Transformation.Interpolations.LINEAR),
				new Keyframe(0.35f, AnimationHelper.createScalingVector(1f, 1f, 0.7f), Transformation.Interpolations.LINEAR),
				new Keyframe(0.5f, AnimationHelper.createScalingVector(1f, 0.8f, 0.8f), Transformation.Interpolations.LINEAR),
				new Keyframe(0.75f, AnimationHelper.createScalingVector(1f, 1f, 1f), Transformation.Interpolations.LINEAR),
				new Keyframe(1f, AnimationHelper.createScalingVector(1f, 1f, 1f), Transformation.Interpolations.LINEAR)))
		.addBoneAnimation("visor",
			new Transformation(Transformation.Targets.TRANSLATE,
				new Keyframe(0.5f, AnimationHelper.createTranslationalVector(0f, 4f, 0f), Transformation.Interpolations.LINEAR),
				new Keyframe(0.85f, AnimationHelper.createTranslationalVector(0f, 0f, 0f), Transformation.Interpolations.LINEAR)))
		.addBoneAnimation("visor",
			new Transformation(Transformation.Targets.SCALE,
				new Keyframe(0f, AnimationHelper.createScalingVector(0f, 0f, 0f), Transformation.Interpolations.LINEAR),
				new Keyframe(0.15f, AnimationHelper.createScalingVector(0f, 0.5f, 1f), Transformation.Interpolations.LINEAR),
				new Keyframe(0.35f, AnimationHelper.createScalingVector(1f, 0.5f, 1f), Transformation.Interpolations.LINEAR),
				new Keyframe(0.5f, AnimationHelper.createScalingVector(1f, 1f, 1f), Transformation.Interpolations.LINEAR),
				new Keyframe(0.85f, AnimationHelper.createScalingVector(1f, 1f, 1f), Transformation.Interpolations.LINEAR)))
		.addBoneAnimation("lens",
			new Transformation(Transformation.Targets.TRANSLATE,
				new Keyframe(0.25f, AnimationHelper.createTranslationalVector(0f, 0f, 5f), Transformation.Interpolations.LINEAR),
				new Keyframe(0.5f, AnimationHelper.createTranslationalVector(0f, 0f, 6f), Transformation.Interpolations.CUBIC),
				new Keyframe(0.85f, AnimationHelper.createTranslationalVector(0f, 0f, -1f), Transformation.Interpolations.CUBIC),
				new Keyframe(1f, AnimationHelper.createTranslationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC)))
		.addBoneAnimation("lens",
			new Transformation(Transformation.Targets.ROTATE,
				new Keyframe(0.4f, AnimationHelper.createRotationalVector(0f, 0f, 720f), Transformation.Interpolations.LINEAR),
				new Keyframe(0.8f, AnimationHelper.createRotationalVector(0f, 0f, 0f), Transformation.Interpolations.LINEAR)))
		.addBoneAnimation("lens",
			new Transformation(Transformation.Targets.SCALE,
				new Keyframe(0f, AnimationHelper.createScalingVector(0f, 0f, 0f), Transformation.Interpolations.LINEAR),
				new Keyframe(0.25f, AnimationHelper.createScalingVector(1f, 1f, 0f), Transformation.Interpolations.LINEAR),
				new Keyframe(1f, AnimationHelper.createScalingVector(1f, 1f, 1f), Transformation.Interpolations.LINEAR)))
		.addBoneAnimation("left",
			new Transformation(Transformation.Targets.ROTATE,
				new Keyframe(0.5f, AnimationHelper.createRotationalVector(0f, 0f, -45f), Transformation.Interpolations.LINEAR),
				new Keyframe(0.75f, AnimationHelper.createRotationalVector(0f, 0f, 22.5f), Transformation.Interpolations.LINEAR),
				new Keyframe(0.95f, AnimationHelper.createRotationalVector(0f, 0f, 0f), Transformation.Interpolations.LINEAR)))
		.addBoneAnimation("right",
			new Transformation(Transformation.Targets.ROTATE,
				new Keyframe(0.5f, AnimationHelper.createRotationalVector(0f, 0f, 45f), Transformation.Interpolations.LINEAR),
				new Keyframe(0.75f, AnimationHelper.createRotationalVector(0f, 0f, -20f), Transformation.Interpolations.LINEAR),
				new Keyframe(0.95f, AnimationHelper.createRotationalVector(0f, 0f, 0f), Transformation.Interpolations.LINEAR))).build();

	public static final Animation IDLE = Animation.Builder.create(2f).looping()
		.addBoneAnimation("head",
			new Transformation(Transformation.Targets.TRANSLATE,
				new Keyframe(0f, AnimationHelper.createTranslationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC),
				new Keyframe(1f, AnimationHelper.createTranslationalVector(0f, 1f, 0f), Transformation.Interpolations.CUBIC),
				new Keyframe(2f, AnimationHelper.createTranslationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC)))
		.addBoneAnimation("head",
			new Transformation(Transformation.Targets.ROTATE,
				new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC),
				new Keyframe(0.65f, AnimationHelper.createRotationalVector(2.5f, 0f, 0f), Transformation.Interpolations.CUBIC),
				new Keyframe(1.35f, AnimationHelper.createRotationalVector(-1.25f, 0f, 0f), Transformation.Interpolations.CUBIC),
				new Keyframe(2f, AnimationHelper.createRotationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC)))
		.addBoneAnimation("left",
			new Transformation(Transformation.Targets.ROTATE,
				new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC),
				new Keyframe(0.25f, AnimationHelper.createRotationalVector(0f, 0f, 5f), Transformation.Interpolations.CUBIC),
				new Keyframe(0.75f, AnimationHelper.createRotationalVector(0f, 0f, -5f), Transformation.Interpolations.CUBIC),
				new Keyframe(1.25f, AnimationHelper.createRotationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC),
				new Keyframe(2f, AnimationHelper.createRotationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC)))
		.addBoneAnimation("right",
			new Transformation(Transformation.Targets.ROTATE,
				new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC),
				new Keyframe(0.25f, AnimationHelper.createRotationalVector(0f, 0f, 5f), Transformation.Interpolations.CUBIC),
				new Keyframe(0.75f, AnimationHelper.createRotationalVector(0f, 0f, -5f), Transformation.Interpolations.CUBIC),
				new Keyframe(1.25f, AnimationHelper.createRotationalVector(0f, 0f, 3f), Transformation.Interpolations.CUBIC),
				new Keyframe(2f, AnimationHelper.createRotationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC))).build();

	public static final Animation FLY = Animation.Builder.create(1f).looping()
		.addBoneAnimation("head",
			new Transformation(Transformation.Targets.TRANSLATE,
				new Keyframe(0f, AnimationHelper.createTranslationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC),
				new Keyframe(0.5f, AnimationHelper.createTranslationalVector(0f, 1f, 0f), Transformation.Interpolations.CUBIC),
				new Keyframe(1f, AnimationHelper.createTranslationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC)))
		.addBoneAnimation("head",
			new Transformation(Transformation.Targets.ROTATE,
				new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC),
				new Keyframe(0.35f, AnimationHelper.createRotationalVector(2.5f, 0f, 0f), Transformation.Interpolations.CUBIC),
				new Keyframe(0.65f, AnimationHelper.createRotationalVector(-1.25f, 0f, 0f), Transformation.Interpolations.CUBIC),
				new Keyframe(1f, AnimationHelper.createRotationalVector(0f, 0f, 0f), Transformation.Interpolations.CUBIC))).build();

	public static final Animation ATTACK = Animation.Builder.create(1f)
		.addBoneAnimation("left",
			new Transformation(Transformation.Targets.ROTATE,
				new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f), Transformation.Interpolations.LINEAR),
				new Keyframe(0.2f, AnimationHelper.createRotationalVector(0f, 0f, -45f), Transformation.Interpolations.LINEAR),
				new Keyframe(0.75f, AnimationHelper.createRotationalVector(0f, 0f, 0f), Transformation.Interpolations.LINEAR)))
		.addBoneAnimation("right",
			new Transformation(Transformation.Targets.ROTATE,
				new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f), Transformation.Interpolations.LINEAR),
				new Keyframe(0.2f, AnimationHelper.createRotationalVector(0f, 0f, 45f), Transformation.Interpolations.LINEAR),
				new Keyframe(0.75f, AnimationHelper.createRotationalVector(0f, 0f, 0f), Transformation.Interpolations.LINEAR)))
		.addBoneAnimation("lens",
			new Transformation(Transformation.Targets.TRANSLATE,
				new Keyframe(0f, AnimationHelper.createTranslationalVector(0f, 0f, 0f), Transformation.Interpolations.LINEAR),
				new Keyframe(0.15f, AnimationHelper.createTranslationalVector(0f, 0f, 1f), Transformation.Interpolations.LINEAR),
				new Keyframe(1f, AnimationHelper.createTranslationalVector(0f, 0f, 0f), Transformation.Interpolations.LINEAR))).build();
}
