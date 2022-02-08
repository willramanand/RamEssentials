package com.gmail.willramanand.RamEssentials.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.utils.ColorUtils;
import com.gmail.willramanand.RamEssentials.utils.Formatter;
import org.bukkit.entity.Player;

import java.util.UUID;

@CommandAlias("pay")
public class PayCommand extends BaseCommand {

    private final RamEssentials plugin;

    public PayCommand(RamEssentials plugin) {
        this.plugin = plugin;
    }

    @Default
    @CommandCompletion("@players")
    @Description("Pay another player")
    public void payPlayer(Player player, @Flags("other") Player other, double amount) {
        if (plugin.getAccountManager().isValidTransaction(player, amount)) {
            plugin.getAccountManager().subFromBalance(player, amount);
            plugin.getAccountManager().addToBalance(other, amount);
            player.sendMessage(ColorUtils.colorMessage("&ePayed &d" + other.getName() + " &ethe amount &d" + Formatter.formatMoney(amount)));
            other.sendMessage(ColorUtils.colorMessage("&eYou received &d" + Formatter.formatMoney(amount) + " &efrom &d" + player.getName()));
        } else {
            player.sendMessage(ColorUtils.colorMessage("&cYou do not have that amount!"));
        }
    }
}
