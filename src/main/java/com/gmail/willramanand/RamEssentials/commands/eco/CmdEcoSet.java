package com.gmail.willramanand.RamEssentials.commands.eco;

import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.commands.CommandContext;
import com.gmail.willramanand.RamEssentials.commands.EssCommand;
import com.gmail.willramanand.RamEssentials.utils.Formatter;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public class CmdEcoSet extends EssCommand {
    public CmdEcoSet(RamEssentials plugin) {
        super(plugin, true, false, "ramessentials.eco", 2, 2);
        this.aliases.addAll(Collections.singletonList("set"));
        this.helpText = "Set the amount of current in a player's account";
    }

    @Override
    public void perform(CommandContext context) {
        OfflinePlayer player = context.argAsOffPlayer(0);
        double amount = context.argAsDouble(1);

        if (amount < 0) {
            context.msg("{w}Amount cannot be lower than 0!");
            return;
        }

        if (player == null || !(plugin.getAccountManager().hasAccount(player))) {
            context.msg("{w}This player does not exist or has never joined this server!");
            return;
        }

        plugin.getAccountManager().setBalance(player, amount);
        context.msg("{s}Set balance of {h}" + player.getName() + " {s}to {h}" + Formatter.formatMoney(amount));
        if (player.isOnline()) {
            Player onlinePlayer = player.getPlayer();
            context.msgOther(onlinePlayer, "{s}Balance has been set to {h}" + Formatter.formatMoney(amount));
        }
    }

    @Override
    public List<String> tabCompletes(CommandSender sender, String[] args) {
        return null;
    }
}
