package com.gmail.willramanand.RamEssentials.commands.eco;

import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.commands.CommandContext;
import com.gmail.willramanand.RamEssentials.commands.EssCommand;
import com.gmail.willramanand.RamEssentials.utils.Formatter;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class CmdEcoSubtract extends EssCommand {
    public CmdEcoSubtract(RamEssentials plugin) {
        super(plugin, true, true, "ramessentials.eco", 2, 2);
        this.aliases.addAll(Arrays.asList("subtract", "withdraw"));
        this.usage = " <player> <amount>";
        this.helpText = "Subtract a certain amount of currency from a player's account";
    }

    @Override
    public void perform(CommandContext context) {
        OfflinePlayer player = context.argAsOffPlayer(0);
        double amount = context.argAsDouble(1);

        if (amount < 0) {
            context.msg("{w}Cannot remove negative value!");
            return;
        }

        if (player == null || !(plugin.getAccountManager().hasAccount(player))) {
            context.msg("{w}This player does not exist or has never joined this server!");
            return;
        }

        plugin.getAccountManager().subFromBalance(player, amount);
        context.msg("{s}Removed {h}" + Formatter.formatMoney(amount) + " {s}from {h}" + player.getName() + "'s {s}account");

        if (player.isOnline()) {
            Player onlinePlayer = player.getPlayer();
            context.msgOther(onlinePlayer, "{h}" + Formatter.formatMoney(amount) + " {s}has been removed from your account!");
        }
    }

    @Override
    public List<String> tabCompletes(CommandSender sender, String[] args) {
        return null;
    }
}
