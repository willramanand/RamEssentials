package com.gmail.willramanand.RamEssentials.commands;

import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.utils.Formatter;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CmdPay extends EssCommand {

    public CmdPay(RamEssentials plugin) {
        super(plugin, true, true, null, 2, 2);
    }

    @Override
    public void perform(CommandContext context) {
        OfflinePlayer other = context.argAsOffPlayer(0);
        double amount = context.argAsDouble(1);
        if (other == null) {
            context.msg("{w}That player does not exist or is not online!");
            return;
        }

        if (amount <= 0) {
            context.msg("{w}You cannot pay less than or equal to a zero!");
        }

        if (!(plugin.getAccountManager().hasAccount(other))) {
            context.msg("{w}This player does not exist or has never joined this server!");
            return;
        }

        Player player = context.player;

        if (plugin.getAccountManager().isValidTransaction(player, amount)) {
            plugin.getAccountManager().subFromBalance(player, amount);
            plugin.getAccountManager().addToBalance(other, amount);
            context.msg("{s}Paid {h}" + other.getName() + " {s}the amount {h}" + Formatter.formatMoney(amount));
            if (other.isOnline()) {
                Player otherPlayer = other.getPlayer();
                context.msgOther(otherPlayer, "{s}You received {h}" + Formatter.formatMoney(amount) + " {s}from {h}" + player.getName());
            }
        } else {
            context.msg("{w}You do not have that amount!");
        }
    }

    @Override
    public List<String> tabCompletes(CommandSender sender, String[] args) {
        if (args.length == 1) return tabCompletePlayers();
        return new ArrayList<>();
    }
}
