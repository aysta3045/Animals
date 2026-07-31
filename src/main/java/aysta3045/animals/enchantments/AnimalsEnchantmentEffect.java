package aysta3045.animals.enchantments;

import com.mojang.serialization.MapCodec;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;

public record AnimalsEnchantmentEffect() implements EnchantmentEntityEffect {
    public static final MapCodec<AnimalsEnchantmentEffect> CODEC = MapCodec.unit(AnimalsEnchantmentEffect::new);

    @Override
    public void apply(ServerWorld world, int level, EnchantmentEffectContext context, Entity user, Vec3d pos) {
        if (level >= 1 && !world.isClient) {
            // 创建闪电实体
            LightningEntity lightning = EntityType.LIGHTNING_BOLT.create(world);
            if (lightning != null) {
                // 将闪电定位到受害者位置
                lightning.refreshPositionAndAngles(pos.x, pos.y, pos.z, 0.0f, 0.0f);
                // 生成到世界中
                world.spawnEntity(lightning);
            }
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> getCodec() {
        return CODEC;
    }
}