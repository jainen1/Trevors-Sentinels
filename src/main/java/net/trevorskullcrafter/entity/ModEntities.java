package net.trevorskullcrafter.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.trevorskullcrafter.TrevorsSentinels;
import net.trevorskullcrafter.entity.custom.*;

public class ModEntities {
    public static final EntityType<SentinelEntity> SENTINEL = Registry.register(
		Registries.ENTITY_TYPE, Identifier.of(TrevorsSentinels.MOD_ID, "sentinel"),
		EntityType.Builder.create(SentinelEntity::new, SpawnGroup.MONSTER)
			.dimensions(0.7f, 0.6f).build());
    public static final EntityType<RoombaEntity> JANITOR_DROID = Registry.register(
		Registries.ENTITY_TYPE, Identifier.of(TrevorsSentinels.MOD_ID, "sentinum_roomba"),
		EntityType.Builder.create(RoombaEntity::new, SpawnGroup.CREATURE)
			.dimensions(0.75f, 0.2f).build());
    public static final EntityType<FlorbusEntity> FLORBUS = Registry.register(
		Registries.ENTITY_TYPE, Identifier.of(TrevorsSentinels.MOD_ID, "florbus"),
		EntityType.Builder.create(FlorbusEntity::new, SpawnGroup.CREATURE)
			.dimensions(0.8f, 1.25f).build());

    public static final EntityType<ShardEntity> SHARD_PROJECTILE = Registry.register(
		Registries.ENTITY_TYPE, Identifier.of(TrevorsSentinels.MOD_ID, "shard_projectile"),
		EntityType.Builder.<ShardEntity>create(ShardEntity::new, SpawnGroup.MISC)
			.dimensions(0.25F, 0.25F).maxTrackingRange(32).trackingTickInterval(10).build());
    public static final EntityType<DaggerEntity> DAGGER_PROJECTILE = Registry.register(
		Registries.ENTITY_TYPE, Identifier.of(TrevorsSentinels.MOD_ID, "dagger_projectile"),
		EntityType.Builder.<DaggerEntity>create(DaggerEntity::new, SpawnGroup.MISC)
			.dimensions(0.35F, 0.15F).maxTrackingRange(32).trackingTickInterval(10).build());
    public static final EntityType<PhaserProjectileEntity> PHASER_PROJECTILE = Registry.register(
		Registries.ENTITY_TYPE, Identifier.of(TrevorsSentinels.MOD_ID, "phaser_projectile"),
		EntityType.Builder.<PhaserProjectileEntity>create(PhaserProjectileEntity::new, SpawnGroup.MISC)
			.dimensions(0.25F, 0.25F).makeFireImmune().maxTrackingRange(32).trackingTickInterval(10).build());

	public static final EntityType<GrenadeEntity> DELAYED_EXPLOSIVE = Registry.register(
		Registries.ENTITY_TYPE, Identifier.of(TrevorsSentinels.MOD_ID, "grenade"),
		EntityType.Builder.<GrenadeEntity>create(GrenadeEntity::new, SpawnGroup.MISC)
			.dimensions(0.25F, 0.25F).maxTrackingRange(32).trackingTickInterval(10).build());

	public static void registerModEntities(){ TrevorsSentinels.LOGGER.info("Registering entities..."); }
}
