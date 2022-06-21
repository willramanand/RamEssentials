package com.gmail.willramanand.RamEssentials.lang;

public enum Lang {

    /* =========================== PLUGIN SETUP/END RELATED MESSAGES START =============================== */

    // ENABLE MESSAGES
    ENABLE_START("{gold}==={aqua} ENABLE START {gold}==="),
    ENABLE_COMPLETE("{gold}=== {aqua}ENABLE {darkgreen}COMPLETE {gold}({s}Took {h}{start-time} ms{gold}) ==="),

    // VAULT INTEGRATION MESSAGES
    ENABLE_VAULT_INT("{s}Enabling {h}Vault {s}integration."),
    VAULT_NOT_FOUND("{ew}Vault is not installed, disabling Vault integration!"),

    // CONFIG RELATED MESSAGES
    HOME_LIMIT_EMPTY("{s}Homes limit is empty in config! Setting to {h}3{s}."),
    CMDS_PER_PAGE_EMPTY("{s}Commands per page is empty in config! Setting to {h}7{s}."),
    TP_DELAY_EMPTY("{s}No teleport delay found in config! Setting to {h}0{s}."),

    DISABLE_START("{gold}=== {w}DISABLE {aqua}START {gold}==="),
    DISABLE_COMPLETE("{gold}=== {w}DISABLE {green}COMPLETE {gold}==="),

    SERVER_SPAWN_SET_DEFAULT("{s}Setting server spawn to {h}overworld {s}default spawn."),
    SERVER_SPAWN_SAVED("{s}Server spawn has been saved!"),

    WARPS_SECTION_NOT_DETECTED("{s}No warps section detected."),
    WARPS_LOADED("{s}Loaded {h}{count} {s}warps."),
    WARPS_CREATE_FAIL("{w}Failed to create warps file!"),

    ACCOUNTS_CREATE("{s}Created accounts file!"),
    ACCOUNTS_CREATE_FAIL("{w}Failed to created accounts file!"),
    ACCOUNTS_LOADED("{s}Loaded {h}{count} {s}accounts!"),
    ACCOUNTS_SAVE_FAIL("{w}Failed to save accounts file!"),

    /* =========================== PLUGIN SETUP/END RELATED MESSAGES END =============================== */
    ;

    private final String def;

    Lang(String def) {
        this.def = def;
    }

    public String getDefault() {
        return def;
    }
}
