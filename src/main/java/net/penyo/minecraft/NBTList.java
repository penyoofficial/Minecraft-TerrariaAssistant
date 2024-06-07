package net.penyo.minecraft;

import java.util.ArrayList;

public class NBTList extends ArrayList<NBT> {

    public void putEnchantments(int lvl, String... ids) {
        for (String id : ids)
            add(new NBT() {{
                put("id", id);
                put("lvl", lvl);
            }});
    }
}
