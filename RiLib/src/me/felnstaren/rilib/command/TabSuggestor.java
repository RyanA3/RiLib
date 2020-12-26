package me.felnstaren.rilib.command;

import java.util.ArrayList;

public abstract class TabSuggestor {
	
	public TabSuggestor(String key) {
		this.key = key;
	}
	
	public String key;
	public abstract ArrayList<String> getSuggestions();
	
}
