package de.niklashere.hidenseek;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

import de.niklashere.hidenseek.commands.*;
import de.niklashere.hidenseek.listener.*;

public class RegisterManager {
    private App plugin;
 
     public RegisterManager(App plugin) {
         this.plugin = plugin;
     }
 
     public static RegisterManager init(App plugin) {
         return new RegisterManager(plugin);
     }
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

    public void registerCommands() {
        plugin.getCommand("setup").setExecutor(new SetupCommand());

    }
}
