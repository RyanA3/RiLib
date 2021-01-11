package me.felnstaren.rilib.menu;

import org.bukkit.inventory.ItemStack;

public interface MenuButton {
	
	public void execute(MenuSession session, ItemStack clicked);

}
