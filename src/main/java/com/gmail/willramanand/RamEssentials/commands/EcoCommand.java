package com.gmail.willramanand.RamEssentials.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.utils.ColorUtils;
import com.gmail.willramanand.RamEssentials.utils.Formatter;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("eco|economy")
@CommandPermission("ramessentials.eco")
@Description("Manage the server's economy")
public class EcoCommand extends BaseCommand {

    private final RamEssentials plugin;

    public EcoCommand(RamEssentials plugin) {
        this.plugin = plugin;
    }

    @Subcommand("set")
    @CommandCompletion("@players")
    @Description("Set the balance of a player")
    public void setBalance(CommandSender sender, @Flags("other") Player player, double amount) {

        if (amount < 0) {
            sender.sendMessage(ColorUtils.colorMessage("&cAmount cannot be lower than 0!"));
            return;
        }

        plugin.getAccountManager().setBalance(player, amount);
        sender.sendMessage(ColorUtils.colorMessage("&eSet balance of &d" + player.getName() + " &eto &d" + Formatter.formatMoney(amount)));
    }

    @Subcommand("add")
    @CommandCompletion("@players")
    @Description("Add to the balance of a player")
    public void addBalance(CommandSender sender, @Flags("other") Player player, double amount) {
        if (amount < 0) {
            sender.sendMessage(ColorUtils.colorMessage("&cCannot add negative value!"));
            return;
        }

        plugin.getAccountManager().addToBalance(player, amount);
        sender.sendMessage(ColorUtils.colorMessage("&eAdded &d" + Formatter.formatMoney(amount) + " &eto player's account"));
    }


    @Subcommand("w|withdraw")
    @CommandCompletion("@players")
    @Description("Withdraw from the balance of a player")
    public void removeBalance(CommandSender sender, @Flags("other") Player player, double amount) {
        if (amount < 0) {
            sender.sendMessage(ColorUtils.colorMessage("&cCannot remove negative value!"));
            return;
        }

        plugin.getAccountManager().addToBalance(player, amount);
        sender.sendMessage(ColorUtils.colorMessage("&eRemoved &d" + Formatter.formatMoney(amount) + " &efrom player's account"));
    }
}
