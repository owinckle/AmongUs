package me.yukinox.pixelmystery.commands;

import me.yukinox.pixelmystery.Plugin;
import me.yukinox.pixelmystery.menus.MapsMenu;
import org.bukkit.entity.Player;

public class MapsCommand {
    private final Plugin plugin;

    public MapsCommand(Plugin plugin) {
        this.plugin = plugin;
    }

    public void execute(Player player) {
        MapsMenu joinMenu = new MapsMenu(plugin);
        joinMenu.open(player);
    }
}
