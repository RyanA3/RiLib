package me.felnstaren.felib.packet.wrapper.meta;

import java.util.List;
import java.util.Optional;

import org.bukkit.entity.Player;

import me.felnstaren.felib.FeLib;
import me.felnstaren.felib.logger.Level;
import me.felnstaren.felib.packet.PacketEntityPose;
import me.felnstaren.felib.packet.wrapper.PacketWrapper;
import me.felnstaren.felib.reflect.Reflector;
import me.felnstaren.felib.util.BitMaskUtil;
import net.minecraft.server.v1_16_R3.ChatComponentText;
import net.minecraft.server.v1_16_R3.DataWatcher;
import net.minecraft.server.v1_16_R3.DataWatcherObject;
import net.minecraft.server.v1_16_R3.DataWatcherRegistry;
import net.minecraft.server.v1_16_R3.IChatBaseComponent;
import net.minecraft.server.v1_16_R3.PacketPlayOutEntityMetadata;

public class EntityMetaPacket extends PacketWrapper {

	public EntityMetaPacket(Object packet) {
		super(packet);
		
		FeLib.LOGGER.log(Level.DEBUG, packet.getClass().getSimpleName());
		List<?> datas = getDataWatchers();
		for(int i = 0; i < datas.size(); i++) {
			Object data = datas.get(i);
			Class<?> valuetype = Reflector.getDeclaredFieldValue(data, "b").getClass();
			FeLib.LOGGER.log(Level.DEBUG, "" + i + " -> " + valuetype.getSimpleName());
		}
	}
	
	public EntityMetaPacket(int entity_id, Player player) {
		super(null);
		
		DataWatcher watcher = new DataWatcher(null);
		watcher.register(new DataWatcherObject<Byte>(0, DataWatcherRegistry.a), (byte) 0b00000000);
		//watcher.register(new DataWatcherObject<Integer>(1, DataWatcherRegistry.b), 0);
		watcher.register(new DataWatcherObject<Optional<IChatBaseComponent>>(2, DataWatcherRegistry.f), Optional.of(new ChatComponentText("Scuffed")));
		watcher.register(new DataWatcherObject<Boolean>(3, DataWatcherRegistry.i), true);
		//watcher.register(new DataWatcherObject<Boolean>(4, DataWatcherRegistry.i), false);
		watcher.register(new DataWatcherObject<Boolean>(5, DataWatcherRegistry.i), true);
		//watcher.register(new DataWatcherObject<EntityPose>(6, DataWatcherRegistry.s), EntityPose.SLEEPING);
		//watcher.register(new DataWatcherObject<Byte>(7, DataWatcherRegistry.a), (byte) 0b00000111);
		//watcher.register(new DataWatcherObject<Byte>(16, DataWatcherRegistry.a), (byte) 0b00000000);
		watcher.register(new DataWatcherObject<Integer>(18, DataWatcherRegistry.b), 1024);
		PacketPlayOutEntityMetadata pac = new PacketPlayOutEntityMetadata(entity_id, watcher, true);
		Reflector.setDeclaredFieldValue(pac, "a", entity_id);
		
		this.packet = pac;
	}
	
	
	
	public List<?> getDataWatchers() {
		return (List<?>) Reflector.getDeclaredFieldValue(packet, "b");
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getData(int index, Class<?> T, T defalt) {
		if(index >= getDataWatchers().size()) return defalt;
		Object data_watcher = getDataWatchers().get(index);
		Object value = Reflector.getDeclaredFieldValue(data_watcher, "b");
		if(value.getClass() != T) return defalt;
		try { return (T) value; }
		catch (Exception e) { e.printStackTrace(); return defalt; }
	}
	
	public void setData(int index, Object value) {
		if(index >= getDataWatchers().size()) return;
		Object data_watcher = getDataWatchers().get(index);
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
