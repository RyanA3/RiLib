package me.felnstaren.rilib.ui.menu;

import org.bukkit.entity.Player;

public class MenuSession {

	private Player player;
	private Menu menu;
	
	protected MenuSession(Player player, Menu menu) {
		this.player = player;
		this.menu = menu;
	}
	
	
	
	protected void click(int slot) {
		menu.click(slot, this);
	}
	
	public void close() {
		menu.close();
	}
	
	public void swapMenus(Menu menu) {
		this.menu.close();
		this.menu = menu;
		this.menu.open(player);
	}
	
	
	
	public Player getPlayer() {
		return player;
	}
	
	public Menu getMenu() {
		return menu;
	}
	
}
