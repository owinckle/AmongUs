package listeners;

import managers.MapManager;
import me.yukinox.amongus.Plugin;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class WandListener implements Listener {
    private final Plugin plugin;

    public WandListener(Plugin plugin) {
        this.plugin = plugin;
    }
    
    @EventHandler
    public void onWandClick(PlayerInteractEvent e) {
        if (e.getClickedBlock() == null) return;

        Player player = e.getPlayer();
        if (!eventIsValid(player)) return;
        e.setCancelled(true);

        MapManager mapManager = plugin.getMapManager();
        if (e.getAction() == Action.LEFT_CLICK_BLOCK) {
            mapManager.setFirstSelection(player, e.getClickedBlock().getLocation());
        } else if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            mapManager.setSecondSelection(player, e.getClickedBlock().getLocation());
        }
    }

    @EventHandler
    public void onWandBreak(BlockBreakEvent e) {
        Player player = e.getPlayer();
        if (!eventIsValid(player)) return;
        e.setCancelled(true);
    }

    private boolean eventIsValid(Player player) {
        if (!player.hasPermission("au.admin") && !player.isOp()) return false;

        ItemStack itemInHand = player.getItemInHand();
        if (itemInHand.getType() != Material.BLAZE_ROD) return false;
        return itemInHand.getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Baguette Magique");
    }
}
