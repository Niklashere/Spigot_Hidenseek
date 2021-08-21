package de.niklashere.hidenseek.inventorys;

import de.niklashere.hidenseek.files.languages.Variablelist;
import de.niklashere.hidenseek.libary.Fileaccess;
import de.niklashere.hidenseek.libary.ItemBuilder;
import de.niklashere.hidenseek.libary.LanguageManager;
import de.niklashere.hidenseek.libary.VariableManager;
import de.niklashere.hidenseek.libary.VoteManager;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * Mapvoting inventory.
 *
 * @author Niklashere
 * @since 02.08.2021
 */
public class MapvotingInventory {

  /**
   * Opens mapvoting inventory.
   *
   * @param p Players for whom the inventory should be opened
   */
  public static void openInventory(final Player p) {
    Inventory inv = Bukkit.createInventory(null, 9 * 3,
        LanguageManager.getMessage(Variablelist.inv_mapvoting_name, p));
    int x = 0;
    while (x < inv.getSize()) {
      inv.setItem(x, new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setNoName().build());
      x++;
    }

    String prefix = VariableManager
        .message(Fileaccess.getString("prefix.inv-item", Fileaccess.getConfig()));

    ArrayList<File> maps = VoteManager.rdmMap(Fileaccess.listOfFiles.get("maps"), 3);
    inv.setItem(11,
        new ItemBuilder(Material.getMaterial(Fileaccess.getString("material", maps.get(0))))
            .setDisplayName(prefix + Fileaccess.getString("world", maps.get(0))).build());
    inv.setItem(13,
        new ItemBuilder(Material.getMaterial(Fileaccess.getString("material", maps.get(1))))
            .setDisplayName(prefix + Fileaccess.getString("world", maps.get(1))).build());
    inv.setItem(15,
        new ItemBuilder(Material.getMaterial(Fileaccess.getString("material", maps.get(2))))
            .setDisplayName(prefix + Fileaccess.getString("world", maps.get(2))).build());

    p.openInventory(inv);
  }
}