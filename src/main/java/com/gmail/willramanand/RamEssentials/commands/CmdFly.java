package com.gmail.willramanand.RamEssentials.commands;

import com.gmail.willramanand.RamEssentials.RamEssentials;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class CmdFly extends EssCommand {

    public CmdFly(RamEssentials plugin) {
        super(plugin, true, true,0, 0);
    }

    @Override
    public void perform(CommandContext context) {
        if (context.player.isFlying()) {
            context.player.setAllowFlight(false);
            context.player.setFlying(false);
            context.msg("{s}Fly mode {w}disabled{s}.");
        } else {
            context.player.setAllowFlight(true);
            context.player.setFlying(true);
            context.msg("{s}Fly mode {green}enabled{s}.");
        }
    }

    @Override
    public List<String> tabCompletes(CommandSender sender, String[] args) {
        return new ArrayList<>();
    }
}
