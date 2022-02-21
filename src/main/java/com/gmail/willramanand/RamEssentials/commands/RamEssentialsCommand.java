package com.gmail.willramanand.RamEssentials.commands;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.Subcommand;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import org.bukkit.command.CommandSender;

@CommandAlias("ramessentials|ramess|ress")
@Description("Command for dealing with all things RamEssentials")
public class RamEssentialsCommand extends RBaseCommand {

    private final RamEssentials plugin;

    public RamEssentialsCommand(RamEssentials plugin) {
        this.plugin = plugin;
    }

    @Default
    @Subcommand("version|v")
    @Description("Displays plugin version and information")
    public void displayVersion(CommandSender sender) {
        msg(sender, "{gold}---- {aqua}" + plugin.getName() + "{gold}----");
        msg(sender, "{h}Author: {s}WillRam");
        msg(sender, "{h}Version: {s}" + plugin.getDescription().getVersion());
        msg(sender, "{h}Desc: {s}" + plugin.getDescription().getDescription());
    }
}
