package me.felnstaren.felib.packet.particle;

import java.lang.reflect.Constructor;

import me.felnstaren.felib.packet.enums.PacketParticleType;
import me.felnstaren.felib.reflect.Reflector;

public class PacketParticleDust extends PacketParticle {
	
	private float red, green, blue, size;

	public PacketParticleDust(float speed, float vx, float vy, float vz, float red, float green, float blue, float size) {
		super(PacketParticleType.DUST, speed, vx, vy, vz);
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.size = size;
	}
	
	@Override
	protected Object getParticleParam() {
		return Reflector.newInstanceOf(PARTICLE_PARAM_REDSTONE_CONSTRUCTOR, red, green, blue, size);
	}
	
	
	
	private static final Constructor<?> PARTICLE_PARAM_REDSTONE_CONSTRUCTOR = Reflector.getConstructor("ParticleParamRedstone", float.class, float.class, float.class, float.class);
	
}
