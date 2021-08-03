package de.niklashere.hidenseek.libary;

import java.io.File;
import java.util.HashMap;

import org.bukkit.entity.Player;

/**
 * Outputs the messages in the selected languages.
 *
 * @author Niklashere
 * @since 31.07.2021
 */
public class LanguageManager {

  static HashMap<Player, File> language = new HashMap<Player, File>();

  /**
   * Get a message from the language file for this player.
   * 
   * @param string String under which this message is saved
   * @param p Player which language should be used
   * @return message from lanuage file
   */
  public static String getMessage(String string, Player p) {
    return Fileaccess.getString(string, getLanguage(p));
  }

  /**
   * Get the language file for this player.
   * 
   * @param p Player
   * @return language file
   */
  public static File getLanguage(Player p) {
    return language.get(p);
  }

  /**
   * Saves the language used by the player.
   * 
   * @param p Player
   * @param file language file
   */
  public static void addLanguage(Player p, File file) {
    language.put(p, file);
  }
}
