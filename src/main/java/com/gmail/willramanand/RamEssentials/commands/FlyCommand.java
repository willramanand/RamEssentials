package com.gmail.willramanand.RamEssentials.commands;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import org.bukkit.entity.Player;

@CommandAlias("fly")
public class FlyCommand extends RBaseCommand {

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
            msg(player, "{s}Fly mode {w}disabled{s}.");
        } else {
            player.setAllowFlight(true);
            player.setFlying(true);
            msg(player, "{s}Fly mode {green}enabled{s}.");
        }
    }
}
