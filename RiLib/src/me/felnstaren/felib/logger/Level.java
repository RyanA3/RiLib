package me.felnstaren.felib.logger;

import org.bukkit.ChatColor;

public enum Level {

	FATAL(ChatColor.RED, 4),
	SEVERE(ChatColor.DARK_RED, 3),
	WARNING(ChatColor.YELLOW, 2),
	INFO(ChatColor.WHITE, 1),
	DEBUG(ChatColor.GRAY, 0);
	
	public final ChatColor color;
	public final int weight;
	
	private Level(ChatColor color, int weight) {
		this.color = color;
		this.weight = weight;
	}
	
}
