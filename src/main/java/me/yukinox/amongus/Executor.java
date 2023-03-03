package me.yukinox.amongus;

import commands.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Executor implements CommandExecutor {
    private final Plugin plugin;

    public Executor(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return true;

        Player player = (Player) sender;

        HelpCommand helpCommand = new HelpCommand(plugin);
        ReloadCommand reloadCommand = new ReloadCommand(plugin);
        JoinCommand joinCommand = new JoinCommand(plugin);
        BuildCommand buildCommand = new BuildCommand(plugin);
        MapCommands mapCommands = new MapCommands(plugin);
        MapsCommand mapsCommand = new MapsCommand(plugin);

        if (args.length == 0) {
            helpCommand.execute(player);
        } else if (args.length == 1) {
            if (args[0].equals("help")) {
                helpCommand.execute(player);
            } else if (args[0].equals("reload")) {
                reloadCommand.execute(player);
            } else if (args[0].equals("join")) {
                joinCommand.execute(player);
            } else if (args[0].equals("build")) {
                buildCommand.execute(player);
            } else if (args[0].equals("maps")) {
                mapsCommand.execute(player);
            } else {
                helpCommand.execute(player);
            }
        } else if (args.length == 3) {
            if (args[0].equals("map")) {
                if (!player.hasPermission("au.admin") && !player.isOp()) return true;

                if (args[1].equals("create")) {
                    mapCommands.create(player, args[2]);
                } else if (args[1].equals("delete")) {
                    mapCommands.delete(player, args[2]);
                }
            }
        }

        return true;
    }
}
