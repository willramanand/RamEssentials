package com.gmail.willramanand.RamEssentials.commands.account;

import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.commands.CommandContext;
import com.gmail.willramanand.RamEssentials.commands.EssCommand;
import com.gmail.willramanand.RamEssentials.economy.Bank;
import com.gmail.willramanand.RamEssentials.economy.BankAccount;
import com.gmail.willramanand.RamEssentials.utils.Formatter;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CmdAccountDeposit extends EssCommand {

    public CmdAccountDeposit(RamEssentials plugin) {
        super(plugin, true, true, 2, 2);
        this.aliases.add("deposit");
        this.usage = " <bankName> <amount>";
        this.helpText = "This allows a player to deposit money to an account at a specific bank.";
    }

    @Override
    public void perform(CommandContext context) {
        String bankName = context.argAsString(0);
        double amount = context.argAsDouble(1);

        if (!plugin.getBankManager().exists(bankName)) {
            context.msg("{w}There is no bank that exists with that name!");
            return;
        }

        Bank bank = plugin.getBankManager().getBank(bankName);

        if (!bank.hasAccount(context.player.getUniqueId())) {
            context.msg("{w}You do not have an account at this bank!");
            return;
        }

        BankAccount account = bank.getAccount(context.player.getUniqueId());

        if (amount < 0.0) {
            context.msg("{w}Please enter a valid amount to deposit!");
            return;
        }

        if (!plugin.getAccountManager().isValidTransaction(context.player, amount)) {
            context.msg("{w}You do not have that amount to deposit!");
            return;
        }

        plugin.getAccountManager().subFromBalance(context.player, amount);
        account.deposit(amount);
        context.msg("{s}You have deposited {h}" + Formatter.formatMoney(amount) + " {s}into your account with {h}" + bank.getName());
    }

    @Override
    public List<String> tabCompletes(CommandSender sender, String[] args) {
        if (args.length == 1) {
            return new ArrayList<>(plugin.getBankManager().getBanks().keySet());
        }
        return new ArrayList<>();
    }
}
