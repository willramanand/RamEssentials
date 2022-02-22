package com.gmail.willramanand.RamEssentials.commands;

import co.aikar.commands.annotation.*;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.utils.Formatter;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("eco|economy")
@CommandPermission("ramessentials.eco")
@Description("Manage the server's economy")
public class EcoCommand extends RBaseCommand {

    private final RamEssentials plugin;

    public EcoCommand(RamEssentials plugin) {
        this.plugin = plugin;
    }

    @Subcommand("set")
    @CommandCompletion("@players")
    @Description("Set the balance of a player")
    public void setBalance(CommandSender sender, @Flags("other") OfflinePlayer player, double amount) {

        if (amount < 0) {
            msg(sender,"{w}Amount cannot be lower than 0!");
            return;
        }

        if (!(plugin.getAccountManager().hasAccount(player))) {
            msg(sender, "{w}This player does not exist or has never joined this server!");
            return;
        }

        plugin.getAccountManager().setBalance(player, amount);
        msg(sender,"{s}Set balance of {h}" + player.getName() + " {s}to {h}" + Formatter.formatMoney(amount));
        if (player.isOnline()) {
            Player onlinePlayer = player.getPlayer();
            msg(onlinePlayer, "{s}Balance has been set to {h}" + Formatter.formatMoney(amount));
        }
    }

    @Subcommand("add")
    @CommandCompletion("@players")
    @Description("Add to the balance of a player")
    public void addBalance(CommandSender sender, @Flags("other") OfflinePlayer player, double amount) {
        if (amount < 0) {
            msg(sender, "{w}Cannot add negative value!");
            return;
        }

        if (!(plugin.getAccountManager().hasAccount(player))) {
            msg(sender, "{w}This player does not exist or has never joined this server!");
            return;
        }

        plugin.getAccountManager().addToBalance(player, amount);
        msg(sender, "{s}Added {h}" + Formatter.formatMoney(amount) + " {s}to {h}" + player.getName() + "'s {s}account!");
        if (player.isOnline()) {
            Player onlinePlayer = player.getPlayer();
            msg(onlinePlayer, "{h}" + Formatter.formatMoney(amount) + " {s}has been added to your account!");
        }
    }


    @Subcommand("w|withdraw")
    @CommandCompletion("@players")
    @Description("Withdraw from the balance of a player")
    public void removeBalance(CommandSender sender, @Flags("other") OfflinePlayer player, double amount) {
        if (amount < 0) {
            msg(sender, "{w}Cannot remove negative value!");
            return;
        }

        if (!(plugin.getAccountManager().hasAccount(player))) {
            msg(sender, "{w}This player does not exist or has never joined this server!");
            return;
        }

        plugin.getAccountManager().addToBalance(player, amount);
        msg(sender, "{s}Removed {h}" + Formatter.formatMoney(amount) + " {s}from {h}" + player.getName() + "'s {s}account");

        if (player.isOnline()) {
            Player onlinePlayer = player.getPlayer();
            msg(onlinePlayer, "{h}" + Formatter.formatMoney(amount) + " {s}has been removed from your account!");
        }
    }
}
