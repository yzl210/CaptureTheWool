package cn.leomc.capturethewool;

import cn.leomc.languageutils.LanguageFileType;
import cn.leomc.languageutils.LanguageUtils;
import cn.leomc.languageutils.PluginLanguageProvider;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class CaptureTheWool extends JavaPlugin {

    private static final List<Game> GAMES = new LinkedList<>();

    private static CaptureTheWool instance;

    public static CaptureTheWool getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        LanguageUtils.register(this, new PluginLanguageProvider(new File(getDataFolder(), "messages"), LanguageFileType.YAML));
        if (!new File(new File(getDataFolder(), "messages"), "zh_cn.yml").exists())
            saveResource("messages/zh_cn.yml", false);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!command.getName().equals("capturethewool"))
            return false;

        if (args.length <= 0)
            return true;

        if (args[0].equals("create") && args.length > 1) {
            File file = new File(new File(this.getDataFolder(), "games"), args[1] + ".yml");
            if (file.exists()) {
                Utils.sendMessage(sender, "file.exists");
                return true;
            }
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                sender.sendMessage(e.getMessage());
            }


        }
        if (args[0].equals("woolpoint") && args.length > 3) {
            File configFile = new File(new File(getDataFolder(), "games"), args[1] + ".yml");
            if (!configFile.exists()) {
                Utils.sendMessage(sender, "file.not_exists");
                return true;
            }
            YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);
            config.set(args[2] + ".wools." + args[3] + ".location.world", args[4]);
            config.set(args[2] + ".wools." + args[3] + ".location.x", Double.parseDouble(args[5]));
            config.set(args[2] + ".wools." + args[3] + ".location.y", Double.parseDouble(args[6]));
            config.set(args[2] + ".wools." + args[3] + ".location.z", Double.parseDouble(args[7]));
            try {
                config.save(configFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (args[0].equals("spawnpoint") && args.length > 3) {
            File configFile = new File(new File(getDataFolder(), "games"), args[1] + ".yml");
            if (!configFile.exists()) {
                Utils.sendMessage(sender, "file.not_exists");
                return true;
            }
            YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);
            config.set(args[2] + ".spawnpoint." + args[3] + ".location.world", args[4]);
            config.set(args[2] + ".spawnpoint." + args[3] + ".location.x", Double.parseDouble(args[5]));
            config.set(args[2] + ".spawnpoint." + args[3] + ".location.y", Double.parseDouble(args[6]));
            config.set(args[2] + ".spawnpoint." + args[3] + ".location.z", Double.parseDouble(args[7]));
            try {
                config.save(configFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (args[0].equals("addplayer") && args.length > 1) {
            Player player = Bukkit.getPlayer(args[1]);
            if (player == null) {
                HashMap<String, String> ph = new HashMap<>();
                ph.put("%player", args[1]);
                Utils.sendMessage(sender, "player.not_found", ph);
                return true;
            }
            //player.getPersistentDataContainer().set(Team.NAMESPACED_KEY, Team.PERSISTENT_DATA_TYPE, team);

        }

        return true;
    }

    private void loadGames() {
        File gameFolder = new File(getDataFolder(), "games");
        if (!gameFolder.exists()) {
            try {
                gameFolder.getParentFile().mkdirs();
                gameFolder.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        for (File file : gameFolder.listFiles()) {
            YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        }
    }

    @Override
    public void onDisable() {

    }
}
