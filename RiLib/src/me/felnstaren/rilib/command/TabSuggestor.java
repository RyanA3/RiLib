package me.felnstaren.rilib.command;

import java.util.ArrayList;

import org.bukkit.command.CommandSender;

public abstract class TabSuggestor {
	
	public TabSuggestor(String key) {
		this.key = key;
	}
	
	public String key;
	public abstract ArrayList<String> getSuggestions(CommandSender sender, String[] args, int current);
	
}
