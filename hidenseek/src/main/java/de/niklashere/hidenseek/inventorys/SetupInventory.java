package de.niklashere.hidenseek.inventorys;

import de.niklashere.hidenseek.files.languages.Variablelist;
import de.niklashere.hidenseek.libary.Fileaccess;
import de.niklashere.hidenseek.libary.ItemBuilder;
import de.niklashere.hidenseek.libary.LanguageManager;
import de.niklashere.hidenseek.libary.VariableManager;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * Role inventory.
 *
 * @author Niklashere
 * @since 01.08.2021
 */
public class SetupInventory {

  /**
   * Opens setup inventory.
   *
   * @param p Players for whom the inventory should be opened
   */
  public static void openInventory(Player p) {
    if (p.hasPermission("hidenseek.admin")) {
      Inventory inv = Bukkit.createInventory(null, 9 * 3,
          VariableManager.message(LanguageManager.getMessage(Variablelist.inv_setup_name, p), p));
      int x = 0;
      while (x < inv.getSize()) {
        inv.setItem(x, new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setNoName().build());
        x++;
      }

      inv.setItem(0,
          new ItemBuilder(Material.ENDER_PEARL)
              .setDisplayName(VariableManager.message(
                  LanguageManager.getMessage(Variablelist.inv_setup_spawnpointLobby, p), p))
              .build());
      inv.setItem(9,
          new ItemBuilder(Material.ENDER_PEARL)
              .setDisplayName(VariableManager.message(
                  LanguageManager.getMessage(Variablelist.inv_setup_spawnpointSeeker, p), p))
              .build());
      inv.setItem(18,
          new ItemBuilder(Material.ENDER_PEARL)
              .setDisplayName(VariableManager.message(
                  LanguageManager.getMessage(Variablelist.inv_setup_spawnpointHider, p), p))
              .build());

      inv.setItem(10,
          new ItemBuilder(Material.OAK_SIGN)
              .setDisplayName(VariableManager
                  .message(LanguageManager.getMessage(Variablelist.inv_setup_minPlayers, p), p)
                  + Fileaccess.getInt("min-players", Fileaccess.getConfig()))
              .addLoreLine(VariableManager
                  .message(LanguageManager.getMessage(Variablelist.inv_setup_instruction1, p), p))
              .addLoreLine(VariableManager
                  .message(LanguageManager.getMessage(Variablelist.inv_setup_instruction2, p), p))
              .build());
      inv.setItem(11,
          new ItemBuilder(Material.BARRIER)
              .setDisplayName(VariableManager
                  .message(LanguageManager.getMessage(Variablelist.inv_setup_maxPlayers, p), p)
                  + Fileaccess.getInt("max-players", Fileaccess.getConfig()))
              .addLoreLine(VariableManager
                  .message(LanguageManager.getMessage(Variablelist.inv_setup_instruction1, p), p))
              .addLoreLine(VariableManager
                  .message(LanguageManager.getMessage(Variablelist.inv_setup_instruction2, p), p))
              .build());
      inv.setItem(12,
          new ItemBuilder(Material.ENDER_EYE)
              .setDisplayName(VariableManager
                  .message(LanguageManager.getMessage(Variablelist.inv_setup_maxSeeker, p), p)
                  + Fileaccess.getInt("max-seeker", Fileaccess.getConfig()))
              .addLoreLine(VariableManager
                  .message(LanguageManager.getMessage(Variablelist.inv_setup_instruction1, p), p))
              .addLoreLine(VariableManager
                  .message(LanguageManager.getMessage(Variablelist.inv_setup_instruction2, p), p))
              .build());
      inv.setItem(13,
          new ItemBuilder(Material.CLOCK)
              .setDisplayName(VariableManager
                  .message(LanguageManager.getMessage(Variablelist.inv_setup_inGameTime, p), p)
                  + Fileaccess.getInt("Ingame", Fileaccess.getConfig()))
              .addLoreLine(VariableManager
                  .message(LanguageManager.getMessage(Variablelist.inv_setup_instruction1, p), p))
              .addLoreLine(VariableManager
                  .message(LanguageManager.getMessage(Variablelist.inv_setup_instruction2, p), p))
              .build());

      p.openInventory(inv);
    } else {
      p.sendMessage(VariableManager
          .message(LanguageManager.getMessage(Variablelist.chat_missingPermission, p), p));
    }
  }
}
