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

public class GamestateManager {

	public static void clearInv(Player p) {
		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
	}

	public static void startLobbyCD() {
		LobbyCountdown.startCountdown();
		Gamestate.setState(Gamestate.Lobby);
	}

	public static void startWarmupCD() {
		Bukkit.getScheduler().cancelTask(LobbyCountdown.task);
		for (Player all : Bukkit.getOnlinePlayers()) {
			clearInv(all);
			if (Rolemanager.isHider(all) || Rolemanager.isSpectator(all)) {
				all.teleport(Fileaccess.getLocation("spawnpoint-hider", Fileaccess.getConfig(), all));
			} else if (Rolemanager.isSeeker(all)) {
				all.teleport(Fileaccess.getLocation("spawnpoint-seeker", Fileaccess.getConfig(), all));
			}
		}
		WarmupCountdown.startCountdown();
		Gamestate.setState(Gamestate.WarmUp);
	}

	public static void startIngameCD() {
		Bukkit.getScheduler().cancelTask(WarmupCountdown.task);
		for (Player all : Bukkit.getOnlinePlayers()) {
			clearInv(all);
		}
		IngameCountdown.startCountdown();
		Gamestate.setState(Gamestate.Ingame);
	}

	public static void startEndCD() {
		Bukkit.getScheduler().cancelTask(IngameCountdown.task);
		for (Player all : Bukkit.getOnlinePlayers()) {
			all.teleport(Fileaccess.getLocation("spawnpoint-lobby", Fileaccess.getConfig(), all));
		}
		EndCountdown.startCountdown();
		Gamestate.setState(Gamestate.End);

	}

	public static void stopServer() {
		for (Player all : Bukkit.getOnlinePlayers()) {
			all.kickPlayer(VariableManager.message(LanguageManager.getMessage("stop-server", all)));
		}

	}
}
