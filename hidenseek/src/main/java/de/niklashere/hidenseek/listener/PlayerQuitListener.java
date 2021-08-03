package de.niklashere.hidenseek.listener;

import de.niklashere.hidenseek.gamestates.Rolemanager;
import de.niklashere.hidenseek.libary.LanguageManager;
import de.niklashere.hidenseek.libary.VariableManager;

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

    for (Player all : Bukkit.getOnlinePlayers()) {
      VariableManager.message(LanguageManager.getMessage("quitmessage", all),
          p);
    }

    Rolemanager.removeRole(p);
  }
}
