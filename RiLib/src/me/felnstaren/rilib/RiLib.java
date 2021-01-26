package me.felnstaren.rilib;

import org.bukkit.plugin.java.JavaPlugin;

import me.felnstaren.rilib.command.impl.ParticleCommand;
import me.felnstaren.rilib.command.impl.TestCommand;
import me.felnstaren.rilib.item.custom.CustomMaterial;
import me.felnstaren.rilib.logger.Level;
import me.felnstaren.rilib.logger.Logger;
import me.felnstaren.rilib.packet.listener.PacketEvent;
import me.felnstaren.rilib.packet.listener.PacketInListener;
import me.felnstaren.rilib.packet.listener.PacketInjector;
import me.felnstaren.rilib.packet.listener.PacketListener;
import me.felnstaren.rilib.packet.listener.PacketOutListener;
import me.felnstaren.rilib.ui.menu.ButtonHandler;
import me.felnstaren.rilib.ui.menu.MenuSessionHandler;
import me.felnstaren.rilib.ui.prompt.PromptHandler;
import me.felnstaren.rilib.ui.prompt.listener.PromptCommand;

public class RiLib extends JavaPlugin implements PacketListener {

	public static Logger LOGGER;
	private PacketInjector injector;
	
	public void onEnable() {
		LOGGER = new Logger(this.getServer().getConsoleSender(), "RiLib");
		
		CustomMaterial.init(this);
		ButtonHandler.init();
		MenuSessionHandler.init(this);
		PromptHandler.init(this);
		
		injector = new PacketInjector(this);
		injector.getManager().registerInOut(this);
		
		
		this.getCommand("dtest").setExecutor(new TestCommand());
		this.getCommand("dparticle").setExecutor(new ParticleCommand());
		this.getCommand("prompt").setExecutor(new PromptCommand());
	}
	
	public void onDisable() {
		injector.shutdown();
	}

	@Override
	public void onEvent(PacketEvent event) {
		//LOGGER.log(Level.DEBUG, "Detected Packet I/O " + event.getPacket().getClass().getName());
		if(event.isPacket("PacketPlayOutEntityEffect")) LOGGER.log(Level.DEBUG, "Effect Out!");
	}
	
}
