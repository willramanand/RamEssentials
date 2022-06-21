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

public class CmdAccountDelete extends EssCommand {

    public CmdAccountDelete(RamEssentials plugin) {
        super(plugin, true, true, 1, 1);
        this.aliases.add("delete");
        this.usage = " <bankName>";
        this.helpText = "This allows a player to delete an account at specified bank.";
    }

    @Override
    public void perform(CommandContext context) {
        String bankName = context.argAsString(0);

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
        if (account.getCapital() > 0.0) {
            context.msg("{w}You cannot delete an account that has money in it!");
            context.msg("{w}Please withdraw all the money to delete it!");
            return;
        }

        bank.deleteAccount(context.player.getUniqueId());
        context.msg("{s}Your account with {h}" + bank.getName() + " {s}has been deleted!");
    }

    @Override
    public List<String> tabCompletes(CommandSender sender, String[] args) {
        if (args.length == 1) {
            return new ArrayList<>(plugin.getBankManager().getBanks().keySet());
        }
        return new ArrayList<>();
    }
}
