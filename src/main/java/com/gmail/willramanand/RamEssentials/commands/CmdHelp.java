package com.gmail.willramanand.RamEssentials.commands;

import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.utils.Txt;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.*;

public class CmdHelp extends EssCommand {

    private final List<Command> sortedCommands;
    private final int commandsPerPage;

    public CmdHelp(RamEssentials plugin, int commandsPerPage) {
        super(plugin, true, false, 0, 1);
        this.commandsPerPage = commandsPerPage;

        Set<String> commandNames = new HashSet<>(plugin.getServer().getCommandMap().getKnownCommands().keySet());
        List<String> sortedCommandNames = new ArrayList<>(commandNames);
        Collections.sort(sortedCommandNames);

        this.sortedCommands = new ArrayList<>();
        for (String s : sortedCommandNames) {
            if (sortedCommands.contains(plugin.getServer().getCommandMap().getCommand(s))) continue;
            sortedCommands.add(plugin.getServer().getCommandMap().getCommand(s));
        }
    }

    @Override
    public void perform(CommandContext context) {
        int page = context.argAsInt(0, 1);
        final Map<Integer, List<Command>> commandPages = new HashMap<>();

        int pageCount = 1;
        for (Command cmd : sortedCommands) {
            if (commandPages.get(pageCount) == null) {
                List<Command> cmdList = new ArrayList<>();
                commandPages.put(pageCount, cmdList);
            }

            if (cmd.testPermissionSilent(context.sender)) {
                commandPages.get(pageCount).add(cmd);
            }

            if (commandPages.get(pageCount).size() >= commandsPerPage) pageCount++;
        }

        int totalPages = pageCount;

        if (commandPages.get(totalPages).isEmpty()) {
            commandPages.remove(totalPages);
            totalPages--;
        }

        if (commandPages.get(page) == null) {
            context.msg("{w}Page is out of bounds! Please select between 1 and " + totalPages);
            return;
        }

        context.msg(Txt.header("HELP {yellow}" + page + " {gray}/ " + "{gold}" + totalPages));
        for (Command cmd : commandPages.get(page)) {
            context.msg("{gold}/" + cmd.getName() + " {white}" + cmd.getDescription());
        }
    }

    @Override
    public List<String> tabCompletes(CommandSender sender, String[] args) {
        return new ArrayList<>();
    }
}
