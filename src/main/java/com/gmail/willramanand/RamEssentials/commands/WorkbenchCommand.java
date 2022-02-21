package com.gmail.willramanand.RamEssentials.commands;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import org.bukkit.entity.Player;

@CommandAlias("workbench|craft")
public class WorkbenchCommand extends RBaseCommand {

    private final RamEssentials plugin;

    public WorkbenchCommand(RamEssentials plugin) {
        this.plugin = plugin;
    }

    @Default
    @Description("Opens crafting inventory.")
    @CommandPermission("ramessentials.workbench")
    public void workBench(Player player) {
        player.openWorkbench(player.getLocation(), true);
    }
}
