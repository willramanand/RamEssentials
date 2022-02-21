package com.gmail.willramanand.RamEssentials.commands;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import org.bukkit.entity.Player;

@CommandAlias("anvil|anv")
public class AnvilCommand extends RBaseCommand {

    private final RamEssentials plugin;

    public AnvilCommand(RamEssentials plugin) {
        this.plugin = plugin;
    }

    @Default
    @Description("Opens anvil inventory.")
    @CommandPermission("ramessentials.anvil")
    public void anvil(Player player) {
        player.openAnvil(player.getLocation(), true);
    }
}
