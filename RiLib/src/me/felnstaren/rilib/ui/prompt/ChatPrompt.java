package me.felnstaren.rilib.ui.prompt;

import org.bukkit.entity.Player;

import me.felnstaren.rilib.chat.Message;
import me.felnstaren.rilib.chat.Messenger;

public abstract class ChatPrompt extends Prompt<String> {
	
	protected Message message;

	public ChatPrompt(Player player, int time, String formattable_message) {
		super(player, time);
		this.message = Messenger.colorWithJson(formattable_message);
	}


	public void send() {
		Messenger.send(player, message);
	}

	public void timeout() {
		if(expired) return;
		expired = true;
		Messenger.send(player, "#F22You failed to respond in time!");
	}
	

}
