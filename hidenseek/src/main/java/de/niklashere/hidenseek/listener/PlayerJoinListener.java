package de.niklashere.hidenseek.listener;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import de.niklashere.hidenseek.gamestates.Gamestate;
import de.niklashere.hidenseek.gamestates.Rolemanager;
import de.niklashere.hidenseek.inventorys.InventoryManager;
import de.niklashere.hidenseek.libary.Fileaccess;
import de.niklashere.hidenseek.libary.LanguageManager;
import de.niklashere.hidenseek.libary.VariableManager;
import de.niklashere.hidenseek.libary.WorldManager;

public class PlayerJoinListener implements Listener {
   
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        InventoryManager.clearInv(p);
        e.setJoinMessage(null);
        File[] file = Fileaccess.listOfFiles.get("languages");
        LanguageManager.addLanguage(p, file[0]);
        for (Player all : Bukkit.getOnlinePlayers()) {
            all.sendMessage(VariableManager.message(LanguageManager.getMessage("joinmessage", all), p));
        }

        if (Gamestate.isState(Gamestate.WarmUp) || Gamestate.isState(Gamestate.Ingame)) {
            Rolemanager.addSpectator(p);
            p.teleport(Fileaccess.getLocation("spawnpoint-hider", WorldManager.getResults(), p));
            InventoryManager.spectatorItems(p);

        } else {
            p.teleport(Fileaccess.getLocation("spawnpoint-lobby", Fileaccess.getConfig(), p));
            InventoryManager.lobbyItems(p);

        }
    }
}
