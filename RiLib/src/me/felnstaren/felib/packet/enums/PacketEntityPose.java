package me.felnstaren.felib.packet.enums;

import me.felnstaren.felib.reflect.Reflector;

public enum PacketEntityPose {

	STANDING, 
	FALL_FLYING, 
	SLEEPING, 
	SWIMMING, 
	SPIN_ATTACK, 
	CROUCHING, 
	DYING;
	
	public Object getNMSPose() {
		return Reflector.getNMSClass("EntityPose").getEnumConstants()[this.ordinal()];
	}
	
}
