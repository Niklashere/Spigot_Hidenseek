package de.niklashere.hidenseek.listener;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import de.niklashere.hidenseek.gamestates.Rolemanager;
import de.niklashere.hidenseek.libary.LanguageManager;
import de.niklashere.hidenseek.libary.VariableManager;

public class EntityDamageByEntityListener implements Listener {

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

    private static void damage(Player p, Player k) {
        if (Rolemanager.isSeeker(k) && !Rolemanager.isSeeker(p)) {
            Rolemanager.founded(p, k);

        } else if (k.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equalsIgnoreCase(VariableManager.message(LanguageManager.getMessage("item.stun", p), p))) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 3*20, 5));
            p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 3*20, 5));
            p.sendMessage(VariableManager.message(LanguageManager.getMessage("stunned", p), p, k));
            k.sendMessage(VariableManager.message(LanguageManager.getMessage("stunned", p), p, k));
        }
    }
}
