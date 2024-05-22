package net.penyo.terraria;

public class Color extends java.awt.Color {

    public Color(int r, int g, int b) {
        super(r, g, b);
    }

    public Color(String hex) {
        super(decode("#" + hex).getRGB());
    }

    public static Color is(java.awt.Color c) {
        return new Color(c.getRed(), c.getGreen(), c.getBlue());
    }

    @Override
    public String toString() {
        return String.format("%02x%02x%02x", getRed(), getGreen(), getBlue());
    }
}
