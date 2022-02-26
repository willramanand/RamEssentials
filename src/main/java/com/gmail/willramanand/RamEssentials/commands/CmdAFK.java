package com.gmail.willramanand.RamEssentials.commands;

import com.gmail.willramanand.RamEssentials.RamEssentials;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class CmdAFK extends EssCommand {

    public CmdAFK(RamEssentials plugin) {
        super(plugin, true, true, null, 0, 0);
    }

    @Override
    public void perform(CommandContext context) {
        if (plugin.getAfkTimer().isAfk(context.player)) {
            plugin.getAfkTimer().resetTimer(context.player);
            plugin.getAfkTimer().setLocationAfk(context.player, context.player.getLocation());
            plugin.getAfkTimer().removeAfk(context.player);
        } else {
            plugin.getAfkTimer().addAfk(context.player);
        }
    }

    @Override
    public List<String> tabCompletes(CommandSender sender, String[] args) {
        return new ArrayList<>();
    }
}
