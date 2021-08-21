package de.niklashere.hidenseek.gamestates.countdown;

import de.niklashere.hidenseek.App;
import de.niklashere.hidenseek.files.languages.Variablelist;
import de.niklashere.hidenseek.gamestates.Gamemode;
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
  public static int time = Fileaccess.getInt("Countdown.Lobby", Fileaccess.getConfig()) + 1;

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

          if (time >= Fileaccess.getInt("Countdown.Full", Fileaccess.getConfig()) + 2
              && Bukkit.getOnlinePlayers().size() >= Fileaccess.getInt("Players.skip-lobby",
                  Fileaccess.getConfig())) {
            time = Fileaccess.getInt("Countdown.Full", Fileaccess.getConfig()) + 1;
          }
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

          }
          if (time == Fileaccess.getInt("Countdown.Full", Fileaccess.getConfig()) - 10) {
            Bukkit.createWorld(WorldCreator
                .name(Fileaccess.getString("spawnpoint-seeker.World", VoteManager.getResults())));
            int classic = 0;
            int prop = 0;
            for (Player all : Bukkit.getOnlinePlayers()) {
              if (Gamemode.voted.get(all) != null) {
                if (Gamemode.voted.get(all).equalsIgnoreCase("classic")) {
                  classic++;

                } else if (Gamemode.voted.get(all).equalsIgnoreCase("prop")) {
                  prop++;

                }
              }

              if (prop >= classic) {
                Gamemode.setMode(Gamemode.Prophunt);
              } else {
                Gamemode.setMode(Gamemode.Classic);
              }

              for (int i = 0; Fileaccess
                  .getStringList(Variablelist.chat_mapvoting_won, LanguageManager.getLanguage(all))
                  .size() - 1 >= i; i++) {
                all.sendMessage(LanguageManager
                    .getMessageFromList(Variablelist.chat_mapvoting_won, all, i)
                    .replace("%name%", Fileaccess.getString("world", VoteManager.getResults()))
                    .replace("%author%", Fileaccess.getString("author", VoteManager.getResults())));
                InventoryManager.clearInv(all);
                InventoryManager.lobbyItems(all);
              }
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
