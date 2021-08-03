package de.niklashere.hidenseek.listener;

import de.niklashere.hidenseek.files.languages.Variablelist;
import de.niklashere.hidenseek.gamestates.Rolemanager;
import de.niklashere.hidenseek.inventorys.MapvotingInventory;
import de.niklashere.hidenseek.inventorys.SetupInventory;
import de.niklashere.hidenseek.libary.Fileaccess;
import de.niklashere.hidenseek.libary.LanguageManager;
import de.niklashere.hidenseek.libary.VoteManager;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.meta.FireworkMeta;

/**
 * Listener for the InventoryClickEvent.
 *
 * @author Niklashere
 * @since 31.07.2021
 */
public class InventoryClickListener implements Listener {

  /**
   * Called when the event occurs.
   * 
   * @param e event
   */
  @EventHandler
  public void onInventoryClick(InventoryClickEvent e) {
    Player p = (Player) e.getWhoClicked();
    e.setCancelled(true);

    if (e.getCurrentItem() != null && e.getCurrentItem().hasItemMeta() && p.getOpenInventory()
        .getTitle().equalsIgnoreCase(LanguageManager.getMessage(Variablelist.inv_setup_name, p))) {
      if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(
          LanguageManager.getMessage(Variablelist.inv_setup_spawnpointLobby, p))) {
        Fileaccess.setLocation("spawnpoint-lobby", Fileaccess.getConfig(), p);

      } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(
          LanguageManager.getMessage(Variablelist.inv_setup_spawnpointSeeker, p))) {
        File file = new File("plugins/hidenseek/maps", p.getWorld().getName() + ".yml");
        Fileaccess.setString("world", file, p.getWorld().getName());
        Fileaccess.setLocation("spawnpoint-seeker", file, p);

      } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(
          LanguageManager.getMessage(Variablelist.inv_setup_spawnpointHider, p))) {
        File file = new File("plugins/hidenseek/maps", p.getWorld().getName() + ".yml");
        Fileaccess.setString("world", file, p.getWorld().getName());
        Fileaccess.setLocation("spawnpoint-hider", file, p);

      } else if (e.getCurrentItem().getItemMeta().getDisplayName()
          .equalsIgnoreCase(LanguageManager.getMessage(Variablelist.inv_setup_minPlayers, p)
              + Fileaccess.getInt("min-players", Fileaccess.getConfig()))) {
        String string = "min-players";
        if (e.getClick().isRightClick()) {
          Fileaccess.setInt(string, Fileaccess.getConfig(),
              Fileaccess.getInt(string, Fileaccess.getConfig()) - 1);
          Fileaccess.clearHash();

        } else if (e.getClick().isLeftClick()) {
          Fileaccess.setInt(string, Fileaccess.getConfig(),
              Fileaccess.getInt(string, Fileaccess.getConfig()) + 1);
          Fileaccess.clearHash();

        } else if (e.getClick().isRightClick() && e.getClick().isShiftClick()) {
          Fileaccess.setInt(string, Fileaccess.getConfig(),
              Fileaccess.getInt(string, Fileaccess.getConfig()) - 10);
          Fileaccess.clearHash();

        } else if (e.getClick().isLeftClick() && e.getClick().isShiftClick()) {
          Fileaccess.setInt(string, Fileaccess.getConfig(),
              Fileaccess.getInt(string, Fileaccess.getConfig()) + 10);
          Fileaccess.clearHash();
        }
        SetupInventory.openInventory(p);

      } else if (e.getCurrentItem().getItemMeta().getDisplayName()
          .equalsIgnoreCase(LanguageManager.getMessage(Variablelist.inv_setup_maxPlayers, p)
              + Fileaccess.getInt("max-players", Fileaccess.getConfig()))) {
        String string = "max-players";
        if (e.getClick().isRightClick()) {
          Fileaccess.setInt(string, Fileaccess.getConfig(),
              Fileaccess.getInt(string, Fileaccess.getConfig()) - 1);
          Fileaccess.clearHash();

        } else if (e.getClick().isLeftClick()) {
          Fileaccess.setInt(string, Fileaccess.getConfig(),
              Fileaccess.getInt(string, Fileaccess.getConfig()) + 1);
          Fileaccess.clearHash();

        } else if (e.getClick().isRightClick() && e.getClick().isShiftClick()) {
          Fileaccess.setInt(string, Fileaccess.getConfig(),
              Fileaccess.getInt(string, Fileaccess.getConfig()) - 10);
          Fileaccess.clearHash();

        } else if (e.getClick().isLeftClick() && e.getClick().isShiftClick()) {
          Fileaccess.setInt(string, Fileaccess.getConfig(),
              Fileaccess.getInt(string, Fileaccess.getConfig()) + 10);
          Fileaccess.clearHash();

        }
        SetupInventory.openInventory(p);

      } else if (e.getCurrentItem().getItemMeta().getDisplayName()
          .equalsIgnoreCase(LanguageManager.getMessage(Variablelist.inv_setup_maxSeeker, p)
              + Fileaccess.getInt("max-seeker", Fileaccess.getConfig()))) {
        String string = "max-seeker";
        if (e.getClick().isRightClick()) {
          Fileaccess.setInt(string, Fileaccess.getConfig(),
              Fileaccess.getInt(string, Fileaccess.getConfig()) - 1);
          Fileaccess.clearHash();

        } else if (e.getClick().isLeftClick()) {
          Fileaccess.setInt(string, Fileaccess.getConfig(),
              Fileaccess.getInt(string, Fileaccess.getConfig()) + 1);
          Fileaccess.clearHash();

        } else if (e.getClick().isRightClick() && e.getClick().isShiftClick()) {
          Fileaccess.setInt(string, Fileaccess.getConfig(),
              Fileaccess.getInt(string, Fileaccess.getConfig()) - 10);
          Fileaccess.clearHash();

        } else if (e.getClick().isLeftClick() && e.getClick().isShiftClick()) {
          Fileaccess.setInt(string, Fileaccess.getConfig(),
              Fileaccess.getInt(string, Fileaccess.getConfig()) + 10);
          Fileaccess.clearHash();

        }
        SetupInventory.openInventory(p);

      } else if (e.getCurrentItem().getItemMeta().getDisplayName()
          .equalsIgnoreCase(LanguageManager.getMessage(Variablelist.inv_setup_inGameTime, p)
              + Fileaccess.getInt("Ingame", Fileaccess.getConfig()))) {
        String string = "Ingame";
        if (e.getClick().isRightClick()) {
          Fileaccess.setInt(string, Fileaccess.getConfig(),
              Fileaccess.getInt(string, Fileaccess.getConfig()) - 1);
          Fileaccess.clearHash();

        } else if (e.getClick().isLeftClick()) {
          Fileaccess.setInt(string, Fileaccess.getConfig(),
              Fileaccess.getInt(string, Fileaccess.getConfig()) + 1);
          Fileaccess.clearHash();

        } else if (e.getClick().isRightClick() && e.getClick().isShiftClick()) {
          Fileaccess.setInt(string, Fileaccess.getConfig(),
              Fileaccess.getInt(string, Fileaccess.getConfig()) - 10);
          Fileaccess.clearHash();

        } else if (e.getClick().isLeftClick() && e.getClick().isShiftClick()) {
          Fileaccess.setInt(string, Fileaccess.getConfig(),
              Fileaccess.getInt(string, Fileaccess.getConfig()) + 10);
          Fileaccess.clearHash();

        }
        SetupInventory.openInventory(p);

      }
    } else if (e.getCurrentItem() != null && e.getCurrentItem().hasItemMeta()
        && p.getOpenInventory().getTitle()
            .equalsIgnoreCase(LanguageManager.getMessage(Variablelist.inv_hint_name, p))) {
      if (e.getCurrentItem().getItemMeta().getDisplayName()
          .equalsIgnoreCase(LanguageManager.getMessage(Variablelist.inv_hint_Firework, p))) {
        FireworkEffect effect = FireworkEffect.builder().withColor(Color.GREEN).withFade(Color.RED)
            .with(FireworkEffect.Type.BURST).trail(false).flicker(true).build();
        Firework fw = (Firework) p.getWorld().spawnEntity(p.getLocation(), EntityType.FIREWORK);
        FireworkMeta meta = fw.getFireworkMeta();
        meta.addEffect(effect);
        meta.setPower(1);
        fw.setFireworkMeta(meta);

      } else if (e.getCurrentItem().getItemMeta().getDisplayName()
          .equalsIgnoreCase(LanguageManager.getMessage(Variablelist.inv_hint_Meow, p))) {
        for (Player all : Bukkit.getOnlinePlayers()) {
          Location pl = p.getLocation();
          Location l = new Location(pl.getWorld(), pl.getX(), pl.getY() + 1, pl.getZ());
          all.playSound(l, Sound.ENTITY_CAT_BEG_FOR_FOOD, 5, 1);
          all.spawnParticle(Particle.HEART, l, 3);
        }

      } else if (e.getCurrentItem().getItemMeta().getDisplayName()
          .equalsIgnoreCase(LanguageManager.getMessage(Variablelist.inv_hint_Explosion, p))) {
        for (Player all : Bukkit.getOnlinePlayers()) {
          Location pl = p.getLocation();
          Location l = new Location(pl.getWorld(), pl.getX(), pl.getY() + 1, pl.getZ());
          all.playSound(l, Sound.ENTITY_GENERIC_EXPLODE, 5, 1);
          all.spawnParticle(Particle.EXPLOSION_LARGE, l, 3);
        }
      }
    } else if (e.getCurrentItem() != null && e.getCurrentItem().hasItemMeta()
        && p.getOpenInventory().getTitle()
            .equalsIgnoreCase(LanguageManager.getMessage(Variablelist.inv_mapvoting_Name, p))) {
      ArrayList<File> maps = VoteManager.rdmMap(Fileaccess.listOfFiles.get("maps"), 3);
      if (e.getCurrentItem().getItemMeta().getDisplayName()
          .equalsIgnoreCase(maps.get(0).getName().replace(".yml", ""))) {
        VoteManager.addVote(maps.get(0), p);
        MapvotingInventory.openInventory(p);
      } else if (e.getCurrentItem().getItemMeta().getDisplayName()
          .equalsIgnoreCase(maps.get(1).getName().replace(".yml", ""))) {
        VoteManager.addVote(maps.get(1), p);
        MapvotingInventory.openInventory(p);
      } else if (e.getCurrentItem().getItemMeta().getDisplayName()
          .equalsIgnoreCase("map." + maps.get(2).getName().replace(".yml", ""))) {
        VoteManager.addVote(maps.get(2), p);
        MapvotingInventory.openInventory(p);
      }
    } else if (e.getCurrentItem() != null && e.getCurrentItem().hasItemMeta()
        && p.getOpenInventory().getTitle()
            .equalsIgnoreCase(LanguageManager.getMessage(Variablelist.inv_role_name, p))) {
      if (e.getCurrentItem().getItemMeta().getDisplayName()
          .equalsIgnoreCase(LanguageManager.getMessage(Variablelist.inv_role_hider, p))) {
        Rolemanager.addHider(p);
      } else if (e.getCurrentItem().getItemMeta().getDisplayName()
          .equalsIgnoreCase(LanguageManager.getMessage(Variablelist.inv_role_seeker, p))) {
        Rolemanager.addSeeker(p);
      }
    }
  }
}
