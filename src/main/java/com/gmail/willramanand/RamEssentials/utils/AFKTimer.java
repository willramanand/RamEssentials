package com.gmail.willramanand.RamEssentials.utils;

import com.gmail.willramanand.RamEssentials.RamEssentials;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AFKTimer {

    private final RamEssentials plugin;

    private final Map<Player, Integer> timeAfk;
    private final Map<Player, Location> locationAfk;

    private final List<Player> isAfk;

    public AFKTimer(RamEssentials plugin) {
        this.plugin = plugin;
        this.timeAfk = new HashMap<>();
        this.locationAfk = new HashMap<>();
        this.isAfk = new ArrayList<>();
    }

    public void setupPlayer(Player player) {
        timeAfk.put(player, 0);
        locationAfk.put(player, player.getLocation());
    }

    public void addAfk(Player player) {
        isAfk.add(player);
        player.sendMessage(ColorUtils.colorMessage("&eYou are now AFK."));

        for (Player player1 : Bukkit.getOnlinePlayers()) {
            if (player1 == player) continue;
            player1.sendMessage(ColorUtils.colorMessage("&d" + player.getName() + " &eis now AFK."));
        }
   }

    public void removeAfk(Player player) {
        isAfk.remove(player);

        player.sendMessage(ColorUtils.colorMessage("&eYou are no longer AFK."));

        for (Player player1 : Bukkit.getOnlinePlayers()) {
            if (player1 == player) continue;
            player1.sendMessage(ColorUtils.colorMessage("&d" + player.getName() + " &eis no longer AFK."));
        }
    }

    public boolean isAfk(Player player) {
        return isAfk.contains(player);
    }

    public void resetTimer(Player player) {
        timeAfk.put(player, 0);
    }

    public void incrementTimer(Player player) {
        timeAfk.put(player, timeAfk.get(player) + 1);
    }

    public int getTimer(Player player) {
        return timeAfk.get(player);
    }

    public void setLocationAfk(Player player, Location location) {
        locationAfk.put(player, location);
    }

    public Location getLocationAfk(Player player) {
        return locationAfk.get(player);
    }

    public void runAfkTimer(Player player) {
        new BukkitRunnable() {
            @Override
            public void run() {
                    if (getLocationAfk(player).getBlockX() == player.getLocation().getBlockX() && getLocationAfk(player).getBlockY() == player.getLocation().getBlockY()
                            && getLocationAfk(player).getBlockZ() == player.getLocation().getBlockZ()) {
                        if (!isAfk(player)) {
                            incrementTimer(player);
                            if (getTimer(player) >= 300) {
                                addAfk(player);
                            }
                        }
                    } else {
                        setLocationAfk(player, player.getLocation());
                        resetTimer(player);
                        if (isAfk(player)) {
                            removeAfk(player);
                        }
                    }
                }
        }.runTaskTimerAsynchronously(plugin, 0L, 20L);
    }
}
