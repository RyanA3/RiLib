package me.felnstaren.felib.packet.wrapper.meta;

import java.util.List;

import me.felnstaren.felib.packet.wrapper.PacketWrapper;
import me.felnstaren.felib.reflect.Reflector;

public class EntityMetaPacket extends PacketWrapper {

	public EntityMetaPacket(Object packet) {
		super(packet);
	}
	
	public List<?> getData() {
		return (List<?>) Reflector.getDeclaredFieldValue(packet, "b");
	}

}
