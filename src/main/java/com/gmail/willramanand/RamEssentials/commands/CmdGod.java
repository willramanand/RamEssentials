package com.gmail.willramanand.RamEssentials.commands;

import com.gmail.willramanand.RamEssentials.RamEssentials;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class CmdGod extends EssCommand {

    public CmdGod(RamEssentials plugin) {
        super(plugin, true, true, 0, 0);
    }

    @Override
    public void perform(CommandContext context) {
        if (context.ePlayer.isGodMode()) {
            context.ePlayer.setGodMode(false);
            context.msg("{s}God mode {w}disabled{s}.");
        } else {
            context.ePlayer.setGodMode(true);
            context.msg("{s}God mode {green}enabled{s}.");
        }
    }

    @Override
    public List<String> tabCompletes(CommandSender sender, String[] args) {
        return new ArrayList<>();
    }
}
