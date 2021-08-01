package de.niklashere.hidenseek.libary;

import org.bukkit.entity.Player;

public class VariableManager {

    public static String message(String string) {
        string = string.replaceAll("&", "§");
        return string;
    }
    
    public static String message(String string, Player p) {
        string = string.replaceAll("%p", p.getName());
        string = message(string);
        return string;
    }

    public static String message(String string, Player p, Player k) {
        string = string.replaceAll("%k", k.getName());
        string = message(string, p);
        return string;
    }
}
