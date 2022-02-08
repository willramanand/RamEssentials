package com.gmail.willramanand.RamEssentials.player;

import com.gmail.willramanand.RamEssentials.data.MuteType;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.utils.ColorUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class PlayerConfig {

    private final RamEssentials plugin;

    public PlayerConfig(RamEssentials plugin) {
        this.plugin = plugin;
    }

    public void setup(Player player) {
        File dir = new File(plugin.getDataFolder() + "/playerdata/");
        if (!dir.exists()) {
            dir.mkdir();
        }

        File file = new File(plugin.getDataFolder() + "/playerdata/" + player.getUniqueId() + ".yml");

        if (!file.exists()) {
            try {
                file.createNewFile();
                Bukkit.getServer().getConsoleSender().sendMessage(ColorUtils.colorMessage("&2Created player config for UUID: " + player.getUniqueId()));

                FileConfiguration config = YamlConfiguration.loadConfiguration(file);
                EPlayer ePlayer = new EPlayer(plugin, player);

                if (file.exists()) {

                    plugin.getPlayerManager().addPlayerData(ePlayer);
                    plugin.getAccountManager().createAccount(player, 0.0);

                    try {
                        config.save(file);
                    } catch (IOException e) {
                        Bukkit.getServer().getConsoleSender().sendMessage(ColorUtils.colorMessage("&4Could not save player config for UUID: " + player.getUniqueId()));
                    }
                }
            } catch (IOException e) {
                Bukkit.getServer().getConsoleSender().sendMessage(ColorUtils.colorMessage("&4Could not create player config for UUID: " + player.getUniqueId()));
            }
        }
    }

    public void load(Player player) {
        File file = new File(plugin.getDataFolder() + "/playerdata/" + player.getUniqueId() + ".yml");

        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        EPlayer ePlayer = new EPlayer(plugin, player);

        if (file.exists()) {
            boolean isGodMode = config.getBoolean("isGodMode");
            boolean isDoNotDisturb = config.getBoolean("isDoNotDisturb");
            boolean isMuted = config.getBoolean("isMuted");
            String muteReason = config.getString("muteReason");

            Location lastLocation = config.getLocation("lastLocation");

            ePlayer.setGodMode(isGodMode);
            ePlayer.setDoNotDisturb(isDoNotDisturb);
            ePlayer.setMuted(isMuted);
            ePlayer.setMuteReason(muteReason);

            if (isMuted) {
                ePlayer.setMuteType(MuteType.PERM);
            }

            if (lastLocation != null) {
                ePlayer.setLastLocation(lastLocation);
            }

            ConfigurationSection homesSection = config.getConfigurationSection("homes");

            if (homesSection != null) {
                for (String s : homesSection.getKeys(false)) {
                    ePlayer.addHome(s, homesSection.getLocation(s));
                }
            }

            ConfigurationSection ignoredPlayers = config.getConfigurationSection("ignoredPlayers");

            if (ignoredPlayers != null) {
                for (String s : ignoredPlayers.getKeys(false)) {
                    ePlayer.addIgnored(UUID.fromString(s));
                }
            }

            plugin.getPlayerManager().addPlayerData(ePlayer);
        } else {
            Bukkit.getServer().getConsoleSender().sendMessage(ColorUtils.colorMessage("&bCould not load player config for UUID: " + player.getUniqueId()));
            setup(player);
        }
    }

    public void save(Player player, boolean isShutdown) {
        File file = new File(plugin.getDataFolder() + "/playerdata/" + player.getUniqueId() + ".yml");
        EPlayer ePlayer = plugin.getPlayerManager().getPlayerData(player);

        if (ePlayer == null) return;
        if (ePlayer.shouldNotSave()) return;
        if (ePlayer.isSaving()) return;
        ePlayer.setSaving(true);
        if (file.exists()) {
            FileConfiguration config = YamlConfiguration.loadConfiguration(file);

            config.set("isGodMode", ePlayer.isGodMode());
            config.set("isDoNotDisturb", ePlayer.isDoNotDisturb());

            if (ePlayer.getMuteType() != null && ePlayer.getMuteType() != MuteType.TEMP) {
                config.set("isMuted", ePlayer.isMuted());
                config.set("muteReason", ePlayer.getMuteReason());
            }

            config.set("lastLocation", ePlayer.getLastLocation());
            config.set("logoutLocation", ePlayer.getPlayer().getLocation());

            if (!(ePlayer.getHomeList().isEmpty())) {
                for (String s : ePlayer.getHomeList()) {
                    config.set("homes." + s, ePlayer.getHome(s));
                }
            }

            if (!(ePlayer.getIgnoredPlayers().isEmpty())) {
                config.set("ignoredPlayers", ePlayer.getIgnoredPlayers());
            }

            try {
                config.save(file);
                if (isShutdown) {
                    plugin.getPlayerManager().removePlayerData(player.getUniqueId());
                }
            } catch (IOException e) {
                Bukkit.getServer().getConsoleSender().sendMessage(ColorUtils.colorMessage("&bCould not save player config for UUID: " + player.getUniqueId()));
            }
        } else {
            Bukkit.getServer().getConsoleSender().sendMessage(ColorUtils.colorMessage("&bCould not save player config for UUID: " + player.getUniqueId() + " because it does not exist!"));
        }
        ePlayer.setSaving(false);
    }
}
