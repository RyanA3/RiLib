package me.felnstaren.felib.packet.particle;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import me.felnstaren.felib.packet.enums.PacketParticleType;
import me.felnstaren.felib.reflect.Reflector;

public class PacketParticleItem extends PacketParticle {

	private int item;
	
	public PacketParticleItem(float speed, float vx, float vy, float vz, int item) {
		super(PacketParticleType.ITEM, speed, vx, vy, vz);
		this.item = item;
	}

	@Override
	protected Object getParticleParam() {
		Object type = super.getParticleParam();
		Object imat = Reflector.invokeMethod(GET_ITEM_BY_ID_METHOD, Reflector.getNMSClass("Item"), item);
		Object itemstack = Reflector.newInstanceOf(ITEM_STACK_CONSTRUCTOR, imat);
		return Reflector.newInstanceOf(PARTICLE_PARAM_ITEM_CONSTRUCTOR, type, itemstack);
	}
	
	
	
	private static final Method GET_ITEM_BY_ID_METHOD = Reflector.getDeclaredMethod(Reflector.getNMSClass("Item"), "getById", int.class);
	private static final Constructor<?> ITEM_STACK_CONSTRUCTOR = Reflector.getConstructor("ItemStack", Reflector.getNMSClass("Item"));
	private static final Constructor<?> PARTICLE_PARAM_ITEM_CONSTRUCTOR = Reflector.getConstructor("ParticleParamItem", Reflector.getNMSClass("ParticleType"), Reflector.getNMSClass("ItemStack"));
	
}
