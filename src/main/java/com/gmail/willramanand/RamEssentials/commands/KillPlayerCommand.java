package com.gmail.willramanand.RamEssentials.commands;


import co.aikar.commands.annotation.*;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;

@CommandAlias("killplayer|killp")
public class KillPlayerCommand extends RBaseCommand {

    private final RamEssentials plugin;

    public KillPlayerCommand(RamEssentials plugin) {
        this.plugin = plugin;
    }

    @Default
    @Description("Kills player")
    @CommandPermission("ramessentials.killplayer")
    public void killPlayer(CommandSender sender, @Flags("other")Player player) {
        final EntityDamageEvent ede = new EntityDamageEvent(player, EntityDamageEvent.DamageCause.MAGIC, player.getHealth());
        plugin.getServer().getPluginManager().callEvent(ede);

        if (ede.isCancelled()) return;
        player.setHealth(0);
        msg(sender, "{s}Killed {h}" + player.getName());
    }
}
