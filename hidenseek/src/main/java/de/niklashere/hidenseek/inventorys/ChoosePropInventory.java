package de.niklashere.hidenseek.inventorys;

import de.niklashere.hidenseek.files.languages.Variablelist;
import de.niklashere.hidenseek.libary.Fileaccess;
import de.niklashere.hidenseek.libary.ItemBuilder;
import de.niklashere.hidenseek.libary.LanguageManager;
import de.niklashere.hidenseek.libary.VariableManager;
import de.niklashere.hidenseek.libary.VoteManager;

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

    for (int i = 0; 4 >= i; i++) {
      inv.setItem(11 + i, new ItemBuilder(
          Material.getMaterial(Fileaccess.getStringList("props", VoteManager.getResults()).get(i)))
              .setDisplayName(prefix + Material
                  .getMaterial(Fileaccess.getStringList("props", VoteManager.getResults()).get(i))
                  .name().replace("_", " "))
              .build());

    }
    p.openInventory(inv);
  }
}
