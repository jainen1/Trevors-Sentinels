package net.trevorskullcrafter.entity.custom;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.AboveGroundTargeting;
import net.minecraft.entity.ai.NoPenaltySolidTargeting;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.control.FlightMoveControl;
import net.minecraft.entity.ai.control.LookControl;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.BirdNavigation;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.trevorskullcrafter.entity.ModEntities;
import net.trevorskullcrafter.sound.ModSounds;
import net.trevorskullcrafter.util.TextUtil;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class SentinelEntity extends HostileEntity implements Angerable, Monster, RangedAttackMob {
    private static final TrackedData<Integer> AMMO = DataTracker.registerData(SentinelEntity.class, TrackedDataHandlerRegistry.INTEGER);
	private static final TrackedData<Integer> ANGER = DataTracker.registerData(SentinelEntity.class, TrackedDataHandlerRegistry.INTEGER);
	private static final TrackedData<Integer> COLOR = DataTracker.registerData(SentinelEntity.class, TrackedDataHandlerRegistry.INTEGER);
	public final AnimationState IDLE_ANIMATION_STATE = new AnimationState();
	private int idleAnimationTimeout = 0;
	private static final int maxAmmo = 6;
	private UUID targetUuid;

	public SentinelEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
		this.moveControl = new FlightMoveControl(this, 20, true);
		this.lookControl = new SentinelLookControl(this);
		this.setPathfindingPenalty(PathNodeType.DANGER_FIRE, -1.0F);
		this.setPathfindingPenalty(PathNodeType.WATER, -1.0F);
		this.setPathfindingPenalty(PathNodeType.WATER_BORDER, 16.0F);
		this.setPathfindingPenalty(PathNodeType.COCOA, -1.0F);
		this.setPathfindingPenalty(PathNodeType.FENCE, -1.0F);
	}

    public static DefaultAttributeContainer.Builder setAttributes(){
        return HostileEntity.createLivingAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 10.0D)
                .add(EntityAttributes.GENERIC_ARMOR, 0.5D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4.0f)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 2.0f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.8f)
                .add(EntityAttributes.GENERIC_FLYING_SPEED, 0.8f)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 8D);
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

	@Override public EntityNavigation getNavigation() {
		return super.getNavigation();
	}

	@Override public void tick() {
		super.tick();
		if(this.getWorld() instanceof ServerWorld){ updateAnimations(); }
	}

	protected void fall(double fallDistance, boolean onGround, BlockState landedState, BlockPos landedPosition) {}

	public void travel(Vec3d movementInput) {
		if (this.isLogicalSideForUpdatingMovement()) {
			if (this.isTouchingWater()) {
				this.updateVelocity(0.02F, movementInput);
				this.move(MovementType.SELF, this.getVelocity());
				this.setVelocity(this.getVelocity().multiply(0.800000011920929));
			} else if (this.isInLava()) {
				this.updateVelocity(0.02F, movementInput);
				this.move(MovementType.SELF, this.getVelocity());
				this.setVelocity(this.getVelocity().multiply(0.5));
			} else {
				float f = 0.91F;
				if (this.isOnGround()) {
					f = this.getWorld().getBlockState(this.getVelocityAffectingPos()).getBlock().getSlipperiness() * 0.91F;
				}

				float g = 0.16277137F / (f * f * f);
				f = 0.91F;
				if (this.isOnGround()) {
					f = this.getWorld().getBlockState(this.getVelocityAffectingPos()).getBlock().getSlipperiness() * 0.91F;
				}

				this.updateVelocity(this.isOnGround() ? 0.1F * g : 0.02F, movementInput);
				this.move(MovementType.SELF, this.getVelocity());
				this.setVelocity(this.getVelocity().multiply((double)f));
			}
		}

		this.updateLimbs(false);
	}

    @Override public boolean isClimbing() { return false; }

    @Override protected void initGoals(){
		//this.goalSelector.add(0, new SwimGoal(this)); // otherwise mobs will drown
		this.goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.add(8, new LookAroundGoal(this));

		this.goalSelector.add(2, new ProjectileAttackGoal(this, 1.0f, 40, 8)); //Guardian - FireBeamGoal (?)
		this.goalSelector.add(2, new TrackSentinelTargetGoal(this));
		this.goalSelector.add(3, new SentinelReloadGoal(this));
		this.goalSelector.add(7, new SentinelWanderAroundGoal());

		this.targetSelector.add(1, (new SentinelRevengeGoal(this)).setGroupRevenge(new Class[0]));
		this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class,
			10, true, false, (livingEntity) -> Math.abs(livingEntity.getY() - this.getY()) <= 4.0));
		this.targetSelector.add(2, new ActiveTargetGoal<>(this, PillagerEntity.class,
			10, true, false, (livingEntity) -> Math.abs(livingEntity.getY() - this.getY()) <= 4.0));
		this.targetSelector.add(3, new SentinelFollowTargetGoal(this));
		this.targetSelector.add(3, new ActiveTargetGoal<>(this, IronGolemEntity.class,
			10, true, false, (livingEntity) -> Math.abs(livingEntity.getY() - this.getY()) <= 4.0));
		this.targetSelector.add(4, new ActiveTargetGoal<>(this, MerchantEntity.class,
			10, true, false, (livingEntity) -> Math.abs(livingEntity.getY() - this.getY()) <= 4.0));
		this.targetSelector.add(6, new UniversalAngerGoal<>(this, true));
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

	@Override public void shootAt(LivingEntity target, float pullProgress) {
		if(getAmmo() > 0){
			PhaserProjectileEntity projectile = new PhaserProjectileEntity(ModEntities.PHASER_PROJECTILE, getWorld(), this, 12,
				(float) getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE), getColor(), List.of());
			double xDistance = target.getX() - this.getX();
			double yDistance = target.getEyeY() - projectile.getY();
			double zDistance = target.getZ() - this.getZ();
			projectile.setVelocity(xDistance, yDistance, zDistance, 1.8F, (float)(14 - this.getWorld().getDifficulty().getId() * 4));

			//double e = target.getBodyY(0.3333333333333333) - projectile.getY();
			//double horizontalDistance = Math.sqrt(xDistance * xDistance + zDistance * zDistance);
			//projectile.setVelocity(xDistance, e + horizontalDistance * 0.20000000298023224, zDistance, 1.5F, (float)(14 - this.getWorld().getDifficulty().getId() * 4));

			this.playSound(ModSounds.BLASTER_SHOOT, 1.0F, 1.0F);
			this.getWorld().spawnEntity(projectile);
			setAmmo(getAmmo()-1);
		}
	}

	@Override protected void initDataTracker(DataTracker.Builder builder) {
		super.initDataTracker(builder);
		builder.add(AMMO, maxAmmo);
		builder.add(ANGER, 0);
		builder.add(COLOR, TextUtil.SENTINEL_CRIMSON1.getRGB());
		builder.build();
	}

	public boolean isAngryAt(PlayerEntity player) { return this.shouldAngerAt(player); }
	public Integer getAmmo() { return this.dataTracker.get(AMMO); }
	public void setAmmo(int num) { this.dataTracker.set(AMMO, num); }
	public Integer getColor() { return this.dataTracker.get(COLOR); }
	public void setColor(int num) { this.dataTracker.set(COLOR, num); }
	@Override public int getAngerTime() { return this.dataTracker.get(ANGER); }
	@Override public void setAngerTime(int ticks) { this.dataTracker.set(ANGER, ticks); }
	@Nullable @Override public UUID getAngryAt() { return this.targetUuid; }
	@Override public void setAngryAt(@Nullable UUID uuid) { this.targetUuid = uuid; }
	@Override public void chooseRandomAngerTime() { this.setAngerTime(600); }

	class SentinelLookControl extends LookControl {
		SentinelLookControl(MobEntity entity) { super(entity); }
		public void tick() { if (!SentinelEntity.this.hasAngerTime()) { super.tick(); } }
		protected boolean shouldStayHorizontal() { return false; }
	}

	private class SentinelRevengeGoal extends RevengeGoal {
		SentinelRevengeGoal(SentinelEntity bee) { super(bee); }
		public boolean shouldContinue() { return SentinelEntity.this.hasAngerTime() && super.shouldContinue(); }
		protected void setMobEntityTarget(MobEntity mob, LivingEntity target) { if (mob instanceof SentinelEntity && this.mob.canSee(target)) { mob.setTarget(target); } }
	}

	private static class SentinelFollowTargetGoal extends ActiveTargetGoal<PlayerEntity> {
		SentinelFollowTargetGoal(SentinelEntity sentinel) {
			super(sentinel, PlayerEntity.class, 10, true, false, sentinel::shouldAngerAt);
			Objects.requireNonNull(sentinel);
		}

		public boolean canStart() { return this.canAttack() && super.canStart(); }

		public boolean shouldContinue() {
			boolean bl = this.canAttack();
			if (bl && this.mob.getTarget() != null) { return super.shouldContinue(); }
			this.target = null;
			return false;
		}

		private boolean canAttack() {
			SentinelEntity sentinelEntity = (SentinelEntity) this.mob;
			return sentinelEntity.hasAngerTime() && sentinelEntity.getAmmo() > 0;
		}
	}

	public static class SentinelReloadGoal extends Goal {
		private final SentinelEntity sentinel;
		public SentinelReloadGoal(SentinelEntity sentinel){ this.sentinel = sentinel; }

		@Override public boolean canStart() { return sentinel.getAmmo() < 2; }
		@Override public void start() {
			sentinel.setAmmo(maxAmmo);
			sentinel.playSound(ModSounds.PISTOL_RELOAD);
		}
	}

	class SentinelWanderAroundGoal extends Goal {
		SentinelWanderAroundGoal() { this.setControls(EnumSet.of(Control.MOVE)); }

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
