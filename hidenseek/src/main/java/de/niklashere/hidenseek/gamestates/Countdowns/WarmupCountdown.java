package de.niklashere.hidenseek.gamestates.countdowns;

import de.niklashere.hidenseek.App;
import de.niklashere.hidenseek.gamestates.Gamestate;
import de.niklashere.hidenseek.gamestates.Rolemanager;
import de.niklashere.hidenseek.inventorys.InventoryManager;
import de.niklashere.hidenseek.libary.Fileaccess;
import de.niklashere.hidenseek.libary.LanguageManager;
import de.niklashere.hidenseek.libary.VariableManager;
import de.niklashere.hidenseek.libary.VoteManager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Countdown functionality during the warump phase.
 *
 * @author Niklashere
 * @since 31-07-2021
 */
public class WarmupCountdown {
  /**
   * Countdown length configured in config file.
   */
  private static int time = Fileaccess.getInt("Warmup", Fileaccess.getConfig())
      + 1;

  /**
   * Method to start the Warmup countdown.
   */
  public static void startWarmupCD() {
    for (Player all : Bukkit.getOnlinePlayers()) {
      InventoryManager.clearInv(all);
      Rolemanager.addSeeker(all);
      if (Rolemanager.isHider(all)) {
        all.teleport(Fileaccess.getLocation("spawnpoint-hider",
            VoteManager.getResults()));
        InventoryManager.hiderItems(all);
      } else if (Rolemanager.isSeeker(all)) {
        all.teleport(Fileaccess.getLocation("spawnpoint-seeker",
            VoteManager.getResults()));
        InventoryManager.seekerItems(all);
      } else if (Rolemanager.isSpectator(all)) {
        all.teleport(Fileaccess.getLocation("spawnpoint-hider",
            VoteManager.getResults()));
        InventoryManager.spectatorItems(all);

      }
    }
    startCountdown();
    Gamestate.setState(Gamestate.WarmUp);
  }

  /**
   * Start Warmupcountdown.
   */
  private static void startCountdown() {
    new BukkitRunnable() {

      @Override
      public void run() {
        if (Bukkit.getOnlinePlayers().size() >= Fileaccess.getInt("min-players",
            Fileaccess.getConfig()) && Rolemanager.getGroupsize("hider") >= 1) {
          time--;
          for (Player all : Bukkit.getOnlinePlayers()) {
            all.setLevel(time);
            int i = time % 60;
            if (i == 0 || time == 30 || time == 15 || time == 10 || time == 5
                || time == 3 || time == 2) {
              all.sendMessage(VariableManager
                  .message(LanguageManager.getMessage("countdown-warmup", all)
                      .replaceAll("%t", time + "")
                      .replaceAll("%s", VariableManager.message(
                          LanguageManager.getMessage("second-plural", all)))));

            } else if (time == 1) {
              all.sendMessage(VariableManager.message(LanguageManager
                  .getMessage("countdown-warmup", all)
                  .replaceAll("%t", time + "")
                  .replaceAll("%s", VariableManager.message(
                      LanguageManager.getMessage("second-singular", all)))));
            }
          }
          if (time == 1) {
            IngameCountdown.startIngameCD();
            cancel();
          }
        } else {
          EndCountdown.startEndCD();
          cancel();
        }
      }
    }.runTaskTimer(App.instance, 0L, 20);
  }
}
