package com.gmail.willramanand.RamEssentials.commands;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.player.EPlayer;
import com.gmail.willramanand.RamEssentials.utils.TeleportUtils;
import org.bukkit.entity.Player;

@CommandAlias("back")
public class BackCommand extends RBaseCommand {

    private final RamEssentials plugin;

    public BackCommand(RamEssentials plugin) {
        this.plugin = plugin;
    }

    @Default
    @Description("Return to your previous location.")
    public void back(Player player) {
        EPlayer ePlayer = getData(player);

        if (ePlayer.getLastLocation() == null) {
            msg(player, "{s}You do not have a saved previous location.");
            return;
        }
        TeleportUtils.teleport(player, ePlayer.getLastLocation());
    }
}
