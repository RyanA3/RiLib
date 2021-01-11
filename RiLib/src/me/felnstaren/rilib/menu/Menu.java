package me.felnstaren.rilib.menu;

import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import other.bananapuncher714.nbt.NBTEditor;

public class Menu {

	private Inventory inventory;

	public Menu(MenuSchematic schematic) {
		this.inventory = schematic.construct();
	}
	
	
	public void click(int slot, MenuSession session) {
		ItemStack clicked = inventory.getItem(slot);
		
		if(!NBTEditor.contains(clicked, "button")) return;
		String label = NBTEditor.getString(clicked, "button");
		
		ButtonHandler.inst().getButton(label).execute(session, clicked);
	}
	
	public void close() {
		inventory.clear();
		for(HumanEntity viewer : inventory.getViewers())
			viewer.closeInventory();
	}
	
}
