package de.niklashere.hidenseek.listener;

import de.niklashere.hidenseek.App;
import de.niklashere.hidenseek.gamestates.Rolemanager;
import de.niklashere.hidenseek.libary.Fileaccess;
import de.niklashere.hidenseek.libary.ItemBuilder;
import de.niklashere.hidenseek.libary.LanguageManager;
import de.niklashere.hidenseek.libary.VariableManager;

import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
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

  /**
   * Called when the event occurs.
   * 
   * @param e
   *          event
   */
  @EventHandler
  public void onDamage(EntityDamageByEntityEvent e) {
    e.setCancelled(true);
    if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
      Player p = (Player) e.getEntity();
      Player k = (Player) e.getDamager();

      damage(p, k);
    } else if (e.getEntity() instanceof Player
        && e.getDamager() instanceof Arrow) {
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
   * @param p
   *          player who was damaged
   * @param k
   *          player who damaged p
   */
  private static void damage(Player p, Player k) {
    if (Rolemanager.isSeeker(k) && !Rolemanager.isSeeker(p)) {
      Rolemanager.founded(p, k);

    } else if (k.getInventory().getItemInMainHand().getItemMeta()
        .getDisplayName().equalsIgnoreCase(VariableManager
            .message(LanguageManager.getMessage("item.stun", p), p))) {
      p.addPotionEffect(
          new PotionEffect(PotionEffectType.BLINDNESS, 3 * 20, 5));
      p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 3 * 20, 5));
      p.sendMessage(VariableManager
          .message(LanguageManager.getMessage("stunned", p), p, k));
      k.sendMessage(VariableManager
          .message(LanguageManager.getMessage("stunned", p), p, k));
      int i = Fileaccess.getInt("stun-duration", Fileaccess.getConfig());
      new BukkitRunnable() {
        @Override
        public void run() {
          p.getInventory().setItem(0,
              new ItemBuilder(Material.STICK).setUnbreakable(true)
                  .setDisplayName(VariableManager.message(
                      LanguageManager.getMessage("item.stun", p), p) + " " + i)
                  .build());
          if (i == 0) {
            p.getInventory().setItem(0,
                new ItemBuilder(Material.BLAZE_ROD).setUnbreakable(true)
                    .setDisplayName(VariableManager
                        .message(LanguageManager.getMessage("item.stun", p), p))
                    .build());
            cancel();
          }

        }
      }.runTaskTimer(App.instance, 0, 20);

    }
  }
}
