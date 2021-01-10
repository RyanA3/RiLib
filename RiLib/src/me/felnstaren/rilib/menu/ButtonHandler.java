package me.felnstaren.rilib.menu;

import java.util.ArrayList;

public class ButtonHandler {

	private ArrayList<String> labels_index;
	private ArrayList<MenuButton> buttons;
	
	public ButtonHandler() {
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
	
	public void registerButton(MenuButton button, String label) {
		if(isButton(label)) return;
		buttons.add(button);
		labels_index.add(label);
	}
	
}
