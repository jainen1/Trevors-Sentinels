package net.trevorskullcrafter.potion;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.trevorskullcrafter.TrevorsSentinels;
import net.trevorskullcrafter.effect.ModEffects;

public class ModPotions {
    public static Potion REDSTONED_POTION;
    public static Potion FLIGHT_POTION;

    public static Potion registerPotion(String name, StatusEffectInstance statusEffectInstance){
        return Registry.register(Registries.POTION, Identifier.of(TrevorsSentinels.MOD_ID, name), new Potion(statusEffectInstance));
    }

    public static void registerPotions(){
        TrevorsSentinels.LOGGER.info("Registering potions... (" + TrevorsSentinels.MOD_ID + ")");
        REDSTONED_POTION = registerPotion("redstoned_potion", new StatusEffectInstance(ModEffects.REDSTONED, 600, 0));
        FLIGHT_POTION = registerPotion("flight_potion", new StatusEffectInstance(ModEffects.REDSTONED, 9600, 0));
    }
}
