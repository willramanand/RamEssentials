package com.gmail.willramanand.RamEssentials.listeners;

import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.player.EPlayer;
import com.gmail.willramanand.RamEssentials.utils.ColorUtils;
import com.gmail.willramanand.RamEssentials.utils.TxtReader;
import io.papermc.paper.event.player.AsyncChatEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

    private final RamEssentials plugin;

    public PlayerListener(RamEssentials plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        plugin.getPlayerConfig().load(event.getPlayer());
        TxtReader.sendMotd(event.getPlayer());

        if (!(plugin.getAccountManager().hasAccount(event.getPlayer()))) {
            plugin.getAccountManager().createAccount(event.getPlayer(), 0.0);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        plugin.getPlayerConfig().save(event.getPlayer(), false);
    }

    @EventHandler
    public void checkMuted(AsyncChatEvent event) {
        EPlayer ePlayer = plugin.getPlayerManager().getPlayerData(event.getPlayer());
        if (ePlayer.isMuted()) {
            event.getPlayer().sendMessage(ColorUtils.colorMessage("&cYou are currently muted!"));
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void checkGodMode(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            EPlayer ePlayer = plugin.getPlayerManager().getPlayerData(player);

            if (ePlayer.isGodMode()) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onDeath(EntityDeathEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            EPlayer ePlayer = plugin.getPlayerManager().getPlayerData(player);

            ePlayer.setLastLocation(event.getEntity().getLocation());
        }
    }
}
