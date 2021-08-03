package de.niklashere.hidenseek.libary;

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
    string = string.replaceAll("&", "§");
    return string;
  }

  /**
   * Replaces all variables with a suitable element.
   * 
   * @param string message
   * @param p player
   * @return message
   */
  public static String message(String string, Player p) {
    string = string.replaceAll("%p", p.getName());
    string = string.replaceAll("%sc", StatsManager.getCought(p.getUniqueId()) + "");
    string = string.replaceAll("%sf", StatsManager.getCought(p.getUniqueId()) + "");
    string = string.replaceAll("%sw", StatsManager.getCought(p.getUniqueId()) + "");
    string = string.replaceAll("%spl", StatsManager.getCought(p.getUniqueId()) + "");
    string = string.replaceAll("%spo", StatsManager.getCought(p.getUniqueId()) + "");
    string = message(string);
    return string;
  }

  /**
   * Replaces all variables with a suitable element.
   * 
   * @param string message
   * @param p player
   * @param k killer: player who killed p
   * @return message
   */
  public static String message(String string, Player p, Player k) {
    string = string.replaceAll("%k", k.getName());
    string = message(string, p);
    return string;
  }
}
