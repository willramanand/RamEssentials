package com.gmail.willramanand.RamEssentials;

import com.gmail.willramanand.RamEssentials.commands.*;
import com.gmail.willramanand.RamEssentials.commands.account.CmdAccountRoot;
import com.gmail.willramanand.RamEssentials.commands.bank.CmdBankRoot;
import com.gmail.willramanand.RamEssentials.commands.eco.CmdEcoRoot;
import com.gmail.willramanand.RamEssentials.commands.inv.*;
import com.gmail.willramanand.RamEssentials.data.MessageManager;
import com.gmail.willramanand.RamEssentials.data.RequestManager;
import com.gmail.willramanand.RamEssentials.data.ServerSpawn;
import com.gmail.willramanand.RamEssentials.data.Warps;
import com.gmail.willramanand.RamEssentials.economy.AccountManager;
import com.gmail.willramanand.RamEssentials.economy.Bank;
import com.gmail.willramanand.RamEssentials.economy.BankManager;
import com.gmail.willramanand.RamEssentials.economy.RamEssentialsEconomy;
import com.gmail.willramanand.RamEssentials.lang.Lang;
import com.gmail.willramanand.RamEssentials.lang.LangConfiguration;
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

    private LangConfiguration langConfiguration;

    private PlayerManager playerManager;
    private PlayerConfig playerConfig;

    private ServerSpawn serverSpawn;
    private Warps warps;
    private AFKTimer afkTimer;

    private RequestManager requestManager;
    private MessageManager messageManager;
    private AccountManager accountManager;

    private BankManager bankManager;

    private int houseLimit = 0;
    private int commandsPerPage = 0;
    private int teleportDelay = 0;

    @Override
    public void onEnable() {
        i = this;

        long startTime = System.currentTimeMillis();

        langConfiguration = new LangConfiguration(this);
        langConfiguration.load();

        log.info(Txt.parse(Lang.ENABLE_START));

        if (isVaultActive()) {
            log.info(Txt.parse(Lang.ENABLE_VAULT_INT));
        }

        playerManager = new PlayerManager(this);
        playerConfig = new PlayerConfig(this);
        serverSpawn = new ServerSpawn(this);
        warps = new Warps(this);
        requestManager = new RequestManager(this);
        messageManager = new MessageManager(this);
        accountManager = new AccountManager(this);
        bankManager = new BankManager(this);
        afkTimer = new AFKTimer(this);

        // Config
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();

        houseLimit = this.getConfig().getInt("home-limit");
        setupHomeLimit();

        commandsPerPage = this.getConfig().getInt("commandsPerPage");
        setupHelpLimit();

        teleportDelay = this.getConfig().getInt("teleportDelay");
        setupTeleportDelay();

        TxtReader.setup();

        accountManager.load();
        bankManager.load();

        serverSpawn.load();
        warps.load();

        // Commands
        registerCommands();

        // Listeners
        registerEvents();

        playerManager.startAutoSave();
        accountManager.runAutoSave();
        bankManager.runAutoSave();

        MuteTimer.runMuteTimer();

        startTime = System.currentTimeMillis() - startTime;
        log.info(Txt.process(Lang.ENABLE_COMPLETE, "{start-time}", String.valueOf(startTime)));
    }

    @Override
    public void onDisable() {
        log.info(Txt.parse(Lang.DISABLE_START));

        serverSpawn.save();
        warps.save();
        accountManager.save();
        bankManager.save();

        for (Player player : Bukkit.getOnlinePlayers()) {
            playerConfig.save(player, true);
        }

        log.info(Txt.parse(Lang.DISABLE_COMPLETE));
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
            log.info(Txt.parse(Lang.VAULT_NOT_FOUND));
            return false;
        }
        return true;
    }

    private void setupHomeLimit() {
        if (houseLimit == 0) {
            houseLimit = 3;
            this.getConfig().set("home-limit", 3);
            log.info(Txt.parse(Lang.HOME_LIMIT_EMPTY));
            this.saveConfig();
        }
    }

    private void setupHelpLimit() {
        if (commandsPerPage == 0) {
            commandsPerPage = 7;
            this.getConfig().set("commandsPerPage", 7);
            log.info(Txt.parse(Lang.CMDS_PER_PAGE_EMPTY));
            this.saveConfig();
        }
    }

    private void setupTeleportDelay() {
        if (this.getConfig().get("teleportDelay") == null) {
            log.info(Txt.parse(Lang.TP_DELAY_EMPTY));
            teleportDelay = 0;
            this.getConfig().set("teleportDelay", 0);
            this.saveConfig();
        } else {
            if (teleportDelay < 0) {
                log.info(Txt.parse("{w}Invalid teleport delay! Setting to {h}0{w}!"));
                teleportDelay = 0;
                this.getConfig().set("teleportDelay", 0);
                this.saveConfig();
            }
        }
    }

    private void registerCommands() {
        addCommand("account", new CmdAccountRoot(this));
        addCommand("afk", new CmdAFK(this));
        addCommand("anvil", new CmdAnvil(this));
        addCommand("back", new CmdBack(this));
        addCommand("balance", new CmdBalance(this));
        addCommand("bank", new CmdBankRoot(this));
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

    public String getLang(Lang lang) { return langConfiguration.get(lang); }

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

    public BankManager getBankManager() { return bankManager; }

    public AFKTimer getAfkTimer() {
        return afkTimer;
    }

    public int getHouseLimit() {
        return houseLimit;
    }

    public int getCommandsPerPage() {
        return commandsPerPage;
    }

    public int getTeleportDelay() { return teleportDelay; }
}
