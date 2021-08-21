package de.niklashere.hidenseek.listener;

import de.niklashere.hidenseek.gamestates.Gamestate;
import de.niklashere.hidenseek.libary.Fileaccess;
import de.niklashere.hidenseek.libary.VariableManager;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

/**
 * Listener for the ServerListPingEvent.
 *
 * @author Niklashere
 * @since 31.07.2021
 */
public class ServerListPingListener implements Listener {

  /**
   * Called when the event occurs.
   * 
   * @param e event
   */
  @EventHandler
  public void onServerListPing(ServerListPingEvent e) {
    if (Gamestate.isState(Gamestate.Lobby)) {
      if (Bukkit.getOnlinePlayers().size() >= Fileaccess.getInt("Players.max",
          Fileaccess.getConfig())) {
        e.setMaxPlayers(Fileaccess.getInt("Players.max", Fileaccess.getConfig()));
        e.setMotd(
            VariableManager.message(Fileaccess.getString("motd.Full", Fileaccess.getConfig())));

      } else {
        e.setMaxPlayers(Fileaccess.getInt("Players.max", Fileaccess.getConfig()));

      }
    } else if (Gamestate.isState(Gamestate.WarmUp)) {
      e.setMaxPlayers(Fileaccess.getInt("Players.spectator", Fileaccess.getConfig()));
      e.setMotd(
          VariableManager.message(Fileaccess.getString("motd.Warmup", Fileaccess.getConfig())));

    } else if (Gamestate.isState(Gamestate.Ingame)) {
      e.setMaxPlayers(Fileaccess.getInt("Players.spectator", Fileaccess.getConfig()));
      e.setMotd(
          VariableManager.message(Fileaccess.getString("motd.Ingame", Fileaccess.getConfig())));

    } else if (Gamestate.isState(Gamestate.End)) {
      e.setMaxPlayers(Fileaccess.getInt("Players.spectator", Fileaccess.getConfig()));
      e.setMotd(VariableManager.message(Fileaccess.getString("motd.End", Fileaccess.getConfig())));

    }
  }
}
