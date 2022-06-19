package com.gmail.willramanand.RamEssentials.economy;

import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.gmail.willramanand.RamEssentials.lang.Lang;
import com.gmail.willramanand.RamEssentials.utils.Txt;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class BankManager {

    private final RamEssentials plugin;
    private final Map<String, Bank> bankRegistry;

    public BankManager(RamEssentials plugin) {
        this.plugin = plugin;
        this.bankRegistry = new HashMap<>();
    }

    public void load() {
        File file = new File(plugin.getDataFolder().getPath() + "/banks.yml");

        if (!file.exists()) {
            try {
                file.createNewFile();
                plugin.getLogger().info(Txt.parse("{h}banks.yml {s}was created!"));
            } catch (IOException e) {
                plugin.getLogger().info(Txt.parse("{w}Failed to create banks.yml"));
            }
        }

        if (file.exists()) {
            FileConfiguration config = YamlConfiguration.loadConfiguration(file);
            Set<String> bankNames = config.getKeys(false);

            int i = 0;
            for (String s : bankNames) {
                boolean isAdmin = config.getBoolean(s + ".admin", false);
                double base = config.getDouble(s + ".baseInterest", 0.5);
                double mult = config.getDouble(s + ".multInterest", 0.0);
                double cost = config.getDouble(s + ".openingCost", 2500.0);
                double bankCapital = config.getDouble(s + ".capital", 0.0);
                Bank bank = new Bank(s, isAdmin, base, mult, cost);
                bank.setCapital(bankCapital);
                ConfigurationSection section = config.getConfigurationSection(s + ".accounts");

                if (section == null) continue;
                Set<String> bankAccounts = section.getKeys(false);
                for (String stringUUID : bankAccounts) {
                    UUID uuid = UUID.fromString(stringUUID);
                    double capital = section.getDouble(stringUUID + ".amount", 0.0);
                    bank.createAccount(uuid);
                    bank.getAccount(uuid).setCapital(capital);
                }

                bankRegistry.put(s, bank);
                i++;
            }
            plugin.getLogger().info(Txt.parse("{s}Loaded {h}" + i + " {s}banks."));
        }
    }

    public void save() {
        File file = new File(plugin.getDataFolder().getPath() + "/banks.yml");

        if (file.exists()) {
            FileConfiguration config = YamlConfiguration.loadConfiguration(file);

            Set<String> bankNames = config.getKeys(false);
            for (String s: bankNames) {
                if (bankRegistry.get(s) != null) {
                    ConfigurationSection section = config.getConfigurationSection(s + ".accounts");
                    if (section == null) continue;
                    for (String stringUUID : section.getKeys(false)) {
                        UUID uuid = UUID.fromString(stringUUID);
                        if (bankRegistry.get(s).getAccount(uuid) != null) continue;
                        section.set(stringUUID, null);
                    }
                } else {
                    config.set(s, null);
                }
            }

            for (String bankName : bankRegistry.keySet()) {
                Bank bank = bankRegistry.get(bankName);
                config.set(bankName + ".admin", bank.isAdmin());
                config.set(bankName + ".baseInterest", bank.getBaseInterest());
                config.set(bankName + ".multInterest", bank.getMultInterest());
                config.set(bankName + ".openingCost", bank.getOpeningCost());

                ConfigurationSection section = config.createSection(bankName + ".accounts");
                Map<UUID, BankAccount> bankAccountMap = bank.getBankAccounts();
                for (UUID uuid : bankAccountMap.keySet()) {
                    section.set(uuid.toString() + ".amount", bankAccountMap.get(uuid).getCapital());
                }
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

    public void createBank(String name, double base, double mult, double openingCost) {
        Bank bank = new Bank(name, true, base, mult, openingCost);
        bankRegistry.put(name, bank);
    }

    public boolean exists(String name) {
        return bankRegistry.get(name) != null;
    }

    public Bank getBank(String name) { return bankRegistry.get(name); }

    public void deleteBank(String name) {
        Bank bank = bankRegistry.get(name);

        for (BankAccount account : bank.getBankAccounts().values()) {
            if (account.getCapital() == 0.0) continue;
            plugin.getAccountManager().addToBalance(account.getHolderId(), account.getCapital());
            account.setCapital(0.0);
            if (account.getHolder().isOnline()) {
                account.getHolder().getPlayer().sendMessage(Txt.parse("{s}Your account with bank {h}" + name + " {s}was deleted!"),
                        Txt.parse("{s}The balance of your account has been transferred to your wallet."));
            }
        }

        bankRegistry.remove(name);
    }

    public Map<String, Bank> getBanks() { return bankRegistry;}

    public List<BankAccount> getPlayerAccounts(UUID uuid) {
        List<BankAccount> accounts = new ArrayList<>();
        for (Bank bank : bankRegistry.values()) {
            if (!bank.hasAccount(uuid)) continue;
            accounts.add(bank.getAccount(uuid));
        }

        return accounts;
    }
}
