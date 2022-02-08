package com.gmail.willramanand.RamEssentials.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.utils.ColorUtils;
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
    public void messagePlayer(Player player, Player playerTo, String message) {

        if (player == playerTo) {
            player.sendMessage(ColorUtils.colorMessage("&cYou cannot message yourself!"));
            return;
        }

        player.sendMessage(ColorUtils.colorMessage("&e➜ &d" + playerTo.getName() + "&e: " + message));
        playerTo.sendMessage(ColorUtils.colorMessage("&d" + player.getName() + " &e➜ : " + message));
        plugin.getMessageManager().addLastPlayer(playerTo, player);
    }

    @Subcommand("reply")
    @CommandAlias("reply|r")
    @Description("Reply to a person")
    public void replyPlayer(Player player, String message) {
        if (plugin.getMessageManager().getLastPlayer(player) == null) {
            player.sendMessage(ColorUtils.colorMessage("&cNo recent players to reply to!"));
            return;
        }

        Player playerTo = plugin.getMessageManager().getLastPlayer(player);
        player.sendMessage(ColorUtils.colorMessage("&e➜ &d" + playerTo.getName() + "&e: " + message));
        playerTo.sendMessage(ColorUtils.colorMessage("&d" + player.getName() + " &e➜ : " + message));
        plugin.getMessageManager().addLastPlayer(playerTo, player);
    }
}
