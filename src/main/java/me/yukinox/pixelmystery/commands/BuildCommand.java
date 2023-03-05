package me.yukinox.pixelmystery.commands;

import me.yukinox.pixelmystery.Plugin;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BuildCommand {
    private final Plugin plugin;

    public BuildCommand(Plugin plugin) {
        this.plugin = plugin;
    }
    
    public void execute(Player player) {
        if (!player.hasPermission("pixelmystery.admin") && !player.isOp()) return;

        FileConfiguration config = plugin.getPluginConfig();
        ItemStack wand = new ItemStack(Material.BLAZE_ROD, 1);
        ItemMeta meta = wand.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Baguette Magique");
        wand.setItemMeta(meta);
        player.setItemInHand(wand);
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("commands.build.success")));
    }
}
