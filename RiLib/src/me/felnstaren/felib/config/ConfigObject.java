package me.felnstaren.felib.config;

import org.bukkit.configuration.file.YamlConfiguration;

import me.felnstaren.felib.data.SearchObject;

public class ConfigObject implements SearchObject {

	protected YamlConfiguration config;
	protected String template;
	protected String path;
	
	protected int access_key;
	
	public ConfigObject(String path, String template, int access_key) {
		this.path = path;
		this.template = template;
		this.access_key = access_key;
	}
	
	public ConfigObject(String path, int access_key) {
		this(path, null, access_key);
	}
	
	
	
	protected void save(Loader loader) {
		loader.save(path, config);
	}
	
	protected void load(Loader loader) {
		this.config = loader.readConfig(path, template);
	}
	
	
	
	public void set(String key, Object value) {
		config.set(key, value);
	}
	
	
	
	public int searchValue() {
		return access_key;
	}
	
	
	
	public String getPath() {
		return path;
	}
	
	public String getTemplate() {
		return template;
	}
	
	public YamlConfiguration getConfig() {
		return config;
	}
	
}
