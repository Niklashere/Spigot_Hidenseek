package de.niklashere.hidenseek.libary;

import de.niklashere.hidenseek.files.languages.Variablelist;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

/**
 * Use the fileaccess class to access files and their content.
 *
 * @author Niklashere
 * @since 01.08.2021
 */
public class Fileaccess {
  static HashMap<String, String> strings = new HashMap<>();
  static HashMap<String, Integer> ints = new HashMap<>();
  static HashMap<String, Boolean> booleans = new HashMap<>();
  public static HashMap<String, File[]> listOfFiles = new HashMap<>();

  /**
   * Delete all hashed file contents.
   */
  public static void clearHash() {
    strings.clear();
    ints.clear();
    booleans.clear();
  }

  /**
   * Loads all files from a folder.
   * 
   * @param subfolder Name of the directory in the hidenseek folder.
   */
  public static void loadFolder(String subfolder) {
    File folder = new File("plugins/hidenseek/" + subfolder);
    listOfFiles.put(subfolder, folder.listFiles());
  }

  /**
   * Converts a filelist to a Arraylist.
   * 
   * @param files list of files
   * @return arraylist with files
   */
  public static ArrayList<File> fileListToArrayList(File[] files) {
    ArrayList<File> fileList = new ArrayList<>();
    for (int i = 0; i <= files.length - 1; i++) {
      fileList.add(files[i]);
    }
    return fileList;
  }

  /**
   * Get the config file.
   * 
   * @return config file
   */
  public static File getConfig() {
    return new File("plugins/hidenseek", "config.yml");
  }

  /**
   * Get a string form a file.
   * 
   * @param string string to access.
   * @param file   file to access
   * @return string
   */
  public static String getString(String string, File file) {
    if (strings.get(string + file.getName()) != null) {
      return strings.get(string + file.getName());
    } else {
      FileConfiguration conf = YamlConfiguration.loadConfiguration(file);
      strings.put(string + file.getName(), conf.getString(string));

      return conf.getString(string);
    }
  }

  /**
   * Get a stringlist from a file.
   * 
   * @param string string to access
   * @param file   file to access
   * @return stringlist
   */
  public static List<String> getStringList(String string, File file) {
    FileConfiguration conf = YamlConfiguration.loadConfiguration(file);
    return conf.getStringList(string);
  }

  /**
   * Get a boolean from a file.
   * 
   * @param string string to access
   * @param file   file to access
   * @return boolean
   */
  public static Boolean getBoolean(String string, File file) {
    if (booleans.get(string + file.getName()) != null) {
      return booleans.get(string + file.getName());
    } else {
      FileConfiguration conf = YamlConfiguration.loadConfiguration(file);
      booleans.put(string + file.getName(), conf.getBoolean(string));

      return conf.getBoolean(string);
    }
  }

  /**
   * Get a location from a file.
   * 
   * @param string string to access
   * @param file   file to access
   * @return location
   */
  public static Location getLocation(String string, File file) {
    FileConfiguration conf = YamlConfiguration.loadConfiguration(file);
    int x = conf.getInt(string + ".X");
    int y = conf.getInt(string + ".Y");
    int z = conf.getInt(string + ".Z");
    int yaw = conf.getInt(string + ".Yaw");
    int pitch = conf.getInt(string + ".Pitch");
    String world = conf.getString(string + ".World");

    World w = Bukkit.getWorld(world);
    Location l = new Location(w, x, y, z);
    l.setYaw(yaw);
    l.setPitch(pitch);

    return l;
  }

  /**
   * Get integer from file.
   * 
   * @param string string to access
   * @param file   file to access
   * @return integer
   */
  public static int getInt(String string, File file) {
    if (ints.get(string + file.getName()) != null) {
      return ints.get(string + file.getName());
    } else {
      FileConfiguration conf = YamlConfiguration.loadConfiguration(file);
      ints.put(string + file.getName(), conf.getInt(string));

      return conf.getInt(string);
    }
  }

  /**
   * Save the location in a file.
   * 
   * @param string String under which to save
   * @param file   file to access
   * @param p      player to get location
   */
  public static void setLocation(String string, File file, Player p) {
    FileConfiguration conf = YamlConfiguration.loadConfiguration(file);
    conf.set(string + ".X", p.getLocation().getX());
    conf.set(string + ".Y", p.getLocation().getY());
    conf.set(string + ".Z", p.getLocation().getZ());
    conf.set(string + ".Yaw", p.getLocation().getYaw());
    conf.set(string + ".Pitch", p.getLocation().getPitch());
    conf.set(string + ".World", p.getLocation().getWorld().getName());
    try {
      conf.save(file);
    } catch (Exception e) {
      System.out.println(LanguageManager.getMessage(Variablelist.console_save_error));
    }
  }

  /**
   * Save a Integer in a file.
   * 
   * @param string String under which to save
   * @param file   file to access
   * @param i      integer to save
   */
  public static void setInt(String string, File file, Integer i) {
    FileConfiguration conf = YamlConfiguration.loadConfiguration(file);
    conf.set(string, i);
    try {
      conf.save(file);
    } catch (Exception e) {
      System.out.println(LanguageManager.getMessage(Variablelist.console_save_error));
    }
  }

  /**
   * Save String in a file.
   * 
   * @param string  String under which to save
   * @param file    file to access
   * @param string2 string to save
   */
  public static void setString(String string, File file, String string2) {
    FileConfiguration conf = YamlConfiguration.loadConfiguration(file);
    conf.set(string, string2);
    try {
      conf.save(file);
    } catch (Exception e) {
      System.out.println(LanguageManager.getMessage(Variablelist.console_save_error));
    }
  }
}
