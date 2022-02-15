package com.gmail.willramanand.RamEssentials.commands;


import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.utils.ColorUtils;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;

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
        final EntityDamageEvent ede = new EntityDamageEvent(player, EntityDamageEvent.DamageCause.MAGIC, player.getHealth());
        plugin.getServer().getPluginManager().callEvent(ede);

        if (ede.isCancelled()) return;

        player.setHealth(0);
        sender.sendMessage(ColorUtils.colorMessage("&eKilled &d" + player.getName()));
    }
}
