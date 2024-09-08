package net.trevorskullcrafter.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import net.trevorskullcrafter.effect.ModEffects;
import net.trevorskullcrafter.item.ModArmorMaterials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(ArmorItem.class)
public abstract class ArmorItemMixin extends ItemMixin {
	@Unique public int correctArmorSet(ArmorMaterial material, LivingEntity entity) {
		int num = 0;
		for(ItemStack stack : entity.getArmorItems()){ if(stack.getItem() instanceof ArmorItem armorItem && armorItem.getMaterial().value() == material) { num++; }}
		return num;
	}

	@Override public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected, CallbackInfo ci) {
		if (entity instanceof LivingEntity livingEntity && stack.getItem() instanceof ArmorItem armorItem){
			ArmorMaterial material = armorItem.getMaterial().value();
			if(correctArmorSet(material, livingEntity) == 4){
				RegistryEntry<StatusEffect> effect = null;
				if (material == ModArmorMaterials.DRYADIC.value()) { effect = ModEffects.SET_BONUS_DRYADIC; }
				else if (material == ArmorMaterials.LEATHER.value()) { effect = StatusEffects.SPEED; }
				else if (material == ArmorMaterials.CHAIN.value()) { effect = StatusEffects.NIGHT_VISION; }
				else if (material == ArmorMaterials.IRON.value()) { effect = StatusEffects.RESISTANCE; }
				else if (material == ArmorMaterials.GOLD.value()) { effect = StatusEffects.LUCK; }
				else if (material == ArmorMaterials.DIAMOND.value()) { effect = StatusEffects.HASTE; }
				else if (material == ArmorMaterials.TURTLE.value()) { effect = StatusEffects.WATER_BREATHING; }
				else if (material == ArmorMaterials.NETHERITE.value()) { effect = StatusEffects.FIRE_RESISTANCE; }
				if(effect != null && !livingEntity.hasStatusEffect(effect)) {
					livingEntity.addStatusEffect(new StatusEffectInstance(effect, 5, 0, true, false));
				}
			}
		}
		super.inventoryTick(stack, world, entity, slot, selected, ci);
	}

	@Override public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type, CallbackInfo ci) {
		if(stack.getHolder() instanceof LivingEntity livingEntity && stack.getItem() instanceof ArmorItem armorItem) {
			ArmorMaterial material = armorItem.getMaterial().value();
			if (correctArmorSet(material, livingEntity) == 4) { tooltip.add(Text.translatable("tooltip.trevorssentinels.set_bonus_active").formatted(Formatting.YELLOW)); }
			else { tooltip.add(Text.translatable("tooltip.trevorssentinels.set_bonus_inactive")
				.append(Text.literal(" (" + correctArmorSet(material, livingEntity) + "/4)")).formatted(Formatting.DARK_GRAY)); }
		}
		super.appendTooltip(stack, context, tooltip, type, ci);
	}
}
