package com.gmail.willramanand.RamEssentials.utils;

import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.player.EPlayer;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Map;

public class TeleportUtils {

    private final static Map<Player, Location> teleportingPlayers = new HashMap<>();
    private final static Map<Player, Integer> secondsWaited = new HashMap<>();

    public static void teleport(Player player, Location newLoc) {
        if (RamEssentials.getInstance().getTeleportDelay() <= 0) {

            Location previousLoc = player.getLocation();

            player.teleportAsync(newLoc, PlayerTeleportEvent.TeleportCause.COMMAND);

            EPlayer ePlayer = RamEssentials.getInstance().getPlayerManager().getPlayerData(player);
            ePlayer.setLastLocation(previousLoc);
        } else {
            if (teleportingPlayers.get(player) != null || secondsWaited.get(player) != null) {
                player.sendMessage(Txt.parse("{w}You are already waiting to teleport!"));
                return;
            }

            int seconds = RamEssentials.getInstance().getTeleportDelay();
            int delay = seconds * 20;
            player.sendMessage(Txt.parse("{s}Teleporting in {h}" + seconds + " {s}seconds! Teleport will cancel if you move!"));
            teleportingPlayers.put(player, player.getLocation());

            BukkitTask task = new BukkitRunnable() {

                @Override
                public void run() {

                }
            }.runTaskTimer(RamEssentials.getInstance(), 20, delay);

            if (task.isCancelled()) {
                if (secondsWaited.get(player) >= seconds) {
                    //player
                }
            }
        }
    }
}
