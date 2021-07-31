package de.niklashere.hidenseek.gamestates;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import de.niklashere.hidenseek.gamestates.Countdowns.EndCountdown;
import de.niklashere.hidenseek.gamestates.Countdowns.IngameCountdown;
import de.niklashere.hidenseek.gamestates.Countdowns.LobbyCountdown;
import de.niklashere.hidenseek.gamestates.Countdowns.WarmupCountdown;
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
