package me.felnstaren.rilib.item.custom;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import me.felnstaren.rilib.item.recipe.CustomRecipe;
import me.felnstaren.rilib.util.ItemEditor;
import other.bananapuncher714.nbt.NBTEditor;

public class CustomItem {

	private String label; //label = internal name
	private String name;
	private Material material;
	private int texture_data;
	private CustomRecipe recipe;
	
	public CustomItem(String label, String name, int texture_data, Material material) {
		construct(label, name, texture_data, material);
	}
	
	public CustomItem(String label, String name, int texture_data, Material material, CustomRecipe recipe) {
		construct(label, name, texture_data, material);
		setRecipe(recipe);
	}
	
	private void construct(String label, String name, int texture_data, Material material) {
		this.label = label;
		this.name = name;
		this.material = material;
		this.texture_data = texture_data;
	}
	
	public void setRecipe(CustomRecipe recipe) {
		this.recipe = recipe;
	}
	
	
	
	public ItemStack asStack() {
		ItemStack item = new ItemStack(material);
		item = NBTEditor.set(item, label, ItemRegistry.CUSTOM_KEY);
		item = ItemEditor.setName(item, name);
		item = ItemEditor.setCustomModelData(item, texture_data);
		return item;
	}
	
	
	
	public String getLabel() {
		return label;
	}
	
	public String getName() {
		return name;
	}
	
	public int getTextureData() {
		return texture_data;
	}
	
	public Material getTrueMaterial() {
		return material;
	}
	
	public CustomRecipe getRecipe() {
		return recipe;
	}
	
}
