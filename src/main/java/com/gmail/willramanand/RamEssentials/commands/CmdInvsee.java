package com.gmail.willramanand.RamEssentials.commands;

import com.gmail.willramanand.RamEssentials.RamEssentials;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CmdInvsee extends EssCommand {

    public CmdInvsee(RamEssentials plugin) {
        super(plugin, true, true, 1, 1);
    }

    @Override
    public void perform(CommandContext context) {
        Player player = context.argAsPlayer(0);

        if (player == null) {
            context.msg("{w}That player does not exist or is not online!");
            return;
        }

        context.player.openInventory(player.getInventory());
    }

    @Override
    public List<String> tabCompletes(CommandSender sender, String[] args) {
        if (args.length == 1) return tabCompletePlayers();
        return new ArrayList<>();
    }
}
