package com.gmail.willramanand.RamEssentials.commands;

import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.utils.TeleportUtils;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CmdTeleportPos extends EssCommand {

    public CmdTeleportPos(RamEssentials plugin) {
        super(plugin, true, true, 3, 3);
    }

    @Override
    public void perform(CommandContext context) {
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
    }

    @Override
    public List<String> tabCompletes(CommandSender sender, String[] args) {
        if (args.length == 1 || args.length == 2 || args.length == 3) return new ArrayList<>(Collections.singletonList("~"));
        return new ArrayList<>();
    }
}
