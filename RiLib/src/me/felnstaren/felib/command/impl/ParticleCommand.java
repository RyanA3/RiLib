package me.felnstaren.felib.command.impl;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.felnstaren.felib.chat.Messenger;
import me.felnstaren.felib.packet.enums.PacketParticleType;
import me.felnstaren.felib.packet.particle.PacketParticle;
import me.felnstaren.felib.packet.particle.PacketParticleBlock;
import me.felnstaren.felib.packet.particle.PacketParticleDust;
import me.felnstaren.felib.packet.particle.PacketParticleItem;

public class ParticleCommand implements CommandExecutor {

	private static Random rand = new Random();
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player player = (Player) sender;
		Location location = player.getLocation();
		
		if(!player.hasPermission("rilib.test")) {
			Messenger.send(player, "#F22You do not have permission to #999rilib.test#F22!");
			return true;
		}
		
		int p = rand.nextInt(PacketParticleType.values().length);
		if(args.length > 0) p = Integer.parseInt(args[0]);
		if(p >= PacketParticleType.values().length) p = PacketParticleType.values().length - 1;
		
		PacketParticle particle;
		
		if(p == 3 || p == 23) {
			String block = "HONEY_BLOCK";
			if(args.length > 1) block = args[1].toUpperCase();
			
			particle = new PacketParticleBlock(PacketParticleType.values()[p], 1, 1, 0, 0, block);
			Messenger.send(player, "#F55Spawning Block-Based particle#999 " + block);
		} else if(p == 14) {
			float red = 0, green = 0, blue = 0, size = 4;
			if(args.length > 4) {
				red = Float.parseFloat(args[1]);
				green = Float.parseFloat(args[2]);
				blue = Float.parseFloat(args[3]);
				size = Float.parseFloat(args[4]);
			}
			
			particle = new PacketParticleDust(5, 1, 0, 0, red, green, blue, size);
			Messenger.send(player, "#F55Spawning Dust-Based particle #999(" + red + "," + green + "," + blue + ")*" + size);
		} else if(p == 32) {
			int item = 1;
			if(args.length > 1) item = Integer.parseInt(args[1]);
			
			particle = new PacketParticleItem(1, 1, 0, 0, item);
			Messenger.send(player, "#F55Spawning Item-Based particle#999 " + item);
		} else {
			PacketParticleType type = PacketParticleType.values()[p];
			particle = new PacketParticle(type, 1, 1, 0, 0);
			Messenger.send(player, "#F55Spawning particle#999 " + type.name());
		}
		
		particle.build(location.getBlockX() + 0.5, location.getBlockY() + 1.5, location.getBlockZ() + 0.5);
		particle.send(player);
		return true;
	}
	
}
