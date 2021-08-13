package de.niklashere.hidenseek.commands;

import de.niklashere.hidenseek.files.languages.Variablelist;
import de.niklashere.hidenseek.gamestates.Gamestate;
import de.niklashere.hidenseek.gamestates.RoleManager;
import de.niklashere.hidenseek.inventorys.InventoryManager;
import de.niklashere.hidenseek.libary.Fileaccess;
import de.niklashere.hidenseek.libary.LanguageManager;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Language command which players to switch their language.
 *
 * @author Niklashere
 * @since 04.08.2021
 */
public class LanguageCommand implements CommandExecutor {

  @Override
  public final boolean onCommand(final CommandSender sender, final Command cmd, final String label,
      final String[] args) {
    Player p = (Player) sender;
    ArrayList<File> lang = Fileaccess.fileListToArrayList(Fileaccess.listOfFiles.get("languages"));
    if (args.length == 0) {
      p.sendMessage(LanguageManager.getMessage(Variablelist.command_lang_languages, p));
      int i = 0;
      String languages = " ";

      while (lang.size() - 1 >= i) {
        if (i >= 1) {
          languages = languages + ", " + lang.get(i).getName().replace(".yml", "");

        } else {
          languages = lang.get(i).getName().replace(".yml", "");

        }
        i++;
      }
      p.sendMessage(languages);
    } else if (args.length == 1) {
      File file = new File("plugins/hidenseek/languages", args[0].toLowerCase() + ".yml");
      if (lang.contains(file)) {
        LanguageManager.setLanguage(p, file);
        p.sendMessage(LanguageManager.getMessage(Variablelist.command_lang_switch, p));
        InventoryManager.clearInv(p);
        if (Gamestate.isState(Gamestate.Lobby) || Gamestate.isState(Gamestate.End)) {
          InventoryManager.lobbyItems(p);
        } else if (Gamestate.isState(Gamestate.WarmUp) || Gamestate.isState(Gamestate.Lobby)) {
          if (RoleManager.playerList.get(RoleManager.getPlayer(p)).isHider()) {
            InventoryManager.hiderItems(p);
          } else if (RoleManager.playerList.get(RoleManager.getPlayer(p)).isSeeker()) {
            if (Gamestate.isState(Gamestate.Ingame)) {
              InventoryManager.seekerItems(p);
            } else {
              InventoryManager.lobbyItems(p);
            }
          } else if (RoleManager.playerList.get(RoleManager.getPlayer(p)).isSpectator()) {
            InventoryManager.spectatorItems(p);
            ;
          }
        }
      } else {
        p.sendMessage(LanguageManager.getMessage(Variablelist.command_lang_languages, p)
            + args[0].toLowerCase());
        int i = 0;
        String languages = " ";

        while (lang.size() - 1 >= i) {
          if (i >= 1) {
            languages = languages + ", " + lang.get(i).getName().replace(".yml", "");

          } else {
            languages = lang.get(i).getName().replace(".yml", "");

          }
          i++;
        }
        p.sendMessage(languages);
      }
    } else {
      p.sendMessage(LanguageManager.getMessage(Variablelist.command_lang_usage, p));

    }

    return false;
  }
}