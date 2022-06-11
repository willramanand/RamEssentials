package com.gmail.willramanand.RamEssentials.data;

import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.lang.Lang;
import com.gmail.willramanand.RamEssentials.utils.Txt;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Warps {

    private final RamEssentials plugin;
    private final Map<String, Location> warps;

    public Warps(RamEssentials plugin) {
        this.plugin = plugin;
        this.warps = new HashMap<>();
    }

    public void load() {
        File warpsFile = new File(plugin.getDataFolder().getPath() + "/warps.yml");

        try {
            if (!warpsFile.exists()) {
                warpsFile.createNewFile();
            }

            if (warpsFile.exists()) {
                FileConfiguration config = YamlConfiguration.loadConfiguration(warpsFile);
                ConfigurationSection section = config.getConfigurationSection("warps");

                if (section == null) {
                    plugin.getLogger().info(Txt.parse(Lang.WARPS_SECTION_NOT_DETECTED));
                } else {
                    Set<String> warpNames = section.getKeys(false);

                    int i = 0;
                    for (String s : warpNames) {
                        i++;
                        warps.put(s, section.getLocation(s));
                    }
                    plugin.getLogger().info(Txt.process(Lang.WARPS_LOADED, "{count}", String.valueOf(i)));
                }
            }
        } catch (IOException exception) {
            plugin.getLogger().info(Txt.parse(Lang.WARPS_CREATE_FAIL));
        }
    }

    public void save() {
        File warpsFile = new File(plugin.getDataFolder() + "/warps.yml");

        try {
            if (!warpsFile.exists()) {
                warpsFile.createNewFile();
            }

            if (warpsFile.exists()) {
                FileConfiguration config = YamlConfiguration.loadConfiguration(warpsFile);

                config.set("warps", null);
                for (String s : warps.keySet()) {
                    config.set("warps." + s, warps.get(s));
                }

                config.save(warpsFile);
            }
        } catch (IOException e) {
            plugin.getLogger().info(Txt.parse(Lang.WARPS_CREATE_FAIL));
        }
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
