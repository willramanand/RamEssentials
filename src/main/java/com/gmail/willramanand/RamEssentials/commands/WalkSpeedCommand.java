package com.gmail.willramanand.RamEssentials.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.utils.ColorUtils;
import org.bukkit.entity.Player;

@CommandAlias("walkspeed")
public class WalkSpeedCommand extends BaseCommand {

    private final RamEssentials plugin;

    public WalkSpeedCommand(RamEssentials plugin) {
        this.plugin = plugin;
    }

    @Default
    @Description("Change your walk speed")
    @CommandPermission("ramessentials.walkspeed")
    public void changeSpeed(Player player, @Default("0.2f") float speed) {
        if (speed > 1.0f || speed < -1.0f) {
            player.sendMessage(ColorUtils.colorMessage("&cInvalid speed! Must be between -1.0 and 1.0"));
            return;
        }

        player.setWalkSpeed(speed);
    }

}
