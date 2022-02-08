package com.gmail.willramanand.RamEssentials.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.utils.ColorUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;

@CommandAlias("setspawn")
public class SetSpawnCommand extends BaseCommand {

    private final RamEssentials plugin;

    public SetSpawnCommand(RamEssentials plugin) {
        this.plugin = plugin;
    }

    @Default
    @Description("Set the server spawn.")
    @CommandPermission("ramessentials.setspawn")
    public void serverSpawn(CommandSender sender) {
        Player player = (Player) sender;
        plugin.getServerSpawn().setLocation(player.getLocation());
        sender.sendMessage(ColorUtils.colorMessage("&Set server spawn to &d"
                + player.getLocation().getBlockX() + ", "
                + player.getLocation().getBlockY() + ", "
                + player.getLocation().getBlockZ() + " &ein &d"
                + player.getWorld().getName()));
    }
}
