package me.felnstaren.felib.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;

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
			METHOD_CACHE.put("sendPacket",   getNMSClass("PlayerConnection")  .getDeclaredMethod("sendPacket", getNMSClass("Packet")));
			METHOD_CACHE.put("getHandle",    getNMSClass("CraftPlayer")       .getDeclaredMethod("getHandle"));
			METHOD_CACHE.put("getServer",    getNMSClass("MinecraftServer")   .getDeclaredMethod("getServer"));
			METHOD_CACHE.put("getById",      getNMSClass("Item")              .getMethod        ("getById", int.class));
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		CONSTRUCTOR_CACHE = new HashMap<String, Constructor<?>>();
		try {
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
			FIELD_CACHE.put("EntityPlayer", getNMSClass("EntityPlayer").getField("playerConnection"));
			FIELD_CACHE.put("MinecraftServer", getNMSClass("MinecraftServer").getField("recentTps"));
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
	
	public static Object getDeclaredField(Object from, String field) {
		try {
			Field f = from.getClass().getDeclaredField(field);
			f.setAccessible(true);
			return f.get(from);
		} catch (Exception e) { e.printStackTrace(); }
		return null; 
	}
	
	public static Object getDeclaredField(Object from, String... field_paths) {
		try {
			Field field = from.getClass().getField(field_paths[0]);
			field.setAccessible(true);
			Object stored = from;
			for(int i = 1; i < field_paths.length; i++) {
				stored = field.get(stored);
				field = stored.getClass().getDeclaredField(field_paths[i]);
				field.setAccessible(true);
			}
			return field.get(stored);
		} catch (Exception e) { e.printStackTrace(); }
		return null;
	}
	
	public static void setDeclaredField(Object from, String field, Object value) {
		try {
			Field f = from.getClass().getDeclaredField(field);
			f.setAccessible(true);
			f.set(from, value);
		} catch (Exception e) { e.printStackTrace(); }
	}
	
	public static void setDeclaredField(Object from, Object value, String... field_paths) {
		try {
			Field field = from.getClass().getField(field_paths[0]);
			field.setAccessible(true);
			Object stored = from;
			for(int i = 1; i < field_paths.length; i++) {
				stored = field.get(stored);
				field = stored.getClass().getDeclaredField(field_paths[i]);
				field.setAccessible(true);
			}
			field.set(stored, value);
		} catch (Exception e) { e.printStackTrace(); }
	}
	
	public static Object newInstanceOf(String nmsclass, Object... params) {
		Class<?> nmsclazz = getNMSClass(nmsclass);
		
		Class<?>[] paramclasses = new Class<?>[params.length];
		for(int i = 0; i < params.length; i++) paramclasses[i] = params[i].getClass();
		
		Constructor<?> constructor = null;
		if(CONSTRUCTOR_CACHE.containsKey(nmsclass)) constructor = CONSTRUCTOR_CACHE.get(nmsclass);
		else {
			try {
				constructor = nmsclazz.getConstructor(paramclasses);
				CONSTRUCTOR_CACHE.put(nmsclass, constructor);
			} catch (Exception e) { e.printStackTrace(); }
		}
		if(constructor == null) return null;
		
		try { return constructor.newInstance(params); }
		catch(Exception e) { e.printStackTrace(); return null; }
	}
	
}
