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
 * Mapvoting inventory.
 *
 * @author Niklashere
 * @since 17.08.2021
 */
public class ChoosePropInventory {

  /**
   * Opens mapvoting inventory.
   *
   * @param p Players for whom the inventory should be opened
   */
  public static void openInventory(final Player p) {
    Inventory inv = Bukkit.createInventory(null, 9 * 3,
        LanguageManager.getMessage(Variablelist.inv_chooseprop_name, p));
    int x = 0;
    while (x < inv.getSize()) {
      inv.setItem(x, new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setNoName().build());
      x++;
    }

    String prefix = VariableManager
        .message(Fileaccess.getString("prefix.inv-item", Fileaccess.getConfig()));

    inv.setItem(11, new ItemBuilder(Material.CRAFTING_TABLE)
        .setDisplayName(prefix + Material.CRAFTING_TABLE.name().replace("_", " ")).build());
    inv.setItem(12, new ItemBuilder(Material.HAY_BLOCK)
        .setDisplayName(prefix + Material.HAY_BLOCK.name().replace("_", " ")).build());
    inv.setItem(13, new ItemBuilder(Material.ANVIL)
        .setDisplayName(prefix + Material.ANVIL.name().replace("_", " ")).build());
    inv.setItem(14, new ItemBuilder(Material.NOTE_BLOCK)
        .setDisplayName(prefix + Material.NOTE_BLOCK.name().replace("_", " ")).build());

    inv.setItem(15, new ItemBuilder(Material.BOOKSHELF)
        .setDisplayName(prefix + Material.BOOKSHELF.name().replace("_", " ")).build());

    p.openInventory(inv);
  }
}
