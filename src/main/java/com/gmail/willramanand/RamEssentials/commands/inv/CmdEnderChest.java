package com.gmail.willramanand.RamEssentials.commands.inv;

import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.commands.CommandContext;
import com.gmail.willramanand.RamEssentials.commands.EssCommand;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class CmdEnderChest extends EssCommand {

    public CmdEnderChest(RamEssentials plugin) {
        super(plugin, true, true,0, 1);
    }

    @Override
    public void perform(CommandContext context) {
        context.player.openInventory(context.argAsPlayer(0, context.player).getEnderChest());
    }

    @Override
    public List<String> tabCompletes(CommandSender sender, String[] args) {
        if (args.length == 1) return tabCompletePlayers();
        return new ArrayList<>();
    }
}
