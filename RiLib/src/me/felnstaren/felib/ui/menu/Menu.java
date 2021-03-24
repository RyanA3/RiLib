package me.felnstaren.felib.ui.menu;

import java.util.ArrayList;

import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.felnstaren.felib.util.ArrayUtil;
import other.bananapuncher714.nbt.NBTEditor;

public class Menu {

	protected Inventory inventory;

	public Menu(MenuSchematic schematic) {
		this.inventory = schematic.construct();
	}
	
	
	public void click(int slot, MenuSession session) {
		ItemStack clicked = inventory.getItem(slot);
		if(clicked == null) return;
		
		if(!NBTEditor.contains(clicked, "button")) return;
		String label = NBTEditor.getString(clicked, "button");
		
		MenuButton button = ButtonHandler.inst().getButton(label);
		if(button != null) button.execute(session, clicked);
	}
	
	public void close() {
		inventory.clear();
		HumanEntity[] viewers = ArrayUtil.arrayver(new ArrayList<HumanEntity>(inventory.getViewers()), HumanEntity.class);
		for(HumanEntity viewer : viewers)
			viewer.closeInventory();
	}
	
	public void open(Player player) {
		player.openInventory(inventory);
	}
	
}
