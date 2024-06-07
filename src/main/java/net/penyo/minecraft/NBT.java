package net.penyo.minecraft;

import java.util.HashMap;
import java.util.Map;

public class NBT extends HashMap<String, Object> {

    @Override
    public String toString() {
        if (isEmpty()) return "";
        StringBuilder str = new StringBuilder("{");
        for (Map.Entry<String, Object> entry : entrySet()) {
            str.append(entry.getKey()).append(": ").append(entry.getValue()).append(", ");
        }
        return str.replace(str.length() - 2, str.length(), "}").toString();
    }
}
