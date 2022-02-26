package com.gmail.willramanand.RamEssentials.commands.eco;

import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.commands.CommandContext;
import com.gmail.willramanand.RamEssentials.commands.EssCommand;
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

    private final List<String> subCommandAliases;

    public CmdEcoRoot(RamEssentials plugin) {
        super(plugin, true, false, 0, -1);
        this.subCommandAliases = new ArrayList<>();

        cmdEcoAdd = new CmdEcoAdd(plugin);
        cmdEcoSet = new CmdEcoSet(plugin);
        cmdEcoSubtract = new CmdEcoSubtract(plugin);
        cmdEcoHelp = new CmdEcoHelp(plugin, this);

        this.subCommands.add(cmdEcoAdd);
        this.subCommands.add(cmdEcoSet);
        this.subCommands.add(cmdEcoSubtract);
        this.subCommands.add(cmdEcoHelp);

        for (EssCommand subCommand : subCommands) {
            subCommandAliases.addAll(subCommand.aliases);
        }
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
        if (args.length == 1) return subCommandAliases;
        if (args.length == 2) return tabCompletePlayers();
        return new ArrayList<>();
    }
}
