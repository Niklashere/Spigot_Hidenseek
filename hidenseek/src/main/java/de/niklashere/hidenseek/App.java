package de.niklashere.hidenseek;

import de.niklashere.hidenseek.gamestates.Rolemanager;
import de.niklashere.hidenseek.gamestates.countdowns.LobbyCountdown;
import de.niklashere.hidenseek.libary.Fileaccess;
import de.niklashere.hidenseek.libary.MysqlManager;
import de.niklashere.hidenseek.libary.VoteManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Main class of the plugin.
 *
 * @author Niklashere
 * @since 31.07.2021
 */
public class App extends JavaPlugin {

  private RegisterManager registerManager;
  public static App instance;

  /**
   * Called when plugin is enabled.
   */
  @Override
  public void onEnable() {
    instance = this;
    this.saveDefaultConfig();

    Fileaccess.createConfig();
    List<String> lang = Fileaccess.getStringList("supported-languages",
        Fileaccess.getConfig());
    List<String> maps = Fileaccess.getStringList("supported-maps",
        Fileaccess.getConfig());
    int i = 0;
    while (lang.size() - 1 >= i) {
      saveResource("languages/" + lang.get(i) + ".yml", true);
      i++;
    }
    int a = 0;
    while (maps.size() - 1 >= a) {
      saveResource("maps/" + maps.get(a) + ".yml", true);
      a++;
    }
    Fileaccess.loadFolder("languages");
    Fileaccess.loadFolder("maps");
    Layouts.getBanner();
    Layouts.getLanguage();
    this.registerManager = RegisterManager.init(this);
    registerManager.registerEvents();
    registerManager.registerCommands();
    LobbyCountdown.startLobbyCD();
    Rolemanager.initialize();
    ArrayList<File> file = VoteManager
        .rdmMap(Fileaccess.listOfFiles.get("maps"), 3);

    for (int e = 0; e <= file.size() - 1; e++) {
      System.out.println(file.get(e));
    }
    MysqlManager.connect();

  }

  /**
   * Called when plugin is disabled.
   */
  @Override
  public void onDisable() {
    MysqlManager.close();
    Layouts.getBanner();
    Layouts.getBye();
  }
}
