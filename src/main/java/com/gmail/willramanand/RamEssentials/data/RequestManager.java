package com.gmail.willramanand.RamEssentials.data;

import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.utils.ColorUtils;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestManager {

    private final RamEssentials plugin;

    private final Map<Player, List<Player>> requests;

    public RequestManager(RamEssentials plugin) {
        this.plugin = plugin;
        this.requests = new HashMap<>();
    }

    public void addRequest(Player playerTo, Player playerFrom) {
        if (requests.get(playerTo) == null) {
            List<Player> playersFromList =  new ArrayList<>();
            requests.put(playerTo, playersFromList);
        }
        requests.get(playerTo).add(playerFrom);

        playerTo.sendMessage(ColorUtils.colorMessage("&eYou have received a teleport request from &d" + playerFrom.getName()));
        playerTo.sendMessage(ColorUtils.colorMessage("&eType &d/tpaccept &eto accept this request."));
        playerTo.sendMessage(ColorUtils.colorMessage("&eType &d/tpadeny &eto deny this request."));
        playerTo.sendMessage(ColorUtils.colorMessage("&eThis request will be automatically denied in &d120 &eseconds."));

        startTimer(playerTo, playerFrom);
    }

    public void removeRequest(Player playerTo, Player playerFrom) {
        if (requests.get(playerTo) == null || !(requests.get(playerTo).contains(playerFrom))) {
            return;
        }
        requests.get(playerTo).remove(playerFrom);
    }

    public boolean hasRequest(Player playerTo, Player playerFrom) {
        if (requests.get(playerTo) == null) {
            return false;
        }
        return requests.get(playerTo).contains(playerFrom);
    }

    public Player getMostRecentRequest(Player playerTo) {
        if (requests.get(playerTo) == null) {
            return null;
        }
        return requests.get(playerTo).get(requests.get(playerTo).size() - 1);
    }

    public List<Player> getRequests(Player playerTo) {
        if (requests.get(playerTo) == null) {
            return null;
        }
        return requests.get(playerTo);
    }

    private void startTimer(Player playerTo, Player playerFrom) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (hasRequest(playerTo, playerFrom)) {
                    playerFrom.sendMessage(ColorUtils.colorMessage("&eRequest to &d" + playerTo.getName() + " &edenied!"));
                    removeRequest(playerTo, playerFrom);
                }
            }
        }.runTaskLater(plugin, 2400);
    }
}
