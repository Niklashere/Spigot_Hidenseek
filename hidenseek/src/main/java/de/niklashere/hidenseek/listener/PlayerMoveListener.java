package de.niklashere.hidenseek.listener;

import de.niklashere.hidenseek.App;
import de.niklashere.hidenseek.gamestates.Gamestate;
import de.niklashere.hidenseek.gamestates.RoleManager;
import de.niklashere.hidenseek.libary.Fileaccess;
import de.niklashere.hidenseek.libary.PropManager;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Listener for the PlayerMoveEvent.
 *
 * @author Niklashere
 * @since 04.08.2021
 */
public class PlayerMoveListener implements Listener {

  private static HashMap<Player, Integer> timer = new HashMap<>();

  @EventHandler
  public void onPlayerMove(PlayerMoveEvent e) {
    Player p = e.getPlayer();
    if ((Gamestate.isState(Gamestate.WarmUp) || Gamestate.isState(Gamestate.Ingame)) && RoleManager.playerList.get(RoleManager.getPlayer(p)).isHider()) {
      if (e.getFrom().getX() == e.getTo().getX() && e.getFrom().getZ() == e.getTo().getZ()) {
        if (p.getLocation().getBlock().getType() == Material.AIR && (timer.get(p) == null || timer.get(p) <= -1)) {
          timer.put(p, Fileaccess.getInt("props.wait", Fileaccess.getConfig())*20);
          new BukkitRunnable() {
            @Override
            public void run() {
              System.out.println(timer.get(p) + "  "
              + Fileaccess.getInt("props.wait", Fileaccess.getConfig()));
              float t = 1 - ((float) timer.get(p) / (float) (Fileaccess.getInt("props.wait", Fileaccess.getConfig())*20));
              System.out.println("a " + t);
              if (t >= 0 && 1 >= t) {
               p.setExp(t);
              }
              

              if (timer.get(p) == 0) {
                System.out.println("PM 1");
                PropManager prop = PropManager.propsList.get(p);
                timer.put(p, Integer.MAX_VALUE);
                prop.stopfollow();
            //    prop.setBlock(Material.STONE);
                cancel();
              }

              if (timer.get(p) <= -1) {
                System.out.println("PM 2");
                cancel();
              }
              timer.put(p, timer.get(p) - 1);

            }
          }.runTaskTimer(App.instance, 0, 1);
        }
      } else {
        PropManager prop = PropManager.propsList.get(p);
        System.out.println(prop);
        System.out.println("PM 3");
        timer.put(p, -1);
        p.setExp(0);

        if (prop == null) {
          System.out.println("PM 4");

        PropManager props = new PropManager(p);
        PropManager.propsList.put(p, props);
        props.setProp(Material.STONE);
    //    prop.removeBlock();
        }
      }
    }
  }
}
