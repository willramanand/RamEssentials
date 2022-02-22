package com.gmail.willramanand.RamEssentials.commands;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.Optional;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.player.EPlayer;
import com.gmail.willramanand.RamEssentials.utils.EasyComponent;
import com.gmail.willramanand.RamEssentials.utils.TeleportUtils;
import com.gmail.willramanand.RamEssentials.utils.Txt;
import org.bukkit.entity.Player;

public class HomeCommand extends RBaseCommand {

    private final RamEssentials plugin;

    public HomeCommand(RamEssentials plugin) {
        this.plugin = plugin;
    }

    @CommandAlias("home")
    @CommandCompletion("@homes")
    @Description("Teleport to one of your homes.")
    public void tpHome(Player player, @Optional String name) {
        EPlayer ePlayer = getData(player);

        if (name == null) {
            if (ePlayer.getHomeList().isEmpty()) {
                msg(player, "{w}You have no homes to list!");
            } else {
                msg(player, Txt.header("HOMES"));
                for (String s : ePlayer.getHomeList()) {
                    EasyComponent component = new EasyComponent(s);
                    msg(player, component.clickEvent("/home " + s).hoverEvent("{s}Click to teleport to {h}" + s + "{s}!").get());
                }
            }
            return;
        }

        if (ePlayer.getHomeList().size() == 0) {
            msg(player, "{w}You have no set homes!");
            return;
        }

        if (!(ePlayer.getHomeList().contains(name))) {
            msg(player, "{w}That is not one of your set homes!");
            return;
        }

        msg(player, "{s}Teleporting to home {h}" + name);
        TeleportUtils.teleport(player, ePlayer.getHome(name));
    }

    @CommandAlias("sethome")
    @Description("Set a new home.")
    public void setHome(Player player, String name) {
        EPlayer ePlayer = getData(player);

        if (name.equalsIgnoreCase("")) {
            msg(player, "{w}No name entered!");
            return;
        }

        if (ePlayer.getHomeList().contains(name)) {
            ePlayer.delHome(name);
        }

        if (ePlayer.getHomeList().size() == plugin.getHouseLimit()) {
            msg(player,"{s}You already have the max amount of homes!");
            return;
        }

        ePlayer.addHome(name, player.getLocation());
        msg(player, "{s}You have set a home named {h}" + name + " {s}at this location.");
    }

    @CommandAlias("delhome")
    @CommandCompletion("@homes")
    @Description("Delete a home.")
    public void delHome(Player player, String name) {
        EPlayer ePlayer = getData(player);

        if (name.equalsIgnoreCase("")) {
            msg(player, "{w}No name entered!");
            return;
        }

        if (ePlayer.getHomeList().size() == 0) {
            msg(player, "{w}You have no set homes!");
            return;
        }

        if (!(ePlayer.getHomeList().contains(name))) {
            msg(player, "{w}That is not one of your set homes!");
            return;
        }

        ePlayer.delHome(name);
        msg(player, "{s}You have deleted the home {h}" + name);
    }
}
