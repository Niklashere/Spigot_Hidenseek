package de.niklashere.hidenseek.gamestates;

import java.util.HashMap;

import org.bukkit.entity.Player;

import de.niklashere.hidenseek.libary.Fileaccess;

public class Rolemanager {
    static HashMap<Player, String> role = new HashMap<>();
    static HashMap<String, Integer> groupsize = new HashMap<>();

    public static void initialize() {
        groupsize.put("seeker", 0);
        groupsize.put("hider", 0);
        groupsize.put("spectator", 0);

    }

    public static void addSeeker(Player p) {
        if (Fileaccess.getInt("max-seeker", Fileaccess.getConfig())-1 >= groupsize.get("seeker") && role.get(p) == null) {
            role.put(p, "seeker");
            groupsize.put("seeker", groupsize.get("seeker") + 1);
        }
    }

    public static void addHider(Player p) {
        if (role.get(p) == null) {
            role.put(p, "hider");
            groupsize.put("hider", groupsize.get("hider") + 1);
        }
    }

    public static void addSpectator(Player p) {
        if (role.get(p) == null) {
            role.put(p, "spectator");
            groupsize.put("spectator", groupsize.get("spectator") + 1);
        }
    }

    public static void removeRole(Player p) {
        if (getRole(p) == "seeker") {
            Rolemanager.removeSeeker(p);
        } else if (getRole(p) == "hider") {
            Rolemanager.removeHider(p);
        } else if (getRole(p) == "spectator") {
            Rolemanager.removeSpectator(p);
        }
    }

    public static void removeSeeker(Player p) {
        role.remove(p);
        groupsize.remove("seeker", groupsize.get("seeker") - 1);
        if (!((Gamestate.isState(Gamestate.Ingame) || Gamestate.isState(Gamestate.WarmUp)) && groupsize.get("seeker") >= Fileaccess.getInt("max-seeker", Fileaccess.getConfig()))) {
            GamestateManager.startEndCD();
        }

    }

    public static void removeHider(Player p) {
        role.remove(p);
        groupsize.remove("hider", groupsize.get("hider") - 1);
    }

    public static void removeSpectator(Player p) {
        role.remove(p);
        groupsize.put("spectator", groupsize.get("spectator") - 1);
    }

    public static String getRole(Player p) {
        return role.get(p);

    }

    public static boolean isSeeker(Player p) {
        if (role.get(p) == "seeker") {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isHider(Player p) {
        if (role.get(p) == "hider") {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isSpectator(Player p) {
        if (role.get(p) == "spectator") {
            return true;
        } else {
            return false;
        }
    }
}
