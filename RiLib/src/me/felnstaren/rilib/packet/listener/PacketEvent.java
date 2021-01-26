package me.felnstaren.rilib.packet.listener;

import java.lang.reflect.Field;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;

import me.felnstaren.rilib.reflect.Reflector;

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
	
	public Object getPacketField(String field) {
		return Reflector.getDeclaredField(packet, field);
	}
	
	public void setPacketField(String field, Object value) {
		Reflector.setDeclaredField(packet, field, value);
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
