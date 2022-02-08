package com.gmail.willramanand.RamEssentials.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("cartographytable|carttable")
public class CartographyTableCommand extends BaseCommand {

    private final RamEssentials plugin;

    public CartographyTableCommand(RamEssentials plugin) {
        this.plugin = plugin;
    }

    @Default
    @Description("Opens cartography table inventory.")
    @CommandPermission("ramessentials.cartographytable")
    public void cartographyTable(CommandSender sender) {
        Player player = (Player) sender;
        player.openCartographyTable(player.getLocation(), true);
    }
}
