package de.niklashere.hidenseek.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import de.niklashere.hidenseek.gamestates.Gamestate;
import de.niklashere.hidenseek.gamestates.Rolemanager;
import de.niklashere.hidenseek.libary.Fileaccess;
import de.niklashere.hidenseek.libary.LanguageManager;
import de.niklashere.hidenseek.libary.VariableManager;

public class PlayerJoinListener implements Listener {
   
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        e.setJoinMessage(null);
        LanguageManager.addLanguage(p, Fileaccess.listOfFiles[0]);
        for (Player all : Bukkit.getOnlinePlayers()) {
            all.sendMessage(VariableManager.message(LanguageManager.getMessage("joinmessage", all), p));
        }

        if (Gamestate.isState(Gamestate.Lobby)) {
            Rolemanager.addSeeker(p);
            
        } else if (Gamestate.isState(Gamestate.WarmUp) || Gamestate.isState(Gamestate.Ingame)) {
            Rolemanager.addSpectator(p);
        }
    }
}
