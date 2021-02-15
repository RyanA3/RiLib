package me.felnstaren.felib.packet.wrapper.data;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

import org.bukkit.entity.Entity;

import me.felnstaren.felib.packet.enums.MetadataValue;
import me.felnstaren.felib.reflect.Reflector;

public class DataWatcherWrapper {

	private static final Method WATCHER_REGISTER_METHOD = Reflector.getDeclaredMethod(Reflector.getNMSClass("DataWatcher"), "register", Reflector.getNMSClass("DataWatcherObject"), Object.class);
	private static final Method WATCHER_SET_METHOD = Reflector.getDeclaredMethod(Reflector.getNMSClass("DataWatcher"), "set", Reflector.getNMSClass("DataWatcherObject"), Object.class);
	private static final Method WATCHER_GET_METHOD = Reflector.getDeclaredMethod(Reflector.getNMSClass("DataWatcher"), "get", Reflector.getNMSClass("DataWatcherObject"));
	private static final Constructor<?> WATCHER_CONSTRUCTOR = Reflector.getConstructor(Reflector.getNMSClass("DataWatcher"), Reflector.getNMSClass("Entity"));
	private static final Constructor<?> WATCHER_OBJECT_CONSTRUCTOR = Reflector.getConstructor(Reflector.getNMSClass("DataWatcherObject"), int.class, Reflector.getNMSClass("DataWatcherSerializer"));
	
	
	
	private Object watcher;
	private HashMap<Integer, Object> watcher_objects = new HashMap<Integer, Object>();
	
	/**
	 * Set @boolean construct to true to create a new data watcher for the entity @Object value
	 * Set @boolean construct to false to use a specified data watcher ( @Object value )
	 */
	public DataWatcherWrapper(Object value, boolean construct) {
		if(construct) this.watcher = Reflector.newInstanceOf(WATCHER_CONSTRUCTOR, value); //this.watcher = Reflector.newInstanceOf("DataWatcher", new Class<?>[]{ Reflector.getNMSClass("Entity") }, new Object[] { value });
		else this.watcher = value;
	}
	
	/**
	 * Loads specified entity's data watcher, this will edit the real data values
	 */
	public DataWatcherWrapper(Entity entity) {
		Object craft_entity = Reflector.getNMSClass("CraftEntity").cast(entity);
		Object handle = Reflector.invokeDeclaredMethod(craft_entity, "getHandle");
		this.watcher = Reflector.invokeDeclaredMethod(handle, "getDataWatcher");
	}
	
	/**
	 * Loads values into a new, fake data watcher
	 */
	public DataWatcherWrapper(List<?> watched_items) {
		this(null, true);
		for(Object watched_item : watched_items)
			register(Reflector.getDeclaredFieldValue(watched_item, "a"), Reflector.getDeclaredFieldValue(watched_item, "b"));
		
	}
	
	
	
	public void register(Object watcher_object, Object value) {
		Reflector.invokeMethod(WATCHER_REGISTER_METHOD, watcher, watcher_object, value);
	}
	
	public void register(MetadataValue type, Object value) {
		Object serializer = type.getSerializer().getNMSSerializer();
		Object watcher_object = Reflector.newInstanceOf(WATCHER_OBJECT_CONSTRUCTOR, type.getIndex(), serializer);
		watcher_objects.put(type.getIndex(), watcher_object);
		register(watcher_object, value);
	}
	
	public void set(Object watcher_object, Object value) {
		Reflector.invokeMethod(WATCHER_SET_METHOD, watcher, watcher_object, value);
	}
	
	public void set(MetadataValue type, Object value) {
		Object watcher_object = watcher_objects.get(type.getIndex());
		if(value == null) value = type.getDefaultValue();
		if(watcher_object == null) register(type.getIndex(), value);
		else set(watcher_object, value);
	}
	
	public Object get(Object watcher_object) {
		return Reflector.invokeMethod(WATCHER_GET_METHOD, watcher, watcher_object);
	}
	
	public Object get(MetadataValue type) {
		Object watcher_object = watcher_objects.get(type.getIndex());
		if(watcher_object == null) return type.getDefaultValue();
		else return get(watcher_object);
	}
	
	
	
	public Object getDataWatcher() {
		return watcher;
	}
	
}
