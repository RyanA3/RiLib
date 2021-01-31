package me.felnstaren.felib;

import org.bukkit.plugin.java.JavaPlugin;

import me.felnstaren.felib.command.impl.ParticleCommand;
import me.felnstaren.felib.command.impl.TestCommand;
import me.felnstaren.felib.item.custom.CustomMaterial;
import me.felnstaren.felib.logger.Level;
import me.felnstaren.felib.logger.Logger;
import me.felnstaren.felib.packet.listener.PacketEvent;
import me.felnstaren.felib.packet.listener.PacketInjector;
import me.felnstaren.felib.packet.listener.PacketListener;
import me.felnstaren.felib.packet.wrapper.MetadataPacketWrapper;
import me.felnstaren.felib.reflect.Reflector;
import me.felnstaren.felib.ui.menu.ButtonHandler;
import me.felnstaren.felib.ui.menu.MenuSessionHandler;
import me.felnstaren.felib.ui.prompt.PromptHandler;
import me.felnstaren.felib.ui.prompt.listener.PromptCommand;

public class FeLib extends JavaPlugin implements PacketListener {

	public static Logger LOGGER;
	public static PacketInjector INJECTOR;
	
	public void onEnable() {
		LOGGER = new Logger(this.getServer().getConsoleSender(), "RiLib");
		
		CustomMaterial.init(this);
		ButtonHandler.init();
		MenuSessionHandler.init(this);
		PromptHandler.init(this);
		
		INJECTOR = new PacketInjector(this);
		INJECTOR.getManager().registerOut(this);
		
		
		this.getCommand("dtest").setExecutor(new TestCommand());
		this.getCommand("dparticle").setExecutor(new ParticleCommand());
		this.getCommand("prompt").setExecutor(new PromptCommand());
	}
	
	public void onDisable() {
		INJECTOR.getManager().clear();
		INJECTOR.shutdown();
	}

	@Override
	public void onEvent(PacketEvent event) {
		if(!event.isPacket("PacketPlayOutEntityMetadata")) return;
		LOGGER.log(Level.DEBUG, "Metadata packet got - " + event.getPacket().toString());
		LOGGER.log(Level.DEBUG, "EntityId - " + (int) Reflector.getDeclaredField(event.getPacket(), "a"));
		
		MetadataPacketWrapper packet = new MetadataPacketWrapper(event.getPacket());
		packet.addByteProperty((byte) 0x20);
		packet.addByteProperty((byte) 0x40);
		packet.addByteProperty((byte) 0x01);
		
		/*List<?> data = (List<?>) Reflector.getDeclaredField(event.getPacket(), "b");
		Object item = data.get(0);
		Object item_object = Reflector.getDeclaredField(item, "b");
		
		if(!(item_object instanceof Byte)) return;
		
		byte item_object_byte = (byte) item_object;
		byte glow = (byte) (item_object_byte | 1 << 6);
		
		Reflector.setDeclaredField(item, "b", glow);*/
	}
	
}
