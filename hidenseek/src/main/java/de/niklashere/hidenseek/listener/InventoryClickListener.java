package de.niklashere.hidenseek.listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import de.niklashere.hidenseek.libary.Fileaccess;
import de.niklashere.hidenseek.libary.ItemBuilder;
import de.niklashere.hidenseek.libary.LanguageManager;
import de.niklashere.hidenseek.libary.VariableManager;
import de.niklashere.inventorys.SetupInventory;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        e.setCancelled(true);

        if (e.getCurrentItem() != null && e.getCurrentItem().hasItemMeta() && p.getOpenInventory().getTitle().equalsIgnoreCase(VariableManager.message(LanguageManager.getMessage("inventory-setup", p), p))) {
            if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(VariableManager.message(LanguageManager.getMessage("spawnpoint-lobby", p), p))) {
                Fileaccess.setLocation("spawnpoint-lobby", Fileaccess.getConfig(), p);

            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(VariableManager.message(LanguageManager.getMessage("spawnpoint-seeker", p), p))) {
                Fileaccess.setLocation("spawnpoint-seeker", Fileaccess.getConfig(), p);

            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(VariableManager.message(LanguageManager.getMessage("spawnpoint-hider", p), p))) {
                Fileaccess.setLocation("spawnpoint-hider", Fileaccess.getConfig(), p);

            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(VariableManager.message(LanguageManager.getMessage("min-players", p), p) + Fileaccess.getInt("min-players", Fileaccess.getConfig()))) {
                String string = "min-players";
                if (e.getClick().isRightClick()) {
                    Fileaccess.setInt(string, Fileaccess.getConfig(), Fileaccess.getInt(string, Fileaccess.getConfig())-1);
                    Fileaccess.clearHash();

                } else if (e.getClick().isLeftClick()) {
                    Fileaccess.setInt(string, Fileaccess.getConfig(), Fileaccess.getInt(string, Fileaccess.getConfig())+1);
                    Fileaccess.clearHash();

                } else if (e.getClick().isRightClick() && e.getClick().isShiftClick()) {
                    Fileaccess.setInt(string, Fileaccess.getConfig(), Fileaccess.getInt(string, Fileaccess.getConfig())-10);
                    Fileaccess.clearHash();

                } else if (e.getClick().isLeftClick() && e.getClick().isShiftClick()) {
                    Fileaccess.setInt(string, Fileaccess.getConfig(), Fileaccess.getInt(string, Fileaccess.getConfig())+10);
                    Fileaccess.clearHash();
                }
                SetupInventory.openInventory(p);

            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(VariableManager.message(LanguageManager.getMessage("max-players", p), p) + Fileaccess.getInt("max-players", Fileaccess.getConfig()))) {
                String string = "max-players";
                if (e.getClick().isRightClick()) {
                    Fileaccess.setInt(string, Fileaccess.getConfig(), Fileaccess.getInt(string, Fileaccess.getConfig())-1);
                    Fileaccess.clearHash();

                } else if (e.getClick().isLeftClick()) {
                    Fileaccess.setInt(string, Fileaccess.getConfig(), Fileaccess.getInt(string, Fileaccess.getConfig())+1);
                    Fileaccess.clearHash();

                } else if (e.getClick().isRightClick() && e.getClick().isShiftClick()) {
                    Fileaccess.setInt(string, Fileaccess.getConfig(), Fileaccess.getInt(string, Fileaccess.getConfig())-10);
                    Fileaccess.clearHash();

                } else if (e.getClick().isLeftClick() && e.getClick().isShiftClick()) {
                    Fileaccess.setInt(string, Fileaccess.getConfig(), Fileaccess.getInt(string, Fileaccess.getConfig())+10);
                    Fileaccess.clearHash();

                }
                SetupInventory.openInventory(p);

            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(VariableManager.message(LanguageManager.getMessage("max-seeker", p), p) + Fileaccess.getInt("max-seeker", Fileaccess.getConfig()))) {
                String string = "max-seeker";
                if (e.getClick().isRightClick()) {
                    Fileaccess.setInt(string, Fileaccess.getConfig(), Fileaccess.getInt(string, Fileaccess.getConfig())-1);
                    Fileaccess.clearHash();

                } else if (e.getClick().isLeftClick()) {
                    Fileaccess.setInt(string, Fileaccess.getConfig(), Fileaccess.getInt(string, Fileaccess.getConfig())+1);
                    Fileaccess.clearHash();

                } else if (e.getClick().isRightClick() && e.getClick().isShiftClick()) {
                    Fileaccess.setInt(string, Fileaccess.getConfig(), Fileaccess.getInt(string, Fileaccess.getConfig())-10);
                    Fileaccess.clearHash();

                } else if (e.getClick().isLeftClick() && e.getClick().isShiftClick()) {
                    Fileaccess.setInt(string, Fileaccess.getConfig(), Fileaccess.getInt(string, Fileaccess.getConfig())+10);
                    Fileaccess.clearHash();

                }
                SetupInventory.openInventory(p);

            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(VariableManager.message(LanguageManager.getMessage("ingametime", p), p) + Fileaccess.getInt("Ingame", Fileaccess.getConfig()))) {
                String string = "Ingame";
                if (e.getClick().isRightClick()) {
                    Fileaccess.setInt(string, Fileaccess.getConfig(), Fileaccess.getInt(string, Fileaccess.getConfig())-1);
                    Fileaccess.clearHash();

                } else if (e.getClick().isLeftClick()) {
                    Fileaccess.setInt(string, Fileaccess.getConfig(), Fileaccess.getInt(string, Fileaccess.getConfig())+1);
                    Fileaccess.clearHash();

                } else if (e.getClick().isRightClick() && e.getClick().isShiftClick()) {
                    Fileaccess.setInt(string, Fileaccess.getConfig(), Fileaccess.getInt(string, Fileaccess.getConfig())-10);
                    Fileaccess.clearHash();

                } else if (e.getClick().isLeftClick() && e.getClick().isShiftClick()) {
                    Fileaccess.setInt(string, Fileaccess.getConfig(), Fileaccess.getInt(string, Fileaccess.getConfig())+10);
                    Fileaccess.clearHash();

                }
                SetupInventory.openInventory(p);
                
            }
        }
    }
}
