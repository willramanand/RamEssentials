package com.gmail.willramanand.RamEssentials.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.utils.ColorUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("broadcastworld|bcw")
public class BroadcastWorldCommand extends BaseCommand {

    private final RamEssentials plugin;

    public BroadcastWorldCommand(RamEssentials plugin) {
        this.plugin = plugin;
    }

    @Default
    @Description("Broadcast message to all players in world.")
    @CommandCompletion("@worlds")
    @CommandPermission("ramessentials.broadcast")
    public void broadcastWorld(CommandSender sender, World world, String message) {

        if (message.equalsIgnoreCase("")) {
            sender.sendMessage(ColorUtils.colorMessage("&cYou cannot broadcast an empty message."));
            return;
        }

        for (Player player : world.getPlayers()) {
            player.sendMessage(ColorUtils.colorMessage(message));
        }
        plugin.getLogger().info(ColorUtils.colorMessage("&6Broadcasted to &d" + world.getName() + "&6: &f" + message));
    }

}
