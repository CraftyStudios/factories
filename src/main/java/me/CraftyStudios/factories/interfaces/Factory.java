package me.CraftyStudios.factories.interfaces;
import me.CraftyStudios.factories.Main;
import me.CraftyStudios.factories.Events.NewFactory;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;


public class Factory {
    private final JavaPlugin plugin;

    public Factory(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    

    private void createFactoryGUI() {

    
        Inventory FactoryGUI = Bukkit.createInventory(null, 45, "Factories");

        ItemStack filler = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta fillerMeta = filler.getItemMeta();
        fillerMeta.setDisplayName(" ");
        filler.setItemMeta(fillerMeta);

        ItemStack teleFactory = new ItemStack(Material.DIAMOND);
        ItemMeta teleFactoryMeta = teleFactory.getItemMeta();
        teleFactoryMeta.setDisplayName("§aTeleport to your factory");
        teleFactoryMeta.setLore(Arrays.asList("§7Click to teleport to your personal factory\n§7What are factories? \n§7Factories are powered by you being afk. \n§7The more you are afk, the more time your factories are powered!"));
        teleFactory.setItemMeta(teleFactoryMeta);

        
        FactoryGUI.setItem(22, teleFactory);
        FactoryGUI.setItem(0, filler);

        for (int i = 0; i < 45; i++) {
            if (i < 9 || i > 35) {
                FactoryGUI.setItem(i, filler);
            }
        }

    Bukkit.getPluginManager().registerEvents(new GUIListener(), Main.getPlugin(Main.class));
    }

    private class GUIListener implements Listener {
        @EventHandler
        public void onInventoryClick(InventoryClickEvent event, Inventory FactoryGUI) {
            if (event.getClickedInventory() != null && event.getClickedInventory().equals(FactoryGUI)) {
                int slot = event.getSlot();
                if (slot == 0) {
                    event.setCancelled(true);
                } else {
                    event.setCancelled(true);
                }
                if (slot == 22) {    
                    Player player = (Player) event.getWhoClicked();
                    NewFactory newFactory = new NewFactory(plugin);
                    newFactory.teleportPlayerToFactory(player);
                    event.setCancelled(true);
                } else {
                    event.setCancelled(true);
                }                
            }
        }
    }
}