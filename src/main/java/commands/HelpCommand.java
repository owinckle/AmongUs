package commands;

import me.yukinox.amongus.Plugin;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class HelpCommand {
    private final Plugin plugin;

    public HelpCommand(Plugin plugin) {
        this.plugin = plugin;
    }

    public void execute(Player player) {
        FileConfiguration config = plugin.getPluginConfig();

        player.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("commands.help.title")));
        if (player.isOp() || player.hasPermission("au.admin")) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',config.getString("commands.help.command")
                    .replace("%command%", config.getString("commands.reload.usage"))
                    .replace("%description%", config.getString("commands.reload.description"))));
        }

        // Commands
        player.sendMessage(ChatColor.translateAlternateColorCodes('&',config.getString("commands.help.command")
                .replace("%command%", config.getString("commands.help.usage"))
                .replace("%description%", config.getString("commands.help.description"))));
    }
}
