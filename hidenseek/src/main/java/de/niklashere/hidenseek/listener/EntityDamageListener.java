package de.niklashere.hidenseek.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

/**
 * Listener for the EntityDamageEvent.
 *
 * @author Niklashere
 * @since 31.07.2021
 */
public class EntityDamageListener implements Listener {

  /**
   * Called when the event occurs.
   * 
   * @param e event
   */
  @EventHandler
  public void onDamage(EntityDamageEvent e) {
    e.setCancelled(true);

  }
}
