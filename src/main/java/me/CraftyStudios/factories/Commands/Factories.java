package me.CraftyStudios.factories.Commands;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;

public class Factories implements CommandExecutor, TabCompleter{
    private final JavaPlugin plugin;

    public Factories(JavaPlugin plugin) {
        this.plugin = plugin;
    }


    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> completions = new ArrayList<>();
        if (cmd.getName().equalsIgnoreCase("factories")) {
            if (args.length == 1) {
                //TODO: Add your tab completions here
                completions.add("set");
            }
        }
        return completions;
    }
    @Override
public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (args.length == 0) {
        if (sender.hasPermission("factories.main")) {
            sender.sendMessage(""
                    + "&8----------------------------------------\n"
                    + "&7&lFactories\n"
                    + "\n"
                    + "&7/factories help &8- &7Shows this message\n"
                    + "&7/factories admin &8- &7Shows admin panel\n"
                    + "&8----------------------------------------\n"
                    );
                return true;
            } else {
                // I don't know what you want to do here, but you need to do something.
            }
        }
        if(args.length > 0 && args[0].equalsIgnoreCase("set")) {
            if (sender.hasPermission("afkrewards.setregion")) {
                if (args.length == 2) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Prefix") + "&a&lRegion set to: " + args[1]));
                    plugin.getConfig().set("afk-region", args[1]);
                    plugin.saveConfig();
                    return true;
                }else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Prefix") + "&c&lUsage: /afkrewards region <region>"));
                    return false;
                }
            }else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Prefix") + plugin.getConfig().getString("no-permission")));
                return false;
            }
        }
        return false;
    }
}
