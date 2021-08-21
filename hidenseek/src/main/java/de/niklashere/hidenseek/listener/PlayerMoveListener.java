package de.niklashere.hidenseek.listener;

import de.niklashere.hidenseek.App;
import de.niklashere.hidenseek.gamestates.Gamestate;
import de.niklashere.hidenseek.gamestates.RoleManager;
import de.niklashere.hidenseek.libary.Fileaccess;
import de.niklashere.hidenseek.libary.PropManager;

import java.util.HashMap;

import org.bukkit.Bukkit;
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

  /**
   * Called when the event occurs.
   * 
   * @param e event
   */
  @EventHandler
  public void onPlayerMove(PlayerMoveEvent e) {
    Player p = e.getPlayer();
    if ((Gamestate.isState(Gamestate.WarmUp) || Gamestate.isState(Gamestate.Ingame))
        && RoleManager.playerList.get(RoleManager.getPlayer(p)).isHider()) {
      if (e.getFrom().getX() == e.getTo().getX() && e.getFrom().getZ() == e.getTo().getZ()) {
        if (p.getLocation().getBlock().getType() == Material.AIR
            && (timer.get(p) == null || timer.get(p) <= -2)) {
          timer.put(p, Fileaccess.getInt("props.wait", Fileaccess.getConfig()) * 20);
          new BukkitRunnable() {
            @Override
            public void run() {
              PropManager prop = PropManager.propsList.get(p);

              if (RoleManager.playerList.size() - 1 >= RoleManager.getPlayer(p)
                  && !RoleManager.playerList.get(RoleManager.getPlayer(p)).isHider()) {
                timer.put(p, -2);
                p.setExp(0);
              }
              if (timer.get(p) >= 1) {
                float t = 1 - ((float) timer.get(p)
                    / (float) (Fileaccess.getInt("props.wait", Fileaccess.getConfig()) * 20));
                if (t >= 0 && 1 >= t) {
                  p.setExp(t);
                }
                timer.put(p, timer.get(p) - 1);

              } else if (timer.get(p) == 0) {
                prop.stopfollow();
                PropManager.setBlock(p, PropManager.choosedBlock.get(p));
                for (Player p : Bukkit.getOnlinePlayers()) {
                  if (RoleManager.playerList.get(RoleManager.getPlayer(p)).isHider()) {
                    for (Player all : Bukkit.getOnlinePlayers()) {
                      if (all != p) {
                        all.hidePlayer(App.instance, p);
                      }
                    }
                  }
                }

                timer.put(p, timer.get(p) - 1);

              } else if (timer.get(p) == -1) {
                PropManager.setBlock(p, PropManager.choosedBlock.get(p));

              } else if (timer.get(p) <= -2) {
                cancel();
              }

            }
          }.runTaskTimer(App.instance, 0, 1);
        }
      } else {
        PropManager prop = PropManager.propsList.get(p);
        timer.put(p, -2);
        p.setExp(0);

        if (prop == null) {
          PropManager props = new PropManager(p);
          PropManager.propsList.put(p, props);
          PropManager.removeBlock(p);
          props.setProp(PropManager.choosedBlock.get(p));
        }
      }
    }
  }
}
