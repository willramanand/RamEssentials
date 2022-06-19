package com.gmail.willramanand.RamEssentials.commands.bank;

import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.commands.CommandContext;
import com.gmail.willramanand.RamEssentials.commands.EssCommand;
import com.gmail.willramanand.RamEssentials.utils.Txt;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CmdBankHelp extends EssCommand {

    private final CmdBankRoot root;

    public CmdBankHelp(RamEssentials plugin, CmdBankRoot root) {
        super(plugin, true, false, "ramessentials.eco", 0, 0);
        this.root = root;
        this.aliases.addAll(Arrays.asList("help", "h"));
        this.helpText = "This is the help command for managing the bank system";
    }

    @Override
    public void perform(CommandContext context) {
        context.msg(Txt.header("BANK HELP"));
        for (EssCommand bankSubCommand : root.subCommands) {
            context.msg("{h}/bank " + bankSubCommand.aliases.get(0) + bankSubCommand.usage + " {s}" + bankSubCommand.getHelpText());
        }
    }

    @Override
    public List<String> tabCompletes(CommandSender sender, String[] args) {
        return new ArrayList<>();
    }
}
