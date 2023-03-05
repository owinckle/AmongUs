package me.yukinox.pixelmystery.commands;

import me.yukinox.pixelmystery.managers.MapManager;
import me.yukinox.pixelmystery.Plugin;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class MapCommands {
    private final Plugin plugin;

    public MapCommands(Plugin plugin) {
        this.plugin = plugin;
    }

    public void create(Player player, String mapName) {
        FileConfiguration config = plugin.getPluginConfig();
        MapManager mapManager = plugin.getMapManager();
        if (mapManager.create(player, mapName)) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("commands.mapCreate.success"))
                    .replace("%map%", mapName)
            );
        }
    }

    public void delete(Player player, String mapName) {
        FileConfiguration config = plugin.getPluginConfig();
        MapManager mapManager = plugin.getMapManager();

        if (mapManager.delete(player, mapName)) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("commands.mapDelete.success"))
                    .replace("%map%", mapName)
            );
        }
    }
}
