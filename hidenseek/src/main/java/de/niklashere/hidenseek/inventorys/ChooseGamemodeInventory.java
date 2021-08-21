package de.niklashere.hidenseek.inventorys;

import de.niklashere.hidenseek.files.languages.Variablelist;
import de.niklashere.hidenseek.libary.ItemBuilder;
import de.niklashere.hidenseek.libary.LanguageManager;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * Choose Gamemode inventory.
 *
 * @author Niklashere
 * @since 21.08.2021
 */
public class ChooseGamemodeInventory {

  /**
   * Opens Gamemode inventory.
   *
   * @param p Players for whom the inventory should be opened
   */
  public static void openInventory(final Player p) {
    Inventory inv = Bukkit.createInventory(null, 9 * 3,
        LanguageManager.getMessage(Variablelist.inv_gamemode_name, p));
    int x = 0;
    while (x < inv.getSize()) {
      inv.setItem(x, new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setNoName().build());
      x++;
    }

    inv.setItem(11, new ItemBuilder(Material.SLIME_BALL)
        .setDisplayName(LanguageManager.getMessage(Variablelist.inv_gamemode_classic, p)).build());
    inv.setItem(15, new ItemBuilder(Material.MAGMA_CREAM)
        .setDisplayName(LanguageManager.getMessage(Variablelist.inv_gamemode_prop, p)).build());

    p.openInventory(inv);
  }
}
