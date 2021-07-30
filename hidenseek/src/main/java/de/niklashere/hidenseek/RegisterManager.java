package de.niklashere.hidenseek;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

import de.niklashere.hidenseek.listener.JoinListener;

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
        pm.registerEvents(new JoinListener(), plugin);
    }
    
}
