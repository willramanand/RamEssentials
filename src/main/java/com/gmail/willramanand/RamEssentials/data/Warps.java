package com.gmail.willramanand.RamEssentials.data;

import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.utils.ColorUtils;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.Set;

public class Warps {

    private final RamEssentials plugin;
    private final BiMap<String, Location> warps;

    public Warps(RamEssentials plugin) {
        this.plugin = plugin;
        this.warps = HashBiMap.create();
    }

    public void load() {
        FileConfiguration config = plugin.getConfig();
        ConfigurationSection section = config.getConfigurationSection("warps");

        if (section == null) {
            plugin.getLogger().info(ColorUtils.colorMessage("&eNo warps section detected."));
        } else {
            Set<String> warpNames = section.getKeys(false);

            int i = 0;
            for (String s : warpNames) {
                i++;
                warps.put(s, section.getLocation(s));
            }
            plugin.getLogger().info(ColorUtils.colorMessage("&eLoaded &d" + i + " &ewarps."));
        }
    }

    public void save() {
        FileConfiguration config = plugin.getConfig();

        config.set("warps", null);
        for (String s : warps.keySet()) {
            config.set("warps." + s, warps.get(s));
        }

        plugin.saveConfig();
    }

    public void addWarp(String name, Location location) {
        warps.put(name, location);
    }

    public void delWarp(String name) {
        warps.remove(name);

        FileConfiguration config = plugin.getConfig();
        config.set("warps." + name, null);
        plugin.saveConfig();
    }

    public Location warpLocation(String name) {
        return warps.get(name);
    }

    public Set<String> getWarpList() {
        return warps.keySet();
    }

}
