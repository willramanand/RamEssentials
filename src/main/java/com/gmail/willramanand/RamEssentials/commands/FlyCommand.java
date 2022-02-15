package com.gmail.willramanand.RamEssentials.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.utils.ColorUtils;
import org.bukkit.entity.Player;

@CommandAlias("fly")
public class FlyCommand extends BaseCommand {

    private final RamEssentials plugin;

    public FlyCommand(RamEssentials plugin) {
        this.plugin = plugin;
    }

    @Default
    @Description("Toggle fly mode")
    @CommandPermission("ramessentials.fly")
    public void fly(Player player) {

        if (player.isFlying()) {
            player.setAllowFlight(false);
            player.setFlying(false);
            player.sendMessage(ColorUtils.colorMessage("&eFly mode &cdisabled&e."));
        } else {
            player.setAllowFlight(true);
            player.setFlying(true);
            player.sendMessage(ColorUtils.colorMessage("&eFly mode &aenabled&e."));
        }
    }
}
