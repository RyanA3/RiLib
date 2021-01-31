package me.felnstaren.felib.item.util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import other.bananapuncher714.nbt.NBTEditor;

public class ItemBuild {
	
	private ItemStack item;
	
	public ItemBuild(ItemStack item) {
		this.item = item;
	}
	
	public ItemBuild(Material material) {
		this.item = new ItemStack(material);
	}
	
	public ItemBuild(Material material, int count) {
		this.item = new ItemStack(material, count);
	}
	
	
	
	public ItemStack construct() {
		return item.clone();
	}
	
	
	
	public ItemBuild setName(String name) {
		item = ItemEditor.setName(item, name);
		return this;
	}
	
	public ItemBuild setFlag(String key, Object value) {
		item = NBTEditor.set(item, value, key);
		return this;
	}
	
	public ItemBuild setButton(String label) {
		item = NBTEditor.set(item, label, "button");
		return this;
	}
	
	public ItemBuild setUnbreakable(boolean value) {
		item = ItemEditor.setUnbreakable(item, value);
		return this;
	}
	
	public ItemBuild setModelData(int value) {
		item = ItemEditor.setModelData(item, value);
		return this;
	}
	
	public ItemBuild damage(int value) {
		item = ItemEditor.damage(item, value);
		return this;
	}

}
