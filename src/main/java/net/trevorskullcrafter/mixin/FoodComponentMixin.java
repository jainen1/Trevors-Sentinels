package net.trevorskullcrafter.mixin;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(Items.class)
public class FoodComponentMixin {
	@Unique
    FoodComponent REDSTONE = new FoodComponent.Builder().statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 200), 0.85f).build();

    //@ModifyArg(method= "<clinit>", slice= @Slice(from= @At(value= "CONSTANT", args = "stringValue=glow_berries"), to = @At(value = "CONSTANT", args = "stringValue=honeycomb")),
   //         at= @At(value= "INVOKE", target= "Lnet/minecraft/item/Item$Settings;food(Lnet/minecraft/item/FoodComponent;)Lnet/minecraft/item/Item$Settings;"))
    //private static FoodComponent glow_berries(FoodComponent foodComponent){ return copyWithEffect(foodComponent, new StatusEffectInstance(StatusEffects.GLOWING, 100), 0.85f); }

    //@ModifyArg(method= "<clinit>", slice= @Slice(from= @At(value= "CONSTANT", args = "stringValue=milk_bucket"), to = @At(value = "CONSTANT", args = "stringValue=pufferfish_bucket")),
    //        at= @At(value= "INVOKE", target= "Lnet/minecraft/item/MilkBucketItem;<init>(Lnet/minecraft/item/Item$Settings;)V"))
    //private static Item.Settings milk_bucket(Item.Settings settings){
    //    return settings.food(new FoodComponent.Builder().alwaysEdible().statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 3600), 0.8f).build());
    //}

	@ModifyArg(method= "<clinit>", slice= @Slice(from= @At(value= "CONSTANT", args = "stringValue=redstone"), to = @At(value = "CONSTANT", args = "stringValue=saddle")),
		at= @At(value= "INVOKE", target= "Lnet/minecraft/item/AliasedBlockItem;<init>(Lnet/minecraft/block/Block;Lnet/minecraft/item/Item$Settings;)V"))
	private static Item.Settings redstone(Item.Settings settings){
		return settings.food(new FoodComponent.Builder().statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 200), 0.85f).build());
	}

    @Unique private static FoodComponent copyWithEffect(FoodComponent foodComponent, StatusEffectInstance statusEffectInstance, float f){
        FoodComponent.Builder foodComponentBuilder = new FoodComponent.Builder().nutrition(foodComponent.nutrition()).saturationModifier(foodComponent.saturation());
        if(foodComponent.canAlwaysEat()){ foodComponentBuilder.alwaysEdible(); }

        foodComponent.effects().add(new FoodComponent.StatusEffectEntry(statusEffectInstance, f));
        for(FoodComponent.StatusEffectEntry entry : foodComponent.effects()){ foodComponentBuilder.statusEffect(entry.effect(), entry.probability()); }
        return foodComponentBuilder.build();
    }
}
