package me.felnstaren.felib.particle;

import java.lang.reflect.Field;

import org.bukkit.entity.Player;

import me.felnstaren.felib.FeLib;
import me.felnstaren.felib.logger.Level;
import me.felnstaren.felib.reflect.Packeteer;
import me.felnstaren.felib.reflect.Reflector;

public class PacketParticle {

	public float vx, vy, vz;
	public float speed;
	public int count = 0;  //Count must be set to 0 for direction to work
 	public PacketParticleType type;
	
	public PacketParticle(PacketParticleType type, float speed, float vx, float vy, float vz) {
		this.type = type;
		this.speed = speed;
		this.vx = vx;
		this.vy = vy;
		this.vz = vz;
	}
	
	public void send(Player player, double offx, double offy, double offz) {
		try {
			Object packet = Reflector.CONSTRUCTOR_CACHE.get("PacketPlayOutWorldParticles").newInstance(getParticleParam(), false, offx, offy, offz, vx, vy, vz, speed, count);
			Packeteer.sendClientPacket(player, packet);
		} catch (Exception e) {
			e.printStackTrace();
			FeLib.LOGGER.log(Level.WARNING, "Failure sending Particle " + type.name());
		}
	}
	
	protected Object getParticleParam() {
		Object ptype = null;
		
		try {
			Field pfield = Reflector.getNMSClass("Particles").getDeclaredField(type.name());
			ptype = pfield.get(Reflector.getNMSClass("Particles"));
		} catch (Exception e) {
			e.printStackTrace();
			FeLib.LOGGER.log(Level.SEVERE, "Failure getting Particle Param " + type.name());
		}
		
		return ptype;
	}
	
}
