package com.gmail.willramanand.RamEssentials.commands;

import com.gmail.willramanand.RamEssentials.RamEssentials;
import org.bukkit.command.CommandSender;

import java.util.List;

public class CmdDND extends EssCommand {

    public CmdDND(RamEssentials plugin) {
        super(plugin, true, true, null, 0, 0);
    }

    @Override
    public void perform(CommandContext context) {
        if (context.ePlayer.isDoNotDisturb()) {
            context.ePlayer.setDoNotDisturb(false);
            context.msg("{s}Do not disturb {w}disabled{s}.");
        } else {
            context.ePlayer.setDoNotDisturb(true);
            context.msg("{s}Do not disturb {green}enabled{s}.");
        }
    }

    @Override
    public List<String> tabCompletes(CommandSender sender, String[] args) {
        return null;
    }
}
