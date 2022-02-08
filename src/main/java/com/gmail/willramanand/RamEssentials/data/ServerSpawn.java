package com.gmail.willramanand.RamEssentials.data;

import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.utils.ColorUtils;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

public class ServerSpawn {

    private final RamEssentials plugin;
    private Location spawnLoc;

    public ServerSpawn(RamEssentials plugin) {
        this.plugin = plugin;
    }

    public void load() {
        FileConfiguration config = plugin.getConfig();
        ConfigurationSection section = config.getConfigurationSection("server_spawn");

        if (section == null || section.getLocation("location") == null) {
            plugin.getLogger().info(ColorUtils.colorMessage("&eSetting server spawn to overworld default spawn."));

            spawnLoc = Bukkit.getWorlds().get(0).getSpawnLocation();

            config.set("server_spawn.location", spawnLoc);

            plugin.saveConfig();
        } else {
            spawnLoc = section.getLocation("location");
        }
    }

    public void save() {
        FileConfiguration config = plugin.getConfig();
        ConfigurationSection section = config.getConfigurationSection("server_spawn");

        section.set("location", spawnLoc);

        plugin.saveConfig();
    }

    public void setLocation(Location location) {
        this.spawnLoc = location;
    }

    public Location getLocation() {
        return spawnLoc;
    }

}
