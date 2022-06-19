package com.gmail.willramanand.RamEssentials.commands.bank;

import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.commands.CommandContext;
import com.gmail.willramanand.RamEssentials.commands.EssCommand;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CmdBankDelete extends EssCommand {

    public CmdBankDelete(RamEssentials plugin) {
        super(plugin, true, false, "ramessentials.eco",  1, 1);
        this.aliases.addAll(Arrays.asList("delete", "d"));
        this.usage = " <name>";
        this.helpText = "This is the command for delete banks.";
    }

    @Override
    public void perform(CommandContext context) {
        String bankName = context.argAsString(0);

        if (!plugin.getBankManager().exists(bankName)) {
            context.msg("{w}That bank does not exist!");
            return;
        }

        plugin.getBankManager().deleteBank(bankName);
        context.msg("{h}" + bankName + " {s}and all accounts associated were deleted!");
    }

    @Override
    public List<String> tabCompletes(CommandSender sender, String[] args) {
        if (args.length == 1) {
            return new ArrayList<>(plugin.getBankManager().getBanks().keySet());
        }
        return new ArrayList<>();
    }
}
