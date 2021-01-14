package me.felnstaren.rilib.ui.prompt;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

/*
 * Use Singleton to retain consistency across all plugins
 * using the Prompt interface to register prompts
 */
@SuppressWarnings("rawtypes")
public class PromptHandler {
	
	private static PromptHandler INSTANCE;
	
	public static void init(JavaPlugin plugin) {
		INSTANCE = new PromptHandler(plugin);
	}
	
	public static PromptHandler inst() {
		return INSTANCE;
	}
	
	
	
	private ArrayList<Prompt> prompts;
	private ArrayList<UUID> index_ids;
	private BukkitRunnable updator = new BukkitRunnable() {
		public void run() {
			for(int i = 0; i < prompts.size(); i++) {
				Prompt p = prompts.get(i);
				p.update();
				if(p.expired()) {
					close(i);
					i--;
				}
			}
		}
	};
	
	private PromptHandler(JavaPlugin plugin) {
		prompts = new ArrayList<Prompt>();
		index_ids = new ArrayList<UUID>();
		updator.runTaskTimer(plugin, 20, 20);
	}

	public void register(Prompt prompt) {
		prompt.send();
		prompts.add(prompt);
		index_ids.add(prompt.id);
	}
	
	public boolean isActive(UUID id) {
		return index_ids.contains(id) && !getPrompt(id).expired();
	}
	
	public Prompt getPrompt(UUID id) {
		return prompts.get(index(id));
	}
	
	public void close(UUID id) {
		if(!isActive(id)) return;
		close(index(id));
	}
	
	private int index(UUID id) {
		return index_ids.indexOf(id);
	}
	
	private void close(int index) {
		index_ids.remove(index);
		prompts.remove(index);
	}
	
}
