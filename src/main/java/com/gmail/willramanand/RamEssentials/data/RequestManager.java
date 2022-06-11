package com.gmail.willramanand.RamEssentials.data;

import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.utils.EasyComponent;
import com.gmail.willramanand.RamEssentials.utils.Txt;
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

        playerFrom.sendMessage(Txt.parse("{s}Request sent."));

        playerTo.sendMessage(Txt.parse("{s}You have received a teleport request from {h}" + playerFrom.getName()));
        EasyComponent componentAccept = new EasyComponent("{s}Type {h}/tpaaccept {s}to accept this request.");
        playerTo.sendMessage(componentAccept.clickEvent("/tpaccept " + playerFrom.getName()).hoverEvent("&6Click here to accept &b" + playerFrom.getName() + "'s &6request!").get());
        EasyComponent componentDeny = new EasyComponent("{s}Type {h}/tpadeny {s}to deny this request.");
        playerTo.sendMessage(componentDeny.clickEvent("/tpadeny " + playerFrom.getName()).hoverEvent("&6Click here to deny &b" + playerFrom.getName() + "'s &6request!").get());
        playerTo.sendMessage(Txt.parse("{s}This request will be automatically denied in {h}120 {s}seconds."));

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
                    playerFrom.sendMessage(Txt.parse("{s}Request to {d}" + playerTo.getName() + " {s}denied!"));
                    removeRequest(playerTo, playerFrom);
                }
            }
        }.runTaskLater(plugin, 2400);
    }
}
