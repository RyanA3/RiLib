package me.felnstaren.felib.ui.menu;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class MenuSchematic {
	
	protected static final HashMap<InventoryType, Integer> WIDTHS = new HashMap<InventoryType, Integer>();
	static {
		WIDTHS.put(InventoryType.ANVIL, 3);
		WIDTHS.put(InventoryType.BARREL, 9);
		WIDTHS.put(InventoryType.CHEST, 9);
		WIDTHS.put(InventoryType.CRAFTING, 3);
		WIDTHS.put(InventoryType.CREATIVE, 9);
		WIDTHS.put(InventoryType.DISPENSER, 3);
		WIDTHS.put(InventoryType.DROPPER, 3);
		WIDTHS.put(InventoryType.ENDER_CHEST, 9);
		WIDTHS.put(InventoryType.FURNACE, 1);
		WIDTHS.put(InventoryType.HOPPER, 5);
		WIDTHS.put(InventoryType.SHULKER_BOX, 9);
	}
	
	
	
	private ItemSchematic[] items;
	
	private InventoryType type;
	private int rows;
	
	private String name;
	
	public MenuSchematic(InventoryType type, String name, ItemSchematic... items) {
		this.type = type;
		this.name = name;
		this.items = items;
	}
	
	public MenuSchematic(int rows, String name, ItemSchematic... items) {
		this.rows = rows;
		this.name = name;
		this.items = items;
	}
	
	
	
	public Inventory construct() {
		Inventory inventory;
		int width = 9;
		
		if(type == null) inventory = Bukkit.createInventory(null, rows, name);
		else {
			inventory = Bukkit.createInventory(null, type, name);
			width = WIDTHS.get(type);
		}
		
		ItemStack[] contents = inventory.getContents();
		for(ItemSchematic item : items)
			contents[item.getX() + (item.getY() * width) - 1] = item.getStack();
		inventory.setContents(contents);
		
		return inventory;
	}
	
	
	
	public String getName() {
		return name;
	}
	
	public int getRows() {
		return rows;
	}
	
	public InventoryType getType() {
		return type;
	}
	
	public ItemSchematic[] getItems() {
		return items;
	}

}
