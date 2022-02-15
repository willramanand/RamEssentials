package com.gmail.willramanand.RamEssentials.commands;


import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.utils.ColorUtils;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;

@CommandAlias("suicide")
public class SuicideCommand extends BaseCommand {

    private final RamEssentials plugin;

    public SuicideCommand(RamEssentials plugin) {
        this.plugin = plugin;
    }

    @Default
    @Description("Kills you")
    public void suicide(Player player) {
        final EntityDamageEvent ede = new EntityDamageEvent(player, EntityDamageEvent.DamageCause.MAGIC, player.getHealth());
        plugin.getServer().getPluginManager().callEvent(ede);

        if (ede.isCancelled()) return;

        player.setHealth(0);
        player.damage(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
        player.sendMessage(ColorUtils.colorMessage("&eKilled yourself!"));
    }
}
