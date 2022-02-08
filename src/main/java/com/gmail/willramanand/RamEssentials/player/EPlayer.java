package com.gmail.willramanand.RamEssentials.player;

import com.gmail.willramanand.RamEssentials.data.MuteType;
import com.gmail.willramanand.RamEssentials.RamEssentials;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.*;

public class EPlayer {

    private final RamEssentials plugin;

    private final Player player;
    private final UUID uuid;

    private boolean isGodMode;
    private boolean isDoNotDisturb;

    private boolean isMuted;
    private String muteReason;
    private MuteType muteType;

    private Location lastLocation;

    private final BiMap<String, Location> homes;
    private final List<UUID> ignoredPlayers;

    private boolean saving;
    private boolean shouldSave;

    public EPlayer(RamEssentials plugin, Player player) {
        this.plugin = plugin;
        this.player = player;
        this.isGodMode = false;
        this.isDoNotDisturb = false;
        this.isMuted = false;
        this.muteReason = null;
        this.muteType = null;
        this.lastLocation = null;
        this.uuid = this.player.getUniqueId();
        this.homes = HashBiMap.create();
        this.ignoredPlayers = new ArrayList<>();
        this.saving = false;
        this.shouldSave = true;
    }

    public Player getPlayer() {
        return player;
    }

    public UUID getUuid() {
        return uuid;
    }

    public boolean isGodMode() {
        return isGodMode;
    }

    public void setGodMode(boolean godMode) {
        isGodMode = godMode;
    }

    public boolean isDoNotDisturb() {
        return isDoNotDisturb;
    }

    public void setDoNotDisturb(boolean doNotDisturb) {
        isDoNotDisturb = doNotDisturb;
    }

    public boolean isMuted() {
        return isMuted;
    }

    public void setMuted(boolean muted) {
        isMuted = muted;
    }

    public String getMuteReason() {
        return muteReason;
    }

    public void setMuteReason(String muteReason) {
        this.muteReason = muteReason;
    }

    public MuteType getMuteType() {
        return muteType;
    }

    public void setMuteType(MuteType muteType) {
        this.muteType = muteType;
    }

    public Location getLastLocation() {
        return lastLocation;
    }

    public void setLastLocation(Location lastLocation) {
        this.lastLocation = lastLocation;
    }

    public void addHome(String name, Location location) {
        homes.put(name, location);
    }

    public void delHome(String name) {
        homes.remove(name);
    }

    public Location getHome(String name) {
        return homes.get(name);
    }

    public Set<String> getHomeList() {
        return homes.keySet();
    }

    public void addIgnored(UUID uuid) {
        ignoredPlayers.add(uuid);
    }

    public void removeIgnored(UUID uuid) {
        ignoredPlayers.remove(uuid);
    }

    public boolean isIgnored(UUID uuid) {
        return ignoredPlayers.contains(uuid);
    }

    public List<UUID> getIgnoredPlayers() { return ignoredPlayers; }

    public boolean isSaving() {
        return saving;
    }

    public void setSaving(boolean saving) {
        this.saving = saving;
    }

    public boolean shouldNotSave() {
        return !shouldSave;
    }

    public void setShouldSave(boolean shouldSave) {
        this.shouldSave = shouldSave;
    }
}
