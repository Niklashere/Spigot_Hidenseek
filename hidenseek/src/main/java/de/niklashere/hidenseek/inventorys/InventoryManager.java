package de.niklashere.hidenseek.inventorys;

import de.niklashere.hidenseek.files.languages.Variablelist;
import de.niklashere.hidenseek.gamestates.Gamestate;
import de.niklashere.hidenseek.libary.ItemBuilder;
import de.niklashere.hidenseek.libary.LanguageManager;

import org.bukkit.Material;
import org.bukkit.entity.Player;

/**
 * InventoryManager wich manages player Roles.
 *
 * @author Niklashere
 * @since 02.08.2021
 */
public class InventoryManager {

  /**
   * This prevents the default parameter-less constructor from being used
   * elsewhere.
   */
  private InventoryManager() {
  }

  /**
   * Clears player p inventory.
   *
   * @param p Player who should have their inventory cleared.
   */
  public static void clearInv(final Player p) {
    p.getInventory().clear();
    p.getInventory().setArmorContents(null);
  }

  /**
   * Give the lobby items to player p.
   *
   * @param p Player to receive lobby items.
   */
  public static void lobbyItems(final Player p) {
    if (Gamestate.isState(Gamestate.Lobby)) {
      p.getInventory().setItem(1, new ItemBuilder(Material.CHEST)
          .setDisplayName(LanguageManager.getMessage(Variablelist.items_lobby_chest, p)).build());
      p.getInventory().setItem(2,
          new ItemBuilder(Material.RED_BANNER)
              .setDisplayName(LanguageManager.getMessage(Variablelist.items_lobby_redBanner, p))
              .build());
      p.getInventory().setItem(3,
          new ItemBuilder(Material.SLIME_BALL)
              .setDisplayName(LanguageManager.getMessage(Variablelist.items_lobby_slimeball, p))
              .build());

    }

  }

  /**
   * Give the seeker items to player p.
   *
   * @param p Player to receive seeker items.
   */
  public static void seekerItems(final Player p) {
    p.getInventory().setItem(0, new ItemBuilder(Material.IRON_SWORD)
        .setDisplayName(LanguageManager.getMessage(Variablelist.items_seeker_sword, p)).build());
    p.getInventory().setItem(1, new ItemBuilder(Material.BOW).setUnbreakable(true)
        .setDisplayName(LanguageManager.getMessage(Variablelist.items_seeker_bow, p)).build());
    p.getInventory().setItem(9, new ItemBuilder(Material.ARROW).setAmount(2).build());

  }

  /**
   * Give the hider items to player p.
   *
   * @param p Player to receive hider items.
   */
  public static void hiderItems(final Player p) {
    p.getInventory().setItem(0, new ItemBuilder(Material.BLAZE_ROD).setUnbreakable(true)
        .setDisplayName(LanguageManager.getMessage(Variablelist.items_hider_stun, p)).build());
    p.getInventory().setItem(1, new ItemBuilder(Material.BLAZE_POWDER)
        .setDisplayName(LanguageManager.getMessage(Variablelist.items_hider_hint, p)).build());
  }

  /**
   * Give the spectator items to player p.
   *
   * @param p Player to receive spectator items.
   */
  public static void spectatorItems(final Player p) {

  }
}
