package de.niklashere.hidenseek;

import de.niklashere.hidenseek.commands.SetupCommand;
import de.niklashere.hidenseek.commands.StatsCommand;
import de.niklashere.hidenseek.files.languages.DE;
import de.niklashere.hidenseek.files.languages.EN;
import de.niklashere.hidenseek.libary.Fileaccess;
import de.niklashere.hidenseek.listener.BlockBreakListener;
import de.niklashere.hidenseek.listener.BlockPlaceListener;
import de.niklashere.hidenseek.listener.EntityDamageByEntityListener;
import de.niklashere.hidenseek.listener.EntityDamageListener;
import de.niklashere.hidenseek.listener.EntityShootBowListener;
import de.niklashere.hidenseek.listener.FoodLevelChangeListener;
import de.niklashere.hidenseek.listener.InventoryClickListener;
import de.niklashere.hidenseek.listener.PlayerDeathListener;
import de.niklashere.hidenseek.listener.PlayerDropItemListener;
import de.niklashere.hidenseek.listener.PlayerInteractAtEntityListener;
import de.niklashere.hidenseek.listener.PlayerInteractListener;
import de.niklashere.hidenseek.listener.PlayerJoinListener;
import de.niklashere.hidenseek.listener.PlayerLoginListener;
import de.niklashere.hidenseek.listener.PlayerQuitListener;
import de.niklashere.hidenseek.listener.PlayerRespawnListener;
import de.niklashere.hidenseek.listener.ServerListPingListener;
import de.niklashere.hidenseek.listener.WeatherChangeListener;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

/**
 * RegisterManager to keep the main class a little cleaner.
 *
 * @author Niklashere
 * @since 31.07.2021
 */
public class RegisterManager {
  private App plugin;

  public RegisterManager(App plugin) {
    this.plugin = plugin;
  }

  public static RegisterManager init(App plugin) {
    return new RegisterManager(plugin);
  }

  /**
   * Register all Events.
   */
  public void registerEvents() {
    PluginManager pm = Bukkit.getPluginManager();
    pm.registerEvents(new BlockBreakListener(), plugin);
    pm.registerEvents(new BlockPlaceListener(), plugin);
    pm.registerEvents(new EntityDamageByEntityListener(), plugin);
    pm.registerEvents(new EntityDamageListener(), plugin);
    pm.registerEvents(new EntityShootBowListener(), plugin);
    pm.registerEvents(new FoodLevelChangeListener(), plugin);
    pm.registerEvents(new InventoryClickListener(), plugin);
    pm.registerEvents(new PlayerDeathListener(), plugin);
    pm.registerEvents(new PlayerDropItemListener(), plugin);
    pm.registerEvents(new PlayerInteractAtEntityListener(), plugin);
    pm.registerEvents(new PlayerInteractListener(), plugin);
    pm.registerEvents(new PlayerJoinListener(), plugin);
    pm.registerEvents(new PlayerLoginListener(), plugin);
    pm.registerEvents(new PlayerQuitListener(), plugin);
    pm.registerEvents(new PlayerRespawnListener(), plugin);
    pm.registerEvents(new ServerListPingListener(), plugin);
    pm.registerEvents(new WeatherChangeListener(), plugin);

  }

  /**
   * Register all Commands.
   */
  public void registerCommands() {
    plugin.getCommand("setup").setExecutor(new SetupCommand());
    plugin.getCommand("stats").setExecutor(new StatsCommand());

  }

  /**
   * Loads all languages.
   */
  public void loadLanguages() {
    EN.loadMessages();
    DE.loadMessages();
    Fileaccess.loadFolder("languages");

  }
}
