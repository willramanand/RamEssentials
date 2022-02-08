package com.gmail.willramanand.RamEssentials.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("anvil|anv")
public class AnvilCommand extends BaseCommand {

    private final RamEssentials plugin;

    public AnvilCommand(RamEssentials plugin) {
        this.plugin = plugin;
    }

    @Default
    @Description("Opens anvil inventory.")
    @CommandPermission("ramessentials.anvil")
    public void anvil(CommandSender sender) {
        Player player = (Player) sender;
        player.openAnvil(player.getLocation(), true);
    }
}
