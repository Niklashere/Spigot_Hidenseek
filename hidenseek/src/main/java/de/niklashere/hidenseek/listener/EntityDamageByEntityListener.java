package de.niklashere.hidenseek.listener;

import de.niklashere.hidenseek.App;
import de.niklashere.hidenseek.files.languages.Variablelist;
import de.niklashere.hidenseek.gamestates.Gamestate;
import de.niklashere.hidenseek.gamestates.PlayerData;
import de.niklashere.hidenseek.gamestates.RoleManager;
import de.niklashere.hidenseek.libary.Fileaccess;
import de.niklashere.hidenseek.libary.ItemBuilder;
import de.niklashere.hidenseek.libary.LanguageManager;
import de.niklashere.hidenseek.libary.PropManager;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Silverfish;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Listener for the EntityDamageByEntityEvent.
 *
 * @author Niklashere
 * @since 01.08.2021
 */
public class EntityDamageByEntityListener implements Listener {

  private static HashMap<Player, Integer> timer = new HashMap<>();

  /**
   * Called when the event occurs.
   * 
   * @param e event
   */
  @EventHandler
  public void onDamage(EntityDamageByEntityEvent e) {
    e.setCancelled(true);
    if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
      Player p = (Player) e.getEntity();
      Player k = (Player) e.getDamager();

      damage(p, k);
    } else if (e.getEntity() instanceof Player && e.getDamager() instanceof Arrow) {
      Arrow arrow = (Arrow) e.getDamager();
      if (arrow.getShooter() instanceof Player) {
        Player p = (Player) e.getEntity();
        Player k = (Player) arrow.getShooter();
        damage(p, k);

      }
    }
  }

  /**
   * Manage the case when a player harms another player.
   * 
   * @param p player who was damaged
   * @param k player who damaged p
   */
  private static void damage(Player p, Player k) {
    if (Gamestate.isState(Gamestate.Ingame)) {
      if (RoleManager.playerList.get(RoleManager.getPlayer(k)).isSeeker()
          && !RoleManager.playerList.get(RoleManager.getPlayer(p)).isSeeker()) {
        RoleManager.founded(p, k);

      } else if (k.getInventory().getItemInMainHand().getItemMeta().getDisplayName()
          .equalsIgnoreCase(LanguageManager.getMessage(Variablelist.items_hider_stun, p))) {
        p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 3 * 20, 5));
        p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 3 * 20, 5));
        p.sendMessage(LanguageManager.getMessage(Variablelist.chat_stunned, p, k));
        k.sendMessage(LanguageManager.getMessage(Variablelist.chat_stunned, p, k));
        timer.put(k, Fileaccess.getInt("items.stun-duration", Fileaccess.getConfig()));
        new BukkitRunnable() {
          @Override
          public void run() {
            k.getInventory().setItem(0,
                new ItemBuilder(Material.STICK).setUnbreakable(true)
                    .setDisplayName(LanguageManager.getMessage(Variablelist.items_hider_stun, p)
                        + " " + timer.get(k))
                    .build());
            if (timer.get(k) == 0) {
              k.getInventory().setItem(0,
                  new ItemBuilder(Material.BLAZE_ROD).setUnbreakable(true)
                      .setDisplayName(LanguageManager.getMessage(Variablelist.items_hider_stun, p))
                      .build());
              cancel();
            }
            timer.put(k, timer.get(k) - 1);

          }
        }.runTaskTimer(App.instance, 0, 20);

      }
    }
  }
}
