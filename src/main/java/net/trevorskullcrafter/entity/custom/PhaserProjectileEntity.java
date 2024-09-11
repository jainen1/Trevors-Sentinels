package net.trevorskullcrafter.entity.custom;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.projectile.thrown.ThrownEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.*;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.trevorskullcrafter.TrevorsSentinels;
import net.trevorskullcrafter.block.entity.PhaseplateBlockEntity;
import net.trevorskullcrafter.datagen.BlockTagGenerator;
import net.trevorskullcrafter.sound.ModSounds;
import net.trevorskullcrafter.util.TextUtil;

import java.awt.*;
import java.util.List;

public class PhaserProjectileEntity extends ThrownEntity {
    private static final TrackedData<Integer> COLOR = DataTracker.registerData(PhaserProjectileEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Integer> AGE = DataTracker.registerData(PhaserProjectileEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Integer> LIFETIME = DataTracker.registerData(PhaserProjectileEntity.class, TrackedDataHandlerRegistry.INTEGER);
    public float damage; List<StatusEffectInstance> effects;

    public PhaserProjectileEntity(EntityType<? extends PhaserProjectileEntity> type, World world) { super(type, world); }

    public PhaserProjectileEntity(EntityType<? extends PhaserProjectileEntity> type, double x, double y, double z, World world,
								  int lifetime, float damage, int color, List<StatusEffectInstance> effects) {
        this(type, world); this.setPosition(x, y, z); this.damage = damage; this.effects = effects;
		this.dataTracker.set(COLOR, color); this.dataTracker.set(LIFETIME, lifetime);
    }

    public PhaserProjectileEntity(EntityType<? extends PhaserProjectileEntity> type, World world, LivingEntity owner,
								  int lifetime, float damage, int color, List<StatusEffectInstance> effects) {
        this(type, owner.getX(), owner.getEyeY(), owner.getZ(), world, lifetime, damage, color, effects); setOwner(owner);
    }

    public void setVelocity(float pitch, float yaw, float roll, float speed, float divergence) {
        float x = -MathHelper.sin(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F);
        float y = -MathHelper.sin((pitch + roll) * 0.017453292F);
        float z = MathHelper.cos(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F);
        super.setVelocity(x, y, z, speed, divergence);
    }

    @Override public void tick() {
		super.tick();
		if(!getWorld().isClient()) {
			if(this.getAge() >= getLifetime() || getVelocity().length() <= 0.2f) { this.discard(); }
			else { this.setAge(this.getAge()+1); }
		} else {
			if(this.getAge() == 0){
				Color color = new Color(getColor());
				getWorld().addImportantParticle(TrevorsSentinels.Registries.MUZZLE_FLASH, getX(), getY(), getZ(),
					(double) color.getRed() /255, (double) color.getGreen() /255, (double) color.getBlue() /255);
			}
		}
	}

    @Override protected void onCollision(HitResult hitResult) {
        HitResult.Type type = hitResult.getType();
        if(hitResult.getType() != HitResult.Type.MISS && getWorld() instanceof ServerWorld serverWorld) {
            if (type == HitResult.Type.ENTITY && ((EntityHitResult) hitResult).getEntity() instanceof LivingEntity entity) {
                if (damage < 1) {
                    entity.heal(Math.abs(damage));
                    serverWorld.spawnParticles(ParticleTypes.HEART, getX(), getY(), getZ(), (int) damage, 0, 0, 0, 0.01);
                    serverWorld.playSound(null, getX(), getY(), getZ(), SoundEvents.BLOCK_NOTE_BLOCK_BELL.value(), SoundCategory.PLAYERS, 1, 1);
                } else {
                    entity.damage(new DamageSource(entity.getWorld().getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(TrevorsSentinels.Registries.PHASER_PROJECTILE)), damage);
                    doExplosion(serverWorld, getVelocity().multiply(-1));
                }
                if (effects != null) { for (StatusEffectInstance sEI : effects) { entity.addStatusEffect(new StatusEffectInstance(sEI.getEffectType(),
                        sEI.getDuration(), sEI.getAmplifier(), sEI.isAmbient(), sEI.shouldShowParticles(), sEI.shouldShowIcon())); }}
                entity.addVelocity((getVelocity().multiply(0.1)));
                this.discard();
            } else if (type == HitResult.Type.BLOCK) {
                BlockHitResult blockHitResult = (BlockHitResult) hitResult;
                BlockPos pos = blockHitResult.getBlockPos();
                BlockState state = serverWorld.getBlockState(pos);
                if(collidesWithStateAtPos(pos, state)){
                    state.onProjectileHit(serverWorld, state, blockHitResult, this);
                    Direction sideHit = blockHitResult.getSide();
                    Vec3d reflectionVelocity = this.getVelocity().multiply((sideHit == Direction.EAST || sideHit == Direction.WEST)? -1: 1,
                            (sideHit == Direction.UP || sideHit == Direction.DOWN)? -1: 1,
                            (sideHit == Direction.NORTH || sideHit == Direction.SOUTH)? -1: 1);

                    if(getWorld().getBlockEntity(pos) instanceof PhaseplateBlockEntity phaseplateBlockEntity){
                        phaseplateBlockEntity.color = getColor();
                        phaseplateBlockEntity.markDirty();
                        getWorld().updateListeners(pos, state, state, 0);
                        this.discard();
                    } else if(state.isIn(BlockTagGenerator.LASER_REFLECTIVE)){ reflect(serverWorld, reflectionVelocity); }
                    else { doExplosion(serverWorld, reflectionVelocity); }
                }
            }
        }

        if (type == HitResult.Type.ENTITY) {
            this.onEntityHit((EntityHitResult)hitResult);
            this.getWorld().emitGameEvent(GameEvent.PROJECTILE_LAND, hitResult.getPos(), new GameEvent.Emitter(this, null));
        } else if (type == HitResult.Type.BLOCK) {
            BlockHitResult blockHitResult = (BlockHitResult)hitResult;
            this.onBlockHit(blockHitResult);
            BlockPos blockPos = blockHitResult.getBlockPos();
            this.getWorld().emitGameEvent(GameEvent.PROJECTILE_LAND, blockPos, new GameEvent.Emitter(this, this.getWorld().getBlockState(blockPos)));
        }
    }

    public void reflect(ServerWorld serverWorld, Vec3d velocity){
        serverWorld.spawnParticles(ParticleTypes.CRIT, getX(), getY(), getZ(), 5, velocity.x, velocity.y, velocity.z, 0.02);
        serverWorld.playSound(null, getX(), getY(), getZ(), ModSounds.STEEL_BREAK, SoundCategory.PLAYERS, 1, 1);
        setVelocity(velocity);
        setLifetime(getLifetime()-5);
        //setVelocity(-this.getVelocity().x, -this.getVelocity().y, -this.getVelocity().z, (float) getVelocity().length()-0.2f, 2);
    }

    public void doExplosion(ServerWorld serverWorld, Vec3d velocity){
        serverWorld.spawnParticles(ParticleTypes.SMOKE, getX(), getY(), getZ(), (int) damage, velocity.x, velocity.y, velocity.z, 0.05);
        serverWorld.spawnParticles(ParticleTypes.FLAME, getX(), getY(), getZ(), (int) damage / 2, velocity.x, velocity.y, velocity.z, 0.02);
        serverWorld.playSound(null, getX(), getY(), getZ(), SoundEvents.ENTITY_GENERIC_EXPLODE.value(), SoundCategory.PLAYERS, 1, 1);
        this.discard();
    }

    @Override public boolean hasNoGravity() { return true; }
    @Override protected void initDataTracker(DataTracker.Builder builder) {
		builder.add(COLOR, TextUtil.BLOOD_RED.getRGB());
		builder.add(LIFETIME, 20);
		builder.add(AGE, 0);
		builder.build();
	}
    @Override public boolean collidesWithStateAtPos(BlockPos pos, BlockState state) { return !state.isIn(BlockTagGenerator.LASER_PROJECTILE_PASS); }
    public void setColor(Integer color) { this.getDataTracker().set(COLOR, color); }
    public Integer getColor() { return this.getDataTracker().get(COLOR); }
	public void setAge(Integer age) { this.getDataTracker().set(AGE, age); }
	public Integer getAge() { return this.getDataTracker().get(AGE); }
	public void setLifetime(Integer age) { this.getDataTracker().set(LIFETIME, age); }
	public Integer getLifetime() { return this.getDataTracker().get(LIFETIME); }

	@Override protected void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("Color", getColor());
        nbt.putInt("Age", getAge());
        nbt.putInt("Lifetime", getLifetime());
    }

    @Override protected void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.setColor(nbt.getInt("Color"));
        this.setAge(nbt.getInt("Age"));
        this.setLifetime(nbt.getInt("Lifetime"));
    }
}
