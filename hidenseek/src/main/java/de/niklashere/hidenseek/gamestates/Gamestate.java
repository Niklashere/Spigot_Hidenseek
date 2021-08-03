package de.niklashere.hidenseek.gamestates;

/**
 * Enum class wich outputs current gamestate.
 *
 * @author Niklashere
 * @since 31.07.2021
 */
public enum Gamestate {
  /**
   * Definition of current gamestates.
   */
  Lobby, Full, WarmUp, Ingame, End;

  /**
   * Saves current gamestate.
   */
  private static Gamestate state;

  /**
   * Change the current gamestate.
   *
   * @param gamestate gamestate to which to switch
   */
  public static void setState(final Gamestate gamestate) {
    Gamestate.state = gamestate;
  }

  /**
   * Query whether gamestate is the current gamestate.
   *
   * @param gamestate Gamestate which should be queried
   * @return if state is the current gamestate
   */
  public static boolean isState(final Gamestate gamestate) {
    if (Gamestate.state == gamestate) {
      return true;
    }
    return false;
  }

  /**
   * Query which gamestate is current.
   *
   * @return outputs current gamestate
   */
  public static Gamestate getState() {
    return state;
  }
}
