package me.felnstaren.rilib.command.impl;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.felnstaren.rilib.chat.Messenger;
import me.felnstaren.rilib.ui.prompt.ChatOptionPrompt;
import me.felnstaren.rilib.ui.prompt.ChatPrompt;
import me.felnstaren.rilib.ui.prompt.PromptHandler;

public class TestCommand implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player player = (Player) sender;
		//ChatOptionPrompt prompt = new ChatOptionPrompt(player, 10, "#FD0Do you like gaming? #999[#0F0%option1#999] [#F00%option2#999]", "YES", "NO") {
		//	public void callback(String response) {
		//		Messenger.send(this.player, "#0F0You chose #999" + response + "#0F0!");
		//		expired = true;
		//	}
		//};
		
		ChatPrompt prompt = new ChatPrompt(player, 20, "#FD0What's your favorite game to game upon?") {
			public void callback(String response) {
				Messenger.send(this.player, "#0F0You said #999" + response + "#0F0!");
				expired = true;
			}
		};
		
		PromptHandler.inst().register(prompt);
		
		return true;
	}
	
}
