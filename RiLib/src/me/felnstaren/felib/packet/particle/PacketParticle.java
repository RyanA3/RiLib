package me.felnstaren.felib.packet.particle;

import java.lang.reflect.Constructor;

import org.bukkit.entity.Player;

import me.felnstaren.felib.FeLib;
import me.felnstaren.felib.logger.Level;
import me.felnstaren.felib.packet.enums.PacketParticleType;
import me.felnstaren.felib.reflect.Packeteer;
import me.felnstaren.felib.reflect.Reflector;

public class PacketParticle {

	public float vx, vy, vz;
	public float speed;
	public int count = 0;  //Count must be set to 0 for direction to work
 	public PacketParticleType type;
 	
 	private Object packet;

	public PacketParticle(PacketParticleType type, float speed, float vx, float vy, float vz) {
		this.type = type;
		this.speed = speed;
		this.vx = vx;
		this.vy = vy;
		this.vz = vz;
	}
	
	public PacketParticle build(double offx, double offy, double offz) {
		this.packet = Reflector.newInstanceOf(PACKET_PARTICLE_CONSTRUCTOR, getParticleParam(), false, offx, offy, offz, vx, vy, vz, speed, count);
		return this;
	}
	
	public void send(Player player) {
		if(packet == null) {
			FeLib.LOGGER.log(Level.WARNING, "Attempted to send unbuilt packet!");
			return;
		}
		
		Packeteer.sendClientPacket(player, packet);
	}
	
	protected Object getParticleParam() {
		return Reflector.getDeclaredStaticFieldValue("Particles", type.name());
	}
	
	
	
	private static final Constructor<?> PACKET_PARTICLE_CONSTRUCTOR = Reflector.getConstructor("PacketPlayOutWorldParticles", Reflector.getNMSClass("ParticleParam"), boolean.class, double.class, double.class, double.class, float.class, float.class, float.class, float.class, int.class);
	
}
