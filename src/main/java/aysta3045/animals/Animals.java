package aysta3045.animals;

import aysta3045.animals.effects.ModStatusEffects;
import aysta3045.animals.enchantments.ModEnchantmentEffects;
import net.fabricmc.api.ModInitializer;
import net.minecraft.registry.RegistryWrapper;

import java.util.logging.Logger;

public class Animals implements ModInitializer {
    public static final String MOD_ID = "animals";
    public static final Logger LOGGER = Logger.getLogger(MOD_ID);

    // 缓存服务器动态注册表查找器（1.21+ 附魔等内容必须通过动态注册表获取）
    public static RegistryWrapper.WrapperLookup REGISTRY_LOOKUP;

    @Override
    public void onInitialize(){


        // 注册各类内容
        ModEnchantmentEffects.registerModEnchantmentEffects();
        ModStatusEffects.registerModStatusEffects();

        LOGGER.info("Initializing Animals Successfully");
    }
}