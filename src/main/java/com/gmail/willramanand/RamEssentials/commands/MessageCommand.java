package com.gmail.willramanand.RamEssentials.commands;

import co.aikar.commands.annotation.*;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.player.EPlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("message|msg")
public class MessageCommand extends RBaseCommand {

    private final RamEssentials plugin;

    public MessageCommand(RamEssentials plugin) {
        this.plugin = plugin;
    }

    @Default
    @CommandCompletion("@players")
    @Description("Message a player on the server")
    public void messagePlayer(CommandSender sender, @Flags("other") Player player, String message) {
        if (!(sender instanceof Player)) {
            msg(sender,"{w}You must be player to use this command!");
            return;
        }

        Player playerSender = (Player) sender;

        EPlayer ePlayer = plugin.getPlayerManager().getPlayerData(playerSender);
        if (ePlayer.isDoNotDisturb()) {
            msg(sender, "{w}You cannot send a message if you are do not disturb!");
            return;
        }


        EPlayer ePlayerOther = plugin.getPlayerManager().getPlayerData(player);
        if (ePlayerOther.isDoNotDisturb()) {
            msg(sender, "{w}This player is do not disturb!");
            return;
        } else if (ePlayerOther.getIgnoredPlayers().contains(playerSender.getUniqueId())) {
            msg(sender, "{w}You cannot send a message to a player ignoring you!");
            return;
        }

        msg(playerSender, "{s}➜ {h}" + player.getName() + "{s} " + message);
        msg(player, "{h}" + playerSender.getName() + " {s}➜ " + message);
        plugin.getMessageManager().addLastPlayer(player, playerSender);
    }

    @Subcommand("reply")
    @CommandAlias("reply|r")
    @Description("Reply to a person")
    public void replyPlayer(CommandSender sender, String message) {
        if (!(sender instanceof Player)) {
            msg(sender,"{w}You must be player to use this command!");
            return;
        }

        Player player = (Player) sender;

        EPlayer ePlayer = getData(player);
        if (ePlayer.isDoNotDisturb()) {
            msg(sender, "{w}You cannot send a message if you are do not disturb!");
            return;
        }

        if (plugin.getMessageManager().getLastPlayer((Player) sender) == null) {
            msg(sender, "{w}No recent players to reply to!");
            return;
        }

        Player playerTo = plugin.getMessageManager().getLastPlayer(player);

        EPlayer ePlayerOther = getData(playerTo);
        if (ePlayerOther.isDoNotDisturb()) {
            msg(sender, "{w}This player is do not disturb!");
            return;
        }

        msg(sender, "{s}➜ {h}" + playerTo.getName() + "{s} " + message);
        msg(playerTo, "{h}" + player.getName() + " {s}➜ " + message);
        plugin.getMessageManager().addLastPlayer(playerTo, player);
    }
}
