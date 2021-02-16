package me.felnstaren.felib.packet.wrapper;

import java.lang.reflect.Constructor;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import me.felnstaren.felib.reflect.Reflector;

public class EntityNamedSpawnPacket extends PacketWrapper {

	private static final Constructor<?> ENTITY_NAMED_SPAWN_PACKET_CONSTRUCTOR = Reflector.getConstructor("PacketPlayOutNamedEntitySpawn");
	
	public EntityNamedSpawnPacket(Object packet) {
		super(packet);
	}
	
	public EntityNamedSpawnPacket(int entity_id, UUID entity_uuid, Location location) {
		super(Reflector.newInstanceOf(ENTITY_NAMED_SPAWN_PACKET_CONSTRUCTOR));
		set("a", entity_id);
		set("b", entity_uuid);
		set("c", location.getX());
		set("d", location.getY());
		set("e", location.getZ());
		set("f", (byte) ((int) location.getYaw() * 256.0D / 360.0D));
		set("g", (byte) ((int) location.getPitch() * 256.0D / 360.0D));
	}
	
	public EntityNamedSpawnPacket(boolean indicator, Player player) {
		this(player.getEntityId(), player.getUniqueId(), player.getLocation());
	}
	
	
	
	public int getEntityId() {
		return (int) get("a");
	}
	
	public UUID getUniqueId() {
		return (UUID) get("b");
	}
	
}
