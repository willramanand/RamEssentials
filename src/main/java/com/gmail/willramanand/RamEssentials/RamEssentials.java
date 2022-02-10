package com.gmail.willramanand.RamEssentials;

import co.aikar.commands.PaperCommandManager;
import com.gmail.willramanand.RamEssentials.commands.*;
import com.gmail.willramanand.RamEssentials.data.MessageManager;
import com.gmail.willramanand.RamEssentials.data.RequestManager;
import com.gmail.willramanand.RamEssentials.data.ServerSpawn;
import com.gmail.willramanand.RamEssentials.data.Warps;
import com.gmail.willramanand.RamEssentials.economy.AccountManager;
import com.gmail.willramanand.RamEssentials.economy.RamEssentialsEconomy;
import com.gmail.willramanand.RamEssentials.listeners.PlayerListener;
import com.gmail.willramanand.RamEssentials.player.EPlayer;
import com.gmail.willramanand.RamEssentials.player.PlayerConfig;
import com.gmail.willramanand.RamEssentials.player.PlayerManager;
import com.gmail.willramanand.RamEssentials.utils.AFKTimer;
import com.gmail.willramanand.RamEssentials.utils.ColorUtils;
import com.gmail.willramanand.RamEssentials.utils.MuteTimer;
import com.gmail.willramanand.RamEssentials.utils.TxtReader;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;
import java.util.logging.Logger;

public final class RamEssentials extends JavaPlugin {

    private static final Logger log = Logger.getLogger("Minecraft");
    private static RamEssentials i;

    private PlayerManager playerManager;
    private PlayerConfig playerConfig;

    private ServerSpawn serverSpawn;
    private Warps warps;
    private AFKTimer afkTimer;

    private RequestManager requestManager;
    private MessageManager messageManager;
    private AccountManager accountManager;

    private final List<UUID> tempMutedPlayers = new ArrayList<>();

    private int houseLimit = this.getConfig().getInt("home-limit");

    @Override
    public void onEnable() {
        i = this;

        long startTime = System.currentTimeMillis();
        log.info(ColorUtils.colorMessage("[" + this.getName() + "] &6===&b ENABLE START &6==="));

        if (isVaultActive()) {
            log.info(ColorUtils.colorMessage("[" + this.getName() + "] &eEnabling &dVault &eintegration."));
        }

        playerManager = new PlayerManager(this);
        playerConfig = new PlayerConfig(this);
        serverSpawn = new ServerSpawn(this);
        warps = new Warps(this);
        requestManager = new RequestManager(this);
        messageManager = new MessageManager(this);
        accountManager = new AccountManager(this);
        afkTimer = new AFKTimer(this);

        // Config
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();

        TxtReader.setup();

        accountManager.load();

        serverSpawn.load();
        warps.load();

        // Commands
        registerCommands();

        // Listeners
        registerEvents();

        playerManager.startAutoSave();
        accountManager.runAutoSave();

        setupHomeLimit();

        startTime = System.currentTimeMillis() - startTime;
        log.info(ColorUtils.colorMessage("[" + this.getName() + "] &6=== &bENABLE &2COMPLETE &6(&eTook &d" + startTime +"ms&6) ==="));
    }

    @Override
    public void onDisable() {
        serverSpawn.save();
        warps.save();
        accountManager.save();

        for (UUID uuid : tempMutedPlayers) {
            MuteTimer.clearMute(uuid);
        }

        tempMutedPlayers.clear();

        for (Player player : Bukkit.getOnlinePlayers()) {
            playerConfig.save(player, true);
        }

        log.info("Disabled");
    }

    @Override
    public void onLoad() {
        try {
            Class.forName("net.milkbowl.vault.economy.Economy");
            getServer().getServicesManager().register(Economy.class, new RamEssentialsEconomy(this), this, ServicePriority.Highest);
        } catch (final ClassNotFoundException ignored) {

        }
    }

    public static RamEssentials getInstance() {
        return i;
    }

