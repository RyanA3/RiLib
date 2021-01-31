package me.felnstaren.felib.item.recipe;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

public class RecipeMaker {

	private JavaPlugin plugin;
	
	public RecipeMaker(JavaPlugin plugin) {
		this.plugin = plugin;
	}
	
	public Recipe construct(CustomRecipe shape, ItemStack result) {
		NamespacedKey key = new NamespacedKey(plugin, shape.getLabel());
		ShapedRecipe recipe = new ShapedRecipe(key, result);
		
		recipe.shape(shape.getShape());
		for(Character charac : shape.getMaterialMap().keySet())
			recipe.setIngredient(charac, shape.getMaterialMap().get(charac));
		
		return recipe;
	}
	
}
