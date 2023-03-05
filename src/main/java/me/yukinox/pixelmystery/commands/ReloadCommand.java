package me.yukinox.pixelmystery.commands;

import me.yukinox.pixelmystery.Plugin;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class ReloadCommand {
    private final Plugin plugin;

    public ReloadCommand(Plugin plugin) {
        this.plugin = plugin;
    }

    public void execute(Player player) {
        if (!player.hasPermission("pixelmystery.admin") && !player.isOp()) return;

        FileConfiguration config = plugin.getConfig();
        plugin.initPluginConfig();
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("commands.reload.success")));
    }
}
