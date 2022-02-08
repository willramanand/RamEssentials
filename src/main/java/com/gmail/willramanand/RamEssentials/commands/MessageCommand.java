package com.gmail.willramanand.RamEssentials.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.player.EPlayer;
import com.gmail.willramanand.RamEssentials.utils.ColorUtils;
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
        if (!(sender instanceof Player)) {
            sender.sendMessage(ColorUtils.colorMessage("&cYou must be player to use this command!"));
            return;
        }

        Player playerSender = (Player) sender;

        EPlayer ePlayer = plugin.getPlayerManager().getPlayerData(playerSender);
        if (ePlayer.isDoNotDisturb()) {
            sender.sendMessage(ColorUtils.colorMessage("&cYou cannot send a message if you are do not disturb!"));
            return;
        }


        EPlayer ePlayerOther = plugin.getPlayerManager().getPlayerData(player);
        if (ePlayerOther.isDoNotDisturb()) {
            sender.sendMessage(ColorUtils.colorMessage("&cThis player is do not disturb!"));
            return;
        } else if (ePlayerOther.getIgnoredPlayers().contains(playerSender.getUniqueId())) {
            player.sendMessage(ColorUtils.colorMessage("&cYou cannot send a message to a player ignoring you!"));
            return;
        }

        playerSender.sendMessage(ColorUtils.colorMessage("&e➜ &d" + player.getName() + "&e: " + message));
        player.sendMessage(ColorUtils.colorMessage("&d" + playerSender.getName() + " &e➜ : " + message));
        plugin.getMessageManager().addLastPlayer(player, playerSender);
    }

    @Subcommand("reply")
    @CommandAlias("reply|r")
    @Description("Reply to a person")
    public void replyPlayer(CommandSender sender, String message) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ColorUtils.colorMessage("&cOnly a player can direct reply. Use &d/msg &cinstead!"));
            return;
        }

        EPlayer ePlayer = plugin.getPlayerManager().getPlayerData((Player) sender);
        if (ePlayer.isDoNotDisturb()) {
            sender.sendMessage(ColorUtils.colorMessage("&cYou cannot send a message if you are do not disturb!"));
            return;
        }

        if (plugin.getMessageManager().getLastPlayer((Player) sender) == null) {
            sender.sendMessage(ColorUtils.colorMessage("&cNo recent players to reply to!"));
            return;
        }

        Player playerTo = plugin.getMessageManager().getLastPlayer((Player) sender);

        EPlayer ePlayerOther = plugin.getPlayerManager().getPlayerData(playerTo);
        if (ePlayerOther.isDoNotDisturb()) {
            sender.sendMessage(ColorUtils.colorMessage("&cThis player is do not disturb!"));
            return;
        }

        sender.sendMessage(ColorUtils.colorMessage("&e➜ &d" + playerTo.getName() + "&e: " + message));
        playerTo.sendMessage(ColorUtils.colorMessage("&d" + sender.getName() + " &e➜ : " + message));
        plugin.getMessageManager().addLastPlayer(playerTo, (Player) sender);
    }
}
