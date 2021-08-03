package de.niklashere.hidenseek.inventorys;

import de.niklashere.hidenseek.files.languages.Variablelist;
import de.niklashere.hidenseek.libary.Fileaccess;
import de.niklashere.hidenseek.libary.ItemBuilder;
import de.niklashere.hidenseek.libary.LanguageManager;

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
          LanguageManager.getMessage(Variablelist.inv_setup_name, p));
      int x = 0;
      while (x < inv.getSize()) {
        inv.setItem(x, new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setNoName().build());
        x++;
      }

      inv.setItem(0,
          new ItemBuilder(Material.ENDER_PEARL)
              .setDisplayName(LanguageManager.getMessage(Variablelist.inv_setup_spawnpointLobby, p))
              .build());
      inv.setItem(9, new ItemBuilder(Material.ENDER_PEARL)
          .setDisplayName(LanguageManager.getMessage(Variablelist.inv_setup_spawnpointSeeker, p))
          .build());
      inv.setItem(18,
          new ItemBuilder(Material.ENDER_PEARL)
              .setDisplayName(LanguageManager.getMessage(Variablelist.inv_setup_spawnpointHider, p))
              .build());

      inv.setItem(10, new ItemBuilder(Material.OAK_SIGN)
          .setDisplayName(LanguageManager.getMessage(Variablelist.inv_setup_minPlayers, p)
              + Fileaccess.getInt("min-players", Fileaccess.getConfig()))
          .addLoreLine(LanguageManager.getMessage(Variablelist.inv_setup_instruction1, p))
          .addLoreLine(LanguageManager.getMessage(Variablelist.inv_setup_instruction2, p)).build());
      inv.setItem(11, new ItemBuilder(Material.BARRIER)
          .setDisplayName(LanguageManager.getMessage(Variablelist.inv_setup_maxPlayers, p)
              + Fileaccess.getInt("max-players", Fileaccess.getConfig()))
          .addLoreLine(LanguageManager.getMessage(Variablelist.inv_setup_instruction1, p))
          .addLoreLine(LanguageManager.getMessage(Variablelist.inv_setup_instruction2, p)).build());
      inv.setItem(12, new ItemBuilder(Material.ENDER_EYE)
          .setDisplayName(LanguageManager.getMessage(Variablelist.inv_setup_maxSeeker, p)
              + Fileaccess.getInt("max-seeker", Fileaccess.getConfig()))
          .addLoreLine(LanguageManager.getMessage(Variablelist.inv_setup_instruction1, p))
          .addLoreLine(LanguageManager.getMessage(Variablelist.inv_setup_instruction2, p)).build());
      inv.setItem(13, new ItemBuilder(Material.CLOCK)
          .setDisplayName(LanguageManager.getMessage(Variablelist.inv_setup_inGameTime, p)
              + Fileaccess.getInt("Ingame", Fileaccess.getConfig()))
          .addLoreLine(LanguageManager.getMessage(Variablelist.inv_setup_instruction1, p))
          .addLoreLine(LanguageManager.getMessage(Variablelist.inv_setup_instruction2, p)).build());

      p.openInventory(inv);
    } else {
      p.sendMessage(LanguageManager.getMessage(Variablelist.chat_missingPermission, p));
    }
  }
}
