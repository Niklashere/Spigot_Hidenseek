package de.niklashere.hidenseek.libary;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import org.bukkit.entity.Player;

public class WorldManager {
    
    static ArrayList<File> maps = new ArrayList<>();
    static LinkedHashmap<Player, File> votes = new LinkedHashmap<>();
    static File result;

    public static ArrayList<File> rdmMap(File[] file, int maxMaps) {
        ArrayList<File> files = Fileaccess.fileListToArrayList(file);
        for (int i = maps.size()+1; i <= maxMaps; i++) {
            int rdm = new Random().nextInt(files.size()-1);
            maps.add(files.get(rdm));
            files.remove(rdm);
        }
        
        return maps;
    }

    public static void addVote(File file, Player p) {
        votes.put(p, file);

    }

    public static void removeVote(File file, Player p) {
        votes.remove(p);

    }

    public static File getResults() {
        if (result == null) {
            File result = maps.get(0);
            LinkedHashmap<File, Integer> results = new LinkedHashmap<>();
            ArrayList<File> map = WorldManager.rdmMap(Fileaccess.listOfFiles.get("maps"), 3);
            for (int i = 0; map.size()-1 >= i; i++) {
                results.put(map.get(i), 0);

            }

            for (int i = 0; votes.size()-1 >= i; i++) {
                results.put(votes.getValue(i), results.get(votes.getValue(i))+1);
            }
            

            for (int i = 0; results.size()-1 >= i; i++) {
                if (results.getValue(i) >= results.getValue(results.get(result))) {
                    result = results.getEntry(i).getKey();

                }
            }
            return result;

        } 
        return result;

    }
}
