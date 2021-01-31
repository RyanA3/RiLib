package me.felnstaren.felib.item.custom;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import me.felnstaren.felib.item.recipe.RecipeMaker;
import other.bananapuncher714.nbt.NBTEditor;

/*
 * Use Singleton to retain consistency across all plugins
 * using the CustomMaterial to register custom items
 */

public class CustomMaterial {

	protected static final String CUSTOM_KEY = "ritem";
	
	private static CustomMaterial INSTANCE;
	
	public static void init(JavaPlugin plugin) {
		INSTANCE = new CustomMaterial(plugin);
	}
	
	public static CustomMaterial inst() {
		return INSTANCE;
	}
	
	
	
	private ArrayList<CustomMaterialData> custom_items;
	private ArrayList<String> index_labels;
	private RecipeMaker recipe_maker;
	
	private CustomMaterial(JavaPlugin plugin) {
		custom_items = new ArrayList<CustomMaterialData>();
		index_labels = new ArrayList<String>();
		recipe_maker = new RecipeMaker(plugin);
	}
	
	public CustomMaterialData item(String item_label) {
		int index = index_labels.indexOf(item_label);
		if(index == -1) return null;
		return custom_items.get(index);
	}
	
	public boolean exists(String item_label) {
		return index_labels.indexOf(item_label) != -1;
	}
	
	
	
	/**
	 * Registers a CustomItem alongside its internal label
	 * @param new_entry CustomItem object to be registered
	 * @return True if successful
	 */
	public boolean register(CustomMaterialData new_entry) {
		if(exists(new_entry.getLabel())) return false;
		custom_items.add(new_entry);
		index_labels.add(new_entry.getLabel());
		Bukkit.addRecipe(recipe_maker.construct(new_entry.getRecipe(), new_entry.stack()));
		return true;
	}
	
	/**
	 * Checks if a given ItemStack is a CustomMaterial
	 * @param stack to check
	 * @return True if custom
	 */
	public boolean is(ItemStack stack) {
		return NBTEditor.contains(stack, CUSTOM_KEY);
	}
	
	/**
	 * Gets the CustomMaterialData of a custom ItemStack
	 * @param stack to check
	 * @return Null if not custom
	 */
	public CustomMaterialData as(ItemStack stack) {
		if(!is(stack)) return null;
		String label = NBTEditor.getString(stack, CUSTOM_KEY);
		return item(label);
	}
	
}
