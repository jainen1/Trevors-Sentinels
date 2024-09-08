package net.trevorskullcrafter.entity.custom;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.trevorskullcrafter.TrevorsSentinels;
import net.trevorskullcrafter.entity.ModEntities;
import net.trevorskullcrafter.item.TSItems;
import org.jetbrains.annotations.Nullable;

public class DaggerEntity extends PersistentProjectileEntity {
	private static final TrackedData<Boolean> ENCHANTED = DataTracker.registerData(DaggerEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private boolean dealtDamage;

    public DaggerEntity(EntityType<? extends DaggerEntity> entityType, World world) { super(entityType, world); }

	public DaggerEntity(World world, LivingEntity owner, ItemStack stack) {
		super(ModEntities.DAGGER_PROJECTILE, owner, world, stack, null);
		this.dataTracker.set(ENCHANTED, stack.hasGlint());
	}

	public DaggerEntity(World world, double x, double y, double z, ItemStack stack, @Nullable ItemStack stack2) {
		super(ModEntities.DAGGER_PROJECTILE, x, y, z, world, stack, stack2);
		this.dataTracker.set(ENCHANTED, stack.hasGlint());
	}

	public void setVelocity(float pitch, float yaw, float roll, float speed, float divergence) {
		float x = -MathHelper.sin(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F);
		float y = -MathHelper.sin((pitch + roll) * 0.017453292F);
		float z = MathHelper.cos(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F);
		super.setVelocity(x, y, z, speed, divergence);
	}

    @Override public boolean isCritical() { return getVelocity().length() > 5; }
    @Override public boolean isCollidable() { return inGround; }
    @Override public ActionResult interact(PlayerEntity player, Hand hand) {
        if (!this.getWorld().isClient && (this.inGround || this.isNoClip()) && this.shake <= 0 && tryPickup(player)) {
            TrevorsSentinels.LOGGER.info("dagger pickup - success");
            player.sendPickup(this, 1);
            this.discard();
            return ActionResult.SUCCESS;
        }
		TrevorsSentinels.LOGGER.info("dagger pickup - fail");
        return ActionResult.PASS;
    }

	@Override public void onPlayerCollision(PlayerEntity player) {}
	@Override protected ItemStack getDefaultItemStack() { return TSItems.Magic.ICICLE_DAGGER.getDefaultStack(); }

	@Override protected float getDragInWater() { return 0.7333f; }
    @Override protected SoundEvent getHitSound() { return SoundEvents.ITEM_TRIDENT_HIT_GROUND; }

    @Nullable protected EntityHitResult getEntityCollision(Vec3d currentPosition, Vec3d nextPosition) {
        return this.dealtDamage ? null : super.getEntityCollision(currentPosition, nextPosition);
    }

	@Override public double getDamage() { return getItemStack().getComponents().get(DataComponentTypes.ATTRIBUTE_MODIFIERS).modifiers().get(0).modifier().value(); }

	private PotionContentsComponent getPotionContents() { return this.getItemStack().getOrDefault(DataComponentTypes.POTION_CONTENTS, PotionContentsComponent.DEFAULT); }
	private void setPotionContents(PotionContentsComponent value) { this.getItemStack().set(DataComponentTypes.POTION_CONTENTS, value); }
	public void addEffect(StatusEffectInstance effect) { this.setPotionContents(this.getPotionContents().with(effect)); }

	@Override protected void onEntityHit(EntityHitResult entityHitResult) {
		Entity entity = entityHitResult.getEntity();
		float f = 8.0F;
		DamageSource damageSource = new DamageSource(entity.getWorld().getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(TrevorsSentinels.Registries.DAGGER_PROJECTILE));
		if (this.getWorld() instanceof ServerWorld serverWorld) { f = EnchantmentHelper.getDamage(serverWorld, getWeaponStack(), entity, damageSource, f); }

		this.dealtDamage = true;
		if (entity.damage(damageSource, f)) {
			if (entity.getType() == EntityType.ENDERMAN) { return; }
			if (this.getWorld() instanceof ServerWorld serverWorld) { EnchantmentHelper.onTargetDamaged(serverWorld, entity, damageSource, getWeaponStack()); }

			if (entity instanceof LivingEntity livingEntity) {
				this.knockback(livingEntity, damageSource);
				this.onHit(livingEntity);
				getPotionContents().getEffects().forEach(effect -> livingEntity.addStatusEffect(new StatusEffectInstance(effect.getEffectType(), effect.getDuration(),
					effect.getAmplifier(), effect.isAmbient(), effect.shouldShowParticles(), effect.shouldShowIcon())));
			}
		}
		this.setVelocity(this.getVelocity().multiply(-0.01, -0.1, -0.01));
		this.playSound(SoundEvents.ITEM_TRIDENT_HIT, 1.0F, 1.0F);
	}

    @Override protected void onCollision(HitResult hitResult) {
        if(hitResult.getType() == HitResult.Type.ENTITY || hitResult.getType() == HitResult.Type.BLOCK) {
            if(getWorld() instanceof ServerWorld serverWorld) {
                ParticleEffect particleEffect = new ItemStackParticleEffect(ParticleTypes.ITEM, this.asItemStack());
                serverWorld.spawnParticles(particleEffect, getX(), getY(), getZ(), 8, 0, 0, 0, 0.1);
                serverWorld.playSound(null, getX(), getY(), getZ(), SoundEvents.ENTITY_ITEM_BREAK, SoundCategory.PLAYERS, 1, 1);
                this.discard();
            }
        } super.onCollision(hitResult);
    }
}
