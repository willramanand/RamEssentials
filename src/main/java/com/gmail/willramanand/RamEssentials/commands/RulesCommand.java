package com.gmail.willramanand.RamEssentials.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.utils.TxtReader;
import org.bukkit.entity.Player;

@CommandAlias("rules")
public class RulesCommand extends BaseCommand {

    private final RamEssentials plugin;

    public RulesCommand(RamEssentials plugin) {
        this.plugin = plugin;
    }

    @Default
    @Description("Show the server rules")
    public void rules(Player player) {
        TxtReader.sendRules(player);
    }
}
