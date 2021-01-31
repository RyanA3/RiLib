package me.felnstaren.felib.ui.progress;

import java.util.ArrayList;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

/*
 * Use Singleton to retain consistency across all plugins
 * using the MenuSessionHandler to register MenuSessions
 */
public class ProgressBarHandler {
	
	private static ProgressBarHandler INSTANCE;
	
	public static void init(JavaPlugin plugin) {
		INSTANCE = new ProgressBarHandler(plugin);
	}

	public static ProgressBarHandler inst() {
		return INSTANCE;
	}
	
	
	
	private ArrayList<ProgressBar> bars;
	private BukkitRunnable updater;
	
	public ProgressBarHandler(JavaPlugin plugin) {
		this.bars = new ArrayList<ProgressBar>();
		this.updater = new BukkitRunnable() {
			public void run() {
				
			}
		};
		
		updater.runTaskTimer(plugin, 100, 10);
	}

}
