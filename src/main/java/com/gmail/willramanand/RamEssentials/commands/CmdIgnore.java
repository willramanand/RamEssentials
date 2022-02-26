package com.gmail.willramanand.RamEssentials.commands;

import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.player.EPlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CmdIgnore extends EssCommand {

    public CmdIgnore(RamEssentials plugin) {
        super(plugin, true, true, null, 1, 1);
    }

    @Override
    public void perform(CommandContext context) {
        EPlayer ePlayer = context.ePlayer;
        Player playerOther = context.argAsPlayer(0);

        if (playerOther == null) {
            context.msg("{w}That player does not exist or is not online!");
            return;
        }

        if (ePlayer.getIgnoredPlayers().contains(playerOther.getUniqueId())) {
            ePlayer.getIgnoredPlayers().remove(playerOther.getUniqueId());
            context.msg("{s}You are no longer ignoring {h}" + playerOther.getName());
        } else {
            ePlayer.getIgnoredPlayers().add(playerOther.getUniqueId());
            context.msg("{s}You are now ignoring {h}" + playerOther.getName());
        }
    }

    @Override
    public List<String> tabCompletes(CommandSender sender, String[] args) {
        if (args.length == 1) return tabCompletePlayers();
        return new ArrayList<>();
    }
}
