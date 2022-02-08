package com.gmail.willramanand.RamEssentials.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("enderchest|echest")
public class EnderChestCommand extends BaseCommand {

    private final RamEssentials plugin;

    public EnderChestCommand(RamEssentials plugin) {
        this.plugin = plugin;
    }

    @Default
    @Description("View your enderchest.")
    @CommandPermission("ramessentials.enderchest")
    public void enderChest(CommandSender sender) {
        Player player = (Player) sender;
        player.openInventory(player.getEnderChest());
    }

    @Subcommand("other|o")
    @Description("View another player's enderchest.")
    @CommandCompletion("@players")
    @CommandPermission("ramessentials.enderchest.other")
    public void enderChestOther(CommandSender sender, @Flags("other") Player player) {
        Player playerSender = (Player) sender;
        playerSender.openInventory(player.getEnderChest());
    }

}
