package menus;

import managers.ItemManager;
import me.yukinox.amongus.Plugin;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class JoinMenu {
    private final Plugin plugin;
    private final int size = 9;

    public JoinMenu(Plugin plugin) {
        this.plugin = plugin;
    }

    public void open(Player player) {
        FileConfiguration config = plugin.getPluginConfig();
        Inventory menu = Bukkit.createInventory(null, size, config.getString("menus.join.title"));
        ItemManager itemManager = plugin.getItemManager();

        for (int i = 0; i < size; i++) {
            menu.setItem(i, itemManager.getEmptyItem());
        }

        player.openInventory(menu);
    }
}
