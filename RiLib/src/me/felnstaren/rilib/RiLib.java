package me.felnstaren.rilib;

import org.bukkit.plugin.java.JavaPlugin;

import me.felnstaren.rilib.command.impl.TestCommand;
import me.felnstaren.rilib.item.custom.CustomMaterial;
import me.felnstaren.rilib.logger.Logger;
import me.felnstaren.rilib.ui.menu.ButtonHandler;
import me.felnstaren.rilib.ui.menu.MenuSessionHandler;

public class RiLib extends JavaPlugin {

	public static Logger LOGGER;
	
	public void onEnable() {
		LOGGER = new Logger(this.getServer().getConsoleSender(), "RiLib");
		
		CustomMaterial.init(this);
		ButtonHandler.init();
		MenuSessionHandler.init(this);
		
		this.getCommand("dtest").setExecutor(new TestCommand());
	}
	
	public void onDisable() {
		
	}
	
}
