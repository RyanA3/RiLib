package me.felnstaren.felib.ui.menu;

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
	
	

	private ArrayList<String> index_labels;
	private ArrayList<MenuButton> buttons;
	
	private ButtonHandler() {
		index_labels = new ArrayList<String>();
		buttons = new ArrayList<MenuButton>();
	}
	
	
	
	public boolean isButton(String label) { 
		return index_labels.contains(label);
	}
	
	public MenuButton getButton(String label) {
		if(!isButton(label)) return null;
		return buttons.get(index_labels.indexOf(label));
	}
	
	public void register(MenuButton button, String label) {
		if(isButton(label)) return;
		buttons.add(button);
		index_labels.add(label);
	}
	
}
