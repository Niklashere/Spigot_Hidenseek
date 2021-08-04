package de.niklashere.hidenseek.listener;

import de.niklashere.hidenseek.files.languages.Variablelist;
import de.niklashere.hidenseek.gamestates.Gamestate;
import de.niklashere.hidenseek.gamestates.PlayerData;
import de.niklashere.hidenseek.gamestates.RoleManager;
import de.niklashere.hidenseek.inventorys.InventoryManager;
import de.niklashere.hidenseek.libary.Fileaccess;
import de.niklashere.hidenseek.libary.GhostMaker;
import de.niklashere.hidenseek.libary.LanguageManager;
import de.niklashere.hidenseek.libary.PropManager;
import de.niklashere.hidenseek.libary.VoteManager;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Listener for the PlayerJoinEvent.
 *
 * @author Niklashere
 * @since 31.07.2021
 */
public class PlayerJoinListener implements Listener {

  public static HashMap<Player, PropManager> props = new HashMap<>();


  /**
   * Called when the event occurs.
   * 
   * @param e event
   */
  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent e) {
    Player p = e.getPlayer();
    p.setCollidable(false);
    InventoryManager.clearInv(p);
    e.setJoinMessage(null);
    PropManager prop = new PropManager(p);
    prop.setProp(Material.STONE);
    prop.follow(p);
    props.put(p, prop);
    for (Player all : Bukkit.getOnlinePlayers()) {
      all.sendMessage(LanguageManager.getMessage(Variablelist.chat_joinMessage, all, p, null));
    }

    if (Gamestate.isState(Gamestate.WarmUp) || Gamestate.isState(Gamestate.Ingame)) {
      PlayerData playerData = new PlayerData(p);
      playerData.setSpectator(true);
      RoleManager.playerList.add(playerData);
      GhostMaker.addGhost(p, "spectator");
      p.addPotionEffect(
          new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 1, false, false));
      p.teleport(Fileaccess.getLocation("spawnpoint-hider", VoteManager.getResults()));
      InventoryManager.spectatorItems(p);

    } else {
      p.teleport(Fileaccess.getLocation("spawnpoint-lobby", Fileaccess.getConfig()));
      InventoryManager.lobbyItems(p);

    }
  }
}
