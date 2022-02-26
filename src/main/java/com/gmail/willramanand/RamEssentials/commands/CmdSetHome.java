package com.gmail.willramanand.RamEssentials.commands;

import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.player.EPlayer;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class CmdSetHome extends EssCommand {

    public CmdSetHome(RamEssentials plugin) {
        super(plugin, true, true, null, 1, 1);
    }

    @Override
    public void perform(CommandContext context) {
        EPlayer ePlayer = context.ePlayer;
        String name = context.argAsString(0);

        if (name == null) {
            context.msg("{w}No name entered!");
            return;
        }

        if (ePlayer.getHomeList().contains(name)) {
            ePlayer.delHome(name);
        }

        if (ePlayer.getHomeList().size() == plugin.getHouseLimit()) {
            context.msg("{s}You already have the max amount of homes!");
            return;
        }

        ePlayer.addHome(name, context.player.getLocation());
        context.msg("{s}You have set a home named {h}" + name + " {s}at this location.");
    }

    @Override
    public List<String> tabCompletes(CommandSender sender, String[] args) {
        return new ArrayList<>();
    }
}
