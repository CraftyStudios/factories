package me.CraftyStudios.factories.Events;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Chest;
import org.bukkit.plugin.java.JavaPlugin;

public class Quary {
    private final JavaPlugin plugin;

    public Quary(JavaPlugin plugin) {
        this.plugin = plugin;
    }
    public void quarryMine(Location quarryLocation) {
        World world = quarryLocation.getWorld();
        int x = quarryLocation.getBlockX();
        int y = quarryLocation.getBlockY();
        int z = quarryLocation.getBlockZ();
        
        List<ItemStack> droppedItems = new ArrayList<>();
        for (int i = x - 1; i <= x + 2; i++) {
            for (int j = y; j >= y - 3; j--) {
                for (int k = z - 1; k <= z + 2; k++) {
                    Block block = world.getBlockAt(i, j, k);
                    if (block.getType() == Material.BEDROCK) {
                        continue;
                    }
                    Collection<ItemStack> drops = block.getDrops();
                    droppedItems.addAll(drops);
                    block.setType(Material.AIR);
                }
            }
        } 
        
        Block chestBlock = world.getBlockAt(x, y + 1, z);
        if (chestBlock.getType() != Material.CHEST) {
            chestBlock.setType(Material.CHEST);
        }
        Chest chest = (Chest) chestBlock.getState();
        InventoryHolder holder = (InventoryHolder) chest;
        Inventory chestInventory = holder.getInventory();
        for (ItemStack itemStack : droppedItems) {
            chestInventory.addItem(itemStack);
        }
        
        world.regenerateChunk(x >> 4, z >> 4, null, false);

    }
    
    
    
    
}
