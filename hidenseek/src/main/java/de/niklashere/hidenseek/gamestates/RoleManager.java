package de.niklashere.hidenseek.gamestates;

import de.niklashere.hidenseek.files.languages.Variablelist;
import de.niklashere.hidenseek.inventorys.InventoryManager;
import de.niklashere.hidenseek.libary.Fileaccess;
import de.niklashere.hidenseek.libary.LanguageManager;
import de.niklashere.hidenseek.libary.PropManager;
import de.niklashere.hidenseek.libary.StatsManager;
import de.niklashere.hidenseek.libary.VoteManager;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

/**
 * Rolemanager wich manages player Roles.
 *
 * @author Niklashere
 * @since 04.08.2021
 */
public class RoleManager {

  public static ArrayList<PlayerData> playerList = new ArrayList<PlayerData>();

  /**
   * Get the position in the arraylist from player p.
   * 
   * @param p player
   * @return position
   */
  public static int getPlayer(Player p) {
    int i = 0;
    while (playerList.size() - 1 >= i) {
      if (playerList.get(i).getPlayer() == p) {
        return i;
      }
      i++;
    }
    return 0;
  }

  /**
   * Get a list of all seekers.
   * 
   * @return Arraylist of seekers
   */
  public static ArrayList<PlayerData> getSeekers() {
    int i = 0;
    ArrayList<PlayerData> seekers = new ArrayList<>();
    while (playerList.size() - 1 >= i) {
      if (playerList.get(i).isSeeker()) {
        seekers.add(playerList.get(i));
      }
      i++;
    }
    return seekers;
  }

  /**
   * Get a list of all hiders.
   * 
   * @return Arraylist of hiders
   */
  public static ArrayList<PlayerData> getHiders() {
    int i = 0;
    ArrayList<PlayerData> hiders = new ArrayList<>();
    while (playerList.size() - 1 >= i) {
      if (playerList.get(i).isHider()) {
        hiders.add(playerList.get(i));
      }
      i++;
    }
    return hiders;
  }

  /**
   * Get a list of all spectators.
   * 
   * @return Arraylist of spectators
   */
  public static ArrayList<PlayerData> getSpectators() {
    int i = 0;
    ArrayList<PlayerData> spectator = new ArrayList<>();
    while (playerList.size() - 1 >= i) {
      if (playerList.get(i).isSpectator()) {
        spectator.add(playerList.get(i));
      }
      i++;
    }
    return spectator;
  }

  /**
   * Methode wich managed p after he was found.
   *
   * @param p player who was found
   * @param k player who catched p.
   */
  public static void founded(final Player p, final Player k) {
    for (PotionEffect effect : p.getActivePotionEffects()) {
      p.removePotionEffect(effect.getType());
    }
    playerList.get(getPlayer(p)).setHider(false);
    playerList.get(getPlayer(p)).setSeeker(true);
    PropManager props = PropManager.propsList.get(p);
    if (props != null) {
      props.stopfollow();
    }
    PropManager.removeBlock(p);
    InventoryManager.clearInv(p);
    InventoryManager.seekerItems(p);
    for (Player all : Bukkit.getOnlinePlayers()) {
      all.spawnParticle(Particle.FLAME, p.getLocation(), 10);
      all.sendMessage(LanguageManager.getMessage(Variablelist.chat_found, all, p, k));
    }
    p.teleport(Fileaccess.getLocation("spawnpoint-seeker", VoteManager.getResults()));
    StatsManager.addCought(p.getUniqueId(), 1);
    StatsManager.addFound(k.getUniqueId(), 1);
  }

  /**
   * Give points to the teams.
   * 
   * @param winnerTeam team who has won
   */
  public static void endGame(String winnerTeam) {
    if (winnerTeam.equalsIgnoreCase("hider")) {
      int i = 0;
      while (playerList.size() - 1 >= i) {
        Player all = playerList.get(i).getPlayer();
        if (playerList.get(i).isHider()) {
          StatsManager.addWins(all.getUniqueId(), 1);
        }
        i++;
      }
    } else if (winnerTeam.equalsIgnoreCase("seeker")) {
      int i = 0;
      while (playerList.size() - 1 >= i) {
        Player all = playerList.get(i).getPlayer();
        if (playerList.get(i).isSeeker()) {
          StatsManager.addWins(all.getUniqueId(), 1);
        }
        i++;
      }
    }
  }
}
