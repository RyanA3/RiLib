package me.felnstaren.felib.packet.wrapper.meta;

import java.util.List;

import org.bukkit.entity.Entity;

import me.felnstaren.felib.FeLib;
import me.felnstaren.felib.logger.Level;
import me.felnstaren.felib.packet.PacketEntityPose;
import me.felnstaren.felib.packet.wrapper.PacketWrapper;
import me.felnstaren.felib.packet.wrapper.data.DataWatcherWrapper;
import me.felnstaren.felib.reflect.Reflector;
import me.felnstaren.felib.util.BitMaskUtil;

public class EntityMetaPacket extends PacketWrapper {

	protected DataWatcherWrapper watcher;
	
	/**
	 * Editing a packet this way will only affect the packet
	 */
	public EntityMetaPacket(Object packet) {
		super(packet);
		FeLib.LOGGER.log(Level.DEBUG, packet.getClass().getSimpleName());
		List<?> datas = getWatchedValues();
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
		this.packet = Reflector.newInstanceOf("PacketPlayOutEntityMetadata", new Class<?>[] { int.class, Reflector.getNMSClass("DataWatcher"), boolean.class }, new Object[] { entity_id, watcher.getDataWatcher(), true } );
	}
	
	/**
	 * Editing a packet this way will affect the entity's data as well
	 */
	public EntityMetaPacket(Entity entity) {
		super(entity.getEntityId());
		this.watcher = new DataWatcherWrapper(entity);
	}
	
	
	
	public List<?> getWatchedValues() {
		return (List<?>) Reflector.getDeclaredFieldValue(packet, "b");
	}
	
	public DataWatcherWrapper getDataWatcher() {
		return watcher;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getData(int index, Class<?> T, T defalt) {
		if(index >= getWatchedValues().size()) return defalt;
		Object data_watcher = getWatchedValues().get(index);
		Object value = Reflector.getDeclaredFieldValue(data_watcher, "b");
		if(value.getClass() != T) return defalt;
		try { return (T) value; }
		catch (Exception e) { e.printStackTrace(); return defalt; }
	}
	
	public void setData(int index, Object value) {
		if(index >= getWatchedValues().size()) return;
		Object data_watcher = getWatchedValues().get(index);
		if(Reflector.getDeclaredFieldValue(data_watcher, "b").getClass() != value.getClass()) return;
		Reflector.setDeclaredFieldValue(data_watcher, "b", value);
		FeLib.LOGGER.log(Level.DEBUG, "Success set data");
	}
	
	
	//Must use Byte.class not byte.class since that's how it is in NMS classes /shrug
	public void toggleValue(int bit, int index) {
		Byte mask = getData(index, Byte.class, (byte) 0);
		mask = BitMaskUtil.toggle(mask, bit);
		setData(index, mask);
	}
	
	public void toggleValue(EntityBitProperty value) { toggleValue(value.ordinal(), 0); } 
	
	public boolean getValue(int bit, int index) {
		byte mask = getData(index, Byte.class, (byte) 0);
		return BitMaskUtil.get(mask, bit);
	}
	
	public boolean getValue(EntityBitProperty value) { return getValue(value.ordinal(), 0); }
	
	
	public void setAirTicks(int value) { setData(1, value); }
	public int getAirTicks() { return getData(1, Integer.class, -1); }
	public void setEntityPose(PacketEntityPose pose) { 
		setData(2, Reflector.getNMSClass("EntityPose").getEnumConstants()[pose.ordinal()]); 
	}
	public PacketEntityPose getEntityPose() { return getData(2, PacketEntityPose.class, PacketEntityPose.CROUCHING); }
	
	public enum EntityBitProperty {
		FIRE,
		CROUCHING,
		UNUSED_RIDING,
		SPRINTING,
		SWIMMING,
		INVISIBLE,
		GLOWING,
		GLIDING
	}

}
