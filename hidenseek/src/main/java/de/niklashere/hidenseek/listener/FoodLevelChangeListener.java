package de.niklashere.hidenseek.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

/**
 * Listener for the FoodLevelChangeEvent.
 *
 * @author Niklashere
 * @since 31.07.2021
 */
public class FoodLevelChangeListener implements Listener {

  /**
   * Called when the event occurs.
   * 
   * @param e event
   */
  @EventHandler
  public void onFoodLevelChange(FoodLevelChangeEvent e) {
    e.setCancelled(true);
  }
}
