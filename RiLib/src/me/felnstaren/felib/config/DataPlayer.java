package me.felnstaren.felib.config;

import java.util.UUID;

import org.bukkit.entity.Player;

import me.felnstaren.felib.util.data.SearchObject;

public class DataPlayer extends ConfigObject {
	
	protected UUID uuid;
	
	public DataPlayer(UUID uuid, String template) {
		super("playerdata/" + uuid + ".yml", template, SearchObject.getIndexValue(uuid)); //Using UUID's clock sequence time as unique identifier
		this.uuid = uuid;
	}
	
	public DataPlayer(Player player, String template) {
		this(player.getUniqueId(), template);
	}

	
	
	public UUID getUniqueId() {
		return uuid;
	}

}
