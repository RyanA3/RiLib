package me.felnstaren.felib.ui.prompt;

import org.bukkit.entity.Player;

import me.felnstaren.felib.chat.ClickComponent;

public abstract class ChatOptionPrompt extends ChatPrompt {

	protected String[] options;
	
	//Formattable message format: "Something something... #FFF[%option1] [&c%option2] #AABBCC[%option3#999]"
	public ChatOptionPrompt(Player player, int time, String formattable_message, String... options) {
		super(player, time, formattable_message);
		this.options = options;
		
		for(int i = 1, j = 1; i < message.size(); i++) {
			if(!message.getComponent(i).getText().startsWith("%")) continue;
			message.getComponent(i).replaceAll("%option" + j, options[j-1]);
			message.getComponent(i).setClickComponent(new ClickComponent("/prompt " + id + " " + options[j-1]));
			j++;
		}
		
	}
	
}
