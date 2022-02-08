package com.gmail.willramanand.RamEssentials.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.gmail.willramanand.RamEssentials.data.MuteType;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.player.EPlayer;
import com.gmail.willramanand.RamEssentials.utils.ColorUtils;
import com.gmail.willramanand.RamEssentials.utils.MuteTimer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("mute")
public class MuteCommand extends BaseCommand {

    private final RamEssentials plugin;

    public MuteCommand(RamEssentials plugin) {
        this.plugin = plugin;
    }

    @Default
    @CommandPermission("ramessentials.mute")
    @CommandCompletion("@players")
    @Description("Permanently mute a user")
    public void permaMute(CommandSender sender, @Flags("other") Player player, @Default("Muted by operator") String reason) {
        EPlayer ePlayer = plugin.getPlayerManager().getPlayerData(player);

        if (ePlayer == null) {
            sender.sendMessage(ColorUtils.colorMessage("&cPlayer does not exist or is not online!"));
        }

        if (ePlayer.isMuted()) {
            sender.sendMessage(ColorUtils.colorMessage("&cPlayer is already muted!"));
            return;
        }

        ePlayer.setMuted(true);
        ePlayer.setMuteReason(reason);
        ePlayer.setMuteType(MuteType.PERM);

        sender.sendMessage(ColorUtils.colorMessage("&eYou have muted the player &d" + player.getName() + " &epermanently."));
        player.sendMessage(ColorUtils.colorMessage("&eYou have been muted &dPERMANENTLY&e."));
    }

    @Subcommand("temp|t")
    @CommandAlias("mutetemp")
    @CommandPermission("ramessentials.mute")
    @CommandCompletion("@players")
    @Description("Temporarily mute a player")
    public void tempMute(CommandSender sender, @Flags("other") Player player, @Default("60") int seconds, @Default("Muted by operator") String reason) {
        EPlayer ePlayer = plugin.getPlayerManager().getPlayerData(player);

        if (ePlayer == null) {
            sender.sendMessage(ColorUtils.colorMessage("&cPlayer does not exist or is not online!"));
        }

        if (ePlayer.isMuted()) {
            sender.sendMessage(ColorUtils.colorMessage("&cPlayer is already muted!"));
            return;
        }

        ePlayer.setMuted(true);
        ePlayer.setMuteReason(reason);
        ePlayer.setMuteType(MuteType.TEMP);
        plugin.getTempMutedPlayers().add(ePlayer.getUuid());
        MuteTimer.runTimer(ePlayer, seconds);

        sender.sendMessage(ColorUtils.colorMessage("&eYou have muted the player &d" + player.getName() + " &efor &d" + seconds + "&eseconds."));
        player.sendMessage(ColorUtils.colorMessage("&eYou have been muted for &d" + seconds + " &eseconds."));
    }

    @Subcommand("remove|r")
    @CommandAlias("unmute")
    @CommandPermission("ramessentials.unmute")
    @CommandCompletion("@players")
    @Description("Unmutes a muted player.")
    public void removeMute(CommandSender sender, @Flags("other") Player player) {
        EPlayer ePlayer = plugin.getPlayerManager().getPlayerData(player);

        if (ePlayer == null) {
            sender.sendMessage(ColorUtils.colorMessage("&cPlayer does not exist or is not online!"));
        }

        if (!(ePlayer.isMuted())) {
            sender.sendMessage(ColorUtils.colorMessage("&cPlayer is not muted!"));
            return;
        }

        if (ePlayer.getMuteType() == MuteType.TEMP) {
            plugin.getTempMutedPlayers().remove(ePlayer.getUuid());
        }

        ePlayer.setMuted(false);
        ePlayer.setMuteReason(null);
        ePlayer.setMuteType(null);

        sender.sendMessage(ColorUtils.colorMessage("&eYou have unmuted the player &d" + player.getName() + "&e."));
        player.sendMessage(ColorUtils.colorMessage("&eYou have been unmuted."));
    }
}
