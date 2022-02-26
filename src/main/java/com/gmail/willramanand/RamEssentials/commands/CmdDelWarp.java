package com.gmail.willramanand.RamEssentials.commands;

import com.gmail.willramanand.RamEssentials.RamEssentials;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class CmdDelWarp extends EssCommand {
    public CmdDelWarp(RamEssentials plugin) {
        super(plugin, true, true,1, 1);
    }

    @Override
    public void perform(CommandContext context) {
        String warpName = context.argAsString(0);

        if (plugin.getWarps().getWarpList().contains(warpName)) {
            context.msg("{s}Deleted warp {h}" + warpName);
            plugin.getWarps().delWarp(warpName);
            return;
        }
        context.msg("{w}That warp does not exist!");
    }

    @Override
    public List<String> tabCompletes(CommandSender sender, String[] args) {
        if (args.length == 1) return new ArrayList<>(plugin.getWarps().getWarpList());
        return null;
    }
}
