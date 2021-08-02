package de.niklashere.inventorys;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import de.niklashere.hidenseek.libary.ItemBuilder;
import de.niklashere.hidenseek.libary.LanguageManager;
import de.niklashere.hidenseek.libary.VariableManager;

public class RoleInventory {

    public static void openInventory(Player p) {
        Inventory inv = Bukkit.createInventory(null, 9*3, VariableManager.message(LanguageManager.getMessage("inventory.role", p), p));
        int x = 0;
        while (x < inv.getSize()) {
            inv.setItem(x, new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setNoName().build());
            x++;
        }

        inv.setItem(11, new ItemBuilder(Material.GRASS).setDisplayName(VariableManager.message(LanguageManager.getMessage("role.hider", p), p)).build());
        inv.setItem(15, new ItemBuilder(Material.DIRT).setDisplayName(VariableManager.message(LanguageManager.getMessage("role.seeker", p), p)).build());

        p.openInventory(inv);
    }
}
