package de.niklashere.hidenseek.listener;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;

/**
 * Listener for the EntityChangeBlockEvent.
 *
 * @author Niklashere
 * @since 31-07-2021
 */
public class EntityChangeBlockListener implements Listener {

  @EventHandler
  public void onEntityChangeBlock(EntityChangeBlockEvent e) {
    if (e.getEntityType() == EntityType.FALLING_BLOCK) {
      e.setCancelled(true);
    }
  }
}
