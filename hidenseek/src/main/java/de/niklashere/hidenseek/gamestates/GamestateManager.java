package de.niklashere.hidenseek.gamestates;

import org.bukkit.entity.Player;

public class GamestateManager {

	public static void clearInv(Player p) {
		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
	}
}
