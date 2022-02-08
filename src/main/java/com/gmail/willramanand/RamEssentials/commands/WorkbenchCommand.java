package com.gmail.willramanand.RamEssentials.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.Inventory;

@CommandAlias("workbench|craft")
public class WorkbenchCommand extends BaseCommand {

    private final RamEssentials plugin;

    public WorkbenchCommand(RamEssentials plugin) {
        this.plugin = plugin;
    }

    @Default
    @Description("Opens crafting inventory.")
    @CommandPermission("ramessentials.workbench")
    public void workBench(CommandSender sender) {
        Player player = (Player) sender;
        player.openWorkbench(player.getLocation(), true);
    }
}
