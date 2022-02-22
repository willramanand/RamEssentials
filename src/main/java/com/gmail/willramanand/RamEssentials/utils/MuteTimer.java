package com.gmail.willramanand.RamEssentials.utils;

import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.player.EPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Date;

public class MuteTimer {

    public static void runMuteTimer() {

        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    EPlayer ePlayer = RamEssentials.getInstance().getPlayerManager().getPlayerData(player);
                    if (!(ePlayer.isMuted())) continue;
                    if (ePlayer.getMuteExpire() != null && ePlayer.getMuteExpire().getTime().before(new Date())) {
                        ePlayer.setMuted(false);
                        ePlayer.setMuteReason(null);
                        ePlayer.setMuteExpire(null);

                        ePlayer.getPlayer().sendMessage(Txt.parse("{s}Your mute penalty has ended!"));
                    }
                }
            }
        }.runTaskTimerAsynchronously(RamEssentials.getInstance(), 20L, 20L);
    }

    public static void clearMute(Player player) {
        EPlayer ePlayer = RamEssentials.getInstance().getPlayerManager().getPlayerData(player);

        ePlayer.setMuted(false);
        ePlayer.setMuteReason(null);
        ePlayer.setMuteExpire(null);
    }
}
