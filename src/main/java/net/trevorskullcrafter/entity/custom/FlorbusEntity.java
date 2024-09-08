package net.trevorskullcrafter.entity.custom;

import net.minecraft.block.BlockState;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.trevorskullcrafter.entity.ModEntities;
import org.jetbrains.annotations.Nullable;

public class FlorbusEntity extends AnimalEntity {

	public final AnimationState IDLE_ANIMATION_STATE = new AnimationState();
	private int idleAnimationTimeout = 0;

    public FlorbusEntity(EntityType<? extends AnimalEntity> entityType, World world) { super(entityType, world); }

	@Override public boolean isBreedingItem(ItemStack stack) { return stack.getItem() == Items.BROWN_MUSHROOM; }

	@Nullable @Override public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return ModEntities.FLORBUS.create(world);
    }

	private void updateAnimations(){
		if(this.idleAnimationTimeout <= 0){
			this.idleAnimationTimeout = this.random.nextInt(40) + 80;
			this.IDLE_ANIMATION_STATE.start(this.age);
		} else { --this.idleAnimationTimeout; }
	}

	@Override protected void updateLimbs(float limbDistance) {
		float f = this.getPose() == EntityPose.STANDING ? Math.min(limbDistance * 6.0f, 1.0f) : 0.0f;
		this.limbAnimator.updateLimbs(f, 0.2f);
	}

	@Override public void tick() {
		super.tick();
		if(this.getWorld() instanceof ServerWorld serverWorld){
			updateAnimations();
		}
	}

	public static DefaultAttributeContainer.Builder setAttributes(){
        return PassiveEntity.createLivingAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 8D)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.8f)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 1f);
    }

    @Override protected void initGoals(){
        this.goalSelector.add(1, new EscapeDangerGoal(this, 0.5f));
        this.goalSelector.add(2, new FollowMobGoal(this, 0.2f, 3.0f,7.0f));
        this.goalSelector.add(3, new FollowParentGoal(this, 1.0f));
        this.goalSelector.add(4, new WanderAroundFarGoal(this, 0.2f, 1));
        this.goalSelector.add(5, new LookAroundGoal(this));

        this.targetSelector.add(2, new ActiveTargetGoal<>(this, FlorbusEntity.class, true));
        this.targetSelector.add(3, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
    }

    @Override protected SoundEvent getAmbientSound(){ return SoundEvents.ENTITY_STRIDER_AMBIENT; }
    @Override protected SoundEvent getHurtSound(DamageSource source){ return SoundEvents.ENTITY_STRIDER_HURT; }
    @Override protected SoundEvent getDeathSound(){ return SoundEvents.ENTITY_STRIDER_DEATH; }
    @Override protected void playStepSound(BlockPos pos, BlockState state){ this.playSound(SoundEvents.ENTITY_STRIDER_STEP, 0.25f, 0.0f); }
}
