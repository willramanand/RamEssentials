package com.gmail.willramanand.RamEssentials.commands.eco;

import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.commands.CommandContext;
import com.gmail.willramanand.RamEssentials.commands.EssCommand;
import com.gmail.willramanand.RamEssentials.commands.bank.CmdBankRoot;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CmdEcoRoot extends EssCommand {

    public CmdEcoAdd cmdEcoAdd;
    public CmdEcoSet cmdEcoSet;
    public CmdEcoSubtract cmdEcoSubtract;
    public CmdEcoHelp cmdEcoHelp;
    public CmdBankRoot cmdBankRoot;

    public CmdEcoRoot(RamEssentials plugin) {
        super(plugin, true, false, 0, -1);

        cmdEcoAdd = new CmdEcoAdd(plugin);
        cmdEcoSet = new CmdEcoSet(plugin);
        cmdEcoSubtract = new CmdEcoSubtract(plugin);
        cmdEcoHelp = new CmdEcoHelp(plugin, this);

        this.subCommands.add(cmdEcoAdd);
        this.subCommands.add(cmdEcoSet);
        this.subCommands.add(cmdEcoSubtract);
        this.subCommands.add(cmdEcoHelp);
    }

    @Override
    public void perform(CommandContext context) {
        context.commandChain.add(this);
        this.cmdEcoHelp.execute(context);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        this.execute(new CommandContext(sender, command, new ArrayList<>(Arrays.asList(args)), label));
        return true;
    }

    @Override
    public List<String> tabCompletes(CommandSender sender, String[] args) {
        if (args.length == 1) {
            List<String> allowAliases = new ArrayList<>();
            for (EssCommand subCommand : subCommands) {
                if (subCommand.getPermission() == null) {
                    allowAliases.addAll(subCommand.aliases);
                } else if (sender.hasPermission(subCommand.getPermission())) {
                    allowAliases.addAll(subCommand.aliases);
                }
            }
            return allowAliases;
        }

        if (args.length >= 2) {
            for (EssCommand subCommand : subCommands) {
                if (subCommand.aliases.contains(args[0])) {
                    String[] modifiedArgs = Arrays.copyOfRange(args, 1, args.length);
                    return subCommand.tabCompletes(sender, modifiedArgs);
                }
            }
        }
        return new ArrayList<>();
    }
}
