package de.niklashere.hidenseek.listener;

import de.niklashere.hidenseek.gamestates.Gamestate;
import de.niklashere.hidenseek.gamestates.RoleManager;
import de.niklashere.hidenseek.libary.Fileaccess;
import de.niklashere.hidenseek.libary.LayoutManager;
import de.niklashere.hidenseek.libary.VariableManager;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * Listener for the AsyncPlayerChatEvent.
 *
 * @author Niklashere
 * @since 21.08.2021
 */
public class AsyncPlayerChatListener implements Listener {

  /**
   * Called when the event occurs.
   * 
   * @param e event
   */
  @EventHandler
  public void onAsync(AsyncPlayerChatEvent e) {
    Player p = e.getPlayer();
    if (Gamestate.isState(Gamestate.Lobby)) {
      e.setFormat(VariableManager.message(
          Fileaccess.getString("Chat." + Gamestate.getState() + "." + LayoutManager.getRank(p),
              Fileaccess.getConfig()),
          p) + e.getMessage());
    } else {
      if (RoleManager.playerList.get(RoleManager.getPlayer(p)).isHider()) {
        e.setFormat(VariableManager.message(
            Fileaccess.getString("Chat." + Gamestate.getState() + "." + LayoutManager.getRank(p),
                Fileaccess.getConfig()),
            p).replaceAll("%role%",
                VariableManager.message(Fileaccess.getString("Chat.Hider", Fileaccess.getConfig()),
                    p))
            + e.getMessage());

      } else if (RoleManager.playerList.get(RoleManager.getPlayer(p)).isHider()) {
        e.setFormat(VariableManager.message(
            Fileaccess.getString("Chat." + Gamestate.getState() + "." + LayoutManager.getRank(p),
                Fileaccess.getConfig()),
            p).replaceAll("%role%",
                VariableManager.message(Fileaccess.getString("Chat.Seeker", Fileaccess.getConfig()),
                    p))
            + e.getMessage());
      } else if (RoleManager.playerList.get(RoleManager.getPlayer(p)).isHider()) {
        e.setFormat(VariableManager
            .message(Fileaccess.getString(
                "Chat." + Gamestate.getState() + "." + LayoutManager.getRank(p),
                Fileaccess.getConfig()), p)
            .replaceAll("%role%",
                VariableManager
                    .message(Fileaccess.getString("Chat.Spectator", Fileaccess.getConfig()), p))
            + e.getMessage());
      }
    }
  }
}
