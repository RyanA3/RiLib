package me.felnstaren.felib.item.custom.function;

import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import me.felnstaren.felib.item.custom.CustomMaterial;
import me.felnstaren.felib.item.custom.CustomMaterialData;

public class MaterialFunctionRegistry implements Listener {

	private static MaterialFunctionRegistry INSTANCE;
	
	public static MaterialFunctionRegistry inst() {
		return INSTANCE;
	}
	
	public static void init(JavaPlugin plugin) {
		INSTANCE = new MaterialFunctionRegistry(plugin);
	}
	
	
	
	public MaterialFunctionRegistry(JavaPlugin plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onCombat(EntityDamageByEntityEvent event) {
		if(!(event.getDamager() instanceof HumanEntity)) return;
		HumanEntity damager = (HumanEntity) event.getDamager();
		
		ItemStack tool = damager.getInventory().getItemInMainHand();
		if(tool == null) return;
		
		CustomMaterialData mat = CustomMaterial.inst().as(tool);
		if(mat == null) return;
		if(!(mat instanceof MaterialCombatFunction)) return;
		((MaterialCombatFunction) mat).execute(event);
	}
	
	@EventHandler
	public void onDamage(PlayerItemDamageEvent event) {
		ItemStack tool = event.getItem();
		if(tool == null) return;
		
		CustomMaterialData mat = CustomMaterial.inst().as(tool);
		if(mat == null) return;
		if(!(mat instanceof MaterialDamageFunction)) return;
		((MaterialDamageFunction) mat).execute(event);
	}
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent event) {
		ItemStack tool = event.getItemDrop().getItemStack();
		if(tool == null) return;
		
		CustomMaterialData mat = CustomMaterial.inst().as(tool);
		if(mat == null) return;
		if(!(mat instanceof MaterialDropFunction)) return;
		((MaterialDropFunction) mat).execute(event);
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		ItemStack tool = event.getItem();
		if(tool == null) return;
		
		CustomMaterialData mat = CustomMaterial.inst().as(tool);
		if(mat == null) return;
		if(!(mat instanceof MaterialInteractFunction)) return;
		((MaterialInteractFunction) mat).execute(event);
	}
	
}
