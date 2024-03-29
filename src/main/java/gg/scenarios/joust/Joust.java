package gg.scenarios.joust;

import gg.scenarios.joust.commands.BracketCommand;
import gg.scenarios.joust.commands.RulesCommand;
import gg.scenarios.joust.commands.TournamentAdmin;
import gg.scenarios.joust.commands.WhitelistCommand;
import gg.scenarios.joust.listeners.PlayerListener;
import gg.scenarios.joust.managers.arena.ArenaManager;
import gg.scenarios.joust.managers.Tournament;
import gg.scenarios.joust.managers.kit.KitManager;
import gg.scenarios.joust.nms.NMS;
import gg.scenarios.joust.nms.verisons.v1_8_R3;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Getter
public class Joust extends JavaPlugin {

    @Getter
    private static Joust instance;
    public static List<UUID> mods = new ArrayList<>();
    private ArenaManager arenaManager;
    @Getter private Tournament tournament;
    private final String API_KEY = "gGw4BJ2lSNMtbrUlSgz423EMULM05o2sIdZWu0Mn";

    private final String PREFIX = "&8[&3Tournament&8]&r ";
    private final String pvpPrefix = "&8[&4&lPvP&8]&8 »&r ";

    private KitManager kitManager;
    @Getter
    private NMS nms = new v1_8_R3();


    @Override
    public void onEnable() {
        saveDefaultConfig();
        instance = this;
        arenaManager = new ArenaManager();
        arenaManager.init();
        tournament = new Tournament();
        tournament.setApiKey(API_KEY);
        kitManager = new KitManager();
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerListener(), this);
        getCommand("tournament").setExecutor(new TournamentAdmin());
        getCommand("bracket").setExecutor(new BracketCommand());
        getCommand("rules").setExecutor(new RulesCommand());
        getCommand("whitelist").setExecutor(new WhitelistCommand());
        System.out.println(tournament);
    }


    @Override
    public void onDisable(){
        saveConfig();
    }

    public static Joust getInstance() {
        return instance;
    }
}
