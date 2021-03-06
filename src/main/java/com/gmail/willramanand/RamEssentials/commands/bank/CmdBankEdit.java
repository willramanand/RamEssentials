package com.gmail.willramanand.RamEssentials.commands.bank;

import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.commands.CommandContext;
import com.gmail.willramanand.RamEssentials.commands.EssCommand;
import com.gmail.willramanand.RamEssentials.economy.Bank;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class CmdBankEdit extends EssCommand {
    public CmdBankEdit(RamEssentials plugin) {
        super(plugin, true, false, "ramessentials.eco", 4, 4);
        this.aliases.add("edit");
        this.usage = " <name> [baseInterest] [interestMult] [openingCost]";
        this.helpText = "This commands allows administrators to edit banks";
    }

    @Override
    public void perform(CommandContext context) {
        String bankName = context.argAsString(0);

        if (!plugin.getBankManager().exists(bankName)) {
            context.msg("{w}This bank does not exist!");
            return;
        }

        Bank bank = plugin.getBankManager().getBank(bankName);

        double base = context.argAsDouble(1, bank.getBaseInterest());
        double mult = context.argAsDouble(2, bank.getMultInterest());
        double openingCost = context.argAsDouble(3, bank.getOpeningCost());

        if (base < 0.0) {
            context.msg("{w}The base interest value cannot be negative!");
            return;
        }

        if (mult < 0.0) {
            context.msg("{w}The interest value multiplier cannot be negative!");
            return;
        }

        if (openingCost < 0.0) {
            context.msg("{w}The opening cost cannot be negative!");
            return;
        }

        bank.setBaseInterest(base);
        bank.setMultInterest(mult);
        bank.setOpeningCost(openingCost);

        context.msg(bank.toString());
    }

    @Override
    public List<String> tabCompletes(CommandSender sender, String[] args) {
        if (args.length == 1) {
            return new ArrayList<>(plugin.getBankManager().getBanks().keySet());
        }
        return new ArrayList<>();
    }
}
