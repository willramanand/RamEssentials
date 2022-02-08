package com.gmail.willramanand.RamEssentials.data;

import com.gmail.willramanand.RamEssentials.RamEssentials;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class MessageManager {

    private final RamEssentials plugin;
    private final Map<Player, Player> lastReceivedPlayer;

    public MessageManager(RamEssentials plugin) {
        this.plugin = plugin;
        this.lastReceivedPlayer = new HashMap<>();
    }

    public void addLastPlayer(Player playerTo, Player playerFrom) {
        lastReceivedPlayer.put(playerTo, playerFrom);
    }

    public Player getLastPlayer(Player playerTo) {
        if (lastReceivedPlayer.get(playerTo) == null) {
            return null;
        }
        return lastReceivedPlayer.get(playerTo);
    }
}
