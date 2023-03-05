package me.yukinox.pixelmystery.commands;

import me.yukinox.pixelmystery.Plugin;
import me.yukinox.pixelmystery.menus.JoinMenu;
import org.bukkit.entity.Player;

public class JoinCommand {
    private final Plugin plugin;

    public JoinCommand(Plugin plugin) {
        this.plugin = plugin;
    }
    
    public void execute(Player player) {
        JoinMenu joinMenu = new JoinMenu(plugin);
        joinMenu.open(player);
    }
}
