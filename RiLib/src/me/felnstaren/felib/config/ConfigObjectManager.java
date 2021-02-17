package me.felnstaren.felib.config;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import me.felnstaren.felib.data.BinarySearchable;

public class ConfigObjectManager extends BinarySearchable<ConfigObject> {

	protected Loader loader;
	
	public ConfigObjectManager(Loader loader) {
		super();
		this.loader = loader;
	}
	
	
	
	@Override
	public void add(ConfigObject o) {
		o.load(loader);
		super.add(o);
	}
	
	@Override
	public void remove(int search_value) {
		ConfigObject o = super.get(search_value);
		if(o == null) return;
		o.save(loader);
		super.remove(search_value);
	}
	
	
	
	public void saveAll() {
		for(ConfigObject o : values)
			o.save(loader);
	}
	
	public void reloadAll() {
		for(ConfigObject o : values)
			o.load(loader);
	}
	
	public void registerAutoSaveTask(JavaPlugin plugin, long millis) {
		new BukkitRunnable() {
			public void run() {
				saveAll();
			}
		}.runTaskTimer(plugin, millis, millis); //TODO: Should run async?
	}
	
}
