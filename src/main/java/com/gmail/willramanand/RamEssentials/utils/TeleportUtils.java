package com.gmail.willramanand.RamEssentials.utils;

import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.player.EPlayer;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;

public class TeleportUtils {

    public static void teleport(Player player, Location newLoc) {
        Location previousLoc = player.getLocation();

        player.teleportAsync(newLoc, PlayerTeleportEvent.TeleportCause.COMMAND);

        EPlayer ePlayer = RamEssentials.getInstance().getPlayerManager().getPlayerData(player);
        ePlayer.setLastLocation(previousLoc);
    }
}
