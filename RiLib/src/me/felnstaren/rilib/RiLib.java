package me.felnstaren.rilib;

import org.bukkit.plugin.java.JavaPlugin;

import me.felnstaren.rilib.item.custom.CustomMaterial;

public class RiLib extends JavaPlugin {

	public void onEnable() {
		CustomMaterial.init(this);
	}
	
	public void onDisable() {
		
	}
	
}
