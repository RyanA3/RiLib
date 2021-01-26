package me.felnstaren.rilib.packet;

import java.lang.reflect.Field;

import org.bukkit.entity.Player;

import me.felnstaren.rilib.RiLib;
import me.felnstaren.rilib.logger.Level;
import me.felnstaren.rilib.reflect.Reflector;

public class PacketPlayer {
	
	Object player_connection;

	public PacketPlayer(Player player) {
		try {
			Object craft_player = Reflector.getNMSClass("CraftPlayer").cast(player);
			Object entity_player = Reflector.METHOD_CACHE.get("getHandle").invoke(craft_player);
			this.player_connection = Reflector.FIELD_CACHE.get("EntityPlayer").get(entity_player);
		} catch (Exception e) {
			e.printStackTrace();
			RiLib.LOGGER.log(Level.WARNING, "ERROR - GET PLAYER CONNECTION - " + player.getName());
		}
	}
	
	
	
	public void send(Object packet) {
		try {
			Reflector.METHOD_CACHE.get("sendPacket").invoke(player_connection, packet);
		} catch (Exception e) {
			e.printStackTrace();
			RiLib.LOGGER.log(Level.WARNING, "ERROR - SEND PACKET TO PLAYER");
		}
	}
	
	public void sendEffect(int entity_id, String effect_name, int amplification, int duration, boolean show_particles, boolean ambient, boolean show_icon) {
		try {
			Field effect_type_field = Reflector.getNMSClass("MobEffects").getField(effect_name);
			Object effect_type = effect_type_field.get(Reflector.getNMSClass("MobEffects"));
			Object effect = Reflector.CONSTRUCTOR_CACHE.get("MobEffect").newInstance(effect_type, duration, amplification, ambient, show_particles, show_icon);
			Object packet = Reflector.CONSTRUCTOR_CACHE.get("PacketPlayOutEntityEffect").newInstance(entity_id, effect);
			send(packet);
		} catch (Exception e) {
			//eh
			e.printStackTrace();
		}
	}
	
	public void sendEffect(int entity_id, String effect_name, int amplification, int duration, boolean show_particles) {
		sendEffect(entity_id, effect_name, amplification, duration, show_particles, false, show_particles);
	}
	
	public void sendMetadata(int entity_id) {
		
	}
	
}
