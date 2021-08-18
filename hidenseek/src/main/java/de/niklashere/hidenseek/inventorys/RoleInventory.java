package de.niklashere.hidenseek.inventorys;

import de.niklashere.hidenseek.files.languages.Variablelist;
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
 * @since 02.08.2021
 */
public class RoleInventory {

  /**
   * Opens role inventory.
   *
   * @param p Players for whom the inventory should be opened
   */
  public static void openInventory(Player p) {
    Inventory inv = Bukkit.createInventory(null, 9 * 3,
        LanguageManager.getMessage(Variablelist.inv_role_name, p));
    int x = 0;
    while (x < inv.getSize()) {
      inv.setItem(x, new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setNoName().build());
      x++;
    }

    inv.setItem(11, new ItemBuilder(Material.RED_BANNER)
        .setDisplayName(LanguageManager.getMessage(Variablelist.inv_role_hider, p)).build());
    inv.setItem(15, new ItemBuilder(Material.GREEN_BANNER)
        .setDisplayName(LanguageManager.getMessage(Variablelist.inv_role_seeker, p)).build());

    p.openInventory(inv);
  }
}
