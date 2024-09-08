package net.trevorskullcrafter.item.custom;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import net.trevorskullcrafter.util.TextUtil;

import java.util.List;

public class InfiniteEffectItem extends Item implements ExtendedTooltipItem {
    StatusEffectInstance[] effects;

    public InfiniteEffectItem(Settings settings, StatusEffectInstance... effects) { super(settings); this.effects = effects; }

	@Override public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if(world instanceof ServerWorld && entity instanceof LivingEntity livingEntity && selected){ for(StatusEffectInstance effect : effects){
			if(!livingEntity.hasStatusEffect(effect.getEffectType())) { livingEntity.addStatusEffect(new StatusEffectInstance(effect.getEffectType(),
				effect.getDuration(), effect.getAmplifier(), effect.isAmbient(), effect.shouldShowParticles(), effect.shouldShowIcon()));
			}
		}}
    }

	@Override public List<Text> longText(ItemStack stack) {
		List<Text> tooltip = ExtendedTooltipItem.super.longText(stack);
		PotionContentsComponent effects = stack.getComponents().get(DataComponentTypes.POTION_CONTENTS);
		if(effects != null && effects.hasEffects()) { effects.getEffects().forEach(effect -> tooltip.add(TextUtil.potionText(effect, true))); }
		return tooltip;
	}

	@Override public List<Text> shortText(ItemStack stack) {
		return List.of(Text.empty().append(Text.literal("SHIFT").formatted(Formatting.GOLD)).append(Text.literal(" to show status effects.").formatted(Formatting.DARK_GRAY)));
	}
}
