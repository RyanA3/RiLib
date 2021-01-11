package me.felnstaren.rilib;

import org.bukkit.plugin.java.JavaPlugin;

import me.felnstaren.rilib.item.custom.CustomMaterial;
import me.felnstaren.rilib.menu.ButtonHandler;
import me.felnstaren.rilib.menu.MenuSessionHandler;

public class RiLib extends JavaPlugin {

	public void onEnable() {
		CustomMaterial.init(this);
		ButtonHandler.init();
		MenuSessionHandler.init(this);
	}
	
	public void onDisable() {
		
	}
	
}
