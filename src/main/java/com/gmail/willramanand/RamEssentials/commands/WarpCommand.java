package com.gmail.willramanand.RamEssentials.commands;

import co.aikar.commands.annotation.*;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.utils.EasyComponent;
import com.gmail.willramanand.RamEssentials.utils.TeleportUtils;
import com.gmail.willramanand.RamEssentials.utils.Txt;
import org.bukkit.entity.Player;

@CommandAlias("warp")
public class WarpCommand extends RBaseCommand {

    private final RamEssentials plugin;

    public WarpCommand(RamEssentials plugin) {
        this.plugin = plugin;
    }

    @Default
    @CommandCompletion("@warps")
    @Description("Teleport to warp location.")
    public void warp(Player player, @Optional String name) {
        if (name == null) {
            if (plugin.getWarps().getWarpList().isEmpty()) {
                msg(player,"{w}There are no warps on this server!");
                return;
            }
            msg(player, Txt.header("WARPS"));
            for (String s : plugin.getWarps().getWarpList()) {
                EasyComponent component = new EasyComponent(s);
                msg(player, component.clickEvent("/warp " + s).hoverEvent("{s}Click to warp to {h}" + s + "{s}!").get());
            }
            return;
        }

        if (plugin.getWarps().getWarpList().contains(name)) {
            msg(player, "{s}Teleporting to warp {h}" + name);
            TeleportUtils.teleport(player, plugin.getWarps().warpLocation(name));
            return;
        }
        msg(player, "{w}That warp does not exist!");
    }

    @Subcommand("set")
    @CommandAlias("setwarp")
    @CommandPermission("ramessentials.setwarp")
    @Description("Set a new warp.")
    public void setWarp(Player player, String name) {
        if (plugin.getWarps().getWarpList().contains(name)) {
            msg(player, "{h}" + name + " {s}already exists!");
            return;
        }
        plugin.getWarps().addWarp(name, player.getLocation());
        msg(player, "{s}Set warp {h}" + name + " {s}to this location.");
    }

    @Subcommand("del")
    @CommandAlias("delwarp")
    @CommandCompletion("@warps")
    @Description("Delete a warp.")
    @CommandPermission("ramessentials.delwarp")
    public void delWarp(Player player, String warpName) {
        if (plugin.getWarps().getWarpList().contains(warpName)) {
            msg(player, "{s}Deleted warp {h}" + warpName);
            plugin.getWarps().delWarp(warpName);
            return;
        }
        msg(player, "{w}That warp does not exist!");
    }
}
