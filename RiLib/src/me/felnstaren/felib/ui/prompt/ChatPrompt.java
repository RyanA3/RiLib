package me.felnstaren.felib.ui.prompt;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.felnstaren.felib.chat.Message;
import me.felnstaren.felib.chat.Messenger;

public abstract class ChatPrompt extends Prompt<String> {
	
	protected Message message;

	public ChatPrompt(Player player, int time, String formattable_message) {
		super(player, time);
		this.message = Messenger.colorWithJson(formattable_message);
	}
	
	public ChatPrompt(Player player, int time, Message message) {
		super(player, time);
		this.message = message;
	}


	public void send() {
		Bukkit.broadcastMessage("Send:\n" + message.build());
		Messenger.sendChatPacket(player, message.build());
		
	}

	public void timeout() {
		if(expired) return;
		expired = true;
		Messenger.send(player, "#F22You failed to respond in time!");
	}
	

}
