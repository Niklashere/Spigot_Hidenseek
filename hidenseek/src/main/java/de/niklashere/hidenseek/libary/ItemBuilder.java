package de.niklashere.hidenseek.libary;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Constructor to create Items.
 *
 * @author Niklashere
 * @since 01.08.2021
 */
public class ItemBuilder {
  private ItemStack item;
  private List<String> lore = new ArrayList<String>();
  private ItemMeta meta;

  /**
   * Create a new item.
   * 
   * @param item itemstack.
   */
  public ItemBuilder(ItemStack item) {
    this.item = item;
    this.meta = item.getItemMeta();
  }

  /**
   * Create a new item.
   * 
   * @param mat    material of the item
   * @param amount stacksize
   */
  public ItemBuilder(Material mat, int amount) {
    this.item = new ItemStack(mat, amount);
    this.meta = this.item.getItemMeta();
  }

  /**
   * Create a new item.
   * 
   * @param mat material of the item
   */
  public ItemBuilder(Material mat) {
    this.item = new ItemStack(mat, 1);
    this.meta = this.item.getItemMeta();
  }

  /**
   * Define the size of the batch of articles.
   * 
   * @param value size of the itemstack
   * @return itembuilder
   */
  public ItemBuilder setAmount(int value) {
    this.item.setAmount(value);
    return this;
  }

  /**
   * Add a potioneffect to the item.
   * 
   * @param type     Type of potioneffect
   * @param duration duration of the effect
   * @param power    power of the effect
   * @return itembuilder
   */
  public ItemBuilder addPotionEffect(PotionEffectType type, int duration, int power) {
    ((PotionMeta) this.meta).addCustomEffect(new PotionEffect(type, duration, power), true);
    return this;
  }

  /**
   * Set no name for the item.
   * 
   * @return itembuilder
   */
  public ItemBuilder setNoName() {
    this.meta.setDisplayName(" ");
    return this;
  }

  /**
   * Set a glow for the item.
   * 
   * @return itembuilder
   */
  public ItemBuilder setGlow() {
    this.meta.addEnchant(Enchantment.DURABILITY, 1, true);
    this.meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
    return this;
  }

  /**
   * Add a loreline for the item.
   * 
   * @param line String for the loreline
   * @return itembuilder
   */
  public ItemBuilder addLoreLine(String line) {
    this.lore.add(line);
    return this;
  }

  /**
   * Add a bookpage.
   * 
   * @param line Content of the page
   * @return itembuilder
   */
  public ItemBuilder addBookPage(String line) {
    ((BookMeta) this.meta).addPage(line);
    return this;
  }

  /**
   * Add a list of lines to the item.
   * 
   * @param lines list of lines
   * @return itembuilder
   */
  public ItemBuilder addLoreArray(String... lines) {
    int x = 0;
    while (x < lines.length) {
      this.lore.add(lines[x]);
      ++x;
    }
    return this;
  }

  /**
   * Add a list of lines to the item.
   * 
   * @param lines list of lines
   * @return itembuilder
   */
  public ItemBuilder addLoreAll(List<String> lines) {
    this.lore.addAll(lines);
    return this;
  }

  /**
   * Set a displayname for the item.
   * 
   * @param name displayname
   * @return itembuilder
   */
  public ItemBuilder setDisplayName(String name) {
    this.meta.setDisplayName(name);
    return this;
  }

  /**
   * Reset the lore for the item.
   * 
   * @return itembuilder
   */
  public ItemBuilder resetLore() {
    this.lore.clear();
    return this;
  }

  /**
   * Make an item unbreakable.
   * 
   * @param value unbreakable
   * @return itembuilder
   */
  public ItemBuilder setUnbreakable(boolean value) {
    this.meta.setUnbreakable(value);
    return this;
  }

  /**
   * Add an Enchantment for the item.
   * 
   * @param ench Enchantment
   * @param lvl  Level of the Enchantment
   * @return itembuilder
   */
  public ItemBuilder addEnchantment(Enchantment ench, int lvl) {
    this.meta.addEnchant(ench, lvl, true);
    return this;
  }

  /**
   * Add item flags to the item.
   * 
   * @param flag itemflag
   * @return itembuilder
   */
  public ItemBuilder addItemFlag(ItemFlag flag) {
    this.meta.addItemFlags(flag);
    return this;
  }

  /**
   * Add a color the the leatherarmor.
   * 
   * @param color leather color
   * @return itembuilder
   */
  public ItemBuilder addLeatherColor(Color color) {
    ((LeatherArmorMeta) this.meta).setColor(color);
    return this;
  }

  /**
   * Add a banner pattern.
   * 
   * @param color   color of the pattern
   * @param pattern patterntype
   * @return itembuilder
   */
  public ItemBuilder addPattern(DyeColor color, PatternType pattern) {
    ((BannerMeta) this.meta).addPattern(new Pattern(color, pattern));
    return this;
  }

  /**
   * Build your item.
   * 
   * @return itemstack.
   */
  public ItemStack build() {
    if (!this.lore.isEmpty()) {
      this.meta.setLore(this.lore);
    }
    this.item.setItemMeta(this.meta);
    return this.item;
  }
}
