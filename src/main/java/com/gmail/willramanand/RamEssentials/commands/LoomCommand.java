package com.gmail.willramanand.RamEssentials.commands;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import org.bukkit.entity.Player;

@CommandAlias("loom")
public class LoomCommand extends RBaseCommand {

    private final RamEssentials plugin;

    public LoomCommand(RamEssentials plugin) {
        this.plugin = plugin;
    }

    @Default
    @Description("Opens loom inventory.")
    @CommandPermission("ramessentials.loom")
    public void loom(Player player) {
        player.openLoom(player.getLocation(), true);
    }
}
