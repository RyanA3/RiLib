package me.felnstaren.felib.packet.wrapper.data;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.bukkit.entity.Entity;

import me.felnstaren.felib.reflect.Reflector;

public class DataWatcherWrapper {
	
	private static final HashMap<Class<?>, Object> SERIALIZERS = new HashMap<Class<?>, Object>();
	static {
		SERIALIZERS.put(Byte.class, Reflector.getDeclaredStaticFieldValue("DataWatcherRegistry", "a"));
		SERIALIZERS.put(Integer.class, Reflector.getDeclaredStaticFieldValue("DataWatcherRegistry", "b"));
		SERIALIZERS.put(Float.class, Reflector.getDeclaredStaticFieldValue("DataWatcherRegistry", "c"));
		SERIALIZERS.put(String.class, Reflector.getDeclaredStaticFieldValue("DataWatcherRegistry", "d"));
		SERIALIZERS.put(Reflector.getNMSClass("IChatBaseComponent"), Reflector.getDeclaredStaticFieldValue("DataWatcherRegistry", "e"));
		SERIALIZERS.put(Reflector.getNMSClass("ItemStack"), Reflector.getDeclaredStaticFieldValue("DataWatcherRegistry", "g"));
		SERIALIZERS.put(Boolean.class, Reflector.getDeclaredStaticFieldValue("DataWatcherRegistry", "i"));
		SERIALIZERS.put(Reflector.getNMSClass("ParticleParam"), Reflector.getDeclaredStaticFieldValue("DataWatcherRegistry", "j"));
		SERIALIZERS.put(Reflector.getNMSClass("Vector3f"), Reflector.getDeclaredStaticFieldValue("DataWatcherRegistry", "k"));
		SERIALIZERS.put(Reflector.getNMSClass("BlockPosition"), Reflector.getDeclaredStaticFieldValue("DataWatcherRegistry", "l"));
		SERIALIZERS.put(Reflector.getNMSClass("EnumDirection"), Reflector.getDeclaredStaticFieldValue("DataWatcherRegistry", "n"));
		SERIALIZERS.put(Reflector.getNMSClass("NBTTagCompound"), Reflector.getDeclaredStaticFieldValue("DataWatcherRegistry", "p"));
		SERIALIZERS.put(Reflector.getNMSClass("VillagerData"), Reflector.getDeclaredStaticFieldValue("DataWatcherRegistry", "q"));
		SERIALIZERS.put(Reflector.getNMSClass("EntityPose"), Reflector.getDeclaredStaticFieldValue("DataWatcherRegistry", "s"));
	}
	
	private static final HashMap<Class<?>, Object> OPTIONAL_SERIALIZERS = new HashMap<Class<?>, Object>();
	static {
		OPTIONAL_SERIALIZERS.put(Reflector.getNMSClass("IChatBaseComponent"), Reflector.getDeclaredStaticFieldValue("DataWatcherRegistry", "f"));
		OPTIONAL_SERIALIZERS.put(Reflector.getNMSClass("IBlockData"), Reflector.getDeclaredStaticFieldValue("DataWatcherRegistry", "h"));
		OPTIONAL_SERIALIZERS.put(Reflector.getNMSClass("BlockPosition"), Reflector.getDeclaredStaticFieldValue("DataWatcherRegistry", "m"));
		OPTIONAL_SERIALIZERS.put(UUID.class, Reflector.getDeclaredStaticFieldValue("DataWatcherRegistry", "o"));
		OPTIONAL_SERIALIZERS.put(Integer.class, Reflector.getDeclaredStaticFieldValue("DataWatcherRegistry", "r"));
	}

	private Object watcher;
	
	/**
	 * Set @boolean construct to true to create a new data watcher for the entity @Object value
	 * Set @boolean construct to false to use a specified data watcher ( @Object value )
	 */
	public DataWatcherWrapper(Object value, boolean construct) {
		if(construct) this.watcher = Reflector.newInstanceOf("DataWatcher", new Class<?>[]{ Reflector.getNMSClass("Entity") }, new Object[] { value });
		else this.watcher = value;
	}
	
	/**
	 */
	public DataWatcherWrapper(Entity entity) {
		Object craft_entity = Reflector.getNMSClass("CraftEntity").cast(entity);
		Object handle = Reflector.invokeDeclaredMethod(craft_entity, "getHandle");
		this.watcher = Reflector.invokeDeclaredMethod(handle, "getDataWatcher");
	}
	
	/**
	 * Loads values into a new data watcher
	 */
	public DataWatcherWrapper(List<?> data_watcher_items) {
		this(null, true);
		for(int i = 0; i < data_watcher_items.size(); i++)
			if(data_watcher_items.get(i) != null) register(i, Reflector.getDeclaredFieldValue(data_watcher_items.get(i), "b"));
	}
	
	
	
	private Object getSerializerFor(Object what) {
		if(what instanceof Optional) {
			Optional<?> option = (Optional<?>) what;
			Object serializer = OPTIONAL_SERIALIZERS.get(option.get().getClass());
			if(serializer != null) return serializer;
			for(Class<?> possible_serializer : OPTIONAL_SERIALIZERS.keySet()) if(possible_serializer.isAssignableFrom(option.get().getClass())) return OPTIONAL_SERIALIZERS.get(possible_serializer);
			return serializer;
		}
		else {
			Object serializer = SERIALIZERS.get(what.getClass());
			if(serializer != null) return SERIALIZERS.get(what.getClass());
			for(Class<?> possible_serializer : SERIALIZERS.keySet()) if(possible_serializer.isAssignableFrom(what.getClass())) return SERIALIZERS.get(possible_serializer);
			return serializer;
		}
	}
	
	public void register(int index, Object value) {
		Object serializer = getSerializerFor(value);
		Object watcher_object = Reflector.newInstanceOf("DataWatcherObject", new Class<?>[]{ int.class, Reflector.getNMSClass("DataWatcherSerializer") }, new Object[]{ index, serializer });
		Reflector.invokeDeclaredMethod(Reflector.getNMSClass("DataWatcher"), "register", watcher, new Class<?>[] { Reflector.getNMSClass("DataWatcherObject"), Object.class }, new Object[] { watcher_object, value });
	}
	
	
	
	public Object getDataWatcher() {
		return watcher;
	}
	
}
