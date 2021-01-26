package me.felnstaren.rilib.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import org.bukkit.entity.Player;

import io.netty.channel.Channel;

public class Packeteer {

	/**
	 * Sends a packet to the specified player
	 * @param player The player the packet is to be sent to
	 * @param packet The packet to be sent to the player
	 * @return 
	 *  IF 0 success
	 *  IF 1 invocation error
	 *  IF 2 unknown error
	 */
	public static int sendClientPacket(Player player, Object packet) {
		try {
			//Reflector.METHOD_CACHE.get("sendPacket").invoke(getPlayerConnection(player), packet);
			getChannel(player).pipeline().writeAndFlush(packet);
			return 0;
		} catch (InvocationTargetException e) {
			return 1;
		} catch (Exception e) {
			return 2;
		}
	}
	
	/**
	 * Sends a packet to the server from the specified player
	 * @param player The player which the packet is from
	 * @param packet The packet to be sent to the server
	 * @return
	 */
	public static int sendServerPacket(Player player, Object packet) {
		try {
			getChannel(player).pipeline().fireChannelRead(packet);
			return 0;
		} catch (InvocationTargetException e) {
			return 1;
		} catch (Exception e) {
			return 2;
		}
	}

	
	
	
	/**
	 * Get the CraftPlayer PlayerConnection from a Player
	 * @param player Player to get connection of
	 * @return nms PlayerConnection
	 */
	public static Object getPlayerConnection(Player player) throws Exception {
		Object craft_player = Reflector.getNMSClass("CraftPlayer").cast(player);
		Object entity_player = Reflector.METHOD_CACHE.get("getHandle").invoke(craft_player);
		return Reflector.FIELD_CACHE.get("EntityPlayer").get(entity_player);
	}
	
	/**
	 * Get the NetworkManager from a PlayerConnection
	 * @param connection The PlayerConnection
	 * @return nms NetworkManager
	 */
	public static Object getNetworkManager(Object connection) throws Exception {
		Field netwm_field = Reflector.getNMSClass("PlayerConnection").getDeclaredField("networkManager");
		return netwm_field.get(connection);
	}
	
	public static Object getNetworkManager(Player player) throws Exception {
		return getNetworkManager(getPlayerConnection(player));
	}
	
	/**
	 * Get the netty Channel from a NetworkManager
	 * @param netwm The NetworkManager
	 * @return netty Channel
	 */
	public static Channel getChannel(Object netwm) throws Exception {
		return (Channel) Reflector.getNMSClass("NetworkManager").getDeclaredField("channel").get(netwm);
	}
	
	public static Channel getChannel(Player player) throws Exception {
		return getChannel(getNetworkManager(getPlayerConnection(player)));
	}
	
}
