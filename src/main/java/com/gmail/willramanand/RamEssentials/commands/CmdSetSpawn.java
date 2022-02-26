package com.gmail.willramanand.RamEssentials.commands;

import com.gmail.willramanand.RamEssentials.RamEssentials;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class CmdSetSpawn extends EssCommand {

    public CmdSetSpawn(RamEssentials plugin) {
        super(plugin, true, true, 0, 0);
    }

    @Override
    public void perform(CommandContext context) {
        plugin.getServerSpawn().setLocation(context.player.getLocation());
        context.msg("{s}Set server spawn to {h}"
                + context.player.getLocation().getBlockX() + "{s}, {h}"
                + context.player.getLocation().getBlockY() + "{s}, {h}"
                + context.player.getLocation().getBlockZ() + " {s}in {h}"
                + context.player.getWorld().getName());
    }

    @Override
    public List<String> tabCompletes(CommandSender sender, String[] args) {
        return new ArrayList<>();
    }
}
