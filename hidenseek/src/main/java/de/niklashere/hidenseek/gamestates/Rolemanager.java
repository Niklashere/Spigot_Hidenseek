package de.niklashere.hidenseek.gamestates;

import de.niklashere.hidenseek.inventorys.InventoryManager;
import de.niklashere.hidenseek.libary.Fileaccess;
import de.niklashere.hidenseek.libary.LanguageManager;
import de.niklashere.hidenseek.libary.StatsManager;
import de.niklashere.hidenseek.libary.VariableManager;
import de.niklashere.hidenseek.libary.VoteManager;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

/**
 * Rolemanager wich manages player Roles.
 *
 * @author Niklashere
 * @since 01.08.2021
 */
public class Rolemanager {
  /**
   * Saves the role of a player.
   */
  private static HashMap<Player, String> role = new HashMap<>();
  /**
   * Saves the size of all roles.
   */
  private static HashMap<String, Integer> groupsize = new HashMap<>();

  /**
   * Initilize roles.
   */
  public static void initialize() {
    groupsize.put("seeker", 0);
    groupsize.put("hider", 0);
    groupsize.put("spectator", 0);

  }

  /**
   * Get playersize of a specific group.
   *
   * @param string
   *          name of the group
   * @return groupsize
   */
  public static int getGroupsize(final String string) {
    return groupsize.get(string);
  }

  /**
   * Methode wich managed p after he was found.
   *
   * @param p
   *          player who was found
   * @param k
   *          player who catched p.
   */
  public static void founded(final Player p, final Player k) {
    removeRole(p);
    role.put(p, "seeker");
    groupsize.put("seeker", groupsize.get("seeker") + 1);
    InventoryManager.clearInv(p);
    InventoryManager.seekerItems(p);
    for (Player all : Bukkit.getOnlinePlayers()) {
      all.spawnParticle(Particle.FLAME, p.getLocation(), 10);
      all.sendMessage(VariableManager
          .message(LanguageManager.getMessage("found", all), p, k));
    }
    p.teleport(
        Fileaccess.getLocation("spawnpoint-seeker", VoteManager.getResults()));
    StatsManager.addCought(p.getUniqueId(), 1);
    StatsManager.addFound(k.getUniqueId(), 1);
  }

  /**
   * Give points to the teams.
   * 
   * @param winnerTeam
   *          team who has won
   */
  public static void endGame(String winnerTeam) {
    if (winnerTeam.equalsIgnoreCase("hider")) {
      for (Player all : Bukkit.getOnlinePlayers()) {
        if (Rolemanager.isHider(all)) {
          StatsManager.addWins(all.getUniqueId(), 1);
        }
      }
    } else if (winnerTeam.equalsIgnoreCase("seeker")) {
      for (Player all : Bukkit.getOnlinePlayers()) {
        if (Rolemanager.isSeeker(all)) {
          StatsManager.addWins(all.getUniqueId(), 1);
        }
      }
    }
  }

  /**
   * Add a player to the seeker group.
   *
   * @param p
   *          Player to be added.
   */
  public static void addSeeker(final Player p) {
    if (Fileaccess.getInt("max-seeker", Fileaccess.getConfig()) - 1 >= groupsize
        .get("seeker")) {
      removeRole(p);
      role.put(p, "seeker");
      groupsize.put("seeker", groupsize.get("seeker") + 1);
    }
  }

  /**
   * Add a player to the hider group.
   *
   * @param p
   *          Player to be added.
   */
  public static void addHider(final Player p) {
    if (role.get(p) == null) {
      role.put(p, "hider");
      groupsize.put("hider", groupsize.get("hider") + 1);
    }
  }

  /**
   * Add a player to the spectator group.
   *
   * @param p
   *          Player to be added.
   */
  public static void addSpectator(final Player p) {
    if (role.get(p) == null) {
      role.put(p, "spectator");
      groupsize.put("spectator", groupsize.get("spectator") + 1);
    }
  }

  /**
   * Remove player form his role.
   *
   * @param p
   *          Player to be removed.
   */
  public static void removeRole(final Player p) {
    if (getRole(p) == "seeker") {
      Rolemanager.removeSeeker(p);
    } else if (getRole(p) == "hider") {
      Rolemanager.removeHider(p);
    } else if (getRole(p) == "spectator") {
      Rolemanager.removeSpectator(p);
    }
  }

  /**
   * Remove a player from the seeker group.
   *
   * @param p
   *          Player to be removed.
   */
  public static void removeSeeker(final Player p) {
    role.remove(p);
    groupsize.put("seeker", groupsize.get("seeker") - 1);

  }

  /**
   * Remove a player from the hider group.
   *
   * @param p
   *          Player to be removed.
   */
  public static void removeHider(final Player p) {
    role.remove(p);
    groupsize.put("hider", groupsize.get("hider") - 1);
  }

  /**
   * Remove a player from the spectator group.
   *
   * @param p
   *          Player to be removed.
   */
  public static void removeSpectator(final Player p) {
    role.remove(p);
    groupsize.put("spectator", groupsize.get("spectator") - 1);
  }

  /**
   * Get the role of the player p.
   *
   * @param p
   *          Player which role should be queried
   * @return role of p
   */
  public static String getRole(final Player p) {
    return role.get(p);

  }

  /**
   * Query whether the player is a seeker.
   *
   * @param p
   *          Player to be queried
   * @return is seeker
   */
  public static boolean isSeeker(final Player p) {
    return role.get(p) == "seeker";
  }

  /**
   * Query whether the player is a hider.
   *
   * @param p
   *          Player to be queried
   * @return is hider
   */
  public static boolean isHider(final Player p) {
    return role.get(p) == "hider";
  }

  /**
   * Query whether the player is a spectator.
   *
   * @param p
   *          Player to be queried
   * @return is hider
   */
  public static boolean isSpectator(final Player p) {
    return role.get(p) == "spectator";
  }
}
