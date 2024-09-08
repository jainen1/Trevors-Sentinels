package net.trevorskullcrafter.item.custom;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import net.trevorskullcrafter.item.TSItems;

public class CannedItem extends Item {
    public CannedItem(Settings settings) { super(settings); }

    @Override public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        PlayerEntity playerEntity = user instanceof PlayerEntity ? (PlayerEntity)user : null;
        if (playerEntity instanceof ServerPlayerEntity) Criteria.CONSUME_ITEM.trigger((ServerPlayerEntity)playerEntity, stack);
        if (playerEntity != null){ playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
            if (!playerEntity.getAbilities().creativeMode) {
                playerEntity.giveItemStack(new ItemStack(TSItems.Tech.EMPTY_CAN));
            }
        }
		return super.finishUsing(stack, world, user);
	}

    @Override public UseAction getUseAction(ItemStack stack) { return UseAction.DRINK; }

    @Override public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) { return ItemUsage.consumeHeldItem(world, user, hand); }
}
