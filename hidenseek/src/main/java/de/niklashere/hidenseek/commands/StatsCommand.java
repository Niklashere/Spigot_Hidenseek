package de.niklashere.hidenseek.commands;

import de.niklashere.hidenseek.libary.LanguageManager;
import de.niklashere.hidenseek.libary.UuidFetcher;
import de.niklashere.hidenseek.libary.VariableManager;

import org.bukkit.Bukkit;
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
  public final boolean onCommand(final CommandSender sender, final Command cmd,
      final String label, final String[] args) {
    Player p = (Player) sender;
    if (args.length == 0) {
      p.sendMessage(VariableManager.message(LanguageManager.getMessage("stats.m1", p), p));
      p.sendMessage(VariableManager.message(LanguageManager.getMessage("stats.m2", p), p));
      p.sendMessage(VariableManager.message(LanguageManager.getMessage("stats.m3", p), p));
      p.sendMessage(VariableManager.message(LanguageManager.getMessage("stats.m4", p), p));
      p.sendMessage(VariableManager.message(LanguageManager.getMessage("stats.m5", p), p));

    } else if (args.length == 1) {
      Player k = (Player) Bukkit.getOfflinePlayer(UuidFetcher.getUuid(args[0]));
      p.sendMessage(VariableManager.message(LanguageManager.getMessage("stats.m1", p), k));
      p.sendMessage(VariableManager.message(LanguageManager.getMessage("stats.m2", p), k));
      p.sendMessage(VariableManager.message(LanguageManager.getMessage("stats.m3", p), k));
      p.sendMessage(VariableManager.message(LanguageManager.getMessage("stats.m4", p), k));
      p.sendMessage(VariableManager.message(LanguageManager.getMessage("stats.m5", p), k));
      
    } else {
      p.sendMessage(VariableManager.message(LanguageManager.getMessage("stats.usage", p), p));
    }
    return false;
  }
}
