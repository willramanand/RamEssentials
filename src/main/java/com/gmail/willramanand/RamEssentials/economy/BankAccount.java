package com.gmail.willramanand.RamEssentials.economy;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.UUID;

public class BankAccount {

    private final Bank bank;
    private final UUID owner;
    private double capital;

    public BankAccount(Bank bank, UUID owner) {
        this.bank = bank;
        this.owner = owner;
        this.capital = 0.0;
    }

    public UUID getHolderId() {
        return owner;
    }

    public OfflinePlayer getHolder() {
        return Bukkit.getOfflinePlayer(owner);
    }

    public double getCapital() {
        return capital;
    }

    public boolean hasCapital(double amount) {
        return capital >= amount;
    }

    public void setCapital(double capital) {
        this.capital = capital;
    }

    public void deposit(double amount) {
        this.capital += amount;
        bank.increaseCapital(amount);
    }

    public void withdraw(double amount) {
        this.capital -= amount;
        bank.decreaseCapital(amount);
    }

    public Bank getBank() {
        return bank;
    }
}
