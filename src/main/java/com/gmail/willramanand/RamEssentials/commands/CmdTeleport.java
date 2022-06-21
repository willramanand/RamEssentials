package com.gmail.willramanand.RamEssentials.commands;

import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.utils.TeleportUtils;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CmdTeleport extends EssCommand {

    public CmdTeleport(RamEssentials plugin) {
        super(plugin, true, true, 1, 3);
    }

    @Override
    public void perform(CommandContext context) {
        if (context.argIsSet(2)) {
            String x = context.argAsString(0);
            String y = context.argAsString(1);
            String z = context.argAsString(2);

            double xCoord;
            double yCoord;
            double zCoord;

            try {
                if (x.equalsIgnoreCase("~")) {
                    xCoord = context.player.getLocation().getBlockX();
                } else {
                    xCoord = Double.parseDouble(x);
                }

                if (y.equalsIgnoreCase("~")) {
                    yCoord = context.player.getLocation().getBlockY();
                } else {
                    yCoord = Double.parseDouble(y);
                }

                if (z.equalsIgnoreCase("~")) {
                    zCoord = context.player.getLocation().getBlockZ();
                } else {
                    zCoord = Double.parseDouble(z);
                }

            } catch (NumberFormatException e) {
                context.msg("{w}Not valid coordinates!");
                return;
            }
            Location tpLocation = new Location(context.player.getWorld(), xCoord, yCoord, zCoord);
            TeleportUtils.teleport(context.player, tpLocation);
            context.msg("{s}Teleporting to {h}" + xCoord + " " + yCoord + " " + zCoord + "{s}.");
        } else {
            if (context.argIsSet(1)) {
                Player player1 = context.argAsPlayer(0);
                Player player2 = context.argAsPlayer(1);

                if (player1 == null || player2 == null) {
                    context.msg("{w}One of these player does not exist or is not online!");
                    return;
                }

                TeleportUtils.teleport(player1, player2.getLocation());
                context.msg("{s}Teleporting {h}" + player1.getName() + " {s}to {h}" + player2.getName() + "{s}.");
            } else {
                Player player = context.argAsPlayer(0);
                if (player == null) {
                    context.msg("{w}This player does not exist or is not online!");
                    return;
                }

                TeleportUtils.teleport(context.player, player.getLocation());
                context.msg("{s}Teleporting to {h}" + player.getName() + "{s}.");
            }
        }
    }

    @Override
    public List<String> tabCompletes(CommandSender sender, String[] args) {
        if (args.length == 1 || args.length == 2) {
            List<String> completes = tabCompletePlayers();
            completes.add("~");
            return completes;
        }
        if (args.length == 3) {
            return new ArrayList<>(Collections.singletonList("~"));
        }
        return new ArrayList<>();
    }
}
