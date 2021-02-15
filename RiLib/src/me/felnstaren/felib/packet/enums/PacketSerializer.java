package me.felnstaren.felib.packet.enums;

import java.util.Optional;
import java.util.UUID;

import me.felnstaren.felib.reflect.Reflector;

public enum PacketSerializer {

	BYTE(Reflector.getDeclaredStaticFieldValue("DataWatcherRegistry", "a"), Byte.class),
	INTEGER(Reflector.getDeclaredStaticFieldValue("DataWatcherRegistry", "b"), Integer.class),
	FLOAT(Reflector.getDeclaredStaticFieldValue("DataWatcherRegistry", "c"), Float.class),
	STRING(Reflector.getDeclaredStaticFieldValue("DataWatcherRegistry", "d"), String.class),
	CHAT_COMPONENT(Reflector.getDeclaredStaticFieldValue("DataWatcherRegistry", "e"), Reflector.getNMSClass("IChatBaseComponent")),
	ITEM_STACK(Reflector.getDeclaredStaticFieldValue("DataWatcherRegistry", "g"), Reflector.getNMSClass("ItemStack")),
	BOOLEAN(Reflector.getDeclaredStaticFieldValue("DataWatcherRegistry", "i"), Boolean.class),
	PARTICLE_PARAM(Reflector.getDeclaredStaticFieldValue("DataWatcherRegistry", "j"), Reflector.getNMSClass("ParticleParam")),
	VECTOR_3F(Reflector.getDeclaredStaticFieldValue("DataWatcherRegistry", "k"), Reflector.getNMSClass("Vector3f")),
	BLOCK_POSITION(Reflector.getDeclaredStaticFieldValue("DataWatcherRegistry", "l"), Reflector.getNMSClass("BlockPosition")),
	ENUM_DIRECTION(Reflector.getDeclaredStaticFieldValue("DataWatcherRegistry", "n"), Reflector.getNMSClass("EnumDirection")),
	NBT_TAG_COMPOUND(Reflector.getDeclaredStaticFieldValue("DataWatcherRegistry", "p"), Reflector.getNMSClass("NBTTagCompound")),
	VILLAGER_DATA(Reflector.getDeclaredStaticFieldValue("DataWatcherRegistry", "q"), Reflector.getNMSClass("VillagerData")),
	ENTITY_POSE(Reflector.getDeclaredStaticFieldValue("DataWatcherRegistry", "s"), Reflector.getNMSClass("EntityPose")),
	
	OPTIONAL_CHAT_COMPONENT(Reflector.getDeclaredStaticFieldValue("DataWatcherRegistry", "f"), Reflector.getNMSClass("IChatBaseComponent")),
	OPTIONAL_BLOCK_DATA(Reflector.getDeclaredStaticFieldValue("DataWatcherRegistry", "h"), Reflector.getNMSClass("IBlockData")),
	OPTIONAL_BLOCK_POSITION(Reflector.getDeclaredStaticFieldValue("DataWatcherRegistry", "m"), Reflector.getNMSClass("BlockPosition")),
	OPTIONAL_UUID(Reflector.getDeclaredStaticFieldValue("DataWatcherRegistry", "o"), UUID.class),
	OPTIONAL_INTEGER(Reflector.getDeclaredStaticFieldValue("DataWatcherRegistry", "r"), Integer.class);
	
	private Object serializer;
	private Class<?> type;
	
	private PacketSerializer(Object serializer, Class<?> type) {
		this.serializer = serializer;
		this.type = type;
	}
	
	public Object getNMSSerializer() {
		return serializer;
	}
	
	public Class<?> getType() {
		return type;
	}
	
	public static PacketSerializer getForType(Class<?> type) {
		for(PacketSerializer check : values())
			if(check.getType() == type) return check;
		return null;
	}
	
	public static PacketSerializer getForObject(Object object) {
		if(object instanceof Optional) return getForType(((Optional<?>) object).get().getClass());
		return getForType(object.getClass());
	}
	
}
