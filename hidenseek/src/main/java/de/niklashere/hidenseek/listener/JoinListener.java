package de.niklashere.hidenseek.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import de.niklashere.hidenseek.gamestates.Gamestate;
import de.niklashere.hidenseek.libary.Fileaccess;
import de.niklashere.hidenseek.libary.LanguageManager;
import de.niklashere.hidenseek.libary.VariableManager;

public class JoinListener implements Listener {
   
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        e.setJoinMessage(null);
        LanguageManager.addLanguage(e.getPlayer(), Fileaccess.listOfFiles[0]);
        for (Player all : Bukkit.getOnlinePlayers()) {
            VariableManager.message(LanguageManager.sendMessage("joinmessage", all), e.getPlayer());
        }

        if (Gamestate.isState(Gamestate.Lobby)) {
            
        }

    }
}
