package de.niklashere.hidenseek.inventorys;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import de.niklashere.hidenseek.libary.ItemBuilder;
import de.niklashere.hidenseek.libary.LanguageManager;
import de.niklashere.hidenseek.libary.VariableManager;

public class HintInventory {

    public static void openInventory(Player p) {
        Inventory inv = Bukkit.createInventory(null, 9*3, VariableManager.message(LanguageManager.getMessage("inventory.hint", p), p));
        int x = 0;
        while (x < inv.getSize()) {
            inv.setItem(x, new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setNoName().build());
            x++;
        }

        inv.setItem(11, new ItemBuilder(Material.FIREWORK_ROCKET).setDisplayName(VariableManager.message(LanguageManager.getMessage("hint.Firework", p), p)).build());
        inv.setItem(13, new ItemBuilder(Material.CAT_SPAWN_EGG).setDisplayName(VariableManager.message(LanguageManager.getMessage("hint.Meow", p), p)).build());
        inv.setItem(15, new ItemBuilder(Material.TNT).setDisplayName(VariableManager.message(LanguageManager.getMessage("hint.Explosion", p), p)).build());

        p.openInventory(inv);

    }
}
