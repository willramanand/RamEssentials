package com.gmail.willramanand.RamEssentials.commands;

import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.Subcommand;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

@CommandAlias("gamemode|gm")
@CommandPermission("ramessentials.gamemode")
@Description("Switch your gamemode")
public class GamemodeCommand extends RBaseCommand {

    private final RamEssentials plugin;

    public GamemodeCommand(RamEssentials plugin) {
        this.plugin = plugin;
    }

    @Subcommand("survival|s|0")
    public void survival(Player player) {
        player.setGameMode(GameMode.SURVIVAL);
        msg(player, "{s}Gamemode set to {h}SURVIVAL{s}.");
    }

    @Subcommand("creative|c|1")
    public void creative(Player player) {
        player.setGameMode(GameMode.CREATIVE);
        msg(player, "{s}Gamemode set to {h}CREATIVE{s}.");
    }

    @Subcommand("adventure|a|2")
    public void adventure(Player player) {
        player.setGameMode(GameMode.ADVENTURE);
        msg(player, "{s}Gamemode set to {h}ADVENTURE{s}.");
    }

    @Subcommand("spectator|sp|3")
    public void spectator(Player player) {
        player.setGameMode(GameMode.SPECTATOR);
        msg(player, "{s}Gamemode set to {h}SPECTATOR{s}.");
    }
}
