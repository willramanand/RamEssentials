package com.gmail.willramanand.RamEssentials.commands.account;

import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.commands.CommandContext;
import com.gmail.willramanand.RamEssentials.commands.EssCommand;
import com.gmail.willramanand.RamEssentials.commands.bank.CmdBankCreate;
import com.gmail.willramanand.RamEssentials.commands.bank.CmdBankDelete;
import com.gmail.willramanand.RamEssentials.commands.bank.CmdBankView;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CmdAccountRoot extends EssCommand {

    CmdAccountHelp cmdAccountHelp;
    CmdAccountCreate cmdAccountCreate;
    CmdAccountDelete cmdAccountDelete;
    CmdAccountDeposit cmdAccountDeposit;
    CmdAccountWithdraw cmdAccountWithdraw;
    public CmdAccountRoot(RamEssentials plugin) {
        super(plugin, true, false, 0, -1);

        cmdAccountHelp = new CmdAccountHelp(plugin, this);
        cmdAccountCreate = new CmdAccountCreate(plugin);
        cmdAccountDelete = new CmdAccountDelete(plugin);
        cmdAccountDeposit = new CmdAccountDeposit(plugin);
        cmdAccountWithdraw = new CmdAccountWithdraw(plugin);

        this.subCommands.add(cmdAccountHelp);
        this.subCommands.add(cmdAccountCreate);
        this.subCommands.add(cmdAccountDelete);
        this.subCommands.add(cmdAccountDeposit);
        this.subCommands.add(cmdAccountWithdraw);
    }

    @Override
    public void perform(CommandContext context) {
        context.commandChain.add(this);
        this.cmdAccountHelp.execute(context);
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
