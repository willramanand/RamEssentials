package com.gmail.willramanand.RamEssentials.commands;

import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.player.EPlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CmdDelHome extends EssCommand {

    public CmdDelHome(RamEssentials plugin) {
        super(plugin, true, true, null, 0, 1);
    }

    @Override
    public void perform(CommandContext context) {
        EPlayer ePlayer = context.ePlayer;
        String name = context.argAsString(0);

        if (name == null) {
            context.msg("{w}No name entered!");
            return;
        }

        if (ePlayer.getHomeList().size() == 0) {
            context.msg("{w}You have no set homes!");
            return;
        }

        if (!(ePlayer.getHomeList().contains(name))) {
            context.msg("{w}That is not one of your set homes!");
            return;
        }

        ePlayer.delHome(name);
        context.msg("{s}You have deleted the home {h}" + name);
    }

    @Override
    public List<String> tabCompletes(CommandSender sender, String[] args) {
        if (args.length == 1 && sender instanceof Player) {
            Player player = (Player) sender;
            return new ArrayList<>(plugin.getPlayerManager().getPlayerData(player).getHomeList());
        }
        return new ArrayList<>();
    }
}
