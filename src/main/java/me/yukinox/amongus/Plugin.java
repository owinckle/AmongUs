package me.yukinox.amongus;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class Plugin extends JavaPlugin {
    private FileConfiguration pluginConfig;

    @Override
    public void onEnable() {
        initPluginConfig();

        // Main command
        getCommand("au").setExecutor(new Executor(this));
    }

    private void initPluginConfig() {
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        reloadConfig();
        pluginConfig = getConfig();
    }

    public FileConfiguration getPluginConfig() {
        return pluginConfig;
    }

    public void setPluginConfig(FileConfiguration config) {
        pluginConfig = config;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
