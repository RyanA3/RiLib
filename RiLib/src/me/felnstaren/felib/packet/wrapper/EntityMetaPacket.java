package me.felnstaren.felib.packet.wrapper;

import java.lang.reflect.Constructor;
import java.util.List;

import org.bukkit.entity.Entity;

import me.felnstaren.felib.FeLib;
import me.felnstaren.felib.logger.Level;
import me.felnstaren.felib.packet.enums.MetadataValue;
import me.felnstaren.felib.packet.wrapper.data.DataWatcherWrapper;
import me.felnstaren.felib.reflect.Reflector;

public class EntityMetaPacket extends PacketWrapper {

	private static final Constructor<?> ENTITY_METADATA_PACKET_CONSTRUCTOR = Reflector.getConstructor(Reflector.getNMSClass("PacketPlayOutEntityMetadata"), int.class, Reflector.getNMSClass("DataWatcher"), boolean.class);
	
	protected DataWatcherWrapper watcher;
	
	/**
	 * Editing a packet this way will only affect the packet
	 */
	public EntityMetaPacket(Object packet) {
		super(packet);
		FeLib.LOGGER.log(Level.DEBUG, packet.getClass().getSimpleName());
		List<?> datas = getNMSWatchedValues();
		this.watcher = new DataWatcherWrapper(datas);
		
		for(int i = 0; i < datas.size(); i++) {
			Object data = datas.get(i);
			Class<?> valuetype = Reflector.getDeclaredFieldValue(data, "b").getClass();
			FeLib.LOGGER.log(Level.DEBUG, "" + i + " -> " + valuetype.getSimpleName());
		}
	}
	
	/**
	 * Editing a packet this way will only affect the packet
	 */
	public EntityMetaPacket(int entity_id, DataWatcherWrapper watcher) {
		super(null);
		this.watcher = watcher;
		this.packet = Reflector.newInstanceOf(ENTITY_METADATA_PACKET_CONSTRUCTOR, entity_id, watcher.getNMSWatcher(), true);
	}
	
	/**
	 * Editing a packet this way will affect the entity's data as well
	 */
	public EntityMetaPacket(boolean indicator, Entity entity) {
		super(entity.getEntityId());
		this.watcher = new DataWatcherWrapper(entity);
	}
	
	
	
	public List<?> getNMSWatchedValues() {
		return (List<?>) Reflector.getDeclaredFieldValue(packet, "b");
	}
	
	public DataWatcherWrapper getDataWatcher() {
		return watcher;
	}
	
	public Object getData(MetadataValue type) {
		return watcher.get(type);
	}
	
	public void setData(MetadataValue type, Object value) {
		watcher.set(type, value);
	}

}
