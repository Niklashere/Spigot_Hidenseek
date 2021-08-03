package de.niklashere.hidenseek.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

/**
 * Listener for the PlayerInteractAtEntityEvent.
 *
 * @author Niklashere
 * @since 31.07.2021
 */
public class PlayerInteractAtEntityListener implements Listener {

  /**
   * Called when the event occurs.
   * 
   * @param e event
   */
  @EventHandler
  public void onInteractatEntity(PlayerInteractAtEntityEvent e) {
    e.setCancelled(true);
  }
}
