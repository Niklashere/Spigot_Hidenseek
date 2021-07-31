package de.niklashere.hidenseek.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import de.niklashere.hidenseek.gamestates.Rolemanager;
import de.niklashere.hidenseek.libary.LanguageManager;
import de.niklashere.hidenseek.libary.VariableManager;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        e.setQuitMessage(null);

        for (Player all : Bukkit.getOnlinePlayers()) {
            VariableManager.message(LanguageManager.getMessage("quitmessage", all), p);
        }
        
        Rolemanager.removeRole(p);
    } 
}
