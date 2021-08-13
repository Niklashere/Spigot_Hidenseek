package de.niklashere.hidenseek.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;

/**
 * Listener for the EntityChangeBlockEvent.
 *
 * @author Niklashere
 * @since 06.08.2021
 */
public class EntityBlockChangeListener implements Listener {

  @EventHandler
  public void onEntityBlockChange(EntityChangeBlockEvent e) {
    e.setCancelled(true);

  }
}
