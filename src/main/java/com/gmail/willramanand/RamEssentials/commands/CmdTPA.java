package com.gmail.willramanand.RamEssentials.commands;

import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.player.EPlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class CmdTPA extends EssCommand {

    public CmdTPA(RamEssentials plugin) {
        super(plugin, true, true, null, 1, 1);
    }

    @Override
    public void perform(CommandContext context) {
        Player playerTo = context.argAsPlayer(0);

        if (playerTo == null) {
            context.msg("{w}This player does not exist or is not online!");
            return;
        }

        if (plugin.getRequestManager().hasRequest(playerTo, context.player)) {
            context.msg("{w}You already have an active request with this player!");
            return;
        }

        EPlayer ePlayer = plugin.getPlayerManager().getPlayerData(playerTo);

        if (ePlayer.getIgnoredPlayers().contains(context.player.getUniqueId())) {
            context.msg("{w}You cannot send a request to a player ignoring you!");
            return;
        }

        plugin.getRequestManager().addRequest(playerTo, context.player);
    }

    @Override
    public List<String> tabCompletes(CommandSender sender, String[] args) {
        if (args.length == 1) return tabCompletePlayers();
        return null;
    }
}
