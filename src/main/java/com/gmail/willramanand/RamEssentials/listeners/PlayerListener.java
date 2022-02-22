package com.gmail.willramanand.RamEssentials.listeners;

import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.player.EPlayer;
import com.gmail.willramanand.RamEssentials.utils.Txt;
import com.gmail.willramanand.RamEssentials.utils.TxtReader;
import io.papermc.paper.event.player.AsyncChatEvent;
import org.bukkit.GameMode;
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

        plugin.getAfkTimer().setupPlayer(event.getPlayer());
        plugin.getAfkTimer().runAfkTimer(event.getPlayer());

        EPlayer ePlayer = plugin.getPlayerManager().getPlayerData(event.getPlayer());

        boolean isOp = event.getPlayer().isOp();
        if (ePlayer.isGodMode()) {
            if (!isOp) {
                ePlayer.setGodMode(false);
                event.getPlayer().sendMessage(Txt.parse("{s}God mode {red}disabled{s}."));
            } else {
                event.getPlayer().sendMessage(Txt.parse("{s}God mode {green}enabled{s}."));
            }
        }

        if (event.getPlayer().isFlying()) {
            if (!isOp) {
                event.getPlayer().setAllowFlight(false);
                event.getPlayer().setFlying(false);
                event.getPlayer().sendMessage(Txt.parse("{s}Fly mode {red}disabled{s}."));
            } else {
                event.getPlayer().sendMessage(Txt.parse("{s}Fly mode {green}enabled{s}."));
            }
        }

        if (event.getPlayer().getGameMode() != GameMode.SURVIVAL) {
            if (!isOp) {
                event.getPlayer().setGameMode(GameMode.SURVIVAL);
            }
            event.getPlayer().sendMessage(Txt.parse("{s}Gamemode set to {h}" + event.getPlayer().getGameMode().name() + "{s}."));
        }

        if (ePlayer.isMuted()) {
            long seconds = (ePlayer.getMuteExpire().getTimeInMillis() - System.currentTimeMillis()) / 1000;
            event.getPlayer().sendMessage(Txt.parse("{w}You are currently muted for {h}" + seconds + " {w}seconds!"));
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
            long seconds = (ePlayer.getMuteExpire().getTimeInMillis() - System.currentTimeMillis()) / 1000;
            event.getPlayer().sendMessage(Txt.parse("{w}Muted for: {white}" + ePlayer.getMuteReason()));
            event.getPlayer().sendMessage(Txt.parse("{w}Expires in {h}" + seconds + "{w} seconds!"));
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
