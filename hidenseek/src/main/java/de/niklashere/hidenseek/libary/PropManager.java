package de.niklashere.hidenseek.libary;

import de.niklashere.hidenseek.App;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.entity.Silverfish;
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
  public static HashMap<Player, PropManager> propsList = new HashMap<>();
  public static HashMap<Player, Location> blockList = new HashMap<>();

  private Player player;
  private Silverfish blockmount;
  private boolean mounted = false;
  private boolean timer = false;

  /**
   * Constructor to create player props.
   * 
   * @param p player for wich a prop should be created
   */
  public PropManager(Player p) {
    this.player = p;
  }

  /**
   * Query whether prop is mounted.
   * 
   * @return is mounted
   */
  public boolean isMounted() {
    return mounted;
  }

  /**
   * Create a prop.
   * 
   * @param mat Material of this prop
   */
  public void setProp(Material mat) {
    Player p = this.player;
    if (!mounted) {
      Silverfish s = p.getWorld().spawn(p.getLocation().add(0, 0, 0), Silverfish.class);
      System.out.println(1 + p.getName());
      FallingBlock b = s.getWorld().spawnFallingBlock(s.getLocation().add(0, 0.2, 0), mat,
          (byte) 1);
      b.setDropItem(false);
      s.addPassenger(b);
      s.setRemoveWhenFarAway(false);
      s.setCanPickupItems(false);
      s.setSilent(true);
      s.setVelocity(new Vector(0.0D, 0.5D, 0.0D));
      s.setInvisible(true);
      s.addPotionEffect(
          new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 20, false, false));
      blockmount = s;
      follow(p);
    } else {
      deleteProp();
      setProp(mat);
    }
  }

  /**
   * Delete the prop incase its no longer needed. 
   */
  public void deleteProp() {
    System.out.println(2 + blockmount.getType().toString());
    blockmount.remove();
    // this.blockmount = null;
    // this.mounted = false;
  }

  /**
   * Prop stops following and disappears.
   */
  public void stopfollow() {
    timer = false;
  }

  /**
   * Get the prop.
   * 
   * @return silverfish
   */
  public Silverfish getProp() {
    return blockmount;
  }

  /**
   * Props will follow p.
   * 
   * @param p Player who should get followed.
   */
  public void follow(Player p) {
    timer = true;
    PropManager props = propsList.get(p);
    Silverfish w = props.getProp();

    new BukkitRunnable() {

      @Override
      public void run() {
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
          System.out.println(4);
          props.setProp(Material.STONE);
          cancel();

        }

        if (!timer) {
          System.out.println(3);
          props.deleteProp();
          cancel();
        }
      }
    }.runTaskTimer(App.instance, 0, 1);

  }

  public void setBlock(Material mat) {
    Player p = this.player;
    Block b = p.getLocation().getBlock();
  //  b.setType(mat);
  System.out.println(5);
    for (Player all : Bukkit.getOnlinePlayers()) {
   //   all.sendBlockChange(p.getLocation(), b.getBlockData());
    }
    blockList.put(p, p.getLocation());
  }

  public void removeBlock() {
    Player p = this.player;
    if (blockList.get(p) != null) {
      System.out.println(6);
      PropManager props = propsList.get(p);
      Block b = blockList.get(p).getBlock();
      b.setType(Material.AIR);

   //   blockList.get(p).getBlock().setType(Material.AIR);
   for (Player all : Bukkit.getOnlinePlayers()) {
 //   all.sendBlockChange(b.getLocation(), b.getBlockData());
  }
  System.out.println(7);

      blockList.remove(p);
      props.setProp(Material.STONE);
    }
  }
}
