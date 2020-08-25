package cn.leomc.capturethewool;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class Team {

    public static final NamespacedKey NAMESPACED_KEY = new NamespacedKey(CaptureTheWool.getInstance(), "ctwteam");
    public static final PersistentDataType<Team, Team> PERSISTENT_DATA_TYPE = new TeamDataType<>(Team.class);
    private final HashMap<Color, Wool> wools = new LinkedHashMap();
    private final List<Player> players = new LinkedList<>();
    private Color color;
    private Location spawnPoint = null;
    private ConfigurationSection config;
    private Game belongGame;


    public Team(ConfigurationSection config, Game belongGame, Color teamColor) {
        this.color = teamColor;
        this.config = config;
        this.belongGame = belongGame;
        ConfigurationSection spawnpointLoc = config.getConfigurationSection("spawnpoint.location");
        spawnPoint = new Location(Bukkit.getWorld(spawnpointLoc.getString("world")), spawnpointLoc.getDouble("x"), spawnpointLoc.getDouble("y"), spawnpointLoc.getDouble("z"));
        for (String string : config.getConfigurationSection("wools").getKeys(false)) {
            ConfigurationSection wools = config.getConfigurationSection("wools." + string + "location");
            Color color = Utils.stringToColor(string);
            Wool wool = new Wool(color, new Location(Bukkit.getWorld(wools.getString("world")), wools.getDouble("x"), wools.getDouble("y"), wools.getDouble("z")), this);
            this.wools.put(color, wool);
            Bukkit.getPluginManager().registerEvents(wool, CaptureTheWool.getInstance());
        }

    }


    public List<Player> getPlayers() {
        return players;
    }

    public HashMap<Color, Wool> getWools() {
        return wools;
    }

    public Game getBelongGame() {
        return belongGame;
    }

    public Color getColor() {
        return color;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }
}
