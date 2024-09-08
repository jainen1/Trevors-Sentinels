package net.trevorskullcrafter.effect;

import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.trevorskullcrafter.TrevorsSentinels;
import net.trevorskullcrafter.util.TextUtil;

import static net.trevorskullcrafter.TrevorsSentinels.MOD_ID;

public class ModEffects {
    public static RegistryEntry<StatusEffect> REDSTONED = register("redstoned", new RedstonedStatusEffect(StatusEffectCategory.NEUTRAL, TextUtil.BLOOD_RED.getRGB()));
    public static RegistryEntry<StatusEffect> TETHERED = register("tethered", new ModStatusEffect(StatusEffectCategory.NEUTRAL, TextUtil.TETHERED.getRGB()));
	public static RegistryEntry<StatusEffect> FLIGHT = register("flight", new FlightStatusEffect(StatusEffectCategory.BENEFICIAL, TextUtil.FLIGHT.getRGB())
			.addAttributeModifier(EntityAttributes.GENERIC_GRAVITY, Identifier.of(MOD_ID, "effect.flight"), 0.2, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
    //public static RegistryEntry<StatusEffect> COSMIC_FIRE = registerModifications("cosmic_fire", new FireStatusEffect(StatusEffectCategory.HARMFUL, TextUtil.HELLFIRE.getRGB()));
    public static RegistryEntry<StatusEffect> WELL_FED = register("well_fed", new WellFedEffect(StatusEffectCategory.BENEFICIAL, TextUtil.WELL_FED.getRGB())
			.addAttributeModifier(EntityAttributes.GENERIC_MOVEMENT_EFFICIENCY, Identifier.of(MOD_ID, "effect.well_fed"), 0.3, EntityAttributeModifier.Operation.ADD_VALUE));
    public static RegistryEntry<StatusEffect> FORTIFIED = register("fortified", new CureStatusEffect(StatusEffectCategory.BENEFICIAL, TextUtil.FORTIFIED.getRGB(), StatusEffects.WITHER));
    public static RegistryEntry<StatusEffect> INFESTED = register("infested", new InfestedStatusEffect(StatusEffectCategory.HARMFUL, TextUtil.FLESH_PUS.getRGB()));
    public static RegistryEntry<StatusEffect> IRRADIATED = register("irradiated", new IrradiatedStatusEffect(StatusEffectCategory.HARMFUL, TextUtil.IRRADIATED.getRGB()));
    public static RegistryEntry<StatusEffect> NATURES_BOON = register("natures_boon", new NatureBoonEffect(StatusEffectCategory.BENEFICIAL, TextUtil.VIRIDIAN.getRGB()));

	public static RegistryEntry<StatusEffect> SET_BONUS_SCRAP_METAL = register("set_bonus_scrap_metal", new WellFedEffect(StatusEffectCategory.BENEFICIAL, TextUtil.SCRAP_METAL_BASE.getRGB()));
	public static RegistryEntry<StatusEffect> SET_BONUS_DRYADIC = register("set_bonus_dryadic", new WellFedEffect(StatusEffectCategory.BENEFICIAL, TextUtil.VIRIDIAN.getRGB()));
	public static RegistryEntry<StatusEffect> SET_BONUS_ROSE_GOLD = register("set_bonus_rose_gold", new WellFedEffect(StatusEffectCategory.BENEFICIAL, TextUtil.ROSE_GOLD.getRGB()));
	public static RegistryEntry<StatusEffect> SET_BONUS_DRUIDIC = register("set_bonus_druidic", new WellFedEffect(StatusEffectCategory.BENEFICIAL, TextUtil.FLESH_PINK.getRGB()));
	public static RegistryEntry<StatusEffect> SET_BONUS_STARSTEEL = register("set_bonus_starsteel", new WellFedEffect(StatusEffectCategory.BENEFICIAL, TextUtil.STARSTEEL_BASE.getRGB()));
	public static RegistryEntry<StatusEffect> SET_BONUS_IMPERIAL = register("set_bonus_imperial", new WellFedEffect(StatusEffectCategory.BENEFICIAL, TextUtil.IMPERIAL_ALLOY.getRGB()));
	public static RegistryEntry<StatusEffect> SET_BONUS_UNHOLY = register("set_bonus_unholy", new WellFedEffect(StatusEffectCategory.BENEFICIAL, TextUtil.TRANQUIL.getRGB()));
	public static RegistryEntry<StatusEffect> SET_BONUS_NUCLEAR = register("set_bonus_nuclear", new WellFedEffect(StatusEffectCategory.BENEFICIAL, TextUtil.NUCLEAR2.getRGB()));
	public static RegistryEntry<StatusEffect> SET_BONUS_ARMA_DEI = register("set_bonus_arma_dei", new WellFedEffect(StatusEffectCategory.BENEFICIAL, TextUtil.WELL_FED.getRGB()));
	public static RegistryEntry<StatusEffect> SET_BONUS_NANOTECH = register("set_bonus_nanotech", new WellFedEffect(StatusEffectCategory.BENEFICIAL, TextUtil.WELL_FED.getRGB()));
	public static RegistryEntry<StatusEffect> SET_BONUS_ZENITHIUM = register("set_bonus_zenithium", new WellFedEffect(StatusEffectCategory.BENEFICIAL, TextUtil.ZENITHIUM2.getRGB()));

	private static RegistryEntry<StatusEffect> register(String name, StatusEffect entry) { return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(MOD_ID, name), entry); }
    public static void registerStatusEffects(){ TrevorsSentinels.LOGGER.info("Registering status effects..."); }
}
