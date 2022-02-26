package com.gmail.willramanand.RamEssentials.commands;

import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.player.EPlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CmdUnmute extends EssCommand {

    public CmdUnmute(RamEssentials plugin) {
        super(plugin, true, false, 1, 1);
    }

    @Override
    public void perform(CommandContext context) {
        Player player = context.argAsPlayer(0);

        if (player == null) {
            context.msg("{w}That player does not exist or is not online!");
            return;
        }

        EPlayer ePlayer = plugin.getPlayerManager().getPlayerData(player);

        if (!(ePlayer.isMuted())) {
            context.msg("{w}Player is not muted!");
            return;
        }

        ePlayer.setMuted(false);
        ePlayer.setMuteReason(null);
        ePlayer.setMuteExpire(null);

        context.msg("{s}You have unmuted the player {h}" + player.getName() + "{s}.");
        context.msgOther(player, "{s}You have been unmuted.");
    }

    @Override
    public List<String> tabCompletes(CommandSender sender, String[] args) {
        if (args.length == 1) return tabCompletePlayers();
        return new ArrayList<>();
    }
}