    public boolean isVaultActive() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            log.warning("Vault is not installed, disabling Vault integration!");
            return false;
        }
        return true;
    }

    private void setupHomeLimit() {
        if (houseLimit == 0) {
            houseLimit = 3;
            this.getConfig().set("home-limit", 3);
            this.getLogger().info(ColorUtils.colorMessage("&eHomes limit is empty in config! Setting to &d3"));
            this.saveConfig();
        }
    }

    private void registerCommands() {
        PaperCommandManager commandManager = new PaperCommandManager(this);

        commandManager.enableUnstableAPI("help");

        commandManager.getCommandCompletions().registerAsyncCompletion("warps", context -> warps.getWarpList());

        commandManager.getCommandCompletions().registerAsyncCompletion("homes", context -> {
            Player player = context.getPlayer();
            EPlayer ePlayer = this.getPlayerManager().getPlayerData(player);

            if (ePlayer.getHomeList().isEmpty()) {
                return null;
            }
            return ePlayer.getHomeList();
        });

        commandManager.getCommandCompletions().registerAsyncCompletion("requests", context -> {
           Player playerTo = context.getPlayer();

           if (this.requestManager.getRequests(playerTo) == null) {
               return null;
           }
           Set<String> requestNames = new HashSet<>();
           for (Player player : requestManager.getRequests(playerTo)) {
               requestNames.add(player.getName());
           }
           return requestNames;
        });

        // Teleport Commands
        commandManager.registerCommand(new TeleportCommand(this));
        commandManager.registerCommand(new SpawnCommand(this));
        commandManager.registerCommand(new SetSpawnCommand(this));
        commandManager.registerCommand(new WarpCommand(this));
        commandManager.registerCommand(new BackCommand(this));
        commandManager.registerCommand(new HomeCommand(this));
        commandManager.registerCommand(new TPACommand(this));
        commandManager.registerCommand(new WorldCommand(this));

        // Messaging Commands
        commandManager.registerCommand(new BroadcastCommand(this));
        commandManager.registerCommand(new BroadcastWorldCommand(this));
        commandManager.registerCommand(new MessageCommand(this));
        commandManager.registerCommand(new DNDCommand(this));
        commandManager.registerCommand(new IgnoreCommand(this));

        // Inventory Commands
        commandManager.registerCommand(new EnderChestCommand(this));
        commandManager.registerCommand(new WorkbenchCommand(this));
        commandManager.registerCommand(new AnvilCommand(this));
        commandManager.registerCommand(new SmithingTableCommand(this));
        commandManager.registerCommand(new CartographyTableCommand(this));
        commandManager.registerCommand(new GrindstoneCommand(this));
        commandManager.registerCommand(new LoomCommand(this));
        commandManager.registerCommand(new StoneCutterCommand(this));
        commandManager.registerCommand(new InventoryCommand(this));

        // Misc Commands
        commandManager.registerCommand(new KillPlayerCommand(this));
        commandManager.registerCommand(new SuicideCommand(this));
        commandManager.registerCommand(new GodCommand(this));
        commandManager.registerCommand(new MotdCommand(this));
        commandManager.registerCommand(new RulesCommand(this));
        commandManager.registerCommand(new FlyCommand(this));
        commandManager.registerCommand(new WalkSpeedCommand(this));
        commandManager.registerCommand(new FlySpeedCommand(this));
        commandManager.registerCommand(new AFKCommand(this));

        // Admin Commands
        commandManager.registerCommand(new MuteCommand(this));

        // Money Commands
        commandManager.registerCommand(new BalanceCommand(this));
        commandManager.registerCommand(new EcoCommand(this));
        commandManager.registerCommand(new PayCommand(this));
    }

    private void registerEvents() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PlayerListener(this), this);
    }

    public PlayerConfig getPlayerConfig() { return playerConfig; }

    public PlayerManager getPlayerManager() { return playerManager; }

    public List<UUID> getTempMutedPlayers() { return tempMutedPlayers; }

    public ServerSpawn getServerSpawn() { return serverSpawn; }

    public Warps getWarps() { return warps; }

    public RequestManager getRequestManager() { return requestManager; }

    public MessageManager getMessageManager() { return messageManager; }

    public AccountManager getAccountManager() { return accountManager; }

    public AFKTimer getAfkTimer() { return afkTimer; }

    public int getHouseLimit() { return houseLimit; }
}
