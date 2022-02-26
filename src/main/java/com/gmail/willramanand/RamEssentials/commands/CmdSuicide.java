package com.gmail.willramanand.RamEssentials.commands;

import com.gmail.willramanand.RamEssentials.RamEssentials;
import org.bukkit.command.CommandSender;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.List;

public class CmdSuicide extends EssCommand {

    public CmdSuicide(RamEssentials plugin) {
        super(plugin, true, true, null, 0, 0);
    }

    @Override
    public void perform(CommandContext context) {
        final EntityDamageEvent ede = new EntityDamageEvent(context.player, EntityDamageEvent.DamageCause.MAGIC, context.player.getHealth());
        plugin.getServer().getPluginManager().callEvent(ede);

        if (ede.isCancelled()) return;

        context.player.setHealth(0);
        context.msg("{w}Killed yourself!");
    }

    @Override
    public List<String> tabCompletes(CommandSender sender, String[] args) {
        return null;
    }
}
