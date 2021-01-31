package me.felnstaren.felib.particle;

import java.lang.reflect.Field;

import me.felnstaren.felib.FeLib;
import me.felnstaren.felib.logger.Level;
import me.felnstaren.felib.reflect.Reflector;

public class PacketParticleBlock extends PacketParticle {

	private String blockname;
	
	public PacketParticleBlock(PacketParticleType type, float speed, float vx, float vy, float vz, String blockname) {
		super(type, speed, vx, vy, vz);
		if(type != PacketParticleType.BLOCK && type != PacketParticleType.FALLING_DUST) FeLib.LOGGER.log(Level.WARNING, "PacketParticleBlock should not be used for particle type " + type.name() + "!");
		this.blockname = blockname;
	}
	
	@Override
	protected Object getParticleParam() {
		Object param = null;
		
		try {
			Field particlefield = Reflector.getNMSClass("Particles").getField(type.name());
			Object particle = particlefield.get(Reflector.getNMSClass("Particles"));
			
			Field blockfield = Reflector.getNMSClass("Blocks").getField(blockname);
			Object block = blockfield.get(Reflector.getNMSClass("Blocks"));
			
			Field blockdatafield = Reflector.getNMSClass("Block").getDeclaredField("blockData");
			blockdatafield.setAccessible(true);
			Object blockdata = blockdatafield.get(block);
			param = Reflector.CONSTRUCTOR_CACHE.get("ParticleParamBlock").newInstance(particle, blockdata);
		} catch(Exception e) {
			FeLib.LOGGER.log(Level.DEBUG, "Failed to get ParticleParamBlock for " + blockname);
			param = super.getParticleParam();
		}
		
		return param;
	}

}
