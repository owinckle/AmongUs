package managers;

import me.yukinox.amongus.Plugin;
import objects.Map;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

public class MapManager {
    private final Plugin plugin;
    private FileConfiguration config;
    private File configFile;

    private HashMap<UUID, Location> firstSelection;
    private HashMap<UUID, Location> secondSelection;

    public MapManager(Plugin plugin) {
        this.plugin = plugin;
        firstSelection = new HashMap<>();
        secondSelection = new HashMap<>();

        initConfig();
    }

    private void initConfig() {
        configFile = new File(plugin.getDataFolder(), "maps.yml");
        if (!configFile.exists()) {
            configFile.getParentFile().mkdirs();
            try {
                configFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        config = YamlConfiguration.loadConfiguration(configFile);
    }

    private void saveConfig() {
        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setFirstSelection(Player player, Location loc) {
        firstSelection.put(player.getUniqueId(), loc);
        int x = (int) loc.getX();
        int y = (int) loc.getY();
        int z = (int) loc.getZ();
        player.sendMessage(ChatColor.GREEN + "[Among Us] Première sélection : " + x + ", " + y + ", " + z);
    }

    public void setSecondSelection(Player player, Location loc) {
        secondSelection.put(player.getUniqueId(), loc);
        int x = (int) loc.getX();
        int y = (int) loc.getY();
        int z = (int) loc.getZ();
        player.sendMessage(ChatColor.GREEN + "[Among Us] Deuxième sélection : " + x + ", " + y + ", " + z);
    }

        private Location[] formatLocations(Location loc1, Location loc2) {
		int x1 = Math.min((int) loc1.getX(), (int) loc2.getX());
		int y1 = Math.min((int) loc1.getY(), (int) loc2.getY());
		int z1 = Math.min((int) loc1.getZ(), (int) loc2.getZ());
		int x2 = Math.max((int) loc1.getX(), (int) loc2.getX());
		int y2 = Math.max((int) loc1.getY(), (int) loc2.getY());
		int z2 = Math.max((int) loc1.getZ(), (int) loc2.getZ());

        Location[] locations = new Location[2];
        locations[0] = new Location(loc1.getWorld(), x1, y1, z1);
        locations[1] = new Location(loc1.getWorld(), x2, y2, z2);
        return locations;
    }

    public boolean create(Player player, String mapName) {
        Location loc1 = firstSelection.get(player.getUniqueId());
        Location loc2 = secondSelection.get(player.getUniqueId());

        if (loc1 == null || loc2 == null || loc1.getWorld() != loc2.getWorld()) {
            player.sendMessage(ChatColor.RED + "[Among Us] Sélections invalides.");
            return false;
        }

        if (config.get(mapName) != null) {
            player.sendMessage(ChatColor.RED + "[Among Us] Une map avec le même nom existe déjà.");
            return false;
        }

        String world = loc1.getWorld().getName();
        Location[] locations = formatLocations(loc1, loc2);

        config.set(mapName + ".map.world", world);
        config.set(mapName + ".map.from.x", (int) locations[0].getX());
        config.set(mapName + ".map.from.y", (int) locations[0].getY());
        config.set(mapName + ".map.from.z", (int) locations[0].getZ());
        config.set(mapName + ".map.to.x", (int) locations[1].getX());
        config.set(mapName + ".map.to.y", (int) locations[1].getY());
        config.set(mapName + ".map.to.z", (int) locations[1].getZ());

        setImpostor(mapName, 2);
        setMinPlayers(mapName, 4);
        setMaxPlayers(mapName, 10);
        setMeetingDuration(mapName, 60);
        setEmergencyCooldown(mapName, 30);
        setEmergencyLimit(mapName, 1);
        setKillCooldown(mapName, 25);
        saveConfig();
        return true;
    }

    public boolean delete(Player player, String mapName) {
        if (config.get(mapName) == null) {
            player.sendMessage(ChatColor.RED + "[Among Us] Cette map n'existe pas.");
            return false;
        }

        config.set(mapName, null);
        saveConfig();
        return true;
    }

    public void setImpostor(String mapName, int amount) {
        config.set(mapName + ".settings.impostors", amount);
    }

    public void setMinPlayers(String mapName, int amount) {
        config.set(mapName + ".settings.minPlayers", amount);
    }

    public void setMaxPlayers(String mapName, int amount) {
        config.set(mapName + ".settings.maxPlayers", amount);
    }

    public void setMeetingDuration(String mapName, int amount) {
        config.set(mapName + ".settings.meetingDuration", amount);
    }

    public void setEmergencyCooldown(String mapName, int amount) {
        config.set(mapName + ".settings.emergencyCooldown", amount);
    }

    public void setEmergencyLimit(String mapName, int amount) {
        config.set(mapName + ".settings.emergencyLimit", amount);
    }

    public void setKillCooldown(String mapName, int amount) {
        config.set(mapName + ".settings.killCooldown", amount);
    }

    public Map[] getMaps() {
        Set<String> mapNames = config.getKeys(false);
        Map[] maps = new Map[mapNames.size()];

        int i = 0;
        for (String mapName : mapNames) {
            ConfigurationSection mapSettings = config.getConfigurationSection(mapName + ".settings");
            maps[i] = new Map(mapName,
                    mapSettings.getInt("minPlayers"),
                    mapSettings.getInt("maxPlayers"),
                    mapSettings.getInt("meetingDuration"),
                    mapSettings.getInt("emergencyCooldown"),
                    mapSettings.getInt("emergencyLimit"),
                    mapSettings.getInt("killCooldown")
            );
            i++;
        }

        return maps;
    }
}
