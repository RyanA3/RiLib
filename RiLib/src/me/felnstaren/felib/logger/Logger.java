package me.felnstaren.felib.logger;

import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;

import me.felnstaren.felib.chat.Messenger;

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
	
	
	public void stream(String message) { this.log(Level.STREAM,  message); }
	public void debug(String message)  { this.log(Level.DEBUG,   message); }
	public void info(String message)   { this.log(Level.INFO,    message); }
	public void warn(String message)   { this.log(Level.WARNING, message); }
	public void severe(String message) { this.log(Level.SEVERE,  message); }
	public void fatal(String message)  { this.log(Level.FATAL,   message); }
	
	
	public void setPriority(Level priority) {
		this.priority = priority;
	}
	
}
