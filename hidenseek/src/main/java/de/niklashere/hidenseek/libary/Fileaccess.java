package de.niklashere.hidenseek.libary;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import de.niklashere.hidenseek.App;

public class Fileaccess {

    static HashMap<String, String> strings = new HashMap<>();
    static HashMap<String, Integer> ints = new HashMap<>();
    static HashMap<String, Boolean> booleans = new HashMap<>();

    public static File[] listOfFiles;

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

    public static int getInt(String string, File file) {
        if (ints.get(string+file.getName()) != null) {
            return ints.get(string+file.getName());
        } else {
            FileConfiguration conf = YamlConfiguration.loadConfiguration(file);
            ints.put(string+file.getName(), conf.getInt(string));

            return conf.getInt(string);
        }
    }

    public static void setString(String var, String string) {
        FileConfiguration config = App.instance.getConfig();
        config.set(string, string);
        App.instance.saveConfig();;

    }
}
