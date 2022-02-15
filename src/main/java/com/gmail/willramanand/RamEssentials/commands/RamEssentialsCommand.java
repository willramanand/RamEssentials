package com.gmail.willramanand.RamEssentials.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.Subcommand;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.utils.ColorUtils;
import org.bukkit.command.CommandSender;

@CommandAlias("ramessentials|ramess|ress")
@Description("Command for dealing with all things RamEssentials")
public class RamEssentialsCommand extends BaseCommand {

    private final RamEssentials plugin;

    public RamEssentialsCommand(RamEssentials plugin) {
        this.plugin = plugin;
    }

    @Default
    @Subcommand("version|v")
    @Description("Displays plugin version and information")
    public void displayVersion(CommandSender sender) {
        sender.sendMessage(ColorUtils.colorMessage("&6---- &b" + plugin.getName() + "&6----"));
        sender.sendMessage(ColorUtils.colorMessage("&dAuthor: &eWillRam"));
        sender.sendMessage(ColorUtils.colorMessage("&dVersion: &e" + plugin.getDescription().getVersion()));
        sender.sendMessage(ColorUtils.colorMessage("dDesc: &e" + plugin.getDescription().getDescription()));
    }
}
