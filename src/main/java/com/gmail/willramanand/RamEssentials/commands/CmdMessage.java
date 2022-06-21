package com.gmail.willramanand.RamEssentials.commands;

import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.player.EPlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CmdMessage extends EssCommand {

    public CmdMessage(RamEssentials plugin) {
        super(plugin, true, true, null, 2, -1);
    }

    @Override
    public void perform(CommandContext context) {
        EPlayer ePlayer = context.ePlayer;
        String message = context.argAsConcatString(1, "");

        if (ePlayer.isDoNotDisturb()) {
            context.msg("{w}You cannot send a message if you are do not disturb!");
            return;
        }

        if (message.equalsIgnoreCase("")) {
            context.msg("{w}You cannot send an empty message!");
            return;
        }

        Player player = context.argAsPlayer(0);

        if (player == null) {
            context.msg("{w}That player does not exist or is not online!");
            return;
        }

        EPlayer ePlayerOther = plugin.getPlayerManager().getPlayerData(player);
        if (ePlayerOther.isDoNotDisturb()) {
            context.msg("{w}This player is do not disturb!");
            return;
        } else if (ePlayerOther.isIgnoring(context.player.getUniqueId())) {
            context.msg("{w}You cannot send a message to a player ignoring you!");
            return;
        }

        context.msg("{s}➜ {h}" + player.getName() + "{s} " + message);
        context.msgOther(player, "{h}" + context.player.getName() + " {s}➜ " + message);
        plugin.getMessageManager().addLastPlayer(player, context.player);
    }

    @Override
    public List<String> tabCompletes(CommandSender sender, String[] args) {
        if (args.length == 1) return tabCompletePlayers();
        return new ArrayList<>();
    }
}
