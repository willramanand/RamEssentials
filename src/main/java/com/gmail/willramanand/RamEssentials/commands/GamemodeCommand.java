package com.gmail.willramanand.RamEssentials.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.Subcommand;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.utils.ColorUtils;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

@CommandAlias("gamemode|gm")
@CommandPermission("ramessentials.gamemode")
@Description("Switch you gamemode")
public class GamemodeCommand extends BaseCommand {

    private final RamEssentials plugin;

    public GamemodeCommand(RamEssentials plugin) {
        this.plugin = plugin;
    }

    @Subcommand("survival|s|0")
    public void survival(Player player) {
        player.setGameMode(GameMode.SURVIVAL);
        player.sendMessage(ColorUtils.colorMessage("&eGamemode set to &dSURVIVAL&e."));
    }

    @Subcommand("creative|c|1")
    public void creative(Player player) {
        player.setGameMode(GameMode.CREATIVE);
        player.sendMessage(ColorUtils.colorMessage("&eGamemode set to &dCREATIVE&e."));
    }

    @Subcommand("adventure|a|2")
    public void adventure(Player player) {
        player.setGameMode(GameMode.ADVENTURE);
        player.sendMessage(ColorUtils.colorMessage("&eGamemode set to &dADVENTURE&e."));
    }

    @Subcommand("spectator|sp|3")
    public void spectator(Player player) {
        player.setGameMode(GameMode.SPECTATOR);
        player.sendMessage(ColorUtils.colorMessage("&eGamemode set to &dSPECTATOR&e."));
    }
}
