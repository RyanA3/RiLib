package me.felnstaren.felib.packet;

import java.lang.reflect.Method;

import org.bukkit.entity.Player;

import me.felnstaren.felib.reflect.Reflector;

@Deprecated
public class PacketPlayer {
	
	Object player_connection;

	public PacketPlayer(Player player) {
		Object craft_player = Reflector.cast(player, Reflector.getNMSClass("CraftPlayer"));
		Object entity_player = Reflector.invokeMethod(CRAFT_PLAYER_GET_HANDLE_METHOD, craft_player);
		this.player_connection = Reflector.getDeclaredFieldValue(entity_player, "playerConnection");
	}
	
	
	
	public void send(Object packet) {
		Reflector.invokeMethod(PLAYER_CONNECTION_SEND_PACKET_METHOD, player_connection, packet);
	}
	
	
	
	private static final Method CRAFT_PLAYER_GET_HANDLE_METHOD = Reflector.getDeclaredMethod(Reflector.getNMSClass("CraftPlayer"), "getHandle");
	private static final Method PLAYER_CONNECTION_SEND_PACKET_METHOD = Reflector.getDeclaredMethod(Reflector.getNMSClass("PlayerConnection"), "sendPacket", Reflector.getNMSClass("Packet"));
	
}
