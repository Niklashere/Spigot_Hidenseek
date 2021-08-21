package de.niklashere.hidenseek.commands;

import de.niklashere.hidenseek.files.languages.Variablelist;
import de.niklashere.hidenseek.gamestates.countdown.LobbyCountdown;
import de.niklashere.hidenseek.libary.Fileaccess;
import de.niklashere.hidenseek.libary.LanguageManager;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Setup command which allows admin to setup this plugin.
 *
 * @author Niklashere
 * @since 21.08.2021
 */
public class SkipCommand implements CommandExecutor {

  @Override
  public final boolean onCommand(final CommandSender sender, final Command cmd, final String label,
      final String[] args) {
    Player p = (Player) sender;
    if (p.hasPermission("hidenseek.skip")) {
      if (LobbyCountdown.time >= Fileaccess.getInt("Countdown.Full", Fileaccess.getConfig())) {
        LobbyCountdown.time = Fileaccess.getInt("Countdown.Full", Fileaccess.getConfig());
        for (Player all : Bukkit.getOnlinePlayers()) {
          all.sendMessage(LanguageManager.getMessage(Variablelist.chat_skiplobby, all));
        }
      } else {
        p.sendMessage(LanguageManager.getMessage(Variablelist.chat_already_skipped, p));

      }
    } else {
      p.sendMessage(LanguageManager.getMessage(Variablelist.chat_missingPermission, p));
    }
    return false;
  }
}
