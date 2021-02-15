package me.felnstaren.felib.packet.enums;

import me.felnstaren.felib.util.BitMaskUtil;

public enum ByteInfo {

	ENTITY_IS_ON_FIRE(0),
	ENTITY_IS_CROUDHING(1),
	ENTITY_IS_RIDING_UNUSED(2),
	ENTITY_IS_SPRINTING(3),
	ENTITY_IS_SWIMMING(4),
	ENTITY_IS_INVISIBLE(5),
	ENTITY_IS_GLOWING(6),
	ENTITY_IS_GLIDING(7),
	
	ABSTRACT_ARROW_IS_CRITICAL(0),
	ABSTRACT_ARROW_IS_NOCLIP(1),
	
	LIVING_ENTITY_IS_HAND_ACTIVE(0),
	LIVING_ENTITY_ACTIVE_HAND(1),
	LIVING_ENTITY_IS_RIPTIDING(2),
	
	PLAYER_SKIN_CAPE_ENABLED(0),
	PLAYER_SKIN_JACKET_ENABLED(1),
	PLAYER_SKIN_LEFT_SLEEVE_ENABLED(2),
	PLAYER_SKIN_RIGHT_SLEEVE_ENABLED(3),
	PLAYER_SKIN_LEFT_PANTALONE_ENABLED(4),
	PLAYER_SKIN_RIGHT_PANTALONE_ENABLED(5),
	PLAYER_SKIN_HAT_ENABLED(6),
	PLAYER_SKIN_UNUSED(7),
	
	PLAYER_MAIN_HAND(0),
	
	ARMOR_STAND_IS_SMALL(0),
	ARMOR_STAND_HAS_ARMS(1),
	ARMOR_STAND_HASNT_BASEPLATE(2),
	ARMOR_STAND_IS_MARKER(3),
	
	MOB_HASNT_AI(0),
	MOB_IS_LEFT_HANDED(1),
	MOB_IS_AGRESSIVE(2),
	
	BAT_IS_HANGING(0),
	
	ABSTRACT_HORSE_UNUSED(0),
	ABSTRACT_HORSE_IS_TAME(1),
	ABSTRACT_HORSE_IS_SADDLED(2),
	ABSTRACT_HORSE_HAS_BRED(3),
	ABSTRACT_HORSE_IS_EATING(4),
	ABSTRACT_HORSE_IS_REARING(5),
	ABSTRACT_HORSE_IS_MOUTH_OPEN(6),
	
	BEE_UNUSED(0),
	BEE_IS_ANGRY(1),
	BEE_HAS_STUNG(2),
	BEE_HAS_NECTAR(3),
	
	FOX_IS_SITTING(0),
	FOX_UNUSED(1),
	FOX_IS_CROUCHING(2),
	FOX_IS_INTERESTED(3),
	FOX_IS_POUNCING(4),
	FOX_IS_SLEEPING(5),
	FOX_IS_FACEPLANTED(6),
	FOX_IS_DEFENDING(7),
	
	PANDA_UNUSED(0),
	PANDA_IS_SNEEZING(1),
	PANDA_IS_ROLLING(2),
	PANDA_IS_SITTING(3),
	PANDA_IS_ON_BACK(4),
	
	SHEEP_COLOR_BIT_A(0),
	SHEEP_COLOR_BIT_B(1),
	SHEEP_COLOR_BIT_C(2),
	SHEEP_COLOR_BIT_D(3),
	SHEEP_IS_SHEARED(4),
	
	TAMEABLE_ANIMAL_IS_SITTING(0),
	TAMEABLE_ANIMAL_UNUSED(1),
	TAMEABLE_ANIMAL_IS_TAMED(2),
	
	IRON_GOLEM_IS_PLAYER_CREATED(0),
	
	SNOW_GOLEM_HAS_PUMPKIN_HAT(4),
	
	BLAZE_IS_ON_FIRE(0),
	
	VEX_IS_ATTACKING(0),
	
	SPIDER_IS_CLIMBING(0);
	
	
	
	private int bit_index;
	
	private ByteInfo(int bit_index) {
		this.bit_index = bit_index;
	}
	
	public byte enable(byte value) {
		return BitMaskUtil.enable(value, bit_index);
	}
	
	public byte disable(byte value) {
		return BitMaskUtil.disable(value, bit_index);
	}
	
	public byte toggle(byte value) {
		return BitMaskUtil.toggle(value, bit_index);
	}
	
	public boolean get(byte value) {
		return BitMaskUtil.get(value, bit_index);
	}
	
	public static byte createWithSet(ByteInfo... set_bits) {
		return enable((byte) 0, set_bits);
	}
	
	public static byte enable(byte value, ByteInfo... set_bits) {
		for(ByteInfo set_bit : set_bits) value = set_bit.enable(value);
		return value;
	}
	
	public static byte disable(byte value, ByteInfo... unset_bits) {
		for(ByteInfo unset_bit : unset_bits) value = unset_bit.disable(value);
		return value;
	}
	
	public static byte toggle(byte value, ByteInfo... toggle_bits) {
		for(ByteInfo toggle_bit : toggle_bits) value = toggle_bit.toggle(value);
		return value;
	}
	
}
