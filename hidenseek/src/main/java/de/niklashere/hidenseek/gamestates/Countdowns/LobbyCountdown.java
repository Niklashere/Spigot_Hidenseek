package de.niklashere.hidenseek.gamestates.countdowns;

import de.niklashere.hidenseek.App;
import de.niklashere.hidenseek.files.languages.Variablelist;
import de.niklashere.hidenseek.gamestates.Gamestate;
import de.niklashere.hidenseek.inventorys.InventoryManager;
import de.niklashere.hidenseek.libary.Fileaccess;
import de.niklashere.hidenseek.libary.LanguageManager;
import de.niklashere.hidenseek.libary.VoteManager;

import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Countdown functionality during the lobby phase.
 *
 * @author Niklashere
 * @since 31-07-2021
 */
public class LobbyCountdown {
  /**
   * Countdown length configured in config file.
   */
  private static int time = Fileaccess.getInt("Countdown.Lobby", Fileaccess.getConfig()) + 1;

  /**
   * Method to start the Lobby Countdown.
   */
  public static void startLobbyCD() {
    startCountdown();
    Gamestate.setState(Gamestate.Lobby);
    for (Player all : Bukkit.getOnlinePlayers()) {
      InventoryManager.lobbyItems(all);
    }
  }

  /**
   * Start Lobbycountdown.
   */
  private static void startCountdown() {
    new BukkitRunnable() {

      @Override
      public void run() {
        if (Bukkit.getOnlinePlayers().size() >= Fileaccess.getInt("Players.min",
            Fileaccess.getConfig())) {
          time--;
          for (Player all : Bukkit.getOnlinePlayers()) {
            all.setLevel(time);
            int i = time % 60;
            if (i == 0 || time == 30 || time == 15 || time == 10 || time == 5 || time == 3
                || time == 2) {
              all.sendMessage(LanguageManager.getMessage(Variablelist.chat_countdownLobby, all)
                  .replaceAll("%t%", time + "").replaceAll("%s%",
                      LanguageManager.getMessage(Variablelist.chat_secondPlural, all)));

            } else if (time == 1) {
              all.sendMessage(LanguageManager.getMessage(Variablelist.chat_countdownLobby, all)
                  .replaceAll("%t%", time + "").replaceAll("%s%",
                      LanguageManager.getMessage(Variablelist.chat_secondSingular, all)));
            }
            if (time == 5) {
              Bukkit.createWorld(
                  WorldCreator.name(Fileaccess.getString("world", VoteManager.getResults())));

            }
          }
          if (time == 1) {
            WarmupCountdown.startWarmupCD();
            cancel();
          }
        } else {
          time = Fileaccess.getInt("Countdown.Lobby", Fileaccess.getConfig()) + 1;
        }
      }
    }.runTaskTimer(App.instance, 0L, 20);
  }
}
