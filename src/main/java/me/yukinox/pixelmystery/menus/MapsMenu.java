package me.yukinox.pixelmystery.menus;

import me.yukinox.pixelmystery.managers.ItemManager;
import me.yukinox.pixelmystery.Plugin;
import me.yukinox.pixelmystery.managers.MapManager;
import me.yukinox.pixelmystery.objects.Map;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class MapsMenu {

    private final Plugin plugin;
    private final int size = 9;

    public MapsMenu(Plugin plugin) {
        this.plugin = plugin;
    }

    public void open(Player player) {
        FileConfiguration config = plugin.getPluginConfig();
        Inventory menu = Bukkit.createInventory(null, size, config.getString("menus.maps.title"));
        Map[] maps = plugin.getMapManager().getMaps();
        ItemManager itemManager = plugin.getItemManager();

        for (int i = 0; i < maps.length; i++) {
            menu.setItem(i, itemManager.getMapItem(maps[i]));
        }

        for (int i = maps.length; i < size; i++) {
            menu.setItem(i, itemManager.getEmptyItem());
        }

        player.openInventory(menu);
    }

    public void handler(InventoryClickEvent e) {
        e.setCancelled(true);

        Player player = (Player) e.getWhoClicked();
        ItemStack clickedItem = e.getCurrentItem();

        if (clickedItem.getType() != Material.PAPER) return;

        String itemName = ChatColor.stripColor(clickedItem.getItemMeta().getDisplayName());
        MapManager mapManager = plugin.getMapManager();
        Map map = mapManager.getMap(itemName);

        if (map == null) return;

        MapMenu mapMenu = new MapMenu(plugin);
        mapMenu.open(player);
    }
}
