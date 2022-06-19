package com.gmail.willramanand.RamEssentials.economy;

import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.utils.Formatter;
import com.gmail.willramanand.RamEssentials.utils.Txt;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Bank {

    private final String name;
    private double capital;
    private final boolean isAdmin;
    private final Map<UUID, BankAccount> bankAccounts;
    private double baseInterest;
    private double multInterest;

    private double openingCost;

    public Bank(String name, boolean isAdmin, double baseInterest, double multInterest, double openingCost) {
        this.name = name;
        this.capital = 0.0;
        this.isAdmin = isAdmin;
        this.bankAccounts = new HashMap<>();
        this.baseInterest = baseInterest;
        this.multInterest = multInterest;
        this.openingCost = openingCost;

        runInterestTask();
    }

    public String getName() {
        return name;
    }

    public void setCapital(double capital) {
        this.capital = capital;
    }

    public boolean hasCapital(double amount) {
        return isAdmin || capital >= amount;
    }

    public double getCapital() {
        return capital;
    }

    public void increaseCapital(double amount) {
        this.capital += amount;
    }

    public void decreaseCapital(double amount) {
        this.capital -= amount;
    }

    public void createAccount(UUID owner) {
        BankAccount account = new BankAccount(this, owner);
        this.bankAccounts.put(owner, account);
        this.increaseCapital(openingCost);
    }

    public boolean hasAccount(UUID owner) {
        return this.getAccount(owner) != null;
    }

    public BankAccount getAccount(UUID owner) {
        return this.bankAccounts.get(owner);
    }

    public void deleteAccount(UUID owner) {
        this.bankAccounts.remove(owner);
    }

    public Map<UUID, BankAccount> getBankAccounts() {
        return this.bankAccounts;
    }

    public double getBaseInterest() {
        return baseInterest;
    }

    public void setBaseInterest(double baseInterest) {
        this.baseInterest = baseInterest;
    }

    public double getMultInterest() {
        return multInterest;
    }

    public void setMultInterest(double multInterest) {
        this.multInterest = multInterest;
    }

    public double getOpeningCost() {
        return openingCost;
    }

    public void setOpeningCost(double openingCost) {
        this.openingCost = openingCost;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    private void runInterestTask() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (BankAccount account : bankAccounts.values()) {
                    double capital = account.getCapital();
                    if (capital == 0.0) continue;
                    double interest = capital * baseInterest;
                    account.deposit(interest);
                    if (!account.getHolder().isOnline()) continue;
                    account.getHolder().getPlayer().sendMessage(Txt.parse("{h}" + Formatter.formatMoney(interest) + " {s}interest has been deposited into your account at {h}" + name));
                }
            }
        }.runTaskTimerAsynchronously(RamEssentials.getInstance(), 24000L, 24000L);
    }
}
