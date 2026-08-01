package aysta3045.animals.enchantments;

import aysta3045.animals.Animals;
import com.mojang.serialization.MapCodec;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;

public record AnimalsEnchantmentEffect() implements EnchantmentEntityEffect {
    public static final MapCodec<AnimalsEnchantmentEffect> CODEC = MapCodec.unit(AnimalsEnchantmentEffect::new);
    private static final Identifier EFFECT_ID = Identifier.of(Animals.MOD_ID, "animals_effect");
    private static final int MAX_AMPLIFIER = 9; // 可选的最大等级

    @Override
    public void apply(ServerWorld world, int level, EnchantmentEffectContext context, Entity user, Vec3d pos) {
        if (level < 1 || world.isClient()) {
            return;
        }

        if (!(user instanceof LivingEntity victim)) {
            return;
        }

        if (victim.getHealth() <= 0.0F) {
            grantEffectToAttacker(context);
            return;
        }

        int victimId = victim.getId();
        world.getServer().execute(() -> {
            Entity delayedVictim = world.getEntityById(victimId);
            if (delayedVictim == null || (delayedVictim instanceof LivingEntity le && !le.isAlive())) {
                grantEffectToAttacker(context);
            }
        });
    }

    private void grantEffectToAttacker(EnchantmentEffectContext context) {
        Entity attacker = context.owner();
        if (!(attacker instanceof ServerPlayerEntity player)) {
            return;
        }

        RegistryEntry.Reference<StatusEffect> effectRef = Registries.STATUS_EFFECT.getEntry(EFFECT_ID)
                .orElseThrow(() -> new IllegalStateException("Missing status effect: " + EFFECT_ID));

        // 直接使用 effectRef（RegistryEntry），不要 .value()
        StatusEffectInstance existing = player.getStatusEffect(effectRef);
        StatusEffectInstance newInstance;
        if (existing != null) {
            int newAmplifier = Math.min(existing.getAmplifier() + 1, MAX_AMPLIFIER);
            newInstance = new StatusEffectInstance(effectRef, 24 * 20, newAmplifier,
                    existing.isAmbient(), existing.shouldShowParticles(), existing.shouldShowIcon());
        } else {
            newInstance = new StatusEffectInstance(effectRef, 24 * 20, 0);
        }
        player.addStatusEffect(newInstance);
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> getCodec() {
        return CODEC;
    }
}