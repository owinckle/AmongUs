package me.yukinox.pixelmystery.listeners;

import me.yukinox.pixelmystery.Plugin;
import me.yukinox.pixelmystery.menus.MapsMenu;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MenuListener implements Listener {
    private final Plugin plugin;

    public MenuListener(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onMenuItemClick(InventoryClickEvent e) {
        FileConfiguration config = plugin.getPluginConfig();

        String inventoryName = e.getInventory().getName();
        String joinInventoryName = config.getString("menus.join.title");
        String mapsInventoryName = config.getString("menus.maps.title");
        String mapInventoryName = config.getString("menus.map.title");

        MapsMenu mapsMenu = new MapsMenu(plugin);

        if (inventoryName.equals(joinInventoryName)) {
            e.setCancelled(true);
        } else if (inventoryName.equals(mapsInventoryName)) {
            e.setCancelled(true);
        } else if (inventoryName.equals(mapInventoryName)) {
            mapsMenu.handler(e);
        }
    }
}
