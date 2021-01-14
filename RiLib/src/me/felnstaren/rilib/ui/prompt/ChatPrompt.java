package me.felnstaren.rilib.ui.prompt;

import org.bukkit.entity.Player;

import me.felnstaren.rilib.chat.ClickComponent;
import me.felnstaren.rilib.chat.Message;
import me.felnstaren.rilib.chat.Messenger;

public abstract class ChatPrompt extends Prompt<String> {

	protected Message message;
	protected String[] options;
	
	//Formattable message format: "Something something... %#FFF|option1| %&c<option2> %#AABBCC[option3]"
	public ChatPrompt(Player player, int time, String formattable_message, String... options) {
		super(player, time);
		this.message = Messenger.colorWithJson(formattable_message);
		this.options = options;
		
		//String[] infos = new String[options.length];
		for(int i = 1, j = 1; i < message.size(); i++) {
			if(!message.getComponent(i).getText().startsWith("%")) continue;
			message.getComponent(i).replaceAll("%option" + j, options[j-1]);
			message.getComponent(i).setClickComponent(new ClickComponent("/prompt " + id + " " + options[j-1]));
			j++;
		}
		
		//for(int i = 1; i < infos.length; i++)
		//	message.addComponent(new TextComponent(infos[i].replace("option" + i, options[i-1])).setClickComponent(new ClickComponent("/prompt " + id + " " + options[i-1])));
	}

	
	
	public void send() {
		//RiLib.LOGGER.log(Level.DEBUG, message.build());
		Messenger.send(player, message);
	}
	
	public void timeout() {
		if(expired) return;
		expired = true;
		Messenger.send(player, "#F22You failed to respond in time!");
	}
	
}
