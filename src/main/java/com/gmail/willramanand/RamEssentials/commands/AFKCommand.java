package com.gmail.willramanand.RamEssentials.commands;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import org.bukkit.entity.Player;

@CommandAlias("afk")
public class AFKCommand extends RBaseCommand {

    private final RamEssentials plugin;

    public AFKCommand(RamEssentials plugin) {
        this.plugin = plugin;
    }

    @Default
    @Description("Toggle your AFK status")
    public void toggleAFK(Player player) {
        if (plugin.getAfkTimer().isAfk(player)) {
            plugin.getAfkTimer().resetTimer(player);
            plugin.getAfkTimer().setLocationAfk(player, player.getLocation());
            plugin.getAfkTimer().removeAfk(player);
        } else {
            plugin.getAfkTimer().addAfk(player);
        }
    }
}
