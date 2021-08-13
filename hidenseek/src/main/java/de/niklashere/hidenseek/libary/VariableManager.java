package de.niklashere.hidenseek.libary;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

/**
 * Replaces specific variables with a suitable element.
 *
 * @author Niklashere
 * @since 31.07.2021
 */
public class VariableManager {

  /**
   * Replaces all variables with a suitable element.
   * 
   * @param string message
   * @return message
   */
  public static String message(String string) {
    // Prefixes
    string = string.replaceAll("%pc%", Fileaccess.getString("prefix.chat", Fileaccess.getConfig()));
    string = string.replaceAll("%pii%",
        Fileaccess.getString("prefix.inv-item", Fileaccess.getConfig()));
    string = string.replaceAll("%pin%",
        Fileaccess.getString("prefix.inv-names", Fileaccess.getConfig()));
    string = string.replaceAll("%pi%", Fileaccess.getString("prefix.item", Fileaccess.getConfig()));

    // Colors:
    string = string.replaceAll("%cp%",
        Fileaccess.getString("colors.primary", Fileaccess.getConfig()));
    string = string.replaceAll("%cs%",
        Fileaccess.getString("colors.secondary", Fileaccess.getConfig()));
    string = string.replaceAll("%cw%",
        Fileaccess.getString("colors.warning", Fileaccess.getConfig()));
    string = string.replaceAll("&", "§");

    return string;
  }

  /**
   * Replaces all variables with a suitable element.
   * 
   * @param string message
   * @param p      player
   * @return message
   */
  public static String message(String string, Player p) {
    if (p != null) {
      string = string.replaceAll("%pp%", p.getName());
      string = string.replaceAll("%sc%", StatsManager.getCought(p.getUniqueId()) + "");
      string = string.replaceAll("%sf%", StatsManager.getFound(p.getUniqueId()) + "");
      string = string.replaceAll("%sw%", StatsManager.getWins(p.getUniqueId()) + "");
      string = string.replaceAll("%spl%", StatsManager.getPlayes(p.getUniqueId()) + "");
      string = string.replaceAll("%spo%", StatsManager.getPoints(p.getUniqueId()) + "");
    }
    string = message(string);
    return string;
  }

  /**
   * Replaces all variables with a suitable element.
   * 
   * @param string message
   * @param p      player
   * @param k      killer: player who killed p
   * @return message
   */
  public static String message(String string, Player p, Player k) {
    if (k != null) {
      string = string.replaceAll("%pk%", k.getName());
    }
    string = message(string, p);
    return string;
  }

  /**
   * Replaces all variables with a suitable element.
   * 
   * @param string message
   * @param p      player
   * @param k      offlineplayer
   * @return message
   */
  public static String message(String string, Player p, OfflinePlayer k) {
    if (k != null) {
      string = string.replaceAll("%pk%", k.getName());
      string = string.replaceAll("%sc%", StatsManager.getCought(k.getUniqueId()) + "");
      string = string.replaceAll("%sf%", StatsManager.getFound(k.getUniqueId()) + "");
      string = string.replaceAll("%sw%", StatsManager.getWins(k.getUniqueId()) + "");
      string = string.replaceAll("%spl%", StatsManager.getPlayes(k.getUniqueId()) + "");
      string = string.replaceAll("%spo%", StatsManager.getPoints(k.getUniqueId()) + "");
    }
    string = message(string, p);
    return string;
  }
}
