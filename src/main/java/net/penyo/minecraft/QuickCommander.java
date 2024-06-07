package net.penyo.minecraft;

import net.penyo.minecraft.objects.Entity;
import net.penyo.minecraft.objects.EntitySelector;

public class QuickCommander {

    private static final Commander c = Commander.set();

    public static Runnable effectSelfSuperman() {
        c.effect(true, EntitySelector.SELF.value(), "resistance", -1, 255);
        return c;
    }

    public static Runnable giveSelfSuperStick() {
        c.give(EntitySelector.SELF.value(), "stick", new NBT() {{
            put("Enchantments", new NBTList() {{
                putEnchantments(255, "sharpness", "knockback");
                putEnchantments(1, "fire_aspect", "silk_touch");
            }});
        }}, 1);
        return c;
    }

    public static Runnable summonInstantAtomBomb() {
        c.summon(Entity.of("creeper"), Coordinate.ofCurrent(), new NBT() {{
            put("ExplosionRadius", 114514);
            put("Fuse", 0);
        }});
        return c;
    }
}
