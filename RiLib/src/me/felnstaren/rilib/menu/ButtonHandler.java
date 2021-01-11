package me.felnstaren.rilib.menu;

import java.util.ArrayList;

/*
 * Use Singleton to retain consistency across all plugins
 * using the ItemRegistry to register custom items
 */
public class ButtonHandler {
	
	private static ButtonHandler INSTANCE;
	
	public static void init() {
		INSTANCE = new ButtonHandler();
	}
	
	public static ButtonHandler inst() {
		return INSTANCE;
	}
	
	

	private ArrayList<String> labels_index;
	private ArrayList<MenuButton> buttons;
	
	private ButtonHandler() {
		labels_index = new ArrayList<String>();
		buttons = new ArrayList<MenuButton>();
	}
	
	
	
	public boolean isButton(String label) { 
		return labels_index.contains(label);
	}
	
	public MenuButton getButton(String label) {
		if(!isButton(label)) return null;
		return buttons.get(labels_index.indexOf(label));
	}
	
	public void register(MenuButton button, String label) {
		if(isButton(label)) return;
		buttons.add(button);
		labels_index.add(label);
	}
	
}
