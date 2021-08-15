package de.niklashere.hidenseek.commands;

import de.niklashere.hidenseek.files.languages.Variablelist;
import de.niklashere.hidenseek.libary.Fileaccess;
import de.niklashere.hidenseek.libary.LanguageManager;
import de.niklashere.hidenseek.libary.UuidFetcher;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Stats command which allows players to see their stats.
 *
 * @author Niklashere
 * @since 03.08.2021
 */
public class StatsCommand implements CommandExecutor {

  @Override
  public final boolean onCommand(final CommandSender sender, final Command cmd, final String label,
      final String[] args) {
    Player p = (Player) sender;
    if (args.length == 0) {
      for (int i = 0; Fileaccess
          .getStringList(Variablelist.command_stats, LanguageManager.getLanguage(p)).size()
          - 1 >= i; i++) {
        p.sendMessage(LanguageManager.getMessageFromList(Variablelist.command_stats, p, i));

      }
    } else if (args.length == 1) {
      OfflinePlayer k = Bukkit.getOfflinePlayer(UuidFetcher.getUuid(args[0]));
      for (int i = 0; Fileaccess
          .getStringList(Variablelist.command_stats, LanguageManager.getLanguage(p)).size()
          - 1 >= i; i++) {
        p.sendMessage(LanguageManager.getMessageFromList(Variablelist.command_stats, p, k, i));

      }
    } else {
      p.sendMessage(LanguageManager.getMessage(Variablelist.command_stats_usage, p));
    }
    return false;
  }
}
