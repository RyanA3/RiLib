package me.felnstaren.felib.packet.enums;

import java.util.Optional;

import me.felnstaren.felib.reflect.Reflector;
import me.felnstaren.felib.util.PrimitiveUtil;

public enum MetadataValue {

	ENTITY_INFO_BYTE(0, byte.class, (byte) 0b00000000),
	ENTITY_AIR_TICKS(1, int.class, 300),
	ENTITY_CUSTOM_NAME(2, Optional.class, null, PacketSerializer.OPTIONAL_CHAT_COMPONENT),
	ENTITY_IS_CUSTOM_NAME_VISIBLE(3, boolean.class, false),
	ENTITY_IS_SILENT(4, boolean.class, false),
	ENTITY_IS_NO_GRAVITY(5, boolean.class, false),
	ENTITY_POSE(6, Reflector.getNMSClass("EntityPose"), PacketEntityPose.STANDING.getNMSPose(), PacketSerializer.ENTITY_POSE),
	
	FALLING_BLOCK_BLOCK_POSE(7, Reflector.getNMSClass("BlockPosition"), Reflector.newInstanceOf("BlockPosition", 0, 0, 0), PacketSerializer.BLOCK_POSITION),
	
	AREA_AFFECT_CLOUD_RADIUS(7, float.class, 0.5f),
	AREA_AFFECT_CLOUD_COLOR(8, int.class, 0),
	AREA_AFFECT_CLOUD_SHOULD_IGNORE_RADIUS(9, boolean.class, false),
	AREA_AFFECT_CLOUD_PARTICLE(10, Reflector.getNMSClass("ParticleParam"), Reflector.getDeclaredStaticFieldValue("Particles", "EFFECT"), PacketSerializer.PARTICLE_PARAM),
	
	ABSTRACT_ARROW_INFO_BYTE(7, byte.class, (byte) 0b00000000),
	ABSTRACT_ARROW_PIERCING_LEVEL(8, byte.class, (byte) 0b00000000),
	
	ARROW_COLOR(9, int.class, 0),
	
	THROWN_TRIDENT_LOYALTY_LEVEL(9, int.class, 0),
	THROWN_TRIDENT_HAS_ENCHANTMENT_GLINT(10, boolean.class, false),
	
	BOAT_TIME_SINCE_LAST_HIT(7, int.class, 0),
	BOAT_FORWARD_DIRECTION(8, int.class, 1),
	BOAT_DAMAGE_TAKEN(9, float.class, 0.0f),
	BOAT_TYPE(10, int.class, 0),
	BOAT_IS_LEFT_PADDLE_TURNING(11, boolean.class, false),
	BOAT_IS_RIGHT_PADDLE_TURNING(12, boolean.class, false),
	BOAT_SPLASH_TIMER(13, int.class, 0),
	
	END_CRYSTAL_BLOCK_POS(7, Optional.class, null, PacketSerializer.OPTIONAL_BLOCK_POSITION),
	END_CRYSTAL_SHOULD_SHOW_BOTTOM(8, boolean.class, true),
	
	WITHER_SKULL_IS_INVULNERABLE(7, boolean.class, false),
	
	LIVING_ENTITY_INFO_BYTE(7, byte.class, (byte) 0b00000000),
	LIVING_ENTITY_HEALTH(8, float.class, 1.0f),
	LIVING_ENTITY_POTION_EFFECT_COLOR(9, int.class, 0),
	LIVING_ENTITY_IS_POTION_EFFECT_AMBIENT(10, boolean.class, false),
	LIVING_ENTITY_NUMBER_ARROWS(11, int.class, 0),
	LIVING_ENTITY_HEATH_ADDED_BY_ABSORBTION(12, int.class, 0),
	LIVING_ENTITY_BED_BLOCK_POS(13, Optional.class, null, PacketSerializer.OPTIONAL_BLOCK_POSITION),
	
