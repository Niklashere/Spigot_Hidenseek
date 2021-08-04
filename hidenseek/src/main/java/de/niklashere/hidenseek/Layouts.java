package de.niklashere.hidenseek;

import de.niklashere.hidenseek.files.languages.Variablelist;
import de.niklashere.hidenseek.libary.Fileaccess;
import de.niklashere.hidenseek.libary.LanguageManager;

import java.io.File;
import java.time.Year;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;

/**
 * Layout to keep the main class a little cleaner.
 *
 * @author Niklashere
 * @since 31.07.2021
 */
public class Layouts {

  /**
   * Outputs a nice banner into the console.
   */
  public static void getBanner() {
    PluginDescriptionFile pdf = Bukkit.getServer().getPluginManager().getPlugin("hidenseek")
        .getDescription();

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
    System.out.println(" Developed by Niklas Zabel. Licensed under GNU GPLv3 © 2021 - "
        + Year.now().getValue() + ".");
    System.out.println(" ");

  }

  /**
   * Outputs all loaded languages into the console.
   */
  public static void getLanguage() {
    System.out.println(" ");
    System.out.println(LanguageManager.getMessage(Variablelist.console_languages));

    int i = 0;
    String languages = " ";

    ArrayList<File> lang = Fileaccess.fileListToArrayList(Fileaccess.listOfFiles.get("languages"));

    while (lang.size() - 1 >= i) {
      if (i >= 1) {
        languages = languages + ", " + lang.get(i).getName().replace(".yml", "");

      } else {
        languages = lang.get(i).getName().replace(".yml", "");

      }
      i++;
    }
    System.out.println(languages);
    System.out.println(" ");

  }

  /**
   * Outputs a nice "BYE" banner into the console.
   */
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
