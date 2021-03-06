package me.felnstaren.felib.item.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Damageable;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.felnstaren.felib.chat.Messenger;

public class ItemEditor {
	
	private static Random rand = new Random();

	public static ItemStack setName(ItemStack item, String str) {
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(str.replace('&', '\u00a7'));
		item.setItemMeta(meta);
		return item;
	}
	
	public static String getName(ItemStack item) {
		ItemMeta meta = item.getItemMeta();
		return meta.getDisplayName();
	}	
	
	
	
	public static ItemStack setUnbreakable(ItemStack item, boolean unb) {
		ItemMeta meta = item.getItemMeta();
		meta.setUnbreakable(unb);
		item.setItemMeta(meta);
		return item;
	}
	
	
	
	public static ItemStack damage(ItemStack item, int damage) {
		ItemMeta meta = item.getItemMeta();
		Damageable dam = (Damageable) meta;
		
		int unbreaking_level = meta.getEnchantLevel(Enchantment.DURABILITY);
		damage = Math.max(0, damage - (unbreaking_level - rand.nextInt(unbreaking_level + 1)));
		
		dam.damage(damage);
		item.setItemMeta((ItemMeta) dam);
		
		if (dam.getHealth() < 0) 
			item.setType(Material.AIR);
		
		return item;
	}
	
	
	
	public static ItemStack setModelData(ItemStack item, int data) {
		ItemMeta meta = item.getItemMeta();
		meta.setCustomModelData(data);
		item.setItemMeta(meta);
		
		return item;
	}
	
	public static ItemStack setLore(ItemStack item, String value, int line) {
		value = Messenger.color(value);
		ItemMeta meta = item.getItemMeta();
		List<String> lore = meta.getLore();
		if(lore == null) lore = new ArrayList<String>();
		if(lore.size() >= line) lore.add(line, value);
		else lore.set(line, value);
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}
	
	public static ItemStack setLore(ItemStack item, String... values) {
		ItemMeta meta = item.getItemMeta();
		ArrayList<String> lore = new ArrayList<String>();
		for(int i = 0; i < values.length; i++) lore.add(Messenger.color(values[i]));
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}
	
}
