package de.niklashere.hidenseek.libary;

import de.niklashere.hidenseek.gamestates.Gamestate;
import de.niklashere.hidenseek.gamestates.RoleManager;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

/**
 * Manags Server layouts.
 *
 * @author Niklashere
 * @since 21.08.2021
 */
public class LayoutManager {

  private static HashMap<Player, String> rank = new HashMap<>();
  private static Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();

  /**
   * Set all layout again for user p.
   * 
   * @param p Player whos layout should be reset.
   */
  public static void setLayout(Player p) {
    sendTablist(p);
    setDisplayName(p);
    setTablistName(p);
  }

  /**
   * Sends specific tablist for each state.
   * 
   * @param p player who should recive the tablist
   */
  public static void sendTablist(Player p) {
    if (Fileaccess.getBoolean("Tablist.enabled", Fileaccess.getConfig())) {
      for (int i = 0; Fileaccess
          .getStringList("Tablist." + Gamestate.getState() + ".Header", Fileaccess.getConfig())
          .size() - 1 >= i; i++) {
        p.setPlayerListHeader(VariableManager.message(Fileaccess
            .getStringList("Tablist." + Gamestate.getState() + ".Header", Fileaccess.getConfig())
            .get(i), p));

      }
      for (int i = 0; Fileaccess
          .getStringList("Tablist." + Gamestate.getState() + ".Footer", Fileaccess.getConfig())
          .size() - 1 >= i; i++) {
        p.setPlayerListFooter(VariableManager.message(Fileaccess
            .getStringList("Tablist." + Gamestate.getState() + ".Footer", Fileaccess.getConfig())
            .get(i), p));

      }
    }
  }

  /**
   * Returns the rank of the player p.
   * 
   * @param p Player who rank should be queried.
   * @return Return String of the rank, defined in the config.
   */
  public static String getRank(Player p) {
    if (rank.get(p) != null) {
      return rank.get(p);
    } else {
      for (int i = 0; Fileaccess.getStringList("Ranks", Fileaccess.getConfig()).size()
          - 1 >= i; i++) {
        if (Fileaccess.getStringList("Ranks", Fileaccess.getConfig()).size() - 2 >= i) {
          if (p.hasPermission("Hidenseek.rank."
              + Fileaccess.getStringList("Ranks", Fileaccess.getConfig()).get(i))) {
            rank.put(p, Fileaccess.getStringList("Ranks", Fileaccess.getConfig()).get(i));
            break;
          }
        } else {
          rank.put(p, Fileaccess.getStringList("Ranks", Fileaccess.getConfig()).get(i));
          break;
        }
      }
      return rank.get(p);
    }
  }

  /**
   * Sets the display name for the player p.
   * 
   * @param p Player who should get a display name
   */
  public static void setDisplayName(Player p) {

    String prefix = VariableManager.message(
        Fileaccess.getString("Displaynames." + Gamestate.getState() + "." + getRank(p) + ".Prefix",
            Fileaccess.getConfig()),
        p);
    String suffix = VariableManager.message(
        Fileaccess.getString("Displaynames." + Gamestate.getState() + "." + getRank(p) + ".Suffix",
            Fileaccess.getConfig()),
        p);

    if (Fileaccess.getBoolean("Displaynames.enabled", Fileaccess.getConfig())) {

      if (!Gamestate.isState(Gamestate.Lobby)) {
        if (RoleManager.playerList.get(RoleManager.getPlayer(p)).isHider()) {
          prefix = prefix.replaceAll("%role%", VariableManager
              .message(Fileaccess.getString("Displaynames.Hider", Fileaccess.getConfig()), p));
          suffix = suffix.replaceAll("%role%", VariableManager
              .message(Fileaccess.getString("Displaynames.Hider", Fileaccess.getConfig()), p));

        } else if (RoleManager.playerList.get(RoleManager.getPlayer(p)).isSeeker()) {
          prefix = prefix.replaceAll("%role%", VariableManager
              .message(Fileaccess.getString("Displaynames.Seeker", Fileaccess.getConfig()), p));
          suffix = suffix.replaceAll("%role%", VariableManager
              .message(Fileaccess.getString("Displaynames.Seeker", Fileaccess.getConfig()), p));
        } else if (RoleManager.playerList.get(RoleManager.getPlayer(p)).isSpectator()) {
          prefix = prefix.replaceAll("%role%", VariableManager
              .message(Fileaccess.getString("Displaynames.Spectator", Fileaccess.getConfig()), p));
          suffix = suffix.replaceAll("%role%", VariableManager
              .message(Fileaccess.getString("Displaynames.Spectator", Fileaccess.getConfig()), p));
        }
      }
      Team team = board.getTeam(getRank(p));

      if (team == null) {
        team = board.registerNewTeam(getRank(p));
      }
      team.addEntry(p.getName());
      team.setPrefix(prefix);
      team.setSuffix(suffix);
      p.setDisplayName(prefix + p.getName() + suffix);

    }
  }

  /**
   * Sets the tablist name for the player p.
   * 
   * @param p Player who should get a tablist name
   */
  public static void setTablistName(Player p) {
    if (Fileaccess.getBoolean("PlayerListName.enabled", Fileaccess.getConfig())) {

      String msg = Fileaccess.getString("PlayerListName." + Gamestate.getState() + "." + getRank(p),
          Fileaccess.getConfig());
      System.out.println(msg);
      System.out.println("PlayerListName." + Gamestate.getState() + "." + getRank(p)
          + Fileaccess.getConfig().getName());

      if (Gamestate.isState(Gamestate.Lobby)) {
        p.setPlayerListName(VariableManager.message(msg, p));
      } else {
        if (RoleManager.playerList.get(RoleManager.getPlayer(p)).isHider()) {
          p.setPlayerListName(VariableManager.message(msg, p).replaceAll("%role%", VariableManager
              .message(Fileaccess.getString("PlayerListName.Hider", Fileaccess.getConfig()), p)));

        } else if (RoleManager.playerList.get(RoleManager.getPlayer(p)).isHider()) {
          p.setPlayerListName(VariableManager.message(msg, p).replaceAll("%role%", VariableManager
              .message(Fileaccess.getString("PlayerListName.Seeker", Fileaccess.getConfig()), p)));
        } else if (RoleManager.playerList.get(RoleManager.getPlayer(p)).isHider()) {
          p.setPlayerListName(
              VariableManager.message(msg, p).replaceAll("%role%", VariableManager.message(
                  Fileaccess.getString("PlayerListName.Spectator", Fileaccess.getConfig()), p)));
        }
      }
    }
  }
}