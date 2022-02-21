package com.gmail.willramanand.RamEssentials.commands;

import co.aikar.commands.annotation.*;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.utils.TeleportUtils;
import org.bukkit.World;
import org.bukkit.entity.Player;

@CommandAlias("world")
public class WorldCommand extends RBaseCommand {

    private final RamEssentials plugin;

    public WorldCommand(RamEssentials plugin) {
        this.plugin = plugin;
    }

    @Default
    @CommandCompletion("@worlds")
    @CommandPermission("ramessentials.world")
    @Description("Teleport to spawn location of each world")
    public void worldTeleport(Player player, World world) {
        msg(player, "{s}Teleporting to {h}" + world.getName());
        TeleportUtils.teleport(player, world.getSpawnLocation());
    }
}
