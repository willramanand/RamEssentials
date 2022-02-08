package com.gmail.willramanand.RamEssentials.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.player.EPlayer;
import com.gmail.willramanand.RamEssentials.utils.ColorUtils;
import org.bukkit.entity.Player;

@CommandAlias("dnd|donotdisturb")
public class DNDCommand extends BaseCommand {

    private final RamEssentials plugin;

    public DNDCommand(RamEssentials plugin) {
        this.plugin = plugin;
    }

    @Default
    @Description("Toggle to prevent direct messages")
    public void dnd(Player player) {
        EPlayer ePlayer = plugin.getPlayerManager().getPlayerData(player);

        if (ePlayer.isDoNotDisturb()) {
            ePlayer.setDoNotDisturb(false);
            player.sendMessage(ColorUtils.colorMessage("&eDo not disturb &cdisabled&e."));
        } else {
            ePlayer.setDoNotDisturb(true);
            player.sendMessage(ColorUtils.colorMessage("&eDo not disturb &aenabled&e."));
        }
    }
}
