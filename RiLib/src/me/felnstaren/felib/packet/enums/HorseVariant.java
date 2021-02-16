package me.felnstaren.felib.packet.enums;

public enum HorseVariant {

	COLOR_WHITE,
	COLOR_CREAMY,
	COLOR_CHESTNUT,
	COLOR_BROWN,
	COLOR_BLACK,
	COLOR_GRAY,
	COLOR_DARK_BROWN,
	
	MARKING_NONE,
	MARKING_STOCKINGS_AND_BLAZE,
	MARKING_WHITE_PATCHES,
	MARKING_WHITE_DOTS,
	MARKING_SOOTY;
	
	public static int getVariant(HorseVariant color, HorseVariant markings) {
		return ((markings.ordinal() - 7) * 256) + color.ordinal();
	}
	
}
