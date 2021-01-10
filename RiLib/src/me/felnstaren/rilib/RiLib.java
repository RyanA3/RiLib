package me.felnstaren.rilib;

import org.bukkit.plugin.java.JavaPlugin;

import me.felnstaren.rilib.item.custom.ItemRegistry;

public class RiLib extends JavaPlugin {

	public void onEnable() {
		ItemRegistry.init(this);
	}
	
	public void onDisable() {
		
	}
	
}
