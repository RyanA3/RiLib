package me.felnstaren.felib.util;

import java.util.ArrayList;

public class PrimitiveUtil {

	public static final ArrayList<Class<?>> PRIMITIVES = new ArrayList<Class<?>>();
	public static final ArrayList<Class<?>> WRAPPERS = new ArrayList<Class<?>>();
	static {
		PRIMITIVES.add(byte.class); WRAPPERS.add(Byte.class);
		PRIMITIVES.add(int.class); WRAPPERS.add(Integer.class);
		PRIMITIVES.add(short.class); WRAPPERS.add(Short.class);
		PRIMITIVES.add(long.class); WRAPPERS.add(Long.class);
		PRIMITIVES.add(boolean.class); WRAPPERS.add(Boolean.class);
	}
	
	public static Class<?> getPrimitiveVersion(Class<?> wrapper) {
		if(!WRAPPERS.contains(wrapper)) return wrapper;
		else return PRIMITIVES.get(WRAPPERS.indexOf(wrapper));
	}
	
	public static Class<?> getWrapperVersion(Class<?> primitive) {
		if(!PRIMITIVES.contains(primitive)) return primitive;
		else return WRAPPERS.get(PRIMITIVES.indexOf(primitive));
	}
	
}
