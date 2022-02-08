package com.gmail.willramanand.RamEssentials.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.player.EPlayer;
import com.gmail.willramanand.RamEssentials.utils.ColorUtils;
import org.bukkit.entity.Player;

@CommandAlias("ignore")
public class IgnoreCommand extends BaseCommand {

    private final RamEssentials plugin;

    public IgnoreCommand(RamEssentials plugin) {
        this.plugin = plugin;
    }

    @Default
    @CommandCompletion("@players")
    @Description("Prevent another player from sending requests and messages")
    public void ignore(Player player, @Flags("other") Player playerOther) {
        EPlayer ePlayer = plugin.getPlayerManager().getPlayerData(player);

        if (ePlayer.getIgnoredPlayers().contains(playerOther.getUniqueId())) {
            ePlayer.getIgnoredPlayers().remove(playerOther.getUniqueId());
            player.sendMessage(ColorUtils.colorMessage("&eYou are no longer ignoring &d" + playerOther.getName()));
        } else {
            ePlayer.getIgnoredPlayers().add(playerOther.getUniqueId());
            player.sendMessage(ColorUtils.colorMessage("&eYou are now ignoring &d" + playerOther.getName()));
        }
    }
}
