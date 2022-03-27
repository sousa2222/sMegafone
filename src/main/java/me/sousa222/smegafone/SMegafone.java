package me.sousa222.smegafone;

import me.sousa222.smegafone.commands.ManageCommand;

import me.sousa222.smegafone.commands.MegafoneCommand;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class SMegafone extends JavaPlugin {

    private static Economy econ = null;
    private static SMegafone plugin;

    @Override
    public void onEnable() {
        plugin = this;
        setupEconomy();
        loadConfig();
        loadCommands();
        startupMessage();
    }

    public void loadConfig(){
        getConfig().options().copyDefaults();
        saveDefaultConfig();
    }

    public void loadCommands(){
        getCommand("megafone").setExecutor(new MegafoneCommand());
        getCommand("smegafone").setExecutor(new ManageCommand());
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    public static SMegafone getPlugin(){
        return plugin;
    }

    public static Economy getEconomy() {
        return econ;
    }
    public void startupMessage(){
        Bukkit.getConsoleSender().sendMessage("\n" +
                "§a          _____                       _____                     \n" +
                "  ______ /     \\   ____   _________ _/ ____\\____   ____   ____  \n" +
                " /  ___//  \\ /  \\_/ __ \\ / ___\\__  \\\\   __\\/  _ \\ /    \\_/ __ \\ \n" +
                " \\___ \\/    Y    \\  ___// /_/  > __ \\|  | (  <_> )   |  \\  ___/ \n" +
                "/____  >____|__  /\\___  >___  (____  /__|  \\____/|___|  /\\___  >\n" +
                "     \\/        \\/     \\/_____/     \\/                 \\/     \\/ ");
        Bukkit.getConsoleSender().sendMessage("§a[sMegafone v1.1.0] Habilitado com sucesso!");
    }
}