package de.niklashere.hidenseek.libary;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.OfflinePlayer;
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
   * Get a message from the default language file.
   * 
   * @param string String under which this message is saved
   * @return message from lanuage file
   */
  public static String getMessage(String string) {
    return Fileaccess.getString(string, new File("plugins/hidenseek/languages",
        Fileaccess.getString("language.default-file", Fileaccess.getConfig())));
  }

  /**
   * Get a message from the language file for this player.
   * 
   * @param string String under which this message is saved
   * @param p      Player which language should be used
   * @return message from lanuage file
   */
  public static String getMessage(String string, Player p) {
    return VariableManager.message(Fileaccess.getString(string, getLanguage(p)), p);
  }

  /**
   * Get a message from the language file for this player.
   * 
   * @param string String under which this message is saved
   * @param p1     Player which language should be used
   * @param p2     Player wich should be used for the variablemanager
   * @param k      Second player for %k
   * @return message from lanuage file
   */
  public static String getMessage(String string, Player p1, Player p2, Player k) {
    return VariableManager.message(Fileaccess.getString(string, getLanguage(p1)), p2, k);
  }

  /**
   * Get a message from the language file for this player.
   * 
   * @param string String under which this message is saved
   * @param p      Player which language should be used
   * @param k      Second player for %k
   * @return message from lanuage file
   */
  public static String getMessage(String string, Player p, OfflinePlayer k) {
    return VariableManager.message(Fileaccess.getString(string, getLanguage(p)), p, k);
  }

  /**
   * Get the language file for this player.
   * 
   * @param p Player
   * @return language file
   */
  public static File getLanguage(Player p) {
    if (language.get(p) == null) {
      if (playerExists(p.getUniqueId())) {
        try {
          ResultSet rs = MysqlManager
              .query("SELECT * FROM Languages WHERE UUID= '" + p.getUniqueId() + "';");
          if (rs.next()) {
            return new File("plugins/hidenseek/languages", rs.getString("language"));
          }
        } catch (SQLException ex) {
          return new File("plugins/hidenseek/languages",
              Fileaccess.getString("language.default-file", Fileaccess.getConfig()));
        }
      } else {
        createPlayer(p.getUniqueId());
        getLanguage(p);
      }
      return new File("plugins/hidenseek/languages",
          Fileaccess.getString("language.default-file", Fileaccess.getConfig()));
    } else {
      return language.get(p);
    }
  }

  /**
   * Saves the language used by the player.
   * 
   * @param p    Player
   * @param file language file
   */
  public static void setLanguage(Player p, File file) {
    if (playerExists(p.getUniqueId())) {
      MysqlManager.update("UPDATE Languages SET language= '" + file.getName() + "' WHERE UUID= '"
          + p.getUniqueId() + "';");
    } else {
      createPlayer(p.getUniqueId());
      setLanguage(p, file);
    }
    language.remove(p);
    language.put(p, file);

  }

  /**
   * Check if already entry for the UUID in the table exists.
   * 
   * @param uuid uuid of player, who should be checked
   * @return exist in table
   */
  public static boolean playerExists(UUID uuid) {
    try {
      ResultSet rs = MysqlManager.query("SELECT * FROM Languages WHERE UUID= '" + uuid + "';");

      if (rs.next()) {
        return rs.getString("UUID") != null;
      }
      rs.close();
      return false;
    } catch (java.sql.SQLException e) {
      e.printStackTrace();
    }

    return false;
  }

  /**
   * Creates a player in case their arn't already one created.
   * 
   * @param uuid uuid of the player, who's entry should be created
   */
  public static void createPlayer(UUID uuid) {
    if (!playerExists(uuid)) {
      MysqlManager.update("INSERT INTO Languages(UUID, language) VALUES ('" + uuid + "', '"
          + Fileaccess.getString("language.default-file", Fileaccess.getConfig()) + "');");
    }
  }
}
