package com.gmail.willramanand.RamEssentials.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.utils.ColorUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("message|msg")
public class MessageCommand extends BaseCommand {

    private final RamEssentials plugin;

    public MessageCommand(RamEssentials plugin) {
        this.plugin = plugin;
    }

    @Default
    @CommandCompletion("@players")
    @Description("Message a player on the server")
    public void messagePlayer(CommandSender sender, @Flags("other") Player player, String message) {

        sender.sendMessage(ColorUtils.colorMessage("&e➜ &d" + player.getName() + "&e: " + message));
        player.sendMessage(ColorUtils.colorMessage("&d" + sender.getName() + " &e➜ : " + message));
        plugin.getMessageManager().addLastPlayer(player, (Player) sender);
    }

    @Subcommand("reply")
    @CommandAlias("reply|r")
    @Description("Reply to a person")
    public void replyPlayer(CommandSender sender, String message) {
        if (plugin.getMessageManager().getLastPlayer((Player) sender) == null) {
            sender.sendMessage(ColorUtils.colorMessage("&cNo recent players to reply to!"));
            return;
        }

        Player playerTo = plugin.getMessageManager().getLastPlayer((Player) sender);
        sender.sendMessage(ColorUtils.colorMessage("&e➜ &d" + playerTo.getName() + "&e: " + message));
        playerTo.sendMessage(ColorUtils.colorMessage("&d" + sender.getName() + " &e➜ : " + message));
        plugin.getMessageManager().addLastPlayer(playerTo, (Player) sender);
    }
}
