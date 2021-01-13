package me.felnstaren.rilib.ui.prompt;

import java.util.ArrayList;
import java.util.UUID;

/*
 * Use Singleton to retain consistency across all plugins
 * using the Prompt interface to register prompts
 */
public class PromptHandler {
	
	private static PromptHandler INSTANCE;
	
	public static void init() {
		INSTANCE = new PromptHandler();
	}
	
	public static PromptHandler inst() {
		return INSTANCE;
	}
	
	
	
	private ArrayList<Prompt> prompts;
	private ArrayList<UUID> index_ids;
	
	private PromptHandler() {
		prompts = new ArrayList<Prompt>();
		index_ids = new ArrayList<UUID>();
	}

	/**
	 * PromptHandler
	 *  - <UUID : Prompt>...
	 *  - Pass events to prompts
	 *  - time out prompts
	 *  
	 *  
	 * Prompt <Interface>
	 *  - void timeout(), void send(), abstract complete(Object response)
	 *  - 
	 *  ChatPrompt extends Prompt
	 *   - 
	 *   
	 *  TextPrompt
	 *   - 
	 *  SingleSelectPrompt
	 *   - 
	 *  MultiSelectPrompt
	 *   - 
	 * 
	 * 
	 */
	
}
