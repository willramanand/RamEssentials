package com.gmail.willramanand.RamEssentials.player;

import com.gmail.willramanand.RamEssentials.RamEssentials;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerManager {

    private final RamEssentials plugin;
    private final ConcurrentHashMap<UUID, EPlayer> playerData;

    public PlayerManager(RamEssentials plugin) {
        this.plugin = plugin;
        this.playerData = new ConcurrentHashMap<>();
        startAutoSave();
    }


    @Nullable
    public EPlayer getPlayerData(Player player) {
        return playerData.get(player.getUniqueId());
    }

    @Nullable
    public EPlayer getPlayerData(UUID id) {
        return this.playerData.get(id);
    }

    public void addPlayerData(@NotNull EPlayer ePlayer) {
        this.playerData.put(ePlayer.getUuid(), ePlayer);
    }

    public void removePlayerData(UUID id) {
        this.playerData.remove(id);
    }

    public boolean hasPlayerData(Player player) {
        return playerData.containsKey(player.getUniqueId());
    }

    public ConcurrentHashMap<UUID, EPlayer> getPlayerDataMap() {
        return playerData;
    }

    public void startAutoSave() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    EPlayer ePlayer = plugin.getPlayerManager().getPlayerData(player);
                    if (ePlayer != null && !ePlayer.isSaving()) {
                        plugin.getPlayerConfig().save(ePlayer.getPlayer(), false);
                    }
                }
            }
        }.runTaskTimer(plugin, 6000L, 6000L);
    }
}