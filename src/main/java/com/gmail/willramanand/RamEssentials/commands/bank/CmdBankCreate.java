package com.gmail.willramanand.RamEssentials.commands.bank;

import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.commands.CommandContext;
import com.gmail.willramanand.RamEssentials.commands.EssCommand;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;

public class CmdBankCreate extends EssCommand {
    public CmdBankCreate(RamEssentials plugin) {
        super(plugin, true, false, "ramessentials.eco", 1, 4);
        this.aliases.addAll(Arrays.asList("create", "c"));
        this.usage = " <name> [baseInterest] [interestMult] [openingCost]";
        this.helpText = "This commands allows administrators to create banks";
    }

    @Override
    public void perform(CommandContext context) {
        String bankName = context.argAsString(0);
        double base = context.argAsDouble(1, 0.5);
        double mult = context.argAsDouble(2, 2.0);
        double openingCost = context.argAsDouble(3, 2500.0);

        if (plugin.getBankManager().exists(bankName)) {
            context.msg("{w}A bank with this name already exists!");
            return;
        }

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

        plugin.getBankManager().createBank(bankName, base, mult, openingCost);
        context.msg("{h}" + bankName + " {s}was created!");
    }

    @Override
    public List<String> tabCompletes(CommandSender sender, String[] args) {
        return null;
    }
}
