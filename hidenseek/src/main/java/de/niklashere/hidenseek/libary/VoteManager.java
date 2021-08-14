package de.niklashere.hidenseek.libary;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import org.bukkit.entity.Player;

/**
 * Manages all votes for map vote.
 *
 * @author Niklashere
 * @since 02.08.2021
 */
public class VoteManager {

  static ArrayList<File> maps = new ArrayList<>();
  static LinkedHashmap<Player, File> votes = new LinkedHashmap<>();
  static File result;

  /**
   * Outputs random maps for map voting.
   * 
   * @param file    files of the maps
   * @param maxMaps Number of maps for map voting
   * @return Arraylist of choosen files.
   */
  public static ArrayList<File> rdmMap(File[] file, int maxMaps) {
    ArrayList<File> files = Fileaccess.fileListToArrayList(file);
    for (int i = maps.size() + 1; i <= maxMaps; i++) {
      int rdm = 0;
      if (files.size() >= 1) {
        rdm = new Random().nextInt(files.size());
      }
      maps.add(files.get(rdm));
      files.remove(rdm);
    }

    return maps;
  }

  /**
   * Adds a vote.
   * 
   * @param file Map wich a voted for.
   * @param p    player who has voted.
   */
  public static void addVote(File file, Player p) {
    votes.put(p, file);

  }

  /**
   * Remove a vote.
   * 
   * @param file Map wich voted gets removed.
   * @param p    player who removed his vote.
   */
  public static void removeVote(File file, Player p) {
    votes.remove(p);

  }

  /**
   * Outputs the mapvoting results.
   * 
   * @return file of the voted map
   */
  public static File getResults() {
    if (result == null) {
      File result = maps.get(0);
      LinkedHashmap<File, Integer> results = new LinkedHashmap<>();
      ArrayList<File> map = VoteManager.rdmMap(Fileaccess.listOfFiles.get("maps"), 3);
      for (int i = 0; map.size() - 1 >= i; i++) {
        results.put(map.get(i), 0);

      }

      for (int i = 0; votes.size() - 1 >= i; i++) {
        results.put(votes.getValue(i), results.get(votes.getValue(i)) + 1);
      }

      for (int i = 0; results.size() - 1 >= i; i++) {
        if (results.getValue(i) >= results.getValue(results.get(result))) {
          result = results.getEntry(i).getKey();

        }
      }
      return result;

    }
    return result;

  }
}
