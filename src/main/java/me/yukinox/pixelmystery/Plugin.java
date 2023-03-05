package me.yukinox.pixelmystery;

import me.yukinox.pixelmystery.listeners.MenuListener;
import me.yukinox.pixelmystery.listeners.WandListener;
import me.yukinox.pixelmystery.managers.ItemManager;
import me.yukinox.pixelmystery.managers.MapManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class Plugin extends JavaPlugin {
    private FileConfiguration pluginConfig;
    private MapManager mapManager;
    private ItemManager itemManager;

    @Override
    public void onEnable() {
        initPluginConfig();
        mapManager = new MapManager(this);
        itemManager = new ItemManager(this);

        // Main command
        getCommand("mystery").setExecutor(new Executor(this));

        // Listeners
        getServer().getPluginManager().registerEvents(new MenuListener(this), this);
        getServer().getPluginManager().registerEvents(new WandListener(this), this);
    }

    public void initPluginConfig() {
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        reloadConfig();
        pluginConfig = getConfig();
    }

    public FileConfiguration getPluginConfig() {
        return pluginConfig;
    }

    public MapManager getMapManager() {
        return mapManager;
    }

    public ItemManager getItemManager() {
        return itemManager;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
