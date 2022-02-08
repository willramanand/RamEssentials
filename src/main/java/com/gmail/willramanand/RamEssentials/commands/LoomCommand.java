package com.gmail.willramanand.RamEssentials.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("loom")
public class LoomCommand extends BaseCommand {

    private final RamEssentials plugin;

    public LoomCommand(RamEssentials plugin) {
        this.plugin = plugin;
    }

    @Default
    @Description("Opens loom inventory.")
    @CommandPermission("ramessentials.loom")
    public void loom(CommandSender sender) {
        Player player = (Player) sender;
        player.openLoom(player.getLocation(), true);
    }
}
