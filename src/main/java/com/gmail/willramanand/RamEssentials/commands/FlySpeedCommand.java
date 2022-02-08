package com.gmail.willramanand.RamEssentials.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.utils.ColorUtils;
import org.bukkit.entity.Player;

@CommandAlias("flyspeed")
public class FlySpeedCommand extends BaseCommand {

    private final RamEssentials plugin;

    public FlySpeedCommand(RamEssentials plugin) {
        this.plugin = plugin;
    }

    @Default
    @Description("Change your fly speed")
    @CommandPermission("ramessentials.flyspeed")
    public void changeSpeed(Player player, @Default("0.1f") float speed) {
        if (speed > 1.0f || speed < -1.0f) {
            player.sendMessage(ColorUtils.colorMessage("&cInvalid speed! Must be between -1.0 and 1.0"));
            return;
        }
        player.setFlySpeed(speed);
    }

}
