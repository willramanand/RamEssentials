package com.gmail.willramanand.RamEssentials.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.utils.ColorUtils;
import com.gmail.willramanand.RamEssentials.utils.TeleportUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;

@CommandAlias("warp")
public class WarpCommand extends BaseCommand {

    private final RamEssentials plugin;

    public WarpCommand(RamEssentials plugin) {
        this.plugin = plugin;
    }

    @Default
    @CommandCompletion("@warps")
    @Description("Teleport to warp location.")
    public void warp(CommandSender sender, @Optional String name) {
        Player player = (Player) sender;

        if (name == null) {
            if (plugin.getWarps().getWarpList().isEmpty()) {
                player.sendMessage(ColorUtils.colorMessage("&cThere are no warps on this server!"));
                return;
            }
            for (String s : plugin.getWarps().getWarpList()) {
                player.sendMessage(s);
            }
            return;
        }

        if (plugin.getWarps().getWarpList().contains(name)) {
            player.sendMessage(ColorUtils.colorMessage("&eTeleport to warp &d" + name));
            TeleportUtils.teleport(player, plugin.getWarps().warpLocation(name));
            return;
        }
        player.sendMessage(ColorUtils.colorMessage("&cThat warp does not exist!"));
    }

    @Subcommand("set")
    @CommandAlias("setwarp")
    @CommandPermission("ramessentials.setwarp")
    @Description("Set a new warp.")
    public void setWarp(CommandSender sender, String name) {
        Player player = (Player) sender;
        if (plugin.getWarps().getWarpList().contains(name)) {
            player.sendMessage(ColorUtils.colorMessage("&d" + name + " &ealready exists!"));
            return;
        } else if (name.equalsIgnoreCase("list")) {
            player.sendMessage(ColorUtils.colorMessage("&d" + name + " &ecannot be used!"));
            return;
        }
        plugin.getWarps().addWarp(name, player.getLocation());
        player.sendMessage(ColorUtils.colorMessage("&eSet warp &d" + name + " &eto this location."));
    }

    @Subcommand("del")
    @CommandAlias("delwarp")
    @CommandCompletion("@warps")
    @Description("Delete a warp.")
    @CommandPermission("ramessentials.delwarp")
    public void delWarp(CommandSender sender, String warpName) {
        Player player = (Player) sender;
        if (plugin.getWarps().getWarpList().contains(warpName)) {
            player.sendMessage(ColorUtils.colorMessage("&eDeleted warp &d" + warpName));
            plugin.getWarps().delWarp(warpName);
            return;
        }
        player.sendMessage(ColorUtils.colorMessage("&cThat warp does not exist!"));
    }
}
