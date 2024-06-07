package net.penyo.minecraft;

import net.penyo.minecraft.objects.Entity;

public class Commander implements Runnable {

    private String current = "";

    private Commander() {
    }

    public static Commander set() {
        return new Commander();
    }

    private void set(String... tokens) {
        current = "/" + String.join(" ", tokens);
    }

    public void effect(boolean needGive, Entity e, String enchantment, int keepSec, int lvl) {
        set("effect", needGive ? "give" : "clear", e.toString(), enchantment, keepSec < 0 ? "infinite" : String.valueOf(keepSec), String.valueOf(lvl));
    }

    public void give(Entity e, String obj, NBT nbt, int amount) {
        set("give", e.toString(), obj + nbt.toString(), String.valueOf(amount));
    }

    public void summon(Entity e, Coordinate at, NBT nbt) {
        set("summon", e.toString(), at.toString(), nbt.toString());
    }

    @Override
    public void run() {
        System.out.println(current);
    }
}
