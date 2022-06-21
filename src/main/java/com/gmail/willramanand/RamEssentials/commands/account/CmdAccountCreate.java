package com.gmail.willramanand.RamEssentials.commands.account;

import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.commands.CommandContext;
import com.gmail.willramanand.RamEssentials.commands.EssCommand;
import com.gmail.willramanand.RamEssentials.economy.Bank;
import com.gmail.willramanand.RamEssentials.utils.Formatter;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CmdAccountCreate extends EssCommand {

    public CmdAccountCreate(RamEssentials plugin) {
        super(plugin, true, true, 1, 1);
        this.aliases.add("create");
        this.usage = " <bankName>";
        this.helpText = "This allows a player to create an account at specified bank.";
    }

    @Override
    public void perform(CommandContext context) {
        String bankName = context.argAsString(0);

        if (!plugin.getBankManager().exists(bankName)) {
            context.msg("{w}There is no bank that exists with that name!");
            return;
        }

        Bank bank = plugin.getBankManager().getBank(bankName);

        if (bank.hasAccount(context.player.getUniqueId())) {
            context.msg("{w}You already have an account at this bank!");
            return;
        }

        if (!plugin.getAccountManager().isValidTransaction(context.player, bank.getOpeningCost())) {
            context.msg("{w}You do not have the balance of {h}" + Formatter.formatMoney(bank.getOpeningCost()) + " {w}to open an account with this bank!");
            return;
        }

        bank.createAccount(context.player.getUniqueId());
        plugin.getAccountManager().subFromBalance(context.player, bank.getOpeningCost());
        context.msg("{s}You now have an account with {h}" + bank.getName());
    }

    @Override
    public List<String> tabCompletes(CommandSender sender, String[] args) {
        if (args.length == 1) {
            return new ArrayList<>(plugin.getBankManager().getBanks().keySet());
        }
        return new ArrayList<>();
    }
}
