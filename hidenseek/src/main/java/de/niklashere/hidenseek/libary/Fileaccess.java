package de.niklashere.hidenseek.libary;

import java.io.File;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;

import de.niklashere.hidenseek.App;

public class Fileaccess {

    public static File[] listOfFiles;

    public static void loadLanguages() {
        File folder = new File("plugins/hidenseek/languages");
        listOfFiles = folder.listFiles();
    }

    public static String getString(String string, File file) {
        
        
        return "";
    }

    public static void createConfig() {
        FileConfiguration config = App.instance.getConfig();
        List<String> lang = List.of("en", "de");
        config.set("supported-languages", lang);
        App.instance.saveConfig();

    }
    public static List<String> getStringList(String string) {
        FileConfiguration config = App.instance.getConfig();
        return config.getStringList(string);
    }

    public static Boolean getBoolean(String string) {
        FileConfiguration config = App.instance.getConfig();
        return config.getBoolean(string);
    }

    public static void setString(String var, String string) {
        FileConfiguration config = App.instance.getConfig();
        config.set(string, string);
        App.instance.saveConfig();;

    }

}
