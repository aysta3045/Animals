package aysta3045.animals.effects;

import aysta3045.animals.Animals;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModStatusEffects {
    public static final StatusEffect ANIMALS_EFFECT = new AnimalStatusEffect();

    public static void registerModStatusEffects() {
        // 注册到游戏状态效果注册表
        Registry.register(
                Registries.STATUS_EFFECT,
                Identifier.of(Animals.MOD_ID, "animals_effect"),
                ANIMALS_EFFECT
        );
    }
}