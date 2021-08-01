package de.niklashere.hidenseek.gamestates.countdowns;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import de.niklashere.hidenseek.App;
import de.niklashere.hidenseek.gamestates.GamestateManager;
import de.niklashere.hidenseek.libary.Fileaccess;
import de.niklashere.hidenseek.libary.LanguageManager;
import de.niklashere.hidenseek.libary.VariableManager;

public class EndCountdown {
	public static int task;
  static int time = Fileaccess.getInt("End", Fileaccess.getConfig()) + 1;

    public static void startCountdown() {
      new BukkitRunnable() {

			@Override
			public void run() {
        if (Bukkit.getOnlinePlayers().size() >= 1) {
          time--;
          for (Player all : Bukkit.getOnlinePlayers()) {
            all.setLevel(time);
            int i = time%60;
            if (i == 0 || time == 30 || time == 15 || time == 10 || time == 5 || time == 3 || time == 2) {
              all.sendMessage(VariableManager.message(LanguageManager.getMessage("countdown-end", all).replaceAll("%t", time + "").replaceAll("%s", VariableManager.message(LanguageManager.getMessage("second-plural", all)))));
              
            } else if (time == 1) {
              all.sendMessage(VariableManager.message(LanguageManager.getMessage("countdown-end", all).replaceAll("%t", time + "").replaceAll("%s", VariableManager.message(LanguageManager.getMessage("second-singular", all)))));
            }
          }
          if (time == 1) {
            GamestateManager.stopServer();
            cancel();

          }
        } else {
          GamestateManager.stopServer();
          cancel();        
        }
      }
		}.runTaskTimer(App.instance, 0L, 20);
  }
}
