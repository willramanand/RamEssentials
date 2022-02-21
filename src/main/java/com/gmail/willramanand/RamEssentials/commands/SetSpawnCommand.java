package com.gmail.willramanand.RamEssentials.commands;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import org.bukkit.entity.Player;

@CommandAlias("setspawn")
public class SetSpawnCommand extends RBaseCommand {

    private final RamEssentials plugin;

    public SetSpawnCommand(RamEssentials plugin) {
        this.plugin = plugin;
    }

    @Default
    @Description("Set the server spawn.")
    @CommandPermission("ramessentials.setspawn")
    public void serverSpawn(Player player) {
        plugin.getServerSpawn().setLocation(player.getLocation());
        msg(player, "{s}Set server spawn to {h}"
                + player.getLocation().getBlockX() + "{s}, {h}"
                + player.getLocation().getBlockY() + "{s}, {h}"
                + player.getLocation().getBlockZ() + " {s}in {h}"
                + player.getWorld().getName());
    }
}
