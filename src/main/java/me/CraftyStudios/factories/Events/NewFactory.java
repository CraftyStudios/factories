package me.CraftyStudios.factories.Events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.CraftyStudios.factories.utils.WorldManager;

public class NewFactory {
    private final JavaPlugin plugin;

    public NewFactory(JavaPlugin plugin) {
        this.plugin = plugin;
    }
    public void newFactory(Player player) {
        String factoryName = "Factory - " + player.getName();
        WorldManager worldManager = new WorldManager(plugin);
        worldManager.cloneWorld(factoryName);
    }
    //NewFactory.newFactory(player);
    public void teleportPlayerToFactory(Player player) {
        World factoryWorld = Bukkit.getWorld("Factory - " + player.getName());
        if (factoryWorld != null) {
            Location spawnLocation = factoryWorld.getSpawnLocation();
            player.teleport(spawnLocation);
        } else {
            player.sendMessage(ChatColor.RED + "Factory world not found.");
        }
    }
    //NewFactory.teleportPlayerToFactory(player);
    
}
