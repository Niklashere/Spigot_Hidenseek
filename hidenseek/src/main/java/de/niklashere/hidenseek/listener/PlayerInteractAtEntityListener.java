package de.niklashere.hidenseek.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

public class PlayerInteractAtEntityListener implements Listener {

    @EventHandler
    public void onInteractatEntity(PlayerInteractAtEntityEvent e) {
        e.setCancelled(true);
    }
}
