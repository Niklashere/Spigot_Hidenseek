package de.niklashere.hidenseek.gamestates;

import org.bukkit.entity.Player;

/**
 * Constructor for player data.
 *
 * @author Niklashere
 * @since 04.08.2021
 */
public class PlayerData {
  private Player player;
  private boolean isSeeker = false;
  private boolean isHider = false;
  private boolean isSpectator = false;

  /**
   * Constructor to save game roles to players.
   * 
   * @param p Player
   */
  public PlayerData(Player p) {
    this.player = p;

  }

  /**
   * Get the player.
   * 
   * @return Player
   */
  public Player getPlayer() {
    return player;
  }

  /**
   * Query if player is Seeker.
   * 
   * @return boolean
   */
  public boolean isSeeker() {
    return isSeeker;
  }

  /**
   * Query if player is Hider.
   * 
   * @return boolean
   */
  public boolean isHider() {
    return isHider;
  }

  /**
   * Query if player is Spectator.
   * 
   * @return boolean
   */
  public boolean isSpectator() {
    return isSpectator;
  }

  /**
   * Give player the seeker role.
   */
  public void setSeeker(boolean isSeeker) {
    this.isSeeker = isSeeker;
  }

  /**
   * Give player the hider role.
   */
  public void setHider(boolean isHider) {
    this.isHider = isHider;
  }

  /**
   * Give player the spectator role.
   */
  public void setSpectator(boolean isSpectator) {
    this.isSpectator = isSpectator;
  }
}