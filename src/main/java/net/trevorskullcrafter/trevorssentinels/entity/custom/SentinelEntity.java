package net.trevorskullcrafter.trevorssentinels.entity.custom;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.AboveGroundTargeting;
import net.minecraft.entity.ai.NoPenaltySolidTargeting;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.control.FlightMoveControl;
import net.minecraft.entity.ai.control.LookControl;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.BirdNavigation;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.trevorskullcrafter.trevorssentinels.entity.ModEntities;
import net.trevorskullcrafter.trevorssentinels.sound.ModSounds;
import net.trevorskullcrafter.trevorssentinels.util.TextUtil;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.EnumSet;
import java.util.List;

public class SentinelEntity extends PathAwareEntity implements GeoEntity, Monster, RangedAttackMob {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private static final RawAnimation ATTACK = RawAnimation.begin().thenPlay("attack");
    private static final TrackedData<Integer> AMMO = DataTracker.registerData(SentinelEntity.class, TrackedDataHandlerRegistry.INTEGER);

    public SentinelEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
        this.moveControl = new FlightMoveControl(this, 30, true);
        this.lookControl = new SentinelLookControl(this);
    }

    public static DefaultAttributeContainer.Builder setAttributes(){
        return HostileEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 10.0D)
                .add(EntityAttributes.GENERIC_ARMOR, 0.5D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 3.0f)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 2.0f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.8f)
                .add(EntityAttributes.GENERIC_FLYING_SPEED, 0.8f)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 8D);
    }

    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(AMMO, 0);
    }
    public Integer getAmmo() { return this.dataTracker.get(AMMO); }
    public void setAmmo(int num) { this.dataTracker.set(AMMO, num); }

    public boolean handleFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource) { return false; }
    @Override protected void fall(double heightDifference, boolean onGround, BlockState state, BlockPos landedPosition) { }

    @Override public boolean isClimbing() { return false; }

    @Override protected void initGoals(){
        this.goalSelector.add(4, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(5, new ProjectileAttackGoal(this, 1.0f, 10, 8));
        this.goalSelector.add(2, new RevengeGoal(this, SentinelEntity.class));

        this.goalSelector.add(6, new LookAroundGoal(this));
        this.goalSelector.add(6, new SentinelWanderGoal());

        this.targetSelector.add(1, new ActiveTargetGoal<>(this, PlayerEntity.class, 10, true, false,
                (entity) -> Math.abs(entity.getY() - this.getY()) <= 4.0));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, IronGolemEntity.class, true));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PillagerEntity.class, true));
        this.targetSelector.add(3, new ActiveTargetGoal<>(this, VillagerEntity.class, true));

        this.goalSelector.add(5, new TrackSentinelTargetGoal(this));
    }

    @Override protected EntityNavigation createNavigation(World world) {
        BirdNavigation birdNavigation = new BirdNavigation(this, world){
            public boolean isValidPosition(BlockPos pos) { return !this.world.getBlockState(pos.down()).isAir() || !this.world.getBlockState(pos.down().down()).isAir(); }
        };
        birdNavigation.setCanPathThroughDoors(false);
        birdNavigation.setCanSwim(true);
        birdNavigation.setCanEnterOpenDoors(true);
        return birdNavigation;
    }

    @Override protected SoundEvent getAmbientSound(){ return SoundEvents.ENTITY_BEE_LOOP; }
    @Override protected SoundEvent getHurtSound(DamageSource source){ return SoundEvents.ENTITY_BEE_HURT; }
    @Override protected SoundEvent getDeathSound(){ return SoundEvents.ENTITY_BEE_DEATH; }

    @Override public boolean isCollidable() { return true; }
    @Override public boolean canBreatheInWater() { return true; }

    @Override public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(DefaultAnimations.getSpawnController(this, state -> this, 0));
        controllers.add(DefaultAnimations.genericAttackAnimation(this, ATTACK));
        controllers.add(DefaultAnimations.genericFlyIdleController(this));
    }

    @Override public AnimatableInstanceCache getAnimatableInstanceCache() { return this.cache; }

    static class SentinelLookControl extends LookControl {
        public SentinelLookControl(MobEntity entity) {
            super(entity);
        }

        @Override protected boolean shouldStayHorizontal() { return false; }
    }

    class SentinelWanderGoal extends Goal {
        SentinelWanderGoal() { this.setControls(EnumSet.of(Control.MOVE)); }
        public boolean canStart() { return SentinelEntity.this.navigation.isIdle() && SentinelEntity.this.random.nextInt(10) == 0; }
        public boolean shouldContinue() { return SentinelEntity.this.navigation.isFollowingPath(); }

        public void start() {
            Vec3d vec3d = this.getRandomLocation();
            if (vec3d != null) { SentinelEntity.this.navigation.startMovingAlong(SentinelEntity.this.navigation.findPathTo(BlockPos.ofFloored(vec3d), 1), 1.0); }
        }

        @Nullable private Vec3d getRandomLocation() {
            Vec3d vec3d2 = SentinelEntity.this.getRotationVec(0.0F);
            Vec3d vec3d3 = AboveGroundTargeting.find(SentinelEntity.this, 8, 7, vec3d2.x, vec3d2.z, 1.5707964F, 3, 1);
            return vec3d3 != null ? vec3d3 : NoPenaltySolidTargeting.find(SentinelEntity.this, 8, 4, -2, vec3d2.x, vec3d2.z, 1.5707963705062866);
        }
    }

    public void shootAt(LivingEntity target, float pullProgress) {
        if(getAmmo() > 0){
            LaserEntity laserEntity = new LaserEntity(ModEntities.LASER, getWorld(), this, 12, 1f, TextUtil.SENTINEL_CRIMSON.getRGB());
            double d = target.getX() - this.getX();
            double e = target.getBodyY(0.3333333333333333) - laserEntity.getY();
            double f = target.getZ() - this.getZ();
            double g = Math.sqrt(d * d + f * f);
            laserEntity.setVelocity(d, e + g * 0.20000000298023224, f, 1.5F, (float)(14 - this.getWorld().getDifficulty().getId() * 4));
            this.playSound(ModSounds.BLASTER_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
            this.getWorld().spawnEntity(laserEntity);
            setAmmo(getAmmo()-1);
        }
    }

    public static class TrackSentinelTargetGoal extends TrackTargetGoal {
        private final SentinelEntity sentinel;
        @Nullable
        private LivingEntity target;
        private final TargetPredicate targetPredicate = TargetPredicate.createAttackable().setBaseMaxDistance(64.0);

        public TrackSentinelTargetGoal(SentinelEntity sentinel) {
            super(sentinel, false, true);
            this.sentinel = sentinel;
            this.setControls(EnumSet.of(Control.TARGET));
        }

        public boolean canStart() {
            Box box = this.sentinel.getBoundingBox().expand(10.0, 8.0, 10.0);
            List<? extends LivingEntity> list = this.sentinel.getWorld().getTargets(VillagerEntity.class, this.targetPredicate, this.sentinel, box);
            List<PlayerEntity> list2 = this.sentinel.getWorld().getPlayers(this.targetPredicate, this.sentinel, box);

            for (LivingEntity livingEntity : list) {
                VillagerEntity villagerEntity = (VillagerEntity) livingEntity;
                for (PlayerEntity playerEntity : list2) {
                    int i = villagerEntity.getReputation(playerEntity);
                    if (i <= -100) { this.target = playerEntity; }
                }
            }

            if (this.target == null) { return false; }
            else return !(this.target instanceof PlayerEntity) || !this.target.isSpectator() && !((PlayerEntity) this.target).isCreative();
        }

        public void start() {
            this.sentinel.setTarget(this.target);
            super.start();
        }
    }
}