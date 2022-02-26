package com.gmail.willramanand.RamEssentials;

import com.gmail.willramanand.RamEssentials.commands.*;
import com.gmail.willramanand.RamEssentials.commands.inv.*;
import com.gmail.willramanand.RamEssentials.commands.eco.CmdEcoRoot;
import com.gmail.willramanand.RamEssentials.data.MessageManager;
import com.gmail.willramanand.RamEssentials.data.RequestManager;
import com.gmail.willramanand.RamEssentials.data.ServerSpawn;
import com.gmail.willramanand.RamEssentials.data.Warps;
import com.gmail.willramanand.RamEssentials.economy.AccountManager;
import com.gmail.willramanand.RamEssentials.economy.RamEssentialsEconomy;
import com.gmail.willramanand.RamEssentials.listeners.PlayerListener;
import com.gmail.willramanand.RamEssentials.player.PlayerConfig;
import com.gmail.willramanand.RamEssentials.player.PlayerManager;
import com.gmail.willramanand.RamEssentials.utils.AFKTimer;
import com.gmail.willramanand.RamEssentials.utils.MuteTimer;
import com.gmail.willramanand.RamEssentials.utils.Txt;
import com.gmail.willramanand.RamEssentials.utils.TxtReader;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class RamEssentials extends JavaPlugin {

    private final Logger log = this.getLogger();
    private static RamEssentials i;

    private PlayerManager playerManager;
    private PlayerConfig playerConfig;

    private ServerSpawn serverSpawn;
    private Warps warps;
    private AFKTimer afkTimer;

    private RequestManager requestManager;
    private MessageManager messageManager;
    private AccountManager accountManager;

    private int houseLimit = 0;
    private int commandsPerPage = 0;

    @Override
    public void onEnable() {
        i = this;

        long startTime = System.currentTimeMillis();
        log.info(Txt.parse("{gold}==={aqua} ENABLE START {gold}==="));

        if (isVaultActive()) {
            log.info(Txt.parse("{s}Enabling {h}Vault {s}integration."));
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

        houseLimit = this.getConfig().getInt("home-limit");
        setupHomeLimit();

        commandsPerPage = this.getConfig().getInt("commandsPerPage");
        setupHelpLimit();

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

        MuteTimer.runMuteTimer();

        startTime = System.currentTimeMillis() - startTime;
        log.info(Txt.parse("{gold}=== {aqua}ENABLE {darkgreen}COMPLETE {gold}({s}Took {h}" + startTime + "ms{gold}) ==="));
    }

    @Override
    public void onDisable() {
        serverSpawn.save();
        warps.save();
        accountManager.save();

        for (Player player : Bukkit.getOnlinePlayers()) {
            playerConfig.save(player, true);
        }

        log.info(Txt.parse("{w}Disabled"));
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
            log.info(Txt.parse("{s}Homes limit is empty in config! Setting to {h}3"));
            this.saveConfig();
        }
    }

    private void setupHelpLimit() {
        if (commandsPerPage == 0) {
            commandsPerPage = 7;
            this.getConfig().set("commandsPerPage", 7);
            log.info(Txt.parse("{s}Commands per page is empty in config! Setting to {h}7"));
            this.saveConfig();
        }
    }

    private void registerCommands() {
        addCommand("afk", new CmdAFK(this));
        addCommand("anvil", new CmdAnvil(this));
        addCommand("back", new CmdBack(this));
        addCommand("balance", new CmdBalance(this));
        addCommand("broadcast", new CmdBroadcast(this));
        addCommand("broadcastworld", new CmdBroadcastWorld(this));
        addCommand("cartographytable", new CmdCartographyTable(this));
        addCommand("delhome", new CmdDelHome(this));
        addCommand("delwarp", new CmdDelWarp(this));
        addCommand("donotdisturb", new CmdDND(this));
        addCommand("eco", new CmdEcoRoot(this));
        addCommand("enderchest", new CmdEnderChest(this));
        addCommand("fly", new CmdFly(this));
        addCommand("flyspeed", new CmdFlySpeed(this));
        addCommand("gamemode", new CmdGamemode(this));
        addCommand("godmode", new CmdGod(this));
        addCommand("grindstone", new CmdGrindstone(this));
        addCommand("heal", new CmdHeal(this));
        addCommand("help", new CmdHelp(this, getCommandsPerPage()));
        addCommand("home", new CmdHome(this));
        addCommand("ignore", new CmdIgnore(this));
        addCommand("inventorysee", new CmdInvsee(this));
        addCommand("killplayer", new CmdKillPlayer(this));
        addCommand("loom", new CmdLoom(this));
        addCommand("message", new CmdMessage(this));
        addCommand("messageoftheday", new CmdMotd(this));
        addCommand("mute", new CmdMute(this));
        addCommand("mutetemp", new CmdMuteTemp(this));
        addCommand("pay", new CmdPay(this));
        addCommand("reply", new CmdReply(this));
        addCommand("rules", new CmdRules(this));
        addCommand("sethome", new CmdSetHome(this));
        addCommand("setspawn", new CmdSetSpawn(this));
        addCommand("setwarp", new CmdSetWarp(this));
        addCommand("smithingtable", new CmdSmithingTable(this));
        addCommand("spawn", new CmdSpawn(this));
        addCommand("stonecutter", new CmdStonecutter(this));
        addCommand("suicide", new CmdSuicide(this));
        addCommand("teleport", new CmdTeleport(this));
        addCommand("teleportpos", new CmdTeleportPos(this));
        addCommand("tpa", new CmdTPA(this));
        addCommand("tpaccept", new CmdTPAccept(this));
        addCommand("tpadeny", new CmdTPADeny(this));
        addCommand("unmute", new CmdUnmute(this));
        addCommand("walkspeed", new CmdWalkSpeed(this));
        addCommand("warp", new CmdWarp(this));
        addCommand("workbench", new CmdWorkbench(this));
        addCommand("world", new CmdWorld(this));
    }

    private void addCommand(String name, CommandExecutor executor) {
        this.getCommand(name).setExecutor(executor);
    }

    private void registerEvents() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PlayerListener(this), this);
    }

    public PlayerConfig getPlayerConfig() {
        return playerConfig;
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public ServerSpawn getServerSpawn() {
        return serverSpawn;
    }

    public Warps getWarps() {
        return warps;
    }

    public RequestManager getRequestManager() {
        return requestManager;
    }

    public MessageManager getMessageManager() {
        return messageManager;
    }

    public AccountManager getAccountManager() {
        return accountManager;
    }

    public AFKTimer getAfkTimer() {
        return afkTimer;
    }

    public int getHouseLimit() {
        return houseLimit;
    }

    public int getCommandsPerPage() {
        return commandsPerPage;
    }
}
