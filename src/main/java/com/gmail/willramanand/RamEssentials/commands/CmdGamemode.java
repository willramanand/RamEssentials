package com.gmail.willramanand.RamEssentials.commands;

import com.gmail.willramanand.RamEssentials.RamEssentials;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CmdGamemode extends EssCommand {

    public CmdGamemode(RamEssentials plugin) {
        super(plugin, true, true, 1, 1);
    }

    @Override
    public void perform(CommandContext context) {
        String gm = context.argAsString(0);

        GameMode selected = null;
        switch (gm) {
            case "s", "survival", "0" -> selected = GameMode.SURVIVAL;
            case "c", "creative", "1" -> selected = GameMode.CREATIVE;
            case "a", "adventure", "2" -> selected = GameMode.ADVENTURE;
            case "sp", "spectator", "3" -> selected = GameMode.SPECTATOR;
        }

        if (selected == null) {
            context.msg("{w}You did not select a valid gamemode!");
            return;
        }

        context.player.setGameMode(selected);
        context.msg("{s}Gamemode has been set to {h}" + selected.name() + "{s}.");
    }

    @Override
    public List<String> tabCompletes(CommandSender sender, String[] args) {
        return new ArrayList<>(Arrays.asList("survival", "creative", "adventure", "spectator", "s", "c", "a", "sp", "0", "1", "2", "3"));
    }
}
