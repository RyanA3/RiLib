package me.felnstaren.felib.packet.wrapper;

import me.felnstaren.felib.reflect.Reflector;

public class PacketWrapper {

	protected Object packet;
	
	public PacketWrapper(Object packet) {
		this.packet = packet;
	}
	
	
	
	public void set(String field, Object value) {
		Reflector.setDeclaredFieldValue(packet, field, value);
	}
	
	public void set(Object value, String... fields) {
		Reflector.setDeclaredFieldsValue(packet, value, fields);
	}
	
	public Object get(String field) {
		return Reflector.getDeclaredFieldValue(packet, field);
	}
	
	public Object get(String... fields) {
		return Reflector.getDeclaredFieldsValue(packet, fields);
	}
	
	public Object getPacket() {
		return packet;
	}
	
}
