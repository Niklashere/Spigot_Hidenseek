package de.niklashere.hidenseek.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

/**
 * Listener for the BlockPlaceEvent.
 *
 * @author Niklashere
 * @since 31.07.2021
 */
public class BlockPlaceListener implements Listener {

  /**
   * Called when the event occurs.
   * 
   * @param e event
   */
  @EventHandler
  public void onBlockPlace(BlockPlaceEvent e) {
    e.setCancelled(true);
  }
}
