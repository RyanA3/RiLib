package me.felnstaren.felib.packet.particle;

import me.felnstaren.felib.FeLib;
import me.felnstaren.felib.logger.Level;
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
		Object param;
		
		try {
			param = Reflector.CONSTRUCTOR_CACHE.get("ParticleParamRedstone").newInstance(red, green, blue, size);
		} catch (Exception e) {
			FeLib.LOGGER.log(Level.DEBUG, "Failed to get ParticleParamRedstone for " + red + "," + green + "," + blue + "," + size);
			param = super.getParticleParam();
		}
		
		return param;
	}

}
