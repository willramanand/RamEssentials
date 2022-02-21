package com.gmail.willramanand.RamEssentials.commands;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import org.bukkit.entity.Player;

@CommandAlias("cartographytable|carttable")
public class CartographyTableCommand extends RBaseCommand {

    private final RamEssentials plugin;

    public CartographyTableCommand(RamEssentials plugin) {
        this.plugin = plugin;
    }

    @Default
    @Description("Opens cartography table inventory.")
    @CommandPermission("ramessentials.cartographytable")
    public void cartographyTable(Player player) {
        player.openCartographyTable(player.getLocation(), true);
    }
}
