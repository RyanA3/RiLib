package me.felnstaren.rilib.particle;

import java.lang.reflect.Field;

import me.felnstaren.rilib.RiLib;
import me.felnstaren.rilib.logger.Level;
import me.felnstaren.rilib.reflect.Reflector;

public class PacketParticleItem extends PacketParticle {

	private int item;
	
	public PacketParticleItem(float speed, float vx, float vy, float vz, int item) {
		super(PacketParticleType.ITEM, speed, vx, vy, vz);
		this.item = item;
	}

	@Override
	protected Object getParticleParam() {
		Object param = null;
		
		try {
			Field particlefield = Reflector.getNMSClass("Particles").getField(type.name());
			Object particle = particlefield.get(Reflector.getNMSClass("Particles"));
			
			Object imat = Reflector.METHOD_CACHE.get("getById").invoke(null, item);
			Object itemstack = Reflector.CONSTRUCTOR_CACHE.get(Reflector.getNMSClass("ItemStack")).newInstance(imat);
			param = Reflector.CONSTRUCTOR_CACHE.get(Reflector.getNMSClass("ParticleParamItem")).newInstance(particle, itemstack);
		} catch(Exception e) {
			RiLib.LOGGER.log(Level.DEBUG, "Failed to get ParticleParamItem for " + item);
			param = super.getParticleParam();
		}
		
		return param;
	}
	
}
