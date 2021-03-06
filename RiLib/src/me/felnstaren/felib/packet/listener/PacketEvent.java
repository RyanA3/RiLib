package me.felnstaren.felib.packet.listener;

import java.lang.reflect.Field;
import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;

import me.felnstaren.felib.reflect.Reflector;

/**
 * EXTERNAL
 * Contains all the relevant information to be passed to a PacketListener.
 * It is constructed in a PacketHandler and sent to the PacketEventManager
 * when the PacketHandler's player is sent/sends a packet.
 */
public class PacketEvent implements Cancellable {
	
	private Player player;
	private Object packet;
	
	private boolean cancelled;
	
	public PacketEvent(Player player, Object packet) {
		this.player = player;
		this.packet = packet;
	}
	
	
	
	public Object getPacket() {
		return packet;
	}
	
	public boolean isPacket(String name) {
		return packet.getClass().getSimpleName().equalsIgnoreCase(name);
	}
	
	public boolean isPacket(ArrayList<String> names) {
		for(String name : names)
			if(isPacket(name)) return true;
		return false;
	}
	
	public boolean isPacket(String... names) {
		for(String name : names)
			if(isPacket(name)) return true;
		return false;
	}
	
	public Object getPacketField(String field) {
		return Reflector.getDeclaredFieldValue(packet, field);
	}
	
	public void setPacketField(String field, Object value) {
		Reflector.setDeclaredFieldValue(packet, field, value);
	}
	
	public Field[] getPacketFields() {
		return packet.getClass().getDeclaredFields();
	}
	
	public Player getPlayer() {
		return player;
	}



	public boolean isCancelled() {
		return cancelled;
	}

	public void setCancelled(boolean value) {
		this.cancelled = value;
	}
	
}
