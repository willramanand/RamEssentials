package com.gmail.willramanand.RamEssentials.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.utils.ColorUtils;
import com.gmail.willramanand.RamEssentials.utils.TeleportUtils;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeleportCommand extends BaseCommand {

    private final RamEssentials plugin;

    public TeleportCommand(RamEssentials plugin) {
        this.plugin = plugin;
    }

    @CommandAlias("teleport|tp")
    @Description("Teleport to specific coordinates.")
    @CommandPermission("ramessentials.tp")
    public void tpCoords(CommandSender sender, String x, String y, String z) {
        Player player = (Player) sender;

        double xCoord = 0;
        double yCoord = 0;
        double zCoord = 0;

        try {
            if (x.equalsIgnoreCase("~")) {
                xCoord = player.getLocation().getBlockX();
            } else {
                xCoord = Double.parseDouble(x);
            }

            if (y.equalsIgnoreCase("~")) {
                yCoord = player.getLocation().getBlockY();
            } else {
                yCoord = Double.parseDouble(y);
            }

            if (z.equalsIgnoreCase("~")) {
                zCoord = player.getLocation().getBlockZ();
            } else {
                zCoord = Double.parseDouble(z);
            }

        } catch (NumberFormatException e) {
            sender.sendMessage(ColorUtils.colorMessage("&cNot valid coordinates!"));
            return;
        }


        Location tpLocation = new Location(player.getWorld(), xCoord, yCoord, zCoord);
        TeleportUtils.teleport(player, tpLocation);
        sender.sendMessage(ColorUtils.colorMessage("&eTeleporting to &d" + xCoord + " " + yCoord + " " + zCoord + "&e."));
    }

    @CommandAlias("teleportp|tpp")
    @Description("Teleport to player.")
    @CommandCompletion("@players")
    @CommandPermission("ramessentials.tp")
    public void tpToPlayer(CommandSender sender, @Flags("other") Player player) {
        Player playerSender = (Player) sender;

        TeleportUtils.teleport(playerSender, player.getLocation());
        sender.sendMessage(ColorUtils.colorMessage("&eTeleporting to &d" + player.getName() + "&e."));
    }

    @CommandAlias("teleportp2p|tpp2p")
    @Description("Teleport player to player.")
    @CommandCompletion("@players")
    @CommandPermission("ramessentials.tp")
    public void tpPlayerToPlayer(CommandSender sender, @Flags("other") Player player1, @Flags("other") Player player2) {
        TeleportUtils.teleport(player1, player2.getLocation());
        sender.sendMessage(ColorUtils.colorMessage("&eTeleporting &d" + player1.getName() + " &eto &d" + player2.getName() + "&e."));
    }
}
