package me.felnstaren.felib.player;

import java.util.UUID;

import me.felnstaren.felib.util.data.SearchObject;

public class PlayerNameID implements SearchObject {

	private UUID id;
	private String name;
	
	public PlayerNameID(UUID id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public PlayerNameID(String data) {
		String[] values = data.split("\\.");
		this.id = UUID.fromString(values[0]);
		this.name = values[1];
	}
	
	
	
	public String getName() {
		return name;
	}
	
	public UUID getID() {
		return id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	//For storing data
	public String data() {
		return id.toString() + "." + name;
	}
	

	
	public int searchValue() {
		return SearchObject.getIndexValue(id);
	}

}
