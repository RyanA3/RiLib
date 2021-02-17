package me.felnstaren.felib.command.impl;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TestCommand implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		//Player player = (Player) sender;
		
		/*ChatPrompt prompt = new ChatPrompt(player, 20, "#FD0What's your favorite game to game upon?") {
			public void callback(String response) {
				//Messenger.send(this.player, "#0F0You said #999" + response + "#0F0!");
				Message parsed_text = Messenger.parse(response);
				player.sendMessage(parsed_text.build());
				Messenger.sendChatPacket(player, parsed_text.build());
				expired = true;
			}
		};
		
		PromptHandler.inst().register(prompt);*/
		//PacketPlayer pp = new PacketPlayer(player);
		//pp.sendEffect(player.getEntityId(), "BLINDNESS", -1, 32740, false);
		
		return true;
	}
	
}
