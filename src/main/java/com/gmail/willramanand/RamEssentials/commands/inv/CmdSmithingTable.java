package com.gmail.willramanand.RamEssentials.commands.inv;

import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.commands.CommandContext;
import com.gmail.willramanand.RamEssentials.commands.EssCommand;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class CmdSmithingTable extends EssCommand {

    public CmdSmithingTable(RamEssentials plugin) {
        super(plugin, true, true, 0, 0);
    }

    @Override
    public void perform(CommandContext context) {
        context.player.openSmithingTable(context.player.getLocation(), true);
    }

    @Override
    public List<String> tabCompletes(CommandSender sender, String[] args) {
        return new ArrayList<>();
    }
}
