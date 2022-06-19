package com.gmail.willramanand.RamEssentials.commands.bank;

import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.commands.CommandContext;
import com.gmail.willramanand.RamEssentials.commands.EssCommand;
import com.gmail.willramanand.RamEssentials.economy.Bank;
import com.gmail.willramanand.RamEssentials.utils.Txt;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CmdBankView extends EssCommand {
    public CmdBankView(RamEssentials plugin) {
        super(plugin, true, false, "ramessentials.eco", 1, 1);
        this.aliases.addAll(Arrays.asList("view", "v"));
        this.usage = " <name>";
        this.helpText = "View the general information of a bank.";
    }

    @Override
    public void perform(CommandContext context) {
        String bankName = context.argAsString(0);

        if (!plugin.getBankManager().exists(bankName)) {
            context.msg("{w}This bank does not exist!");
            return;
        }

        Bank bank = plugin.getBankManager().getBank(bankName);

        context.msg(Txt.header(bankName.toUpperCase()));
        context.msg("{s}Admin Bank: " + (bank.isAdmin() ? "{green}yes" : "{w}no"));
        context.msg("{s}Capital: {h}" + bank.getCapital());
        context.msg("{s}Base Interest: {h}" + bank.getBaseInterest());
        context.msg("{s}Interest Multiplier: {h}" + bank.getMultInterest());
        context.msg("{s}Number of accounts: {h}" + bank.getBankAccounts().keySet().size() + " {s}accounts");
    }

    @Override
    public List<String> tabCompletes(CommandSender sender, String[] args) {
        if (args.length == 1) {
            return new ArrayList<>(plugin.getBankManager().getBanks().keySet());
        }
        return new ArrayList<>();
    }
}
