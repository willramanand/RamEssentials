package com.gmail.willramanand.RamEssentials.commands;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.player.EPlayer;
import org.bukkit.entity.Player;

@CommandAlias("dnd|donotdisturb")
public class DNDCommand extends RBaseCommand {

    private final RamEssentials plugin;

    public DNDCommand(RamEssentials plugin) {
        this.plugin = plugin;
    }

    @Default
    @Description("Toggle to prevent direct messages")
    public void dnd(Player player) {
        EPlayer ePlayer = getData(player);

        if (ePlayer.isDoNotDisturb()) {
            ePlayer.setDoNotDisturb(false);
            msg(player,"{s}Do not disturb {w}disabled{s}.");
        } else {
            ePlayer.setDoNotDisturb(true);
            msg(player,"{s}Do not disturb {green}enabled{s}.");
        }
    }
}
