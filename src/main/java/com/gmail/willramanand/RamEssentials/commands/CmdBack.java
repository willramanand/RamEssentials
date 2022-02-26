package com.gmail.willramanand.RamEssentials.commands;

import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.utils.TeleportUtils;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class CmdBack extends EssCommand {

    public CmdBack(RamEssentials plugin) {
        super(plugin, true, true, null, 0, 0);
    }

    @Override
    public void perform(CommandContext context) {
        if (context.ePlayer.getLastLocation() == null) {
            context.msg("{s}You do not have a saved previous location.");
            return;
        }
        context.msg("{s}Returning to saved previous location.");
        TeleportUtils.teleport(context.player, context.ePlayer.getLastLocation());
    }

    @Override
    public List<String> tabCompletes(CommandSender sender, String[] args) {
        return new ArrayList<>();
    }
}
