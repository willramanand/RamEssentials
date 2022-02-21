package com.gmail.willramanand.RamEssentials.commands;

import co.aikar.commands.BaseCommand;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.player.EPlayer;
import com.gmail.willramanand.RamEssentials.utils.Txt;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public abstract class RBaseCommand extends BaseCommand {

    protected EPlayer getData(Player player) {
        return RamEssentials.getInstance().getPlayerManager().getPlayerData(player);
    }

    protected EPlayer getData(UUID uuid) {
        return RamEssentials.getInstance().getPlayerManager().getPlayerData(uuid);
    }

    protected void msg(CommandSender sender, String message) {
        sender.sendMessage(Txt.parse(message));
    }

    protected void msg(CommandSender sender, String... messages) {
        sender.sendMessage(Txt.parse(messages));
    }

    protected void msg(CommandSender sender, TextComponent component) {
        sender.sendMessage(component);
    }
}
