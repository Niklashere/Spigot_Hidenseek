package de.niklashere.inventorys;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import de.niklashere.hidenseek.libary.ItemBuilder;
import de.niklashere.hidenseek.libary.LanguageManager;
import de.niklashere.hidenseek.libary.VariableManager;

public class InventoryManager {
    
	public static void clearInv(Player p) {
		p.getInventory().clear();
		p.getInventory().setArmorContents(null);
	}

	public static void lobbyItems(Player p) {
        p.getInventory().setItem(4, new ItemBuilder(Material.CHEST).setDisplayName(VariableManager.message(LanguageManager.getMessage("item.chest", p), p)).build());

	}

	public static void seekerItems(Player p) {
        p.getInventory().setItem(0, new ItemBuilder(Material.IRON_SWORD).setDisplayName(VariableManager.message(LanguageManager.getMessage("item.sword", p), p)).build());
        p.getInventory().setItem(1, new ItemBuilder(Material.BOW).setUnbreakable(true).setDisplayName(VariableManager.message(LanguageManager.getMessage("item.bow", p), p)).build());
        p.getInventory().setItem(9, new ItemBuilder(Material.ARROW).setAmount(2).build());

    }

    public static void hiderItems(Player p) {
        p.getInventory().setItem(0, new ItemBuilder(Material.BLAZE_ROD).setUnbreakable(true).setDisplayName(VariableManager.message(LanguageManager.getMessage("item.stun", p), p)).build());
        p.getInventory().setItem(1, new ItemBuilder(Material.BLAZE_POWDER).setDisplayName(VariableManager.message(LanguageManager.getMessage("item.hint", p), p)).build());
    }

    public static void spectatorItems(Player p) {
        p.getInventory().setItem(0, new ItemBuilder(Material.CHEST).setDisplayName(VariableManager.message(LanguageManager.getMessage("item.spectator", p), p)).build());

    }
}
