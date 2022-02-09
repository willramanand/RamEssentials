package com.gmail.willramanand.RamEssentials.economy;

import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.utils.Formatter;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.OfflinePlayer;

import java.util.Collections;
import java.util.List;

public class RamEssentialsEconomy implements Economy {

    private final RamEssentials plugin;

    public RamEssentialsEconomy(RamEssentials plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getName() {
        return "RamEssentials Economy";
    }

    @Override
    public boolean hasBankSupport() {
        return false;
    }

    @Override
    public int fractionalDigits() {
        return -1;
    }

    @Override
    public String format(double amount) {
        return Formatter.formatMoney(amount);
    }

    @Override
    public String currencyNamePlural() {
        return currencyNameSingular();
    }

    @Override
    public String currencyNameSingular() {
        return "$";
    }

    @Deprecated
    @Override
    public boolean hasAccount(String playerName) {
        return plugin.getAccountManager().hasAccount(playerName);
    }

    @Override
    public boolean hasAccount(OfflinePlayer player) {
        return plugin.getAccountManager().hasAccount(player);
    }

    @Deprecated
    @Override
    public boolean hasAccount(String playerName, String worldName) {
        return hasAccount(playerName);
    }

    @Override
    public boolean hasAccount(OfflinePlayer player, String worldName) {
        return hasAccount(player);
    }

    @Deprecated
    @Override
    public double getBalance(String playerName) {
        return plugin.getAccountManager().getBalance(playerName);
    }

    @Override
    public double getBalance(OfflinePlayer player) {
        return plugin.getAccountManager().getBalance(player);
    }

    @Deprecated
    @Override
    public double getBalance(String playerName, String world) {
        return getBalance(playerName);
    }

    @Override
    public double getBalance(OfflinePlayer player, String world) {
        return getBalance(player);
    }

    @Deprecated
    @Override
    public boolean has(String playerName, double amount) {
        return plugin.getAccountManager().isValidTransaction(playerName, amount);
    }

    @Override
    public boolean has(OfflinePlayer player, double amount) {
        return plugin.getAccountManager().isValidTransaction(player, amount);
    }

    @Deprecated
    @Override
    public boolean has(String playerName, String worldName, double amount) {
        return has(playerName, amount);
    }

    @Override
    public boolean has(OfflinePlayer player, String worldName, double amount) {
        return has(player, amount);
    }

    @Deprecated
    @Override
    public EconomyResponse withdrawPlayer(String playerName, double amount) {
        if (playerName == null) {
            return new EconomyResponse(0, 0, EconomyResponse.ResponseType.FAILURE, "Player name cannot be null!");
        }
        if (amount < 0) {
            return new EconomyResponse(0, 0, EconomyResponse.ResponseType.FAILURE, "Cannot withdraw negative funds!");
        }

        plugin.getAccountManager().subFromBalance(playerName, amount);
        return new EconomyResponse(amount, getBalance(playerName), EconomyResponse.ResponseType.SUCCESS, null);
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer player, double amount) {
        if (player == null) {
            return new EconomyResponse(0, 0, EconomyResponse.ResponseType.FAILURE, "Player name cannot be null!");
        }
        if (amount < 0) {
            return new EconomyResponse(0, 0, EconomyResponse.ResponseType.FAILURE, "Cannot withdraw negative funds!");
        }

        plugin.getAccountManager().subFromBalance(player, amount);
        return new EconomyResponse(amount, getBalance(player), EconomyResponse.ResponseType.SUCCESS, null);
    }

    @Deprecated
    @Override
    public EconomyResponse withdrawPlayer(String playerName, String worldName, double amount) {
        return withdrawPlayer(playerName, amount);
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer player, String worldName, double amount) {
        return withdrawPlayer(player, amount);
    }

    @Deprecated
    @Override
    public EconomyResponse depositPlayer(String playerName, double amount) {
        if (playerName == null) {
            return new EconomyResponse(0, 0, EconomyResponse.ResponseType.FAILURE, "Player name can not be null.");
        }
        if (amount < 0) {
            return new EconomyResponse(0, 0, EconomyResponse.ResponseType.FAILURE, "Cannot deposit negative funds");
        }

        plugin.getAccountManager().addToBalance(playerName, amount);
        return new EconomyResponse(amount, getBalance(playerName), EconomyResponse.ResponseType.SUCCESS, null);
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer player, double amount) {
        if (player == null) {
            return new EconomyResponse(0, 0, EconomyResponse.ResponseType.FAILURE, "Player name can not be null.");
        }
        if (amount < 0) {
            return new EconomyResponse(0, 0, EconomyResponse.ResponseType.FAILURE, "Cannot deposit negative funds");
        }

        plugin.getAccountManager().addToBalance(player, amount);
        return new EconomyResponse(amount, getBalance(player), EconomyResponse.ResponseType.SUCCESS, null);
    }

    @Deprecated
    @Override
    public EconomyResponse depositPlayer(String playerName, String worldName, double amount) {
        return depositPlayer(playerName, amount);
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer player, String worldName, double amount) {
        return depositPlayer(player, amount);
    }

    @Deprecated
    @Override
    public EconomyResponse createBank(String name, String player) {
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "RamEssentials does not support bank accounts!");
    }

    @Override
    public EconomyResponse createBank(String name, OfflinePlayer player) {
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "RamEssentials does not support bank accounts!");
    }

    @Override
    public EconomyResponse deleteBank(String name) {
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "RamEssentials does not support bank accounts!");
    }

    @Override
    public EconomyResponse bankBalance(String name) {
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "RamEssentials does not support bank accounts!");
    }

    @Override
    public EconomyResponse bankHas(String name, double amount) {
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "RamEssentials does not support bank accounts!");
    }

    @Override
    public EconomyResponse bankWithdraw(String name, double amount) {
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "RamEssentials does not support bank accounts!");
    }

    @Override
    public EconomyResponse bankDeposit(String name, double amount) {
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "RamEssentials does not support bank accounts!");
    }

    @Deprecated
    @Override
    public EconomyResponse isBankOwner(String name, String playerName) {
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "RamEssentials does not support bank accounts!");
    }

    @Override
    public EconomyResponse isBankOwner(String name, OfflinePlayer player) {
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "RamEssentials does not support bank accounts!");
    }

    @Deprecated
    @Override
    public EconomyResponse isBankMember(String name, String playerName) {
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "RamEssentials does not support bank accounts!");
    }

    @Override
    public EconomyResponse isBankMember(String name, OfflinePlayer player) {
        return new EconomyResponse(0, 0, EconomyResponse.ResponseType.NOT_IMPLEMENTED, "RamEssentials does not support bank accounts!");
    }

    @Override
    public List<String> getBanks() {
        return Collections.emptyList();
    }

    @Deprecated
    @Override
    public boolean createPlayerAccount(String playerName) {
        if (hasAccount(playerName)) {
            return false;
        }

        plugin.getAccountManager().createAccount(playerName, 0);
        return true;
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer player) {
        if (hasAccount(player)) {
            return false;
        }

        plugin.getAccountManager().createAccount(player, 0.0);
        return true;
    }

    @Deprecated
    @Override
    public boolean createPlayerAccount(String playerName, String worldName) {
        return createPlayerAccount(playerName);
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer player, String worldName) {
        return createPlayerAccount(player);
    }
}
