package com.gmail.willramanand.RamEssentials.commands.eco;

import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.commands.CommandContext;
import com.gmail.willramanand.RamEssentials.commands.EssCommand;
import com.gmail.willramanand.RamEssentials.utils.Txt;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CmdEcoHelp extends EssCommand {
    private final CmdEcoRoot root;
    public CmdEcoHelp(RamEssentials plugin, CmdEcoRoot root) {
        super(plugin, true, false, "ramessentials.eco", 0, 0);
        this.root = root;
        this.aliases.addAll(Arrays.asList("help", "h"));
        this.helpText = "This is the help command for managing the economy";
    }

    @Override
    public void perform(CommandContext context) {
        context.msg(Txt.header("ECONOMY"));
        for (EssCommand ecoSubCommand : root.subCommands) {
            if (ecoSubCommand instanceof CmdEcoHelp) continue;
            context.msg("{aqua}/eco " + ecoSubCommand.aliases.get(0) + " <player> <amount>{white} " + ecoSubCommand.getHelpText());
        }
    }

    @Override
    public List<String> tabCompletes(CommandSender sender, String[] args) {
        return new ArrayList<>();
    }
}
