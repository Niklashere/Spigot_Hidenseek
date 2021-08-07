package de.niklashere.hidenseek.listener;

import de.niklashere.hidenseek.files.languages.Variablelist;
import de.niklashere.hidenseek.gamestates.RoleManager;
import de.niklashere.hidenseek.inventorys.HintInventory;
import de.niklashere.hidenseek.inventorys.MapvotingInventory;
import de.niklashere.hidenseek.inventorys.RoleInventory;
import de.niklashere.hidenseek.libary.LanguageManager;

import de.niklashere.hidenseek.libary.PropManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Listener for the PlayerInteractEvent.
 *
 * @author Niklashere
 * @since 31.07.2021
 */
public class PlayerInteractListener implements Listener {

  /**
   * Called when the event occurs.
   * 
   * @param e event
   */
  @EventHandler
  public void onInteract(PlayerInteractEvent e) {
    Player p = e.getPlayer();
    if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
      System.out.println(1);
      for (Player all :Bukkit.getOnlinePlayers()){
        if (PropManager.blockList.get(all) != null) {
        System.out.println(e.getClickedBlock().getLocation().getBlock().getLocation() + "  " + PropManager.blockList.get(all).getBlock().getLocation());
        if (e.getClickedBlock().getLocation().getBlock().getLocation().equals(PropManager.blockList.get(all).getBlock().getLocation())) {
          System.out.println(3);
          EntityDamageByEntityListener.damage(all, p);
        }
        }
      }
    }
    if (e.getItem() != null && e.getItem().hasItemMeta()) {
      if (e.getItem().getType() == Material.BOW) {
        e.setCancelled(false);
      } else {
        e.setCancelled(true);

      }

      if (e.getItem().getItemMeta().getDisplayName()
          .equalsIgnoreCase(LanguageManager.getMessage(Variablelist.items_lobby_chest, p))) {
        MapvotingInventory.openInventory(p);
      } else if (e.getItem().getItemMeta().getDisplayName()
          .equalsIgnoreCase(LanguageManager.getMessage(Variablelist.items_hider_hint, p))) {
        HintInventory.openInventory(p);
      } else if (e.getItem().getItemMeta().getDisplayName()
          .equalsIgnoreCase(LanguageManager.getMessage(Variablelist.items_lobby_redBanner, p))) {
        RoleInventory.openInventory(p);
      }
    } else {
      e.setCancelled(true);

    }
  }
  @EventHandler
  public void onBLockDamage(BlockDamageEvent e) {
    System.out.println(e.getBlock().getType());
  }

}
