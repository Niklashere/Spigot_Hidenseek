package de.niklashere.hidenseek.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

/**
 * Listener for the BlockBreakEvent.
 *
 * @author Niklashere
 * @since 31.07.2021
 */
public class BlockBreakListener implements Listener {

  /**
   * Called when the event occurs.
   * 
   * @param e event
   */
  @EventHandler
  public void onBreak(BlockBreakEvent e) {
    e.setCancelled(true);
  }
}
