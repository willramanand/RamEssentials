package com.gmail.willramanand.RamEssentials.commands;

import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.player.EPlayer;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CmdMuteTemp extends EssCommand {

    public CmdMuteTemp(RamEssentials plugin) {
        super(plugin, true, false, 1, -1);
    }

    @Override
    public void perform(CommandContext context) {
        EPlayer ePlayer = context.argAsEPlayer(0);
        int seconds = context.argAsInt(1, 60);
        String reason = context.argAsConcatString(2, "Muted by OP.");

        if (ePlayer == null) {
            context.msg("{w}That player does not exist or is not online!");
            return;
        }

        if (ePlayer.isMuted()) {
            context.msg("{w}Player is already muted!");
            return;
        }

        ePlayer.setMuted(true);
        ePlayer.setMuteReason(reason);
        ePlayer.setMuteTime(seconds);

        context.msg("{s}You have muted the player {h}" + ePlayer.getPlayer().getName() + " {s}for {h}" + seconds + " {s}seconds.");
        context.msgOther(ePlayer.getPlayer(), "{s}You have been muted for {h}" + seconds + " {s}seconds.");
    }

    @Override
    public List<String> tabCompletes(CommandSender sender, String[] args) {
        if (args.length == 1) return tabCompletePlayers();
        if (args.length == 2) return new ArrayList<>(Arrays.asList("30", "60", "90"));
        return new ArrayList<>();
    }
}