	PLAYER_ADDITIONAL_HEARTS(14, float.class, 0.0f),
	PLAYER_SCORE(15, int.class, 0),
	PLAYER_SKIN_INFO_BYTE(16, byte.class, (byte) 0b00000000),
	PlAYER_MAIN_HAND_BYTE(17, byte.class, (byte) 0),
	PLAYER_LEFT_SHOULDER_PARROT_DATA(18, Reflector.getNMSClass("NBTTagCompound"), null, PacketSerializer.NBT_TAG_COMPOUND),
	PLAYER_RIGHT_SHOULDER_PARROT_DATA(19, Reflector.getNMSClass("NBTTagCompound"), null, PacketSerializer.NBT_TAG_COMPOUND),
	
	ARMOR_STAND_INFO_BYTE(14, byte.class, (byte) 0b00000000),
	ARMOR_STAND_HEAD_ROTATION(15, Reflector.getNMSClass("Vector3f"), Reflector.newInstanceOf("Vector3f", 0.0f, 0.0f, 0.0f), PacketSerializer.VECTOR_3F),
	ARMOR_STAND_BODY_ROTATION(16, Reflector.getNMSClass("Vector3f"), Reflector.newInstanceOf("Vector3f", 0.0f, 0.0f, 0.0f), PacketSerializer.VECTOR_3F),
	ARMOR_STAND_LEFT_ARM_ROTATION(17, Reflector.getNMSClass("Vector3f"), Reflector.newInstanceOf("Vector3f", -10.0f, 0.0f, -10.0f), PacketSerializer.VECTOR_3F),
	ARMOR_STAND_RIGHT_ARM_ROTATION(18, Reflector.getNMSClass("Vector3f"), Reflector.newInstanceOf("Vector3f", -15.0f, 0.0f, 10.0f), PacketSerializer.VECTOR_3F),
	ARMOR_STAND_LEFT_LEG_ROTATION(19, Reflector.getNMSClass("Vector3f"), Reflector.newInstanceOf("Vector3f", -1.0f, 0.0f, -1.0f), PacketSerializer.VECTOR_3F),
	ARMOR_STAND_RIGHT_LEG_ROTATION(20, Reflector.getNMSClass("Vector3f"), Reflector.newInstanceOf("Vector3f", 1.0f, 0.0f, 1.0f), PacketSerializer.VECTOR_3F),
	
	MOB_INFO_BYTE(14, byte.class, 0b00000000),
	
	BAT_IS_HANGING_BYTE(15, byte.class, 0b00000000),
	
	DOLPHIN_TREASURE_POSITION(15, int.class, 0),
	DOLPHIN_CAN_FIND_TREASURE(16, boolean.class, false),
	DOLPHIN_HAS_FISH(17, boolean.class, false),
	
	ABSTRACT_FISH_IS_FROM_BUCKET(15, boolean.class, false),
	PUFFER_FISH_PUFF_STATE(16, int.class, 0),
	TROPICAL_FISH_VARIANT(16, int.class, 0),
	
	AGEABLE_MOB_IS_BABY(15, boolean.class, false),
	
	ABSTRACT_HORSE_INFO_BYTE(16, byte.class, (byte) 0b00000000),
	ABSTRACT_HORSE_OWNER(17, Optional.class, null, PacketSerializer.OPTIONAL_UUID),
	HORSE_VARIANT(18, int.class, 0),
	CHESTED_HORSE_HAS_CHEST(18, boolean.class, false),
	LLAMA_INVENTORY_COLUMNS(19, int.class, 0),
	LLAMA_CARPET_COLOR(20, int.class, -1),
	LLAMA_VARIANT(21, int.class, 0),
	
	BEE_INFO_BYTE(16, byte.class, (byte) 0b00000000),
	BEE_ANGER_TIME(17, int.class, 0),
	
	FOX_VARIANT(16, int.class, 0),
	FOX_INFO_BYTE(17, byte.class, 0b00000000),
	FOX_FIRST_UUID(18, Optional.class, null, PacketSerializer.OPTIONAL_UUID),
	FOX_SECOND_UUID(19, Optional.class, null, PacketSerializer.OPTIONAL_UUID),
	
	OCELOT_IS_TRUSTING(16, boolean.class, false),
	
