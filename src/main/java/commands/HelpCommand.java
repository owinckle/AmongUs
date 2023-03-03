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
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',config.getString("commands.help.commandAdmin")
                    .replace("%command%", config.getString("commands.reload.usage"))
                    .replace("%description%", config.getString("commands.reload.description")))
            );
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',config.getString("commands.help.commandAdmin")
                    .replace("%command%", config.getString("commands.build.usage"))
                    .replace("%description%", config.getString("commands.build.description")))
            );
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',config.getString("commands.help.commandAdmin")
                    .replace("%command%", config.getString("commands.maps.usage"))
                    .replace("%description%", config.getString("commands.maps.description")))
            );
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',config.getString("commands.help.commandAdmin")
                    .replace("%command%", config.getString("commands.mapCreate.usage"))
                    .replace("%description%", config.getString("commands.mapCreate.description")))
            );
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',config.getString("commands.help.commandAdmin")
                    .replace("%command%", config.getString("commands.mapDelete.usage"))
                    .replace("%description%", config.getString("commands.mapDelete.description")))
            );
        }

        // Commands
        player.sendMessage(ChatColor.translateAlternateColorCodes('&',config.getString("commands.help.command")
                .replace("%command%", config.getString("commands.help.usage"))
                .replace("%description%", config.getString("commands.help.description")))
        );
        player.sendMessage(ChatColor.translateAlternateColorCodes('&',config.getString("commands.help.command")
                .replace("%command%", config.getString("commands.join.usage"))
                .replace("%description%", config.getString("commands.join.description")))
        );
    }
}
