package cn.leomc.capturethewool;

import cn.leomc.languageutils.Language;
import cn.leomc.languageutils.LanguageUtils;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

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

    public static List<Player> getNearByPlayers(Location location, double radius) {
        return location.getWorld().getPlayers().stream().filter(player -> player.getLocation().distance(location) <= radius).collect(Collectors.toList());
    }

    public static void sendMessage(CommandSender sender, String key, Map<String, String> placeHolders) {
        String message;
        if (sender instanceof Player)
            message = LanguageUtils.getMessage(CaptureTheWool.getInstance(), Language.fromPlayer((Player) sender), key, new Language(Locale.US));
        else
            message = LanguageUtils.getMessage(CaptureTheWool.getInstance(), new Language(Locale.SIMPLIFIED_CHINESE), key, new Language(Locale.US));

        if (!placeHolders.isEmpty())
            for (Map.Entry<String, String> entry : placeHolders.entrySet())
                message = message.replace(entry.getKey(), entry.getValue());


        sender.sendMessage(message);

    }

    public static void sendMessage(CommandSender sender, String key) {
        sendMessage(sender, key, Collections.emptyMap());
    }

}
