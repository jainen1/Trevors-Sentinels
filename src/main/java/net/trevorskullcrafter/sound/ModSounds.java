package net.trevorskullcrafter.sound;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import static com.mojang.text2speech.Narrator.LOGGER;
import static net.trevorskullcrafter.TrevorsSentinels.MOD_ID;

public class ModSounds {
    public static SoundEvent STEEL_BREAK = registerSoundEvent("steel_break");
    public static SoundEvent STEEL_STEP = registerSoundEvent("steel_step");
    public static SoundEvent STEEL_PLACE = registerSoundEvent("steel_place");
    public static SoundEvent STEEL_HIT = registerSoundEvent("steel_hit");
    public static SoundEvent STEEL_FALL = registerSoundEvent("steel_fall");

    public static SoundEvent FLESH_BREAK = registerSoundEvent("flesh_break");
    public static SoundEvent FLESH_STEP = registerSoundEvent("flesh_step");
    public static SoundEvent FLESH_PLACE = registerSoundEvent("flesh_place");
    public static SoundEvent FLESH_HIT = registerSoundEvent("flesh_hit");
    public static SoundEvent FLESH_FALL = registerSoundEvent("flesh_fall");
    public static SoundEvent FLESH_AMBIENT = registerSoundEvent("flesh_ambient");

    public static SoundEvent HARD_LIGHT_BREAK = registerSoundEvent("hard_light_break");
    public static SoundEvent HARD_LIGHT_STEP = registerSoundEvent("hard_light_step");
    public static SoundEvent HARD_LIGHT_PLACE = registerSoundEvent("hard_light_place");
    public static SoundEvent HARD_LIGHT_HIT = registerSoundEvent("hard_light_hit");
    public static SoundEvent HARD_LIGHT_FALL = registerSoundEvent("hard_light_fall");

    public static SoundEvent BLASTER_SHOOT = registerSoundEvent("blaster_shoot");
    public static SoundEvent PISTOL_RELOAD = registerSoundEvent("pistol_reload");
    public static SoundEvent SHOTGUN_RELOAD = registerSoundEvent("shotgun_reload");
    public static SoundEvent SNIPER_RELOAD = registerSoundEvent("sniper_reload");
    public static SoundEvent BIO_RELOAD = registerSoundEvent("bio_reload");

    public static SoundEvent ASSASSINATION_UPLOAD = registerSoundEvent("assassination_upload");
    public static SoundEvent ODE_TO_TRANQUILITY = registerSoundEvent("ode_to_tranquility");
    public static SoundEvent LAPSE_IN_JUDGEMENT = registerSoundEvent("lapse_in_judgement");
    public static SoundEvent RECITAL = registerSoundEvent("recital");

    public static SoundEvent DATABASE_OPEN = registerSoundEvent("database_open");
    public static SoundEvent DATABASE_FLIP = registerSoundEvent("database_flip");
    public static SoundEvent NOTCH_UP = registerSoundEvent("notch_up");

    public static final BlockSoundGroup STEEL_SOUNDS = new BlockSoundGroup(0.8f,1f,
            ModSounds.STEEL_BREAK, ModSounds.STEEL_STEP, ModSounds.STEEL_PLACE, ModSounds.STEEL_HIT, ModSounds.STEEL_FALL);

    public static final BlockSoundGroup FLESH_SOUNDS = new BlockSoundGroup(1.2f, 1f,
            ModSounds.FLESH_BREAK, ModSounds.FLESH_STEP, ModSounds.FLESH_PLACE, ModSounds.FLESH_HIT, ModSounds.FLESH_FALL);

    public static final BlockSoundGroup HARD_LIGHT_SOUNDS = new BlockSoundGroup(1.0f, 5f,
            ModSounds.HARD_LIGHT_BREAK, ModSounds.HARD_LIGHT_STEP, ModSounds.HARD_LIGHT_PLACE, ModSounds.HARD_LIGHT_HIT, ModSounds.HARD_LIGHT_FALL);

    private static SoundEvent registerSoundEvent(String name){
        LOGGER.info("Registering sound "+ name +"... ("+ MOD_ID +")");
        Identifier id = Identifier.of(MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }
}
