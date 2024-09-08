package net.trevorskullcrafter.item.custom.unique;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.trevorskullcrafter.item.ModDataComponentTypes;
import net.trevorskullcrafter.item.TSItems;
import net.trevorskullcrafter.item.custom.PillarAligned;
import net.trevorskullcrafter.util.Pillars;
import net.trevorskullcrafter.util.StyleUtil;

import java.util.List;

public class LilithBladeItem extends SwordItem implements StyleUtil.StyleSwitchable, PillarAligned {
    public LilithBladeItem(ToolMaterial toolMaterial, Settings settings) { super(toolMaterial, settings.component(ModDataComponentTypes.MAX_STYLE, 2)); }

    @Override public Formatting getStyleSwitchFormatting(ItemStack stack) {
        return TSItems.getOrSetComponent(stack, ModDataComponentTypes.STYLE, 1) == 2? Formatting.RED : Formatting.YELLOW;
    }

    @Override public Pillars.Pillar getPillar(ItemStack stack) {
        if(TSItems.getOrSetComponent(stack, ModDataComponentTypes.STYLE, 1) == 1){ return Pillars.ONODE; }
        else { return Pillars.POWER; }
    }

    @Override public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (world instanceof ServerWorld serverWorld && hand == Hand.MAIN_HAND) {
            if(TSItems.getOrSetComponent(stack, ModDataComponentTypes.STYLE, 1) == 2){
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH,200,1,false,true,false));
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED,200,2,false,false,false));
                user.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE,200,255,false,false,false));
                serverWorld.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_GHAST_SHOOT, SoundCategory.BLOCKS, 2.0F, 0.0F);
                user.getItemCooldownManager().set(this, 200);
            }
        } return super.use(world, user, hand);
    }

    @Override public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if(TSItems.getOrSetComponent(stack, ModDataComponentTypes.STYLE, 1) == 1) target.addStatusEffect(new StatusEffectInstance(StatusEffects.INSTANT_HEALTH,1,3,false,false,false));
        else target.addStatusEffect(new StatusEffectInstance(StatusEffects.INSTANT_DAMAGE,1,3,false,false,false));
        return true;
    }

	@Override public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType config) {
        if (TSItems.getOrSetComponent(stack, ModDataComponentTypes.STYLE, 1) == 1)  tooltip.add(Text.literal("Hits heal those blessed by the sun!").formatted(Formatting.GOLD));
		else {
			tooltip.add(Text.literal("Hits deal a huge amount of damage.").formatted(Formatting.GOLD));
			tooltip.add(Text.literal("Right click to channel your rage.").formatted(Formatting.DARK_RED));
		}
		super.appendTooltip(stack, context, tooltip, config);
	}

    @Override public boolean hasGlint(ItemStack stack){ return TSItems.getOrSetComponent(stack, ModDataComponentTypes.STYLE, 1) == 2; }
}
