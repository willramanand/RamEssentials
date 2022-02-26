package com.gmail.willramanand.RamEssentials.commands;

import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.utils.TeleportUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CmdTeleport extends EssCommand {

    public CmdTeleport(RamEssentials plugin) {
        super(plugin, true, true, 1, 2);
    }

    @Override
    public void perform(CommandContext context) {
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

    @Override
    public List<String> tabCompletes(CommandSender sender, String[] args) {
        if (args.length == 1 || args.length == 2) return tabCompletePlayers();
        return new ArrayList<>();
    }
}
