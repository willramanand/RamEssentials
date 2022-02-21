package com.gmail.willramanand.RamEssentials.utils;

import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.player.EPlayer;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class MuteTimer {

    public static void runTimer(EPlayer ePlayer, int seconds) {
        int ticks = seconds * 20;

        new BukkitRunnable() {
            @Override
            public void run() {
                if (!(ePlayer.isMuted())) return;
                ePlayer.setMuted(false);
                ePlayer.setMuteReason(null);
                RamEssentials.getInstance().getTempMutedPlayers().remove(ePlayer.getUuid());

                ePlayer.getPlayer().sendMessage(Txt.parse("{s}Your mute penalty has ended!"));
            }
        }.runTaskLater(RamEssentials.getInstance(), ticks);
    }

    public static void clearMute(UUID uuid) {
        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(uuid);
        Player player = (Player) offlinePlayer;
        EPlayer ePlayer = RamEssentials.getInstance().getPlayerManager().getPlayerData(player);

        ePlayer.setMuted(false);
        ePlayer.setMuteReason(null);
    }
}
