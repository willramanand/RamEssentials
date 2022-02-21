package com.gmail.willramanand.RamEssentials.commands;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import org.bukkit.entity.Player;

@CommandAlias("walkspeed")
public class WalkSpeedCommand extends RBaseCommand {

    private final RamEssentials plugin;

    public WalkSpeedCommand(RamEssentials plugin) {
        this.plugin = plugin;
    }

    @Default
    @Description("Change your walk speed")
    @CommandPermission("ramessentials.walkspeed")
    public void changeSpeed(Player player, @Default("0.2f") float speed) {
        if (speed > 1.0f || speed < -1.0f) {
            msg(player, "{w}Invalid speed! Must be between -1.0 and 1.0");
            return;
        }

        if (speed == 0.2f) {
            msg(player, "{s}Set to default speed!");
        }
        player.setWalkSpeed(speed);
    }

}
