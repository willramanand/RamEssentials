package com.gmail.willramanand.RamEssentials.commands;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import org.bukkit.entity.Player;

@CommandAlias("smithingtable|smithtable")
public class SmithingTableCommand extends RBaseCommand {

    private final RamEssentials plugin;

    public SmithingTableCommand(RamEssentials plugin) {
        this.plugin = plugin;
    }

    @Default
    @Description("Opens smithing table inventory.")
    @CommandPermission("ramessentials.smithingtable")
    public void smithingTable(Player player) {
        player.openSmithingTable(player.getLocation(), true);
    }
}
