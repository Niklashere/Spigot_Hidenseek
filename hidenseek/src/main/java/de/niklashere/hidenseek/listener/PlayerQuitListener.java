package de.niklashere.hidenseek.listener;

import de.niklashere.hidenseek.files.languages.Variablelist;
import de.niklashere.hidenseek.gamestates.Gamestate;
import de.niklashere.hidenseek.gamestates.RoleManager;
import de.niklashere.hidenseek.libary.LanguageManager;
import de.niklashere.hidenseek.libary.PropManager;
import de.niklashere.hidenseek.libary.StatsManager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Listener for the PlayerQuitEvent.
 *
 * @author Niklashere
 * @since 31.07.2021
 */
public class PlayerQuitListener implements Listener {

  /**
   * Called when the event occurs.
   * 
   * @param e event
   */
  @EventHandler
  public void onPlayerQuit(PlayerQuitEvent e) {
    Player p = e.getPlayer();
    e.setQuitMessage(null);

    PropManager props = PropManager.propsList.get(p);
    if (props != null) {
      props.stopfollow();
    }
    PropManager.removeBlock(p);

    for (Player all : Bukkit.getOnlinePlayers()) {
      LanguageManager.getMessage(Variablelist.chat_quitMessage, all, p, null);
    }

    if (Gamestate.isState(Gamestate.Ingame) || Gamestate.isState(Gamestate.WarmUp)) {
      StatsManager.addPlayes(p.getUniqueId(), 1);
    }

    if (RoleManager.playerList.size() != 0) {
      RoleManager.playerList.remove(RoleManager.playerList.get(RoleManager.getPlayer(p)));
    }
  }
}
