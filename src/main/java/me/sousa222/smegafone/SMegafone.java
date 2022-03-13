package me.sousa222.smegafone;

import me.sousa222.smegafone.commands.MegafoneCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class SMegafone extends JavaPlugin {

    private static SMegafone plugin;
    @Override
    public void onEnable() {
        plugin = this;
        loadConfig();
        loadCommands();
        System.out.println("sMegafone habilitado com sucesso.");
    }

    public void loadConfig(){
        this.saveDefaultConfig();
        this.getConfig().options().copyDefaults();
    }
    public static SMegafone getPlugin(){
        return plugin;
    }
    public void loadCommands(){
        getCommand("megafone").setExecutor(new MegafoneCommand());
    }
}
