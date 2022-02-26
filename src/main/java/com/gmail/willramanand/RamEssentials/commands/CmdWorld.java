package com.gmail.willramanand.RamEssentials.commands;

import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.utils.TeleportUtils;
import org.bukkit.World;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class CmdWorld extends EssCommand {

    public CmdWorld(RamEssentials plugin) {
        super(plugin, true, true, "ramessentials.world", 1, 1);
    }

    @Override
    public void perform(CommandContext context) {
        World world = context.argAsWorld(0);

        if (world == null) {
            context.msg("{w}This world does not exist!");
            return;
        }

        context.msg("{s}Teleporting to {h}" + world.getName());
        TeleportUtils.teleport(context.player, world.getSpawnLocation());
    }

    @Override
    public List<String> tabCompletes(CommandSender sender, String[] args) {
        if (args.length == 1) return tabCompleteWorlds();
        return new ArrayList<>();
    }
}
