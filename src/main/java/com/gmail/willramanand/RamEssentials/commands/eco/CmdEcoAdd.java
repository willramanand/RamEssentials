package com.gmail.willramanand.RamEssentials.commands.eco;

import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.commands.CommandContext;
import com.gmail.willramanand.RamEssentials.commands.EssCommand;
import com.gmail.willramanand.RamEssentials.utils.Formatter;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CmdEcoAdd extends EssCommand {
    public CmdEcoAdd(RamEssentials plugin) {
        super(plugin, true, false, "ramessentials.eco", 2, 2);
        this.aliases.addAll(Arrays.asList("add", "deposit"));
        this.helpText = "Add a specific amount of currency to player's account";
    }

    @Override
    public void perform(CommandContext context) {
        OfflinePlayer player = context.argAsOffPlayer(0);
        double amount = context.argAsDouble(1);

        context.msg(context.args.toString());

        if (amount < 0) {
            context.msg("{w}Cannot add negative value!");
            return;
        }

        if (player == null || !(plugin.getAccountManager().hasAccount(player))) {
            context.msg("{w}This player does not exist or has never joined this server!");
            return;
        }

        plugin.getAccountManager().addToBalance(player, amount);
        context.msg("{s}Added {h}" + Formatter.formatMoney(amount) + " {s}to {h}" + player.getName() + "'s {s}account!");
        if (player.isOnline()) {
            Player onlinePlayer = player.getPlayer();
            context.msgOther(onlinePlayer, "{h}" + Formatter.formatMoney(amount) + " {s}has been added to your account!");
        }
    }

    @Override
    public List<String> tabCompletes(CommandSender sender, String[] args) {
        return new ArrayList<>();
    }
}
