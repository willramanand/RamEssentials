package com.gmail.willramanand.RamEssentials.commands;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.Subcommand;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.utils.TeleportUtils;
import org.bukkit.entity.Player;

@CommandAlias("spawn")
public class SpawnCommand extends RBaseCommand {

    private final RamEssentials plugin;

    public SpawnCommand(RamEssentials plugin) {
        this.plugin = plugin;
    }

    @Default
    @Description("Teleports you to server spawn.")
    public void serverSpawn(Player player) {
        TeleportUtils.teleport(player, plugin.getServerSpawn().getLocation());
        msg(player, "{s}Teleporting to server spawn.");
    }

    @Subcommand("world|w")
    @Description("Teleports you to world spawn.")
    public void worldSpawn(Player player) {
        TeleportUtils.teleport(player, player.getWorld().getSpawnLocation());
        msg(player, "{s}Teleporting to world spawn.");
    }
}
