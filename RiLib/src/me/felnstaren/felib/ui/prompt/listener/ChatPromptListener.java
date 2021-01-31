package me.felnstaren.felib.ui.prompt.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.felnstaren.felib.ui.prompt.ChatOptionPrompt;
import me.felnstaren.felib.ui.prompt.Prompt;
import me.felnstaren.felib.ui.prompt.PromptHandler;

public class ChatPromptListener implements Listener {

	@SuppressWarnings({"unchecked", "rawtypes"})
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		Prompt prompt = PromptHandler.inst().getPrompt(player);
		if(prompt == null) return;
		if(prompt instanceof ChatOptionPrompt) return;
		
		prompt.callback(event.getMessage());
		event.setCancelled(true);
	}
	
}
