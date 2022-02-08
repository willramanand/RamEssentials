package com.gmail.willramanand.RamEssentials.economy;

import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.utils.ColorUtils;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class AccountManager {

    private final RamEssentials plugin;

    private final Map<UUID, Double> accounts;
    private final BiMap<String, UUID> playerNames;

    public AccountManager(RamEssentials plugin) {
        this.plugin = plugin;
        this.accounts = new HashMap<>();
        this.playerNames = HashBiMap.create();
    }

    public void load() {
        File file = new File(plugin.getDataFolder().getPath() + "/accounts.yml");

        if (!file.exists()) {
            try {
                file.createNewFile();
                plugin.getLogger().info(ColorUtils.colorMessage("&eCreated accounts file!"));
            } catch (IOException e) {
                plugin.getLogger().info(ColorUtils.colorMessage("&cFailed to created accounts file!"));
            }
        }

        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        Set<String> stringUUIDs = config.getKeys(false);

        int i = 0;
        for (String s : stringUUIDs) {
            createAccount(UUID.fromString(s), config.getDouble(s + ".balance"));
            playerNames.put(config.getString(s + ".name"), UUID.fromString(s));
            i++;
        }
        plugin.getLogger().info(ColorUtils.colorMessage("&eLoaded &d" + i + " &eaccounts!"));
    }

    public void save() {
        File file = new File(plugin.getDataFolder().getPath() + "/accounts.yml");

        if (file.exists()) {
            FileConfiguration config = YamlConfiguration.loadConfiguration(file);

            for (UUID uuid : accounts.keySet()) {
                config.set(uuid.toString() + ".balance", accounts.get(uuid));
            }
            try {
                config.save(file);
            } catch (IOException e) {
                plugin.getLogger().info(ColorUtils.colorMessage("&cFailed to save accounts file!"));
            }
        }
    }

    public void createAccount(OfflinePlayer player, double amount) {
        accounts.put(player.getUniqueId(), amount);
        playerNames.put(player.getName(), player.getUniqueId());
    }

    public void createAccount(UUID uuid, double amount) {
        accounts.put(uuid, amount);
    }

    @Deprecated
    public void createAccount(String playerName, double amount) {
        UUID uuid = playerNames.get(playerName);
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
        UUID uuid = playerNames.get(playerName);
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
        UUID uuid = playerNames.get(playerName);
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
        UUID uuid = playerNames.get(playerName);
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
        UUID uuid = playerNames.get(playerName);
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
        UUID uuid = playerNames.get(playerName);
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
        UUID uuid = playerNames.get(playerName);
        double initial = accounts.get(uuid);
        return (initial - amount) >= 0;
    }

    @Deprecated
    public void updateUserName(String playerName, UUID uuid) {
        playerNames.put(playerName, uuid);
    }

    @Deprecated
    public String getPlayerNameByUUID(UUID uuid) {
        return playerNames.inverse().get(uuid);
    }
}
