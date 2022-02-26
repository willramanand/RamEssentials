package com.gmail.willramanand.RamEssentials.commands;

import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.player.EPlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CmdReply extends EssCommand {

    public CmdReply(RamEssentials plugin) {
        super(plugin, true, true, null, 1, 1);
    }

    @Override
    public void perform(CommandContext context) {
        EPlayer ePlayer = context.ePlayer;
        String message = context.argAsConcatString(0, "");
        if (ePlayer.isDoNotDisturb()) {
            context.msg("{w}You cannot send a message if you are do not disturb!");
            return;
        }

        if (plugin.getMessageManager().getLastPlayer(context.player) == null) {
            context.msg("{w}No recent players to reply to!");
            return;
        }

        Player playerTo = plugin.getMessageManager().getLastPlayer(context.player);

        EPlayer ePlayerOther = plugin.getPlayerManager().getPlayerData(playerTo);
        if (ePlayerOther.isDoNotDisturb()) {
            context.msg("{w}This player is do not disturb!");
            return;
        }

        context.msg("{s}➜ {h}" + playerTo.getName() + "{s} " + message);
        context.msgOther(playerTo, "{h}" + context.player.getName() + " {s}➜ " + message);
        plugin.getMessageManager().addLastPlayer(playerTo, context.player);
    }

    @Override
    public List<String> tabCompletes(CommandSender sender, String[] args) {
        return new ArrayList<>();
    }
}
