package net.trevorskullcrafter.trevorssentinels.entity.custom;

import net.minecraft.entity.*;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.world.World;
import net.trevorskullcrafter.trevorssentinels.entity.ModEntities;
import org.jetbrains.annotations.Nullable;

public class NuclearChargeEntity extends TntEntity {
    private static final TrackedData<Integer> FUSE;
    @Nullable private LivingEntity causingEntity;

    public NuclearChargeEntity(EntityType<? extends TntEntity> entityType, World world) {
        super(entityType, world);
        this.intersectionChecked = true;
    }

    public NuclearChargeEntity(World world, double x, double y, double z, @Nullable LivingEntity igniter) {
        this(ModEntities.NUCLEAR_CHARGE, world);
        this.setPosition(x, y, z);
        double d = world.random.nextDouble() * 6.2831854820251465;
        this.setVelocity(-Math.sin(d) * 0.02, 0.20000000298023224, -Math.cos(d) * 0.02);
        this.setFuse(80);
        this.prevX = x;
        this.prevY = y;
        this.prevZ = z;
        this.causingEntity = igniter;
    }

    @Override protected void initDataTracker() { this.dataTracker.startTracking(FUSE, 400); }

    @Override protected Entity.MoveEffect getMoveEffect() { return Entity.MoveEffect.NONE; }

    @Override public void tick() {
        if (!this.hasNoGravity()) { this.setVelocity(this.getVelocity().add(0.0, -0.04, 0.0)); }
        this.move(MovementType.SELF, this.getVelocity());
        this.setVelocity(this.getVelocity().multiply(0.98));
        if (isOnGround()) { this.setVelocity(this.getVelocity().multiply(0.7, -0.5, 0.7)); }
        int i = this.getFuse() - 1; this.setFuse(i);
        if (i <= 0) { this.discard(); if (!getWorld().isClient()) this.explode();
        } else {
            this.updateWaterState();
            if (getWorld().isClient()) { getWorld().addParticle(ParticleTypes.SMOKE, this.getX(), this.getY() + 0.5, this.getZ(), 0.0, 0.0, 0.0); }
        }
    }

    private void explode() {
        getWorld().createExplosion(this, this.getX(), this.getBodyY(0.0625), this.getZ(), 20.0f, World.ExplosionSourceType.TNT);
    }

    @Override public boolean shouldRender(double distance) { return super.shouldRender(distance); }

    protected void writeCustomDataToNbt(NbtCompound nbt) { nbt.putShort("Fuse", (short)this.getFuse()); }
    protected void readCustomDataFromNbt(NbtCompound nbt) { this.setFuse(nbt.getShort("Fuse")); }

    public @Nullable LivingEntity getCausingEntity() { return this.causingEntity; }

    @Override protected float getEyeHeight(EntityPose pose, EntityDimensions dimensions) { return 0.15f; }

    public void setFuse(int fuse) { this.dataTracker.set(FUSE, fuse); }
    public int getFuse() { return this.dataTracker.get(FUSE); }

    static { FUSE = DataTracker.registerData(NuclearChargeEntity.class, TrackedDataHandlerRegistry.INTEGER); }
}