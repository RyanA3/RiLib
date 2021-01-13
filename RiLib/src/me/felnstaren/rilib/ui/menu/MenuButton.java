package me.felnstaren.rilib.ui.menu;

import org.bukkit.inventory.ItemStack;

public interface MenuButton {
	
	public void execute(MenuSession session, ItemStack clicked);

}
