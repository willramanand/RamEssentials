package com.gmail.willramanand.RamEssentials.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.utils.ColorUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

@CommandAlias("broadcast|bc")
public class BroadcastCommand extends BaseCommand {

    private final RamEssentials plugin;

    public BroadcastCommand(RamEssentials plugin) {
        this.plugin = plugin;
    }

    @Default
    @Description("Broadcast message to all players on server.")
    @CommandPermission("ramessentials.broadcast")
    public void broadcastServer(CommandSender sender, String message) {

        if (message.equalsIgnoreCase("")) {
            sender.sendMessage(ColorUtils.colorMessage("&cYou cannot broadcast an empty message."));
            return;
        }

        Bukkit.broadcast(Component.text(ColorUtils.colorMessage(message)));
    }

}
