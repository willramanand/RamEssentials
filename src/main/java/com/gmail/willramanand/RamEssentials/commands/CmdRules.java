package com.gmail.willramanand.RamEssentials.commands;

import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.utils.Txt;
import com.gmail.willramanand.RamEssentials.utils.TxtReader;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class CmdRules extends EssCommand {

    public CmdRules(RamEssentials plugin) {
        super(plugin, true, true, null, 0, 0);
    }

    @Override
    public void perform(CommandContext context) {
        TxtReader.sendRules(context.player);
    }

    @Override
    public List<String> tabCompletes(CommandSender sender, String[] args) {
        return new ArrayList<>();
    }
}
