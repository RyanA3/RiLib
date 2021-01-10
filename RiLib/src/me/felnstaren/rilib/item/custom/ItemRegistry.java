package me.felnstaren.rilib.item.custom;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import me.felnstaren.rilib.item.recipe.RecipeMaker;
import other.bananapuncher714.nbt.NBTEditor;

/*
 * Use Singleton to retain consistency across all plugins
 * using the ItemRegistry to register custom items
 */

public class ItemRegistry {

	protected static final String CUSTOM_KEY = "ritem";
	
	private static ItemRegistry INSTANCE;
	
	public static ItemRegistry inst() {
		return INSTANCE;
	}
	
	public static void init(JavaPlugin plugin) {
		INSTANCE = new ItemRegistry(plugin);
	}
	
	
	
	private ArrayList<CustomItem> custom_items;
	private ArrayList<String> index_labels;
	private RecipeMaker recipe_maker;
	
	private ItemRegistry(JavaPlugin plugin) {
		custom_items = new ArrayList<CustomItem>();
		index_labels = new ArrayList<String>();
		recipe_maker = new RecipeMaker(plugin);
	}
	
	public CustomItem item(String item_label) {
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
	public boolean register(CustomItem new_entry) {
		if(exists(new_entry.getLabel())) return false;
		custom_items.add(new_entry);
		index_labels.add(new_entry.getLabel());
		Bukkit.addRecipe(recipe_maker.construct(new_entry.getRecipe(), new_entry.asStack()));
		return true;
	}
	
	
	public boolean isCustom(ItemStack stack) {
		return NBTEditor.contains(stack, CUSTOM_KEY);
	}
	
	public CustomItem getCustom(ItemStack stack) {
		if(!isCustom(stack)) return null;
		String label = NBTEditor.getString(stack, CUSTOM_KEY);
		return item(label);
	}
	
}
