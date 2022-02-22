package com.gmail.willramanand.RamEssentials.commands;

import co.aikar.commands.annotation.*;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.utils.Formatter;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

@CommandAlias("balance|bal|money")
public class BalanceCommand extends RBaseCommand {

    private final RamEssentials plugin;

    public BalanceCommand(RamEssentials plugin) {
        this.plugin = plugin;
    }

    @Default
    @Description("Show your balance")
    @CommandCompletion("@players")
    public void balance(Player player, @Optional @Flags("other") OfflinePlayer other) {
        double balance;

        if (other == null) {
            balance = plugin.getAccountManager().getBalance(player);
        } else {
            if (!(plugin.getAccountManager().hasAccount(other))) {
                msg(player, "{w}This player does not exist or has never joined this server!");
                return;
            }
            balance = plugin.getAccountManager().getBalance(other);
        }

        msg(player,"{green}Balance: {gold}" + Formatter.formatMoney(balance));
    }
}
