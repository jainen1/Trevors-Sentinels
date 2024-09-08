package net.trevorskullcrafter.entity.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import net.trevorskullcrafter.entity.ModEntities;
import net.trevorskullcrafter.item.TSItems;

public class GrenadeEntity extends ThrownItemEntity {
    public GrenadeEntity(EntityType<? extends GrenadeEntity> entityType, World world) { super(entityType, world); }
    public GrenadeEntity(World world, LivingEntity owner) { super(ModEntities.DELAYED_EXPLOSIVE, owner, world); }
    public GrenadeEntity(World world, double x, double y, double z) { super(ModEntities.DELAYED_EXPLOSIVE, x, y, z, world); }

    @Override protected Item getDefaultItem() { return TSItems.Tech.SCRAP_METAL_SHARD; }

    private ParticleEffect getParticleParameters() { // Not entirely sure, but probably has to do with the snowball's particles. (OPTIONAL)
        return new ItemStackParticleEffect(ParticleTypes.ITEM, getDefaultItem().getDefaultStack());
    }

    public void handleStatus(byte status) { // Also not entirely sure, but probably also has to do with the particles. This method (as well as the previous one) are optional, so if you don't understand, don't include this one.
        if (status == 3) {
            ParticleEffect particleEffect = this.getParticleParameters();
            for(int i = 0; i < 8; ++i) {
                getWorld().addParticle(particleEffect, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        entity.damage(entity.getDamageSources().mobProjectile(this, this.getOwner() instanceof LivingEntity livingEntity? livingEntity : null), 3);
    }

    @Override protected double getGravity() { return 0.06f; }

    @Override protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!getWorld().isClient) {
            getWorld().sendEntityStatus(this, EntityStatuses.PLAY_DEATH_SOUND_OR_ADD_PROJECTILE_HIT_PARTICLES); this.discard();
        }
    }
}
