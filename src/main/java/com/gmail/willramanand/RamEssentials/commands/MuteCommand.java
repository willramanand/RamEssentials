package com.gmail.willramanand.RamEssentials.commands;

import co.aikar.commands.annotation.*;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.player.EPlayer;
import com.gmail.willramanand.RamEssentials.utils.MuteTimer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Date;

@CommandAlias("mute")
public class MuteCommand extends RBaseCommand {

    private final RamEssentials plugin;

    public MuteCommand(RamEssentials plugin) {
        this.plugin = plugin;
    }

    @Default
    @CommandPermission("ramessentials.mute")
    @CommandCompletion("@players")
    @Description("Permanently mute a user")
    public void permaMute(CommandSender sender, @Flags("other") Player player, @Default("Muted by operator") String reason) {
        EPlayer ePlayer = getData(player);

        if (ePlayer.isMuted()) {
            msg(sender, "{w}Player is already muted!");
            return;
        }

        ePlayer.setMuted(true);
        ePlayer.setMuteReason(reason);

        msg(sender, "{s}You have muted the player {h}" + player.getName() + " {s}permanently.");
        msg(player, "{s}You have been muted {h}PERMANENTLY{s}.");
    }

    @Subcommand("temp|t")
    @CommandAlias("mutetemp")
    @CommandPermission("ramessentials.mute")
    @CommandCompletion("@players")
    @Description("Temporarily mute a player")
    public void tempMute(CommandSender sender, @Flags("other") Player player, @Default("60") int seconds, @Default("Muted by operator") String reason) {
        EPlayer ePlayer = getData(player);

        if (ePlayer.isMuted()) {
            msg(sender, "{w}Player is already muted!");
            return;
        }

        ePlayer.setMuted(true);
        ePlayer.setMuteReason(reason);
        ePlayer.setMuteTime(seconds);

        msg(sender, "{s}You have muted the player {h}" + player.getName() + " {s}for {h}" + seconds + " {s}seconds.");
        msg(player, "{s}You have been muted for {h}" + seconds + " {s}seconds.");
    }

    @Subcommand("remove|r")
    @CommandAlias("unmute")
    @CommandPermission("ramessentials.unmute")
    @CommandCompletion("@players")
    @Description("Unmutes a muted player.")
    public void removeMute(CommandSender sender, @Flags("other") Player player) {
        EPlayer ePlayer = getData(player);

        if (ePlayer == null) {
            msg(sender, "{w}Player does not exist or is not online!");
        }

        if (!(ePlayer.isMuted())) {
            msg(sender, "{w}Player is not muted!");
            return;
        }

        ePlayer.setMuted(false);
        ePlayer.setMuteReason(null);
        ePlayer.setMuteExpire(null);

        msg(sender,"{s}You have unmuted the player {h}" + player.getName() + "{s}.");
        msg(player, "{s}You have been unmuted.");
    }
}
