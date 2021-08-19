package de.niklashere.hidenseek.commands;

import de.niklashere.hidenseek.files.languages.Variablelist;
import de.niklashere.hidenseek.libary.Fileaccess;
import de.niklashere.hidenseek.libary.LanguageManager;
import de.niklashere.hidenseek.libary.StatsManager;

import java.util.ArrayList;
import java.util.UUID;

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
 * @since 18.08.2021
 */
public class LeaderboardCommand implements CommandExecutor {

  @Override
  public final boolean onCommand(final CommandSender sender, final Command cmd, final String label,
      final String[] args) {
    Player p = (Player) sender;
    int pos = 0;
    ArrayList<UUID> top = StatsManager.getTop(
        Fileaccess.getStringList(Variablelist.command_lead, LanguageManager.getLanguage(p)).size());

    for (int i = 0; Fileaccess
        .getStringList(Variablelist.command_lead, LanguageManager.getLanguage(p)).size()
        - 1 >= i; i++) {
      if (top.size() - 1 >= pos && LanguageManager
          .getMessageFromList(Variablelist.command_lead, p, i).contains("%pos%")) {
        OfflinePlayer k = Bukkit.getOfflinePlayer(top.get(pos));
        p.sendMessage(LanguageManager.getMessageFromList(Variablelist.command_lead, p, k, i)
            .replaceAll("%pos%", pos + ""));
        pos++;
      } else if (!LanguageManager.getMessageFromList(Variablelist.command_lead, p, i)
          .contains("%pos%")) {
        p.sendMessage(LanguageManager.getMessageFromList(Variablelist.command_lead, p, i));

      }

    }

    return false;
  }
}
