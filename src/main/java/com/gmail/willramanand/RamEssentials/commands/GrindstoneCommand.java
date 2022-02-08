package com.gmail.willramanand.RamEssentials.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("grindstone")
public class GrindstoneCommand extends BaseCommand {

    private final RamEssentials plugin;

    public GrindstoneCommand(RamEssentials plugin) {
        this.plugin = plugin;
    }

    @Default
    @Description("Opens grindstone inventory.")
    @CommandPermission("ramessentials.grindstone")
    public void grindstone(CommandSender sender) {
        Player player = (Player) sender;
        player.openGrindstone(player.getLocation(), true);
    }
}
