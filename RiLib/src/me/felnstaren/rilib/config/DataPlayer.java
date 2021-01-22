package me.felnstaren.rilib.config;

import java.util.UUID;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public abstract class DataPlayer {
	
	private YamlConfiguration data;
	private String path;
	private UUID uuid;
	
	public DataPlayer(UUID uuid) {
		this.uuid = uuid;
		this.path = "playerdata/" + uuid + ".yml";
		load();
	}
	
	public DataPlayer(Player player) {
		this.uuid = player.getUniqueId();
		this.path = "playerdata/" + uuid + ".yml";
		load();
	}
	
	protected void finalize() throws Throwable {
		save();
	}
	
	
	protected abstract void load();
	public abstract void save();
	
	
	
	public void set(String key, Object value) {
		data.set(key, value);
	}
	
	
	
	public String getPath() {
		return path;
	}
	
	public UUID getUniqueId() {
		return uuid;
	}
	
	public YamlConfiguration getData() {
		return data;
	}
	
}
