package de.niklashere.hidenseek.libary;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import de.niklashere.hidenseek.App;

public class Fileaccess {

    static HashMap<String, String> strings = new HashMap<>();
    static HashMap<String, Integer> ints = new HashMap<>();
    static HashMap<String, Boolean> booleans = new HashMap<>();

    public static File[] listOfFiles;

    public static void clearHash() {
        strings.clear();
        ints.clear();
        booleans.clear();
    }

    public static void loadLanguages() {
        File folder = new File("plugins/hidenseek/languages");
        listOfFiles = folder.listFiles();
    }

    public static void createConfig() {
        FileConfiguration config = App.instance.getConfig();
        List<String> lang = List.of("en", "de");
        config.set("supported-languages", lang);
        App.instance.saveConfig();
    }

    public static File getConfig() {
        return new File("plugins/hidenseek", "config.yml");
    }

    public static String getString(String string, File file) {
        if (strings.get(string+file.getName()) != null) {
            return strings.get(string+file.getName());
        } else {
            FileConfiguration conf = YamlConfiguration.loadConfiguration(file);
            strings.put(string+file.getName(), conf.getString(string));

            return conf.getString(string);
        }
    }

    public static List<String> getStringList(String string, File file) {
        FileConfiguration conf = YamlConfiguration.loadConfiguration(file);
        return conf.getStringList(string);
    }

    public static Boolean getBoolean(String string, File file) {
        if (booleans.get(string+file.getName()) != null) {
            return booleans.get(string+file.getName());
        } else {
            FileConfiguration conf = YamlConfiguration.loadConfiguration(file);
            booleans.put(string+file.getName(), conf.getBoolean(string));

            return conf.getBoolean(string);
        }
    }
        
    public static Location getLocation(String string, File file, Player p) {
        FileConfiguration conf = YamlConfiguration.loadConfiguration(file);
        int x = conf.getInt(string + ".X");
        int y = conf.getInt(string + ".Y");
        int z = conf.getInt(string + ".Z");
        int yaw =  conf.getInt(string + ".Yaw");
        int pitch = conf.getInt(string + ".Pitch");
        String world = conf.getString(string + ".World");

        World w = Bukkit.getWorld(world);
        Location l = new Location(w, x, y, z);
        l.setYaw(yaw);
        l.setPitch(pitch);

        return l;
    }

    public static int getInt(String string, File file) {
        if (ints.get(string+file.getName()) != null) {
            return ints.get(string+file.getName());
        } else {
            FileConfiguration conf = YamlConfiguration.loadConfiguration(file);
            ints.put(string+file.getName(), conf.getInt(string));

            return conf.getInt(string);
        }
    }

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
            System.out.println("save-error");
        }
    }

    public static void setInt(String string, File file, Integer i) {
        FileConfiguration conf = YamlConfiguration.loadConfiguration(file);
        conf.set(string, i);
        try {
            conf.save(file);
        } catch (Exception e) {
            System.out.println("save-error");
        }
    }
}
