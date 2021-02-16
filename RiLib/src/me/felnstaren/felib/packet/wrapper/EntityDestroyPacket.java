package me.felnstaren.felib.packet.wrapper;

import java.lang.reflect.Constructor;

import me.felnstaren.felib.FeLib;
import me.felnstaren.felib.logger.Level;
import me.felnstaren.felib.reflect.Reflector;

public class EntityDestroyPacket extends PacketWrapper {

	private static final Constructor<?> ENTITY_DESTROY_PACKET_CONSTRUCTOR = Reflector.getConstructor("PacketPlayOutEntityDestroy", int[].class);
	
	public EntityDestroyPacket(Object packet) {
		super(packet);
	}
	
	public EntityDestroyPacket(boolean indicator, int... entity_ids) {
		super(Reflector.newInstanceOf(ENTITY_DESTROY_PACKET_CONSTRUCTOR, entity_ids));	
		set("a", entity_ids);
		FeLib.LOGGER.log(Level.DEBUG, "Created destroy packet for " + entity_ids[0]);
	}
	
}
