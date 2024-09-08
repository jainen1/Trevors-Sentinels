package net.trevorskullcrafter.entity.custom;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.FollowMobGoal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.WanderAroundGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class RoombaEntity extends PassiveEntity {
    public RoombaEntity(EntityType<? extends PassiveEntity> entityType, World world) { super(entityType, world); }

    @Nullable @Override public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) { return null; }

    public static DefaultAttributeContainer.Builder setAttributes(){
        return PassiveEntity.createLivingAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 0.5D)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 1f)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 1f);
    }

    @Override public boolean isCollidable() { return true; }
    @Override public boolean collidesWith(Entity other) { return (other.isCollidable() || other.isPushable()) && !isConnectedThroughVehicle(other); }

    @Override protected void initGoals(){
        this.goalSelector.add(1, new FollowMobGoal(this, 0.1f, 3.0f,7.0f));
        this.goalSelector.add(2, new WanderAroundGoal(this, 0.1f, 1));
        this.goalSelector.add(3, new LookAroundGoal(this));

        this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
    }

    @Override protected SoundEvent getAmbientSound(){ return SoundEvents.ENTITY_SILVERFISH_AMBIENT; }
    @Override protected SoundEvent getHurtSound(DamageSource source){ return SoundEvents.ENTITY_SILVERFISH_HURT; }
    @Override protected SoundEvent getDeathSound(){ return SoundEvents.ENTITY_SILVERFISH_DEATH; }
    @Override protected void playStepSound(BlockPos pos, BlockState state){ this.playSound(SoundEvents.ENTITY_SILVERFISH_STEP, 0.15f, 1.0f); }
}
