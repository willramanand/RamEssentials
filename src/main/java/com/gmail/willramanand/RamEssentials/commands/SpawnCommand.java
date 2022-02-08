package com.gmail.willramanand.RamEssentials.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.Subcommand;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.utils.ColorUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;

@CommandAlias("spawn")
public class SpawnCommand extends BaseCommand {

    private final RamEssentials plugin;

    public SpawnCommand(RamEssentials plugin) {
        this.plugin = plugin;
    }

    @Default
    @Description("Teleports you to server spawn.")
    public void serverSpawn(CommandSender sender) {
        Player player = (Player) sender;
        player.teleportAsync(plugin.getServerSpawn().getLocation(), PlayerTeleportEvent.TeleportCause.COMMAND);
        sender.sendMessage(ColorUtils.colorMessage("&eTeleporting to server spawn."));
    }

    @Subcommand("world|w")
    @Description("Teleports you to world spawn.")
    public void worldSpawn(CommandSender sender) {
        Player player = (Player) sender;
        player.teleportAsync(player.getWorld().getSpawnLocation(), PlayerTeleportEvent.TeleportCause.COMMAND);
        sender.sendMessage(ColorUtils.colorMessage("&eTeleporting to world spawn."));
    }
}
