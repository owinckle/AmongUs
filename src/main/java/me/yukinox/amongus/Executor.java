package me.yukinox.amongus;

import commands.HelpCommand;
import commands.ReloadCommand;
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

        if (args.length == 0) {
            helpCommand.execute(player);
        } else if (args.length == 1) {
            switch (args[0]) {
                default:
                case "help":
                    helpCommand.execute(player);
                    return true;
                case "reload":
                    reloadCommand.execute(player);
                    return true;

            }
        }

        return true;
    }
}
