package aysta3045.animals.sound;

import aysta3045.animals.Animals;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
    public static final SoundEvent EFFECT_GRANT = SoundEvent.of(Identifier.of(Animals.MOD_ID, "effect_grant"));

    public static void register() {
        Registry.register(Registries.SOUND_EVENT, Identifier.of(Animals.MOD_ID, "effect_grant"), EFFECT_GRANT);
    }
}