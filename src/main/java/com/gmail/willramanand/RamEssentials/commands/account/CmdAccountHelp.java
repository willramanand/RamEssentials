package com.gmail.willramanand.RamEssentials.commands.account;

import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.commands.CommandContext;
import com.gmail.willramanand.RamEssentials.commands.EssCommand;
import com.gmail.willramanand.RamEssentials.utils.Txt;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CmdAccountHelp extends EssCommand {

    private final CmdAccountRoot root;

    public CmdAccountHelp(RamEssentials plugin, CmdAccountRoot root) {
        super(plugin, true, false, 0, 0);
        this.root = root;
        this.aliases.add("help");
        this.helpText = "This is the help command for managing your account.";
    }

    @Override
    public void perform(CommandContext context) {
        context.msg(Txt.header("ACCOUNT HELP"));
        for (EssCommand accSubCommand : root.subCommands) {
            context.msg("{h}/account " + accSubCommand.aliases.get(0) + accSubCommand.usage + " {s}" + accSubCommand.getHelpText());
        }
    }

    @Override
    public List<String> tabCompletes(CommandSender sender, String[] args) {
        return new ArrayList<>();
    }
}
