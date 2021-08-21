package de.niklashere.hidenseek.gamestates;

import java.util.HashMap;

import org.bukkit.entity.Player;

/**
 * Enum class wich outputs current gamemode.
 *
 * @author Niklashere
 * @since 21.08.2021
 */
public enum Gamemode {

  /**
   * Definition of current gamemode.
   */
  Prophunt, Classic;

  public static HashMap<Player, String> voted = new HashMap<>();


  /**
   * Saves current gamemode.
   */
  private static Gamemode mode;

  /**
   * Change the current gamemode.
   *
   * @param mode gamemode to which to switch
   */
  public static void setMode(final Gamemode mode) {
    Gamemode.mode = mode;
  }

  /**
   * Query whether gamemode is the current gamemode.
   *
   * @param mode Gamemode which should be queried
   * @return if mode is the current gamemode
   */
  public static boolean isMode(final Gamemode mode) {
    if (Gamemode.mode == mode) {
      return true;
    }
    return false;
  }

  /**
   * Query which gamemode is current.
   *
   * @return outputs current gamemode
   */
  public static Gamemode getMode() {
    return mode;
  }
}
