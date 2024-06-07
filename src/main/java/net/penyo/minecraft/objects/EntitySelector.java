package net.penyo.minecraft.objects;

public enum EntitySelector {

    /**
     * current player
     */
    SELF(Entity.of("@s")),

    /**
     * player who is nearest from current player
     */
    NEAREST_PLAYER(Entity.of("@p")),

    /**
     * all players online
     */
    ALL_PLAYERS(Entity.of("@a")),

    /**
     * one of players online
     */
    RANDOM_PLAYER(Entity.of("@r")),

    /**
     * every entities
     */
    EVERY_ENTITIES(Entity.of("@e"));

    private final Entity e;

    EntitySelector(Entity e) {
        this.e = e;
    }

    public Entity value() {
        return e;
    }

    @Override
    public String toString() {
        return e.toString();
    }
}
