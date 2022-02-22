package com.gmail.willramanand.RamEssentials.utils;

import net.kyori.adventure.text.TextComponent;
import org.bukkit.ChatColor;

import java.util.HashMap;
import java.util.Map;

public class Txt {

    private static final Map<String, String> alternateChars = new HashMap<>();
    private static final char COLOR_CHAR = ChatColor.COLOR_CHAR;

    public static String parse(String message) {
        return replaceAlternateChars(ChatColor.translateAlternateColorCodes('&', message));
    }

    public static String[] parse(String... messages) {
        for (int i = 0; i < messages.length; i++) {
            messages[i] = replaceAlternateChars(ChatColor.translateAlternateColorCodes('&', messages[i]));
        }
        return messages;
    }

    public static TextComponent parse(TextComponent component) {
        String componentString = component.content();
        componentString = replaceAlternateChars(ChatColor.translateAlternateColorCodes('&', componentString));
        return component.content(componentString);
    }

    public static String header(String headerTitle) {
        return parse("{gold}{str}                  {r}{aqua}{bold} " + headerTitle + " {r}{gold}{str}                  ");
    }


    private static String replaceAlternateChars(String message) {
        if (alternateChars.isEmpty()) {
            setupAlternateCodes();
        }

        for (String s : alternateChars.keySet()) {
            if (message.contains(s)) {
                message = message.replace(s, alternateChars.get(s));
            }
        }

        return message;
    }


    public static void setupAlternateCodes() {
        alternateChars.put("{darkred}", COLOR_CHAR + "4");
        alternateChars.put("{dred}", COLOR_CHAR + "4");
        alternateChars.put("{red}", COLOR_CHAR + "c");
        alternateChars.put("{w}", COLOR_CHAR + "c");
        alternateChars.put("{gold}", COLOR_CHAR + "6");
        alternateChars.put("{yellow}", COLOR_CHAR + "e");
        alternateChars.put("{ye}", COLOR_CHAR + "e");
        alternateChars.put("{s}", COLOR_CHAR + "e");
        alternateChars.put("{darkgreen}", COLOR_CHAR + "2");
        alternateChars.put("{dgr}", COLOR_CHAR + "2");
        alternateChars.put("{green}", COLOR_CHAR + "a");
        alternateChars.put("{gr}", COLOR_CHAR + "a");
        alternateChars.put("{aqua}", COLOR_CHAR + "b");
        alternateChars.put("{aq}", COLOR_CHAR + "b");
        alternateChars.put("{darkaqua}", COLOR_CHAR + "3");
        alternateChars.put("{daq}", COLOR_CHAR + "3");
        alternateChars.put("{darkblue}", COLOR_CHAR + "1");
        alternateChars.put("{dbl}", COLOR_CHAR + "1");
        alternateChars.put("{blue}", COLOR_CHAR + "9");
        alternateChars.put("{bl}", COLOR_CHAR + "9");
        alternateChars.put("{purple}", COLOR_CHAR + "d");
        alternateChars.put("{pur}", COLOR_CHAR + "d");
        alternateChars.put("{h}", COLOR_CHAR + "d");
        alternateChars.put("{darkpurple}", COLOR_CHAR + "5");
        alternateChars.put("{dpur}", COLOR_CHAR + "5");
        alternateChars.put("{white}", COLOR_CHAR + "f");
        alternateChars.put("{whi}", COLOR_CHAR + "f");
        alternateChars.put("{gray}", COLOR_CHAR + "7");
        alternateChars.put("{darkgray}", COLOR_CHAR + "8");
        alternateChars.put("{dgray}", COLOR_CHAR + "8");
        alternateChars.put("{black}", COLOR_CHAR + "0");
        alternateChars.put("{bla}", COLOR_CHAR + "0");

        alternateChars.put("{obf}", COLOR_CHAR + "k");
        alternateChars.put("{bold}", COLOR_CHAR + "l");
        alternateChars.put("{str}", COLOR_CHAR + "m");
        alternateChars.put("{ul}", COLOR_CHAR + "n");
        alternateChars.put("{it}", COLOR_CHAR + "o");
        alternateChars.put("{r}", COLOR_CHAR + "r");
    }
}
