package me.felnstaren.felib.ui.menu;

import org.bukkit.inventory.ItemStack;

public interface MenuButton {
	
	public void execute(MenuSession session, ItemStack clicked);

}