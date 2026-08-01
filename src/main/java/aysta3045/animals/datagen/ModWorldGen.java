package aysta3045.animals.datagen;

import aysta3045.animals.enchantments.ModEnchantments;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;
import java.util.concurrent.CompletableFuture;

public class ModWorldGen extends FabricDynamicRegistryProvider {
    public ModWorldGen(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
        // 只添加自己的附魔，不引入原版附魔数据
        RegistryWrapper.Impl<Enchantment> enchantmentRegistry = registries.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
        RegistryEntry.Reference<Enchantment> enchantRef = enchantmentRegistry.getOrThrow(ModEnchantments.ANIMALS_ENCHANTMENT);
        entries.add(enchantRef);
    }

    @Override
    public String getName() {
        return "World Gen Data Generator";
    }
}