	PANDA_BREED_TIMER(16, int.class, 0),
	PANDA_SNEEZE_TIMER(17, int.class, 0),
	PANDA_EAT_TIMER(18, int.class, 0),
	PANDA_MAIN_GENE(19, byte.class, (byte) 0b00000000),
	PANDA_HIDDEN_GENE(20, byte.class, (byte) 0b00000000),
	PANDA_INFO_BYTE(21, byte.class, (byte) 0b00000000),
	
	PIG_HAS_SADDLE(16, boolean.class, false),
	PIG_BOOST_TIME(17, int.class, 0),
	
	RABBIT_TYPE(16, int.class, 0),
	
	TURTLE_HOME_POSITION(16, Reflector.getNMSClass("BlockPosition"), Reflector.newInstanceOf("BlockPosition", 0, 0, 0), PacketSerializer.BLOCK_POSITION),
	TURTLE_HAS_EGG(17, boolean.class, false),
	TURTLE_IS_LAYING_EGG(18, boolean.class, false),
	TURTLE_TRAVEL_POSITION(19, Reflector.getNMSClass("BlockPosition"), Reflector.newInstanceOf("BlockPosition", 0, 0, 0), PacketSerializer.BLOCK_POSITION),
	TURTLE_IS_GOING_HOME(20, boolean.class, false),
	TURTLE_IS_TRAVELLING(21, boolean.class, false),
	
	POLAR_BEAR_IS_STANDING_UP(16, boolean.class, false),
	
	HOGLIN_IS_IMMUNE_TO_ZOMBIFICATION(16, boolean.class, false),
	
	MOOSHROOM_VARIANT(16, String.class, "red"),
	
	SHEEP_INFO_BYTE(16, byte.class, (byte) 0b00000000),
	
	STRIDER_BOOST_TIME(16, int.class, 0),
	STRIDER_IS_SHAKING(17, boolean.class, false),
	STRIDER_HAS_SADDLE(18, boolean.class, false),
	
	TAMEABLE_ANIMAL_INFO_BYTE(16, byte.class, (byte) 0b00000000),
	TAMEABLE_ANIMAL_OWNER(17, Optional.class, null, PacketSerializer.OPTIONAL_UUID),
	
	CAT_VARIANT(18, int.class, 1),
	CAT_IS_LAYING(19, boolean.class, false),
	CAT_IS_RELAXED(20, boolean.class, false),
	CAT_COLLAR_COLOR(21, int.class, 14),
	
	WOLF_IS_BEGGING(18, boolean.class, false),
	WOLF_COLLAR_COLOR(19, int.class, 14),
	WOLF_ANGER_TIME(20, int.class, 0),
	
	PARROT_VARIANT(18, int.class, 0),
	
	ABSTRACT_VILLAGER_HEAD_SHAKE_TIME(16, int.class, 0),
	VILLAGER_VILLAGER_DATA(17, Reflector.getNMSClass("VillagerData"), null, PacketSerializer.VILLAGER_DATA),
	
	IRON_GOLEM_INFO_BYTE(15, byte.class, (byte) 0b00000000),
	SNOW_GOLEM_INFO_BYTE(15, byte.class, (byte) 0b00000000),
	
	SHULKER_DIRECTION(15, Reflector.getNMSClass("EnumDirection"), Reflector.getNMSClass("EnumDirection").getEnumConstants()[0], PacketSerializer.ENUM_DIRECTION),
	SHULKER_ATTACHMENT_POSITION(16, Optional.class, null, PacketSerializer.OPTIONAL_INTEGER),
	SHULKER_SHIELD_HEIGHT(17, byte.class, (byte) 0b00000000),
	SHULKER_COLOR(18, byte.class, (byte) 10),
	
	BASE_PIGLIN_IS_IMMUNE_TO_ZOMBIFICATION(15, boolean.class, false),
	PIGLIN_IS_BABY(16, boolean.class, false),
	PIGLIN_IS_CHARGING_CROSSBOW(17, boolean.class, false),
	PIGLIN_IS_DANCING(18, boolean.class, false),
	
	BLAZE_IS_ON_FIRE(15, byte.class, (byte) 0b00000000),
	
