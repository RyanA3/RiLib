package me.felnstaren.rilib.particle;

import java.lang.reflect.Field;

import me.felnstaren.rilib.RiLib;
import me.felnstaren.rilib.logger.Level;
import me.felnstaren.rilib.reflect.Reflector;

public class PacketParticleBlock extends PacketParticle {

	private String blockname;
	
	public PacketParticleBlock(float speed, float vx, float vy, float vz, String blockname) {
		super(PacketParticleType.BLOCK, speed, vx, vy, vz);
		this.blockname = blockname;
	}
	
	@Override
	protected Object getParticleParam() {
		Object param = null;
		
		try {
			Field particlefield = Reflector.getNMSClass("Particles").getField("BLOCK");
			Object particle = particlefield.get(Reflector.getNMSClass("Particles"));
		
			Field blockfield = Reflector.getNMSClass("Blocks").getField(blockname);
			Object block = blockfield.get(Reflector.getNMSClass("Blocks"));
			
			Field blockdatafield = Reflector.getNMSClass("Block").getDeclaredField("blockData");
			blockdatafield.setAccessible(true);
			Object blockdata = blockdatafield.get(block);
			param = Reflector.CONSTRUCTOR_CACHE.get(Reflector.getNMSClass("ParticleParamBlock")).newInstance(particle, blockdata);
		} catch(Exception e) {
			RiLib.LOGGER.log(Level.DEBUG, "Failed to get ParticleParamBlock for " + blockname);
			param = super.getParticleParam();
		}
		
		return param;
	}

}
