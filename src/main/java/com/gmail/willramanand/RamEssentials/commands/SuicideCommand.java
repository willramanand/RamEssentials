package com.gmail.willramanand.RamEssentials.commands;


import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;

@CommandAlias("suicide")
public class SuicideCommand extends RBaseCommand {

    private final RamEssentials plugin;

    public SuicideCommand(RamEssentials plugin) {
        this.plugin = plugin;
    }

    @Default
    @Description("Kills you")
    public void suicide(Player player) {
        final EntityDamageEvent ede = new EntityDamageEvent(player, EntityDamageEvent.DamageCause.MAGIC, player.getHealth());
        plugin.getServer().getPluginManager().callEvent(ede);

        if (ede.isCancelled()) return;

        player.setHealth(0);
        msg(player, "{w}Killed yourself!");
    }
}
