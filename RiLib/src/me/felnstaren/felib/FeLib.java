package me.felnstaren.felib;

import org.bukkit.plugin.java.JavaPlugin;

import me.felnstaren.felib.command.impl.ParticleCommand;
import me.felnstaren.felib.command.impl.TestCommand;
import me.felnstaren.felib.item.custom.CustomMaterial;
import me.felnstaren.felib.logger.Logger;
import me.felnstaren.felib.packet.listener.PacketInjector;
import me.felnstaren.felib.ui.menu.ButtonHandler;
import me.felnstaren.felib.ui.menu.MenuSessionHandler;
import me.felnstaren.felib.ui.prompt.PromptHandler;
import me.felnstaren.felib.ui.prompt.listener.PromptCommand;

public class FeLib extends JavaPlugin {

	public static Logger LOGGER;
	public static PacketInjector INJECTOR;
	
	public void onEnable() {
		LOGGER = new Logger(this.getServer().getConsoleSender(), "RiLib");
		
		CustomMaterial.init(this);
		ButtonHandler.init();
		MenuSessionHandler.init(this);
		PromptHandler.init(this);
		
		INJECTOR = new PacketInjector(this);
		
		this.getCommand("dtest").setExecutor(new TestCommand());
		this.getCommand("dparticle").setExecutor(new ParticleCommand());
		this.getCommand("prompt").setExecutor(new PromptCommand());
	}
	
	public void onDisable() {
		INJECTOR.getManager().clear();
		INJECTOR.shutdown();
		MenuSessionHandler.inst().closeAllSessions();
	}
	
}
