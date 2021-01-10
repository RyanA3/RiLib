package me.felnstaren.rilib.menu;

import java.util.ArrayList;

import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryEvent;

public class MenuHandler {

	private ArrayList<MenuSession> sessions;
	
	public MenuHandler() {
		sessions = new ArrayList<MenuSession>();
	}
	
	
	
	@EventHandler
	public void onInteract(InventoryEvent event) {
		
	}
	
}
