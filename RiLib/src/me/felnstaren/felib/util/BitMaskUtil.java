package me.felnstaren.felib.util;

public class BitMaskUtil {
	
	public static byte enable(byte mask, byte values) {
		return (byte) (mask | values);
	}
	
	public static byte disable(byte mask, byte values) {
		return (byte) (mask & ~values);
	}
	
	public static byte toggle(byte mask, byte values) {
		return (byte) (mask ^ values);
	}
	
	
	/**
	 * Enables the xth bit
	 */
	public static byte enable(byte mask, int bit_index) {
		byte bit = (byte) (1 << bit_index);
		return enable(mask, bit);
	}
	
	/**
	 * Disables the xth bit
	 */
	public static byte disable(byte mask, int bit_index) {
		byte bit = (byte) (1 << bit_index);
		return disable(mask, bit);
	}
	
	/**
	 * Toggles the xth bit
	 */
	public static byte toggle(byte mask, int bit_index) {
		byte bit = (byte) (1 << bit_index);
		return toggle(mask, bit);
	}
	
	/**
	 * Gets the xth bit, true if 1, false if 0
	 */
	public static boolean get(byte mask, int bit_index) {
		byte bit = (byte) (1 << bit_index);
		byte got = (byte) (mask & bit);
		return got == bit;
	}
}
