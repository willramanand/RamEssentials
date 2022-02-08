package com.gmail.willramanand.RamEssentials.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.utils.ColorUtils;
import com.gmail.willramanand.RamEssentials.utils.TeleportUtils;
import org.bukkit.command.CommandSender;
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
    public void makeRequest(CommandSender sender, @Flags("other") Player playerTo) {
        Player player = (Player) sender;
        if (plugin.getRequestManager().hasRequest(playerTo, player)) {
            player.sendMessage(ColorUtils.colorMessage("&cYou already have an active request with this player!"));
            return;
        }

        plugin.getRequestManager().addRequest(playerTo, player);
    }

    @Subcommand("accept")
    @CommandAlias("tpaaccept")
    @Description("Accept a teleport request")
    @CommandCompletion("@requests")
    public void acceptRequest(CommandSender sender, @Optional @Flags("other") Player playerFrom) {
        Player player = (Player) sender;
        if (playerFrom == null) {
            if (plugin.getRequestManager().getMostRecentRequest(player) == null) {
                player.sendMessage(ColorUtils.colorMessage("&cYou have no requests!"));
            } else {
                Player playerFromGotten = plugin.getRequestManager().getMostRecentRequest(player);
                player.sendMessage(ColorUtils.colorMessage("&eRequest accepted from &d" + playerFromGotten.getName()));
                playerFromGotten.sendMessage(ColorUtils.colorMessage("&eRequest to &d" + player.getName() + " &eaccepted!"));
                TeleportUtils.teleport(playerFromGotten, player.getLocation());
                plugin.getRequestManager().removeRequest(player, playerFromGotten);
            }
        } else {
            if (plugin.getRequestManager().hasRequest(player, playerFrom)) {
                player.sendMessage(ColorUtils.colorMessage("&eRequest accepted from &d" + playerFrom.getName()));
                playerFrom.sendMessage(ColorUtils.colorMessage("&eRequest to &d" + player.getName() + " &eaccepted!"));
                TeleportUtils.teleport(playerFrom, player.getLocation());
                plugin.getRequestManager().removeRequest(player, playerFrom);
            } else {
                player.sendMessage(ColorUtils.colorMessage("&cYou have no request from that player!"));
            }
        }
    }

    @Subcommand("deny")
    @CommandAlias("tpadeny")
    @Description("Deny a teleport request")
    @CommandCompletion("@requests")
    public void denyRequest(CommandSender sender, @Optional @Flags("other") Player playerFrom) {
        Player player = (Player) sender;
        if (playerFrom == null) {
            if (plugin.getRequestManager().getMostRecentRequest(player) == null) {
                player.sendMessage(ColorUtils.colorMessage("&cYou have no requests!"));
            } else {
                Player playerFromGotten = plugin.getRequestManager().getMostRecentRequest(player);
                player.sendMessage(ColorUtils.colorMessage("&eRequest denied from &d" + playerFromGotten.getName()));
                playerFromGotten.sendMessage(ColorUtils.colorMessage("&eRequest to &d" + player.getName() + " &edenied!"));
                plugin.getRequestManager().removeRequest(player, playerFromGotten);
            }
        } else {
            if (plugin.getRequestManager().hasRequest(player, playerFrom)) {
                player.sendMessage(ColorUtils.colorMessage("&eRequest denied from &d" + playerFrom.getName()));
                playerFrom.sendMessage(ColorUtils.colorMessage("&eRequest to &d" + player.getName() + " &edenied!"));
                plugin.getRequestManager().removeRequest(player, playerFrom);
            } else {
                player.sendMessage(ColorUtils.colorMessage("&cYou have no request from that player!"));
            }
        }
    }
}
