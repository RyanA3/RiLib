package me.felnstaren.rilib.item.recipe;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class CustomRecipe {
	
	private String label;
	private String[] shape;
	private HashMap<Character, Material> matmap;
	private ItemStack result;
	
	public CustomRecipe(String label, ItemStack result, String... lines) {
		this.label = label;
		this.shape = lines;
		this.result = result;
	}
	
	
	
	public CustomRecipe material(Character key, Material material) {
		matmap.put(key, material);
		return this;
	}
	
	
	
	public String getLabel() {
		return label;
	}
	
	public String[] getShape() {
		return shape;
	}
	
	public ItemStack getResult() {
		return result;
	}
	
	public HashMap<Character, Material> getMaterialMap() {
		return matmap;
	}

}
