package com.gmail.willramanand.RamEssentials.commands;


import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.utils.ColorUtils;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("suicide")
public class SuicideCommand extends BaseCommand {

    private final RamEssentials plugin;

    public SuicideCommand(RamEssentials plugin) {
        this.plugin = plugin;
    }

    @Default
    @Description("Kills you")
    public void suicide(CommandSender sender) {
        Player player = (Player) sender;
        player.damage(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
        sender.sendMessage(ColorUtils.colorMessage("&eKilled &d" + player.getName()));
    }
}
