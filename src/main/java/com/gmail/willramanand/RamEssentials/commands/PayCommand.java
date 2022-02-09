package com.gmail.willramanand.RamEssentials.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.utils.ColorUtils;
import com.gmail.willramanand.RamEssentials.utils.Formatter;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("pay")
public class PayCommand extends BaseCommand {

    private final RamEssentials plugin;

    public PayCommand(RamEssentials plugin) {
        this.plugin = plugin;
    }

    @Default
    @CommandCompletion("@players")
    @Description("Pay another player")
    public void payPlayer(CommandSender sender, @Flags("other") Player other, double amount) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ColorUtils.colorMessage("&cYou must be player to use this command!"));
            return;
        }

        Player player = (Player) sender;

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
