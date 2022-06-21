package com.gmail.willramanand.RamEssentials.commands;

import com.gmail.willramanand.RamEssentials.RamEssentials;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class EssCommand implements TabExecutor {

    protected final RamEssentials plugin;

    public List<String> aliases;

    private final String permission;

    public int requiredArgsSize;
    public int totalArgs;

    private final boolean enabled;
    private final boolean playerOnly;

    public String helpText;

    public String usage;

    public List<EssCommand> subCommands;


    public EssCommand(RamEssentials plugin, boolean enabled, boolean playerOnly, int requiredArgsSize, int totalArgs) {
        this.plugin = plugin;
        this.enabled = enabled;
        this.playerOnly = playerOnly;
        this.permission = null;
        this.requiredArgsSize = requiredArgsSize;
        this.totalArgs = totalArgs;
        this.aliases = new ArrayList<>();
        this.subCommands = new ArrayList<>();
        this.usage = "";
    }

    public EssCommand(RamEssentials plugin, boolean enabled, boolean playerOnly, String permission, int requiredArgsSize, int totalArgs) {
        this.plugin = plugin;
        this.enabled = enabled;
        this.playerOnly = playerOnly;
        this.permission = permission;
        this.requiredArgsSize = requiredArgsSize;
        this.totalArgs = totalArgs;
        this.aliases = new ArrayList<>();
        this.subCommands = new ArrayList<>();
        this.usage = "";
    }


    public abstract void perform(CommandContext context);

    public abstract List<String> tabCompletes(CommandSender sender, String[] args);

    public void execute(CommandContext context) {
        // Is there a matching sub command?
        if (context.args.size() > 0) {
            for (EssCommand subCommand : this.subCommands) {
                if (subCommand.aliases.contains(context.args.get(0).toLowerCase())) {
                    context.args.remove(0);
                    context.commandChain.add(this);
                    subCommand.execute(context);
                    return;
                }
            }
        }

        if (!validCall(context)) {
            return;
        }

        if (!this.isEnabled()) {
            context.msg("{w}This command is not enabled!");
            return;
        }

        perform(context);
    }

    public boolean validCall(CommandContext context) {
        if (requiredArgsSize != 0 && requiredArgsSize > context.args.size()) {
            context.msg("{w}Usage: " + context.command.getUsage().replace("<command>", context.command.getName()) + (aliases.size() > 0 ? " " + aliases.get(0) : "") + usage);
            return false;
        }

        if (totalArgs != -1 && totalArgs < context.args.size()) {
            context.msg("{w}Too many args! Usage: " + context.command.getUsage().replace("<command>", context.command.getName()) + (aliases.size() > 0 ? " "  + aliases.get(0) : "") + usage);
            return false;
        }

        if (!(context.sender instanceof Player) && playerOnly) {
            context.msg("{w}Only a player can execute this command!");
            return false;
        }

        // Check our perms
        if (permission != null && !(context.sender.hasPermission(permission))) {
            context.msg("{w}You do not have permission for this command!");
            return false;
        }

        // Check spigot perms
        if (context.command.getPermission() != null && !(context.sender.hasPermission(context.command.getPermission()))) {
            context.msg("{w}You do not have permission for this command!");
            return false;
        }

        return true;
    }

    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        this.execute(new CommandContext(sender, command, new ArrayList<>(Arrays.asList(args)), label));
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        return tabCompletes(sender, args);
    }

    public List<String> tabCompletePlayers() {
        List<String> playerNames = new ArrayList<>();
        for (Player player : Bukkit.getOnlinePlayers()) playerNames.add(player.getName());
        return playerNames;
    }

    public List<String> tabCompleteWorlds() {
        List<String> worldNames = new ArrayList<>();
        for (World world : Bukkit.getWorlds()) worldNames.add(world.getName());
        return worldNames;
    }

    public String getHelpText() {
        return helpText;
    }

    public String getPermission() { return permission; }
}
