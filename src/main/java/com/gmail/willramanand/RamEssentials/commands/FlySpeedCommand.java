package com.gmail.willramanand.RamEssentials.commands;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import org.bukkit.entity.Player;

@CommandAlias("flyspeed")
public class FlySpeedCommand extends RBaseCommand {

    private final RamEssentials plugin;

    public FlySpeedCommand(RamEssentials plugin) {
        this.plugin = plugin;
    }

    @Default
    @Description("Change your fly speed")
    @CommandPermission("ramessentials.flyspeed")
    public void changeSpeed(Player player, @Default("0.1f") float speed) {
        if (speed > 1.0f || speed < -1.0f) {
            msg(player, "{w}Invalid speed! Must be between -1.0 and 1.0");
            return;
        }

        if (speed == 0.1f) {
            msg(player, "{s}Set to default speed!");
        }

        player.setFlySpeed(speed);
    }

}
