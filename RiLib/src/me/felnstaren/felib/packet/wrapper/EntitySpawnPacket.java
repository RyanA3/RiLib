package me.felnstaren.felib.packet.wrapper;

import java.lang.reflect.Constructor;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.util.Vector;

import me.felnstaren.felib.packet.enums.PacketEntityType;
import me.felnstaren.felib.reflect.Reflector;
import me.felnstaren.felib.util.math.Maths;

public class EntitySpawnPacket extends PacketWrapper {

	private static final Constructor<?> ENTITY_SPAWN_PACKET_CONSTRUCTOR = Reflector.getConstructor("PacketPlayOutSpawnEntityLiving");
	
	public EntitySpawnPacket(Object packet) {
		super(packet);
	}
	
	public EntitySpawnPacket(int entity_id, UUID entity_uuid, PacketEntityType entity_type, Location location, Vector velocity) {
		super(Reflector.newInstanceOf(ENTITY_SPAWN_PACKET_CONSTRUCTOR));
		set("a", entity_id);
		set("b", entity_uuid);
		set("c", entity_type.ordinal());
		set("d", location.getX());
		set("e", location.getY());
		set("f", location.getZ());
		set("g", (int) Maths.clamp(velocity.getX(), -3.9D, 3.9D));
		set("h", (int) Maths.clamp(velocity.getY(), -3.9D, 3.9D));
		set("i", (int) Maths.clamp(velocity.getZ(), -3.9D, 3.9D));
		set("j", (byte) ((int) location.getYaw() * 256.0F / 360.0F ));
		set("k", (byte) ((int) location.getPitch() * 256.0F / 360.0F));
		set("l", (byte) ((int) location.getYaw() * 256.0F / 360.0F)); // <-- Seems to just be a duplicate of yaw that doesn't change
	}
	
}
