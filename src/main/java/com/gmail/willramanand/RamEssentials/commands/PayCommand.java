package com.gmail.willramanand.RamEssentials.commands;

import co.aikar.commands.annotation.*;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.utils.Formatter;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("pay")
public class PayCommand extends RBaseCommand {

    private final RamEssentials plugin;

    public PayCommand(RamEssentials plugin) {
        this.plugin = plugin;
    }

    @Default
    @CommandCompletion("@players")
    @Description("Pay another player")
    public void payPlayer(CommandSender sender, @Flags("other") OfflinePlayer other, double amount) {
        if (!(sender instanceof Player)) {
            msg(sender, "{w}You must be player to use this command!");
            return;
        }

        if (!(plugin.getAccountManager().hasAccount(other))) {
            msg(sender, "{w}This player does not exist or has never joined this server!");
            return;
        }

        Player player = (Player) sender;

        if (plugin.getAccountManager().isValidTransaction(player, amount)) {
            plugin.getAccountManager().subFromBalance(player, amount);
            plugin.getAccountManager().addToBalance(other, amount);
            msg(player, "{s}Paid {h}" + other.getName() + " {s}the amount {h}" + Formatter.formatMoney(amount));
            if (other.isOnline()) {
                Player otherPlayer = other.getPlayer();
                msg(otherPlayer, "{s}You received {h}" + Formatter.formatMoney(amount) + " {s}from {h}" + player.getName());
            }
        } else {
            msg(player, "{w}You do not have that amount!");
        }
    }
}
