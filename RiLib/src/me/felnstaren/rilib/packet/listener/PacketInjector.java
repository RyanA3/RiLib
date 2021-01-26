package me.felnstaren.rilib.packet.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import io.netty.channel.Channel;
import me.felnstaren.rilib.reflect.Packeteer;

/**
 * INTERNAL
 * Automatically registeres a PacketHandler to every player that
 * joins the server, and removes PacketHandlers from players that
 * leave. 
 */
public class PacketInjector implements Listener {

	private String channel_handler_name;
	private PacketEventManager pman;
	
	public PacketInjector(JavaPlugin plugin) {
		this.channel_handler_name = plugin.getName();
		this.pman = new PacketEventManager();
		
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	
	
	public PacketHandler register(Player player) {
		try {
			Channel channel = Packeteer.getChannel(player);
			if(channel.pipeline().get(channel_handler_name) == null) {
				PacketHandler handler = new PacketHandler(pman, player);
				channel.pipeline().addBefore("packet_handler", channel_handler_name, handler);
				return handler;
			}
		} catch (Exception e) { e.printStackTrace(); }
		return null;
	}
	
	public void unregister(Player player) {
		try {
			Channel channel = Packeteer.getChannel(player);
			if(channel.pipeline().get(channel_handler_name) != null) 
				channel.pipeline().remove(channel_handler_name);
		} catch (Exception e) { e.printStackTrace(); }
	}
	
	public PacketHandler getHandler(Player player) {
		try {
			Channel channel = Packeteer.getChannel(player);
			return (PacketHandler) channel.pipeline().get(channel_handler_name);
		} catch (Exception e) { e.printStackTrace(); }
		return null;
	}
	
	
	
	public void shutdown() {
		for(Player p : Bukkit.getOnlinePlayers())
			unregister(p);
	}
	
	
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		register(event.getPlayer());
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		unregister(event.getPlayer());
	}
	
	
	
	public PacketEventManager getManager() {
		return pman;
	}
	
}
