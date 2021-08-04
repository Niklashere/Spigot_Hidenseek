package de.niklashere.hidenseek.libary;

import de.niklashere.hidenseek.App;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

/**
 * Class to give players their props.
 *
 * @author Niklashere
 * @since 31-07-2021
 */
public class PropManager {

  private Player player;
  private Wolf blockmount;
  private boolean mounted = false;
  private int timer = 1;

  public PropManager(Player p) {
    this.player = p;
  }

  public boolean isMounted() {
    return mounted;
  }

  public void setProp(Material mat) {
    Player p = this.player;
    if (!mounted) {
      Wolf wolf = p.getWorld().spawn(p.getLocation(), Wolf.class);
      System.out.println(p.getName());
      FallingBlock b = wolf.getWorld().spawnFallingBlock(wolf.getLocation().add(0, 2, 0), mat,
          (byte) 1);
      b.setDropItem(false);
      wolf.addPassenger(b);
      wolf.setRemoveWhenFarAway(false);
      wolf.setCanPickupItems(false);
      wolf.setSilent(true);
      wolf.setVelocity(new Vector(0.0D, 0.5D, 0.0D));
      wolf.setCollidable(false);
      // wolf.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY,
      // Integer.MAX_VALUE, 1, false, false));
      wolf.addPotionEffect(
          new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 20, false, false));
      this.blockmount = wolf;
    } else {
      deleteBallon();
      setProp(mat);
    }
  }

  public void deleteBallon() {
    this.blockmount.remove();
    this.blockmount = null;
    this.mounted = false;
  }

  public void follow(Player p) {
    Wolf w = this.blockmount;
    new BukkitRunnable() {

      @Override
      public void run() {
        if (!p.isOnline()) {
          cancel();
        }

        if (w.getLocation().distance(p.getLocation()) >= 2) {
          w.teleport(p);
          w.setVelocity(new Vector(p.getLocation().getX() - w.getLocation().getX(),
              p.getLocation().getY() - w.getLocation().getY(),
              p.getLocation().getZ() - (w.getLocation().getZ())));

        } else {
          w.setVelocity(new Vector(p.getLocation().getX() - w.getLocation().getX(),
              p.getLocation().getY() - w.getLocation().getY(),
              p.getLocation().getZ() - (w.getLocation().getZ())));
        }
        if (w.getPassenger() == null) {
          deleteBallon();
          setProp(Material.STONE);

        }
      }
    }.runTaskTimer(App.instance, 0, 1);

  }
}
