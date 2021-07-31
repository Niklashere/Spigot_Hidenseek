package de.niklashere.hidenseek;

import java.time.Year;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;

import de.niklashere.hidenseek.libary.Fileaccess;

public class Layouts {

    public static void getBanner() {
        PluginDescriptionFile pdf = Bukkit.getServer().getPluginManager().getPlugin("hidenseek").getDescription();

        System.out.println(" ");
        System.out.println(".._...._._....._......_......._..............._....");
        System.out.println(".|.|..|.(_)...|.|....(.).....(.).............|.|...");
        System.out.println(".|.|__|.|_..__|.|.___|/._.__.|/.___..___..___|.|.__");
        System.out.println(".|..__..|.|/._`.|/._.\\.|.'_.\\../.__|/._.\\/._.\\.|/./");
        System.out.println(".|.|..|.|.|.(_|.|..__/.|.|.|.|.\\__.\\..__/..__/...<.");
        System.out.println(".|_|..|_|_|\\__,_|\\___|.|_|.|_|.|___/\\___|\\___|_|\\_\\");
        System.out.println("..............................................v" + pdf.getVersion());
        System.out.println(" ");
        System.out.println(" Support this projekt on Github:");
        System.out.println(" https://github.com/Niklashere/Spigot_Hidenseek");
        System.out.println(" ");
        System.out.println(" Developed by Niklas Zabel. Licensed under GNU GPLv3 © 2021 - " + Year.now().getValue() + ".");
        System.out.println(" ");

    }

    public static void getLanguage() {
        System.out.println(" ");
        System.out.println(" Successfully loaded languages: ");

        int i = 0;
        String languages = " ";

        while (Fileaccess.getStringList("supported-languages", Fileaccess.getConfig()).size()-1 >= i) {
            if (i >= 1) {
                languages = languages + ", " + Fileaccess.getStringList("supported-languages", Fileaccess.getConfig()).get(i);

            } else {
                languages = Fileaccess.getStringList("supported-languages", Fileaccess.getConfig()).get(i);

            }
            i++;
        }
        System.out.println(languages);
        System.out.println(" ");

    }

    public static void getBye() {
        System.out.println(" ");
        System.out.println("..______.....________._.");
        System.out.println(".|.._.\\.\\..././..____|.|");
        System.out.println(".|.|_).\\.\\_/./|.|__..|.|");
        System.out.println(".|.._.<.\\.../.|..__|.|.|");
        System.out.println(".|.|_).|.|.|..|.|____|_|");
        System.out.println(".|____/..|_|..|______(_)");
        System.out.println("........................");
        System.out.println(" ");


    }    
}
