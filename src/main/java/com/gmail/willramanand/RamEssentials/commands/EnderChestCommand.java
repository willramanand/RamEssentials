package com.gmail.willramanand.RamEssentials.commands;

import co.aikar.commands.annotation.*;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("enderchest|echest")
public class EnderChestCommand extends RBaseCommand {

    private final RamEssentials plugin;

    public EnderChestCommand(RamEssentials plugin) {
        this.plugin = plugin;
    }

    @Default
    @Description("View your enderchest.")
    @CommandPermission("ramessentials.enderchest")
    public void enderChest(Player player) {
        player.openInventory(player.getEnderChest());
    }

    @Subcommand("other|o")
    @Description("View another player's enderchest.")
    @CommandCompletion("@players")
    @CommandPermission("ramessentials.enderchest.other")
    public void enderChestOther(CommandSender sender, @Flags("other") Player player) {
        if (!(sender instanceof Player)) {
            msg(sender, "{w}You must be player to use this command!");
            return;
        }

        Player playerSender = (Player) sender;
        playerSender.openInventory(player.getEnderChest());
    }

}
