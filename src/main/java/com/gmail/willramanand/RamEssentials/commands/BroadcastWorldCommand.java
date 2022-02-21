package com.gmail.willramanand.RamEssentials.commands;

import co.aikar.commands.annotation.*;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.utils.Txt;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("broadcastworld|bcw")
public class BroadcastWorldCommand extends RBaseCommand {

    private final RamEssentials plugin;

    public BroadcastWorldCommand(RamEssentials plugin) {
        this.plugin = plugin;
    }

    @Default
    @Description("Broadcast message to all players in world.")
    @CommandCompletion("@worlds")
    @CommandPermission("ramessentials.broadcast")
    public void broadcastWorld(CommandSender sender, World world, String message) {

        if (message.equalsIgnoreCase("")) {
            msg(sender,"{w}You cannot broadcast an empty message.");
            return;
        }

        for (Player player : world.getPlayers()) {
            msg(player, message);
        }
        plugin.getLogger().info(Txt.parse("{gold}Broadcasted to {h}" + world.getName() + "{gold}: {white}" + message));
    }

}
