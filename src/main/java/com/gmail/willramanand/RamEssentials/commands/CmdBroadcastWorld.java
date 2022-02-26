package com.gmail.willramanand.RamEssentials.commands;

import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.utils.Txt;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CmdBroadcastWorld extends EssCommand {
    public CmdBroadcastWorld(RamEssentials plugin) {
        super(plugin, true, false,2, -1);
    }

    @Override
    public void perform(CommandContext context) {
        World world = context.argAsWorld(0);
        String message = context.argAsConcatString(1, "");

        if (world == null) {
            context.msg("{w}This world does not exist!");
            return;
        }

        if (message.equalsIgnoreCase("")) {
            context.msg("{w}You cannot broadcast an empty message.");
            return;
        }

        for (Player player : world.getPlayers()) {
            context.msgOther(player, message);
        }

        plugin.getLogger().info(Txt.parse("{gold}Broadcasted to {h}" + world.getName() + "{gold}: {white}" + message));
    }

    @Override
    public List<String> tabCompletes(CommandSender sender, String[] args) {
        if (args.length == 1) return tabCompleteWorlds();
        return new ArrayList<>();
    }
}
