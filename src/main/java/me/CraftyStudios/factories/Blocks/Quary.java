package me.CraftyStudios.factories.Blocks;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Quary implements Listener {
    private final JavaPlugin plugin;

    public Quary(JavaPlugin plugin) {
        this.plugin = plugin;
    }


    // Create a custom block with the given Material and place it at the given Block location
    public void createBlock(Material material, Block block) {
        block.setType(material);
        plugin.getLogger().info("A custom block was placed at " + block.getLocation());
    }

    // Handle right-click events on the custom block
    @EventHandler
    public void onBlockInteract(PlayerInteractEvent event) {
        if (event.getClickedBlock() != null && event.getClickedBlock().getType() == Material.COPPER_BLOCK) {
            Player player = event.getPlayer();
            Block block = event.getClickedBlock();
            plugin.getLogger().info(player.getName() + " interacted with the custom block at " + block.getLocation());
            // Add your custom behavior here
            //plugin.getServer().getPluginManager().registerEvents(new CustomBlock(plugin), plugin);
        }
    }
}
