package de.niklashere.inventorys;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import de.niklashere.hidenseek.libary.Fileaccess;
import de.niklashere.hidenseek.libary.ItemBuilder;
import de.niklashere.hidenseek.libary.LanguageManager;
import de.niklashere.hidenseek.libary.VariableManager;

public class SetupInventory {

    public static void openInventory(Player p) {
        if (p.hasPermission("hidenseek.admin")) {
            Inventory inv = Bukkit.createInventory(null, 9*3, VariableManager.message(LanguageManager.getMessage("inventory.setup", p), p));
            int x = 0;
            while (x < inv.getSize()) {
                inv.setItem(x, new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setNoName().build());
                x++;
            }

            inv.setItem(0, new ItemBuilder(Material.ENDER_PEARL).setDisplayName(VariableManager.message(LanguageManager.getMessage("setup.spawnpoint-lobby", p), p)).build());
            inv.setItem(9, new ItemBuilder(Material.ENDER_PEARL).setDisplayName(VariableManager.message(LanguageManager.getMessage("setup.spawnpoint-seeker", p), p)).build());
            inv.setItem(18, new ItemBuilder(Material.ENDER_PEARL).setDisplayName(VariableManager.message(LanguageManager.getMessage("setup.spawnpoint-hider", p), p)).build());

            inv.setItem(10, new ItemBuilder(Material.OAK_SIGN).setDisplayName(VariableManager.message(LanguageManager.getMessage("setup.min-players", p), p) + Fileaccess.getInt("min-players", Fileaccess.getConfig())).addLoreLine(VariableManager.message(LanguageManager.getMessage("setup.instruction-1", p), p)).addLoreLine(VariableManager.message(LanguageManager.getMessage("setup.instruction-2", p), p)).build());
            inv.setItem(11, new ItemBuilder(Material.BARRIER).setDisplayName(VariableManager.message(LanguageManager.getMessage("setup.max-players", p), p) + Fileaccess.getInt("max-players", Fileaccess.getConfig())).addLoreLine(VariableManager.message(LanguageManager.getMessage("setup.instruction-1", p), p)).addLoreLine(VariableManager.message(LanguageManager.getMessage("setup.instruction-2", p), p)).build());
            inv.setItem(12, new ItemBuilder(Material.ENDER_EYE).setDisplayName(VariableManager.message(LanguageManager.getMessage("setup.max-seeker", p), p) + Fileaccess.getInt("max-seeker", Fileaccess.getConfig())).addLoreLine(VariableManager.message(LanguageManager.getMessage("setup.instruction-1", p), p)).addLoreLine(VariableManager.message(LanguageManager.getMessage("setup.instruction-2", p), p)).build());
            inv.setItem(13, new ItemBuilder(Material.CLOCK).setDisplayName(VariableManager.message(LanguageManager.getMessage("setup.ingametime", p), p) + Fileaccess.getInt("Ingame", Fileaccess.getConfig())).addLoreLine(VariableManager.message(LanguageManager.getMessage("setup.instruction-1", p), p)).addLoreLine(VariableManager.message(LanguageManager.getMessage("setup.instruction-2", p), p)).build());

            p.openInventory(inv);
        } else {
            p.sendMessage(VariableManager.message(LanguageManager.getMessage("missing-permission", p), p));
        }
    }
}
