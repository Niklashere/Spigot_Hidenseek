package de.niklashere.hidenseek.gamestates.countdown1;

import de.niklashere.hidenseek.App;
import de.niklashere.hidenseek.files.languages.Variablelist;
import de.niklashere.hidenseek.gamestates.Gamestate;
import de.niklashere.hidenseek.inventorys.InventoryManager;
import de.niklashere.hidenseek.libary.Fileaccess;
import de.niklashere.hidenseek.libary.LanguageManager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Countdown functionality during the end phase.
 *
 * @author Niklashere
 * @since 31-07-2021
 */
public class EndCountdown {
  /**
   * Countdown length configured in config file.
   */
  private static int time = Fileaccess.getInt("Countdown.End", Fileaccess.getConfig()) + 1;

  /**
   * Method to start the End countdown.
   */
  public static void startEndCD() {
    for (Player all : Bukkit.getOnlinePlayers()) {
      all.teleport(Fileaccess.getLocation("spawnpoint-lobby", Fileaccess.getConfig()));
      InventoryManager.clearInv(all);
      InventoryManager.lobbyItems(all);
    }
    startCountdown();
    Gamestate.setState(Gamestate.End);
  }

  /**
   * To stop the server and send everyone a kickmessage.
   */
  private static void stopServer() {
    for (Player all : Bukkit.getOnlinePlayers()) {
      all.kickPlayer(LanguageManager.getMessage(Variablelist.chat_stopServer, all));
    }
    Bukkit.shutdown();

  }

  /**
   * Start Endcountdown.
   */
  private static void startCountdown() {
    new BukkitRunnable() {
      @Override
      public void run() {
        if (Bukkit.getOnlinePlayers().size() >= 1) {
          time--;
          for (Player all : Bukkit.getOnlinePlayers()) {
            all.setLevel(time);
            int i = time % 60;
            if (i == 0 || time == 30 || time == 15 || time == 10 || time == 5 || time == 3
                || time == 2) {
              all.sendMessage(LanguageManager.getMessage(Variablelist.chat_countdownEnd, all)
                  .replaceAll("%t%", time + "").replaceAll("%s%",
                      LanguageManager.getMessage(Variablelist.chat_secondSingular, all)));

            } else if (time == 1) {
              all.sendMessage(LanguageManager.getMessage(Variablelist.chat_countdownEnd, all)
                  .replaceAll("%t%", time + "").replaceAll("%s%",
                      LanguageManager.getMessage(Variablelist.chat_secondSingular, all)));
            }
          }
          if (time == 1) {
            stopServer();
            cancel();

          }
        } else {
          stopServer();
          cancel();
        }
      }
    }.runTaskTimer(App.instance, 0L, 20);
  }
}
