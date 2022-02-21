package com.gmail.willramanand.RamEssentials.commands;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.utils.Txt;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

@CommandAlias("broadcast|bc")
public class BroadcastCommand extends RBaseCommand {

    private final RamEssentials plugin;

    public BroadcastCommand(RamEssentials plugin) {
        this.plugin = plugin;
    }

    @Default
    @Description("Broadcast message to all players on server.")
    @CommandPermission("ramessentials.broadcast")
    public void broadcastServer(CommandSender sender, String message) {

        if (message.equalsIgnoreCase("")) {
            msg(sender,"{w}You cannot broadcast an empty message.");
            return;
        }

        Bukkit.broadcast(Component.text(Txt.parse(message)));
    }

}
