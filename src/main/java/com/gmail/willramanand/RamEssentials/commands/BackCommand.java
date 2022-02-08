package com.gmail.willramanand.RamEssentials.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.player.EPlayer;
import com.gmail.willramanand.RamEssentials.utils.ColorUtils;
import com.gmail.willramanand.RamEssentials.utils.TeleportUtils;
import org.bukkit.entity.Player;

@CommandAlias("back")
public class BackCommand extends BaseCommand {

    private final RamEssentials plugin;

    public BackCommand(RamEssentials plugin) {
        this.plugin = plugin;
    }

    @Default
    @Description("Return to your previous location.")
    public void back(Player player) {
        EPlayer ePlayer = plugin.getPlayerManager().getPlayerData(player);

        if (ePlayer.getLastLocation() == null) {
            player.sendMessage(ColorUtils.colorMessage("&eYou do not have a saved previous location."));
            return;
        }
        TeleportUtils.teleport(player, ePlayer.getLastLocation());
    }
}
