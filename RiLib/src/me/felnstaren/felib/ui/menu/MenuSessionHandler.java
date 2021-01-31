package me.felnstaren.felib.ui.menu;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/*
 * Use Singleton to retain consistency across all plugins
 * using the MenuSessionHandler to register MenuSessions
 */
public class MenuSessionHandler implements Listener {
	
	private static MenuSessionHandler INSTANCE;
	
	public static void init(JavaPlugin plugin) {
		INSTANCE = new MenuSessionHandler();
		
		PluginManager pm = plugin.getServer().getPluginManager();
		pm.registerEvents(INSTANCE, plugin);
	}

	public static MenuSessionHandler inst() {
		return INSTANCE;
	}
	
	
	
	private ArrayList<MenuSession> sessions;
	private ArrayList<UUID> index_players;
	
	public MenuSessionHandler() {
		sessions = new ArrayList<MenuSession>();
		index_players = new ArrayList<UUID>();
	}
	
	
	
	@EventHandler
	public void onInteract(InventoryClickEvent event) {
		if(!(event.getWhoClicked() instanceof Player)) return;
		Player player = (Player) event.getWhoClicked();
		
		if(!isSession(player)) return;
		event.setCancelled(true);
		
		if(event.getAction() == InventoryAction.PICKUP_ALL)
			getSession(player).click(event.getSlot());
	}
	
	@EventHandler
	public void onClose(InventoryCloseEvent event) {
		if(!(event.getPlayer() instanceof Player)) return;
		Player player = (Player) event.getPlayer();
		
		if(!isSession(player)) return;
		
		closeSession(player);
	}
	
	
	
	public void startSession(Player player, Menu menu) {
		index_players.add(player.getUniqueId());
		sessions.add(new MenuSession(player, menu));
	}
	
	public void closeSession(Player player) {
		int index = index_players.indexOf(player.getUniqueId());
		index_players.remove(index);
		sessions.get(index).close();
		sessions.remove(index);
	}
	
	public boolean isSession(Player player) {
		return index_players.contains(player.getUniqueId());
	}
	
	public MenuSession getSession(Player player) {
		if(!isSession(player)) return null;
		return sessions.get(index_players.indexOf(player.getUniqueId()));
	}
	
}
