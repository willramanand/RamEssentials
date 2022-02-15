package com.gmail.willramanand.RamEssentials.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.player.EPlayer;
import com.gmail.willramanand.RamEssentials.utils.ColorUtils;
import com.gmail.willramanand.RamEssentials.utils.TeleportUtils;
import org.bukkit.entity.Player;

public class HomeCommand extends BaseCommand {

    private final RamEssentials plugin;

    public HomeCommand(RamEssentials plugin) {
        this.plugin = plugin;
    }

    @CommandAlias("home")
    @CommandCompletion("@homes")
    @Description("Teleport to one of your homes.")
    public void tpHome(Player player, @Optional String name) {
        EPlayer ePlayer = plugin.getPlayerManager().getPlayerData(player);

        if (name == null) {
            if (ePlayer.getHomeList().isEmpty()) {
                player.sendMessage(ColorUtils.colorMessage("&cYou have no homes to list!"));
            } else {
                player.sendMessage(ColorUtils.colorMessage("&6---- &bHomes &6----"));
                for (String s : ePlayer.getHomeList()) {
                    player.sendMessage(s);
                }
            }
            return;
        }

        if (ePlayer.getHomeList().size() == 0) {
            player.sendMessage(ColorUtils.colorMessage("&cYou have no set homes!"));
            return;
        }

        if (!(ePlayer.getHomeList().contains(name))) {
            player.sendMessage(ColorUtils.colorMessage("&cThat is not one of your set homes!"));
            return;
        }

        player.sendMessage(ColorUtils.colorMessage("&eTeleporting to home &d" + name));
        TeleportUtils.teleport(player, ePlayer.getHome(name));
    }

    @CommandAlias("sethome")
    @Description("Set a new home.")
    public void setHome(Player player, String name) {
        EPlayer ePlayer = plugin.getPlayerManager().getPlayerData(player);

        if (name.equalsIgnoreCase("")) {
            player.sendMessage(ColorUtils.colorMessage("&cNo name entered!"));
            return;
        }

        if (ePlayer.getHomeList().contains(name)) {
            ePlayer.delHome(name);
        }

        if (ePlayer.getHomeList().size() == plugin.getHouseLimit()) {
            player.sendMessage(ColorUtils.colorMessage("&eYou already have the max amount of homes!"));
            return;
        }

        ePlayer.addHome(name, player.getLocation());
        player.sendMessage(ColorUtils.colorMessage("&eYou have set a home named &d" + name + " &eat this location."));
    }

    @CommandAlias("delhome")
    @CommandCompletion("@homes")
    @Description("Delete a home.")
    public void delHome(Player player, String name) {
        EPlayer ePlayer = plugin.getPlayerManager().getPlayerData(player);

        if (name.equalsIgnoreCase("")) {
            player.sendMessage(ColorUtils.colorMessage("&cNo name entered!"));
            return;
        }

        if (ePlayer.getHomeList().size() == 0) {
            player.sendMessage(ColorUtils.colorMessage("&cYou have no set homes!"));
            return;
        }

        if (!(ePlayer.getHomeList().contains(name))) {
            player.sendMessage(ColorUtils.colorMessage("&cThat is not one of your set homes!"));
            return;
        }

        ePlayer.delHome(name);
        player.sendMessage(ColorUtils.colorMessage("&eYou have deleted the home &d" + name));
    }
}
