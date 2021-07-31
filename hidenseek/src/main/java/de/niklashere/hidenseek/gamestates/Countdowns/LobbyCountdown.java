package de.niklashere.hidenseek.gamestates.countdowns;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import de.niklashere.hidenseek.App;
import de.niklashere.hidenseek.gamestates.GamestateManager;
import de.niklashere.hidenseek.libary.Fileaccess;
import de.niklashere.hidenseek.libary.LanguageManager;
import de.niklashere.hidenseek.libary.VariableManager;

public class LobbyCountdown {
	public static int task;
  static int time = Fileaccess.getInt("Lobby", Fileaccess.getConfig()) + 1;

    public static void startCountdown() {
		task = Bukkit.getScheduler().scheduleSyncRepeatingTask(App.instance, new Runnable() {

			@Override
			public void run() {
        time--;
        if (Bukkit.getOnlinePlayers().size() >= Fileaccess.getInt("min-players", Fileaccess.getConfig())) {
          for (Player all : Bukkit.getOnlinePlayers()) {
            all.setLevel(time);
            int i = time%60;
            if (i == 0 || time == 30 || time == 15 || time == 10 || time == 5 || time == 3 || time == 2) {
              all.sendMessage(VariableManager.message(LanguageManager.getMessage("countdown-lobby", all).replaceAll("%t", time + "").replaceAll("%s", VariableManager.message(LanguageManager.getMessage("second-plural", all)))));
                
            } else if (time == 1) {
              all.sendMessage(VariableManager.message(LanguageManager.getMessage("countdown-lobby", all).replaceAll("%t", time + "").replaceAll("%s", VariableManager.message(LanguageManager.getMessage("second-singular", all)))));
              GamestateManager.startWarmupCD();
            }
          }
        } else {
          time = Fileaccess.getInt("Lobby", Fileaccess.getConfig())+1;
        }
      }
		}, 0, 20);
  }
}
