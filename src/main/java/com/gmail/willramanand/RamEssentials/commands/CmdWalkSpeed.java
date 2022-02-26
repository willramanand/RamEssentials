package com.gmail.willramanand.RamEssentials.commands;

import com.gmail.willramanand.RamEssentials.RamEssentials;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CmdWalkSpeed extends EssCommand {

    public CmdWalkSpeed(RamEssentials plugin) {
        super(plugin, true, true, 0, 1);
    }

    @Override
    public void perform(CommandContext context) {

        float speed = context.argAsFloat(0);

        if (speed > 1.0f || speed < -1.0f) {
            context.msg("{w}Invalid speed! Must be between -1.0 and 1.0");
            return;
        }

        if (speed == 0.2f) {
            context.msg("{s}Set to default speed!");
        }
        context.player.setWalkSpeed(speed);
    }

    @Override
    public List<String> tabCompletes(CommandSender sender, String[] args) {
        if (args.length == 1) return new ArrayList<>(Arrays.asList("-1.0", "0.2", "1.0"));
        return new ArrayList<>();
    }
}
