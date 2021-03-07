package me.felnstaren.felib.player;

import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import me.felnstaren.felib.config.Loader;
import me.felnstaren.felib.util.data.BinarySearchable;
import me.felnstaren.felib.util.data.SearchObject;

public class PlayerNameIDTransposer extends BinarySearchable<PlayerNameID> implements Listener {

	private Loader loader;
	
	private String path;
	//private String template;
	
	public PlayerNameIDTransposer(JavaPlugin plugin, Loader loader, String path, String template) {
		this.loader = loader;
		this.path = path;
		//this.template = template;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		
		//Load name ids from file on construction
		String data = loader.readString(path, template);
		String[] nameids = data.split(",");
		for(String nameid : nameids)
			super.add(new PlayerNameID(nameid));
	}
	
	public PlayerNameIDTransposer(JavaPlugin plugin, Loader loader, String path) {
		this(plugin, loader, path, null);
	}
	
	
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		PlayerNameID nameid = super.get(SearchObject.getIndexValue(player.getUniqueId()));
		
		if(nameid == null) //Add them to the registry if they arent in it
			super.add(new PlayerNameID(player.getUniqueId(), player.getName())); 
		else //Otherwise, update their name incase they changed it
			nameid.setName(player.getName());
	}
	
	
	
	public void save() {
		String data = "";
		for(int i = 0; i < super.values.size(); i++) {
			if(i > 0) data += ",";
			data += super.values.get(i).data();
		}
		
		loader.save(path, data);
	}
	
	public void registerAutoSaveTask(JavaPlugin plugin, long millis) {
		new BukkitRunnable() {
			public void run() {
				save();
			}
		}.runTaskTimer(plugin, millis, millis); //TODO: Should run async?
	}
	
	
	
	//Binary Search
	public String getName(UUID id) {
		return super.get(SearchObject.getIndexValue(id)).getName();
	}
	
	//Linear Search :(
	public UUID getID(String name) {
		for(PlayerNameID nameid : super.values)
			if(nameid.getName().equals(name)) return nameid.getID();
		return null;
	}

}
