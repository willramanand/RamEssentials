package com.gmail.willramanand.RamEssentials.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.utils.TxtReader;
import org.bukkit.entity.Player;

@CommandAlias("motd")
public class MotdCommand extends BaseCommand {

    private final RamEssentials plugin;

    public MotdCommand(RamEssentials plugin) {
        this.plugin = plugin;
    }

    @Default
    @Description("Outputs the message of the day")
    public void motd(Player player) {
        TxtReader.sendMotd(player);
    }
}
