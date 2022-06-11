package com.gmail.willramanand.RamEssentials.economy;

import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.lang.Lang;
import com.gmail.willramanand.RamEssentials.utils.Txt;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class AccountManager {

    private final RamEssentials plugin;

    private final Map<UUID, Double> accounts;
    private final Map<UUID, String> playerNames;

    public AccountManager(RamEssentials plugin) {
        this.plugin = plugin;
        this.accounts = new HashMap<>();
        this.playerNames = new HashMap<>();
    }

    public void load() {
        File file = new File(plugin.getDataFolder().getPath() + "/accounts.yml");

        if (!file.exists()) {
            try {
                file.createNewFile();
                plugin.getLogger().info(Txt.parse(Lang.ACCOUNTS_CREATE));
            } catch (IOException e) {
                plugin.getLogger().info(Txt.parse(Lang.ACCOUNTS_CREATE_FAIL));
            }
        }

        if (file.exists()) {
            FileConfiguration config = YamlConfiguration.loadConfiguration(file);
            Set<String> stringUUIDs = config.getKeys(false);

            int i = 0;
            for (String s : stringUUIDs) {
                playerNames.put(UUID.fromString(s), config.getString(s + ".name"));
                createAccount(UUID.fromString(s), config.getDouble(s + ".balance"));
                i++;
            }
            plugin.getLogger().info(Txt.process(Lang.ACCOUNTS_LOADED, "{count}", String.valueOf(i)));
        }
    }

    public void save() {
        File file = new File(plugin.getDataFolder().getPath() + "/accounts.yml");

        if (file.exists()) {
            FileConfiguration config = YamlConfiguration.loadConfiguration(file);

            for (UUID uuid : accounts.keySet()) {
                config.set(uuid + ".name", playerNames.get(uuid));
                config.set(uuid + ".balance", accounts.get(uuid));
            }
            try {
                config.save(file);
            } catch (IOException e) {
                plugin.getLogger().info(Txt.parse(Lang.ACCOUNTS_SAVE_FAIL));
            }
        }
    }

    public void runAutoSave() {
        new BukkitRunnable() {
            @Override
            public void run() {
                save();
            }
        }.runTaskTimerAsynchronously(plugin, 6000L, 6000L);
    }

    public void createAccount(OfflinePlayer player, double amount) {
        accounts.put(player.getUniqueId(), amount);
        playerNames.put(player.getUniqueId(), player.getName());
    }

    public void createAccount(UUID uuid, double amount) {
        accounts.put(uuid, amount);
    }

    @Deprecated
    public void createAccount(String playerName, double amount) {
        UUID uuid = getUUIDByPlayerName(playerName);
        accounts.put(uuid, amount);
    }

    public boolean hasAccount(OfflinePlayer player) {
        return accounts.containsKey(player.getUniqueId());
    }

    public boolean hasAccount(UUID uuid) {
        return accounts.containsKey(uuid);
    }

    @Deprecated
    public boolean hasAccount(String playerName) {
        UUID uuid = getUUIDByPlayerName(playerName);
        return accounts.containsKey(uuid);
    }

    public void setBalance(OfflinePlayer player, double amount) {
        accounts.put(player.getUniqueId(), amount);
    }


    public void setBalance(UUID uuid, double amount) {
        accounts.put(uuid, amount);
    }

    @Deprecated
    public void setBalance(String playerName, double amount) {
        UUID uuid = getUUIDByPlayerName(playerName);
        accounts.put(uuid, amount);
    }

    public double getBalance(OfflinePlayer player) {
        return accounts.get(player.getUniqueId());
    }

    public double getBalance(UUID uuid) {
        return accounts.get(uuid);
    }

    @Deprecated
    public double getBalance(String playerName) {
        UUID uuid = getUUIDByPlayerName(playerName);
        return accounts.get(uuid);
    }

    public void addToBalance(OfflinePlayer player, double amount) {
        double initial = accounts.get(player.getUniqueId());
        double newAmount = initial + amount;
        accounts.put(player.getUniqueId(), newAmount);
    }

    public void addToBalance(UUID uuid, double amount) {
        double initial = accounts.get(uuid);
        double newAmount = initial + amount;
        accounts.put(uuid, newAmount);
    }

    @Deprecated
    public void addToBalance(String playerName, double amount) {
        UUID uuid = getUUIDByPlayerName(playerName);
        double initial = accounts.get(uuid);
        double newAmount = initial + amount;
        accounts.put(uuid, newAmount);
    }

    public void subFromBalance(OfflinePlayer player, double amount) {
        double initial = accounts.get(player.getUniqueId());
        double newAmount = initial - amount;
        accounts.put(player.getUniqueId(), newAmount);
    }

    public void subFromBalance(UUID uuid, double amount) {
        double initial = accounts.get(uuid);
        double newAmount = initial - amount;
        accounts.put(uuid, newAmount);
    }

    @Deprecated
    public void subFromBalance(String playerName, double amount) {
        UUID uuid = getUUIDByPlayerName(playerName);
        double initial = accounts.get(uuid);
        double newAmount = initial - amount;
        accounts.put(uuid, newAmount);
    }

    public boolean isValidTransaction(UUID uuid, double amount) {
        double initial = accounts.get(uuid);
        return (initial - amount) >= 0;
    }

    public boolean isValidTransaction(OfflinePlayer player, double amount) {
        double initial = accounts.get(player.getUniqueId());
        return (initial - amount) >= 0;
    }

    @Deprecated
    public boolean isValidTransaction(String playerName, double amount) {
        UUID uuid = getUUIDByPlayerName(playerName);
        double initial = accounts.get(uuid);
        return (initial - amount) >= 0;
    }

    public String getPlayerNameByUUID(UUID uuid) {
        OfflinePlayer player = Bukkit.getPlayer(uuid);

        if (player != null) {
            return player.getName();
        }
        return null;
    }

    @Deprecated
    public UUID getUUIDByPlayerName(String playerName) {
        OfflinePlayer player = Bukkit.getOfflinePlayer(playerName);
        if (player != null) {
            return player.getUniqueId();
        }
        return null;
    }

}
