package com.gmail.willramanand.RamEssentials.commands;


import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.utils.ColorUtils;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("killplayer|killp")
public class KillPlayerCommand extends BaseCommand {

    private final RamEssentials plugin;

    public KillPlayerCommand(RamEssentials plugin) {
        this.plugin = plugin;
    }

    @Default
    @Description("Kills player")
    @CommandPermission("ramessentials.killplayer")
    public void killPlayer(CommandSender sender, @Flags("other")Player player) {
        player.damage(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
        sender.sendMessage(ColorUtils.colorMessage("&eKilled &d" + player.getName()));
    }
}
