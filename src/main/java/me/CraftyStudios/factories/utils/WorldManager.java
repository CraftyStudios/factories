package me.CraftyStudios.factories.utils;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorldManager {
    private final JavaPlugin plugin;

    public WorldManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }
    public void cloneWorld(String clonedWorldName) {
        Server server = Bukkit.getServer();
        World originalWorld = server.getWorld("factoryworld");
        if (originalWorld == null) {
            throw new IllegalArgumentException("Original world does not exist");
        }
    
        // Disable unnecessary features in the cloned world
        WorldCreator worldCreator = new WorldCreator(clonedWorldName);
        worldCreator.generator(originalWorld.getGenerator().toString());
        worldCreator.environment(originalWorld.getEnvironment());
        worldCreator.generateStructures(originalWorld.canGenerateStructures());
        worldCreator.seed(originalWorld.getSeed());
        worldCreator.hardcore(originalWorld.isHardcore());
        worldCreator.createWorld().setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        worldCreator.createWorld().setGameRule(GameRule.DO_WEATHER_CYCLE, false);
        World clonedWorld = worldCreator.createWorld();
        clonedWorld.setGameRule(GameRule.DO_MOB_SPAWNING, false);  
        // Create the cloned world asynchronously
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            File originalWorldFolder = originalWorld.getWorldFolder();
            File clonedWorldFolder = clonedWorld.getWorldFolder();
            try {
                Files.walk(originalWorldFolder.toPath())
                     .forEach(source -> {
                         Path destination = clonedWorldFolder.toPath().resolve(originalWorldFolder.toPath().relativize(source));
                         try {
                             Files.copy(source, destination);
                         } catch (IOException e) {
                             e.printStackTrace();
                         }
                     });
                Bukkit.getScheduler().runTask(plugin, () -> {
                    server.createWorld(new WorldCreator(clonedWorldName));
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    

    //WorldManager.cloneWorld(factoryworldname);
    //use this to clone the factory world for the player
}
