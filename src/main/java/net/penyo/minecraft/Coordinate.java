package net.penyo.minecraft;

public record Coordinate(String x, String y, String z) {

    public static Coordinate ofCurrent() {
        return new Coordinate("~", "~", "~");
    }

    @Override
    public String toString() {
        return x + " " + y + " " + z;
    }
}
