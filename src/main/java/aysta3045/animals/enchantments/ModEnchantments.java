package aysta3045.animals.enchantments;

import aysta3045.animals.Animals;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModEnchantments {

    // 注册附魔
    public static final RegistryKey<Enchantment> AnimalsEffect = of("animals_effect");


    private static RegistryKey<Enchantment> of(String id) {
        return RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(Animals.MOD_ID, id));
    }

    private static void register(Registerable<Enchantment> registry, RegistryKey<Enchantment> key, Enchantment.Builder builder ) {
        registry.register(key, builder.build(key.getValue()));
    }
}
