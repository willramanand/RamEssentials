package com.gmail.willramanand.RamEssentials.commands;

import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.player.EPlayer;
import com.gmail.willramanand.RamEssentials.utils.EasyComponent;
import com.gmail.willramanand.RamEssentials.utils.TeleportUtils;
import com.gmail.willramanand.RamEssentials.utils.Txt;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CmdHome extends EssCommand {

    public CmdHome(RamEssentials plugin) {
        super(plugin, true, true, null, 0, 1);
    }

    @Override
    public void perform(CommandContext context) {
        EPlayer ePlayer = context.ePlayer;
        String name = context.argAsString(0);

        if (name == null) {
            if (ePlayer.getHomeList().isEmpty()) {
                context.msg("{w}You have no homes to list!");
            } else {
                context.msg(Txt.header("HOMES"));
                for (String s : ePlayer.getHomeList()) {
                    EasyComponent component = new EasyComponent(s);
                    context.msg(component.clickEvent("/home " + s).hoverEvent("{s}Click to teleport to {h}" + s + "{s}!").get());
                }
            }
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

        context.msg("{s}Teleporting to home {h}" + name);
        TeleportUtils.teleport(context.player, ePlayer.getHome(name));
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
