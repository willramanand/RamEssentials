package com.gmail.willramanand.RamEssentials.commands;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.utils.Txt;
import com.gmail.willramanand.RamEssentials.utils.TxtReader;
import org.bukkit.entity.Player;

@CommandAlias("motd")
public class MotdCommand extends RBaseCommand {

    private final RamEssentials plugin;

    public MotdCommand(RamEssentials plugin) {
        this.plugin = plugin;
    }

    @Default
    @Description("Outputs the message of the day")
    public void motd(Player player) {
        msg(player, Txt.header("MOTD"));
        TxtReader.sendMotd(player);
    }
}
