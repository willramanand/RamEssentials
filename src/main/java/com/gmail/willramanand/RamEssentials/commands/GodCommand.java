package com.gmail.willramanand.RamEssentials.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.player.EPlayer;
import com.gmail.willramanand.RamEssentials.utils.ColorUtils;
import org.bukkit.entity.Player;

@CommandAlias("god|godmode|tgm")
public class GodCommand extends BaseCommand {

    private final RamEssentials plugin;

    public GodCommand(RamEssentials plugin) {
        this.plugin = plugin;
    }

    @Default
    @Description("Toggle god mode.")
    @CommandPermission("ramessentials.god")
    public void godMode(Player player) {
        EPlayer ePlayer = plugin.getPlayerManager().getPlayerData(player);

        if (ePlayer.isGodMode()) {
            ePlayer.setGodMode(false);
            player.sendMessage(ColorUtils.colorMessage("&eGod mode &cdisabled&e."));
        } else {
            ePlayer.setGodMode(true);
            player.sendMessage(ColorUtils.colorMessage("&eGod mode &aenabled&e."));
        }
    }
}
