package net.trevorskullcrafter.item.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.*;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;
import net.trevorskullcrafter.entity.custom.DaggerEntity;
import net.trevorskullcrafter.util.TextUtil;

import java.util.List;

public class DaggerItem extends Item implements ProjectileItem, ExtendedTooltipItem {
    private final ToolMaterial material;
    private final float attackDamage;
    private final StatusEffectInstance[] effects;

    public DaggerItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, float destroyChance, Item.Settings settings, StatusEffectInstance... effects) {
        super(settings); this.material = toolMaterial; this.attackDamage = (float) attackDamage + toolMaterial.getAttackDamage(); this.effects = effects;
    }

    public ToolMaterial getMaterial() { return this.material; }
    public int getEnchantability() { return this.material.getEnchantability(); }
    public float getAttackDamage() { return this.attackDamage; }
    public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity miner) { return !miner.isCreative(); }
    public boolean isSuitableFor(BlockState state) { return state.isOf(Blocks.COBWEB); }
    public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
        if (state.isOf(Blocks.COBWEB)) { return 7.5F; }
        else { return state.isIn(BlockTags.SWORD_EFFICIENT) ? 1.25F : 1.0F; }
    }

    @Override public UseAction getUseAction(ItemStack stack) { return UseAction.SPEAR; }
    @Override public int getMaxUseTime(ItemStack stack, LivingEntity user) { return 72000; }

    @Override public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.setCurrentHand(hand);
        return TypedActionResult.consume(user.getStackInHand(hand));
    }

    @Override public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (user instanceof PlayerEntity playerEntity) {
            int i = this.getMaxUseTime(stack, playerEntity) - remainingUseTicks;
            if(i >= 10) { if (world instanceof ServerWorld serverWorld) {
                DaggerEntity dagger = new DaggerEntity(serverWorld, user, stack);
                dagger.setVelocity(user, user.getPitch(), user.getYaw(), 0.0f, BowItem.getPullProgress(i) * 3.0f, 0.0f);
                if (playerEntity.getAbilities().creativeMode) dagger.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
                serverWorld.spawnEntity(dagger);
                serverWorld.playSoundFromEntity(null, dagger, SoundEvents.ITEM_TRIDENT_THROW.value(), SoundCategory.PLAYERS,
                        user.isSneaking()? 0.2f : 1f, 1f);
                if (!playerEntity.getAbilities().creativeMode) stack.decrement(1);
            } playerEntity.incrementStat(Stats.USED.getOrCreateStat(stack.getItem())); }
        }
    }

    @Override public List<Text> longText(ItemStack stack) {
        List<Text> tooltip = ExtendedTooltipItem.super.longText(stack);
        for (StatusEffectInstance statusEffectInstance : effects) { tooltip.add(TextUtil.potionText(statusEffectInstance, true)); }
        return tooltip;
    }

    @Override public List<Text> shortText(ItemStack stack) {
        return List.of(Text.empty().append(Text.literal("SHIFT").formatted(Formatting.GOLD)).append(Text.literal(" to show status effects.").formatted(Formatting.DARK_GRAY)));
    }

    @Override public ProjectileEntity createEntity(World world, Position pos, ItemStack stack, Direction direction) {
		DaggerEntity daggerEntity = new DaggerEntity(world, pos.getX(), pos.getY(), pos.getZ(), stack.copyWithCount(1), null);
		daggerEntity.pickupType = PersistentProjectileEntity.PickupPermission.ALLOWED;
		return daggerEntity;
	}
}
