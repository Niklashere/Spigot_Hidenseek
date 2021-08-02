package de.niklashere.hidenseek.gamestates;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import de.niklashere.hidenseek.gamestates.countdowns.EndCountdown;
import de.niklashere.hidenseek.gamestates.countdowns.IngameCountdown;
import de.niklashere.hidenseek.gamestates.countdowns.LobbyCountdown;
import de.niklashere.hidenseek.gamestates.countdowns.WarmupCountdown;
import de.niklashere.hidenseek.libary.Fileaccess;
import de.niklashere.hidenseek.libary.LanguageManager;
import de.niklashere.hidenseek.libary.VariableManager;
import de.niklashere.hidenseek.libary.WorldManager;
import de.niklashere.inventorys.InventoryManager;

public class GamestateManager {


	public static void startLobbyCD() {
		LobbyCountdown.startCountdown();
		Gamestate.setState(Gamestate.Lobby);
		for (Player all : Bukkit.getOnlinePlayers()) {
			InventoryManager.lobbyItems(all);
		}
	}

	public static void startWarmupCD() {
		for (Player all : Bukkit.getOnlinePlayers()) {
			InventoryManager.clearInv(all);
			if (Rolemanager.isHider(all)) {
				all.teleport(Fileaccess.getLocation("spawnpoint-hider", WorldManager.getResults(), all));
				InventoryManager.hiderItems(all);
			} else if (Rolemanager.isSeeker(all)) {
				all.teleport(Fileaccess.getLocation("spawnpoint-seeker", WorldManager.getResults(), all));
				InventoryManager.seekerItems(all);
			} else if (Rolemanager.isSpectator(all)) {
				all.teleport(Fileaccess.getLocation("spawnpoint-hider", WorldManager.getResults(), all));
				InventoryManager.spectatorItems(all);

			}
		}
		WarmupCountdown.startCountdown();
		Gamestate.setState(Gamestate.WarmUp);
	}

	public static void startIngameCD() {
		IngameCountdown.startCountdown();
		Gamestate.setState(Gamestate.Ingame);
	}

	public static void startEndCD() {
		for (Player all : Bukkit.getOnlinePlayers()) {
			all.teleport(Fileaccess.getLocation("spawnpoint-lobby", Fileaccess.getConfig(), all));
			InventoryManager.clearInv(all);
			InventoryManager.lobbyItems(all);
		}
		EndCountdown.startCountdown();
		Gamestate.setState(Gamestate.End);
	}

	public static void stopServer() {
		for (Player all : Bukkit.getOnlinePlayers()) {
			all.kickPlayer(VariableManager.message(LanguageManager.getMessage("stop-server", all)));
		}
		Bukkit.shutdown();

	}
}
