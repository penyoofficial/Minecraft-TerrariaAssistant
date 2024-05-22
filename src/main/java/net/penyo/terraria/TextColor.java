package net.penyo.terraria;

import java.util.List;
import java.util.function.Function;

public enum TextColor implements Function<String, String> {
    CHAT("ffffff"), COMMON_EVENT("32ff82"), FIGHT("af4bff"), PLAYER_DEATH("e11919"), NPC_DEATH("ff1919"), NPC_ARRIVAL("327dff"), COMMON_STATE("fff014"), PARTY("ff00a0");

    private final String colorHex;

    TextColor(String colorHex) {
        this.colorHex = colorHex;
    }

    private static String apply(String colorHex, String text) {
        return "[c/" + colorHex + ":" + text + "]";
    }

    @Override
    public String apply(String text) {
        return apply(colorHex, text);
    }

    public static String apply(String text, String colorHexFrom, String colorHexTo) {
        List<Color> cs = new GradientColor(new Color(colorHexFrom), new Color(colorHexTo)).apply(text.length());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cs.size(); i++)
            sb.append(apply(cs.get(i).toString(), String.valueOf(text.charAt(i))));
        return sb.toString();
    }
}
