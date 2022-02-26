package com.gmail.willramanand.RamEssentials.commands;

import com.gmail.willramanand.RamEssentials.RamEssentials;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.ArrayList;
import java.util.List;

public class CmdKillPlayer extends EssCommand {

    public CmdKillPlayer(RamEssentials plugin) {
        super(plugin, true, false, 1, 1);
    }

    @Override
    public void perform(CommandContext context) {
        Player player = context.argAsPlayer(0);

        if (player == null) {
            context.msg("{w}That player does not exist or is not online!");
            return;
        }

        final EntityDamageEvent ede = new EntityDamageEvent(player, EntityDamageEvent.DamageCause.MAGIC, player.getHealth());
        plugin.getServer().getPluginManager().callEvent(ede);

        if (ede.isCancelled()) return;
        player.setHealth(0);
        context.msg("{s}Killed {h}" + player.getName());
    }

    @Override
    public List<String> tabCompletes(CommandSender sender, String[] args) {
        if (args.length == 1) return tabCompletePlayers();
        return new ArrayList<>();
    }
}
