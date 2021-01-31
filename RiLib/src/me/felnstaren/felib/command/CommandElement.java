package me.felnstaren.felib.command;

import org.bukkit.command.CommandSender;

public interface CommandElement {

	public boolean handle(CommandSender sender, String[] args, int current);
	
}
