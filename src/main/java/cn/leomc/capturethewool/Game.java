package cn.leomc.capturethewool;


import org.bukkit.Color;
import org.bukkit.configuration.ConfigurationSection;


import java.util.HashMap;
import java.util.LinkedHashMap;

import java.util.List;

public class Game {

    private final HashMap<Color, Team> teams = new LinkedHashMap<>();

    public Game(ConfigurationSection config){
        for(String teamKey : config.getKeys(false)){
            addTeam(Utils.stringToColor(teamKey), new Team(config.getConfigurationSection(teamKey), this));
        }
    }


    public void addTeam(Color color, Team team){
        this.teams.put(color, team);
    }

    public HashMap<Color, Team> getTeams() {
        return teams;
    }
}
