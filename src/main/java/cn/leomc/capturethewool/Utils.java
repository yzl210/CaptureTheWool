package cn.leomc.capturethewool;

import org.bukkit.Color;

import java.lang.reflect.Field;

public class Utils {

    public static Color stringToColor(String value) {
        if (value == null) {
            return Color.WHITE;
        }
        try {
            final Field f = Color.class.getField(value.toUpperCase());
            return (Color) f.get(null);
        } catch (Exception ce) {
            return Color.WHITE;
        }
    }

}
