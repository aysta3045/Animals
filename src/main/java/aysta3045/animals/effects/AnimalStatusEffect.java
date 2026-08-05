package aysta3045.animals.effects;

import aysta3045.animals.Animals;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.Identifier;

public class AnimalStatusEffect extends StatusEffect {
    // 1.21+ 修饰符ID统一使用Identifier，格式为 模组ID:修饰符名称，避免冲突
    private static final Identifier SPEED_MODIFIER_ID = Identifier.of(Animals.MOD_ID, "speed_modifier");
    private static final Identifier STRENGTH_MODIFIER_ID = Identifier.of(Animals.MOD_ID, "strength_modifier");

    public AnimalStatusEffect() {
        // 效果分类：有益效果；第二个参数为Buff显示颜色（狼棕色RGB）
        super(StatusEffectCategory.BENEFICIAL, 0x8B4513);

        // 移动速度加成：乘法总值叠加，和原版迅疾逻辑一致
        this.addAttributeModifier(
                EntityAttributes.GENERIC_MOVEMENT_SPEED,
                SPEED_MODIFIER_ID,
                0.1D,
                EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
        );

        // 攻击伤害加成：加法叠加，和原版力量逻辑一致
        this.addAttributeModifier(
                EntityAttributes.GENERIC_ATTACK_DAMAGE,
                STRENGTH_MODIFIER_ID,
                1.0D,
                EntityAttributeModifier.Operation.ADD_VALUE
        );

    }
}