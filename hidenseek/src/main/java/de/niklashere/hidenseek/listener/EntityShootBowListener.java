package de.niklashere.hidenseek.listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;

import de.niklashere.hidenseek.gamestates.Rolemanager;
import de.niklashere.hidenseek.libary.ItemBuilder;

public class EntityShootBowListener implements Listener {
    
    @EventHandler
    public void onEntityShootBow (EntityShootBowEvent e) {
        e.setCancelled(false);
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if (Rolemanager.isSeeker(p)) {
                p.getInventory().addItem(new ItemBuilder(Material.ARROW).build());
            }
        }
    }
}
