package de.niklashere.hidenseek.inventorys;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import de.niklashere.hidenseek.libary.Fileaccess;
import de.niklashere.hidenseek.libary.ItemBuilder;
import de.niklashere.hidenseek.libary.LanguageManager;
import de.niklashere.hidenseek.libary.VariableManager;
import de.niklashere.hidenseek.libary.WorldManager;

public class MapvotingInventory {

    public static void openInventory(Player p) {
        Inventory inv = Bukkit.createInventory(null, 9*3, VariableManager.message(LanguageManager.getMessage("inventory.mapvoting", p), p));
        int x = 0;
        while (x < inv.getSize()) {
            inv.setItem(x, new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setNoName().build());
            x++;
        }

        ArrayList<File> maps = WorldManager.rdmMap(Fileaccess.listOfFiles.get("maps"), 3);
        inv.setItem(11, new ItemBuilder(Material.GRASS).setDisplayName(VariableManager.message(LanguageManager.getMessage("map." + maps.get(0).getName().replace(".yml", ""), p))).build());
        inv.setItem(13, new ItemBuilder(Material.DIRT).setDisplayName(VariableManager.message(LanguageManager.getMessage("map." + maps.get(1).getName().replace(".yml", ""), p))).build());
        inv.setItem(15, new ItemBuilder(Material.STONE).setDisplayName(VariableManager.message(LanguageManager.getMessage("map." + maps.get(2).getName().replace(".yml", ""), p))).build());

        p.openInventory(inv);
    }
}