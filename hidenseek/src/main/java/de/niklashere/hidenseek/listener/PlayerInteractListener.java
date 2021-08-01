package de.niklashere.hidenseek.listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import de.niklashere.hidenseek.libary.LanguageManager;
import de.niklashere.hidenseek.libary.VariableManager;
import de.niklashere.inventorys.HintInventory;

public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if (e.getItem() != null && e.getItem().hasItemMeta()) {
            if (e.getItem().getType() == Material.BOW) {
                e.setCancelled(false);
            } else {
                e.setCancelled(true);

            }

            if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(VariableManager.message(LanguageManager.getMessage("item.hint", p), p))) {
                HintInventory.openInventory(p);
            }
        } else {
            e.setCancelled(true);

        }
    }
}
