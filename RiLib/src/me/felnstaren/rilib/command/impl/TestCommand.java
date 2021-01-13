package me.felnstaren.rilib.command.impl;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.felnstaren.rilib.chat.Messenger;
import me.felnstaren.rilib.particle.PacketParticle;
import me.felnstaren.rilib.particle.PacketParticleBlock;
import me.felnstaren.rilib.particle.PacketParticleType;

public class TestCommand implements CommandExecutor {

	private static Random rand = new Random();
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player player = (Player) sender;
		Location location = player.getLocation();
		
		int p = rand.nextInt(PacketParticleType.values().length);
		if(args.length > 0) p = Integer.parseInt(args[0]);
		
		PacketParticle particle;
		
		if(p == 3) {
			String block = "HONEY_BLOCK";
			if(args.length > 1) block = args[1].toUpperCase();
			
			particle = new PacketParticleBlock(1, 0, 1, 0.5f, block);
			Messenger.send(player, "#F55Spawning particle #999" + block);
		} else {
			PacketParticleType type = PacketParticleType.values()[p];
			particle = new PacketParticle(type, 0.5f, 0, 0.5f, 0);
			Messenger.send(player, "#F55Spawning particle #999" + type.name());
		}
		
		particle.send(player, location.getBlockX() + 0.5, location.getBlockY() + 1.5, location.getBlockZ() + 0.5);
		return true;
	}
	
}
