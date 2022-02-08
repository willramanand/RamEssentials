package com.gmail.willramanand.RamEssentials.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.utils.ColorUtils;
import com.gmail.willramanand.RamEssentials.utils.TeleportUtils;
import org.bukkit.entity.Player;

@CommandAlias("tpa")
public class TPACommand extends BaseCommand {

    private final RamEssentials plugin;

    public TPACommand(RamEssentials plugin) {
        this.plugin = plugin;
    }

    @Default
    @Description("Make a teleport requests to specified player.")
    @CommandCompletion("@players")
    public void makeRequest(Player player, Player playerTo) {
        if (plugin.getRequestManager().hasRequest(playerTo, player)) {
            player.sendMessage(ColorUtils.colorMessage("&cYou already have an active request with this player!"));
            return;
        }

        if (player == playerTo) {
            player.sendMessage(ColorUtils.colorMessage("&cYou cannot send a request to yourself!"));
            return;
        }

        plugin.getRequestManager().addRequest(playerTo, player);
    }

    @Subcommand("accept")
    @CommandAlias("tpaaccept")
    @Description("Accept a teleport request")
    @CommandCompletion("@requests")
    public void acceptRequest(Player playerTo, @Optional Player playerFrom) {
        if (playerFrom == null) {
            if (plugin.getRequestManager().getMostRecentRequest(playerTo) == null) {
                playerTo.sendMessage(ColorUtils.colorMessage("&cYou have no requests!"));
            } else {
                Player playerFromGotten = plugin.getRequestManager().getMostRecentRequest(playerTo);
                playerTo.sendMessage(ColorUtils.colorMessage("&eRequest accepted from &d" + playerFromGotten.getName()));
                playerFromGotten.sendMessage(ColorUtils.colorMessage("&eRequest to &d" + playerTo.getName() + " &eaccepted!"));
                TeleportUtils.teleport(playerFromGotten, playerTo.getLocation());
                plugin.getRequestManager().removeRequest(playerTo, playerFromGotten);
            }
        } else {
            if (plugin.getRequestManager().hasRequest(playerTo, playerFrom)) {
                playerTo.sendMessage(ColorUtils.colorMessage("&eRequest accepted from &d" + playerFrom.getName()));
                playerFrom.sendMessage(ColorUtils.colorMessage("&eRequest to &d" + playerTo.getName() + " &eaccepted!"));
                TeleportUtils.teleport(playerFrom, playerTo.getLocation());
                plugin.getRequestManager().removeRequest(playerTo, playerFrom);
            } else {
                playerTo.sendMessage(ColorUtils.colorMessage("&cYou have no request from that player!"));
            }
        }
    }

    @Subcommand("deny")
    @CommandAlias("tpadeny")
    @Description("Deny a teleport request")
    @CommandCompletion("@requests")
    public void denyRequest(Player playerTo, @Optional Player playerFrom) {
        if (playerFrom == null) {
            if (plugin.getRequestManager().getMostRecentRequest(playerTo) == null) {
                playerTo.sendMessage(ColorUtils.colorMessage("&cYou have no requests!"));
            } else {
                Player playerFromGotten = plugin.getRequestManager().getMostRecentRequest(playerTo);
                playerTo.sendMessage(ColorUtils.colorMessage("&eRequest denied from &d" + playerFromGotten.getName()));
                playerFromGotten.sendMessage(ColorUtils.colorMessage("&eRequest to &d" + playerTo.getName() + " &edenied!"));
                plugin.getRequestManager().removeRequest(playerTo, playerFromGotten);
            }
        } else {
            if (plugin.getRequestManager().hasRequest(playerTo, playerFrom)) {
                playerTo.sendMessage(ColorUtils.colorMessage("&eRequest denied from &d" + playerFrom.getName()));
                playerFrom.sendMessage(ColorUtils.colorMessage("&eRequest to &d" + playerTo.getName() + " &edenied!"));
                plugin.getRequestManager().removeRequest(playerTo, playerFrom);
            } else {
                playerTo.sendMessage(ColorUtils.colorMessage("&cYou have no request from that player!"));
            }
        }
    }
}
