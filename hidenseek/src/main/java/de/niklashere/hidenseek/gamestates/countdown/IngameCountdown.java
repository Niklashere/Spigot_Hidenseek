package de.niklashere.hidenseek.gamestates.countdown;

import de.niklashere.hidenseek.App;
import de.niklashere.hidenseek.files.languages.Variablelist;
import de.niklashere.hidenseek.gamestates.Gamestate;
import de.niklashere.hidenseek.gamestates.RoleManager;
import de.niklashere.hidenseek.inventorys.InventoryManager;
import de.niklashere.hidenseek.libary.Fileaccess;
import de.niklashere.hidenseek.libary.LanguageManager;
import de.niklashere.hidenseek.libary.VoteManager;

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
  private static int time = Fileaccess.getInt("Countdown.Ingame", Fileaccess.getConfig()) + 1;

  /**
   * Method to start the Ingame countdown.
   */
  public static void startIngameCD() {
    startCountdown();
    Gamestate.setState(Gamestate.Ingame);
    int i = 0;
    while (RoleManager.playerList.size() - 1 >= i) {

      Player all = RoleManager.playerList.get(i).getPlayer();

      if (RoleManager.playerList.get(i).isSeeker()) {
        all.teleport(Fileaccess.getLocation("spawnpoint-seeker", VoteManager.getResults()));
        InventoryManager.seekerItems(all);
      }

      i++;
    }
  }

  /**
   * Start Ingamecountdown.
   */
  private static void startCountdown() {
    new BukkitRunnable() {

      @Override
      public void run() {
        if (Bukkit.getOnlinePlayers().size() >= Fileaccess.getInt("Players.min",
            Fileaccess.getConfig()) && RoleManager.getHiders().size() >= 1
            && RoleManager.getSeekers().size() >= 1) {
          time--;
          for (Player all : Bukkit.getOnlinePlayers()) {
            all.setLevel(time);
            int i = time % 60;
            if (i == 0 || time == 30 || time == 15 || time == 10 || time == 5 || time == 3
                || time == 2) {
              all.sendMessage(LanguageManager.getMessage(Variablelist.chat_countdownIngame, all)
                  .replaceAll("%t%", time + "").replaceAll("%s%",
                      LanguageManager.getMessage(Variablelist.chat_secondPlural, all)));

            } else if (time == 1) {
              all.sendMessage(LanguageManager.getMessage(Variablelist.chat_countdownIngame, all)
                  .replaceAll("%t%", time + "").replaceAll("%s%",
                      LanguageManager.getMessage(Variablelist.chat_secondSingular, all)));
            }
          }
          if (time == 1) {
            RoleManager.endGame("hider");
            EndCountdown.startEndCD();
            cancel();

          }
        } else {
          if (RoleManager.getHiders().size() < 1) {
            RoleManager.endGame("seeker");
          } else if (RoleManager.getSeekers().size() < 1) {
            RoleManager.endGame("hider");
          }
          EndCountdown.startEndCD();
          cancel();
        }
      }
    }.runTaskTimer(App.instance, 0L, 20);
  }
}
