package me.felnstaren.felib.packet.particle;

import java.lang.reflect.Constructor;

import me.felnstaren.felib.FeLib;
import me.felnstaren.felib.logger.Level;
import me.felnstaren.felib.packet.enums.PacketParticleType;
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
		Object type = super.getParticleParam();
		Object block = Reflector.getDeclaredStaticFieldValue("Blocks", blockname);
		Object blockdata = Reflector.getDeclaredFieldValue(block, "blockData");
		
		return Reflector.newInstanceOf(PARTICLE_PARAM_BLOCK_CONSTRUCTOR, type, blockdata);
	}
	
	
	
	private static final Constructor<?> PARTICLE_PARAM_BLOCK_CONSTRUCTOR = Reflector.getConstructor("ParticleParamBlock", Reflector.getNMSClass("ParticleType"), Reflector.getNMSClass("IBlockData"));

}
