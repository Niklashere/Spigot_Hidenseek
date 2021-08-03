package de.niklashere.hidenseek;

import de.niklashere.hidenseek.gamestates.Rolemanager;
import de.niklashere.hidenseek.gamestates.countdowns.LobbyCountdown;
import de.niklashere.hidenseek.libary.Fileaccess;
import de.niklashere.hidenseek.libary.MysqlManager;

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

    List<String> maps = Fileaccess.getStringList("supported-maps", Fileaccess.getConfig());
    int a = 0;
    while (maps.size() - 1 >= a) {
      saveResource("maps/" + maps.get(a) + ".yml", true);
      a++;
    }
    Fileaccess.loadFolder("maps");

    this.registerManager = RegisterManager.init(this);
    registerManager.loadLanguages();
    Layouts.getBanner();
    Layouts.getLanguage();
    registerManager.registerEvents();
    registerManager.registerCommands();
    LobbyCountdown.startLobbyCD();
    Rolemanager.initialize();
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
