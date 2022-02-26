package com.gmail.willramanand.RamEssentials.commands;

import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.utils.Txt;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class CmdBroadcast extends EssCommand {
    public CmdBroadcast(RamEssentials plugin) {
        super(plugin, true, false, 1, -1);
    }

    @Override
    public void perform(CommandContext context) {
        String message = context.argAsConcatString(0, "");

        if (message.equalsIgnoreCase("")) {
            context.msg("{w}You cannot broadcast an empty message.");
            return;
        }

        Bukkit.broadcast(Component.text(Txt.parse(message)));
    }

    @Override
    public List<String> tabCompletes(CommandSender sender, String[] args) {
        return new ArrayList<>();
    }
}
