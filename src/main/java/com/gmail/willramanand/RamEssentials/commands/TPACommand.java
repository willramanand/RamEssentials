package com.gmail.willramanand.RamEssentials.commands;

import co.aikar.commands.annotation.*;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.player.EPlayer;
import com.gmail.willramanand.RamEssentials.utils.TeleportUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("tpa")
public class TPACommand extends RBaseCommand {

    private final RamEssentials plugin;

    public TPACommand(RamEssentials plugin) {
        this.plugin = plugin;
    }

    @Default
    @Description("Make a teleport requests to specified player.")
    @CommandCompletion("@players")
    public void makeRequest(CommandSender sender, @Flags("other") Player playerTo) {
        if (!(sender instanceof Player)) {
            msg(sender, "{w}You must be player to use this command!");
            return;
        }

        Player player = (Player) sender;

        if (plugin.getRequestManager().hasRequest(playerTo, player)) {
            msg(player, "{w}You already have an active request with this player!");
            return;
        }

        EPlayer ePlayer = plugin.getPlayerManager().getPlayerData(playerTo);

        if (ePlayer.getIgnoredPlayers().contains(player.getUniqueId())) {
            msg(player, "{w}You cannot send a request to a player ignoring you!");
            return;
        }

        plugin.getRequestManager().addRequest(playerTo, player);
    }

    @Subcommand("accept")
    @CommandAlias("tpaaccept")
    @Description("Accept a teleport request")
    @CommandCompletion("@requests")
    public void acceptRequest(CommandSender sender, @Optional @Flags("other") Player playerFrom) {
        if (!(sender instanceof Player)) {
            msg(sender, "{w}You must be player to use this command!");
            return;
        }

        Player player = (Player) sender;
        if (playerFrom == null) {
            if (plugin.getRequestManager().getMostRecentRequest(player) == null) {
                msg(player, "{w}You have no requests!");
            } else {
                Player playerFromGotten = plugin.getRequestManager().getMostRecentRequest(player);
                msg(player, "{s}Request accepted from {h}" + playerFromGotten.getName());
                msg(playerFromGotten, "{s}Request to {h}" + player.getName() + " {s}accepted!");
                TeleportUtils.teleport(playerFromGotten, player.getLocation());
                plugin.getRequestManager().removeRequest(player, playerFromGotten);
            }
        } else {
            if (plugin.getRequestManager().hasRequest(player, playerFrom)) {
                msg(player, "{s}Request accepted from {h}" + playerFrom.getName());
                msg(playerFrom, "{s}Request to {h}" + player.getName() + " {s}accepted!");
                TeleportUtils.teleport(playerFrom, player.getLocation());
                plugin.getRequestManager().removeRequest(player, playerFrom);
            } else {
                msg(player, "{w}You have no request from that player!");
            }
        }
    }

    @Subcommand("deny")
    @CommandAlias("tpadeny")
    @Description("Deny a teleport request")
    @CommandCompletion("@requests")
    public void denyRequest(CommandSender sender, @Optional @Flags("other") Player playerFrom) {
        if (!(sender instanceof Player)) {
            msg(sender, "{w}You must be player to use this command!");
            return;
        }

        Player player = (Player) sender;
        if (playerFrom == null) {
            if (plugin.getRequestManager().getMostRecentRequest(player) == null) {
                msg(player, "{w}You have no requests!");
            } else {
                Player playerFromGotten = plugin.getRequestManager().getMostRecentRequest(player);
                msg(player, "{s}Request denied from {h}" + playerFromGotten.getName());
                msg(playerFromGotten, "{s}Request to {h}" + player.getName() + " {s}denied!");
                plugin.getRequestManager().removeRequest(player, playerFromGotten);
            }
        } else {
            if (plugin.getRequestManager().hasRequest(player, playerFrom)) {
                msg(player, "{s}Request denied from {h}" + playerFrom.getName());
                msg(playerFrom, "{s}Request to {h}" + player.getName() + " {s}denied!");
                plugin.getRequestManager().removeRequest(player, playerFrom);
            } else {
                msg(player, "{w}You have no request from that player!");
            }
        }
    }
}
