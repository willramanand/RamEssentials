package com.gmail.willramanand.RamEssentials.commands;

import com.gmail.willramanand.RamEssentials.RamEssentials;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CmdTPADeny extends EssCommand {

    public CmdTPADeny(RamEssentials plugin) {
        super(plugin, true, true, null, 0, 1);
    }

    @Override
    public void perform(CommandContext context) {
        Player playerFrom = context.argAsPlayer(0);

        if (playerFrom == null) {
            if (plugin.getRequestManager().getMostRecentRequest(context.player) == null) {
                context.msg("{w}You have no requests!");
            } else {
                Player playerFromGotten = plugin.getRequestManager().getMostRecentRequest(context.player);
                context.msg("{s}Request denied from {h}" + playerFromGotten.getName());
                context.msgOther(playerFromGotten, "{s}Request to {h}" + context.player.getName() + " {s}denied!");
                plugin.getRequestManager().removeRequest(context.player, playerFromGotten);
            }
        } else {
            if (plugin.getRequestManager().hasRequest(context.player, playerFrom)) {
                context.msg("{s}Request denied from {h}" + playerFrom.getName());
                context.msgOther(playerFrom, "{s}Request to {h}" + context.player.getName() + " {s}denied!");
                plugin.getRequestManager().removeRequest(context.player, playerFrom);
            } else {
                context.msg("{w}You have no request from that player!");
            }
        }
    }

    @Override
    public List<String> tabCompletes(CommandSender sender, String[] args) {
        if (args.length == 1) {
            if (sender instanceof Player player) {
                List<String> playerNames = new ArrayList<>();
                for (Player playerReq : plugin.getRequestManager().getRequests(player)) playerNames.add(playerReq.getName());
                return playerNames;
            }
        }
        return new ArrayList<>();
    }
}