	CREEPER_STATE(15, int.class, -1),
	CREEPER_IS_CHARGED(16, boolean.class, false),
	CREEPER_IS_IGNITED(17, boolean.class, false),
	
	GUARDIAN_IS_RETRACTING_SPIKES(15, boolean.class, false),
	GUARDIAN_TARGET_ENTITY_ID(16, int.class, 0),
	
	RAIDER_IS_CELEBRATING(15, boolean.class, false),
	
	SPELLCASTER_ILLAGER_SPELL_CASTED(16, byte.class, (byte) 0),
	
	WITCH_IS_DRINKING_POTION(16, boolean.class, false),
	
	VEX_IS_ATTACKING(15, byte.class, (byte) 0b00000000),
	
	SPIDER_IS_CLIMBING(15, byte.class, (byte) 0b00000000),
	
	WITHER_CENTER_HEAD_TARGET_ENTITY_ID(15, int.class, 0),
	WITHER_LEFT_HEAD_TARGET_ENTITY_ID(16, int.class, 0),
	WITHER_RIGHT_HEAD_TARGET_ENTITY_ID(17, int.class, 0),
	WITHER_INVULNERABLE_TIME(18, int.class, 0),
	
	ZOGLIN_IS_BABY(15, boolean.class, false),
	
	ZOMBIE_IS_BABY(15, boolean.class, false),
	ZOMBIE_TYPE_UNUSED(16, int.class, 0),
	ZOMBIE_IS_BECOMING_DROWNED(17, boolean.class, false),
	
	ZOMBIE_VILLAGER_IS_CONVERTING(18, boolean.class, false),
	ZOMBIE_VILLAGER_VILLAGER_DATA(19, Reflector.getNMSClass("VillagerData"), null, PacketSerializer.VILLAGER_DATA),
	
	ENDERMAN_CARRIED_BLOCK_ID(15, Optional.class, null, PacketSerializer.OPTIONAL_INTEGER),
	ENDERMAN_IS_SCREAMING(16, boolean.class, false),
	ENDERMAN_IS_STARING(17, boolean.class, false),
	
	ENDER_DRAGON_PHASE(15, int.class, 10),
	
	GHAST_IS_ATTACKING(15, boolean.class, false),
	
	PHANTOM_SIZE(15, int.class, 0),
	
	SLIME_SIZE(15, int.class, 1),
	
	ABSTRACT_MINECART_SHAKING_POWER(7, int.class, 0),
	ABSTRACT_MINECART_SHAKING_DIRECTION(8, int.class, 1),
	ABSTRACT_MINECART_SHAKING_MULTIPLIER(9, float.class, 0.0f),
	ABSTRACT_MINECART_CUSTOM_BLOCK_ID(10, int.class, 0),
	ABSTRACT_MINECART_CUSTOM_BLOCK_Y_POS(11, int.class, 6), //In 16ths of a block
	ABSTRACT_MINECART_SHOULD_SHOW_CUSTOM_BLOCK(12, boolean.class, false),
	MINECART_FURNACE_HAS_FUEL(13, boolean.class, false),
	MINECART_COMMAND_BLOCK_COMMAND(13, String.class, ""),
	MINECART_COMMAND_BLOCK_LAST_OUTPUT(14, String.class, "{\"text\":\"\"}"),
	
	PRIMED_TNT_FUSE_TIME(7, int.class, 80);
	
	
	
	
	private Class<?> type;
	private int index;
	private Object defalt;
	private PacketSerializer serializer;
	
	private MetadataValue(int index, Class<?> type, Object defalt) {
		this.index = index;
		this.type = type;
		this.defalt = defalt;
		this.serializer = PacketSerializer.getForType(PrimitiveUtil.getWrapperVersion(type));
	}
	
	private MetadataValue(int index, Class<?> type, Object defalt, PacketSerializer serializer) {
		this.index = index;
		this.type = type;
		this.defalt = defalt;
		this.serializer = serializer;
	}
	
	public Object getDefaultValue() {
		return defalt;
	}
	
	public int getIndex() {
		return index;
	}
	
	public Class<?> getType() {
		return type;
	}
	
	public PacketSerializer getSerializer() {
		return serializer;
	}
	
}
