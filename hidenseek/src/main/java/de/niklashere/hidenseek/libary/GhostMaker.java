package de.niklashere.hidenseek.libary;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

/**
 * Class to make players translucent.
 *
 * @author Niklashere
 * @since 31-07-2021
 */
public class GhostMaker {

  private static ScoreboardManager manager;
  private static Scoreboard board;
  private static Team team;

  /**
   * Make a player translucent like a ghost.
   * 
   * @param p player, who should get translucent
   * @param teamName name of the team, who can see him translucent
   */
  public static void addGhost(Player p, String teamName) {
    manager = Bukkit.getScoreboardManager();
    board = manager.getNewScoreboard();
    team = board.registerNewTeam(teamName);
    team.setAllowFriendlyFire(true);
    team.setCanSeeFriendlyInvisibles(true);
    team.setDisplayName("Ghost");
    p.setScoreboard(board);
    team.addEntry(p.getName());
  }
}
