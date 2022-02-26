package com.gmail.willramanand.RamEssentials.commands;

import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.utils.TeleportUtils;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class CmdSpawn extends EssCommand {

    public CmdSpawn(RamEssentials plugin) {
        super(plugin, true, true, null, 0, 1);
    }

    @Override
    public void perform(CommandContext context) {
        if (context.argIsSet(0)) {
            TeleportUtils.teleport(context.player, context.player.getWorld().getSpawnLocation());
            context.msg("{s}Teleporting to world spawn.");
        } else {
            TeleportUtils.teleport(context.player, plugin.getServerSpawn().getLocation());
            context.msg("{s}Teleporting to server spawn.");
        }
    }

    @Override
    public List<String> tabCompletes(CommandSender sender, String[] args) {
        if (args.length == 1) return tabCompleteWorlds();
        return new ArrayList<>();
    }
}
