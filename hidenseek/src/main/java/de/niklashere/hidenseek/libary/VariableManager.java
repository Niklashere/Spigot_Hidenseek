package de.niklashere.hidenseek.libary;

import org.bukkit.entity.Player;

public class VariableManager {

    public static String message(String string) {
        string.replaceAll("&", "§");
        return string;
    }
    
    public static String message(String string, Player p) {
        string = message(string);
        string.replaceAll("%p", p.getName());
        return string;
    }
}
