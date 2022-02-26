package com.gmail.willramanand.RamEssentials.commands;

import com.gmail.willramanand.RamEssentials.RamEssentials;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityRegainHealthEvent;

import java.util.ArrayList;
import java.util.List;

public class CmdHeal extends EssCommand {
    
    public CmdHeal(RamEssentials plugin) {
        super(plugin, true, true, 0, 1);
    }

    @Override
    public void perform(CommandContext context) {
        Player player = context.argAsPlayer(0, context.player);
        final EntityRegainHealthEvent erhe = new EntityRegainHealthEvent(player, player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue(), EntityRegainHealthEvent.RegainReason.MAGIC);
        plugin.getServer().getPluginManager().callEvent(erhe);
        if (erhe.isCancelled()) return;

        player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
        player.setFoodLevel(20);
        player.setFireTicks(0);
        
        if (player == context.player) {
            context.msg("{s}You have been {green}healed{s}.");
        } else {
            context.msg("{h}" + player.getName() + " {s}has been {green}healed{s}.");
            context.msgOther(player, "{s}You have been {green}healed{s}.");
        }
    }

    @Override
    public List<String> tabCompletes(CommandSender sender, String[] args) {
        if (args.length == 1) return tabCompletePlayers();
        return new ArrayList<>();
    }
}
