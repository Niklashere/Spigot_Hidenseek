package de.niklashere.hidenseek.libary;

import java.io.File;
import java.util.HashMap;

import org.bukkit.entity.Player;

public class LanguageManager {

    static HashMap<Player, File> language = new HashMap<Player,File>();
    
    public static String sendMessage(String string, Player p) {
        return Fileaccess.getString(string, getLanguage(p));
    }

    public static File getLanguage(Player p) {
        return language.get(p);
    }

    public static void addLanguage(Player p, File file) {
        language.put(p, file);
    }
}
