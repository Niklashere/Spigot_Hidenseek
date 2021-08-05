package de.niklashere.hidenseek.listener;

import de.niklashere.hidenseek.App;
import de.niklashere.hidenseek.gamestates.Gamestate;
import de.niklashere.hidenseek.libary.Fileaccess;
import de.niklashere.hidenseek.libary.PropManager;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerMoveListener implements Listener {

  private static HashMap<Player, Integer> timer = new HashMap<>();

  @EventHandler
  public void onPlayerMove(PlayerMoveEvent e) {
    Player p = e.getPlayer();
    if (Gamestate.isState(Gamestate.WarmUp) || Gamestate.isState(Gamestate.Ingame)) {
      PropManager prop = PropManager.propsList.get(p);
      if (e.getFrom().getX() == e.getTo().getX() || e.getFrom().getZ() == e.getTo().getZ()) {
        if (p.getLocation().getBlock().getType() == Material.AIR) {
          timer.put(p, Fileaccess.getInt("props.wait", Fileaccess.getConfig()) * 4);
          new BukkitRunnable() {
            @Override
            public void run() {
              int t = 1 - (timer.get(p) / (Fileaccess.getInt("props.wait", Fileaccess.getConfig())*4));
              System.out.println(t + "  " + timer.get(p) + "  "
                  + Fileaccess.getInt("props.wait", Fileaccess.getConfig()));
              // p.setExp(t);

              if (timer.get(p) <= 0) {
                timer.put(p, 0);
                prop.stopfollow();
                prop.setBlock(Material.STONE);
                cancel();
              }
              timer.put(p, timer.get(p) - 1);

            }
          }.runTaskTimer(App.instance, 0, 5);
        }
      } else {
        timer.put(p, 0);
        prop.removeBlock();
      }
    }
  }
}
