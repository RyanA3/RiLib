package me.felnstaren.felib.ui.progress;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.entity.Player;

import me.felnstaren.felib.packet.PacketPlayer;

public class PlayerProgressBar extends ProgressBar {

	protected ArrayList<PacketPlayer> players;
 	
	public PlayerProgressBar(int max, int value, int increment, PacketPlayer... players) {
		super(max, value, increment);
		this.players = (ArrayList<PacketPlayer>) Arrays.asList(players);
	}
	
	public PlayerProgressBar(int max, int value, int increment) {
		this(max, value, increment, new PacketPlayer[0]);
	}
	
	
	
	public void addPlayer(Player player) {
		players.add(new PacketPlayer(player));
	}

}
