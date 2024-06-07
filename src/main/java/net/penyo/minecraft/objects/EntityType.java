package net.penyo.minecraft.objects;

public enum EntityType {

    PLAYER("player"), WITHER("wither"), ENDER_DRAGON("ender_dragon");

    private final String v;

    EntityType(String v) {
        this.v = v;
    }

    @Override
    public String toString() {
        return v;
    }
}
