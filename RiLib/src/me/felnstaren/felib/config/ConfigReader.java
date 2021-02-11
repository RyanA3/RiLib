package me.felnstaren.felib.config;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import me.felnstaren.felib.util.ArrayUtil;

public class ConfigReader {
	
	private static final HashMap<Class<? extends ConfigurationSectionObject>, Constructor<? extends ConfigurationSectionObject>> CONSTRUCTORS = new HashMap<Class<? extends ConfigurationSectionObject>, Constructor<? extends ConfigurationSectionObject>>();
	
	public static ArrayList<UUID> readUUIDList(FileConfiguration config, String key) {
		ArrayList<UUID> ids = new ArrayList<UUID>();
		String[] unparsed_ids = ArrayUtil.stringver(config.getStringList(key).toArray());
		for(int i = 0; i < unparsed_ids.length; i++)
			ids.add(UUID.fromString(unparsed_ids[i]));
		return ids;
	}
	
	public static ConfigurationSectionObject readSectionObject(FileConfiguration config, String key, ConfigurationSectionObject template) {
		return template.template().load(config.getConfigurationSection(key));
	}
	
	public static <T extends ConfigurationSectionObject> ArrayList<T> readSectionInSectionObjects(FileConfiguration config, String key, Class<T> type /*,ConfigurationSectionObject template*/) {
		ConfigurationSection super_section = config.getConfigurationSection(key);
		String[] sub_section_keys = ArrayUtil.stringver(super_section.getKeys(false).toArray());
		
		ArrayList<T> objects = new ArrayList<T>();
		
		Constructor<T> constructor = null;
		if(CONSTRUCTORS.containsKey(type)) constructor = (Constructor<T>) CONSTRUCTORS.get(type);
		else {
			try { constructor = type.getConstructor(ConfigurationSection.class); }
			catch (Exception e) { e.printStackTrace(); }
			CONSTRUCTORS.put(type, constructor);
		}
		if(constructor == null) return objects;
		
		for(int i = 0; i < sub_section_keys.length; i++) {
			try { objects.add(constructor.newInstance(super_section.getConfigurationSection(sub_section_keys[i]))); }
			catch (Exception e) { e.printStackTrace(); break; }
		}
		
		return objects;
	}

}
