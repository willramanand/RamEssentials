package com.gmail.willramanand.RamEssentials.commands;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import org.bukkit.entity.Player;

@CommandAlias("stonecutter")
public class StoneCutterCommand extends RBaseCommand {

    private final RamEssentials plugin;

    public StoneCutterCommand(RamEssentials plugin) {
        this.plugin = plugin;
    }

    @Default
    @Description("Opens stonecutter inventory.")
    @CommandPermission("ramessentials.stonecutter")
    public void stoneCutter(Player player) {
        player.openStonecutter(player.getLocation(), true);
    }
}
