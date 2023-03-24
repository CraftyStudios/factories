package me.CraftyStudios.factories.interfaces;
import me.CraftyStudios.factories.Main;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;


public class Admin {
    private Inventory adminGUI;

    private void createAdminGUI() {
        adminGUI = Bukkit.createInventory(null, 45, "Admin GUI");

        ItemStack filler = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta fillerMeta = filler.getItemMeta();
        fillerMeta.setDisplayName(" ");
        filler.setItemMeta(fillerMeta);
        for (int i = 0; i < 45; i++) {
            if (i < 9 || i > 35) {
                adminGUI.setItem(i, filler);
            }
        }

    Bukkit.getPluginManager().registerEvents(new GUIListener(), Main.getPlugin(Main.class));
    }

    private class GUIListener implements Listener {
        @EventHandler
        public void onInventoryClick(InventoryClickEvent event) {
            if (event.getClickedInventory() != null && event.getClickedInventory().equals(adminGUI)) {
                int slot = event.getSlot();
                if (slot == 0) {
                    event.setCancelled(true);
                } else {
                    event.setCancelled(true);
                }
            }
        }
    }
}