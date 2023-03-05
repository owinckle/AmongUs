package me.yukinox.pixelmystery.menus;

import me.yukinox.pixelmystery.Plugin;
import me.yukinox.pixelmystery.managers.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class MapMenu {
    private final Plugin plugin;
    private final int size = 9 * 4;

    public MapMenu(Plugin plugin) {
        this.plugin = plugin;
    }

    public void open(Player player) {
        FileConfiguration config = plugin.getPluginConfig();
        Inventory menu = Bukkit.createInventory(null, size, config.getString("menus.map.title"));
        ItemManager itemManager = plugin.getItemManager();

        for (int i = 0; i < size; i++) {
            menu.setItem(i, itemManager.getEmptyItem());
        }

        player.openInventory(menu);
    }
}
