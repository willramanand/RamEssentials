package com.gmail.willramanand.RamEssentials.commands;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.utils.Txt;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.*;

@CommandAlias("help|?")
public class HelpCommand extends RBaseCommand {

    private final RamEssentials plugin;

    private final List<Command> sortedCommands;
    private final int commandsPerPage;

    public HelpCommand(RamEssentials plugin, int commandsPerPage) {
        this.plugin = plugin;
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

    @Default
    @Description("Show help for all commands on server.")
    public void onHelpPage(CommandSender sender, @Default("1") int page) {
        final Map<Integer, List<Command>> commandPages = new HashMap<>();

        int pageCount = 1;
        for (Command cmd : sortedCommands) {
            if (commandPages.get(pageCount) == null) {
                List<Command> cmdList = new ArrayList<>();
                commandPages.put(pageCount, cmdList);
            }
            if (cmd.testPermissionSilent(sender)) {
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
            msg(sender, "{w}Page is out of bounds! Please select between 1 and " + totalPages);
            return;
        }

        msg(sender, Txt.header("HELP {yellow}" + page + " {gray}/ " + "{gold}" + totalPages));
        for (Command cmd : commandPages.get(page)) {
            msg(sender, "{gold}/" + cmd.getName() + " {white}" + cmd.getDescription());
        }
    }
}
