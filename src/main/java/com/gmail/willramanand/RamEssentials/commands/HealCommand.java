package com.gmail.willramanand.RamEssentials.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.utils.ColorUtils;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityRegainHealthEvent;

@CommandAlias("heal")
@CommandPermission("ramessentials.heal")
@Description("Heal yourself to max health and hunger")
public class HealCommand extends BaseCommand {

    private final RamEssentials plugin;

    public HealCommand(RamEssentials plugin) {
        this.plugin = plugin;
    }

    @Default
    public void heal(Player player) {

        final EntityRegainHealthEvent erhe = new EntityRegainHealthEvent(player, player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue(), EntityRegainHealthEvent.RegainReason.MAGIC);
        plugin.getServer().getPluginManager().callEvent(erhe);
        if (erhe.isCancelled()) return;

        player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
        player.setFoodLevel(20);
        player.setFireTicks(0);
        player.sendMessage(ColorUtils.colorMessage("&eYou have been &ahealed&e."));
    }
}
