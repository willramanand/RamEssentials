package com.gmail.willramanand.RamEssentials.commands;

import co.aikar.commands.annotation.*;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("invsee")
public class InventoryCommand extends RBaseCommand {

    private final RamEssentials plugin;

    public InventoryCommand(RamEssentials plugin) {
        this.plugin = plugin;
    }

    @Default
    @Description("Opens crafting inventory.")
    @CommandCompletion("@players")
    @CommandPermission("ramessentials.invsee")
    public void inventorySee(CommandSender sender, @Flags("other") Player player) {
        if (!(sender instanceof Player)) {
            msg(sender, "{w}You must be player to use this command!");
            return;
        }
        Player playerSender = (Player) sender;
        playerSender.openInventory(player.getInventory());
    }
}
