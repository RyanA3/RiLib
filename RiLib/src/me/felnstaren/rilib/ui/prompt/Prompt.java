package me.felnstaren.rilib.ui.prompt;

import java.util.UUID;

import org.bukkit.entity.Player;

public abstract class Prompt<T extends Object> {

	protected UUID id;
	protected Player player;
	protected int time;
	protected boolean expired = false;
	
	public Prompt(Player player, int time) {
		this.player = player;
		this.time = time;
		this.id = UUID.randomUUID();
	}
	
	
	
	public void update() {
		time--;
		if(time == 0) timeout();
	}
	
	public abstract void send();
	public abstract void timeout();
	public abstract void callback(T response);
	
	
	
	public UUID getID() {
		return id;
	}
	
	public int getTime() {
		return time;
	}
	
	public boolean expired() {
		return time <= 0 || expired;
	}
	
	public Player getPlayer() {
		return player;
	}
	
}
