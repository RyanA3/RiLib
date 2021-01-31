package me.felnstaren.felib.packet.wrapper;

import me.felnstaren.felib.reflect.Reflector;

public class PacketWrapper {

	protected Object packet;
	
	public PacketWrapper(Object packet) {
		this.packet = packet;
	}
	
	
	
	public void set(String field, Object value) {
		Reflector.setDeclaredField(packet, field, value);
	}
	
	public void set(Object value, String... fields) {
		Reflector.setDeclaredField(packet, value, fields);
	}
	
	public Object get(String field) {
		return Reflector.getDeclaredField(packet, field);
	}
	
	public Object get(String... fields) {
		return Reflector.getDeclaredField(packet, fields);
	}
	
	public Object getPacket() {
		return packet;
	}
	
}
