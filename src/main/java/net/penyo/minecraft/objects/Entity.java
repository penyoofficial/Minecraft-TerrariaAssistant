package net.penyo.minecraft.objects;

public interface Entity {

    static Entity of(String info) {
        return new Entity() {

            @Override
            public String toString() {
                return info;
            }
        };
    }

    default String except(EntityType et) {
        return this + "[type=!" + et + "]";
    }
}
