package de.niklashere.hidenseek.gamestates.countdown;

import de.niklashere.hidenseek.App;
import de.niklashere.hidenseek.files.languages.Variablelist;
import de.niklashere.hidenseek.gamestates.Gamestate;
import de.niklashere.hidenseek.gamestates.PlayerData;
import de.niklashere.hidenseek.gamestates.RoleManager;
import de.niklashere.hidenseek.inventorys.InventoryManager;
import de.niklashere.hidenseek.libary.Fileaccess;
import de.niklashere.hidenseek.libary.GhostMaker;
import de.niklashere.hidenseek.libary.LanguageManager;
import de.niklashere.hidenseek.libary.PropManager;
import de.niklashere.hidenseek.libary.VoteManager;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
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
  private static int time = Fileaccess.getInt("Countdown.Warmup", Fileaccess.getConfig()) + 1;

  /**
   * Method to start the Warmup countdown.
   */
  public static void startWarmupCD() {

    for (Player all : Bukkit.getOnlinePlayers()) {
      PlayerData playerData = new PlayerData(all);
      if (!RoleManager.playerList.contains(playerData)) {
        playerData.setHider(true);
        RoleManager.playerList.add(playerData);

      }

    }

    int i = 0;
    int seeker = 0;
    while (RoleManager.playerList.size() - 1 >= i) {
      if (RoleManager.playerList.get(i).isSeeker()) {
        seeker = seeker + 1;
      }
      i++;
    }

    i = 0;
    while (Fileaccess.getInt("Players.seeker", Fileaccess.getConfig()) - 1 >= seeker) {
      if (RoleManager.playerList.get(i).isHider()) {
        RoleManager.playerList.get(i).setHider(false);
        RoleManager.playerList.get(i).setSeeker(true);
        seeker = seeker + 1;
      }

      i++;
    }

    i = 0;
    while (RoleManager.playerList.size() - 1 >= i) {

      Player all = RoleManager.playerList.get(i).getPlayer();

      InventoryManager.clearInv(all);
      if (RoleManager.playerList.get(i).isHider()) {
        GhostMaker.addGhost(all, all.getName());
        all.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 1));
        all.teleport(Fileaccess.getLocation("spawnpoint-hider", VoteManager.getResults()));
        InventoryManager.hiderItems(all);
        PropManager props = new PropManager(all);
        PropManager.propsList.put(all, props);

        PropManager.choosedBlock.put(all, Material.STONE);
        props.setProp(PropManager.choosedBlock.get(all));
        PropManager.propsList.put(all, props);

      } else if (RoleManager.playerList.get(i).isSpectator()) {
        GhostMaker.addGhost(all, "spectator");
        all.addPotionEffect(
            new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 1, false, false));
        all.teleport(Fileaccess.getLocation("spawnpoint-hider", VoteManager.getResults()));
        InventoryManager.spectatorItems(all);

      }

      i++;
    }

    for (Player p : Bukkit.getOnlinePlayers()) {
      if (RoleManager.playerList.get(RoleManager.getPlayer(p)).isSeeker()
          || RoleManager.playerList.get(RoleManager.getPlayer(p)).isSpectator()) {
        for (Player all : Bukkit.getOnlinePlayers()) {
          all.hidePlayer(App.instance, p);
        }
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
        if (Bukkit.getOnlinePlayers().size() >= Fileaccess.getInt("Players.min",
            Fileaccess.getConfig()) && RoleManager.getHiders().size() >= 1
            && RoleManager.getSeekers().size() >= 1) {
          time--;
          for (Player all : Bukkit.getOnlinePlayers()) {
            all.setLevel(time);
            int i = time % 60;
            if (i == 0 || time == 30 || time == 15 || time == 10 || time == 5 || time == 3
                || time == 2) {
              all.sendMessage(LanguageManager.getMessage(Variablelist.chat_countdownWarmup, all)
                  .replaceAll("%t%", time + "").replaceAll("%s%",
                      LanguageManager.getMessage(Variablelist.chat_secondPlural, all)));

            } else if (time == 1) {
              all.sendMessage(LanguageManager.getMessage(Variablelist.chat_countdownWarmup, all)
                  .replaceAll("%t%", time + "").replaceAll("%s%",
                      LanguageManager.getMessage(Variablelist.chat_secondSingular, all)));

            }
          }
          if (time == 1) {
            IngameCountdown.startIngameCD();
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
