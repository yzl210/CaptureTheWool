package cn.leomc.capturethewool;

import cn.leomc.languageutils.Language;
import cn.leomc.languageutils.LanguageUtils;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class Wool implements Listener {

    private final List<Player> acquiredPlayers = new LinkedList<>();
    private Color color;
    private Location location;
    private Team belongTeam;

    public Wool(Color color, Location location, Team belongTeam) {
        this.color = color;
        this.location = location;
        this.belongTeam = belongTeam;
    }


    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public List<Player> getAcquiredPlayers() {
        return acquiredPlayers;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        List<Player> players = Utils.getNearByPlayers(location, 1);
        if (!acquiredPlayers.contains(event.getPlayer()) && !players.isEmpty()) {
            acquiredPlayers.addAll(players);
            for (Player player : players) {
                for (Player p : belongTeam.getPlayers()) {
                    String message = LanguageUtils.getMessage(CaptureTheWool.getInstance(), Language.fromPlayer(player), "wool.acquire.you", new Language(Locale.US));
                    //Team pteam = event.getPlayer().getPersistentDataContainer().get(Team.NAMESPACED_KEY, Team.PERSISTENT_DATA_TYPE);
                    message = message.replace("%player", event.getPlayer().getDisplayName());
                    p.sendTitle("", message, 20, 20, 20);
                }
            }
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        acquiredPlayers.remove(event.getEntity());

    }


}
