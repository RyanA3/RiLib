package me.felnstaren.felib.ui.menu;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.felnstaren.felib.chat.Messenger;
import me.felnstaren.felib.util.ArrayUtil;
import other.bananapuncher714.nbt.NBTEditor;

public class Menu {

	protected Inventory inventory;
	protected MenuSchematic schematic;

	public Menu(MenuSchematic schematic) {
		this.inventory = schematic.construct();
		this.schematic = schematic;
	}
	
	
	public void click(int slot, MenuSession session) {
		ItemStack clicked = inventory.getItem(slot);
		if(clicked == null) return;
		
		if(!NBTEditor.contains(clicked, "button")) return;
		String label = NBTEditor.getString(clicked, "button");
		
		MenuButton button = ButtonHandler.inst().getButton(label);
		if(button != null) button.execute(session, clicked);
	}
	
	public void setTitle(String title) {
		title = Messenger.color(title);
		ItemStack[] contents = inventory.getContents();
		if(schematic.getType() != null) this.inventory = Bukkit.createInventory(null, schematic.getType(), title);
		else this.inventory = Bukkit.createInventory(null, schematic.getRows(), title);
		inventory.setContents(contents);
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
