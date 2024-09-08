package net.trevorskullcrafter.entity.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.trevorskullcrafter.TrevorsSentinels;
import net.trevorskullcrafter.entity.ModEntities;
import net.trevorskullcrafter.item.TSItems;

public class ShardEntity extends ThrownItemEntity {
    public ShardEntity(EntityType<? extends ShardEntity> entityType, World world) { super(entityType, world); }
    public ShardEntity(World world, LivingEntity owner) { super(ModEntities.SHARD_PROJECTILE, owner, world); }
    public ShardEntity(World world, double x, double y, double z) { super(ModEntities.SHARD_PROJECTILE, x, y, z, world); }

    @Override protected Item getDefaultItem() { return TSItems.Tech.SCRAP_METAL_SHARD; }

	public void setVelocity(float pitch, float yaw, float roll, float speed, float divergence) {
		float x = -MathHelper.sin(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F);
		float y = -MathHelper.sin((pitch + roll) * 0.017453292F);
		float z = MathHelper.cos(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F);
		super.setVelocity(x, y, z, speed, divergence);
	}

    private ParticleEffect getParticleParameters() { // Not entirely sure, but probably has to do with the snowball's particles. (OPTIONAL)
        ItemStack itemStack = TSItems.Tech.SCRAP_METAL_SHARD.getDefaultStack();
        return itemStack.isEmpty() ? ParticleTypes.ITEM_SNOWBALL : new ItemStackParticleEffect(ParticleTypes.ITEM, itemStack);
    }

    public void handleStatus(byte status) {
        if (status == 3) {
            ParticleEffect particleEffect = this.getParticleParameters();
            for(int i = 0; i < 8; ++i) getWorld().addParticle(particleEffect, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
        }
    }

    @Override protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();
		entity.damage(new DamageSource(entity.getWorld().getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(TrevorsSentinels.Registries.SHARD_PROJECTILE)), 3);
	}

    @Override protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!getWorld().isClient()) { getWorld().sendEntityStatus(this, EntityStatuses.PLAY_DEATH_SOUND_OR_ADD_PROJECTILE_HIT_PARTICLES); this.discard(); }
    }
}
