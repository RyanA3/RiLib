package me.felnstaren.felib.item.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class InventoryEditor {
	
	public static int countItems(Inventory inventory, ItemStack item) {
		ItemStack[] contents = inventory.getContents();
		
		int count = 0;
		for(int i = 0; i < contents.length; i++) {
			if(contents[i] == null) continue;
			if(isSimilar(contents[i], item)) count += contents[i].getAmount();
		}
		
		return count;
	}
	
	public static void remove(Inventory inventory, ItemStack remove, int count) {
		ItemStack[] contents = inventory.getContents();
		
		for(int i = 0; i < contents.length; i++) {
			if(count <= 0) break;
			if(contents[i] == null || !isSimilar(contents[i], remove)) continue;
			
			int stack_size = contents[i].getAmount();
			
			if(stack_size > count) {
				contents[i].setAmount(stack_size - count);
				count = 0;
			} else {
				contents[i].setAmount(0);
				count -= stack_size;
			}
		}
		
		inventory.setContents(contents);
	}

	public static void add(Inventory inventory, ItemStack add, int count, boolean drop_if_full) {
		ItemStack[] contents = inventory.getContents();
		
		for(int i = 0; i < contents.length; i++) {
			if(count <= 0) break; 
			if(contents[i] != null && !isSimilar(contents[i], add)) continue;
			
			int max_stack_size = add.getMaxStackSize();
			int stack_size = contents[i] == null ? 0 : contents[i].getAmount();
			int open = contents[i] == null ? max_stack_size : max_stack_size - contents[i].getAmount();
			if(contents[i] == null) contents[i] = add.clone();
			
			if(open > count) {
				contents[i].setAmount(stack_size + count);
				count = 0;
			} else {
				contents[i].setAmount(max_stack_size);
				count -= open;
			}
		}
		
		inventory.setContents(contents);
	
		if(count > 0 && drop_if_full) 
			dropItemTask(add, count, inventory.getLocation(), (JavaPlugin) Bukkit.getServer().getPluginManager().getPlugin("FeLib"));
		
	}
	
	public static boolean isSimilar(ItemStack a, ItemStack b) {
		ItemStack aa = a.clone();
		ItemStack bb = b.clone();
		aa.setAmount(1); bb.setAmount(1);
		return aa.isSimilar(bb);
	}
	
	
	
	public static void dropItemTask(ItemStack what, int count, Location location, JavaPlugin plugin) {
		new BukkitRunnable() {
			public void run() {
				int max_stack_size = what.getMaxStackSize();
				ItemStack drop = what.clone(); 
					
				int remainder = count % max_stack_size;
				if(remainder != 0) {
					drop.setAmount(remainder);
					location.getWorld().dropItem(location, drop);
				}
				
				drop.setAmount(max_stack_size);
				for(int i = 0; i < count / max_stack_size; i++)
					location.getWorld().dropItem(location, drop);
			}
		}.runTask(plugin);
	}
	
}
