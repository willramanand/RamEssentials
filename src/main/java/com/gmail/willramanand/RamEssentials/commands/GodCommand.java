package com.gmail.willramanand.RamEssentials.commands;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.player.EPlayer;
import org.bukkit.entity.Player;

@CommandAlias("god|godmode|tgm")
public class GodCommand extends RBaseCommand {

    private final RamEssentials plugin;

    public GodCommand(RamEssentials plugin) {
        this.plugin = plugin;
    }

    @Default
    @Description("Toggle god mode.")
    @CommandPermission("ramessentials.god")
    public void godMode(Player player) {
        EPlayer ePlayer = getData(player);

        if (ePlayer.isGodMode()) {
            ePlayer.setGodMode(false);
            msg(player, "{s}God mode {w}disabled{s}.");
        } else {
            ePlayer.setGodMode(true);
            msg(player, "{s}God mode {green}enabled{s}.");
        }
    }
}
