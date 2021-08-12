package de.niklashere.hidenseek.libary;

import de.niklashere.hidenseek.App;

import java.util.HashMap;

import net.minecraft.network.protocol.game.PacketPlayOutEntityDestroy;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.ArmorStand;
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
  public static HashMap<Player, Material> choosedBlock = new HashMap<>();
  public static HashMap<Player, Location> blockList = new HashMap<>();
  private static HashMap<Player, ArmorStand> armorStandList = new HashMap<>();

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
    return this.mounted;
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
      FallingBlock b = s.getWorld().spawnFallingBlock(s.getLocation().add(0, 0.2, 0),
          mat.createBlockData());
      b.setDropItem(false);
      s.addPassenger(b);
      s.setRemoveWhenFarAway(false);
      s.setCanPickupItems(false);
      s.setSilent(true);
      s.setVelocity(new Vector(0.0D, 0.5D, 0.0D));
      s.setInvisible(true);
      s.addPotionEffect(
          new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 20, false, false));
      this.blockmount = s;
      this.follow(p);
    } else {
      this.deleteProp();
      this.setProp(mat);
    }
  }

  /**
   * Delete the prop incase its no longer needed.
   */
  public void deleteProp() {
    this.blockmount.remove();
    this.blockmount = null;
    this.mounted = false;
    propsList.remove(this.player);
  }

  /**
   * Prop stops following and disappears.
   */
  public void stopfollow() {
    this.timer = false;
  }

  /**
   * Get the prop.
   * 
   * @return silverfish
   */
  public Silverfish getProp() {
    return this.blockmount;
  }

  /**
   * Props will follow p.
   * 
   * @param p Player who should get followed.
   */
  public void follow(Player p) {
    this.timer = true;
    new BukkitRunnable() {

      @Override
      public void run() {
        PropManager props = propsList.get(p);
        Silverfish w = props.getProp();

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

          props.setProp(PropManager.choosedBlock.get(p));
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

  /**
   * Set a solid block at the location of p with the mat.
   * 
   * @param p The player which location should be used
   * @param mat Material of the block
   */
  public static void setBlock(Player p, Material mat) {

    for (Player all : Bukkit.getOnlinePlayers()) {
      if (all != p) {
        all.sendBlockChange(p.getLocation(), mat.createBlockData());

      } else if (armorStandList.get(p) == null) {
        ArmorStand s = p.getWorld()
            .spawn(p.getLocation().getBlock().getLocation().add(0.5, 0.2, 0.5), ArmorStand.class);
        System.out.println(1 + p.getName());
        FallingBlock b = s.getWorld().spawnFallingBlock(s.getLocation().add(0, 0.2, 0),
            mat.createBlockData());
        b.setDropItem(false);
        s.setGravity(false);
        s.setSmall(true);
        s.addPassenger(b);
        s.setRemoveWhenFarAway(false);
        s.setCanPickupItems(false);
        s.setSilent(true);
        s.setInvisible(true);
        armorStandList.put(p, s);
        PacketPlayOutEntityDestroy pack = new PacketPlayOutEntityDestroy(s.getEntityId());
        for (Player a : Bukkit.getOnlinePlayers()) {

          ((CraftPlayer) a).getHandle().b.sendPacket(pack);
        }
      }
    }
    blockList.put(p, p.getLocation());
  }

  /**
   * Remove the block set with the setBlock Method.
   * 
   * @param p The player whose block should get removed
   */
  public static void removeBlock(Player p) {
    armorStandList.get(p).remove();
    armorStandList.remove(p);
    if (blockList.get(p) != null) {
      System.out.println(6);
      for (Player all : Bukkit.getOnlinePlayers()) {
        all.sendBlockChange(blockList.get(p), Material.AIR.createBlockData());
      }

      blockList.remove(p);
    }
  }
}
