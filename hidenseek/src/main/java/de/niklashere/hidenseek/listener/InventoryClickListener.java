package de.niklashere.hidenseek.listener;

import de.niklashere.hidenseek.App;
import de.niklashere.hidenseek.files.languages.Variablelist;
import de.niklashere.hidenseek.gamestates.Gamemode;
import de.niklashere.hidenseek.gamestates.PlayerData;
import de.niklashere.hidenseek.gamestates.RoleManager;
import de.niklashere.hidenseek.inventorys.MapvotingInventory;
import de.niklashere.hidenseek.inventorys.SetupInventory;
import de.niklashere.hidenseek.libary.Fileaccess;
import de.niklashere.hidenseek.libary.LanguageManager;
import de.niklashere.hidenseek.libary.PropManager;
import de.niklashere.hidenseek.libary.StatsManager;
import de.niklashere.hidenseek.libary.VariableManager;
import de.niklashere.hidenseek.libary.VoteManager;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

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
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Listener for the InventoryClickEvent.
 *
 * @author Niklashere
 * @since 31.07.2021
 */
public class InventoryClickListener implements Listener {
  private static HashMap<String, Integer> timer = new HashMap<>();

  /**
   * Called when the event occurs.
   *
   * @param e event
   */
  @EventHandler
  public void onInventoryClick(InventoryClickEvent e) {
    Player p = (Player) e.getWhoClicked();
    e.setCancelled(true);

    if (e.getCurrentItem() != null && e.getCurrentItem().hasItemMeta()) {
      if (p.getOpenInventory().getTitle()
          .equalsIgnoreCase(LanguageManager.getMessage(Variablelist.inv_setup_name, p))) {
        if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(
            LanguageManager.getMessage(Variablelist.inv_setup_spawnpointLobby, p))) {
          Fileaccess.setLocation("spawnpoint-lobby", Fileaccess.getConfig(), p);
          p.sendMessage(
              LanguageManager.getMessage(Variablelist.chat_location, p).replace("%name%", "Lobby"));

        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(
            LanguageManager.getMessage(Variablelist.inv_setup_spawnpointSeeker, p))) {
          File file = new File("plugins/hidenseek/maps", p.getWorld().getName() + ".yml");
          Fileaccess.setString("world", file, p.getWorld().getName());
          Fileaccess.setLocation("spawnpoint-seeker", file, p);
          p.sendMessage(LanguageManager.getMessage(Variablelist.chat_location, p).replace("%name%",
              "Spawnpoint Seeker"));

        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(
            LanguageManager.getMessage(Variablelist.inv_setup_spawnpointHider, p))) {
          File file = new File("plugins/hidenseek/maps", p.getWorld().getName() + ".yml");
          Fileaccess.setString("world", file, p.getWorld().getName());
          Fileaccess.setLocation("spawnpoint-hider", file, p);
          p.sendMessage(LanguageManager.getMessage(Variablelist.chat_location, p).replace("%name%",
              "Spawnpoint Hider"));

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
      } else if (p.getOpenInventory().getTitle()
          .equalsIgnoreCase(LanguageManager.getMessage(Variablelist.inv_hint_name, p))) {
        if (e.getCurrentItem().getItemMeta().getDisplayName()
            .equalsIgnoreCase(LanguageManager.getMessage(Variablelist.inv_hint_Firework, p))) {

          if (timer.get(p.getName() + "firework") == null
              || timer.get(p.getName() + "firework") <= 0) {
            timer.put(p.getName() + "firework",
                Fileaccess.getInt("items.firework-duration", Fileaccess.getConfig()));
            FireworkEffect effect = FireworkEffect.builder().withColor(Color.GREEN)
                .withFade(Color.RED).with(FireworkEffect.Type.BURST).trail(false).flicker(true)
                .build();
            Firework fw = (Firework) p.getWorld().spawnEntity(p.getLocation(), EntityType.FIREWORK);
            FireworkMeta meta = fw.getFireworkMeta();
            meta.addEffect(effect);
            meta.setPower(1);
            fw.setFireworkMeta(meta);
            StatsManager.addPoints(p.getUniqueId(),
                Fileaccess.getInt("points.hint.firework", Fileaccess.getConfig()));
            p.sendMessage(LanguageManager.getMessage(Variablelist.chat_point, p).replace("%hp%",
                Fileaccess.getInt("points.hint.firework", Fileaccess.getConfig()) + ""));

            new BukkitRunnable() {
              @Override
              public void run() {
                if (timer.get(p.getName() + "firework") == 0) {
                  p.sendMessage(LanguageManager.getMessage(Variablelist.chat_wait_ended, p).replace(
                      "%name%", LanguageManager.getMessage(Variablelist.inv_hint_Firework, p)));
                  cancel();
                }
                timer.put(p.getName() + "firework", timer.get(p.getName() + "firework") - 1);
              }
            }.runTaskTimer(App.instance, 0, 20);

          } else {
            if (timer.get(p.getName() + "firework") == 1) {
              p.sendMessage(LanguageManager.getMessage(Variablelist.chat_wait, p)
                  .replace("%t%", timer.get(p.getName() + "firework") + "")
                  .replace("%s%", LanguageManager.getMessage(Variablelist.chat_secondSingular, p)));
            } else {
              p.sendMessage(LanguageManager.getMessage(Variablelist.chat_wait, p)
                  .replace("%t%", timer.get(p.getName() + "firework") + "")
                  .replace("%s%", LanguageManager.getMessage(Variablelist.chat_secondPlural, p)));

            }
          }
        } else if (e.getCurrentItem().getItemMeta().getDisplayName()
            .equalsIgnoreCase(LanguageManager.getMessage(Variablelist.inv_hint_Meow, p))) {
          if (timer.get(p.getName() + "meow") == null || timer.get(p.getName() + "meow") <= 0) {
            timer.put(p.getName() + "meow",
                Fileaccess.getInt("items.meow-duration", Fileaccess.getConfig()));
            for (Player all : Bukkit.getOnlinePlayers()) {
              Location pl = p.getLocation();
              Location l = new Location(pl.getWorld(), pl.getX(), pl.getY() + 1, pl.getZ());
              all.playSound(l, Sound.ENTITY_CAT_BEG_FOR_FOOD, 5, 1);
              all.spawnParticle(Particle.HEART, l, 3);
              StatsManager.addPoints(p.getUniqueId(),
                  Fileaccess.getInt("points.hint.meow", Fileaccess.getConfig()));
              p.sendMessage(LanguageManager.getMessage(Variablelist.chat_point, p).replace("%hp%",
                  Fileaccess.getInt("points.hint.meow", Fileaccess.getConfig()) + ""));

            }

            new BukkitRunnable() {
              @Override
              public void run() {
                if (timer.get(p.getName() + "meow") == 0) {
                  p.sendMessage(LanguageManager.getMessage(Variablelist.chat_wait_ended, p).replace(
                      "%name%", LanguageManager.getMessage(Variablelist.inv_hint_Meow, p)));
                  cancel();
                }
                timer.put(p.getName() + "meow", timer.get(p.getName() + "meow") - 1);
              }
            }.runTaskTimer(App.instance, 0, 20);

          } else {
            if (timer.get(p.getName() + "meow") == 1) {
              p.sendMessage(LanguageManager.getMessage(Variablelist.chat_wait, p)
                  .replace("%t%", timer.get(p.getName() + "meow") + "")
                  .replace("%s%", LanguageManager.getMessage(Variablelist.chat_secondSingular, p)));
            } else {
              p.sendMessage(LanguageManager.getMessage(Variablelist.chat_wait, p)
                  .replace("%t%", timer.get(p.getName() + "meow") + "")
                  .replace("%s%", LanguageManager.getMessage(Variablelist.chat_secondPlural, p)));

            }
          }

        } else if (e.getCurrentItem().getItemMeta().getDisplayName()
            .equalsIgnoreCase(LanguageManager.getMessage(Variablelist.inv_hint_Explosion, p))) {
          if (timer.get(p.getName() + "explosion") == null
              || timer.get(p.getName() + "explosion") <= 0) {
            timer.put(p.getName() + "explosion",
                Fileaccess.getInt("items.explosion-duration", Fileaccess.getConfig()));
            for (Player all : Bukkit.getOnlinePlayers()) {
              Location pl = p.getLocation();
              Location l = new Location(pl.getWorld(), pl.getX(), pl.getY() + 1, pl.getZ());
              all.playSound(l, Sound.ENTITY_GENERIC_EXPLODE, 5, 1);
              all.spawnParticle(Particle.EXPLOSION_LARGE, l, 3);
              StatsManager.addPoints(p.getUniqueId(),
                  Fileaccess.getInt("points.hint.explosion", Fileaccess.getConfig()));
              p.sendMessage(LanguageManager.getMessage(Variablelist.chat_point, p).replace("%hp%",
                  Fileaccess.getInt("points.hint.explosion", Fileaccess.getConfig()) + ""));
            }
          }

          new BukkitRunnable() {
            @Override
            public void run() {
              if (timer.get(p.getName() + "explosion") == 0) {
                p.sendMessage(LanguageManager.getMessage(Variablelist.chat_wait_ended, p).replace(
                    "%name%", LanguageManager.getMessage(Variablelist.inv_hint_Explosion, p)));
                cancel();
              }
              timer.put(p.getName() + "explosion", timer.get(p.getName() + "explosion") - 1);
            }
          }.runTaskTimer(App.instance, 0, 20);

        } else {
          if (timer.get(p.getName() + "explosion") == 1) {
            p.sendMessage(LanguageManager.getMessage(Variablelist.chat_wait, p)
                .replace("%t%", timer.get(p.getName() + "explosion") + "")
                .replace("%s%", LanguageManager.getMessage(Variablelist.chat_secondSingular, p)));
          } else {
            p.sendMessage(LanguageManager.getMessage(Variablelist.chat_wait, p)
                .replace("%t%", timer.get(p.getName() + "explosion") + "")
                .replace("%s%", LanguageManager.getMessage(Variablelist.chat_secondPlural, p)));

          }
        }
      } else if (p.getOpenInventory().getTitle()
          .equalsIgnoreCase(LanguageManager.getMessage(Variablelist.inv_mapvoting_name, p))) {
        String prefix = VariableManager
            .message(Fileaccess.getString("prefix.inv-item", Fileaccess.getConfig()));

        ArrayList<File> maps = VoteManager.rdmMap(Fileaccess.listOfFiles.get("maps"), 3);
        if (e.getCurrentItem().getItemMeta().getDisplayName()
            .equalsIgnoreCase(prefix + Fileaccess.getString("world", maps.get(0)))) {
          VoteManager.addVote(maps.get(0), p);
          MapvotingInventory.openInventory(p);
          p.sendMessage(LanguageManager.getMessage(Variablelist.chat_mapvoting, p).replace("%name%",
              Fileaccess.getString("world", maps.get(0))));
        } else if (e.getCurrentItem().getItemMeta().getDisplayName()
            .equalsIgnoreCase(prefix + Fileaccess.getString("world", maps.get(1)))) {
          VoteManager.addVote(maps.get(1), p);
          MapvotingInventory.openInventory(p);
          p.sendMessage(LanguageManager.getMessage(Variablelist.chat_mapvoting, p).replace("%name%",
              Fileaccess.getString("world", maps.get(1))));

        } else if (e.getCurrentItem().getItemMeta().getDisplayName()
            .equalsIgnoreCase(prefix + Fileaccess.getString("world", maps.get(2)))) {
          VoteManager.addVote(maps.get(2), p);
          MapvotingInventory.openInventory(p);
          p.sendMessage(LanguageManager.getMessage(Variablelist.chat_mapvoting, p).replace("%name%",
              Fileaccess.getString("world", maps.get(2))));

        }
      } else if (p.getOpenInventory().getTitle()
          .equalsIgnoreCase(LanguageManager.getMessage(Variablelist.inv_role_name, p))) {

        if (e.getCurrentItem().getItemMeta().getDisplayName()
            .equalsIgnoreCase(LanguageManager.getMessage(Variablelist.inv_role_seeker, p))) {
          if (RoleManager.getSeekers().size() <= Fileaccess.getInt("max-seeker",
              Fileaccess.getConfig())) {
            if (RoleManager.playerList.get(RoleManager.getPlayer(p)) == null) {
              PlayerData playerData = new PlayerData(p);
              playerData.setHider(true);
              RoleManager.playerList.add(playerData);

            } else {
              RoleManager.playerList.get(RoleManager.getPlayer(p)).setHider(false);
              RoleManager.playerList.get(RoleManager.getPlayer(p)).setSeeker(true);

            }
            p.sendMessage(LanguageManager.getMessage(Variablelist.chat_role, p).replace("%name%",
                LanguageManager.getMessage(Variablelist.chat_seeker, p).toString()));
          } else {
            p.sendMessage(LanguageManager.getMessage(Variablelist.chat_role_full, p).replace(
                "%name%", LanguageManager.getMessage(Variablelist.chat_seeker, p).toString()));

          }
        } else if (e.getCurrentItem().getItemMeta().getDisplayName()
            .equalsIgnoreCase(LanguageManager.getMessage(Variablelist.inv_role_hider, p))) {
          if (RoleManager.playerList.get(RoleManager.getPlayer(p)) == null) {
            PlayerData playerData = new PlayerData(p);
            playerData.setHider(true);
            RoleManager.playerList.add(playerData);

          } else {
            RoleManager.playerList.get(RoleManager.getPlayer(p)).setHider(true);
            RoleManager.playerList.get(RoleManager.getPlayer(p)).setSeeker(false);

          }
          p.sendMessage(LanguageManager.getMessage(Variablelist.chat_role, p).replace("%name%",
              LanguageManager.getMessage(Variablelist.chat_seeker, p).toString()));

        }
      } else if (p.getOpenInventory().getTitle()
          .equalsIgnoreCase(LanguageManager.getMessage(Variablelist.inv_chooseprop_name, p))) {
        if (e.getCurrentItem().getItemMeta().getDisplayName().startsWith(VariableManager
            .message(Fileaccess.getString("prefix.inv-item", Fileaccess.getConfig())))) {
          PropManager.choosedBlock.put(p, e.getCurrentItem().getType());
          p.sendMessage(LanguageManager.getMessage(Variablelist.chat_choosedprop, p)
              .replace("%name%", e.getCurrentItem().getType().name().replace("_", " ")));

        }
      } else if (p.getOpenInventory().getTitle()
          .equalsIgnoreCase(LanguageManager.getMessage(Variablelist.inv_gamemode_name, p))) {
        System.out.println("a1");
        if (e.getCurrentItem().getItemMeta().getDisplayName()
            .equalsIgnoreCase(LanguageManager.getMessage(Variablelist.inv_gamemode_classic, p))) {
          System.out.println("a2");

          Gamemode.voted.put(p, "classic");
          p.sendMessage(LanguageManager.getMessage(Variablelist.chat_voted_mode, p)
              .replace("%name%", LanguageManager.getMessage(Variablelist.chat_voted_classic)));

        } else if (e.getCurrentItem().getItemMeta().getDisplayName()
            .equalsIgnoreCase(LanguageManager.getMessage(Variablelist.inv_gamemode_prop, p))) {
          System.out.println("a3");

          Gamemode.voted.put(p, "prop");
          p.sendMessage(LanguageManager.getMessage(Variablelist.chat_voted_mode, p)
              .replace("%name%", LanguageManager.getMessage(Variablelist.chat_voted_prophunt)));

        }
      }
    }
  }
}
