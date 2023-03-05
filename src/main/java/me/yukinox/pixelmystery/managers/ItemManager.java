package me.yukinox.pixelmystery.managers;

import me.yukinox.pixelmystery.Plugin;
import me.yukinox.pixelmystery.objects.Map;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {
    private final Plugin plugin;

    public ItemManager(Plugin plugin) {
        this.plugin = plugin;
    }

    public ItemStack getEmptyItem() {
        ItemStack item = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)15);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.GREEN + "");
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack getMapItem(Map map) {
        FileConfiguration config = plugin.getPluginConfig();
        ItemStack item = new ItemStack(Material.PAPER, 1);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();

        meta.setDisplayName(ChatColor.GREEN + map.name);
        lore.add(ChatColor.translateAlternateColorCodes('&',
                config.getString("messages.mapSettings.minPlayers").
                        replace("%value%", Integer.toString(map.minPlayers))));
        lore.add(ChatColor.translateAlternateColorCodes('&',
                config.getString("messages.mapSettings.maxPlayers").
                        replace("%value%", Integer.toString(map.maxPlayers))));
        lore.add(ChatColor.translateAlternateColorCodes('&',
                config.getString("messages.mapSettings.meetingDuration").
                        replace("%value%", Integer.toString(map.meetingDuration))));
        lore.add(ChatColor.translateAlternateColorCodes('&',
                config.getString("messages.mapSettings.emergencyCooldown").
                        replace("%value%", Integer.toString(map.emergencyCooldown))));
        lore.add(ChatColor.translateAlternateColorCodes('&',
                config.getString("messages.mapSettings.emergencyLimit").
                        replace("%value%", Integer.toString(map.emergencyLimit))));
        lore.add(ChatColor.translateAlternateColorCodes('&',
                config.getString("messages.mapSettings.killCooldown").
                        replace("%value%", Integer.toString(map.killCooldown))));
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }
}
