package me.felnstaren.rilib.ui.menu;

import org.bukkit.inventory.ItemStack;

public class ItemSchematic {
	
	private int x, y;
	private ItemStack stack;
	
	public ItemSchematic(ItemStack stack, int x, int y) {
		this.stack = stack;
		this.x = x;
		this.y = y;
	};
	
	
	
	public ItemStack getStack() {
		return stack;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

}
