package me.CraftyStudios.factories.utils;

import org.bukkit.entity.ArmorStand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEntityEvent;

class HolographicsHandler {
@EventHandler
    public void onHolographicInteract(PlayerInteractEntityEvent event) {
        if (event.getRightClicked() instanceof ArmorStand) {
            ArmorStand armorStand = (ArmorStand) event.getRightClicked();
            if(armorStand.getCustomName() != null) {
            }
        }
    }
}