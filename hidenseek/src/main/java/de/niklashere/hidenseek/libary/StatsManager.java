package de.niklashere.hidenseek.libary;

import java.util.UUID;

/**
 * Add, remove and get player stats from the mysql.
 *
 * @author Niklashere
 * @since 03.08.2021
 */
public class StatsManager {

  /**
   * Query whether a player already exists in the database.
   * 
   * @param uuid uuid of the queried player
   * @return is in the databse
   */
  public static boolean playerExists(UUID uuid) {
    return MysqlManager.queryString("SELECT * FROM Stats WHERE UUID= '" + uuid + "';",
    "UUID") != null;
  }

  /**
   * Create an entry in the database for the player.
   * 
   * @param uuid uuid of the player
   */
  public static void createPlayer(UUID uuid) {
    if (!playerExists(uuid)) {
      MysqlManager.update("INSERT INTO Stats(UUID, cought, found, wins, plays, points) VALUES ('"
          + uuid + "', '0', '0', '0', '0', '0');");
    }
  }

  /**
   * Get all wins of the player.
   * 
   * @param uuid uuid of the player
   * @return wins
   */
  public static Integer getWins(UUID uuid) {
    return MysqlManager.queryInt("SELECT * FROM Stats WHERE UUID= '" + uuid + "';",
    "wins");
  }

  /**
   * Get all coughts of the player.
   * 
   * @param uuid uuid of the player
   * @return coughts
   */
  public static Integer getCought(UUID uuid) {
    return MysqlManager.queryInt("SELECT * FROM Stats WHERE UUID= '" + uuid + "';",
    "cought");
  }

  /**
   * Get all founds of the player.
   * 
   * @param uuid uuid of the player
   * @return founds
   */
  public static Integer getFound(UUID uuid) {
    return MysqlManager.queryInt("SELECT * FROM Stats WHERE UUID= '" + uuid + "';",
    "found");
  }

  /**
   * Set winns to the player.
   * 
   * @param uuid uuid of the player
   * @param wins wins of the player
   */
  public static void setWins(UUID uuid, Integer wins) {
    if (playerExists(uuid)) {
      MysqlManager.update("UPDATE Stats SET wins= '" + wins + "' WHERE UUID= '" + uuid + "';");
    } else {
      createPlayer(uuid);
      setWins(uuid, wins);
    }
  }

  /**
   * Add wins to the player.
   * 
   * @param uuid uuid of the player
   * @param wins adds wins to the player
   */
  public static void addWins(UUID uuid, Integer wins) {
    if (playerExists(uuid)) {
      setWins(uuid, Integer.valueOf(getWins(uuid).intValue() + wins.intValue()));
      addPoints(uuid, Fileaccess.getInt("points.win", Fileaccess.getConfig()));

    } else {
      createPlayer(uuid);
      addWins(uuid, wins);
    }
  }

  /**
   * Get all played games of the player.
   * 
   * @param uuid uuid of the player
   * @return played games
   */
  public static Integer getPlayes(UUID uuid) {
    return MysqlManager.queryInt("SELECT * FROM Stats WHERE UUID= '" + uuid + "';",
    "plays");
  }

  /**
   * Set plays to the player.
   * 
   * @param uuid  uuid of the player
   * @param plays plays of the player
   */
  public static void setPlayes(UUID uuid, Integer plays) {
    if (playerExists(uuid)) {
      MysqlManager.update("UPDATE Stats SET plays= '" + plays + "' WHERE UUID= '" + uuid + "';");
    } else {
      createPlayer(uuid);
      setPlayes(uuid, plays);
    }
  }

  /**
   * Add plays to the player.
   * 
   * @param uuid  uuid of the player
   * @param plays adds plays to the player
   */
  public static void addPlayes(UUID uuid, Integer plays) {
    if (playerExists(uuid)) {
      setPlayes(uuid, Integer.valueOf(getPlayes(uuid).intValue() + plays.intValue()));
      addPoints(uuid, Fileaccess.getInt("points.play", Fileaccess.getConfig()));

    } else {
      createPlayer(uuid);
      addPlayes(uuid, plays);
    }
  }

  /**
   * Get all points of the player.
   * 
   * @param uuid uuid of the player
   * @return points
   */
  public static Integer getPoints(UUID uuid) {
    return MysqlManager.queryInt("SELECT * FROM Stats WHERE UUID= '" + uuid + "';",
    "points");
  }

  /**
   * Set points to the player.
   * 
   * @param uuid   uuid of the player
   * @param points points of the player
   */
  public static void setPoints(UUID uuid, Integer points) {
    if (playerExists(uuid)) {
      MysqlManager.update("UPDATE Stats SET points= '" + points + "' WHERE UUID= '" + uuid + "';");
    } else {
      createPlayer(uuid);
      setPoints(uuid, points);
    }
  }

  /**
   * Add points to the player.
   * 
   * @param uuid   uuid of the player
   * @param points adds points to the player
   */
  public static void addPoints(UUID uuid, Integer points) {
    if (playerExists(uuid)) {
      setPoints(uuid, Integer.valueOf(getPoints(uuid).intValue() + points.intValue()));
    } else {
      createPlayer(uuid);
      addPoints(uuid, points);
    }
  }

  /**
   * Set cought to the player.
   * 
   * @param uuid   uuid of the player
   * @param cought cought of the player
   */
  public static void setCought(UUID uuid, Integer cought) {
    if (playerExists(uuid)) {
      MysqlManager.update("UPDATE Stats SET cought= '" + cought + "' WHERE UUID= '" + uuid + "';");
    } else {
      createPlayer(uuid);
      setCought(uuid, cought);
    }
  }

  /**
   * Add cought to the player.
   * 
   * @param uuid   uuid of the player
   * @param cought adds cought to the player
   */
  public static void addCought(UUID uuid, Integer cought) {
    if (playerExists(uuid)) {
      setCought(uuid, Integer.valueOf(getCought(uuid).intValue() + cought.intValue()));
      addPoints(uuid, Fileaccess.getInt("points.cought", Fileaccess.getConfig()));
    } else {
      createPlayer(uuid);
      addCought(uuid, cought);
    }
  }

  /**
   * Set found to the player.
   * 
   * @param uuid  uuid of the player
   * @param found found of the player
   */
  public static void setFound(UUID uuid, Integer found) {
    if (playerExists(uuid)) {
      MysqlManager.update("UPDATE Stats SET found= '" + found + "' WHERE UUID= '" + uuid + "';");
    } else {
      createPlayer(uuid);
      setFound(uuid, found);
    }
  }

  /**
   * Add found to the player.
   * 
   * @param uuid  uuid of the player
   * @param found adds found to the player
   */
  public static void addFound(UUID uuid, Integer found) {
    if (playerExists(uuid)) {
      setFound(uuid, Integer.valueOf(getFound(uuid).intValue() + found.intValue()));
      addPoints(uuid, Fileaccess.getInt("points.found", Fileaccess.getConfig()));

    } else {
      createPlayer(uuid);
      addFound(uuid, found);
    }
  }
}
