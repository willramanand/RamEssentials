package com.gmail.willramanand.RamEssentials.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

@CommandAlias("invsee")
public class InventoryCommand extends BaseCommand {

    private final RamEssentials plugin;

    public InventoryCommand(RamEssentials plugin) {
        this.plugin = plugin;
    }

    @Default
    @Description("Opens crafting inventory.")
    @CommandCompletion("@players")
    @CommandPermission("ramessentials.invsee")
    public void inventorySee(CommandSender sender, @Flags("other") Player player) {
        Player playerSender = (Player) sender;
        playerSender.openInventory(player.getInventory());
    }
}
