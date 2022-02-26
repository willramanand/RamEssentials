package com.gmail.willramanand.RamEssentials.commands;

import com.gmail.willramanand.RamEssentials.RamEssentials;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class CmdSetWarp extends EssCommand {
    public CmdSetWarp(RamEssentials plugin) {
        super(plugin, true, true, 1, 1);
    }

    @Override
    public void perform(CommandContext context) {
        String warpName = context.argAsString(0);

        if (plugin.getWarps().getWarpList().contains(warpName)) {
            context.msg("{h}" + warpName + " {s}already exists!");
            return;
        }

        plugin.getWarps().addWarp(warpName, context.player.getLocation());
        context.msg("{s}Set warp {h}" + warpName + " {s}to this location.");
    }

    @Override
    public List<String> tabCompletes(CommandSender sender, String[] args) {
        return new ArrayList<>();
    }
}
