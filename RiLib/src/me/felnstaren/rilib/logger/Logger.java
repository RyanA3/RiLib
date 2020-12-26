package me.felnstaren.rilib.logger;

import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;

import me.felnstaren.rilib.chat.Messenger;

public class Logger {
	
	private ConsoleCommandSender console;
	private String label;
	private Level priority = Level.DEBUG;
	
	public Logger(ConsoleCommandSender console, String label) {
		this.console = console;
		this.label = label;
	}
	
	public void log(Level level, String message) {
		if(level.weight < priority.weight) return;
		
		String prefix = ChatColor.AQUA + label + "." + level.color + level.name();
		String divider = ChatColor.AQUA + " >> " + ChatColor.GRAY;
		
		console.sendMessage(prefix + divider + Messenger.color(message));
	}
	
	public void setPriority(Level priority) {
		this.priority = priority;
	}
	
}
