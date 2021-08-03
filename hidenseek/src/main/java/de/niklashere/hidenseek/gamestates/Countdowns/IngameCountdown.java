package de.niklashere.hidenseek.gamestates.countdowns;

import de.niklashere.hidenseek.App;
import de.niklashere.hidenseek.files.languages.Variablelist;
import de.niklashere.hidenseek.gamestates.Gamestate;
import de.niklashere.hidenseek.gamestates.Rolemanager;
import de.niklashere.hidenseek.libary.Fileaccess;
import de.niklashere.hidenseek.libary.LanguageManager;
import de.niklashere.hidenseek.libary.VariableManager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Countdown functionality during the game phase.
 *
 * @author Niklashere
 * @since 31-07-2021
 */
public class IngameCountdown {
  /**
   * Countdown length configured in config file.
   */
  private static int time = Fileaccess.getInt("Ingame", Fileaccess.getConfig()) + 1;

  /**
   * Method to start the Ingame countdown.
   */
  public static void startIngameCD() {
    startCountdown();
    Gamestate.setState(Gamestate.Ingame);
  }

  /**
   * Start Ingamecountdown.
   */
  private static void startCountdown() {
    new BukkitRunnable() {

      @Override
      public void run() {
        if (Bukkit.getOnlinePlayers().size() >= Fileaccess.getInt("min-players",
            Fileaccess.getConfig()) && Rolemanager.getGroupsize("hider") >= 1
            && Rolemanager.getGroupsize("seeker") >= 1) {
          time--;
          for (Player all : Bukkit.getOnlinePlayers()) {
            all.setLevel(time);
            int i = time % 60;
            if (i == 0 || time == 30 || time == 15 || time == 10 || time == 5 || time == 3
                || time == 2) {
              all.sendMessage(VariableManager
                  .message(LanguageManager.getMessage(Variablelist.chat_countdownIngame, all)
                      .replaceAll("%t", time + "").replaceAll("%s", VariableManager.message(
                          LanguageManager.getMessage(Variablelist.chat_secondPlural, all)))));

            } else if (time == 1) {
              all.sendMessage(VariableManager
                  .message(LanguageManager.getMessage(Variablelist.chat_countdownIngame, all)
                      .replaceAll("%t", time + "").replaceAll("%s", VariableManager.message(
                          LanguageManager.getMessage(Variablelist.chat_secondSingular, all)))));
            }
          }
          if (time == 1) {
            Rolemanager.endGame("hider");
            EndCountdown.startEndCD();
            cancel();

          }
        } else {
          if (Rolemanager.getGroupsize("hider") < 1) {
            Rolemanager.endGame("seeker");
          } else if (Rolemanager.getGroupsize("seeker") < 1) {
            Rolemanager.endGame("hider");
          }
          EndCountdown.startEndCD();
          cancel();
        }
      }
    }.runTaskTimer(App.instance, 0L, 20);
  }
}
