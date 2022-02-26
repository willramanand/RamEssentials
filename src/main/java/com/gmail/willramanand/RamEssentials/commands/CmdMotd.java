package com.gmail.willramanand.RamEssentials.commands;

import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.utils.Txt;
import com.gmail.willramanand.RamEssentials.utils.TxtReader;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class CmdMotd extends EssCommand {

    public CmdMotd(RamEssentials plugin) {
        super(plugin, true, true, null, 0, 0);
    }

    @Override
    public void perform(CommandContext context) {
        context.msg(Txt.header("MOTD"));
        TxtReader.sendMotd(context.player);
    }

    @Override
    public List<String> tabCompletes(CommandSender sender, String[] args) {
        return new ArrayList<>();
    }
}
