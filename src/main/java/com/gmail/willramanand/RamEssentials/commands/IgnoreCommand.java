package com.gmail.willramanand.RamEssentials.commands;

import co.aikar.commands.annotation.*;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.player.EPlayer;
import org.bukkit.entity.Player;

@CommandAlias("ignore")
public class IgnoreCommand extends RBaseCommand {

    private final RamEssentials plugin;

    public IgnoreCommand(RamEssentials plugin) {
        this.plugin = plugin;
    }

    @Default
    @CommandCompletion("@players")
    @Description("Prevent another player from sending requests and messages")
    public void ignore(Player player, @Flags("other") Player playerOther) {
        EPlayer ePlayer = getData(player);

        if (ePlayer.getIgnoredPlayers().contains(playerOther.getUniqueId())) {
            ePlayer.getIgnoredPlayers().remove(playerOther.getUniqueId());
            msg(player, "{s}You are no longer ignoring {h}" + playerOther.getName());
        } else {
            ePlayer.getIgnoredPlayers().add(playerOther.getUniqueId());
            msg(player, "{s}You are now ignoring {h}" + playerOther.getName());
        }
    }
}
