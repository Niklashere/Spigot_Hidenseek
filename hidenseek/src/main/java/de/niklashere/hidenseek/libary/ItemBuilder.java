package de.niklashere.hidenseek.libary;

import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class ItemBuilder {
    private ItemStack item;
    private List<String> lore = new ArrayList<String>();
    private ItemMeta meta;

    public ItemBuilder(Material mat, short subid, int amount) {
        this.item = new ItemStack(mat, amount, subid);
        this.meta = this.item.getItemMeta();
    }

    public ItemBuilder(ItemStack item) {
        this.item = item;
        this.meta = item.getItemMeta();
    }

    public ItemBuilder(Material mat, short subid) {
        this.item = new ItemStack(mat, 1, subid);
        this.meta = this.item.getItemMeta();
    }

    public ItemBuilder(Material mat, int amount) {
        this.item = new ItemStack(mat, amount, (short) 0);
        this.meta = this.item.getItemMeta();
    }

    public ItemBuilder(Material mat) {
        this.item = new ItemStack(mat, 1, (short) 0);
        this.meta = this.item.getItemMeta();
    }

    public ItemBuilder setAmount(int value) {
        this.item.setAmount(value);
        return this;
    }

    public ItemBuilder addPotionEffect(PotionEffectType type, int duration, int power) {
        ((PotionMeta) this.meta).addCustomEffect(new PotionEffect(type, duration, power), true);
        return this;
    }

    public ItemBuilder setNoName() {
        this.meta.setDisplayName(" ");
        return this;
    }

    public ItemBuilder setGlow() {
        this.meta.addEnchant(Enchantment.DURABILITY, 1, true);
        this.meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        return this;
    }

    public ItemBuilder setGlow(boolean value) {
        if (value) {
            this.meta.addEnchant(Enchantment.DURABILITY, 1, true);
            this.meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        return this;
    }

    public ItemBuilder setData(short data) {
        this.item.setDurability(data);
        return this;
    }

    public ItemBuilder addLoreLine(String line) {
        this.lore.add(line);
        return this;
    }

    public ItemBuilder addBookPage(String line) {
        ((BookMeta) this.meta).addPage(line);
        return this;
    }


    public /* varargs */ ItemBuilder addLoreArray(String... lines) {
        int x = 0;
        while (x < lines.length) {
            this.lore.add(lines[x]);
            ++x;
        }
        return this;
    }

    public ItemBuilder addLoreAll(List<String> lines) {
        this.lore.addAll(lines);
        return this;
    }

    public ItemBuilder setDisplayName(String name) {
        this.meta.setDisplayName(name);
        return this;
    }

    public ItemBuilder setSkullOwner(String owner) {
        ((SkullMeta) this.meta).setOwner(owner);
        return this;
    }

    public ItemBuilder resetLore() {
        this.lore.clear();
        return this;
    }

    public ItemBuilder setUnbreakable(boolean value) {
        this.meta.setUnbreakable(value);
        return this;
    }

    public ItemBuilder addEnchantment(Enchantment ench, int lvl) {
        this.meta.addEnchant(ench, lvl, true);
        return this;
    }

    public ItemBuilder addItemFlag(ItemFlag flag) {
        this.meta.addItemFlags(flag);
        return this;
    }

    public ItemBuilder addLeatherColor(Color color) {
        ((LeatherArmorMeta) this.meta).setColor(color);
        return this;
    }

    public ItemBuilder setBaseColor(DyeColor color) {
        ((BannerMeta) this.meta).setBaseColor(color);
        return this;
    }

    public ItemBuilder addPattern(DyeColor color, PatternType pattern) {
        ((BannerMeta) this.meta).addPattern(new Pattern(color, pattern));
        return this;
    }

    public ItemStack build() {
        if (!this.lore.isEmpty()) {
            this.meta.setLore(this.lore);
        }
        this.item.setItemMeta(this.meta);
        return this.item;
    }
}
