package de.niklashere.hidenseek.listener;

import de.niklashere.hidenseek.gamestates.RoleManager;
import de.niklashere.hidenseek.libary.ItemBuilder;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;

/**
 * Listener for the EntityDamageEvent.
 *
 * @author Niklashere
 * @since 01.08.2021
 */
public class EntityShootBowListener implements Listener {

  /**
   * Called when the event occurs.
   * 
   * @param e event
   */
  @EventHandler
  public void onEntityShootBow(EntityShootBowEvent e) {
    e.setCancelled(false);
    if (e.getEntity() instanceof Player) {
      Player p = (Player) e.getEntity();
      if (RoleManager.playerList.get(RoleManager.getPlayer(p)).isSeeker()) {
        p.getInventory().addItem(new ItemBuilder(Material.ARROW).build());
      }
    }
  }
}
