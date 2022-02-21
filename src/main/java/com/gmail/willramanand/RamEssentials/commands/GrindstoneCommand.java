package com.gmail.willramanand.RamEssentials.commands;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import org.bukkit.entity.Player;

@CommandAlias("grindstone")
public class GrindstoneCommand extends RBaseCommand {

    private final RamEssentials plugin;

    public GrindstoneCommand(RamEssentials plugin) {
        this.plugin = plugin;
    }

    @Default
    @Description("Opens grindstone inventory.")
    @CommandPermission("ramessentials.grindstone")
    public void grindstone(Player player) {
        player.openGrindstone(player.getLocation(), true);
    }
}
