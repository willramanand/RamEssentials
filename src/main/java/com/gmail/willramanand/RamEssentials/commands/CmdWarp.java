package com.gmail.willramanand.RamEssentials.commands;

import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.utils.EasyComponent;
import com.gmail.willramanand.RamEssentials.utils.TeleportUtils;
import com.gmail.willramanand.RamEssentials.utils.Txt;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class CmdWarp extends EssCommand {

    public CmdWarp(RamEssentials plugin) {
        super(plugin,true, true, null, 0, 1);
    }

    @Override
    public void perform(CommandContext context) {
        String warpName = context.argAsString(0);

        if (warpName == null) {
            if (plugin.getWarps().getWarpList().isEmpty()) {
                context.msg("{w}There are no warps on this server!");
                return;
            }

            context.msg(Txt.header("WARPS"));
            for (String s : plugin.getWarps().getWarpList()) {
                EasyComponent component = new EasyComponent(s);
                context.msg(component.clickEvent("/warp " + s).hoverEvent("{s}Click to warp to {h}" + s + "{s}!").get());
            }
            return;
        }

        if (plugin.getWarps().getWarpList().contains(warpName)) {
            context.msg("{s}Teleporting to warp {h}" + warpName);
            TeleportUtils.teleport(context.player, plugin.getWarps().warpLocation(warpName));
            return;
        }
        context.msg("{w}That warp does not exist!");
    }

    @Override
    public List<String> tabCompletes(CommandSender sender, String[] args) {
        if (args.length == 1) return new ArrayList<>(plugin.getWarps().getWarpList());
        return null;
    }
}
