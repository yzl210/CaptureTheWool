package cn.leomc.capturethewool;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.LinkedList;
import java.util.List;

public class Wool implements Listener {

    private Color color;
    private Location location;
    private final List<Player> acquiredPlayers = new LinkedList<>();
    private Team belongTeam;

    public Wool(Color color, Location location, Team belongTeam){
        this.color = color;
        this.location = location;
        this.belongTeam = belongTeam;
    }


    public Color getColor() {
        return color;
    }

    public List<Player> getAcquiredPlayers() {
        return acquiredPlayers;
    }

    public Location getLocation() {
        return location;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
