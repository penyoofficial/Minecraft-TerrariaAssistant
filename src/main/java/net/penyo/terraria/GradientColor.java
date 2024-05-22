package net.penyo.terraria;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public record GradientColor(Color from,
                            Color to) implements Function<Integer, List<Color>> {

    @Override
    public List<Color> apply(Integer points) {
        int p = points < 2 ? 2 : points;

        int deltaR = to.getRed() - from.getRed();
        int deltaG = to.getGreen() - from.getGreen();
        int deltaB = to.getBlue() - from.getBlue();

        return new ArrayList<>() {{
            for (int i = 0; i < p; i++)
                add(new Color(from.getRed() + deltaR / p * i, from.getGreen() + deltaG / p * i, from.getBlue() + deltaB / p * i));
        }};
    }
}
