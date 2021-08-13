package de.niklashere.hidenseek.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

/**
 * Listener for the PlayerDropItemListener.
 *
 * @author Niklashere
 * @since 31.07.2021
 */
public class PlayerDropItemListener implements Listener {

  /**
   * Called when the event occurs.
   * 
   * @param e event
   */
  @EventHandler
  public void onPlayerDropItem(PlayerDropItemEvent e) {
    e.setCancelled(true);
  }
}
