package de.niklashere.hidenseek.inventorys;

import de.niklashere.hidenseek.files.languages.Variablelist;
import de.niklashere.hidenseek.libary.ItemBuilder;
import de.niklashere.hidenseek.libary.LanguageManager;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * Hint Inventory.
 *
 * @author Niklashere
 * @since 01.08.2021
 */
public class HintInventory {

  /**
   * Opens hint inventory.
   *
   * @param p Players for whom the inventory should be opened
   */
  public static void openInventory(final Player p) {
    Inventory inv = Bukkit.createInventory(null, 9 * 3,
        LanguageManager.getMessage(Variablelist.inv_hint_name, p));
    int x = 0;
    while (x < inv.getSize()) {
      inv.setItem(x, new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setNoName().build());
      x++;
    }

    inv.setItem(11, new ItemBuilder(Material.FIREWORK_ROCKET)
        .setDisplayName(LanguageManager.getMessage(Variablelist.inv_hint_Firework, p)).build());
    inv.setItem(13, new ItemBuilder(Material.CAT_SPAWN_EGG)
        .setDisplayName(LanguageManager.getMessage(Variablelist.inv_hint_Meow, p)).build());
    inv.setItem(15, new ItemBuilder(Material.TNT)
        .setDisplayName(LanguageManager.getMessage(Variablelist.inv_hint_Explosion, p)).build());

    p.openInventory(inv);

  }
}
