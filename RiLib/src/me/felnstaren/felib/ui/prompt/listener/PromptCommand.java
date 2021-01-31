package me.felnstaren.felib.ui.prompt.listener;

import java.util.UUID;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.felnstaren.felib.chat.Messenger;
import me.felnstaren.felib.ui.prompt.Prompt;
import me.felnstaren.felib.ui.prompt.PromptHandler;

public class PromptCommand implements CommandExecutor {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!(sender instanceof Player)) return true;
		Player player = (Player) sender;
		
		if(args.length < 2) {
			Messenger.send(player, "#F22Invalid Prompt Response");
			return true;
		}
		
		UUID prompt_id = null;
		try { prompt_id = UUID.fromString(args[0]); }
		catch(Exception e) { Messenger.send(player, "#F22Invalid Prompt ID"); return true; }
		String response = args[1];
		
		if(!PromptHandler.inst().isActive(prompt_id)) {
			Messenger.send(player, "#F22This prompt has expired!");
			return true;
		}
		
		Prompt prompt = PromptHandler.inst().getPrompt(prompt_id);
		prompt.callback(response);
		
		return true;
	}
	
}
