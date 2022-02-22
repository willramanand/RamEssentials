package com.gmail.willramanand.RamEssentials.utils;

import com.gmail.willramanand.RamEssentials.RamEssentials;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.util.Scanner;

public class TxtReader {

    public static void setup() {
        File motd = new File(RamEssentials.getInstance().getDataFolder().getPath() + "/motd.txt");

        if (!motd.exists()) {
            RamEssentials.getInstance().saveResource("motd.txt", false);
        }

        File rules = new File(RamEssentials.getInstance().getDataFolder().getPath() + "/rules.txt");

        if (!rules.exists()) {
            RamEssentials.getInstance().saveResource("rules.txt", false);
        }

    }

    public static void sendMotd(Player player) {
        File file = new File(RamEssentials.getInstance().getDataFolder().getPath() + "/motd.txt");

        try {
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                player.sendMessage(Txt.parse(replace(sc.nextLine(), player)));
            }
        } catch (FileNotFoundException e) {
            RamEssentials.getInstance().getLogger().info("motd.txt not found!");
        }
    }

    public static void sendRules(Player player) {
        File file = new File(RamEssentials.getInstance().getDataFolder().getPath() + "/rules.txt");

        player.sendMessage(Txt.header("RULES"));
        try {
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                player.sendMessage(Txt.parse(sc.nextLine()));
            }
        } catch (FileNotFoundException e) {
            RamEssentials.getInstance().getLogger().info("rules.txt not found!");
        }
    }

    private static String replace(String originalString, Player player) {
        String formattedString = originalString;

        if (originalString.contains("{PLAYER}")) {
            formattedString = formattedString.replace("{PLAYER}", player.getName());
        }

        if (originalString.contains("{ONLINE}")) {
            formattedString = formattedString.replace("{ONLINE}", String.valueOf(Bukkit.getOnlinePlayers().size()));
        }

        if (originalString.contains("{WORLDTIME12}")) {
            long seconds = player.getWorld().getTime();
            LocalTime timeOfDay = LocalTime.ofSecondOfDay(seconds);
            String time = timeOfDay.toString();
            formattedString = formattedString.replace("{WORLDTIME12}", time);
        }

        return formattedString;
    }
}
