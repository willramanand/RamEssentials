package com.gmail.willramanand.RamEssentials.commands;

import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.utils.Formatter;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class CmdBalance extends EssCommand {

    public CmdBalance(RamEssentials plugin) {
        super(plugin, true, true, null, 0, 1);
    }

    @Override
    public void perform(CommandContext context) {
        context.msg("{green}Balance: {gold}" + Formatter.formatMoney(plugin.getAccountManager().getBalance(context.argAsOffPlayer(0, context.player))));
    }

    @Override
    public List<String> tabCompletes(CommandSender sender, String[] args) {
        if (args.length == 1) {
            return tabCompletePlayers();
        }
        return new ArrayList<>();
    }
}
