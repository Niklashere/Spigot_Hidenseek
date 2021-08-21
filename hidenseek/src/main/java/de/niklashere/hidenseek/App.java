package de.niklashere.hidenseek;

import de.niklashere.hidenseek.files.languages.Variablelist;
import de.niklashere.hidenseek.gamestates.Gamemode;
import de.niklashere.hidenseek.gamestates.Gamestate;
import de.niklashere.hidenseek.gamestates.countdown.LobbyCountdown;
import de.niklashere.hidenseek.libary.Fileaccess;
import de.niklashere.hidenseek.libary.LanguageManager;
import de.niklashere.hidenseek.libary.MysqlManager;
import de.niklashere.hidenseek.libary.VoteManager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
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
    Fileaccess.loadFolder("maps");
    if (Fileaccess.listOfFiles.get("maps") == null
        || Fileaccess.listOfFiles.get("maps").length <= 2) {
      saveResource("maps/" + "Map-1.yml", false);
      saveResource("maps/" + "Map-2.yml", false);
      saveResource("maps/" + "Map-3.yml", false);
      Fileaccess.loadFolder("maps");
    }

    VoteManager.rdmMap(Fileaccess.listOfFiles.get("maps"), 3);
    this.registerManager = RegisterManager.init(this);
    registerManager.loadLanguages();
    Layouts.getBanner();
    Layouts.getLanguage();
    registerManager.registerEvents();
    registerManager.registerCommands();
    LobbyCountdown.startLobbyCD();
    MysqlManager.connect();
    Gamestate.setState(Gamestate.Lobby);
    Gamemode.setMode(Gamemode.Classic);

  }

  /**
   * Called when plugin is disabled.
   */
  @Override
  public void onDisable() {
    for (Player all : Bukkit.getOnlinePlayers()) {
      all.kickPlayer(LanguageManager.getMessage(Variablelist.chat_stopServer, all));
    }
    MysqlManager.close();
    Layouts.getBanner();
    Layouts.getBye();
  }
}
