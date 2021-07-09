package me.felnstaren.felib.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;

import me.felnstaren.felib.FeLib;
import me.felnstaren.felib.logger.Level;
import me.felnstaren.felib.util.PrimitiveUtil;

/**
 * Reflector Class
 * @author Ryan
 * 
 * Adapted from BananaPuncher714's NBTEditor class
 * https://github.com/BananaPuncher714/NBTEditor
 */
public final class Reflector {

	private static final String           VERSION;
	
	public static final Map< String,   Class<?>       >   CLASS_CACHE;
	public static final Map< String,   Method         >   METHOD_CACHE;
	public static final Map< String,   Constructor<?> >   CONSTRUCTOR_CACHE;
	public static final Map< String,   Field          >   FIELD_CACHE;
 
	static {
		VERSION = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
		
		CLASS_CACHE = new HashMap<String, Class<?>>();
		try {
			CLASS_CACHE.put("PacketPlayOutTitle", 	 Class.forName( "net.minecraft.server."    + VERSION +   ".PacketPlayOutTitle"				  ));
			CLASS_CACHE.put("EnumTitleAction",       Class.forName( "net.minecraft.server."    + VERSION +   ".PacketPlayOutTitle$EnumTitleAction"));
			CLASS_CACHE.put("ChatMessageType",       Class.forName( "net.minecraft.server."    + VERSION +   ".ChatMessageType"                   ));
			CLASS_CACHE.put("IChatBaseComponent",    Class.forName( "net.minecraft.server."    + VERSION +   ".IChatBaseComponent"                ));
			CLASS_CACHE.put("ChatSerializer",        Class.forName( "net.minecraft.server."    + VERSION +   ".IChatBaseComponent$ChatSerializer" ));
			CLASS_CACHE.put("PacketPlayOutChat",     Class.forName( "net.minecraft.server."    + VERSION +   ".PacketPlayOutChat"                 ));
			CLASS_CACHE.put("EntityPlayer",          Class.forName( "net.minecraft.server."    + VERSION +   ".EntityPlayer"                      ));
			CLASS_CACHE.put("PlayerConnection",      Class.forName( "net.minecraft.server."    + VERSION +   ".PlayerConnection"                  ));
			CLASS_CACHE.put("IChatMutableComponent", Class.forName( "net.minecraft.server."    + VERSION +   ".IChatMutableComponent"             ));
			CLASS_CACHE.put("Packet",                Class.forName( "net.minecraft.server."    + VERSION +   ".Packet"                            ));
			CLASS_CACHE.put("MinecraftServer",       Class.forName( "net.minecraft.server."    + VERSION +   ".MinecraftServer"                   ));
			//CLASS_CACHE.put("PacketPlayOutPlayerListHeaderFooter", Class.forName( "net.minecraft.server" + VERSION + "PacketPlayOutPlayerListHeaderFooter" ));
			CLASS_CACHE.put("CraftPlayer",           Class.forName( "org.bukkit.craftbukkit."  + VERSION +   ".entity.CraftPlayer"                ));
			CLASS_CACHE.put("CraftEntity",           Class.forName( "org.bukkit.craftbukkit."  + VERSION +   ".entity.CraftEntity"                ));
			CLASS_CACHE.put("Blocks",                Class.forName( "net.minecraft.server."    + VERSION +   ".Blocks"                            ));
			CLASS_CACHE.put("Block",                 Class.forName( "net.minecraft.server."    + VERSION +   ".Block"                             ));
			CLASS_CACHE.put("ItemStack",             Class.forName( "net.minecraft.server."    + VERSION +   ".ItemStack"                         ));
			CLASS_CACHE.put("Item",                  Class.forName( "net.minecraft.server."    + VERSION +   ".Item"                              ));	
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		METHOD_CACHE = new HashMap<String, Method>();
		try {
			METHOD_CACHE.put("b",            getNMSClass("ChatSerializer")    .getDeclaredMethod("b", String.class));
			METHOD_CACHE.put("a", 			 getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class));
			METHOD_CACHE.put("sendPacket",   getNMSClass("PlayerConnection")  .getDeclaredMethod("sendPacket", getNMSClass("Packet")));
			METHOD_CACHE.put("getHandle",    getNMSClass("CraftEntity")       .getDeclaredMethod("getHandle"));
			METHOD_CACHE.put("getDataWatcher", getNMSClass("Entity")     .getDeclaredMethod("getDataWatcher"));
			METHOD_CACHE.put("getServer",    getNMSClass("MinecraftServer")   .getDeclaredMethod("getServer"));
			METHOD_CACHE.put("getById",      getNMSClass("Item")              .getMethod        ("getById", int.class));
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		CONSTRUCTOR_CACHE = new HashMap<String, Constructor<?>>();
		try {
			CONSTRUCTOR_CACHE.put("PacketPlayOutTitle", getNMSClass("PacketPlayOutTitle").getConstructor(getNMSClass("PacketPlayOutTitle$EnumTitleAction"), getNMSClass("IChatBaseComponent"), int.class, int.class, int.class));
			CONSTRUCTOR_CACHE.put("PacketPlayOutChat", getNMSClass("PacketPlayOutChat").getConstructor(getNMSClass("IChatBaseComponent"), getNMSClass("ChatMessageType"), UUID.class));
			CONSTRUCTOR_CACHE.put("PacketPlayOutPlayerListHeaderFooter", getNMSClass("PacketPlayOutPlayerListHeaderFooter").getConstructor());
			CONSTRUCTOR_CACHE.put("PacketPlayOutWorldParticles", getNMSClass("PacketPlayOutWorldParticles").getConstructor(getNMSClass("ParticleParam"), boolean.class, double.class, double.class, double.class, float.class, float.class, float.class, float.class, int.class));
			CONSTRUCTOR_CACHE.put("ParticleParamBlock", getNMSClass("ParticleParamBlock").getConstructor(getNMSClass("Particle"), getNMSClass("IBlockData")));
			CONSTRUCTOR_CACHE.put("ParticleParamRedstone", getNMSClass("ParticleParamRedstone").getConstructor(float.class, float.class, float.class, float.class));
			CONSTRUCTOR_CACHE.put("ParticleParamItem", getNMSClass("ParticleParamItem").getConstructor(getNMSClass("Particle"), getNMSClass("ItemStack")));
			CONSTRUCTOR_CACHE.put("ItemStack", getNMSClass("ItemStack").getConstructor(getNMSClass("IMaterial")));
			//CONSTRUCTOR_CACHE.put(getNMSClass("Particle"), getNMSClass("Particle").getConstructor(boolean.class, getNMSClass("ParticleParam$a")));
			CONSTRUCTOR_CACHE.put("PacketPlayOutEntityEffect", getNMSClass("PacketPlayOutEntityEffect").getConstructor(int.class, getNMSClass("MobEffect")));
			CONSTRUCTOR_CACHE.put("MobEffect", getNMSClass("MobEffect").getConstructor(getNMSClass("MobEffectList"), int.class, int.class, boolean.class, boolean.class, boolean.class));
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		FIELD_CACHE = new HashMap<String, Field>();
		try {
			Field entity_id_packet = getNMSClass("PacketPlayOutEntity").getDeclaredField("a");
			
			FIELD_CACHE.put("EntityPlayer:playerConnection", getNMSClass("EntityPlayer")   .getField("playerConnection"));
			FIELD_CACHE.put("MinecraftServer:recentTps",     getNMSClass("MinecraftServer").getField("recentTps"));
			FIELD_CACHE.put("PacketPlayOutEntity:a", entity_id_packet);
			FIELD_CACHE.put("PacketPlayOutEntityLook:a", entity_id_packet);
			FIELD_CACHE.put("PacketPlayOutRelEntityMove:a", entity_id_packet);
			FIELD_CACHE.put("PacketPlayOutRelEntityMoveLook:a", entity_id_packet);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	/**
	 * Get NMS class from class cache, or classes if not cached
	 * 
	 * @author BananaPuncher714
	 */
	public static Class<?> getNMSClass(String name) {
		if ( CLASS_CACHE.containsKey( name ) ) {
			return CLASS_CACHE.get( name );
		}

		try {
			return Class.forName("net.minecraft.server." + VERSION + "." + name);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	

	
	/**
	 * Gets the key to store a class's method
	 */
	private static String getMethodKey(Class<?> from, String method_name, Class<?>... param_types) {
		String key = from.getSimpleName() + ":" + method_name + ":";
		for(Class<?> param : param_types) key += param.getSimpleName() + ",";
		return key;
	}
	
	/**
	 * Gets the key to store a class's constructor
	 */
	private static String getConstructorKey(Class<?> from, Class<?>... param_types) {
		String key = from.getSimpleName() + ":";
		for(Class<?> param : param_types) key += param.getSimpleName() + ",";
		return key;
	}
	
	/**
	 * Gets the key to store a class's field
	 */
	private static String getFieldKey(Class<?> from, String field_name) {
		return from.getSimpleName() + ":" + field_name;
	}
	
	
	
	public static Object cast(Object what, Class<?> to) {
		try {
			return to.cast(what);
		} catch (Exception e) {
			e.printStackTrace();
			FeLib.LOGGER.log(Level.WARNING, "Failed casting object");
			return null;
		}
	}
	
	
	
	
	
	/*
	 * |=============================|
	 * |   FIELD GETTING / SETTING   |
	 * |=============================|
	 */
	
	public static Field getDeclaredField(Class<?> from, String field_name) {
		String key = getFieldKey(from, field_name);
		Field field = FIELD_CACHE.get(key);
		
		if(field != null) { //First Time?
			field.setAccessible(true);
			return field;
		}
		
		try { field = from.getDeclaredField(field_name); } 
		catch (Exception e1) { 
			try { field = from.getSuperclass().getDeclaredField(field_name); }
			catch (Exception e2) { e2.printStackTrace(); return null; };
		}
		
		if(field != null) {
			FIELD_CACHE.put(key, field);
			field.setAccessible(true);
		}
		
		return field;
	}
	
	public static Object getDeclaredFieldValue(Object from, Field field) {
		if(field == null || from == null) return null;
		try { return field.get(from); }
		catch (Exception e) { e.printStackTrace(); }
		return null;
	}
	
	public static Object getDeclaredFieldValue(Object from, String field_name) {
		return getDeclaredFieldValue(from, getDeclaredField(from.getClass(), field_name)); 
	}
	
	public static Object getDeclaredFieldsValue(Object from, String... field_paths) {
		try {
			Field field = getDeclaredField(from.getClass(), field_paths[0]);
			Object stored = from;
			for(int i = 1; i < field_paths.length; i++) {
				stored = field.get(stored);
				field = getDeclaredField(stored.getClass(), field_paths[i]);
			}
			return field.get(stored);
		} catch (Exception e) { e.printStackTrace(); }
		return null;
	}
	
	public static void setDeclaredFieldValue(Object from, String field_name, Object value) {
		try {
			Field field = getDeclaredField(from.getClass(), field_name);
			field.set(from, value);
		} catch (Exception e) { e.printStackTrace(); }
	}

	public static void setDeclaredFieldsValue(Object from, Object value, String... field_paths) {
		try {
			Field field = getDeclaredField(from.getClass(), field_paths[0]);
			Object stored = from;
			for(int i = 1; i < field_paths.length; i++) {
				stored = field.get(stored);
				field = getDeclaredField(stored.getClass(), field_paths[i]);
			}
			field.set(stored, value);
		} catch (Exception e) { e.printStackTrace(); }
	}
	
	public static Object getDeclaredStaticFieldValue(Class<?> from, Field field) {
		if(field == null || from == null) return null;
		try { return field.get(from); }
		catch (Exception e) { e.printStackTrace(); }
		return null;
	}
	
	public static Object getDeclaredStaticFieldValue(String from_name, String field_name) {
		Class<?> from = getNMSClass(from_name);
		return getDeclaredStaticFieldValue(from, getDeclaredField(from, field_name));
	}
	

	
	
	
	
	
	/** 
	 *  |==================|
	 *  |   CONSTRUCTION   |
	 *  |==================|
	 */
	
	public static Constructor<?> getConstructor(Class<?> from, Class<?>... param_types) {
		String key = getConstructorKey(from, param_types);
		Constructor<?> constructor = CONSTRUCTOR_CACHE.get(key);
		
		if(constructor != null) {
			constructor.setAccessible(true);
			return constructor;
		}
		
		try {
			constructor = from.getConstructor(param_types);
			constructor.setAccessible(true);
			CONSTRUCTOR_CACHE.put(key, constructor);
		} catch (Exception e) { 
			try {
				for(int i = 0; i < param_types.length; i++) param_types[i] = PrimitiveUtil.getPrimitiveVersion(param_types[i]);
				constructor = from.getConstructor(param_types);
				constructor.setAccessible(true);
				CONSTRUCTOR_CACHE.put(key, constructor);
			} catch (Exception e2) { e2.printStackTrace(); }
		}
		
		return constructor;
	}
	
	public static Constructor<?> getConstructor(String from, Class<?>... param_types) {
		return getConstructor(getNMSClass(from), param_types);
	}
	
	public static Object newInstanceOf(Constructor<?> constructor, Object... params) {
		if(constructor == null) return null;
		try { return constructor.newInstance(params); }
		catch (Exception e) { e.printStackTrace(); return null; }
	}
	
	public static Object newInstanceOf(Class<?> nmsclass, Class<?>[] paramclasses, Object[] params) {
		Constructor<?> constructor = getConstructor(nmsclass, paramclasses);
		return newInstanceOf(constructor, params);
	}
	
	public static Object newInstanceOf(String nmsclass, Object... params) {
		Class<?>[] paramclasses = new Class<?>[params.length];
		for(int i = 0; i < params.length; i++) paramclasses[i] = params[i].getClass();
		return newInstanceOf(getNMSClass(nmsclass), paramclasses, params);
	}
	
	public static Object newInstanceOf(String nmsclass, Class<?>[] paramclasses, Object[] params) {
		return newInstanceOf(getNMSClass(nmsclass), paramclasses, params);
	}
	
	public static Object newInstanceOf(String nmsclass, String[] paramclassnames, Object[] params) {
		Class<?>[] paramclasses = new Class<?>[paramclassnames.length];
		for(int i = 0; i < paramclassnames.length; i++) paramclasses[i] = getNMSClass(paramclassnames[i]);
		return newInstanceOf(getNMSClass(nmsclass), paramclasses, params);
	}
	
	
	
	
	
	/*
	 * |=======================|
	 * |   METHOD INVOCATION   |
	 * |=======================|
	 */
	
	public static Method getDeclaredMethod(Class<?> from, String name, Class<?>... param_types) {
		String key = getMethodKey(from, name, param_types);
		Method method = METHOD_CACHE.get(key);
		
		if(method != null) { //First Time?
			method.setAccessible(true);
			return method;
		}
		
		FeLib.LOGGER.log(Level.DEBUG, "Try Locate Method: " + key);
		
		try { method = from.getDeclaredMethod(name, param_types); } 
		catch (Exception e1) { 
			try { method = from.getSuperclass().getDeclaredMethod(name, param_types); }
			catch (Exception e2) { e2.printStackTrace(); return null; };
		}
		
		if(method != null) {
			METHOD_CACHE.put(key, method);
			method.setAccessible(true);
		}
		
		return method;
	}
	
	public static Object invokeMethod(Method method, Object container, Object... params) {
		if(method == null) return null;
		try { return method.invoke(container, params); }
		catch (Exception e) { e.printStackTrace(); return null; }
	}
	
	public static Object invokeDeclaredMethod(Class<?> from, String name, Object container, Class<?>[] param_types, Object[] params) {
		Method method = getDeclaredMethod(from, name, param_types);
		return invokeMethod(method, container, params);
	}
	
	public static Object invokeDeclaredMethod(Object container, String name, String[] param_type_names, Object[] params) {
		Class<?>[] param_types = new Class<?>[param_type_names.length];
		for(int i = 0; i < param_type_names.length; i++) param_types[i] = getNMSClass(param_type_names[i]);
		return invokeDeclaredMethod(container.getClass(), name, container, param_types, params);
	}
	
	public static Object invokeDeclaredMethod(Object container, String name, Object... params) {
		Class<?>[] param_types = new Class<?>[params.length];
		for(int i = 0; i < params.length; i++) param_types[i] = params.getClass();
		return invokeDeclaredMethod(container.getClass(), name, container, param_types, params);
	}
	
	public static Object invokeDeclaredStaticMethod(Class<?> from, String name, Object... params) {
		Class<?>[] param_types = new Class<?>[params.length];
		for(int i = 0; i < params.length; i++) param_types[i] = params.getClass();
		return invokeDeclaredMethod(from, name, from, params, param_types);
	}
	
	public static Object invokeDeclaredStaticMethod(String from, String name, Object... params) {
		return invokeDeclaredStaticMethod(getNMSClass(from), name, params);
	}
	
}
